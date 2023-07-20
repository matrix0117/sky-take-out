package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper {
    List<Dish> getDishByCategory(Long categoryid);

    Page<Dish> getDishes(String name, Long categoryId, Integer status);

    Dish getDishById(Long id);
    @AutoFill(value = OperationType.INSERT)
    void addDish(Dish dish);
    @AutoFill(value = OperationType.UPDATE)
    void updateDish(Dish dish);


    void deleteDish(Long[] ids);
}
