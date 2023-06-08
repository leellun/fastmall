package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Coupon;
import com.newland.mall.entity.CouponUser;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.CouponService;
import com.newland.mall.service.CouponUserService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 优惠券管理
 * @author leell
 */
@Tag(name = "优惠券管理")
@RestController
@RequestMapping("/admin/coupon")
@Validated
public class AdminCouponController {
    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponUserService couponUserService;

    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<Coupon>> list(String name, Integer type, Integer status,
                       @RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @Sort @RequestParam(defaultValue = "create_time") String order,
                       @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity=PageEntity.page(pageNo,pageSize);
        pageEntity.setDesc(desc);
        pageEntity.setOrder(order);
        PageInfo<Coupon> couponList = couponService.listCoupon(name, type, status,pageEntity);
        return RestResponse.ok(couponList);
    }

    @Operation(summary = "查询用户")
    @GetMapping("/listuser")
    public RestResponse<PageInfo<CouponUser>> listuser(Long userId, Long couponId, Integer status,
                           @RequestParam(defaultValue = "1") Integer pageNo,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @Sort @RequestParam(defaultValue = "create_time") String order,
                           @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity=PageEntity.page(pageNo,pageSize);
        pageEntity.setDesc(desc);
        pageEntity.setOrder(order);
        PageInfo<CouponUser> couponList = couponUserService.listCouponUser(userId, couponId, status, pageEntity);
        return RestResponse.ok(couponList);
    }


    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public RestResponse<Coupon> get(@PathVariable Long id) {
        Coupon coupon = couponService.get(id);
        return RestResponse.ok(coupon);
    }
    @Operation(summary = "添加")
    @PostMapping
    public RestResponse create(@RequestBody Coupon coupon) {
        couponService.add(coupon);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "编辑")
    @PutMapping
    public RestResponse update(@RequestBody Coupon coupon) {
        couponService.update(coupon);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public RestResponse<String> delete(@PathVariable Long id) {
        couponService.delete(id);
        return RestResponse.success("操作成功");
    }

}
