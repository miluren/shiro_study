package com.z.shiro_study.service.serviceImpl;

import com.z.shiro_study.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * @author Mr zhang
 */
@Service
public class UserServiceImpl implements UserService {


    @Override
    public void checkLogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);

    }
}
