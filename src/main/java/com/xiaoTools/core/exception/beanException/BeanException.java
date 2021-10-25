package com.xiaoTools.core.exception.beanException;

import com.xiaoTools.util.exceptionUtil.ExceptionUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [Bean异常](Bean exception)
 * @description zh - Bean异常
 * @description en - Bean exception
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-25 18:31:44
 */
public class BeanException extends RuntimeException {

	private static final long serialVersionUID = -8096998667745023423L;

	public BeanException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public BeanException(String message) {
		super(message);
	}

	public BeanException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public BeanException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public BeanException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}

}
