package com.xiaoTools.util.exceptionUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;
import com.xiaoTools.util.strUtil.StrUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

/**
 * [异常工具类](Exception tool class)
 * @description: zh - 异常工具类
 * @description: en - Exception tool class
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/16 12:05 下午
*/
public class ExceptionUtil {

    /*异常--------------------------------------------------------------------Exception*/

    /**
     * [获得完整消息，包括异常名，消息格式为：{SimpleClassName}: {ThrowableMessage}](Get the complete message, including the exception name. The message format is: {simpleclassname}: {throwablemessage})
     * @description: zh - 获得完整消息，包括异常名，消息格式为：{SimpleClassName}: {ThrowableMessage}
     * @description: en - Get the complete message, including the exception name. The message format is: {simpleclassname}: {throwablemessage}
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 12:08 下午
     * @param e: 异常问题
     * @return java.lang.String
    */
    public static String getMessage(Throwable e) {
        return Constant.NULL == e ? Constant.STRING_NULL : StrUtil.format(Constant.DOUBLE_BRACKETS, e.getClass().getSimpleName(), e.getMessage());
    }

    /**
     * [获得消息，调用异常类的getMessage方法](Get the message and call the GetMessage method of the exception class)
     * @description: zh - 获得消息，调用异常类的getMessage方法
     * @description: en - Get the message and call the GetMessage method of the exception class
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 12:12 下午
     * @param e: 异常问题
     * @return java.lang.String
    */
    public static String getSimpleMessage(Throwable e) {
        return (Constant.NULL == e) ? Constant.STRING_NULL : e.getMessage();
    }

    /**
     * [使用运行时异常包装编译异常](Compiling exceptions using runtime exception wrapper)
     * @description: zh - 使用运行时异常包装编译异常
     * @description: en - Compiling exceptions using runtime exception wrapper
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 12:14 下午
     * @param throwable: 异常
     * @return java.lang.RuntimeException
    */
    public static RuntimeException wrapRuntime(Throwable throwable) {
        return throwable instanceof RuntimeException ? (RuntimeException) throwable : new RuntimeException(throwable);
    }

    /**
     * [将指定的消息包装为运行时异常](Wraps the specified message as a runtime exception)
     * @description: zh - 将指定的消息包装为运行时异常
     * @description: en - Wraps the specified message as a runtime exception
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 12:17 下午
     * @param message: 异常消息
     * @return java.lang.RuntimeException
    */
    public static RuntimeException wrapRuntime(String message) {
        return new RuntimeException(message);
    }

    /**
     * [包装异常并重新抛出此异常](Wrap the exception and throw it again)
     * @description: zh - 包装异常并重新抛出此异常
     * @description: en - Wrap the exception and throw it again
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 12:18 下午
     * @param throwable: 异常
    */
    public static void wrapAndThrow(Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException) throwable;
        }
        if (throwable instanceof Error) {
            throw (Error) throwable;
        }
        throw new UndeclaredThrowableException(throwable);
    }

    /**
     * [包装一个异常](Packing an exception)
     * @description: zh - 包装一个异常
     * @description: en - Packing an exception
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 12:26 下午
     * @param throwable: 异常
     * @param wrapThrowable: 包装之后的异常
     * @return T
    */
    public static <T extends Throwable> T wrap(Throwable throwable, Class<T> wrapThrowable) {
        if (wrapThrowable.isInstance(throwable)) {
            return (T) throwable;
        }
        return ReflectUtil.newInstance(wrapThrowable, throwable);
    }

    /**
     * [将消息包装为运行时异常并抛出](Wrap the message as a runtime exception and throw it)
     * @description: zh - 将消息包装为运行时异常并抛出
     * @description: en - Wrap the message as a runtime exception and throw it
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 12:19 下午
     * @param message: 异常消息
    */
    public static void wrapRuntimeAndThrow(String message) {
        throw new RuntimeException(message);
    }

    /**
     * [剥离反射引发的InvocationTargetException、UndeclaredThrowableException中间异常，返回业务本身的异常](The middle exception of InvocationTargetException and UndeclaredThrowableException caused by split reflection returns the exception of the service itself)
     * @description: zh - 剥离反射引发的InvocationTargetException、UndeclaredThrowableException中间异常，返回业务本身的异常
     * @description: en - The middle exception of InvocationTargetException and UndeclaredThrowableException caused by split reflection returns the exception of the service itself
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 12:21 下午
     * @param wrapped: 包装的异常
     * @return java.lang.Throwable
    */
    public static Throwable unwrap(Throwable wrapped) {
        Throwable unwrapped = wrapped;
        while (true) {
            if (unwrapped instanceof InvocationTargetException) {
                unwrapped = ((InvocationTargetException) unwrapped).getTargetException();
            } else if (unwrapped instanceof UndeclaredThrowableException) {
                unwrapped = ((UndeclaredThrowableException) unwrapped).getUndeclaredThrowable();
            } else {
                return unwrapped;
            }
        }
    }

    /*堆和栈--------------------------------------------------------------------Heap and Stack*/

    /**
     * [获取当前栈信息](Get current stack information)
     * @description: zh - 获取当前栈信息
     * @description: en - Get current stack information
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 12:28 下午
     * @return java.lang.StackTraceElement[]
    */
    public static StackTraceElement[] getStackElements() {
        return Thread.currentThread().getStackTrace();
    }

    /**
     * [获取指定层的堆栈信息](Gets the stack information of the specified layer)
     * @description: zh - 获取指定层的堆栈信息
     * @description: en - Gets the stack information of the specified layer
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 12:30 下午
     * @param layers: 层数
     * @return java.lang.StackTraceElement
    */
    public static StackTraceElement getStackElement(int layers) {
        return getStackElements()[layers];
    }

    /**
     * [获取入口堆栈信息](Get the entry stack information)
     * @description: zh - 获取入口堆栈信息
     * @description: en - Get the entry stack information
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 12:32 下午
     * @return java.lang.StackTraceElement
    */
    public static StackTraceElement getRootStackElement() {
        final StackTraceElement[] stackElements = getStackElements();
        return stackElements[stackElements.length - 1];
    }

    /**
     * [判断是否由指定异常类引起](Determine whether it is caused by the specified exception class)
     * @description: zh - 判断是否由指定异常类引起
     * @description: en - Determine whether it is caused by the specified exception class
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 12:54 下午
     * @param throwable: 异常
     * @param causeClasses: 定义的引起异常的类
     * @return boolean
    */
    public static boolean isCausedBy(Throwable throwable, Class<? extends Exception>... causeClasses) {
        return null != getCausedBy(throwable, causeClasses);
    }

    /**
     * [获取由指定异常类引起的异常](Gets the exception caused by the specified exception class)
     * @description: zh - 获取由指定异常类引起的异常
     * @description: en - Gets the exception caused by the specified exception class
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 12:55 下午
     * @param throwable: 异常对象
     * @param causeClasses: 定义的引起异常的类
     * @return java.lang.Throwable
    */
    public static Throwable getCausedBy(Throwable throwable, Class<? extends Exception>... causeClasses) {
        Throwable cause = throwable;
        while (cause != Constant.NULL) {
            for (Class<? extends Exception> causeClass : causeClasses) {
                if (causeClass.isInstance(cause)) {
                    return cause;
                }
            }
            cause = cause.getCause();
        }
        return Constant.THROWABLE_NULL;
    }

    /**
     * [判断指定异常是否来自或者包含指定异常](Determine whether the specified exception comes from or contains the specified exception)
     * @description: zh - 判断指定异常是否来自或者包含指定异常
     * @description: en - Determine whether the specified exception comes from or contains the specified exception
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 1:03 下午
     * @param throwable: 异常
     * @param exceptionClass: 定义的引起异常的类
     * @return boolean
    */
    public static boolean isFromOrSuppressedThrowable(Throwable throwable, Class<? extends Throwable> exceptionClass) {
        return convertFromOrSuppressedThrowable(throwable, exceptionClass, Constant.TRUE) != Constant.NULL;
    }

    /**
     * [转化指定异常为来自或者包含指定异常](Converts the specified exception to one from or containing the specified exception)
     * @description: zh - 转化指定异常为来自或者包含指定异常
     * @description: en - Converts the specified exception to one from or containing the specified exception
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 1:05 下午
     * @param throwable: 异常
     * @param exceptionClass: 定义的引起异常的类
     * @return T
    */
    public static <T extends Throwable> T convertFromOrSuppressedThrowable(Throwable throwable, Class<T> exceptionClass) {
        return convertFromOrSuppressedThrowable(throwable, exceptionClass, Constant.TRUE);
    }

    /**
     * [判断指定异常是否来自或者包含指定异常](Determine whether the specified exception comes from or contains the specified exception)
     * @description: zh - 判断指定异常是否来自或者包含指定异常
     * @description: en - Determine whether the specified exception comes from or contains the specified exception
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 1:06 下午
     * @param throwable: 异常
     * @param exceptionClass: 定义的引起异常的类
     * @param checkCause: 判断cause
     * @return boolean
    */
    public static boolean isFromOrSuppressedThrowable(Throwable throwable, Class<? extends Throwable> exceptionClass, boolean checkCause) {
        return convertFromOrSuppressedThrowable(throwable, exceptionClass, checkCause) != Constant.NULL;
    }

    /**
     * [转化指定异常为来自或者包含指定异常](Converts the specified exception to one from or containing the specified exception)
     * @description: zh - 转化指定异常为来自或者包含指定异常
     * @description: en - Converts the specified exception to one from or containing the specified exception
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 1:03 下午
     * @param throwable: 异常
     * @param exceptionClass: 定义的引起异常的类
     * @param checkCause: 判断cause
     * @return T
    */
    public static <T extends Throwable> T convertFromOrSuppressedThrowable(Throwable throwable, Class<T> exceptionClass, boolean checkCause) {
        if (throwable == Constant.NULL || exceptionClass == Constant.NULL) {
            return (T) Constant.NULL;
        }
        if (exceptionClass.isAssignableFrom(throwable.getClass())) {
            return (T) throwable;
        }
        if (checkCause) {
            Throwable cause = throwable.getCause();
            if (cause != Constant.NULL && exceptionClass.isAssignableFrom(cause.getClass())) {
                return (T) cause;
            }
        }
        Throwable[] throwables = throwable.getSuppressed();
        if (ArrayUtil.isNotEmpty(throwables)) {
            for (Throwable throwable1 : throwables) {
                if (exceptionClass.isAssignableFrom(throwable1.getClass())) {
                    return (T) throwable1;
                }
            }
        }
        return (T) Constant.NULL;
    }

    /**
     * [获取异常链上所有异常的集合，如果 Throwable 对象没有cause，返回只有一个节点的List 如果传入null，返回空集合](Get the collection of all exceptions in the exception chain. If the Throwable object has no cause, return a List with only one node. If you pass in null, return an empty collection)
     * @description: zh - 获取异常链上所有异常的集合，如果 Throwable 对象没有cause，返回只有一个节点的List 如果传入null，返回空集合
     * @description: en - Get the collection of all exceptions in the exception chain. If the Throwable object has no cause, return a List with only one node. If you pass in null, return an empty collection
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 2:46 下午
     * @param throwable: 异常对象，可以为null
     * @return java.util.List<java.lang.Throwable>
    */
    public static List<Throwable> getThrowableList(Throwable throwable) {
        final List<Throwable> list = new ArrayList<>();
        while (throwable != Constant.NULL && !list.contains(throwable)) {
            list.add(throwable);
            throwable = throwable.getCause();
        }
        return list;
    }

    /**
     * [获取异常链中最尾端的异常，即异常最早发生的异常对象。 此方法通过调用Throwable.getCause() 直到没有cause为止，如果异常本身没有cause，返回异常本身传入null返回也为null](Get the last exception in the exception chain, that is, the exception object where the exception occurred earliest. This method calls Throwable.getCause() until there is no cause. If the exception itself does not have a cause, the exception itself is returned and null is passed in and the return is also null.)
     * @description: zh - 获取异常链中最尾端的异常，即异常最早发生的异常对象。 此方法通过调用Throwable.getCause() 直到没有cause为止，如果异常本身没有cause，返回异常本身传入null返回也为null
     * @description: en - Get the last exception in the exception chain, that is, the exception object where the exception occurred earliest. This method calls Throwable.getCause() until there is no cause. If the exception itself does not have a cause, the exception itself is returned and null is passed in and the return is also null.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 2:51 下午
     * @param throwable: 异常对象，可能为null
     * @return java.lang.Throwable
    */
    public static Throwable getRootCause(final Throwable throwable) {
        final List<Throwable> list = getThrowableList(throwable);
        return list.size() < Constant.ONE ? Constant.THROWABLE_NULL : list.get(list.size() - Constant.ONE);
    }

    /**
     * [ 获取异常链中最尾端的异常的消息，消息格式为：{SimpleClassName}: {ThrowableMessage} ]()
     * @description: zh - 获取异常链中最尾端的异常的消息，消息格式为：{SimpleClassName}: {ThrowableMessage}
     * @description: en - 获取异常链中最尾端的异常的消息，消息格式为：{SimpleClassName}: {ThrowableMessage}
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 2:52 下午
     * @param throwable: 异常
     * @return java.lang.String
    */
    public static String getRootCauseMessage(final Throwable throwable) {
        return getMessage(getRootCause(throwable));
    }
}
