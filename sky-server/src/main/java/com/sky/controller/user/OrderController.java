package com.sky.controller.user;

import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController("userOrderController")
@RequestMapping("user/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 订单提交
     *
     * @param ordersSubmitDTO 订单提交dto
     * @return {@link Result}<{@link OrderSubmitVO}>
     */
    @PostMapping("/submit")
    public Result<OrderSubmitVO> orderSubmit(@RequestBody OrdersSubmitDTO ordersSubmitDTO){
        OrderSubmitVO orderSubmitVO=orderService.orderSubmit(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }

    /**
     * 付款
     *
     * @param ordersPaymentDTO 订单付款dto
     * @return {@link Result}<{@link OrderPaymentVO}>
     * @throws Exception 异常
     */
    @PutMapping("/payment")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("订单支付：{}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
        log.info("生成预支付交易单：{}", orderPaymentVO);
        return Result.success(orderPaymentVO);
    }


    /**
     * 查询历史订单
     *
     * @param pageQueryDTO 页面查询dto
     * @return {@link Result}<{@link PageResult}>
     */
    @GetMapping("/historyOrders")
    public Result<PageResult> getHistoryOrders(OrdersPageQueryDTO pageQueryDTO){
        PageResult result=orderService.getHistoryOrders(pageQueryDTO);
        return Result.success(result);
    }

    /**
     * 查询订单详情
     *
     * @param id id
     * @return {@link Result}<{@link OrderVO}>
     */
    @GetMapping("/orderDetail/{id}")
    public Result<OrderVO> getOrderDetail(@PathVariable Long id){
        OrderVO vo=orderService.getOrderDetail(id);
        return Result.success(vo);
    }


    /**
     * 取消订单
     *
     * @param id id
     * @return {@link Result}
     */
    @PutMapping("/cancel/{id}")
    public Result cancelOrder(@PathVariable Long id) throws Exception {
        orderService.userCancelById(id);
        return Result.success();
    }

    @PostMapping("/repetition/{id}")
    public Result repetitionOrder(@PathVariable Long id){
        orderService.repetitionOrder(id);
        return Result.success();
    }
}
