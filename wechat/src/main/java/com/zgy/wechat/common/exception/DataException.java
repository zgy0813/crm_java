package com.zgy.wechat.common.exception;

import com.zgy.wechat.common.support.HttpCode;

/**
 * 数据异常
 *
 * @author fish
 * @version 1.0, 2019/08/20
 */
@SuppressWarnings("serial")
public class DataException extends BaseException {
    public DataException() {
    }

    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable throwable) {
        super(message, throwable);
    }

    protected HttpCode getCode() {
        return HttpCode.NOT_ACCEPTABLE;
    }
}
