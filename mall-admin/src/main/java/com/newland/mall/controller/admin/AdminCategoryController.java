package com.newland.mall.controller.admin;

import com.newland.mall.entity.Category;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.vo.BrandAndCategoryVO;
import com.newland.mall.model.vo.CateVO;
import com.newland.mall.model.vo.CategoryVO;
import com.newland.mall.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name="类目管理")
@RestController
@RequestMapping("/admin/category")
@Validated
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<List<CategoryVO>> list() {
        return RestResponse.ok(categoryService.list());
    }
    @GetMapping("/cat")
    @Operation(summary = "分类选择")
    public RestResponse<List<CateVO>> cats() {
        return RestResponse.ok(categoryService.listCats());
    }
    @Operation(summary = "添加")
    @PostMapping
    public RestResponse create(@RequestBody Category category) {
        categoryService.add(category);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public RestResponse<Category> get(@PathVariable Long id) {
        Category category = categoryService.get(id);
        return RestResponse.ok(category);
    }

    @Operation(summary = "更新")
    @PutMapping
    public RestResponse update(@RequestBody Category category) {
        categoryService.update(category);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "删除")
    @PostMapping("/{id}")
    public RestResponse delete(@PathVariable Long id) {
        categoryService.delete(id);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "一级分类")
    @GetMapping("/l1")
    public RestResponse<List<Category>> catL1() {
        // 所有一级分类目录
        List<Category> l1CatList = categoryService.listL1();
        return RestResponse.ok(l1CatList);
    }
}
