package com.sky.controller.admin;

import com.sky.entity.Dish;
import com.sky.result.Result;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/dish")
public class DishController {

    private DishService dishService;

    public DishController(DishService d){
        dishService=d;
    }
    @GetMapping("/list")
    public Result<List<Dish>> getDishByCategory(Long categoryid){
        List<Dish> dishList=dishService.getDishByCategory(categoryid);
        return Result.success(dishList);
    }
}
