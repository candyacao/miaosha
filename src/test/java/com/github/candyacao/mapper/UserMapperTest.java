package com.github.candyacao.mapper;

import com.github.candyacao.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() throws Exception {
//		userMapper.insert(new User("aa1", "a123456", UserSexEnum.MAN));

    }

    @Test
    public void testQuery() throws Exception {
        List<User> users = userMapper.getAll();
    }
}
