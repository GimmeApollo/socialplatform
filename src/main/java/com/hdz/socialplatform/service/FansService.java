package com.hdz.socialplatform.service;

import com.hdz.socialplatform.controller.FansController;
import com.hdz.socialplatform.dao.FansMapper;
import com.hdz.socialplatform.dao.UserMapper;
import com.hdz.socialplatform.entity.FansVO;
import com.hdz.socialplatform.entity.StarVO;
import com.hdz.socialplatform.entity.User;
import com.hdz.socialplatform.utils.Pager;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hdz
 * @description TODO 关注关系服务
 * @create 2022年03月13日 20:23
 */
@Slf4j
@Service
public class FansService {

    private static final Logger logger = LogManager.getLogger(FansService.class);

    @Autowired
    private FansMapper fansMapper;

    @Autowired
    private UserMapper userMapper;

    public Pager<FansVO> fansByPager(int starId, int page ,int size){
        Map<String,Object> params=new HashMap<>();
        params.put("offset",(page-1)*size);
        params.put("limit",size);
        params.put("starId",starId);
        List<FansVO> list = fansMapper.fansByPager(params);
        Pager<FansVO> pager = new Pager<>();
        pager.setPage(page);
        pager.setSize(size);
        pager.setList(list);
        pager.setTotal(fansMapper.countFans(starId));
        return pager;
    }

    public Pager<StarVO> starByPager(int followerId, int page, int size){
        Map<String,Object> params=new HashMap<>();
        params.put("offset",(page-1)*size);
        params.put("limit",size);
        params.put("followerId",followerId);
        List<StarVO> list=fansMapper.starByPager(params);
        Pager<StarVO> pager = new Pager<>();
        pager.setPage(page);
        pager.setSize(size);
        pager.setList(list);
        pager.setTotal(fansMapper.countFollowers(followerId));
        return pager;
    }

    //统计粉丝数量
    public int countFans(User user) {
        return fansMapper.countFans(user);
    }

    //统计已关注数量
    public int countFollowers(User user) {
        return fansMapper.countFollowers(user);
    }

    //查找用户信息
    public User getUser(int id) {
        return userMapper.getUserById(id);
    }

    //黑名单判断自己guestId在不在masterId黑名单里，等于1表示存在记录
    public boolean inWhite(int guestId, int masterId) {
        Integer valid = fansMapper.inWhiteById(guestId, masterId);
        return valid == null || valid != 1;
    }

    //userId去屏蔽blackId
    public boolean shield(int userId, int blackId) {
        Integer valid = fansMapper.inWhiteById(blackId, userId);
        if (valid == null) {
            return 1 == fansMapper.insertShield(userId, blackId);
        } else {
            return 1 == fansMapper.updateShield(userId, blackId);
        }
    }

    //取消屏蔽
    public boolean unshield(int userId, int blackId) {
        return 1 == fansMapper.unShield(userId, blackId);
    }

    //查询关注关系
    public boolean ifFollow(int starId, int followerId) {
        Integer valid = fansMapper.ifFollow(starId, followerId);
        return valid != null && valid == 1;
    }

    //关注（有记录则修改valid值，没记录则插入记录）
    public boolean follow(int starId, int followerId) {
        Integer valid = fansMapper.ifFollow(starId, followerId);
        if (valid == null) {
            return 1 == fansMapper.insertFollow(starId, followerId);
        } else {
            return 1 == fansMapper.updateFollow(starId, followerId);
        }
    }

    //取消关注
    public boolean unfollow(int starId, int followerId) {
        return 1 == fansMapper.unFollow(starId, followerId);
    }

}
