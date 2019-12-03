/**
 * 
 */
package com.zgy.wechat.common.exception;

import com.zgy.wechat.common.support.HttpCode;

/**
 *
 * @author fish
 * @version 1.0, 2019/08/20
 */
@SuppressWarnings("serial")
public class IllegalParameterException extends BaseException {
	public IllegalParameterException() {
	}

	public IllegalParameterException(Throwable ex) {
		super(ex);
	}

	public IllegalParameterException(String message) {
		super(message);
	}

	public IllegalParameterException(String message, Throwable ex) {
		super(message, ex);
	}

	protected HttpCode getCode() {
		return HttpCode.BAD_REQUEST;
	}
}
