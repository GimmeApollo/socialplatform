package com.hdz.socialplatform.entity;

import lombok.Data;

/**
 * @author hdz
 * @description TODO
 * @create 2022年02月12日 12:42
 */

@Data
public class User {

    private Long id;
    private String name;

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
