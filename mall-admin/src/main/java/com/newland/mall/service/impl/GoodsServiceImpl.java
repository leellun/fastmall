package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.config.ConfigHelper;
import com.newland.mall.entity.*;
import com.newland.mall.enumeration.BasicEnum;
import com.newland.mall.enums.CollectTypeEnum;
import com.newland.mall.mapper.GoodsMapper;
import com.newland.mall.model.dto.GoodsAllinoneDTO;
import com.newland.mall.model.dto.GoodsProductDTO;
import com.newland.mall.model.vo.BrandAndCategoryVO;
import com.newland.mall.model.vo.BrandVO;
import com.newland.mall.model.vo.CateVO;
import com.newland.mall.model.vo.GoodsAllinoneVO;
import com.newland.mall.model.vo.wx.CommentVO;
import com.newland.mall.model.vo.wx.GoodsDetailVO;
import com.newland.mall.model.vo.wx.GoodsSpecificationVO;
import com.newland.mall.service.*;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 商品基本信息表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Autowired
    private GoodsProductService goodsProductService;
    @Autowired
    private GoodsSpecService goodsSpecService;
    @Autowired
    private GoodsProductSpecRelationService goodsProductSpecRelationService;
    @Autowired
    private GoodsAttrValueService goodsAttrValueService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CartService cartService;
    @Autowired
    private IssueService issueService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private GrouponRulesService grouponRulesService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private ConfigHelper configHelper;

    @Override
    public PageInfo<Goods> list(Integer goodsId, String goodsSn, String name, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listGoods(goodsId, goodsSn, name));
    }

    @Override
    public GoodsAllinoneVO getDetail(Long id) {
        Goods goods = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(goods, "商品不存在");
        List<GoodsProduct> products = goodsProductService.listGoodsProducts(id);
        List<GoodsSpec> specifications = goodsSpecService.listGoodsSpecifications(id);
        List<GoodsAttrValue> attributes = goodsAttrValueService.listGoodsAttributes(id);

        Long categoryId = goods.getCategoryId();
        Category category = categoryService.getById(categoryId);
        Long[] categoryIds = new Long[]{};
        if (category != null) {
            Long parentCategoryId = category.getPid();
            categoryIds = new Long[]{parentCategoryId, categoryId};
        }
        GoodsAllinoneVO vo = new GoodsAllinoneVO();
        vo.setGoods(goods);
        vo.setAttributes(attributes);
        vo.setProducts(products);
        vo.setCategoryIds(Arrays.asList(categoryIds));
        vo.setSpecifications(specifications);
        return vo;
    }

    @Override
    public BrandAndCategoryVO listCatAndBrands() {
        List<Category> l1CatList = categoryService.listL1();
        // 管理员设置“所属分类”
        List<CateVO> categoryList = new ArrayList<>(l1CatList.size());

        for (Category l1 : l1CatList) {
            CateVO l1CatVo = new CateVO();
            l1CatVo.setValue(l1.getId());
            l1CatVo.setLabel(l1.getName());

            List<Category> l2CatList = categoryService.listByPid(l1.getId());
            List<CateVO> children = new ArrayList<>(l2CatList.size());
            for (Category l2 : l2CatList) {
                CateVO l2CatVo = new CateVO();
                l2CatVo.setValue(l2.getId());
                l2CatVo.setLabel(l2.getName());
                children.add(l2CatVo);
            }
            l1CatVo.setChildren(children);
            categoryList.add(l1CatVo);
        }

        // 管理员设置“所属品牌商”
        List<Brand> list = brandService.all();
        List<BrandVO> brandList = new ArrayList<>(l1CatList.size());
        for (Brand brand : list) {
            BrandVO b = new BrandVO();
            b.setLabel(brand.getName());
            b.setValue(brand.getId());
            brandList.add(b);
        }

        BrandAndCategoryVO data = new BrandAndCategoryVO();
        data.setBrandList(brandList);
        data.setCategoryList(categoryList);
        return data;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(GoodsAllinoneDTO goodsAllinone) {
        validate(goodsAllinone);

        Goods goods = goodsAllinone.getGoods();
        GoodsAttrValue[] attributes = goodsAllinone.getAttributes();
        GoodsSpec[] specifications = goodsAllinone.getSpecifications();
        GoodsProductDTO[] products = goodsAllinone.getGoodsProducts();

        String name = goods.getName();
        AssertUtil.notNull(baseMapper.getByNameAndSale(name, BasicEnum.YES.getKey()), "商品名已经存在");

        // 商品表里面有一个字段retailPrice记录当前商品的最低价
        BigDecimal retailPrice = new BigDecimal(Integer.MAX_VALUE);
        for (GoodsProductDTO productDTO : products) {
            BigDecimal productPrice = productDTO.getGoodsProduct().getPrice();
            if (retailPrice.compareTo(productPrice) > 0) {
                retailPrice = productPrice;
            }
        }
        goods.setRetailPrice(retailPrice);

        // 商品基本信息表_goods
        baseMapper.insert(goods);

        Map<String, GoodsSpec> specMap = new HashMap<>();
        // 商品规格表_goods_specification
        for (GoodsSpec specification : specifications) {
            specification.setGoodsId(goods.getId());
            goodsSpecService.add(specification);
            specMap.put(specification.getName() + "=" + specification.getValue(), specification);
        }

        // 商品参数表_goods_attribute
        for (GoodsAttrValue attribute : attributes) {
            attribute.setGoodsId(goods.getId());
            goodsAttrValueService.add(attribute);
        }
        // 商品货品表_product
        for (GoodsProductDTO productDTO : products) {
            GoodsProduct product = productDTO.getGoodsProduct();
            product.setGoodsId(goods.getId());
            goodsProductService.add(product);

            List<GoodsProductSpecRelation> goodsSpecRelations = productDTO.getGoodsSpecRelations();
            for (int i = 0; i < goodsSpecRelations.size(); i++) {
                GoodsProductSpecRelation relation = goodsSpecRelations.get(i);
                GoodsSpec goodsSpec = specMap.get(relation.getName() + "=" + relation.getValue());
                relation.setItemSort(i);
                relation.setSpecId(goodsSpec.getId());
            }
            goodsProductSpecRelationService.saveBatch(goodsSpecRelations);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, GoodsAllinoneDTO goodsAllinone) {
        validate(goodsAllinone);
        Goods goods = goodsAllinone.getGoods();
        GoodsAttrValue[] attributes = goodsAllinone.getAttributes();
        GoodsSpec[] specifications = goodsAllinone.getSpecifications();
        GoodsProductDTO[] products = goodsAllinone.getGoodsProducts();

        // 商品表里面有一个字段retailPrice记录当前商品的最低价
        BigDecimal retailPrice = new BigDecimal(Integer.MAX_VALUE);
        for (GoodsProductDTO productDTO : products) {
            BigDecimal productPrice = productDTO.getGoodsProduct().getPrice();
            if (retailPrice.compareTo(productPrice) > 0) {
                retailPrice = productPrice;
            }

        }
        goods.setRetailPrice(retailPrice);

        // 商品基本信息表_goods
        if (baseMapper.updateByPrimaryKeySelective(goods) == 0) {
            throw new RuntimeException("更新数据失败");
        }

        // 商品规格表_goods_specification
        for (GoodsSpec specification : specifications) {
            // 目前只支持更新规格表的图片字段
            if (specification.getUpdateTime() == null) {
                specification.setValue(null);
                goodsSpecService.updateById(specification);
            }
        }
        List<Long> productIds = goodsProductService.listGoodsProducts(goods.getId()).stream().map(GoodsProduct::getId).toList();
        // 商品货品表_product
        for (GoodsProductDTO productDTO : products) {
            GoodsProduct product = productDTO.getGoodsProduct();

            if (product.getUpdateTime() == null) {
                goodsProductService.updateById(product);
            }
        }

        // 商品参数表_goods_attribute
        for (GoodsAttrValue attribute : attributes) {
            if (attribute.getId() == null || attribute.getId().equals(0L)) {
                attribute.setGoodsId(goods.getId());
                goodsAttrValueService.add(attribute);
            } else if (attribute.getDeleted().equals(BasicEnum.YES.getKey())) {
                goodsAttrValueService.delete(attribute.getId());
            } else if (attribute.getUpdateTime() == null) {
                goodsAttrValueService.updateById(attribute);
            }
        }

        // 这里需要注意的是购物车_cart有些字段是拷贝商品的一些字段，因此需要及时更新
        // 目前这些字段是goods_sn, goods_name, price, pic_url
//        for (GoodsProduct product : products) {
//            cartService.updateProduct(product.getId(), goods.getGoodsSn(), goods.getName(), product.getPrice(), product.getUrl());
//        }
        baseMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public void delete(Long id) {
        Goods goods = new Goods();
        goods.setId(id);
        goods.setDeleted(1);
        AssertUtil.isTrue(baseMapper.updateByPrimaryKeySelective(goods) > 0, "删除失败");
    }

    @Override
    public List<Goods> listByGoodIds(Long[] goodsIds) {
        return baseMapper.listByGoodsIdsAndOnSale(goodsIds, BasicEnum.YES.getKey());
    }

    @Override
    public Long count() {
        return baseMapper.count();
    }

    @Override
    public GoodsDetailVO getGoodsDetail(Long id) {
        // 商品信息
        Goods info = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(info, "商品不存在");
        // 商品属性
        List<GoodsAttrValue> goodsAttributes = goodsAttrValueService.listGoodsAttributes(id);
        // 商品规格 返回的是定制的GoodsSpeVo
        List<GoodsSpecificationVO> GoodsSpes = goodsSpecService.getSpecificationVoList(id);
        // 商品规格对应的数量和价格
        List<GoodsProduct> goodsProducts = goodsProductService.listGoodsProducts(id);
        // 商品问题，这里是一些通用问题
        List<Issue> issues = issueService.listIssuePage(null, PageEntity.page(1, 4)).getList();
        // 商品品牌商
        Brand brand = brandService.getById(info.getBrandId());
        // 评论
        List<CommentVO> comments = commentService.query(0, id, 0, PageEntity.page(1, 2)).getList();
        //团购信息
        List<GrouponRules> grouponRules = grouponRulesService.listGrouponRules(id, PageEntity.page(1, 10)).getList();

        //自动分享
        boolean share = configHelper.isAutoCreateShareImage();
        //商品分享图片地址
        String shareImage = info.getShareUrl();

        GoodsDetailVO vo = new GoodsDetailVO();
        vo.setInfo(info);
        vo.setAttribute(goodsAttributes);
        vo.setSpecificationList(GoodsSpes);
        vo.setProductList(goodsProducts);
        vo.setIssue(issues);
        vo.setBrand(brand);
        vo.setComment(comments);
        vo.setGroupon(grouponRules);
        vo.setShare(share);
        vo.setShareImage(shareImage);
        return vo;
    }

    @Override
    public PageInfo<Goods> listSelective(Integer categoryId, Integer brandId, String keyword, Integer isHot, Integer isNew, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listSelective(categoryId, brandId, keyword, isHot, isNew));
    }

    @Override
    public List<Goods> listRelated(Long id) {
        Goods goods = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(goods, "商品不存在");

        // 目前的商品推荐算法仅仅是推荐同类目的其他商品
        Long cid = goods.getCategoryId();

        // 查找六个相关商品
        int related = 6;
        PageEntity pageEntity = PageEntity.page(1, related);
        pageEntity.setOrder("create_time");
        pageEntity.setDesc(true);
        return PageWrapper.list(pageEntity, () -> baseMapper.listByCategory(cid));
    }

    @Override
    public List<Goods> listByHot(Integer hotLimit) {
        return PageWrapper.list(1, hotLimit, () -> baseMapper.listByHot());
    }

    @Override
    public List<Goods> listByNew(Integer newLimit) {
        return PageWrapper.list(1, newLimit, () -> baseMapper.listByNew());
    }

    @Override
    public List<Goods> listByCategory(List<Long> categoryIds, Integer catlogMoreLimit) {
        return PageWrapper.list(1, catlogMoreLimit, () -> baseMapper.listByCategorys(categoryIds));
    }

    @Override
    public Goods getOnSaleGoods(Long id) {
        return baseMapper.getOnSaleGoods(id);
    }

    @Override
    public Boolean checkCollect(Long userId, Long id) {
        int count = collectService.count(userId, CollectTypeEnum.GOODS.getKey(), id);
        return count > 0;
    }

    /**
     * 校验
     *
     * @param goodsAllinone 商品
     */
    private void validate(GoodsAllinoneDTO goodsAllinone) {
        Goods goods = goodsAllinone.getGoods();
        String name = goods.getName();
        AssertUtil.isTrue(StringUtils.hasText(name), "商品名称不能为空");
        String goodsSn = goods.getGoodsSn();
        AssertUtil.isTrue(StringUtils.hasText(goodsSn), "商品编号不能为空");
        // 品牌商可以不设置，如果设置则需要验证品牌商存在
        Long brandId = goods.getBrandId();
        if (brandId != null && brandId != 0) {
            AssertUtil.notNull(brandService.getById(brandId), "品牌不存在");
        }
        // 分类可以不设置，如果设置则需要验证分类存在
        Long categoryId = goods.getCategoryId();
        if (categoryId != null && categoryId != 0) {
            AssertUtil.notNull(categoryService.getById(categoryId), "分类不存在");
        }

        GoodsAttrValue[] attributes = goodsAllinone.getAttributes();
        for (GoodsAttrValue attribute : attributes) {
            String attr = attribute.getName();
            AssertUtil.isTrue(StringUtils.hasText(attr), "属性异常");
            String value = attribute.getValue();
            AssertUtil.isTrue(StringUtils.hasText(value), "属性值异常");
        }

        GoodsSpec[] specifications = goodsAllinone.getSpecifications();
        for (GoodsSpec specification : specifications) {
//            String spec = specification.getSpecification();
//            AssertUtil.isTrue(StringUtils.hasText(spec), "规格异常");
//            String value = specification.getValue();
//            AssertUtil.isTrue(StringUtils.hasText(value), "规格值异常");
        }

        GoodsProduct[] products = goodsAllinone.getProducts();
        for (GoodsProduct product : products) {
            Integer number = product.getNumber();
            AssertUtil.isNotTrue(number == null || number < 0, "商品数量异常");

            BigDecimal price = product.getPrice();
            AssertUtil.notNull(price, "商品价格异常");

            AssertUtil.isTrue(StringUtils.hasText(product.getSpecifications()), "商品规格值未添加");
        }
    }
}