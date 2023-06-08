package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.express.ExpressService;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.dto.OrderPayDTO;
import com.newland.mall.model.dto.OrderRefundDTO;
import com.newland.mall.model.dto.OrderReplyDTO;
import com.newland.mall.model.dto.OrderShipDTO;
import com.newland.mall.model.vo.OrderDetailVO;
import com.newland.mall.model.vo.OrderVO;
import com.newland.mall.service.OrderService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author leell
 */
@Tag(name = "订单管理")
@RestController
@RequestMapping("/admin/order")
@Validated
public class AdminOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ExpressService expressService;

    /**
     * 查询订单
     *
     * @param orderSn
     * @param orderStatusArray
     * @param order
     * @return
     */
    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<OrderVO>> list(String nickname, String consignee, String orderSn,
                                                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                                @RequestParam(required = false) List<Integer> orderStatusArray,
                                                @RequestParam(defaultValue = "1") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @Sort @RequestParam(defaultValue = "create_time") String order,
                                                @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<OrderVO> pageInfo = orderService.list(nickname, consignee, orderSn, start, end, orderStatusArray, pageEntity);
        return RestResponse.ok(pageInfo);
    }

    /**
     * 查询物流公司
     *
     * @return
     */
    @Operation(summary = "查询")
    @GetMapping("/channel")
    public RestResponse<List<Map<String, String>>> channel() {
        return RestResponse.ok(expressService.getVendors());
    }

    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public RestResponse<OrderDetailVO> detail(@PathVariable Long id) {
        return RestResponse.ok(orderService.getOrderDetail(id));
    }

    /**
     * 订单退款
     *
     * @param orderRefundDTO 订单信息，{ orderId：xxx }
     * @return 订单退款操作结果
     */
    @Operation(summary = "订单退款")
    @PostMapping("/refund")
    public RestResponse refund(@RequestBody OrderRefundDTO orderRefundDTO) {
        orderService.refund(orderRefundDTO);
        return RestResponse.success("操作成功");
    }

    /**
     * 发货
     *
     * @param orderShipDTO 订单信息，{ orderId：xxx, shipSn: xxx, shipChannel: xxx }
     * @return 订单操作结果
     */
    @Operation(summary = "订单发货")
    @PostMapping("/ship")
    public RestResponse ship(@RequestBody OrderShipDTO orderShipDTO) {
        orderService.ship(orderShipDTO);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "订单收款")
    @PostMapping("/pay")
    public RestResponse pay(@RequestBody OrderPayDTO orderPayDTO) {
        orderService.pay(orderPayDTO);
        return RestResponse.success("操作成功");
    }

    /**
     * 删除订单
     *
     * @return 订单操作结果
     */
    @Operation(summary = "订单删除")
    @DeleteMapping("/{id}")
    public RestResponse delete(@PathVariable Long id) {
        orderService.delete(id);
        return RestResponse.success("删除成功");
    }

    /**
     * 回复订单商品
     *
     * @param orderReplyDTO 订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @Operation(summary = "订单商品回复")
    @PostMapping("/reply")
    public RestResponse reply(@RequestBody OrderReplyDTO orderReplyDTO) {
        orderService.reply(orderReplyDTO);
        return RestResponse.success("操作成功");
    }
}
