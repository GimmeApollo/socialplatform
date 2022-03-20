package com.hdz.socialplatform.dao;

import com.hdz.socialplatform.entity.FansVO;
import com.hdz.socialplatform.entity.StarVO;
import com.hdz.socialplatform.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author hdz
 * @description
 * @create 2022年03月13日 20:24
 */
@Mapper
@Repository
public interface FansMapper {

    //列表操作
    List<FansVO> fansByPager(Map<String,Object> params);

    List<StarVO> starByPager(Map<String,Object> params);

    public int countFans(User user);

    public int countFans(int starId);

    public int countFollowers(User user);

    public int countFollowers(int followerId);

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
