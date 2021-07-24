package com.xiaoTools.core.exception.convertException;

import com.xiaoTools.util.exceptionUtil.ExceptionUtil;

import java.io.Serial;

/**
 * [转换异常](Conversion exception)
 * @description: zh - 转换异常
 * @description: en - Conversion exception
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 3:45 下午
*/
public class ConvertException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 4730597402855274362L;


    /**
     * [转换异常](Conversion exception)
     * @description: zh - 转换异常
     * @description: en - Conversion exception
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 3:46 下午
     * @param e: 异常
    */
    public ConvertException(Throwable e) {
        super(ExceptionUtil.getMessage(e), e);
    }

    /**
     * [转换异常](Conversion exception)
     * @description: zh - 转换异常
     * @description: en - Conversion exception
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 3:47 下午
     * @param message: 异常信息
    */
    public ConvertException(String message) {
        super(message);
    }

    /**
     * [转换异常](Conversion exception)
     * @description: zh - 转换异常
     * @description: en - Conversion exception
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 3:52 下午
     * @param messageTemplate: 错误信息
     * @param params:
    */
    public ConvertException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    /**
     * [转换异常](Conversion exception)
     * @description: zh - 转换异常
     * @description: en - Conversion exception
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 3:53 下午
     * @param message: 错误信息
     * @param throwable: 异常问题
    */
    public ConvertException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * [转换异常](Conversion exception)
     * @description: zh - 转换异常
     * @description: en - Conversion exception
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 3:55 下午
     * @param throwable: 异常问题
     * @param messageTemplate: 异常信息模板
     * @param params: 异常参数
    */
    public ConvertException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }
}
