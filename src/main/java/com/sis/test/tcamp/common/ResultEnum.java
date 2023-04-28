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

    SUCCESS("2000", "操作成功!"),

    FAILED("3000", "操作失败!"),

    BAD_REQUEST("4009", "输入参数错误!"),

    UNAUTHORIZED("4001", "身份认证失败!"),

    FORBIDDEN("4003", "访问被拒绝!"),

    NOT_FOUND("4004", "资源未找到!"),

    METHOD_NOT_ALLOWED("4005", "不支持的请求方法!"),

    INTERNAL_ERROR("500", "系统异常!"),

    //业务错误编码
    DECRYPT_FAILED("4000", "数据解码失败!"),
    //实名回调
    VIN_NOT_BLANK_RNR_CALL_BACK("4010", "车辆vin号不可为空！"),
    STATUS_NOT_BLANK_RNR_CALL_BACK("4011", "实名状态不可为空！"),
    STATUS_ILLEGAL_RNR_CALL_BACK("4012", "实名状态值错误！值=%s无法识别！"),
    TYPE_NOT_BLANK_RNR_CALL_BACK("4013", "实名类型不可为空！"),
    TYPE_ILLEGAL_RNR_CALL_BACK("4014", "实名类型值错误！值=%s无法识别！"),
    SIMLIST_NOT_BLANK_RNR_CALL_BACK("4015", "卡信息列表不可为空！"),
    ICCID_NOT_FOUND_RNR_CALL_BACK("4016", "卡信息列表中的iccid值=%s不存在!"),
    SWAP_VIN_NOT_FOUND_RNR_CALL_BACK("4017", "换件车辆vin=%s不存在！"),
    SIS_CONFIG_NULL("4018", "未找到该配置信息"),

    //卡信息查询
    VIN_ICCID_NOT_BLANK_RNR_QUERY("4020", "车辆vin号与iccid号不可同时为空！"),
    VIN_ICCID_NOT_FOUND_RNR_QUERY("4021", "车辆vin号与iccid号错误，未查询到卡信息！"),
    TIME_RANGE_RNR_QUERY("4022", "时间已过期，无效的查询！"),

    SIS_TCU_SWAP_PHONE_NULL("4030", "手机号不能为空"),
    SIS_TCU_SWAP_BRAND_NULL("4031", "品牌不能为空"),
    SIS_TCU_SWAP_OLD_ICCID_NULL("4032", "旧件ICCID不能为空"),
    SIS_TCU_SWAP_OLD_IMEI_NULL("4033", "旧件IMEI不能为空"),
    SIS_TCU_SWAP_OLD_SN_NULL("4034", "旧件SN不能为空"),
    SIS_TCU_SWAP_NEW_ICCID_NULL("4035", "新件ICCID不能为空"),
    SIS_TCU_SWAP_NEW_IMEI_NULL("4036", "新件IMEI不能为空"),
    SIS_TCU_SWAP_NEW_SN_NULL("4037", "新件SN不能为空"),
    SIS_TCU_SWAP_OLD_TCU_NOT_EXIST("4038", "旧件信息不存在"),
    SIS_TCU_SWAP_NEW_TCU_NOT_EXIST("4039", "新件信息不存在"),
    SIS_TCU_SWAP_NEW_TCU_HAS_ACTIVE("4040", "新件已激活,无法替换"),
    SIS_TCU_SWAP_RNR_STATUS_ERROR("4044", "车辆未实名"),
    SIS_TCU_SWAP_ICCID_ALREADY_BIND("4045", "新卡已绑定车辆"),
    SIS_TCU_SWAP_NEW_MSISDN_NULL("4046", "新件MSISDN不能为空"),
    SIS_TCU_SWAP_NEW_OPERATOR_NULL("4047", "新件运营商不能为空"),

    GHAC_CALLBACK_SUCCESS("1000", "正常结束"),

    GHAC_CALLBACK_APPCODE_FAILED("2001", "APPCODE验证失败"),

    GHAC_CALLBACK_PARAMS_FAILED("2002", "输入参数错误"),

    GHAC_CALLBACK_OTHER_FAILED("2003", "其他错误"),

    CALLBACK_PARAMS_NULL("2004", "第三方未返回实名状态"),

    GHAC_CALLBACK_FAILED("2005", "http成功，第三方响应失败"),

    WHAC_CALLBACK_SUCCESS("1", "成功"),

    WHAC_CALLBACK_FAILED("2", "失败"),

    ;

    private String code;

    private String message;

}
