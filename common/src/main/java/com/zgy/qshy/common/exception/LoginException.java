package com.zgy.qshy.common.exception;

import com.zgy.qshy.common.support.HttpCode;

@SuppressWarnings("serial")
public class LoginException extends BaseException {
	public LoginException() {
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(String message, Exception e) {
		super(message, e);
	}

	protected HttpCode getCode() {
		return HttpCode.LOGIN_FAIL;
	}
}
