package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.config.ConfigHelper;
import com.newland.mall.entity.*;
import com.newland.mall.enumeration.BasicEnum;
import com.newland.mall.enums.CollectTypeEnum;
import com.newland.mall.mapper.GoodsMapper;
import com.newland.mall.mapper.GoodsSaleAttrMapper;
import com.newland.mall.model.dto.GoodsAllinoneDTO;
import com.newland.mall.model.vo.*;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private GoodsSaleAttrMapper goodsSaleAttrMapper;
    @Autowired
    private GoodsAttrValueService goodsAttrValueService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;
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

    @Autowired
    private GoodsSaleAttrService goodsSaleAttrService;

    @Override
    public PageInfo<Goods> list(Integer goodsId, String name, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listGoods(goodsId, name));
    }

    @Override
    public GoodsAllinoneVO getDetail(Long id) {
        Goods goods = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(goods, "商品不存在");
        List<GoodsProductVo> products = goodsProductService.listWithSpecGoodsProducts(id);
        List<GoodsAttrValue> attributes = goodsAttrValueService.listGoodsAttributes(id);
        List<GoodsSaleAttrVo> goodsSaleAttrVos = goodsSaleAttrMapper.listWithSpecByGoodsId(id);

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
        vo.setGoodsSaleAttrVos(goodsSaleAttrVos);
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
        List<GoodsAttrValue> attributes = goodsAllinone.getAttributes();
        List<GoodsSaleAttrVo> specifications = goodsAllinone.getGoodsSaleAttrVos();
        List<GoodsProductVo> products = goodsAllinone.getProducts();

        String name = goods.getName();
        AssertUtil.notNull(baseMapper.getByNameAndSale(name, BasicEnum.YES.getKey()), "商品名已经存在");

        // 商品表里面有一个字段retailPrice记录当前商品的最低价
        BigDecimal retailPrice = new BigDecimal(Integer.MAX_VALUE);
        for (GoodsProductVo productDTO : products) {
            BigDecimal productPrice = productDTO.getPrice();
            if (retailPrice.compareTo(productPrice) > 0) {
                retailPrice = productPrice;
            }
        }
        goods.setRetailPrice(retailPrice);

        // 商品基本信息表_goods
        baseMapper.insert(goods);

        //更新商品参数
        goodsAttrValueService.saveAttributes(goods.getId(), attributes);
        //保存商品规格
        List<GoodsSaleAttrVo> specMap = goodsSaleAttrService.saveSaleAttr(goods.getId(), specifications);
        //更新商品sku信息
        goodsProductService.saveProducts(goods, products, specMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, GoodsAllinoneDTO goodsAllinone) {
        validate(goodsAllinone);
        Goods goods = goodsAllinone.getGoods();
        List<GoodsAttrValue> attributes = goodsAllinone.getAttributes();
        List<GoodsSaleAttrVo> specifications = goodsAllinone.getGoodsSaleAttrVos();
        List<GoodsProductVo> products = goodsAllinone.getProducts();

        // 商品表里面有一个字段retailPrice记录当前商品的最低价
        BigDecimal retailPrice = new BigDecimal(Integer.MAX_VALUE);
        for (GoodsProductVo productDTO : products) {
            BigDecimal productPrice = productDTO.getPrice();
            if (retailPrice.compareTo(productPrice) > 0) {
                retailPrice = productPrice;
            }
        }
        goods.setRetailPrice(retailPrice);

        // 商品基本信息表_goods
        if (baseMapper.updateByPrimaryKeySelective(goods) == 0) {
            throw new RuntimeException("更新数据失败");
        }
        //更新商品参数
        goodsAttrValueService.updateAttributes(goods.getId(), attributes);
        //保存商品规格
        List<GoodsSaleAttrVo> specMap = goodsSaleAttrService.updateSaleAttr(goods.getId(), specifications);
        //更新商品sku信息
        goodsProductService.updateProducts(goods, products, specMap);
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
        List<GoodsSpecificationVO> goodsSpes = goodsSpecService.getSpecificationVoList(id);
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
        vo.setSpecificationList(goodsSpes);
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

        List<GoodsAttrValue> attributes = goodsAllinone.getAttributes();
        for (GoodsAttrValue attribute : attributes) {
            String attr = attribute.getName();
            AssertUtil.isTrue(StringUtils.hasText(attr), "属性异常");
            String value = attribute.getValue();
            AssertUtil.isTrue(StringUtils.hasText(value), "属性值异常");
        }

        List<GoodsSaleAttrVo> specifications = goodsAllinone.getGoodsSaleAttrVos();
        for (GoodsSaleAttrVo specification : specifications) {
            String spec = specification.getName();
            AssertUtil.isTrue(StringUtils.hasText(spec), "规格异常");
            AssertUtil.isTrue(specification.getGoodsSpecs().size() > 0, "规格值异常");
        }

        List<GoodsProductVo> productDtos = goodsAllinone.getProducts();
        for (GoodsProductVo productDto : productDtos) {
            Integer number = productDto.getNumber();
            AssertUtil.isNotTrue(number == null || number < 0, "商品数量异常");

            BigDecimal price = productDto.getPrice();
            AssertUtil.notNull(price, "商品价格异常");
        }
    }

}