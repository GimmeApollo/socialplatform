package com.hdz.socialplatform.service;

import com.hdz.socialplatform.dao.UserMapper;
import com.hdz.socialplatform.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author hdz
 * @description TODO
 * @create 2022年03月12日 15:34
 */
@Slf4j
@Service
public class LoginService {

    private static final Logger logger = LogManager.getLogger(LoginService.class);

    @Autowired
    private UserMapper userMapper;

    public User login(String name, String password) {
        return userMapper.getUser(name, password);
    }

}
