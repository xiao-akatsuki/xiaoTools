package com.xiaoTools.core.exception.cLoneRuntimeException;

import com.xiaoTools.util.exceptionUtil.ExceptionUtil;

import java.io.Serial;

/**
 * [克隆异常](Clone exception)
 * @description: zh - 克隆异常
 * @description: en - Clone exception
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/19 9:56 上午
*/
public class CloneRuntimeException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 6774837422188798989L;

    public CloneRuntimeException(Throwable e) {
        super(ExceptionUtil.getMessage(e), e);
    }

    public CloneRuntimeException(String message) {
        super(message);
    }

    public CloneRuntimeException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public CloneRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CloneRuntimeException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }
}
