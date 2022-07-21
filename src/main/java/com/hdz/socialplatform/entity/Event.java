package com.hdz.socialplatform.entity;

/**
 * @author hdz
 * @description 消息通知
 * @create 2022年03月12日 15:12
 */
public class Event {

    private int guestId;    //点赞人id
    private String guestName;   //点赞人名字
    private int postId;     //被点赞的动态id
    private String summary; //动态内容摘要

    public Event() {
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
