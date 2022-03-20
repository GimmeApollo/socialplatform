package com.hdz.socialplatform.controller;

import com.hdz.socialplatform.entity.FansVO;
import com.hdz.socialplatform.entity.StarVO;
import com.hdz.socialplatform.entity.User;
import com.hdz.socialplatform.service.FansService;
import com.hdz.socialplatform.utils.Pager;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hdz
 * @description TODO
 * @create 2022年03月18日 20:30
 */

@Slf4j
@Controller
@RequestMapping("/fans")
public class FansController {

    private static final Logger logger = LogManager.getLogger(FansController.class);

    @Autowired
    FansService fansService;

    //获取粉丝列表
    @GetMapping(value = "/follows")
    public ModelAndView getFans(@RequestParam("id") int id, @RequestParam("page") int page,@RequestParam("size") int size){
        ModelAndView mv = new ModelAndView();
        Pager<FansVO> pager = fansService.fansByPager(id, page, size);
        User user = fansService.getUser(id);
        mv.addObject(pager);
        mv.addObject(user);
        mv.setViewName("page/fans");
        return mv;
    }

    //获取关注列表
    //获取粉丝列表
    @GetMapping(value = "/stars")
    public ModelAndView getStars(@RequestParam("id") int id, @RequestParam("page") int page,@RequestParam("size") int size){
        ModelAndView mv = new ModelAndView();
        Pager<StarVO> pager = fansService.starByPager(id, page, size);
        User user = fansService.getUser(id);
        mv.addObject(pager);
        mv.addObject(user);
        mv.setViewName("page/stars");
        return mv;
    }

    //关注
    @GetMapping(value = "/follow/{id}")
    public void follow(@PathVariable(value = "id") int id, HttpServletRequest request, HttpServletResponse response){
        User guest = (User) request.getSession().getAttribute("user");

        int guestId = guest.getId();
        if( fansService.follow(id, guestId)){
            logger.info("id:"+guestId+"关注id:"+id+"成功");
        }else{
            logger.error("id:"+guestId+"关注id:"+id+"失败");
        }
        response.setStatus(200);    //暂时这么设置吧
    }

    //取消关注
    @GetMapping(value = "/unfollow/{id}")
    public void unfollow(@PathVariable(value = "id") int id, HttpServletRequest request, HttpServletResponse response){
        User guest = (User) request.getSession().getAttribute("user");

        int guestId = guest.getId();
        if( fansService.unfollow(id, guestId)){
            logger.info("id:"+guestId+"取关id:"+id+"成功");
        }else{
            logger.error("id:"+guestId+"取关id:"+id+"失败");
        }
        response.setStatus(200);    //
    }

    //屏蔽和关注逻辑类似（前端就不实现了）
    @GetMapping(value = "/shield/{id}")
    public void shield(@PathVariable(value = "id") int id, HttpServletRequest request, HttpServletResponse response){
        User guest = (User) request.getSession().getAttribute("user");
        int userId = guest.getId();
        if(fansService.shield(userId,id)){
            logger.info("id:"+userId+"屏蔽id:"+id+"成功");
        }else{
            logger.info("id:"+userId+"屏蔽id:"+id+"失败");
        }
        response.setStatus(200);
    }

    @GetMapping(value = "/unshield/{id}")
    public void unshield(@PathVariable(value = "id") int id, HttpServletRequest request, HttpServletResponse response){
        User guest = (User) request.getSession().getAttribute("user");
        int userId = guest.getId();
        if(fansService.unshield(userId,id)){
            logger.info("id:"+userId+"取消屏蔽id:"+id+"成功");
        }else{
            logger.info("id:"+userId+"取消屏蔽id:"+id+"失败");
        }
        response.setStatus(200);
    }

}
