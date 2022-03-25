package com.hdz.socialplatform.entity;

import java.util.Date;
import java.util.List;

/**
 * @author hdz
 * @description 动态vo
 * @create 2022年03月23日 19:36
 */
public class PostVO {

    private int postId;         //动态id
    private int userId;         //作者id
    private String content;     //动态内容
    private String path;        //动态图片文件夹
    private List<String> fileNames;  //图片名字
    private int onlyISee;       //是否自己可见,默认0=false
    private int ifReprint;      //是否为转载，默认0=false
    private int reprintId;      //转载id,默认0=false
    private int valid;          //是否存在（可能被删除）
    private Date postTime;      //发布时间

    public PostVO() {
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOnlyISee() {
        return onlyISee;
    }

    public void setOnlyISee(int onlyISee) {
        this.onlyISee = onlyISee;
    }

    public int getIfReprint() {
        return ifReprint;
    }

    public void setIfReprint(int ifReprint) {
        this.ifReprint = ifReprint;
    }

    public int getReprintId() {
        return reprintId;
    }

    public void setReprintId(int reprintId) {
        this.reprintId = reprintId;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }
}
