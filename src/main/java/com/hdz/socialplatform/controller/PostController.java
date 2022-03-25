package com.hdz.socialplatform.controller;

import com.hdz.socialplatform.entity.PostVO;
import com.hdz.socialplatform.entity.User;
import com.hdz.socialplatform.service.FansService;
import com.hdz.socialplatform.service.LoginService;
import com.hdz.socialplatform.service.PostService;
import com.hdz.socialplatform.utils.LoadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author hdz
 * @description 动态相关
 * @create 2022年03月23日 15:32
 */

@Slf4j
@Controller
@RequestMapping("/post")
public class PostController {

    private static final Logger logger = LogManager.getLogger(PostController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private FansService fansService;

    @Autowired
    private PostService postService;

    //发送动态
    @GetMapping(value = "/posting")
    public ModelAndView posting(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/page/mypost");
        return mv;
    }

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





}
