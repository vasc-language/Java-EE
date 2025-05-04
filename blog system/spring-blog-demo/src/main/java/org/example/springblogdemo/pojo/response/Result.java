package org.example.springblogdemo.pojo.response;

import lombok.Data;
import org.example.springblogdemo.enums.ResultCodeEnum;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-04
 * Time: 21:50
 */
@Data
public class Result {
    // 业务状态码、错误信息、返回数据
    private ResultCodeEnum code;
    private String errMsg;
    private Object data;

    /**
     * 成功
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 失败
     */
    public static Result fail(String errMsg) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setErrMsg(errMsg);
        return result;
    }

    public static Result fail(String errMsg, Object data) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setErrMsg(errMsg);
        result.setData(data);
        return result;
    }
}
