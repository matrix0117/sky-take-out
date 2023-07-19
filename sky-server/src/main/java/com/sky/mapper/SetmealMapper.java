package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;

import java.util.List;

@Mapper
public interface SetmealMapper {
    Page<Setmeal> getSetmeals(String name, Long categoryId, Integer status);

    List<Setmeal> getSetmealByCategory(Long categoryId);
}
