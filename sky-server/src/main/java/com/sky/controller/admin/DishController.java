package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("adminDishController")
@RequestMapping("/admin/dish")
public class DishController {

    private final DishService dishService;

    public DishController(DishService d) {
        dishService = d;
    }

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId 类别id
     * @return {@link Result}<{@link List}<{@link Dish}>>
     */
    @GetMapping("/list")
    public Result<List<Dish>> getDishByCategory(Long categoryId) {
        List<Dish> dishList = dishService.getDishByCategory(categoryId);
        return Result.success(dishList);
    }

    /**
     * 菜品分页查询
     *
     * @param dishPageQueryDTO 菜页面查询dto
     * @return {@link Result}<{@link PageResult}>
     */
    @GetMapping("/page")
    public Result<PageResult> getDishes(DishPageQueryDTO dishPageQueryDTO) {
        PageResult pageResult=dishService.getDishes(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 通过id获取菜
     *
     * @param id id
     * @return {@link Result}<{@link DishVO}>
     */
    @GetMapping("/{id}")
    public Result<DishVO> getDishById(@PathVariable Long id){
        DishVO dishVO = dishService.getDishById(id);
        return Result.success(dishVO);
    }

    /**
     * 新增菜品
     * @param dishDTO 菜dto
     * @return {@link Result}
     */
    @PostMapping
    public Result addDish(@RequestBody DishDTO dishDTO){
        dishService.addDish(dishDTO);
        return Result.success();
    }

    /**
     * 修改菜品
     *
     * @param dishDTO 菜dto
     * @return {@link Result}
     */
    @PutMapping
    public Result updateDish(@RequestBody DishDTO dishDTO){
        dishService.updateDish(dishDTO);
        return Result.success();
    }

    /**
     * 更新菜品状态
     *
     * @param status 状态
     * @param id     id
     * @return {@link Result}
     */
    @PostMapping("/status/{status}")
    public Result updateDishStatus(@PathVariable Integer status, Long id){
        dishService.updateDishStatus(status,id);
        return Result.success();
    }
    @DeleteMapping
    public Result deleteDish(Long[] ids){
        dishService.deleteDish(ids);
        return Result.success();
    }
}
