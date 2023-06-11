package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.GoodsValue;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.GoodsValueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品参数表 控制器
 *
 * @author leellun
 * @since 2023-06-10 21:59:45
 */
@RestController
@RequestMapping("/admin/goodsValue")
@Tag(name = "商品参数表", description = "商品参数表")
public class GoodsValueController {
    @Autowired
    private GoodsValueService goodsValueService;

    @Operation(description = "查询属性列表或参数列表")
    @GetMapping(value = "/list")
    @ResponseBody
    public RestResponse<PageInfo<GoodsValue>> getList(@RequestParam(value = "goodsAttrId") Long goodsAttrId,
                                                      @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        PageInfo<GoodsValue> productAttributeList = goodsValueService.getList(goodsAttrId, pageNo, pageSize);
        return RestResponse.ok(productAttributeList);
    }

    @Operation(description = "添加商品属性信息")
    @PostMapping(value = "/create")
    @ResponseBody
    public RestResponse create(@RequestBody GoodsValue goodsValue) {
        goodsValueService.create(goodsValue);
        return RestResponse.success("添加成功");
    }

    @Operation(description = "修改商品属性信息")
    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public RestResponse update(@PathVariable Long id, @RequestBody GoodsValue goodsValue) {
        goodsValue.setId(id);
        goodsValueService.update(goodsValue);
        return RestResponse.success("更新成功");
    }

    @Operation(description = "批量删除商品属性")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public RestResponse delete(@RequestBody List<Long> ids) {
        goodsValueService.delete(ids);
        return RestResponse.success("删除成功");
    }
}