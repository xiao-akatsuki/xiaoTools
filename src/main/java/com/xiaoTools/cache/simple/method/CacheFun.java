package com.xiaoTools.cache.simple.method;

import java.io.Serializable;

/**
 * [无参数的函数对象](Function object without parameters)
 * @description: zh - 无参数的函数对象
 * @description: en - Function object without parameters
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/13 7:19 下午
*/
@FunctionalInterface
public interface CacheFun<R> extends Serializable {
    /**
     * [执行函数](Executive function)
     * @description: zh - 执行函数
     * @description: en - Executive function
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/13 7:21 下午
     * @throws Exception 异常
     * @return R
    */
    R call() throws Exception;

    /**
     * [执行函数，异常包装为RuntimeException](Execute the function and wrap the exception as RuntimeException)
     * @description: zh - 执行函数，异常包装为RuntimeException
     * @description: en - Execute the function and wrap the exception as RuntimeException
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/13 7:22 下午
     * @return R
    */
    default R callWithRuntimeException(){
        try {
            return call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
