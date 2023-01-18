package com.hyn.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import lombok.Data;

/**
 * 表名：tag
*/
@Data
@TableName(value = "tag")
public class Tag {
    /**
     * 主键
     */
    @TableId(value="id", type = IdType.ASSIGN_ID)
    @TableField(value = "id")
    private Long id;

    /**
     * 标签名称
     */
    @TableField(value = "tag_name")
    private String tagName;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 父标签id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 是否为父标签 0不是 父 1是


     */
    @TableField(value = "is_parent")
    private Byte isParent;

    /**
     * 创建时间

     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 逻辑删除 0 代表未删除 1删除
     */
    @TableField(value = "is_deleted")
    @TableLogic
    private Byte isDeleted;
}