package org.example.jd20250417springbookdemo.enums;

public enum ResultCodeEnum {
    UNLOGIN(-1), // unlogin 未登录状态
    SUCCESS(200), // success 返回成功 正常
    FAIL(-2); // fail 返回失败 出错

    private int code;

    ResultCodeEnum(int code) {
        this.code = code;
    }

}
