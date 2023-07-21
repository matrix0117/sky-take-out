package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.JwtProperties;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import com.sky.utils.JwtUtil;
import com.sky.vo.UserLoginVO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {
    private static final String WECHAT_URL = "https://api.weixin.qq.com/sns/jscode2session";
    private final JwtProperties jwtProperties;
    private final WeChatProperties weChatProperties;

    private final UserMapper userMapper;

    public UserServiceImpl(JwtProperties jwtProperties, WeChatProperties weChatProperties, UserMapper userMapper) {
        this.jwtProperties = jwtProperties;
        this.weChatProperties = weChatProperties;
        this.userMapper = userMapper;
    }

    @Override
    public UserLoginVO login(UserLoginDTO userLoginDTO) {
        String openId = getOpenId(userLoginDTO);
        User user = userMapper.getUserByOpenId(openId);
        if (user == null) {
            user = User.builder().openid(openId).createTime(LocalDateTime.now()).build();
            userMapper.addUser(user);
        }
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
//        claims.put("openid", user.getOpenid());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);
        return UserLoginVO.builder()
                .openid(openId)
                .id(user.getId())
                .token(token).build();


    }

    private String getOpenId(UserLoginDTO userLoginDTO) {
        String openId;
        HashMap<String, String> params = new HashMap<>();
        params.put("appid", weChatProperties.getAppid());
        params.put("secret", weChatProperties.getSecret());
        params.put("js_code", userLoginDTO.getCode());
        params.put("grant_type", "authorization_code");
        try {
            String response = HttpClientUtil.doPost(WECHAT_URL, params);
            openId = (String) JSON.parseObject(response).get("openid");
        } catch (IOException e) {
            throw new LoginFailedException("获取openid失败");
        }
        if (openId == null) throw new LoginFailedException("获取openid失败");
        return openId;
    }
}
