package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Goods;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.dto.GoodsAllinoneDTO;
import com.newland.mall.model.vo.BrandAndCategoryVO;
import com.newland.mall.model.vo.GoodsAllinoneVO;
import com.newland.mall.service.GoodsService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author leell
 */
@Tag(name = "商品管理")
@RestController
@RequestMapping("/admin/goods")
@Validated
public class AdminGoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 查询商品
     *
     * @return
     */
    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<Goods>> list(Integer goodsId, String goodsSn, String name,
                                              @RequestParam(defaultValue = "1") Integer pageNo,
                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                              @Sort @RequestParam(defaultValue = "create_time") String order,
                                              @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<Goods> pageInfo = goodsService.list(goodsId, goodsSn, name, pageEntity);
        return RestResponse.ok(pageInfo);
    }

    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public RestResponse<GoodsAllinoneVO> detail(@PathVariable Long id) {
        return RestResponse.ok(goodsService.getDetail(id));
    }

    @GetMapping("/catAndBrand")
    @Operation(summary = "购物车")
    public RestResponse<BrandAndCategoryVO> catAndBrands() {
        return RestResponse.ok(goodsService.listCatAndBrands());
    }
    /**
     * 添加商品
     *
     * @param goodsAllinone
     * @return
     */
    @Operation(summary = "添加商品")
    @PostMapping
    public RestResponse create(@RequestBody GoodsAllinoneDTO goodsAllinone) {
        goodsService.create(goodsAllinone);
        return RestResponse.success("操作成功");
    }
    /**
     * 编辑商品
     *
     * @param goodsAllinone
     * @return
     */
    @Operation(summary = "编辑商品")
    @PutMapping("/{id}")
    public RestResponse update(@PathVariable Long id, @RequestBody GoodsAllinoneDTO goodsAllinone) {
        goodsService.update(id, goodsAllinone);
        return RestResponse.success("操作成功");
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public RestResponse delete(@RequestBody Long id) {
        goodsService.delete(id);
        return RestResponse.success("操作成功");
    }


}
