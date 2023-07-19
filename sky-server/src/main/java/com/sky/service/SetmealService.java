package com.sky.service;

import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;

import java.util.List;

public interface SetmealService {
    PageResult getSetmeals(SetmealPageQueryDTO setmealPageQueryDTO);

//    List<Setmeal> getSetmealByCategory(Long categoryId);
}
