package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Ad;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.dto.AdDTO;
import com.newland.mall.service.AdService;
import com.newland.mall.validator.Insert;
import com.newland.mall.validator.Order;
import com.newland.mall.validator.Sort;
import com.newland.mall.validator.Update;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/ad")
@Validated
public class AdminAdController {
    @Autowired
    private AdService adService;

    @Operation(summary = "广告列表")
    @GetMapping
    public RestResponse<PageInfo<Ad>> list(String name, String content,
                                           @RequestParam(defaultValue = "1") Integer pageNo,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @Sort @RequestParam(defaultValue = "create_time") String order,
                                           @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        pageEntity.setDesc(desc);
        pageEntity.setOrder(order);
        PageInfo<Ad> pageInfo = adService.getPageAd(name, content,pageEntity);
        return RestResponse.success(pageInfo);
    }

    @Operation(summary = "添加广告")
    @PostMapping
    public RestResponse create(@RequestBody @Validated(Insert.class) AdDTO adDTO) {
        adService.add(adDTO);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public RestResponse<Ad> get(@PathVariable Long id) {
        Ad ad = adService.getAd(id);
        return RestResponse.ok(ad);
    }

    @Operation(summary = "编辑")
    @PutMapping
    public RestResponse update(@RequestBody @Validated(Update.class) AdDTO adDTO) {
        adService.updateAd(adDTO);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public RestResponse delete(@PathVariable Long id) {
        adService.delete(id);
        return RestResponse.success("操作成功");
    }

}
