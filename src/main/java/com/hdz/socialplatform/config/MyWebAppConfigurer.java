package com.hdz.socialplatform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hdz
 * @description TODO
 * @create 2022年03月25日 0:42
 */

@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //静态资源映射
        registry.addResourceHandler("/pictures/**").addResourceLocations("file:E:/AppData/socialplatform/pictures/");
    }
}

