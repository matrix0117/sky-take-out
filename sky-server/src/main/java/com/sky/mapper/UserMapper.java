package com.sky.mapper;


import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    User getUserByOpenId(String openId);

    void addUser(User user);
    @Select("select * from user where id = #{userId}")
    User getById(Long userId);
}
