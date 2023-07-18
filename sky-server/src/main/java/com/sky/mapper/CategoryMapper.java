package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    public void addCategory(Category category);

    void deleteCategoryById(Long id);

    Page<Category> getCategories(String name, Integer type);

    void update(Category category);

    List<Category> getCategoryByType(Integer type);
}
