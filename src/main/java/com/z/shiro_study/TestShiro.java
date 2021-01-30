package com.z.shiro_study;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

import java.util.Scanner;

/**
 * @author Mr zhang
 */
public class TestShiro {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入账号：");
        String username = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();

        // 1.创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        // 2.创建Realm
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        // 3.将realm设置给安全管理器
        securityManager.setRealm(iniRealm);
        // 4.将realm间接设置给安全工具
        SecurityUtils.setSecurityManager(securityManager);
        // 5.通过Utile工具类获得Subject对象
        Subject subject = SecurityUtils.getSubject();

        //[认证流程]
        // 1. 将账号和密码封装到token中
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 2. 通过该subject对象调用login方法进行认证申请
        boolean b = false;
        //  登录失败就会抛出异常（IncorrectCredentialsException）
        try {
            subject.login(token);
            b = true;
        } catch (IncorrectCredentialsException e) {
            b = false;
        }
        System.out.println(b?"登录成功":"登录失败");

        // 判断是否具有某种角色
        System.out.println(subject.hasRole("seller"));
        // 判断是否具有某种权限
        System.out.println(subject.isPermitted("order-add"));

    }
}
