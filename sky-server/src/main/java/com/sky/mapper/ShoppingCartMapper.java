package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {


    ShoppingCart get(ShoppingCart shoppingCart);

    void update(ShoppingCart s);

    void insert(ShoppingCart shoppingCart);

    void delete(ShoppingCart shoppingCart);

    List<ShoppingCart> list(Long userId);
}
