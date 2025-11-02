package com.itheima.mp.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.itheima.mp.enums.UserStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * MyBatisPlus中@TableName为指定数据库名称
 * ‘@TableId’:用来指定表中的主键字段信息
 * ’@TableField‘:用来指定表中的普通字段信息
 */
@Data
@TableName(value = "user",autoResultMap = true)
public class User {

    /**
     * 用户id
     * type = IdType.AUTO根据表中主键的字段目前自增到的最大值进行自增
     * type = IdType.INPUT手动输入：一般是自行指定
     * type = ASSIGN_ID雪花算法；生成数值字符串
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册手机号
     */
    private String phone;

    /**
     * 详细信息
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private UserInfo info;

    /**
     * 使用状态（1正常 2冻结）
     */
    private UserStatus status;

    /**
     * 账户余额
     */
    private Integer balance;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
