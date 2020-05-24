package com.github.candyacao.service;

import com.github.candyacao.enums.UserSexEnum;
import com.github.candyacao.model.User;

public interface UserService {
    User getOne(Long id);

    User getOne(String username);

    // 注册
    void signUp(String username, String passwd, UserSexEnum userSex, String nickName);

    // 退出
    void logout();

    // 登录
    User signIn(String username, String passwd);

}
