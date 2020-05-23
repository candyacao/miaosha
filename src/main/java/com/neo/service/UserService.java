package com.neo.service;

import com.neo.enums.UserSexEnum;
import com.neo.model.User;

public interface UserService {
    User getOne(Long id);
    User getOne(String username);
    // 注册
    void signUp(String username, String passwd, UserSexEnum userSex, String nickName);
    // 退出
    void logout();
    // 登录
    void signIn(String username, String passwd);

}
