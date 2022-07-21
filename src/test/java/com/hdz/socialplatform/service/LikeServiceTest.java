package com.hdz.socialplatform.service;

import com.hdz.socialplatform.SocialPlatformApplicationTests;
import com.hdz.socialplatform.dao.LikeMapper;
import com.hdz.socialplatform.entity.Event;
import com.hdz.socialplatform.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hdz
 * @description TODO
 * @create 2022年03月26日 19:20
 */
class LikeServiceTest extends SocialPlatformApplicationTests {

    @Autowired
    LikeService likeService;

    @Autowired
    UserService userService;

    @Test
    void insertEvent() {
        User user1 = userService.getUser(1);
        User user2 = userService.getUser(2);
        User user3 = userService.getUser(3);
        likeService.insertEvent(user1,19);
        likeService.insertEvent(user2,20);
        likeService.insertEvent(user3,19);
    }

    @Test
    void checkEvent() {
        List<Event> events = likeService.checkEvent(1);
    }
}