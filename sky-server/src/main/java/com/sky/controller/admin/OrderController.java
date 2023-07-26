package com.sky.controller.admin;

import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import org.springframework.web.bind.annotation.*;

@RestController("adminOrderController")
@RequestMapping("/admin/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 取消订单
     *
     * @param ordersCancelDTO 订单取消dto
     * @return {@link Result}
     */
    @PutMapping("/cancel")
    public Result cancelOrder(@RequestBody OrdersCancelDTO ordersCancelDTO) throws Exception {
        orderService.CancelOrder(ordersCancelDTO);
        return Result.success();
    }

    /**
     * 统计数据
     *
     * @return {@link Result}<{@link OrderStatisticsVO}>
     */
    @GetMapping("/statistics")
    public Result<OrderStatisticsVO> statistics(){
        OrderStatisticsVO vo = orderService.statistics();
        return Result.success(vo);
    }

    /**
     * 完成订单
     *
     * @param id id
     * @return {@link Result}
     */
    @PutMapping("/complete/{id}")
    public Result completeOrder(@PathVariable Long id){
        orderService.completeOrder(id);
        return Result.success();
    }

    /**
     * 拒绝订单
     *
     * @param ordersRejectionDTO 订单被拒绝dto
     * @return {@link Result}
     */
    @PutMapping("/rejection")
    public Result rejectOrder(@RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        orderService.rejectOrder(ordersRejectionDTO);
        return Result.success();
    }

    /**
     * 接单
     *
     * @param confirmDTO 确认dto
     * @return {@link Result}
     */
    @PutMapping("/confirm")
    public Result confirmOrder(@RequestBody OrdersConfirmDTO confirmDTO){
        orderService.confirmOrder(confirmDTO);
        return Result.success();
    }

    /**
     * 查询订单详情
     *
     * @param id id
     * @return {@link Result}<{@link OrderVO}>
     */
    @GetMapping("/details/{id}")
    public Result<OrderVO> details(@PathVariable Long id){
        OrderVO orderDetail = orderService.getOrderDetail(id);
        return Result.success(orderDetail);
    }

    /**
     * 派送订单
     *
     * @param id id
     * @return {@link Result}
     */
    @PutMapping("/delivery/{id}")
    public Result deliveryOrder(@PathVariable Long id){
        orderService.deliveryOrder(id);
        return Result.success();
    }

    @GetMapping("/conditionSearch")
    public Result<PageResult> conditionSearch(OrdersPageQueryDTO pageQueryDTO){
        PageResult result=orderService.getOrders(pageQueryDTO);
        return Result.success(result);
    }
}
