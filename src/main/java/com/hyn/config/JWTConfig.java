package com.hyn.config;


import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuzg
 * @version 1.0.0
 * @description JWT配置类
 * @date 2022/1/18
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JWTConfig {
    /**
     * 密钥KEY
     */
    private String secret;
    /**
     * TokenKey
     */
    private String tokenHeader;
    /**
     * Token前缀字符
     */
    private String tokenPrefix;
    /**
     * 过期时间
     */
    private Integer expiration;
    /**
     * 不需要认证的接口
     */
    private List<String> ignoreUrls = new ArrayList<>();


    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration * 1000;
    }

}