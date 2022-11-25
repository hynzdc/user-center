package com.hyn.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuzg
 * @version 1.0.0
 * @description RabbitMQ配置信息
 * @date 2022/4/9
 */
@Configuration
public class RabbitmqConfig {

    /**
     * @description 消息转换器配置
     * @author wuzg 
     * @date 2022/4/9 13:50 
     * @return: org.springframework.amqp.support.converter.MessageConverter
     * @throws 
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
