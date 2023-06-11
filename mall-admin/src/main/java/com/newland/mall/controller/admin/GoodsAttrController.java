package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.GoodsAttr;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.dto.GoodsAttributeDto;
import com.newland.mall.model.vo.GoodsAttrInfoVo;
import com.newland.mall.model.vo.GoodsAttrWithValueVo;
import com.newland.mall.service.GoodsAttrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性参数表 控制器
 *
 * @author leellun
 * @since 2023-06-04 21:50:54
 */
@RestController
@RequestMapping("/admin/goodsAttr")
@Tag(name = "商品属性参数表", description = "商品属性参数表")
public class GoodsAttrController {
    @Autowired
    private GoodsAttrService goodsAttrService;

    @Operation(description = "查询属性列表或参数列表")
    @GetMapping(value = "/list")
    @ResponseBody
    public RestResponse<PageInfo<GoodsAttr>> getList(@Parameter(description = "0表示属性，1表示参数") @RequestParam(value = "type", required = false) Integer type,
                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {

        PageInfo<GoodsAttr> productAttributeList = goodsAttrService.getList(type, pageSize, pageNo);
        return RestResponse.ok(productAttributeList);
    }

    @Operation(description = "添加商品属性信息")
    @PostMapping(value = "/create")
    @ResponseBody
    public RestResponse create(@RequestBody GoodsAttributeDto productAttributeParam) {
        goodsAttrService.create(productAttributeParam);
        return RestResponse.success("添加成功");
    }

    @Operation(description = "修改商品属性信息")
    @PutMapping(value = "/update/{id}")
    @ResponseBody
    public RestResponse update(@PathVariable Long id, @RequestBody GoodsAttributeDto productAttributeParam) {
        goodsAttrService.update(id, productAttributeParam);
        return RestResponse.success("更新成功");
    }

    @Operation(description = "查询单个属性")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public RestResponse<GoodsAttr> getItem(@PathVariable Long id) {
        GoodsAttr productAttribute = goodsAttrService.getItem(id);
        return RestResponse.ok(productAttribute);
    }

    @Operation(description = "批量删除商品属性")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public RestResponse delete(@RequestBody List<Long> ids) {
        goodsAttrService.delete(ids);
        return RestResponse.success("删除成功");
    }

    @Operation(description = "根据商品分类的id获取商品属性及属性分类")
    @GetMapping(value = "/attrInfo/{goodsCategoryId}")
    @ResponseBody
    public RestResponse<List<GoodsAttrInfoVo>> getAttrInfo(@PathVariable Long goodsCategoryId) {
        List<GoodsAttrInfoVo> productAttrInfoList = goodsAttrService.getGoodsAttrInfo(goodsCategoryId);
        return RestResponse.ok(productAttrInfoList);
    }

    @Operation(description = "根据分组获取所有规格属性和销售属性")
    @Parameter(name = "type",description = "0表示属性，1表示参数")
    @GetMapping(value = "/all/{gid}")
    @ResponseBody
    public RestResponse<List<GoodsAttr>> getAllList(@PathVariable Long gid, @RequestParam(value = "type") Integer type) {

        List<GoodsAttr> productAttributeList = goodsAttrService.getListAttr(gid, type);
        return RestResponse.ok(productAttributeList);
    }
    @Operation(description = "根据分组获取所有非绑定规格属性和销售属性")
    @Parameter(name = "type",description = "0表示属性，1表示参数")
    @GetMapping(value = "/unbind/{gid}")
    @ResponseBody
    public RestResponse<PageInfo<GoodsAttr>> getUnBindList(@PathVariable Long gid, @RequestParam(value = "type") Integer type,
                                                           @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize                                                           ) {

        PageInfo<GoodsAttr> productAttributeList = goodsAttrService.getUnBindListAttr(gid, type,pageNo,pageSize);
        return RestResponse.ok(productAttributeList);
    }
    @Operation(description = "根据分组获取所有规格属性和销售属性")
    @Parameter(name = "type",description = "0表示属性，1表示参数")
    @GetMapping(value = "/bindWithValue/{gid}")
    @ResponseBody
    public RestResponse<List<GoodsAttrWithValueVo>> getBindsWithValue(@PathVariable Long gid, @RequestParam(value = "type") Integer type) {

        List<GoodsAttrWithValueVo> productAttributeList = goodsAttrService.getBindsWithValue(gid, type);
        return RestResponse.ok(productAttributeList);
    }
}