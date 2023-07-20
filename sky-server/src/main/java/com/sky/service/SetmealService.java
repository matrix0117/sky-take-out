package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

public interface SetmealService {
    PageResult getSetmeals(SetmealPageQueryDTO setmealPageQueryDTO);

    SetmealVO getSetmealById(Long id);

    void updateSetmeal(SetmealDTO setmealDTO);

    void updateStatus(Integer status, Long id);

    void deleteSetmeal(Long[] ids);

    void addSetmeal(SetmealDTO setmealDTO);

//    List<Setmeal> getSetmealByCategory(Long categoryId);
}
