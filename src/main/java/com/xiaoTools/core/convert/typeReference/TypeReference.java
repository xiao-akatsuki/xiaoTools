package com.xiaoTools.core.convert.typeReference;

import com.xiaoTools.util.typeUtil.TypeUtil;

import java.lang.reflect.Type;

/**
 * [Type类型参考](Type reference)
 * @description: zh - Type类型参考
 * @description: en - Type reference
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/24 7:39 下午
*/
public abstract class TypeReference<T> implements Type {


    /**
     * 泛型参数
     */
    private final Type type;

    /**
     * 构造
     */
    public TypeReference() {
        this.type = TypeUtil.getTypeArgument(getClass());
    }

    /**
     * [获取用户定义的泛型参数](Gets a user-defined generic parameter)
     * @description: zh - 获取用户定义的泛型参数
     * @description: en - Gets a user-defined generic parameter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 7:41 下午
     * @return java.lang.reflect.Type
    */
    public Type getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }

}
