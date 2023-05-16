package com.itcoder.service.serviceImpl;

import com.itcoder.mapper.UserMapper;
import com.itcoder.pojo.User;
import com.itcoder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String userEmail, String password) {
        LOGGER.info("验证用户名是否正确");
        User user = userMapper.getUserByEmail(userEmail);
        if (user != null){
            if (password.equals(user.getUserPassword())){
                return user;
            }
        }
        return null;
    }
}
