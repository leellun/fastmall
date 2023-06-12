package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Storage;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.StorageService;
import com.newland.mall.storage.MallStorageService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author leell
 */
@Tag(name = "对象存储")
@RestController
@RequestMapping("/admin/storage")
@Validated
public class AdminStorageController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private MallStorageService mallStorageService;

    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<Storage>> list(String key, String name,
                       @RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @Sort @RequestParam(defaultValue = "create_time") String order,
                       @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity=PageEntity.page(pageNo,pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<Storage> storageList = storageService.listStoragePage(key, name, pageEntity);
        return RestResponse.ok(storageList);
    }

    @Operation(summary = "上传")
    @PostMapping
    public RestResponse create(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        Storage storage = mallStorageService.store(file.getInputStream(), file.getSize(),
                file.getContentType(), originalFilename);
        return RestResponse.ok(storage);
    }

    @Operation(summary = "详情")
    @PostMapping("/{id}")
    public RestResponse<Storage> get(@PathVariable Long id) {
        Storage storageInfo = storageService.get(id);
        return RestResponse.ok(storageInfo);
    }

    @Operation(summary = "编辑")
    @PutMapping
    public RestResponse update(@RequestBody Storage storage) {
        storageService.update(storage);
        return RestResponse.success("更新成功");
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{key}")
    public RestResponse delete(@PathVariable String key) {
        storageService.delete(key);
        return RestResponse.success("操作成功");
    }
}
