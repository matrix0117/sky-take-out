package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    List<Dish> getDishByCategory(Long categoryId);

    PageResult getDishes(DishPageQueryDTO dishPageQueryDTO);

    DishVO getDishById(Long id);

    void addDish(DishDTO dishDTO);

    void updateDish(DishDTO dishDTO);

    void updateDishStatus(Integer status, Long id);

    void deleteDish(Long[] ids);

    List<DishVO> getDishByCategoryId(Long categoryId);
}
