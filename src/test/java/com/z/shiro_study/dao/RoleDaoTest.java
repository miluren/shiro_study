package com.z.shiro_study.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;


@SpringBootTest
public class RoleDaoTest {

    private final RoleDao roleDao;

    @Autowired
    public RoleDaoTest(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Test
    void queryRoleNamesByUsername() {
        Set<String> zhangsan = roleDao.queryRoleNamesByUsername("zhangsan");
        System.out.println(zhangsan);
    }
}
