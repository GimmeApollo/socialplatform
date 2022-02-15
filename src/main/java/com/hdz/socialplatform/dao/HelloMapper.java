package com.hdz.socialplatform.dao;

import com.hdz.socialplatform.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hdz
 * @description TODO
 * @create 2022年02月12日 12:39
 */

@Mapper
@Repository
public interface HelloMapper {

    @Select("SELECT * FROM vip_user where id=#{id};")
    User getHelloById(Long id);

    List<User> getHelloByIdUp(Long id);
}
