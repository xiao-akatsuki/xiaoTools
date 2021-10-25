package com.xiaoTools.core.exception.comparatorException;

import com.xiaoTools.util.exceptionUtil.ExceptionUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [比较器异常](Comparator exception)
 * @description zh - 比较器异常
 * @description en - Comparator exception
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-25 08:40:29
 */
public class ComparatorException extends RuntimeException {
	private static final long serialVersionUID = 4475602435485521971L;

	public ComparatorException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public ComparatorException(String message) {
		super(message);
	}

	public ComparatorException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public ComparatorException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ComparatorException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}
}
