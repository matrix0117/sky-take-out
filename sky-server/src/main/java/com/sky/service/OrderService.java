package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {
    OrderSubmitVO orderSubmit(OrdersSubmitDTO ordersSubmitDTO);

    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    void paySuccess(String outTradeNo);

    PageResult getHistoryOrders(OrdersPageQueryDTO pageQueryDTO);

    OrderVO getOrderDetail(Long id);

    void userCancelById(Long id) throws Exception;

    void repetitionOrder(Long id);

    OrderStatisticsVO statistics();

    void completeOrder(Long id);

    void rejectOrder(OrdersRejectionDTO ordersRejectionDTO) throws Exception;

    void confirmOrder(OrdersConfirmDTO confirmDTO);

    void deliveryOrder(Long id);

    PageResult getOrders(OrdersPageQueryDTO pageQueryDTO);

    void CancelOrder(OrdersCancelDTO ordersCancelDTO) throws Exception;
}
