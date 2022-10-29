package com.hyn;
import java.util.Date;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.hyn.entity.User;
import com.hyn.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserCenterApplicationTests {
    @Resource
    private UserService userService;
    @Test
    void contextLoads() {
        String one = SecureUtil.md5("woai2014");
        System.out.println(one);
    }

    @Test
    void userRegister(){
        String userPassword = "woai2014";
        String checkPassword = "woai2014";
        String userAccount = "hynzdcyaya";
        userService.userRegister(userAccount,userPassword,checkPassword);
    }

}
