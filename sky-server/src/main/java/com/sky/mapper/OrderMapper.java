package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    void insertOrder(Orders orders);


    /**
     * 根据订单号查询订单
     *
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    void update(Orders orders);

    Page<OrderVO> getHistoryOrders(OrdersPageQueryDTO pageQueryDTO);

    @Select("select * from orders where id = #{id}")
    OrderVO getById(Long id);


    OrderStatisticsVO statistics();


    Page<OrderVO> getOrders(OrdersPageQueryDTO pageQueryDTO);
    @Select("select * from orders where status = #{status} and order_time < #{time}")
    List<Orders> getByStatusAndOrderTime(Integer status, LocalDateTime time);

    Integer countByMap(Map map);

    Double sumByMap(Map map);
}
