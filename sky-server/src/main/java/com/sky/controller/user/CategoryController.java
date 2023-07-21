package com.sky.controller.user;

import com.sky.entity.Category;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userCategoryController")
@Slf4j
@RequestMapping("/user/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 分类条件查询
     *
     * @param type 类型
     * @return {@link Result}<{@link List}<{@link Category}>>
     */
    @GetMapping("/list")
    public Result<List<Category>> getCategoryByType(Integer type){
        List<Category> categories = categoryService.getCategoryByType(type);
        return Result.success(categories);
    }
}
