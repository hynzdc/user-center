package com.hyn;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.hyn.common.cache.service.RedisService;
import com.hyn.entity.User;
import com.hyn.rabbitmq.RabbitMqService;
import com.hyn.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserCenterApplicationTests {
    @Resource
    private UserService userService;

    @Resource
    private RabbitMqService rabbitMqService;

    @Resource
    private RedisService redisService;
    @Test
    void contextLoads() {
        String one = SecureUtil.md5("woai2014");
        System.out.println(one);
        BigDecimal a1 = new BigDecimal(3);
        BigDecimal a2 = new BigDecimal(3);
        System.out.println(a1.compareTo(a2));
    }

    @Test
    void userRegister(){
        String userPassword = "woai2014";
        String checkPassword = "woai2014";
        String userAccount = "hynzdcyaya";
        userService.userRegister(userAccount,userPassword,checkPassword);
    }
    @Test
    void testRedis(){
        redisService.setCacheObject("hynzdc","hahah",3, TimeUnit.MINUTES);
    }

    @Test
    void testRabbitMq(){
        rabbitMqService.sendMsgToUser("郝亚宁真帅爷爷爷爷");
    }
}
