package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    void cancelOrder(OrdersCancelDTO id);

    OrderStatisticsVO statistics();


    Page<OrderVO> getOrders(OrdersPageQueryDTO pageQueryDTO);
}
