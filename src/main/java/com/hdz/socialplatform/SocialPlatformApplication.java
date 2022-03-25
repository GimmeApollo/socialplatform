package com.hdz.socialplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class SocialPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialPlatformApplication.class, args);
    }

}
