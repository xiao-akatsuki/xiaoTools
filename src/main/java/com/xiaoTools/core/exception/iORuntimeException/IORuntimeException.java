package com.xiaoTools.core.exception.iORuntimeException;

import com.xiaoTools.util.exceptionUtil.ExceptionUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [IO运行时异常，常用于对IOException的包装](IO runtime exception, which is often used to wrap IOException)
 * @description zh - IO运行时异常，常用于对IOException的包装
 * @description en - IO runtime exception, which is often used to wrap IOException
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-22 12:39:51
 */
public class IORuntimeException extends RuntimeException {

	private static final long serialVersionUID = 8247610319171014183L;

	public IORuntimeException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public IORuntimeException(String message) {
		super(message);
	}

	public IORuntimeException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public IORuntimeException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public IORuntimeException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}

	public boolean causeInstanceOf(Class<? extends Throwable> clazz) {
		final Throwable cause = this.getCause();
		return null != clazz && clazz.isInstance(cause);
	}

}
