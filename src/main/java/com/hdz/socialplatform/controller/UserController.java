package com.hdz.socialplatform.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hdz.socialplatform.dao.FansMapper;
import com.hdz.socialplatform.dao.HelloMapper;
import com.hdz.socialplatform.entity.User;
import com.hdz.socialplatform.service.FansService;
import com.hdz.socialplatform.service.LoginService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Host;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hdz
 * @description 登录页
 * @create 2022年02月12日 12:30
 */

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    LoginService loginService;

    @Autowired
    FansService fansService;

    //登录判断逻辑
    @PostMapping(value = "/login")
    public ModelAndView login(User u, HttpServletRequest request) {
        User user = loginService.login(u.getName(), u.getPassword());
        ModelAndView mv;
//        System.out.println(request.getHeader("host"));
        //登录成功转主态页面
        if (user != null) {
            user.setFansNum(fansService.countFans(user));
            user.setFollowsNum(fansService.countFollowers(user));
            logger.info(user);
            mv = new ModelAndView("redirect:/user/home");
            mv.addObject(user);
            request.getSession().setAttribute("user", user);
        } else {
            mv = new ModelAndView("page/login");
        }
        return mv;
    }

    //登录页
    @GetMapping(value = "/login")
    public ModelAndView login() {
        return new ModelAndView("page/login");
    }

    //个人主页
    @GetMapping(value = {"", "/", "/home"})
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            mv.setViewName("redirect:/user/login");
        } else {
            mv.setViewName("page/home");
            mv.addObject(user);
//            boolean canSee=false;
//            mv.addObject("canSee",canSee);
        }
        return mv;
    }

    @GetMapping(value = "/home/{id}")
    public ModelAndView home(@PathVariable(value = "id") int id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User guest = (User) request.getSession().getAttribute("user");
        User user = fansService.getUser(id);

        //根据访客id判断是否在主人黑名单中
        int guestId = guest.getId();
        if (guestId == id) {
            mv.setViewName("page/home");
            mv.addObject(user);
            return mv;
        }

        Boolean canSee = fansService.inWhite(guestId, id);
        if (canSee) {     //不在黑名单
            user.setFansNum(fansService.countFans(user));
            user.setFollowsNum(fansService.countFollowers(user));
        }
        boolean guestFollowUser = fansService.ifFollow(id, guestId);
        boolean userFollowGuest = fansService.ifFollow(guestId, id);

        mv.addObject("guestFollowUser", guestFollowUser);
        mv.addObject("userFollowGuest", userFollowGuest);

        mv.addObject("canSee", canSee);
        mv.addObject(user);
        mv.setViewName("page/home");
        return mv;
    }

}
