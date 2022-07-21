package com.hdz.socialplatform.service;

import com.hdz.socialplatform.dao.LikeMapper;
import com.hdz.socialplatform.dao.PostMapper;
import com.hdz.socialplatform.entity.LikerVO;
import com.hdz.socialplatform.entity.PostVO;
import com.hdz.socialplatform.utils.LoadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hdz
 * @description 动态服务
 * @create 2022年03月24日 15:46
 */
@Slf4j
@Service
public class PostService {

    private static final Logger logger = LogManager.getLogger(PostService.class);

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private LikeMapper likeMapper;

    //发送动态
    public void postOne(PostVO postVO){
        Integer postId = postMapper.insertPost(postVO);
    }
    //查看单条动态详情
    public PostVO selectOne(int postId){
        PostVO postVO = postMapper.selectOne(postId);
        LoadUtil.setFileName(postVO);
        setLikerVOList(postVO);
        return postVO;
    }

    //根据id查看别人动态
    public List<PostVO> selectByUserId(int userId){
        List<PostVO> list = postMapper.selectByUserId(userId);
        LoadUtil.setFileName(list);
        setLikerVOList(list);
        return list;
    }

    //查看自己的动态
    public List<PostVO> selectByMyId(int userId){
        List<PostVO> list = postMapper.selectByMyId(userId);
        LoadUtil.setFileName(list);
        setLikerVOList(list);
        return list;
    }

    //查看自己关注的人的动态，TODO：分页可以用自定义类Pager<T>实现，这里就不重复实现了
    public List<PostVO> selectByFollowerId(int followerId){
        List<PostVO> list = postMapper.selectByFollowerId(followerId);
        LoadUtil.setFileName(list);
        setLikerVOList(list);
        return list;
    }

    //设置仅自己可见
    public void setOnlyISee(int postId){
        postMapper.setOnlyISee(postId);
    }

    //取消仅自己可见
    public void cancelOnlyISee(int postId){
        postMapper.cancelOnlyISee(postId);
    }

    //删除动态
    public int invalidPost(int postId){
        return postMapper.invalidPost(postId);
    }

    //设置点赞VO
    private void setLikerVOList(PostVO postVO){
        postVO.setLikerVOs(likeMapper.likerList(postVO.getPostId()));
    }

    //设置点赞VO
    private void setLikerVOList(List<PostVO> postVOs){
        for (int i = 0; i < postVOs.size(); i++) {
            setLikerVOList(postVOs.get(i));
        }
    }


}
