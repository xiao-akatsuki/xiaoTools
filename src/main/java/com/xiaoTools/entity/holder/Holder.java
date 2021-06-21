package com.xiaoTools.entity.holder;

import com.xiaoTools.entity.mutableObj.MutableObj;

/**
 * [为不可变的对象引用提供一个可变的包装，在java中支持引用传递。](It provides a variable wrapper for immutable object references and supports reference passing in Java.)
 * @description: zh - 为不可变的对象引用提供一个可变的包装，在java中支持引用传递。
 * @description: en - It provides a variable wrapper for immutable object references and supports reference passing in Java.
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/21 8:21 下午
*/
public final class Holder<T> extends MutableObj<T> {

    /**
     * [新建Holder类，持有指定值，当值为空时抛出空指针异常](Create a new holder class, hold the specified value, and throw a null pointer exception when the value is null)
     * @description: zh - 新建Holder类，持有指定值，当值为空时抛出空指针异常
     * @description: en - Create a new holder class, hold the specified value, and throw a null pointer exception when the value is null
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 8:24 下午
     * @param value: 值，不能为空
     * @return com.xiaoTools.entity.holder.Holder<T>
    */
    public static <T> Holder<T> of(T value) throws NullPointerException{
        if(null == value){
            throw new NullPointerException("Holder can not hold a null value!");
        }
        return new Holder<>(value);
    }

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 8:25 下午
    */
    public Holder() {
    }

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 8:24 下午
     * @param value: 被包装的对象
    */
    public Holder(T value) {
        super(value);
    }
}
