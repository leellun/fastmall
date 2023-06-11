package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.GoodsAttr;
import com.newland.mall.entity.GoodsGroup;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.vo.GoodsAttributeCategoryItemVo;
import com.newland.mall.model.vo.GoodsGroupVo;
import com.newland.mall.service.GoodsGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品属性分组表 控制器
 *
 * @author leellun
 * @since 2023-06-04 21:50:54
 */
@RestController
@RequestMapping("/admin/groupGroup")
@Tag(name = "产品属性分组表", description = "产品属性分组表")
public class GoodsGroupController {
    @Autowired
    private GoodsGroupService goodsGroupService;

    @Operation(summary = "分页获取所有商品属性分组")
    @GetMapping(value = "/list")
    @ResponseBody
    public RestResponse<PageInfo<GoodsGroup>> getList(@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "1") Integer pageNo) {
        return RestResponse.ok(goodsGroupService.getList(pageSize, pageNo));
    }

    @Operation(summary = "添加商品属性分组")
    @PostMapping(value = "/create")
    @ResponseBody
    public RestResponse<String> create(@RequestBody GoodsGroup goodsGroup) {
        goodsGroupService.create(goodsGroup);
        return RestResponse.success("添加成功");
    }

    @Operation(summary = "修改商品属性分组")
    @PutMapping(value = "/update")
    @ResponseBody
    public RestResponse<String> update(@RequestBody GoodsGroup goodsGroup) {
        goodsGroupService.update(goodsGroup);
        return RestResponse.success("修改成功");
    }

    @Operation(summary = "删除单个商品属性分组")
    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public RestResponse<String> delete(@PathVariable Long id) {
        goodsGroupService.delete(id);
        return RestResponse.success("修改成功");
    }

    @Operation(summary = "获取单个商品属性分组信息")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public RestResponse<GoodsGroupVo> getItem(@PathVariable Long id) {
        return RestResponse.ok(goodsGroupService.getItem(id));
    }

    @Operation(summary = "获取商品属性分组")
    @GetMapping(value = "/category/{categoryId}")
    @ResponseBody
    public RestResponse<List<GoodsGroup>> getTotalList(@PathVariable Long categoryId) {
        return RestResponse.ok(goodsGroupService.list(categoryId));
    }

    @Operation(summary = "获取所有商品属性分组及其下属性")
    @GetMapping(value = "/list/withAttr")
    @ResponseBody
    public RestResponse<List<GoodsAttributeCategoryItemVo>> getListWithAttr() {
        List<GoodsAttributeCategoryItemVo> goodsAttributeCategoryResultList = goodsGroupService.getListWithAttr();
        return RestResponse.ok(goodsAttributeCategoryResultList);
    }


    @Operation(summary = "绑定属性")
    @PostMapping(value = "/bind/{gid}")
    @ResponseBody
    public RestResponse<Object> bindAttrs(@PathVariable("gid") Long gid, @RequestBody List<Long> attrIds) {
        goodsGroupService.bindAttrs(gid, attrIds);
        return RestResponse.success("绑定成功");
    }
    @Operation(summary = "绑定属性")
    @PostMapping(value = "/unbind/{gid}")
    @ResponseBody
    public RestResponse<Object> unBindAttrs(@PathVariable("gid") Long gid, @RequestBody List<Long> attrIds) {
        goodsGroupService.unBindAttrs(gid, attrIds);
        return RestResponse.success("解绑成功");
    }
}