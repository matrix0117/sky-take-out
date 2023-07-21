package com.sky.mapper;

import com.sky.entity.SetmealDish;
import com.sky.vo.DishItemVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    List<SetmealDish> getSetmealDishBySetmealId(Long id);

    void deleteSetmealDishBySetmealId(Long[] ids);
    void addSetmealDish(List<SetmealDish> setmealDishes);

    List<DishItemVO> getDishBySetmealId(Long id);
}
