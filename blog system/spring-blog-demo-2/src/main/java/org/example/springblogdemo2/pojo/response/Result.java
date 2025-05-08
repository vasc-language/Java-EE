package org.example.springblogdemo2.pojo.response;

import lombok.Data;
import org.example.springblogdemo2.enums.ResultCodeEnum;

/**
 * Created with IntelliJ IDEA.
 * Description: 对结果进行封装
 * User: 姚东名
 * Date: 2025-05-08
 * Time: 22:09
 */
@Data
public class Result {
    private ResultCodeEnum code;//业务状态码, 非Http状态码
    private String errMsg;
    private Object data;

    public static Result success(Object data){
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result fail(String errMsg){
        Result result = new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setErrMsg(errMsg);
        return result;
    }

    public static Result fail(String errMsg, Object data){
        Result result = new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setErrMsg(errMsg);
        result.setData(data);
        return result;
    }

}
