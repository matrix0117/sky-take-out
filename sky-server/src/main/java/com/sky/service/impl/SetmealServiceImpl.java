package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {

    private SetmealMapper setmealMapper;

    public SetmealServiceImpl(SetmealMapper s){
        setmealMapper=s;
    }
    @Override
    public PageResult getSetmeals(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());
        Page<Setmeal> page=setmealMapper.getSetmeals(setmealPageQueryDTO.getName(),setmealPageQueryDTO.getCategoryId(),setmealPageQueryDTO.getStatus());
        return new PageResult(page.getTotal(),page.getResult());
    }

//    @Override
//    public List<Setmeal> getSetmealByCategory(Long categoryId) {
//        return setmealMapper.getSetmeals(null,categoryId,null);
//    }
}
