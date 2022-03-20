package com.hdz.socialplatform.service;

import com.hdz.socialplatform.SocialPlatformApplicationTests;
import com.hdz.socialplatform.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hdz
 * @description TODO
 * @create 2022年03月19日 20:09
 */
class FansServiceTest extends SocialPlatformApplicationTests {

    @Autowired
    FansService fansService;

    @Test
    void fansByPager() {

        User user = new User();
        user.setId(1);
        fansService.fansByPager(1,1,100);
        fansService.starByPager(1,1,100);
    }

    @Test
    void countFans() {
    }

    @Test
    void countFollowers() {
    }
}