package com.sky.service.impl;

import com.sky.entity.Dish;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private DishMapper dishMapper;

    public DishServiceImpl(DishMapper d){
        dishMapper=d;
    }
    @Override
    public List<Dish> getDishByCategory(Long categoryid) {
        return dishMapper.getDishByCategory(categoryid);
    }
}
