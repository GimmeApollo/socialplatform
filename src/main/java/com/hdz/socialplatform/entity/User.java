package com.hdz.socialplatform.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hdz
 * @description TODO 用户信息
 * @create 2022年02月12日 12:42
 */

public class User {

    private int id;
    private String name;
    private String password;
    private String avatar;      //头像
    private String profile;     //简介


    private int fansNum;    //粉丝数量
    private int followsNum; //关注数量

    private List<Event> events; //个人动态


    public User() {
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

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public Integer getFollowsNum() {
        return followsNum;
    }

    public void setFollowsNum(int followsNum) {
        this.followsNum = followsNum;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", profile='" + profile + '\'' +
                ", fansNum=" + fansNum +
                ", followsNum=" + followsNum +
                ", events=" + events +
                '}';
    }
}
