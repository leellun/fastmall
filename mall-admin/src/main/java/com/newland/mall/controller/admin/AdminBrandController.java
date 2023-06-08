package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Brand;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.BrandService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "品牌管理")
@RestController
@RequestMapping("/admin/brand")
@Validated
public class AdminBrandController {
    @Autowired
    private BrandService brandService;

    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<Brand>> list(String id, String name,
                       @RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @Sort @RequestParam(defaultValue = "create_time") String order,
                       @RequestParam(defaultValue = "false") Boolean desc) {
        PageEntity pageEntity=PageEntity.page(pageNo,pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<Brand> pageInfo = brandService.listPage(id, name,pageEntity);
        return RestResponse.ok(pageInfo);
    }

    @Operation(summary = "添加")
    @PostMapping
    public RestResponse create(@RequestBody Brand brand) {
        brandService.add(brand);
        return RestResponse.success("操作成功");
    }
    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public RestResponse<Brand> get(@PathVariable Long id) {
        Brand brand = brandService.get(id);
        return RestResponse.ok(brand);
    }
    @Operation(summary = "编辑")
    @PutMapping
    public Object update(@RequestBody Brand brand) {
        brandService.update(brand);
        return RestResponse.success("操作成功");
    }
    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Long id) {
        brandService.delete(id);
        return RestResponse.success("操作成功");
    }

}
