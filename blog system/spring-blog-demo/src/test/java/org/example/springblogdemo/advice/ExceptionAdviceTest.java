package org.example.springblogdemo.advice;

import org.example.springblogdemo.pojo.response.Result;
import org.example.springblogdemo.enums.ResultCodeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExceptionAdviceTest {

    @InjectMocks
    private ExceptionAdvice exceptionAdvice;

    @Mock
    private HandlerMethodValidationException handlerMethodValidationException;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private FieldError fieldError;

    @Test
    public void testHandlerMethodValidationExceptionHandler() {
        // 设置模拟行为
        when(handlerMethodValidationException.getMessage()).thenReturn("参数校验错误信息");
        
        // 执行测试
        Result result = exceptionAdvice.exceptionHandler(handlerMethodValidationException);

        // 验证结果
        assertNotNull(result);
        assertEquals(ResultCodeEnum.FAIL, result.getCode());
        assertEquals("参数校验失败", result.getErrMsg());
    }

    @Test
    public void testMethodArgumentNotValidExceptionHandler() {
        // 设置模拟行为
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldError()).thenReturn(fieldError);
        when(fieldError.getDefaultMessage()).thenReturn("字段错误信息");
        when(methodArgumentNotValidException.getMessage()).thenReturn("方法参数错误信息");
        
        // 执行测试
        Result result = exceptionAdvice.exceptionHandler(methodArgumentNotValidException);

        // 验证结果
        assertNotNull(result);
        assertEquals(ResultCodeEnum.FAIL, result.getCode());
        assertEquals("字段错误信息", result.getErrMsg());
    }
    
    @Test
    public void testMethodArgumentNotValidExceptionHandlerWithNullFieldError() {
        // 设置模拟行为
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldError()).thenReturn(null);
        when(methodArgumentNotValidException.getMessage()).thenReturn("方法参数错误信息");
        
        // 执行测试
        Result result = exceptionAdvice.exceptionHandler(methodArgumentNotValidException);
        
        // 验证结果
        assertNotNull(result);
        assertEquals(ResultCodeEnum.FAIL, result.getCode());
        assertEquals("参数校验失败", result.getErrMsg());
    }
} 