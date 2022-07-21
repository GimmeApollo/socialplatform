package com.hdz.socialplatform.entity;

/**
 * @author hdz
 * @description 点赞人VO
 * @create 2022年03月19日 14:24
 */
public class LikerVO {

    private int id;
    private String name;
    private String avatar;      //头像

    public LikerVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }



}
