package com.xiaoTools.util.regularUtil.method;

import java.io.Serializable;

/**
 * [只有一个参数的函数对象](Function object with only one argument)
 * @description: zh - 只有一个参数的函数对象
 * @description: en - Function object with only one argument
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/22 11:03 上午
*/
@FunctionalInterface
public interface Func1<P, R> extends Serializable {

    /**
     * [执行函数](Executive function)
     * @description: zh - 执行函数
     * @description: en - Executive function
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 10:55 上午
     * @param parameter: 参数
     * @throws Exception
     * @return R
    */
    R call(P parameter) throws Exception;

    /**
     * [执行函数，异常包装为RuntimeException](Execute the function and wrap the exception as runtimeException)
     * @description: zh - 执行函数，异常包装为RuntimeException
     * @description: en - Execute the function and wrap the exception as runtimeException
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 11:02 上午
     * @param parameter: 参数
     * @return R
    */
    default R callWithRuntimeException(P parameter){
        try {
            return call(parameter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
