package com.hyn.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hyn
 * @version 1.0.0
 * @description
 * @date 2022/11/13
 */
@Component
public class RabbitMqService {
    @Autowired
    RabbitTemplate rabbitTemplate;
    public void  sendMsgToUser(String msg){
        rabbitTemplate.convertAndSend("hynzdc.message.system","send-msg-to-hyn",msg);
    }
}
