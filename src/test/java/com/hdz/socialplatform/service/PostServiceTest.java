package com.hdz.socialplatform.service;

import com.hdz.socialplatform.SocialPlatformApplicationTests;
import com.hdz.socialplatform.entity.PostVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hdz
 * @description TODO
 * @create 2022年03月26日 13:11
 */
class PostServiceTest extends SocialPlatformApplicationTests {

    @Autowired
    PostService postService;

    @Test
    void postOne() {
    }

    @Test
    void selectOne() {
        PostVO postVO = postService.selectOne(19);
    }

    @Test
    void selectByUserId() {
    }

    @Test
    void selectByMyId() {
    }

    @Test
    void selectByFollowerId() {
    }

    @Test
    void setOnlyISee() {
    }

    @Test
    void cancelOnlyISee() {
    }

    @Test
    void invalidPost() {
    }
}