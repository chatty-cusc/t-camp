package com.sis.test.tcamp.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author chatty
 * 返回枚举类型
 * @create 2023-04-28 15:53
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResultEnum {

    SUCCESS("200", "操作成功!"),

    FAILED("300", "操作失败!"),

    BAD_REQUEST("4009", "输入参数错误!"),

    UNAUTHORIZED("4001", "身份认证失败!"),

    FORBIDDEN("4003", "访问被拒绝!"),

    NOT_FOUND("4004", "资源未找到!"),

    METHOD_NOT_ALLOWED("4005", "不支持的请求方法!"),

    INTERNAL_ERROR("500", "系统异常!"),

    ;

    private String code;

    private String message;

}
