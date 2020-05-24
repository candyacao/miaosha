package com.github.candyacao.service.impl;

import com.github.candyacao.enums.UserSexEnum;
import com.github.candyacao.exception.GlobleException;
import com.github.candyacao.mapper.UserMapper;
import com.github.candyacao.model.User;
import com.github.candyacao.model.UserView;
import com.github.candyacao.service.UserService;
import com.github.candyacao.utils.MD5Util;
import com.github.candyacao.utils.RandomString;
import com.github.candyacao.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.github.candyacao.common.enums.ResultStatus.*;

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
        if (StringUtils.isEmpty(username)){
            throw new GlobleException(USERNAME_OR_PASSWD_EMPTY);
        }
        return userMapper.getOneByUsername(username);
    }

    // 注册
    @Override
    public User signUp(String username, String passwd, UserSexEnum userSex, String nickName) {
        User user = getOne(username);
        if(null != user){
            throw new GlobleException(RESIGETR_ISEXSIT);
        }
        if(StringUtils.isEmpty(passwd)){
            throw new GlobleException(USERNAME_OR_PASSWD_EMPTY);
        }
        String halt = RandomString.getRandomString(10);
        passwd = MD5Util.md5(passwd + halt);
        User insertUser = new User(username, halt, passwd, userSex, nickName);
        // Todo: 插入是否成功
        userMapper.insert(insertUser);
        return insertUser;
    }

    @Override
    public void logout() {

    }

    // 登录
    @Override
    public UserView signIn(String username, String passwd) {
        if (StringUtils.isEmpty(username) && StringUtils.isEmpty(passwd)) {
           throw new GlobleException(USERNAME_OR_PASSWD_EMPTY);
        }
        // 根据用户名去数据查询盐值及存储密码，判断用户输入的"密码+盐"生成的MD5编码是否与数据库存储的相同
        // 相同，则通过验证
        User user = getOne(username);
        if (user == null) {
            throw new GlobleException(USER_NOT_EXIST);
        }
        String salt = user.getSalt();
        String md5info = user.getHashPassword();
        String realPassword = MD5Util.md5(passwd + salt);
        if (!md5info.equals(realPassword)) {
            throw new GlobleException(PASSWORD_ERROR);
        }
        UserView retUser = new UserView(user.getId(),user.getUserName(),user.getUserSex(),user.getNickName());
       return retUser;
    }
}
