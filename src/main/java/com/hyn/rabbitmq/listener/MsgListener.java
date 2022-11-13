package com.hyn.rabbitmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author hyn
 * @version 1.0.0
 * @description
 * @date 2022/11/13
 */
@Component
@Slf4j
public class MsgListener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("hynzdc.message.system.queue"),
            exchange = @Exchange("hynzdc.message.system"),
            key = {"send-msg-to-hyn"}
    ))
    public void systemMessageDirectListener(String msg){
        log.info("接受到的消息:"+msg);
        System.out.println("接受到的消息"+msg);
    }
}
