package com.z.shiro_study.service;


/**
 * @author Mr zhang
 */
public interface UserService {

    /**
     * 检查登录的情况
     * @param username 用户名
     * @param password 密码
     */
    void checkLogin(String username, String password);
}
