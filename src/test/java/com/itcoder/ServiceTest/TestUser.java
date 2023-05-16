package com.itcoder.ServiceTest;

import com.itcoder.config.SpringConfig;
import com.itcoder.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class TestUser {
    @Autowired
    private UserService userService;
    @Test
    public void login(){
        System.out.println(userService.login("heima@gmail.com","heimachengxuyuan"));
    }
}
