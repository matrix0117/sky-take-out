package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }


    /**
     * 添加购物车
     *
     * @param shoppingCartDTO 购物车dto
     * @return {@link Result}
     */
    @PostMapping("/add")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        shoppingCartService.add(shoppingCartDTO);
        return Result.success();
    }

    /**
     * 删除单个商品
     *
     * @param shoppingCartDTO 购物车dto
     * @return {@link Result}
     */
    @PostMapping("/sub")
    public Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        shoppingCartService.sub(shoppingCartDTO);
        return Result.success();
    }

    /**
     * 查看购物车
     *
     * @return {@link Result}<{@link List}<{@link ShoppingCart}>>
     */
    @GetMapping("/list")
    public Result<List<ShoppingCart>> list(){
        List<ShoppingCart> shoppingCarts=shoppingCartService.list();
        return Result.success(shoppingCarts);
    }

    /**
     * 清空购物车
     *
     * @return {@link Result}
     */
    @DeleteMapping("clean")
    public Result clean(){
        shoppingCartService.clean();
        return Result.success();
    }


}
