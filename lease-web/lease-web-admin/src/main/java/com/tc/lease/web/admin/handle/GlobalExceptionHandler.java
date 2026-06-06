package com.tc.lease.web.admin.handle;

import com.tc.lease.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e) {
        log.info(e.getMessage());
        return Result.fail();
    }
}
