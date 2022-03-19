package com.hdz.socialplatform.dao;

import com.hdz.socialplatform.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hdz
 * @description TODO
 * @create 2022年03月12日 15:39
 */


@Mapper
@Repository
public interface UserMapper {

    User getUser(String name, String password);

    User getUserById(int id);

}
