package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin/report")
@Slf4j
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * 营业额统计
     *
     * @param begin 开始
     * @param end   结束
     * @return {@link Result}<{@link TurnoverReportVO}>
     */
    @GetMapping("/turnoverStatistics")
    public Result<TurnoverReportVO> turnoverStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        TurnoverReportVO reportVO=reportService.turnoverStatistics(begin,end);
        return Result.success(reportVO);
    }


    /**
     * 用户数据统计
     *
     * @param begin 开始
     * @param end   结束
     * @return {@link Result}<{@link UserReportVO}>
     */
    @GetMapping("/userStatistics")
    public Result<UserReportVO> userStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                               @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        UserReportVO reportVO = reportService.userStatistics(begin,end);
        return Result.success(reportVO);
    }

    /**
     * 订单信息统计
     *
     * @param begin 开始
     * @param end   结束
     * @return {@link Result}<{@link OrderReportVO}>
     */
    @GetMapping("/ordersStatistics")
    public Result<OrderReportVO> ordersStatistics(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        OrderReportVO reportVO = reportService.ordersStatistics(begin,end);
        return Result.success(reportVO);
    }

    @GetMapping("/top10")
    public Result<SalesTop10ReportVO> salesTop10(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        SalesTop10ReportVO top10ReportVO = reportService.salesTop10(begin,end);
        return Result.success(top10ReportVO);
    }

}
