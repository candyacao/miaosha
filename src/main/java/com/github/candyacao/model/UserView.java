package com.github.candyacao.model;

import com.github.candyacao.enums.UserSexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserView {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private UserSexEnum userSex;
    private String nickName;
}
