package com.hyn.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuzg
 * @version 1.0.0
 * @description 系统消息队列参数对象模型
 * @date 2022/4/9
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysMessageQueueDto {
    /**用户ID**/
    private Long userId;
    /**厂商ID**/
    private Long orgId;
    /**触发消息事件**/
    private String event;
    /**值，多个以逗号分隔**/
    private String values;
    /**对象ID**/
    private Object objectId;
}
