package com.xiaoTools.entity.mutableObj;

import com.xiaoTools.entity.mutable.Mutable;
import com.xiaoTools.lang.constant.Constant;

import java.io.Serial;
import java.io.Serializable;

/**
 * [可变Object](Variable object)
 * @description: zh - 可变Object
 * @description: en - Variable object
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/21 8:37 下午
*/
public class MutableObj<T> implements Mutable<T>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private T value;

    /**
     * [构造，空值](Construction, null)
     * @description: zh - 构造，空值
     * @description: en - Construction, null
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 8:32 下午
    */
    public MutableObj() { }

    /**
     * [有参构造](Parametric structure)
     * @description: zh - 有参构造
     * @description: en - Parametric structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 8:32 下午
     * @param value: 值
    */
    public MutableObj(final T value) {
        this.value = value;
    }

    /*重写-----------------------------------------------------------Override*/

    @Override
    public T get() {
        return this.value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == Constant.NULL) {
            return Constant.FALSE;
        }
        if (this == obj) {
            return Constant.TRUE;
        }
        if (this.getClass() == obj.getClass()) {
            final MutableObj<?> that = (MutableObj<?>) obj;
            return this.value.equals(that.value);
        }
        return Constant.FALSE;
    }

    @Override
    public int hashCode() {
        return value == Constant.NULL ? Constant.ZERO : value.hashCode();
    }

    @Override
    public String toString() {
        return value == Constant.NULL ? Constant.STRING_NULL_OUT : value.toString();
    }
}
