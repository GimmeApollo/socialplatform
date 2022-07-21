package com.hdz.socialplatform.controller;

import com.hdz.socialplatform.entity.PostVO;
import com.hdz.socialplatform.entity.User;
import com.hdz.socialplatform.service.LikeService;
import com.hdz.socialplatform.service.PostService;
import com.hdz.socialplatform.utils.LoadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author hdz
 * @description 动态相关controller
 * @create 2022年03月23日 15:32
 */

@Slf4j
@Controller
@RequestMapping("/post")
public class PostController {

    private static final Logger logger = LogManager.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    @Autowired
    private LikeService likeService;

    //发送动态页
    @GetMapping(value = "/posting")
    public ModelAndView posting(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/page/mypost");
        return mv;
    }

    //转发动态页
    @GetMapping(value = "/reposting/{postId}")
    public ModelAndView reposting(@PathVariable(value = "postId") int postId,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/page/mypost");
        PostVO postVO = postService.selectOne(postId);
        //todo:涉及转载的可以在前端使用reprintId再去查表显示，后面也可以考虑可以在PostVO中嵌套一个PostVO来表示转载的动态；
        // 嵌套的话sql使用Resultmap+association实现；
        mv.addObject("postVO",postVO);  //被转发的动态
        return mv;
    }

    //发送动态
    @PostMapping(value = "/upload")
    public ModelAndView upload(HttpServletRequest request) {
        try {
            PostVO postVO = LoadUtil.uploadFiles(request);
            postService.postOne(postVO);
        } catch (Exception e) {
            logger.error(e);
        }
        return new ModelAndView("redirect:/user/home");
    }

    //查看stars的动态（包括自己）
    @GetMapping(value = "/starposts")
    public ModelAndView starPosts(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/page/starPosts");
        User user = (User) request.getSession().getAttribute("user");
        //自带过滤被屏蔽、隐藏的动态，加上自己的动态
        List<PostVO> starsPostVOs = postService.selectByFollowerId(user.getId());
        mv.addObject("starsPostVOs",starsPostVOs);
        return mv;
    }

    //查看单条动态详情
    @GetMapping(value = "/viewdetail/{postId}")
    public ModelAndView viewdetail(@PathVariable(value = "postId") int postId){
        ModelAndView mv = new ModelAndView("/page/postDetail");
        PostVO postVO = postService.selectOne(postId);
        //todo:涉及转载的可以在前端使用reprintId再去查表显示，后面也可以考虑可以在PostVO中嵌套一个PostVO来表示转载的动态；
        // 嵌套的话sql使用Resultmap+association实现；
        mv.addObject("postVO",postVO);
        //加上点赞人列表！！！

        return mv;
    }

    //设置仅自己可见
    @GetMapping(value = "/onlyIsee/{postId}")
    public void onlyIsee(@PathVariable(value = "postId") int postId, HttpServletResponse response){
        postService.setOnlyISee(postId);
        response.setStatus(200);    //暂时这么设置吧
    }

    //取消仅自己可见
    @GetMapping(value = "/cancelonlyIsee/{postId}")
    public void cancelOnlyISee(@PathVariable(value = "postId") int postId, HttpServletResponse response){
        postService.cancelOnlyISee(postId);
        response.setStatus(200);    //暂时这么设置吧
    }

    //删除动态
    @GetMapping(value = "/deletePost/{postId}")
    public void deletePost(@PathVariable(value = "postId") int postId, HttpServletResponse response){
        postService.invalidPost(postId);
        response.setStatus(200);    //暂时这么设置吧
    }

    //点赞
    @GetMapping(value = "/like/{postId}")
    public void like(@PathVariable(value = "postId") int postId, HttpServletRequest request, HttpServletResponse response){
        User guest = (User) request.getSession().getAttribute("user");

        int likerId = guest.getId();

        if( likeService.like(likerId, postId)){
            likeService.insertEvent(guest, postId);
            logger.info("id:"+likerId+"点赞id:"+postId+"成功");
        }else{
            logger.error("id:"+likerId+"点赞id:"+postId+"失败");
        }
        response.setStatus(200);    //暂时这么设置吧
    }

    //取消点赞
    @GetMapping(value = "/unlike/{postId}")
    public void unlike(@PathVariable(value = "postId") int postId, HttpServletRequest request, HttpServletResponse response){
        User guest = (User) request.getSession().getAttribute("user");

        int likerId = guest.getId();
        if( likeService.unLike(likerId, postId)){
            logger.info("id:"+likerId+"取消id:"+postId+"点赞成功");
        }else{
            logger.error("id:"+likerId+"取消id:"+postId+"点赞失败");
        }
        response.setStatus(200);    //
    }

    //点赞人列表 TODO：可以用Pager<T>来做分页的

}
