package org.example.springblogdemo2.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum ResultCodeEnum {
    SUCCESS(200),
    FAIL(-1);  //实际开发中, 需要分的更细

    @Setter
    @Getter
    private int code;
}