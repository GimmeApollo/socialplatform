package com.hdz.socialplatform.controller;

import com.hdz.socialplatform.dao.HelloMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hdz
 * @description TODO
 * @create 2022年02月12日 12:30
 */

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    HelloMapper helloMapper;

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(name = "id") long id){
        return helloMapper.getHelloByIdUp(id).toString();
    }

}
