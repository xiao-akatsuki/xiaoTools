package com.xiaoTools.util.typeUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/**
 * [针对 Type 的工具类封装](Tool class encapsulation for Type)
 * @description: zh - 针对 Type 的工具类封装
 * @description: en - Tool class encapsulation for Type
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 9:12 上午
*/
public class TypeUtil {


    /*类型参数--------------------------------------------------------------------type argument*/

    /**
     * [获得Type对应的原始类](Get the original class corresponding to type)
     * @description: zh - 获得Type对应的原始类
     * @description: en - Get the original class corresponding to type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 2:43 下午
     * @param type: 类型
     * @return java.lang.Class<?>
    */
    public static Class<?> getClass(Type type) {
        if (Constant.TYPE_NULL != type) {
            if (type instanceof Class) {
                return (Class<?>) type;
            } else if (type instanceof ParameterizedType) {
                return (Class<?>) ((ParameterizedType) type).getRawType();
            } else if (type instanceof TypeVariable) {
                return (Class<?>) ((TypeVariable<?>) type).getBounds()[Constant.ZERO];
            } else if (type instanceof WildcardType) {
                final Type[] upperBounds = ((WildcardType) type).getUpperBounds();
                if (upperBounds.length == Constant.ONE) {
                    return getClass(upperBounds[Constant.ZERO]);
                }
            }
        }
        return null;
    }

    /**
     * [获得给定类的第一个泛型参数](Gets the first generic parameter of a given class)
     * @description: zh - 获得给定类的第一个泛型参数
     * @description: en - Gets the first generic parameter of a given class
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 9:14 上午
     * @param type: 被检查的类型，必须是已经确定泛型类型的类型
     * @return java.lang.reflect.Type
    */
    public static Type getTypeArgument(Type type) {
        return getTypeArgument(type, 0);
    }

    /**
     * [获得给定类的泛型参数](Gets the generic parameters of the given class)
     * @description: zh - 获得给定类的泛型参数
     * @description: en - Gets the generic parameters of the given class
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 9:16 上午
     * @param type: 被检查的类型，必须是已经确定泛型类型的类
     * @param index: 泛型类型的索引号，即第几个泛型类型
     * @return java.lang.reflect.Type
    */
    public static Type getTypeArgument(Type type, int index) {
        final Type[] typeArguments = getTypeArguments(type);
        if (Constant.NULL != typeArguments && typeArguments.length > index) {
            return typeArguments[index];
        }
        return Constant.TYPE_NULL;
    }

    /**
     * [获得指定类型中所有泛型参数类型](Gets all generic parameter types in the specified type)
     * @description: zh - 获得指定类型中所有泛型参数类型
     * @description: en - Gets all generic parameter types in the specified type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 9:18 上午
     * @param type: 指定类型
     * @return java.lang.reflect.Type[]
    */
    public static Type[] getTypeArguments(Type type) {
        if (Constant.TYPE_NULL == type) { return Constant.TYPES_NULL; }
        final ParameterizedType parameterizedType = toParameterizedType(type);
        return (Constant.TYPE_NULL == parameterizedType) ? Constant.TYPES_NULL : parameterizedType.getActualTypeArguments();
    }
    /**
     * [用于获取泛型参数具体的参数类型](It is used to get the specific parameter types of generic parameters)
     * @description: zh - 用于获取泛型参数具体的参数类型
     * @description: en - It is used to get the specific parameter types of generic parameters
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 9:19 上午
     * @param type: 指定类型
     * @return java.lang.reflect.ParameterizedType
    */
    public static ParameterizedType toParameterizedType(Type type) {
        ParameterizedType result = null;
        if (type instanceof ParameterizedType) {
            result = (ParameterizedType) type;
        } else if (type instanceof Class) {
            final Class<?> clazz = (Class<?>) type;
            Type genericSuper = clazz.getGenericSuperclass();
            if (null == genericSuper || Object.class.equals(genericSuper)) {
                // 如果类没有父类，而是实现一些定义好的泛型接口，则取接口的Type
                final Type[] genericInterfaces = clazz.getGenericInterfaces();
                if (ArrayUtil.isNotEmpty(genericInterfaces)) {
                    // 默认取第一个实现接口的泛型Type
                    genericSuper = genericInterfaces[0];
                }
            }
            result = toParameterizedType(genericSuper);
        }
        return result;
    }
}
