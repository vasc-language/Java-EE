package org.example.jd20250417springbookdemo.model;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-19
 * Time: 16:17
 */

// import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.example.jd20250417springbookdemo.enums.ResultCodeEnum;

// 统一API响应格式
@Data
public class Result<T> {
    private ResultCodeEnum code;  // -1 未登录    200 正常  -2 出错
    private String errMsg; // 错误信息
    private T data; // 返回什么数据

    public static <T> Result success(T data) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS);
        result.setErrMsg("");
        result.setData(data);
        return result;
    }

    public static <T> Result fail(String errMsg) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setErrMsg(errMsg);
        result.setData(null);
        return result;
    }

    public static <T> Result fail(String errMsg, T data) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setErrMsg(errMsg);
        result.setData(data);
        return result;
    }

    public static <T> Result unlogin() {
        Result result = new Result();
        result.setCode(ResultCodeEnum.UNLOGIN);
        result.setErrMsg("用户未登录");
        return result;
    }
}

