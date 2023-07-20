package com.sky.controller.user;

import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("userShopController")
@RequestMapping("/user/shop")
@Slf4j
public class ShopController {

    public static final String KEY = "SHOP_STATUS";
    private final RedisTemplate redisTemplate;


    public ShopController(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    /**
     * 获得营业状态
     *
     * @return {@link Result}<{@link Integer}>
     */
    @GetMapping("/status")
    public Result<Integer> getStatus(){
        return Result.success((Integer) redisTemplate.opsForValue().get(KEY)) ;
    }
}
