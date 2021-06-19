package com.xiaoTools.lang.tuple;

import com.xiaoTools.core.collection.arrayIter.ArrayIter;
import com.xiaoTools.lang.clone.cLoneSupport.CloneSupport;
import com.xiaoTools.lang.constant.Constant;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

/**
 * [不可变数组类型，用于多值返回](Immutable array type, used for multi value return)
 * @description: zh - 不可变数组类型，用于多值返回
 * @description: en - Immutable array type, used for multi value return
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/19 9:59 上午
*/
public class Tuple extends CloneSupport<Tuple> implements Iterable<Object>, Serializable {

    @Serial
    private static final long serialVersionUID = -7689304393482182157L;

    private final Object[] members;

    private int hashCode;

    private boolean cacheHash;

    /**
     * [构造函数](Constructors)
     * @description: zh - 构造函数
     * @description: en - Constructors
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:00 上午
     * @param members: 成员数组
    */
    public Tuple(Object... members) {
        this.members = members;
    }

    /**
     * [获取指定位置元素](Gets the specified location element)
     * @description: zh - 获取指定位置元素
     * @description: en - Gets the specified location element
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:01 上午
     * @param index: 位置
     * @return T
    */
    public <T> T get(int index){
        return (T) members[index];
    }

    /**
     * [获得所有元素](Get all the elements)
     * @description: zh - 获得所有元素
     * @description: en - Get all the elements
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:03 上午
     * @return java.lang.Object[]
    */
    public Object[] getMembers(){
        return this.members;
    }

    /**
     * [缓存Hash值，当为true时，此对象的hash值只被计算一次，常用于Tuple中的值不变时使用。](Cache hash value. When it is true, the hash value of this object is only calculated once. It is often used when the value in tuple is unchanged.)
     * @description: zh - 缓存Hash值，当为true时，此对象的hash值只被计算一次，常用于Tuple中的值不变时使用。
     * @description: en - Cache hash value. When it is true, the hash value of this object is only calculated once. It is often used when the value in tuple is unchanged.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:03 上午
     * @param cacheHash: 是否缓存hash值
     * @return com.xiaoTools.lang.tuple.Tuple
    */
    public Tuple setCacheHash(boolean cacheHash){
        this.cacheHash = cacheHash;
        return this;
    }

    /**
     * [重写 HashCode](Rewrite hashcode)
     * @description: zh - 重写 HashCode
     * @description: en - Rewrite hashcode
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:10 上午
     * @return int
    */
    @Override
    public int hashCode() {
        if(this.cacheHash && Constant.ZERO != this.hashCode){
            return this.hashCode;
        }
        final int prime = Constant.THIRTY_ONE;
        int result = Constant.ONE;
        result = prime * result + Arrays.deepHashCode(members);
        if(this.cacheHash){
            this.hashCode = result;
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return Constant.TRUE;
        }
        if (obj == Constant.NULL) {
            return Constant.FALSE;
        }
        if (getClass() != obj.getClass()) {
            return Constant.FALSE;
        }
        Tuple other = (Tuple) obj;
        return Arrays.deepEquals(members, other.members);
    }

    @Override
    public String toString() {
        return Arrays.toString(members);
    }

    @Override
    public Iterator<Object> iterator() {
        return new ArrayIter<>(members);
    }
}
