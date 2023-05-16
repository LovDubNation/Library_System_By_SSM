package com.itcoder.MapperTest;

import com.itcoder.config.SpringConfig;
import com.itcoder.mapper.UserMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class Test {
    @Autowired
    private UserMapper userMapper;
    @org.junit.Test
    public void getUser(){
        System.out.println(userMapper.getUserByEmail("heima@gmail.com"));
    }
}
