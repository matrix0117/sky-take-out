package com.sky.controller.user;

import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userDishController")
@Slf4j
@RequestMapping("/user/dish")
public class DishController {
    private final DishService dishService;

    private final RedisTemplate redisTemplate;

    public DishController(DishService dishService, RedisTemplate redisTemplate) {
        this.dishService = dishService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId 类别id
     * @return {@link Result}<{@link List}<{@link DishVO}>>
     */
    @GetMapping("/list")
    @Cacheable(cacheNames = "dishCache",key = "#categoryId")
    public Result<List<DishVO>> getDishByCategoryId(Long categoryId) {
        List<DishVO> dishVO = dishService.getDishByCategoryId(categoryId);
        return Result.success(dishVO);
    }
}
