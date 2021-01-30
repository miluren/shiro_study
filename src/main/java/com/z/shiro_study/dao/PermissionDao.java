package com.z.shiro_study.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Mr zhang
 */
@Repository
@Mapper
public interface PermissionDao {

    /**
     * 通过用户名查询权限列表
     * @param username 用户名
     * @return 权限列表
     */
    Set<String> queryPermissionByUsername(String username);

}
