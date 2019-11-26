package com.zgy.qshy.common.exception;

import com.zgy.qshy.common.support.HttpCode;

@SuppressWarnings("serial")
public class UnauthorizedException extends BaseException {
    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Exception e) {
        super(message, e);
    }

    @Override
    protected HttpCode getCode() {
        return HttpCode.UNAUTHORIZED;
    }

}
