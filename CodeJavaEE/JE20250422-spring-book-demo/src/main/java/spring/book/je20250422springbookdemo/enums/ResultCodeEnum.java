package spring.book.je20250422springbookdemo.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-22
 * Time: 21:47
 */
public enum ResultCodeEnum {
    UNLOGIN(-1), // 未登录状态码
    SUCCESS(200), // 成功状态码
    FAIL(-2); // 失败状态码

    private Integer code;

    ResultCodeEnum(Integer code) {
        this.code = code;
    }
}
