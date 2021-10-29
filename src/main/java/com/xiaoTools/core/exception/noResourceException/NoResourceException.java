package com.xiaoTools.core.exception.noResourceException;

import com.xiaoTools.core.exception.iORuntimeException.IORuntimeException;
import com.xiaoTools.util.exceptionUtil.ExceptionUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [资源文件或资源不存在异常](There is no exception in the resource file or resource)
 * @description zh - 资源文件或资源不存在异常
 * @description en - There is no exception in the resource file or resource
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-29 09:46:21
 */
public class NoResourceException extends IORuntimeException {

	private static final long serialVersionUID = -623254467603299129L;

	public NoResourceException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public NoResourceException(String message) {
		super(message);
	}

	public NoResourceException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public NoResourceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public NoResourceException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}

	@Override
	public boolean causeInstanceOf(Class<? extends Throwable> clazz) {
		final Throwable cause = this.getCause();
		return clazz.isInstance(cause);
	}

}
