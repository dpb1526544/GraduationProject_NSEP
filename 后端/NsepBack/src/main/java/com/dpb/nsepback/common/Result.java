package com.dpb.nsepback.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    // 状态数
    private String code;
    // 状态信息
    private String msg;
    // 返回数据
    private Object data;

    // 默认成功
    public static Result success() {
        return new Result(Constants.CODE_200, "", null);
    }

    // 成功并携带数据返回
    public static Result success(Object data) {
        return new Result(Constants.CODE_200, "", data);
    }

    // 错误并返回错误代号、错误信息和错误数据
    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }

    // 系统错误
    public static Result error() {
        return new Result(Constants.CODE_500, "系统错误", null);
    }

    // 成功并返回状态信息和数据
    public static Result success(String msg,Object data) {
        return new Result(Constants.CODE_200, msg, data);
    }

    // 自定义错误
    public static Result error(String msg) {
        return new Result(Constants.CODE_500, msg, null);
    }
}
