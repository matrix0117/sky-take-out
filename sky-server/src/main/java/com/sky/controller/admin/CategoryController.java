package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/admin/category")
@RestController
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService c){
        categoryService=c;
    }
    @PostMapping
    public Result addCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }
    @DeleteMapping
    public Result deleteCategoryById(Long id){
        categoryService.deleteCategoryById(id);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> getCategories(CategoryPageQueryDTO categoryPageQueryDTO){
        PageResult pageResult=categoryService.getCategories(categoryPageQueryDTO);
        return Result.success(pageResult);
    }
    @GetMapping("/list")
    public Result<List<Category>> getCategoryByType(Integer type){
        List<Category> categories=categoryService.getCategoryByType(type);
        return Result.success(categories);
    }
    @PutMapping
    public Result updateCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result updateCategoryStatus(@PathVariable Integer status, Long id){
        categoryService.updateCategoryStatus(status,id);
        return Result.success();
    }
}
