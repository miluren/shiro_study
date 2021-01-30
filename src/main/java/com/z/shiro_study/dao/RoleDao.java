package com.z.shiro_study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Mr zhang
 */
@Repository
@Mapper
public interface RoleDao {

    /**
     * 通过用户名获取角色列表
     * @param username 用户名
     * @return 角色列表
     */
    Set<String> queryRoleNamesByUsername(String username);

}
