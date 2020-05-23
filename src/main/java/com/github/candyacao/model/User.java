package com.github.candyacao.model;

import com.github.candyacao.enums.UserSexEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String salt;
    private String hashPassword;
    private UserSexEnum userSex;
    private String nickName;

    public User(String userName, String salt, String hashPassword, UserSexEnum userSex, String nickName) {
        this.userName = userName;
        this.salt = salt;
        this.hashPassword = hashPassword;
        this.userSex = userSex;
        this.nickName = nickName;
    }
}
