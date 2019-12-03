/**
 * 
 */
package com.zgy.wechat.common.exception;

import com.zgy.wechat.common.support.HttpCode;

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
