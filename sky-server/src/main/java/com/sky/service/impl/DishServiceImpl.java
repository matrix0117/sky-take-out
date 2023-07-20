package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishMapper dishMapper;
    private final DishFlavorMapper dishFlavorMapper;

    public DishServiceImpl(DishMapper dishMapper, DishFlavorMapper dishFlavorMapper) {
        this.dishMapper = dishMapper;
        this.dishFlavorMapper = dishFlavorMapper;
    }

    @Override
    public List<Dish> getDishByCategory(Long categoryId) {
        return dishMapper.getDishByCategory(categoryId);
    }

    @Override
    public PageResult getDishes(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<Dish> dishPage = dishMapper.getDishes(dishPageQueryDTO.getName(), dishPageQueryDTO.getCategoryId(), dishPageQueryDTO.getStatus());
        return new PageResult(dishPage.getTotal(), dishPage.getResult());
    }

    @Override
    public DishVO getDishById(Long id) {
        Dish dish = dishMapper.getDishById(id);
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        List<DishFlavor> dishFlavors = dishFlavorMapper.getFlavorsByDishId(id);
        dishVO.setFlavors(dishFlavors);
        return dishVO;
    }

    @Override
    @Transactional
    public void addDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.addDish(dish);
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            Long dishId = dish.getId();
            for (int i = 0; i < flavors.size(); i++) {
                flavors.get(i).setDishId(dishId);
            }
            dishFlavorMapper.addDishFlavor(flavors);
        }

    }

    @Transactional
    @Override
    public void updateDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.updateDish(dish);
        dishFlavorMapper.deleteByDishId(new Long[]{dish.getId()});
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            Long dishId = dish.getId();
            for (int i = 0; i < flavors.size(); i++) {
                flavors.get(i).setDishId(dishId);
            }
            dishFlavorMapper.addDishFlavor(flavors);
        }
    }

    @AutoFill(value = OperationType.UPDATE)
    @Override
    public void updateDishStatus(Integer status, Long id) {
        Dish dish = Dish.builder()
                .id(id)
                .status(status)
                .build();
        dishMapper.updateDish(dish);
    }

    @Override
    @Transactional
    public void deleteDish(Long[] ids) {
        dishMapper.deleteDish(ids);
        dishFlavorMapper.deleteByDishId(ids);
    }


}
