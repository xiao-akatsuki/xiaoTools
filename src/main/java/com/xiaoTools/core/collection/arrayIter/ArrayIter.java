package com.xiaoTools.core.collection.arrayIter;

import com.xiaoTools.lang.constant.Constant;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * [数组Iterator对象](Array iterator object)
 * @description: zh - 数组Iterator对象
 * @description: en - Array iterator object
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/19 10:16 上午
*/
public class ArrayIter<E> implements Iterator<E>, Iterable<E>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 数组
     */
    private final Object array;

    /**
     * 起始位置
     */
    private int startIndex;

    /**
     * 结束位置
     */
    private int endIndex;

    /**
     * 当前位置
     */
    private int index;

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:16 上午
     * @param array: 数组
    */
    public ArrayIter(final Object array) {
        this(array, Constant.ZERO);
    }

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:17 上午
     * @param array: 数组
     * @param startIndex: 起始位置，当起始位置小于0或者大于结束位置，置为0。
    */
    public ArrayIter(final Object array, final int startIndex) {
        this(array, startIndex, Constant.NEGATIVE_ONE);
    }

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:19 上午
     * @param array: 数组
     * @param startIndex: 起始位置，当起始位置小于0或者大于结束位置，置为0。
     * @param endIndex: 结束位置，当结束位置小于0或者大于数组长度，置为数组长度。
    */
    public ArrayIter(final Object array, final int startIndex, final int endIndex) {
        this.endIndex = Array.getLength(array);
        if(endIndex > Constant.ZERO && endIndex < this.endIndex){
            this.endIndex = endIndex;
        }
        if(startIndex >= Constant.ZERO && startIndex < this.endIndex){
            this.startIndex = startIndex;
        }
        this.array = array;
        this.index = this.startIndex;
    }

    @Override
    public boolean hasNext() {
        return (index < endIndex);
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return (E)Array.get(array, index++);
    }

    /**
     * [不允许操作数组元素](Operation on array elements is not allowed)
     * @description: zh - 不允许操作数组元素
     * @description: en - Operation on array elements is not allowed
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:19 上午
    */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() method is not supported");
    }

    /*属性--------------------------------------------------------------------Properties*/

    /**
     *
     * @description: zh - 获得原始数组对象
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:20 上午
     * @return java.lang.Object
    */
    public Object getArray() {
        return array;
    }

    /**
     * [重置数组位置](Reset array position)
     * @description: zh - 重置数组位置
     * @description: en - Reset array position
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:20 上午
    */
    public void reset() {
        this.index = this.startIndex;
    }

    @Override
    public Iterator<E> iterator() {
        return this;
    }
}
