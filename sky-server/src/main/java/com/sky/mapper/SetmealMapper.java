package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SetmealMapper {
    Page<SetmealVO> getSetmeals(String name, Long categoryId, Integer status);

    List<Setmeal> getSetmealByCategoryId(Long categoryId);

    Setmeal getSetmealById(Long id);
    @AutoFill(value = OperationType.UPDATE)
    void updateSetmeal(Setmeal setmeal);

    void deleteSetmeals(Long[] ids);
    @AutoFill(value = OperationType.INSERT)
    void addSetmeal(Setmeal setmeal);


    Integer countByMap(Map map);
}
