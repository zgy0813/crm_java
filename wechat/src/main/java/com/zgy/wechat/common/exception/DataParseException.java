package com.zgy.wechat.common.exception;

import com.zgy.wechat.common.support.HttpCode;

/**
 * @author fish
 * @version 1.0, 2019/08/20
 */
@SuppressWarnings("serial")
public class DataParseException extends BaseException {

	public DataParseException() {
	}

	public DataParseException(Throwable ex) {
		super(ex);
	}

	public DataParseException(String message) {
		super(message);
	}

	public DataParseException(String message, Throwable ex) {
		super(message, ex);
	}

	protected HttpCode getCode() {
		return HttpCode.INTERNAL_SERVER_ERROR;
	}

}
