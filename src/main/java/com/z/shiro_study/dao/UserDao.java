package com.z.shiro_study.dao;

import com.z.shiro_study.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Mr zhang
 */
@Mapper
@Repository
public interface UserDao {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    User queryUserByUsername(String username);

}