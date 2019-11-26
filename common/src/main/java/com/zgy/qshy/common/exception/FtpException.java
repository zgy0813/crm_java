package com.zgy.qshy.common.exception;

import com.zgy.qshy.common.support.HttpCode;

/**
 * FTP异常
 *
 * @author fish
 * @version 1.0, 2019/08/20
 */
@SuppressWarnings("serial")
public class FtpException extends BaseException {
    public FtpException() {
    }

    public FtpException(String message) {
        super(message);
    }

    public FtpException(String message, Throwable throwable) {
        super(message, throwable);
    }

    protected HttpCode getCode() {
        return HttpCode.INTERNAL_SERVER_ERROR;
    }
}
