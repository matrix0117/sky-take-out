package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {


    List<DishFlavor> getFlavorsByDishId(Long id);

    void addDishFlavor(List<DishFlavor> flavors);



    void deleteByDishId(Long[] dishIds);
}
