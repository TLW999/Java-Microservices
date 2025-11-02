package com.itheima.mp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserStatus {

    NORMAL(1, "正常"),
    FREEZE(2, "冻结");

    //保存到数据库中的值
    private final Integer code;

    //在序列化的时候；使用的是这个属性值
    @JsonValue
    private final String message;
    UserStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
