package com.sky.mapper;


import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getUserByOpenId(String openId);

    void addUser(User user);
}
