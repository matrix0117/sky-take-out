package com.sky.service;

import com.sky.entity.Dish;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DishService {
    List<Dish> getDishByCategory(Long categoryid);
}
