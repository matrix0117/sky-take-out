package com.sky.service.impl;

import com.sky.dto.GoodsSalesDTO;
import com.sky.entity.Orders;
import com.sky.mapper.ReportMapper;
import com.sky.service.ReportService;
import com.sky.service.WorkspaceService;
import com.sky.vo.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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
    private final WorkspaceService workspaceService;

    public ReportServiceImpl(ReportMapper reportMapper, WorkspaceService workspaceService) {
        this.reportMapper = reportMapper;
        this.workspaceService = workspaceService;
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

    @Override
    public void exportBusinessData(HttpServletResponse response) {
        LocalDate begin = LocalDate.now().minusDays(30);
        LocalDate end = LocalDate.now().minusDays(1);
        //查询概览运营数据，提供给Excel模板文件
        BusinessDataVO businessData = workspaceService.getBusinessData(LocalDateTime.of(begin,LocalTime.MIN), LocalDateTime.of(end, LocalTime.MAX));
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template/运营数据报表模板.xlsx");
        try {
            //基于提供好的模板文件创建一个新的Excel表格对象
            assert inputStream != null;
            XSSFWorkbook excel = new XSSFWorkbook(inputStream);
            //获得Excel文件中的一个Sheet页
            XSSFSheet sheet = excel.getSheet("Sheet1");

            sheet.getRow(1).getCell(1).setCellValue(begin + "至" + end);
            //获得第4行
            XSSFRow row = sheet.getRow(3);
            //获取单元格
            row.getCell(2).setCellValue(businessData.getTurnover());
            row.getCell(4).setCellValue(businessData.getOrderCompletionRate());
            row.getCell(6).setCellValue(businessData.getNewUsers());
            row = sheet.getRow(4);
            row.getCell(2).setCellValue(businessData.getValidOrderCount());
            row.getCell(4).setCellValue(businessData.getUnitPrice());
            for (int i = 0; i < 30; i++) {
                LocalDate date = begin.plusDays(i);
                //准备明细数据
                businessData = workspaceService.getBusinessData(LocalDateTime.of(date,LocalTime.MIN), LocalDateTime.of(date, LocalTime.MAX));
                row = sheet.getRow(7 + i);
                row.getCell(1).setCellValue(date.toString());
                row.getCell(2).setCellValue(businessData.getTurnover());
                row.getCell(3).setCellValue(businessData.getValidOrderCount());
                row.getCell(4).setCellValue(businessData.getOrderCompletionRate());
                row.getCell(5).setCellValue(businessData.getUnitPrice());
                row.getCell(6).setCellValue(businessData.getNewUsers());
            }
            //通过输出流将文件下载到客户端浏览器中
            ServletOutputStream out = response.getOutputStream();
            excel.write(out);
            //关闭资源
            out.flush();
            out.close();
            excel.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
