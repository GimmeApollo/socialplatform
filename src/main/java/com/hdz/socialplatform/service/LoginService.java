package com.hdz.socialplatform.service;

import com.hdz.socialplatform.dao.UserMapper;
import com.hdz.socialplatform.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author hdz
 * @description TODO
 * @create 2022年03月12日 15:34
 */

@Service
public class LoginService {

    @Autowired
    UserMapper userMapper;

    public User login(String name, String password){
        return userMapper.getUser(name,password);
    }

}
