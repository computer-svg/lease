package com.tc.lease.common.exception;

import com.tc.lease.common.result.ResultCodeEnum;

public class LeaseException extends RuntimeException {
    private Integer code;

    private static final long serialVersionUID = 1L;

    public LeaseException(String message) {
        super(message);
    }
    public LeaseException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
    }
}
