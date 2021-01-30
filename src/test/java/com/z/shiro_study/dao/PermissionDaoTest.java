package com.z.shiro_study.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.Set;

@SpringBootTest
public class PermissionDaoTest {

    private final PermissionDao permissionDao;

    @Autowired
    public PermissionDaoTest(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }


    @Test
    void queryPermissionByUsername() {
        Set<String> wangwu = permissionDao.queryPermissionByUsername("wangwu");
        Iterator<String> iterator = wangwu.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
