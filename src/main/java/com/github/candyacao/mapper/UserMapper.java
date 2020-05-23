package com.github.candyacao.mapper;

import com.github.candyacao.enums.UserSexEnum;
import com.github.candyacao.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    List<User> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    User getOneByID(Long id);
    @Select("SELECT * FROM users WHERE userName = #{username}")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    User getOneByUsername(String username);

    @Insert("INSERT INTO users(userName,salt,hashPassword,user_sex,nick_name) VALUES(#{userName}, #{salt}, #{hashPassword}, #{userSex}, #{nickName})")
    void insert(User user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);

}
