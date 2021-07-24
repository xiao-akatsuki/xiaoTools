package com.xiaoTools.core.exception.utilException;

import com.xiaoTools.util.exceptionUtil.ExceptionUtil;

import java.io.Serial;

/**
 * [工具类异常](Tool class exception)
 * @description: zh - 工具类异常
 * @description: en - Tool class exception
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/16 10:26 上午
*/
public class UtilException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable e) {
        super(ExceptionUtil.getMessage(e), e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UtilException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }


}
