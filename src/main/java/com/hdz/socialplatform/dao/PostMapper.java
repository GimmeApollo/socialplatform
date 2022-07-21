package com.hdz.socialplatform.dao;

import com.hdz.socialplatform.entity.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hdz
 * @description TODO
 * @create 2022年03月24日 15:46
 */


@Mapper
@Repository
public interface PostMapper {

    //发表单条动态
    public Integer insertPost(PostVO postVO);

    //查看单条动态
    public PostVO selectOne(int postId);

    //根据客态id查看动态
    public List<PostVO> selectByUserId(int userId);

    //根据主态id查看动态
    public List<PostVO> selectByMyId(int userId);

    //根据关注列表来查看动态
    public List<PostVO> selectByFollowerId(int followerId);

    //设置仅自己可见
    public int setOnlyISee(int postId);

    //取消仅自己可见
    public int cancelOnlyISee(int postId);

    //删除动态
    public int invalidPost(int postId);
}
