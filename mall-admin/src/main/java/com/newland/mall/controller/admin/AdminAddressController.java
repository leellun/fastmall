package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Address;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.AddressService;
import com.newland.mall.validator.Order;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "收货地址")
@RestController
@RequestMapping("/admin/address")
@Validated
public class AdminAddressController {
    @Autowired
    private AddressService addressService;

    @Operation(summary = "地址查询")
    @GetMapping
    public RestResponse<PageInfo<Address>> list(Long userId, String name,
                                                @RequestParam(defaultValue = "1") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @Sort @RequestParam(defaultValue = "create_time") String order,
                                                @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        pageEntity.setDesc(desc);
        pageEntity.setOrder(order);
        PageInfo<Address> pageInfo = addressService.getAddressPage(userId, name, pageEntity);
        return RestResponse.ok(pageInfo);
    }
}
