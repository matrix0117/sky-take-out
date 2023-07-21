package com.sky.controller.user;

import com.sky.entity.Setmeal;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userSetmealController")
@Slf4j
@RequestMapping("/user/setmeal")
public class SetmealController {

    private final SetmealService setmealService;

    public SetmealController(SetmealService setmealService) {
        this.setmealService = setmealService;
    }

    /**
     * 根据分类id查询套餐
     *
     * @param categoryId 类别id
     * @return {@link Result}<{@link List}<{@link Setmeal}>>
     */
    @GetMapping("/list")
    public Result<List<Setmeal>> getSetmealByCategoryId(Long categoryId){
        List<Setmeal> setmeals = setmealService.getSetmealByCategoryId(categoryId);
        return Result.success(setmeals);
    }

    /**
     * 根据套餐id查询包含的菜品
     *
     * @param id id
     * @return {@link Result}<{@link List}<{@link DishItemVO}>>
     */
    @GetMapping("/dish/{id}")
    public Result<List<DishItemVO>> getDishBySetmealId(@PathVariable Long id){
        List<DishItemVO> setmealDishVOS=setmealService.getDishBySetmealId(id);
        return Result.success(setmealDishVOS);
    }
}
