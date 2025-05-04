package org.example.springblogdemo.exception;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-04
 * Time: 22:23
 */
@Data
public class BlogException extends RuntimeException {
    private int code;
    private String errMsg;

    public BlogException(String message, int code) {
        this.code = code;
    }

    public BlogException(String errMsg) {
        this.errMsg = errMsg;
    }
}
