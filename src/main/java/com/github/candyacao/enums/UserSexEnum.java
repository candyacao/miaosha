package com.github.candyacao.enums;


import lombok.Getter;

public enum UserSexEnum {
    MAN(0, "男"), WOMAN(1, "女");
    
    @Getter
    private String name;
    @Getter
    private int index;

    UserSexEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    // 普通方法
    public static String getName(int index) {
        for (UserSexEnum sexEnum : UserSexEnum.values()) {
            if (sexEnum.getIndex() == index) {
                return sexEnum.name;
            }
        }
        return null;
    }

    // 普通方法
    public static UserSexEnum getSexEnum(int index) {
        for (UserSexEnum sexEnum : UserSexEnum.values()) {
            if (sexEnum.getIndex() == index) {
                return sexEnum;
            }
        }
        return null;
    }
}
