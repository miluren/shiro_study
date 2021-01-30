package com.z.shiro_study.utils;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Mr zhang
 * @description 全局异常处理
 * @date 2020/12/8
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public String doException(Exception e) {
        if (e instanceof AuthorizationException) {
            System.out.println("捕捉到异常");
            return "/lesspermission";
        }

        return null;
    }
}
