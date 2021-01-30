package com.z.shiro_study.controller;

import com.z.shiro_study.entity.User;
import com.z.shiro_study.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author Mr zhang
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        System.out.println("登录");
        try {
            userService.checkLogin(username, password);
            System.out.println("登录成功");
            return "login";
        } catch (Exception e) {
            System.out.println("登录失败");
            e.printStackTrace();
            return "index";
        }
    }

    @PostMapping("/login.html")
    public String login() {
        System.out.println("未登录");
        return "no login";
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        System.out.println("--注册--");
        System.out.println(user.toString());

        // (0-9000)之间的 盐
        int salt = new Random().nextInt(9000);
        System.out.println("salt " + salt);

        // 加密 密码
        Md5Hash hash = new Md5Hash(user.getUserPwd());
        System.out.println("--> "+ hash);

        // 加盐加密 密码
        Md5Hash hash1 = new Md5Hash(user.getUserPwd(), salt+"");
        System.out.println("salt--> "+ hash1);

        // 加盐加密 多次hash
        Md5Hash hash2 = new Md5Hash(user.getUserPwd(), salt+"", 3);
        System.out.println("salt3--> "+ hash2);


        return user;
    }

}
