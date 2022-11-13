package com.hyn.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzg
 * @version 1.0.0
 * @description 消息推送服务类
 * @date 2022/4/9
 */
@Component
public class SendMessageService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Async
    public void sendMessageToSingleUser(SysMessageQueueDto msg) {
        rabbitTemplate.convertAndSend("allsec.system.message.direct","send-msg-to-single-user",msg);
    }

    /**
     * @description 发送消息到厂商用户
     * @author wuzg
     * @param: msg
     * @date 2022/9/27 16:51
     * @throws
     */
    @Async
    public void sendMessageToOrgUser(SysMessageQueueDto msg) {
        rabbitTemplate.convertAndSend("allsec.system.message.direct","send-msg-to-org-user",msg);
    }

    /**
     * @description 发送消息到所有用户
     * @author wuzg
     * @param: msg
     * @date 2022/5/7 13:42
     * @throws
     */
    @Async
    public void sendMessageToAllUser(SysMessageQueueDto msg) {
        rabbitTemplate.convertAndSend("allsec.system.message.direct","send-msg-to-all-user",msg);
    }
}
