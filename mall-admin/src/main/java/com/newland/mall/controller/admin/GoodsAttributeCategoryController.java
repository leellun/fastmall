package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.GoodsAttributeCategory;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.vo.GoodsAttributeCategoryItemVo;
import com.newland.mall.service.GoodsAttributeCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品属性分类表 控制器
 * @author leellun
 * @since 2023-06-04 21:50:54
 */
@RestController
@RequestMapping("/admin/goodsAttribute/category")
@Tag(name = "产品属性分类表", description = "产品属性分类表")
public class GoodsAttributeCategoryController {
    @Autowired
    private GoodsAttributeCategoryService goodsAttributeCategoryService;

    @Operation(summary ="添加商品属性分类")
    @PostMapping(value = "/create")
    @ResponseBody
    public RestResponse<String> create(@RequestParam String name) {
        goodsAttributeCategoryService.create(name);
        return RestResponse.success("添加成功");
    }

    @Operation(summary ="修改商品属性分类")
    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public RestResponse<String> update(@PathVariable Long id, @RequestParam String name) {
        goodsAttributeCategoryService.update(id, name);
        return RestResponse.success("修改成功");
    }

    @Operation(summary ="删除单个商品属性分类")
    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public RestResponse<String> delete(@PathVariable Long id) {
        goodsAttributeCategoryService.delete(id);
        return RestResponse.success("修改成功");
    }

    @Operation(summary ="获取单个商品属性分类信息")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public RestResponse<GoodsAttributeCategory> getItem(@PathVariable Long id) {
        return RestResponse.ok(goodsAttributeCategoryService.getItem(id));
    }

    @Operation(summary ="分页获取所有商品属性分类")
    @GetMapping(value = "/list")
    @ResponseBody
    public RestResponse<PageInfo<GoodsAttributeCategory>> getList(@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "1") Integer pageNo) {
        return RestResponse.ok(goodsAttributeCategoryService.getList(pageSize, pageNo));
    }
    @Operation(summary ="获取所有商品属性分类")
    @GetMapping(value = "/all")
    @ResponseBody
    public RestResponse<List<GoodsAttributeCategory>> getTotalList() {
        return RestResponse.ok(goodsAttributeCategoryService.list());
    }

    @Operation(summary ="获取所有商品属性分类及其下属性")
    @GetMapping(value = "/list/withAttr")
    @ResponseBody
    public RestResponse<List<GoodsAttributeCategoryItemVo>> getListWithAttr() {
        List<GoodsAttributeCategoryItemVo> goodsAttributeCategoryResultList = goodsAttributeCategoryService.getListWithAttr();
        return RestResponse.ok(goodsAttributeCategoryResultList);
    }
}