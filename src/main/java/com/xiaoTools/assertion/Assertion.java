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

    /**
     * [检查给定字符串是否为空白（null、空串或只包含空白符），为空抛出 IllegalArgumentException ](Check whether the given string is blank (null, empty string or contains only blank characters), and throw IllegalArgumentException if it is empty)
     * @description: zh - 检查给定字符串是否为空白（null、空串或只包含空白符），为空抛出 IllegalArgumentException
     * @description: en - Check whether the given string is blank (null, empty string or contains only blank characters), and throw IllegalArgumentException if it is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 4:52 下午
     * @param text: 被检查字符串
     * @return T
    */
    public static <T extends CharSequence> T notBlank(T text) throws IllegalArgumentException {
        return notBlank(text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
    }

    /**
     * [检查给定字符串是否为空白（null、空串或只包含空白符），为空抛出 IllegalArgumentException](Check whether the given string is blank (null, empty string or contains only blank characters), and throw IllegalArgumentException if it is empty)
     * @description: zh - 检查给定字符串是否为空白（null、空串或只包含空白符），为空抛出 IllegalArgumentException
     * @description: en - Check whether the given string is blank (null, empty string or contains only blank characters), and throw IllegalArgumentException if it is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 7:52 上午
     * @param text: 被检查的字符串
     * @param errorMsgTemplate: 错误的模板
     * @param params: 参数
     * @return T
    */
    public static <T extends CharSequence> T notBlank(T text, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        return notBlank(text, () -> new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params)));
    }

    /**
     * [检查给定字符串是否为空白（null、空串或只包含空白符），为空抛出自定义异常。 并使用指定的函数获取错误信息返回](Check whether the given string is blank (null, empty string or contains only blank characters), and throw a custom exception if it is blank. And use the specified function to get the error information)
     * @description: zh - 检查给定字符串是否为空白（null、空串或只包含空白符），为空抛出自定义异常。 并使用指定的函数获取错误信息返回
     * @description: en - Check whether the given string is blank (null, empty string or contains only blank characters), and throw a custom exception if it is blank. And use the specified function to get the error information
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 7:53 上午
     * @param text: 被检查字符串
     * @param errorMsgSupplier: 错误抛出异常附带的消息生产接口
     * @return T
    */
    public static <T extends CharSequence, X extends Throwable> T notBlank(T text, Supplier<X> errorMsgSupplier) throws X {
        if (StrUtil.isBlank(text)) {
            throw errorMsgSupplier.get();
        }
        return text;
    }

    /* 是否为真 ------------------------------------------------------------------------------- Is it true */

    /**
     * [是否为真，如果为 false 抛出给定的异常](Whether it is true. If it is false, the given exception is thrown)
     * @description: zh - 是否为真，如果为 false 抛出给定的异常
     * @description: en - Whether it is true. If it is false, the given exception is thrown
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/8 10:41 上午
     * @param expression: 布尔值
     * @param supplier: 判断错误产生的异常
    */
    public static <X extends Throwable> void isTrue(boolean expression, Supplier<? extends X> supplier) throws X {
        if (!expression) {
            throw supplier.get();
        }
    }

    /**
     * [是否为真，如果为 false 抛出给定的异常](Whether it is true. If it is false, the given exception is thrown)
     * @description: zh - 是否为真，如果为 false 抛出给定的异常
     * @description: en - Whether it is true. If it is false, the given exception is thrown
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/8 10:42 上午
     * @param expression: 布尔值
     * @param errorMsgTemplate: 错误抛出异常附带的消息模板
     * @param params: 参数列表
    */
    public static void isTrue(boolean expression, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        isTrue(expression, () -> new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params)));
    }

    /**
     * [是否为真，如果为 false 抛出给定的异常](Whether it is true. If it is false, the given exception is thrown)
     * @description: zh - 是否为真，如果为 false 抛出给定的异常
     * @description: en - Whether it is true. If it is false, the given exception is thrown
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/8 10:43 上午
     * @param expression: 布尔值
    */
    public static void isTrue(boolean expression) throws IllegalArgumentException {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    /* 是否为假 ------------------------------------------------------------------------------- Is it false */

    /**
     * [是否为假，如果为 true 抛出指定类型异常](Whether it is false. If it is true, an exception of the specified type will be thrown)
     * @description: zh - 是否为假，如果为 true 抛出指定类型异常
     * @description: en - Whether it is false. If it is true, an exception of the specified type will be thrown
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/8 10:57 上午
     * @param expression: 布尔值
     * @param errorSupplier: 不通过时抛出的异常
    */
    public static <X extends Throwable> void isFalse(boolean expression, Supplier<X> errorSupplier) throws X {
        if (expression) {
            throw errorSupplier.get();
        }
    }

    /**
     * [是否为假，如果为 true 抛出指定类型异常](Whether it is false. If it is true, an exception of the specified type will be thrown)
     * @description: zh - 是否为假，如果为 true 抛出指定类型异常
     * @description: en - Whether it is false. If it is true, an exception of the specified type will be thrown
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/8 10:58 上午
     * @param expression: 布尔值
     * @param errorMsgTemplate: 错误抛出异常附带的消息模板，变量用{}代替
     * @param params: 参数列表
    */
    public static void isFalse(boolean expression, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        isFalse(expression, () -> new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params)));
    }

    /**
     * [是否为假，如果为 true 抛出指定类型异常](Whether it is false. If it is true, an exception of the specified type will be thrown)
     * @description: zh - 是否为假，如果为 true 抛出指定类型异常
     * @description: en - Whether it is false. If it is true, an exception of the specified type will be thrown
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/8 10:58 上午
     * @param expression: 布尔值
    */
    public static void isFalse(boolean expression) throws IllegalArgumentException {
        isFalse(expression, "[Assertion failed] - this expression must be false");
    }
}
