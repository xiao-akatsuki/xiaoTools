package com.xiaoTools.core.regular.method;

import java.io.Serializable;

/**
 * [一个函数接口代表一个一个函数，用于包装一个函数为对象](A function interface represents a function and is used to wrap a function as an object)
 * @description: zh - 一个函数接口代表一个一个函数，用于包装一个函数为对象
 * @description: en - A function interface represents a function and is used to wrap a function as an object
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/8 4:58 下午
*/
@FunctionalInterface
public interface Func0<R> extends Serializable {
    /**
     * 执行函数
     *
     * @return 函数执行结果
     * @throws Exception 自定义异常
     */
    R call() throws Exception;

    /**
     * 执行函数，异常包装为RuntimeException
     *
     * @return 函数执行结果
     * @since 5.3.6
     */
    default R callWithRuntimeException() {
        try {
            return call();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}