package com.github.candyacao.enums;

public enum UserSexEnum {
    MAN(0,"男"), WOMAN(1,"女");
    private String name;
    private int index;

    UserSexEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    // 普通方法
    public static String getName(int index){
        for (UserSexEnum sexEnum: UserSexEnum.values()) {
            if(sexEnum.getIndex() == index){
                return sexEnum.name;
            }
        }
        return null;
    }
    // 普通方法
    public static UserSexEnum getSexEnum(int index){
        for (UserSexEnum sexEnum: UserSexEnum.values()) {
            if(sexEnum.getIndex() == index){
                return sexEnum;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
