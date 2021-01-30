package com.z.shiro_study;

import com.z.shiro_study.dao.UserDao;
import com.z.shiro_study.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroStudyApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
        User zhangsan = userDao.queryUserByUsername("zhangsan");
        System.out.println(zhangsan);
    }

}
