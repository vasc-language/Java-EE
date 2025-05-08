package org.example.springblogdemo2.common.exception;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-08
 * Time: 22:03
 */
@Data
public class BlogException extends RuntimeException {
    private int code;
    private String errMsg;
}
