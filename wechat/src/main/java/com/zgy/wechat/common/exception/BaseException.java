/**
 * 
 */
package com.zgy.wechat.common.exception;

import com.zgy.wechat.common.support.HttpCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;

/**
 *
 * @author fish
 * @version 1.0, 2019/08/20
 */
@SuppressWarnings("serial")
public abstract class BaseException extends RuntimeException {
	public BaseException() {
	}

	public BaseException(Throwable ex) {
		super(ex);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, Throwable ex) {
		super(message, ex);
	}

	public void handler(ModelMap modelMap) {
		modelMap.put("code", getCode().value());
		if (StringUtils.isNotEmpty(getMessage())) {
			modelMap.put("msg", getMessage());
		} else {
			modelMap.put("msg", getCode().msg());
		}
		modelMap.put("timestamp", System.currentTimeMillis());
	}

	protected abstract HttpCode getCode();
}
