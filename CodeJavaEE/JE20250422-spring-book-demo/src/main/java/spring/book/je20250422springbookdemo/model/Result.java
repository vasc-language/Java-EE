package spring.book.je20250422springbookdemo.model;

import lombok.Data;
import spring.book.je20250422springbookdemo.enums.ResultCodeEnum;

/**
 * Created with IntelliJ IDEA.
 * Description: 统一 API 响应格式
 * User: 姚东名
 * Date: 2025-04-22
 * Time: 21:51
 */
@Data
public class Result<T> {
    private ResultCodeEnum code; // -1 未登录 200 正常成功 -2 出错
    private String errMsg; // 报错信息
    private T data; // 响应后返回什么样的数据

    // 成功的结果
    public static <T> Result success(T data) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.SUCCESS);
        result.setErrMsg("");
        result.setData(data);
        return result;
    }

    // 失败的结果
    public static <T> Result fail(String errMsg) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setErrMsg(errMsg);
        result.setData(null);
        return result;
    }

    // 失败的结果2
    public static <T> Result fail(String errMsg, T data) {
        Result result = new Result();
        result.setCode(ResultCodeEnum.FAIL);
        result.setErrMsg(errMsg);
        result.setData(data);
        return result;
    }

    // 未登录状态
    public static <T> Result unLogin() {
        Result result = new Result();
        result.setCode(ResultCodeEnum.UNLOGIN);
        result.setErrMsg("用户未登录");
        return result;
    }
}
