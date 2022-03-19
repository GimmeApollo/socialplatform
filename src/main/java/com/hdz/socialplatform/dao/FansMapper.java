package com.hdz.socialplatform.dao;

import com.hdz.socialplatform.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hdz
 * @description
 * @create 2022年03月13日 20:24
 */
@Mapper
@Repository
public interface FansMapper {

    public int countFans(User user);

    public int countFollowers(User user);

    //屏蔽系列操作
    public Integer inWhiteById(int guestId, int masterId);  //返回null或0表示在白名单中

    public Integer insertShield(int userId, int blackId);

    public Integer updateShield(int userId, int blackId);

    public Integer unShield(int userId, int blackId);

    //关注系列操作
    public Integer ifFollow(int starId,int followerId);

    public Integer insertFollow(int starId,int followerId);

    public Integer updateFollow(int starId,int followerId);

    public Integer unFollow(int starId,int followerId);
}
