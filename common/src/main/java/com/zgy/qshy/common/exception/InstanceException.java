/**
 * 
 */
package com.zgy.qshy.common.exception;

import com.zgy.qshy.common.support.HttpCode;

@SuppressWarnings("serial")
public class InstanceException extends BaseException {
    public InstanceException() {
        super();
    }

    public InstanceException(Throwable t) {
        super(t);
    }

    protected HttpCode getCode() {
        return HttpCode.INTERNAL_SERVER_ERROR;
    }
}
