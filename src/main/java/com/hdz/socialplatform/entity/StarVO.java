package com.hdz.socialplatform.entity;

/**
 * @author hdz
 * @description TODO
 * @create 2022年03月19日 14:52
 */
public class StarVO {


    private int id;
    private String name;
    private String avatar;      //头像
    private String profile;     //简介

    private State state;    //是否被明星关注

    public StarVO() {
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


}
