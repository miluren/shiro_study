package com.z.shiro_study.controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr zhang
 */
@RestController
public class PageController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index")
    public String index1() {
        return "index1";
    }

    @GetMapping("/lesspermission")
    public String getLesspermission() {

        return "小伙子，不讲武德";
    }
    
}
