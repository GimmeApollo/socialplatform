package com.hdz.socialplatform.dao;

import com.hdz.socialplatform.entity.FansVO;
import com.hdz.socialplatform.entity.LikerVO;
import com.hdz.socialplatform.entity.StarVO;
import com.hdz.socialplatform.entity.User;
import org.apache.ibatis.annotations.Mapper;
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
public interface LikeMapper {

    //列表操作
    List<FansVO> fansByPager(Map<String, Object> params);

    List<StarVO> starByPager(Map<String, Object> params);

    public int countFans(User user);

    public int countFans(int starId);

    public int countFollowers(User user);

    public int countFollowers(int followerId);

    //查看点赞人列表
    public List<LikerVO> likerList(int postId);

    //点赞系列操作
    public Integer ifLike(int likerId, int postId);

    public Integer insertLike(int likerId, int postId);

    public Integer updateLike(int likerId, int postId);

    public Integer unLike(int likerId, int postId);




}
