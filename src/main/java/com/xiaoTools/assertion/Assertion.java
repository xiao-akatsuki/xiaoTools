package com.xiaoTools.assertion;

import com.xiaoTools.util.strUtil.StrUtil;

import java.util.function.Supplier;

/**
 * [判断某些对象或值是否符合规定，否则抛出异常。经常用于做变量检查](Judge whether some objects or values meet the requirements, otherwise throw an exception. It is often used for variable checking)
 * @description: zh - 判断某些对象或值是否符合规定，否则抛出异常。经常用于做变量检查
 * @description: en - Judge whether some objects or values meet the requirements, otherwise throw an exception. It is often used for variable checking
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/14 10:01 上午
*/
public class Assertion {

    /* 检查不为空 ------------------------------------------------------------------------------- Check not null */

    /**
     * [判断对象是否不为null ，如果为 null 抛出 IllegalArgumentException 异常](Judge whether the object is not null. If it is null, throw an IllegalArgumentException exception)
     * @description: zh - 判断对象是否不为null ，如果为 null 抛出 IllegalArgumentException 异常
     * @description: en - Judge whether the object is not null. If it is null, throw an IllegalArgumentException exception
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/14 10:37 上午
     * @param object: 被检查的对象
     * @return T
    */
    public static <T> T notNull(T object) throws IllegalArgumentException {
        return notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

    /**
     * [判断对象是否不为null ，如果为null 抛出 IllegalArgumentException 异常 Assert that an object](Judge whether the object is not null. If it is null, throw the IllegalArgumentException exception, assert that an object)
     * @description: zh - 判断对象是否不为null ，如果为null 抛出 IllegalArgumentException 异常 Assert that an object
     * @description: en - Judge whether the object is not null. If it is null, throw the IllegalArgumentException exception, assert that an object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/14 10:39 上午
     * @param object: 被检查的对象
     * @param errorMsgTemplate: 错误消息模板，变量使用{}表示
     * @param params: 参数
     * @return T
    */
    public static <T> T notNull(T object, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        return notNull(object, () -> new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params)));
    }

    /**
     * [判断对象是否不为 null ，如果为 null 抛出指定类型异常 并使用指定的函数获取错误信息返回](Determine whether the object is not null. If it is null, throw an exception of the specified type and use the specified function to get the error information)
     * @description: zh - 判断对象是否不为 null ，如果为 null 抛出指定类型异常 并使用指定的函数获取错误信息返回
     * @description: en - Determine whether the object is not null. If it is null, throw an exception of the specified type and use the specified function to get the error information
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/14 10:45 上午
     * @param object: 被检查的对象
     * @param errorSupplier: 错误抛出异常附带的消息生产接口
     * @return T
    */
    public static <T, X extends Throwable> T notNull(T object, Supplier<X> errorSupplier) throws X {
        if (null == object) {
            throw errorSupplier.get();
        }
        return object;
    }
}
