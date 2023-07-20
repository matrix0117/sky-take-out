package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @AutoFill(value = OperationType.INSERT)
    void addCategory(Category category);

    void deleteCategoryById(Long id);

    Page<Category> getCategories(String name, Integer type);
    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);

    List<Category> getCategoryByType(Integer type);

}
