package com.z.shiro_study.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Mr zhang
 */
@Data
public class User {

    private Integer uesrId;

    /**
     * 添加传递时的名字
     */
    @JsonProperty("username1")
    private String userName;
    private String userPwd;
    private String pwdSalt;
}
