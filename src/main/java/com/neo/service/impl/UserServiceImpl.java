package com.neo.service.impl;

import com.neo.enums.UserSexEnum;
import com.neo.mapper.UserMapper;
import com.neo.model.User;
import com.neo.service.UserService;
import com.neo.utils.MD5Util;
import com.neo.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getOne(Long id) {
        return userMapper.getOneByID(id);
    }

    @Override
    public User getOne(String username) {
        return userMapper.getOneByUsername(username);
    }

    // 注册
    @Override
    public void signUp(String username, String passwd, UserSexEnum userSex, String nickName) {
        String halt = RandomString.getRandomString(10);
        passwd = MD5Util.md5(passwd+halt);
        User user = new User(username, halt, passwd, userSex, nickName);
        userMapper.insert(user);
    }

    @Override
    public void logout() {

    }

    // 登录
    @Override
    public void signIn(String username, String passwd) {


    }
}
