package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    PageResult getSetmeals(SetmealPageQueryDTO setmealPageQueryDTO);

    SetmealVO getSetmealById(Long id);

    void updateSetmeal(SetmealDTO setmealDTO);

    void updateStatus(Integer status, Long id);

    void deleteSetmeal(Long[] ids);

    void addSetmeal(SetmealDTO setmealDTO);

    List<Setmeal> getSetmealByCategoryId(Long categoryId);

    List<DishItemVO> getDishBySetmealId(Long id);

//    List<Setmeal> getSetmealByCategory(Long categoryId);
}
