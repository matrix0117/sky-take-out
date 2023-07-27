package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class MyTask {

    private final OrderMapper orderMapper;

    public MyTask(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void processTimeOutOrder(){
        LocalDateTime time = LocalDateTime.now();
        List<Orders> orders=orderMapper.getByStatusAndOrderTime(Orders.PENDING_PAYMENT, time.minusMinutes(15));
        for (Orders order : orders) {
            log.info("订单：{}超时",order.getId());
            order.setCancelTime(time);
            order.setCancelReason("超时未支付");
            order.setStatus(Orders.CANCELLED);
            orderMapper.update(order);
        }
    }
    @Scheduled(cron = "0 0 4 * * ?")
    public void processDeliveryOrder(){
        LocalDateTime time = LocalDateTime.now();
        List<Orders> orders=orderMapper.getByStatusAndOrderTime(Orders.DELIVERY_IN_PROGRESS, time.minusHours(4));
        for (Orders order : orders) {
            log.info("订单：{}自动完成",order.getId());
            order.setStatus(Orders.COMPLETED);
            orderMapper.update(order);
        }
    }


}
