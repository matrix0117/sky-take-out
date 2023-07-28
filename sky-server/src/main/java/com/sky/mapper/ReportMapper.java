package com.sky.mapper;

import com.sky.dto.GoodsSalesDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ReportMapper {


    List<Orders> turnoverStatistics(LocalDateTime begin, LocalDateTime end);

    List<LocalDateTime> userStatistics(LocalDateTime begin, LocalDateTime end);

    List<Orders> ordersStatistics(LocalDateTime begin, LocalDateTime end);

    List<GoodsSalesDTO> salesTop10(LocalDateTime begin, LocalDateTime end);
}
