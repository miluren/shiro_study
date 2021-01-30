package com.z.shiro_study.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr zhang
 * @description 顾客控制器
 * @date 2020/12/8
 */
@RequestMapping("/customer")
@RestController
public class CustomerController {

    /**
     *  \@RequiresRoles("admin") 角色验证
     * @return string
     */
    @RequestMapping("/getList")
    @RequiresPermissions("sysfind")
    public String getList() {
        System.out.println("获得顾客列表");
        return "获取顾客列表成功";
    }
}
