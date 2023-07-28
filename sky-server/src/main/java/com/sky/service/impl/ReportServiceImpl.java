package com.sky.service.impl;

import com.sky.dto.GoodsSalesDTO;
import com.sky.entity.Orders;
import com.sky.mapper.ReportMapper;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportMapper reportMapper;

    public ReportServiceImpl(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    @Override
    public TurnoverReportVO turnoverStatistics(LocalDate begin, LocalDate end) {
        List<Orders> orders = reportMapper.turnoverStatistics(begin.atStartOfDay(), end.atTime(LocalTime.MAX));
        StringJoiner dataList = new StringJoiner(",");
        StringJoiner turnoverList = new StringJoiner(",");
        int idx = 0;
        while (!begin.isAfter(end)) {
            BigDecimal sum = new BigDecimal(0);
            while (idx < orders.size() && orders.get(idx).getOrderTime().toLocalDate().equals(begin)) {
                sum = sum.add(orders.get(idx).getAmount());
                idx++;
            }
            dataList.add(begin.toString());
            turnoverList.add(sum.toString());
            begin = begin.plusDays(1);
        }
        return new TurnoverReportVO(dataList.toString(), turnoverList.toString());
    }

    @Override
    public UserReportVO userStatistics(LocalDate begin, LocalDate end) {
        List<LocalDateTime> users = reportMapper.userStatistics(begin.atStartOfDay(), end.atTime(LocalTime.MAX));
        StringJoiner dataList = new StringJoiner(",");
        StringJoiner newUserList = new StringJoiner(",");
        StringJoiner totalUserList = new StringJoiner(",");
        int idx = 0, total = 0;
        while (!begin.isAfter(end)) {
            int newUser = 0;
            while (idx < users.size() && users.get(idx).toLocalDate().equals(begin)) {
                newUser++;
                idx++;
            }
            total += newUser;
            dataList.add(begin.toString());
            newUserList.add(String.valueOf(newUser));
            totalUserList.add(String.valueOf(total));
            begin = begin.plusDays(1);
        }
        return new UserReportVO(dataList.toString(), totalUserList.toString(), newUserList.toString());
    }

    @Override
    public OrderReportVO ordersStatistics(LocalDate begin, LocalDate end) {
        List<Orders> orders = reportMapper.ordersStatistics(begin.atStartOfDay(), end.atTime(LocalTime.MAX));
        StringJoiner dataList = new StringJoiner(",");
        StringJoiner orderCountList = new StringJoiner(",");
        StringJoiner validOrderCountList = new StringJoiner(",");
        int idx = 0, validCount = 0;
        while (!begin.isAfter(end)) {
            int count = 0, valid = 0;
            while (idx < orders.size() && orders.get(idx).getOrderTime().toLocalDate().equals(begin)) {
                count++;
                if (Objects.equals(orders.get(idx).getStatus(), Orders.COMPLETED)) valid++;
                idx++;
            }
            dataList.add(begin.toString());
            validCount += valid;
            orderCountList.add(String.valueOf(count));
            validOrderCountList.add(String.valueOf(valid));
            begin = begin.plusDays(1);
        }
        return new OrderReportVO(dataList.toString(), orderCountList.toString(), validOrderCountList.toString(), orders.size()
                , validCount, orders.size() == 0 ? 0 : (double) validCount / (double) orders.size());
    }

    @Override
    public SalesTop10ReportVO salesTop10(LocalDate begin, LocalDate end) {

        List<GoodsSalesDTO> goodsSalesDTOS = reportMapper.salesTop10(begin.atStartOfDay(), end.atTime(LocalTime.MAX));
        StringJoiner nameList = new StringJoiner(",");
        StringJoiner numberList = new StringJoiner(",");
        for (GoodsSalesDTO goodsSalesDTO : goodsSalesDTOS) {
            nameList.add(goodsSalesDTO.getName());
            numberList.add(goodsSalesDTO.getNumber().toString());
        }
        return new SalesTop10ReportVO(nameList.toString(),numberList.toString());
    }

}
