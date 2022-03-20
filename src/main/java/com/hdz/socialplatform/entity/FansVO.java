package com.hdz.socialplatform.entity;

/**
 * @author hdz
 * @description 粉丝VO
 * @create 2022年03月19日 14:24
 */
public class FansVO {

    private int id;
    private String name;
    private String avatar;      //头像
    private String profile;     //简介

    private Integer valid=0;    //是否关注了粉丝

    public FansVO() {
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}
