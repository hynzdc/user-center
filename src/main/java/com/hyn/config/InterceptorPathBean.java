package com.hyn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hyn
 * @version 1.0.0
 * @description
 * @date 2023/1/16
 */
@Data
@Component
@ConfigurationProperties(prefix = "interceptorconfig.path")
public class InterceptorPathBean {
    /**
     * @description 需要拦截的路径
     */
    private List<String> include;
}
