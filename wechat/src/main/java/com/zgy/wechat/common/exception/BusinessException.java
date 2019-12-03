package com.zgy.wechat.common.exception;

import com.zgy.wechat.common.support.HttpCode;

/**
 * @author fish
 * @version 1.0, 2019/08/20
 */
@SuppressWarnings("serial")
public class BusinessException extends BaseException {
	public BusinessException() {
	}

	public BusinessException(Throwable ex) {
		super(ex);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable ex) {
		super(message, ex);
	}

	protected HttpCode getCode() {
		return HttpCode.CONFLICT;
	}
}