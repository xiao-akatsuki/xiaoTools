package com.xiaoTools.core.exception.dateException;

import com.xiaoTools.util.exceptionUtil.ExceptionUtil;

import java.io.Serial;

/**
 * [日期异常](Date exception)
 * @description: zh - 日期异常
 * @description: en - Date exception
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/18 2:46 下午
*/
public class DateException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 8247610319171014183L;

    public DateException(Throwable e) {
        super(ExceptionUtil.getMessage(e), e);
    }

    public DateException(String message) {
        super(message);
    }

    public DateException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public DateException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DateException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }
}
