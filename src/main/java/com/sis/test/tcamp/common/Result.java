package com.sis.test.tcamp.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chatty
 * @create 2023-04-28 15:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    /**
     * 返回状态码
     */
    public   String code;

    /**
     *返回结果消息  成功为SUCCESS 失败为FAILED
     */
    public  String msg;

    /**
     * 结果集
     */
    public T data;

    public Result(String code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public static Result getResult(ResultEnum resultEnum){
        return new Result(resultEnum.getCode(), resultEnum.getMessage());
    }

    public static <T> Result<T> getResult(ResultEnum resultEnum, T data){
        return new Result(resultEnum.getCode(), resultEnum.getMessage(), data);
    }

    public static <T> Result<T> success(T data){
        return getResult(ResultEnum.SUCCESS, data);
    }

    public static <T> Result<T> success(){
        return getResult(ResultEnum.SUCCESS);
    }

    public static <T> Result<T> failed(ResultEnum resultEnum){
        return getResult(resultEnum);
    }

    public static <T> Result<T> failed(String code, String message){
        return new Result(code, message);
    }

    public static <T> Result<T> failed(String code, String message, T data){
        return new Result(code, message, data);
    }

    public static <T> Result<T> exception(ResultEnum resultEnum){
        return getResult(resultEnum);
    }

    public static <T> Result<T> exception(String code, String message){
        return new Result(code, message);
    }

    public static <T> Result<T> exception(String message){
        return new Result(ResultEnum.INTERNAL_ERROR.getCode(), message);
    }
    public static <T> Result<T> exception(){
        return getResult(ResultEnum.INTERNAL_ERROR);
    }
}
