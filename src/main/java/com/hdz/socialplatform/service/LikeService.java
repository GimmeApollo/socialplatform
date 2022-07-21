package com.hdz.socialplatform.service;

import com.hdz.socialplatform.dao.FansMapper;
import com.hdz.socialplatform.dao.LikeMapper;
import com.hdz.socialplatform.dao.PostMapper;
import com.hdz.socialplatform.dao.UserMapper;
import com.hdz.socialplatform.entity.*;
import com.hdz.socialplatform.utils.Pager;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hdz
 * @description 点赞服务
 * @create 2022年03月13日 20:23
 */
@Slf4j
@Service
public class LikeService {

    private static final Logger logger = LogManager.getLogger(LikeService.class);


    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private PostMapper postMapper;

    @Resource
    private RedisTemplate<String, Event> eventRedisTemplate;

    //插入点赞通知(TODO：要查数据库获得postVO.content还是不太理想,未来可以redis缓存postVO)
    public void insertEvent(User guest, int postId) {
        PostVO postVO = postMapper.selectOne(postId);
        String key = "socialplatform-Event-"+postVO.getUserId();    //加上主人编号
        Event event = new Event();
        event.setPostId(postId);
        event.setGuestId(guest.getId());
        event.setGuestName(guest.getName());
        String content = postVO.getContent();
        String summary = content.substring(0, Math.min(content.length(), 5));
        event.setSummary(summary);
        eventRedisTemplate.opsForList().rightPush(key,event);
    }

    //清空点赞通知
    public List<Event> checkEvent(int userId){
        String key = "socialplatform-Event-"+userId;
        List<Event> list = eventRedisTemplate.opsForList().range(key,0,-1);
        if(list==null){
            return new ArrayList<>();
        }else {
            eventRedisTemplate.opsForList().trim(key,list.size(),-1);
            return list;
        }
    }

    //点赞（有记录则修改valid值，没记录则插入记录）
    public boolean like(int likerId, int postId) {
        Integer valid = likeMapper.ifLike(likerId, postId);
        if (valid == null) {
            return 1 == likeMapper.insertLike(likerId, postId);
        } else {
            return 1 == likeMapper.updateLike(likerId, postId);
        }
    }

    //取消点赞
    public boolean unLike(int likerId, int postId) {
        return 1 == likeMapper.unLike(likerId, postId);
    }

    //    public Integer ifLike(int likerId, int postId);
    //
    //    public Integer insertLike(int likerId, int postId);
    //
    //    public Integer updateLike(int likerId, int postId);
    //
    //    public Integer unLike(int likerId, int postId);

}
