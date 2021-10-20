package com.xiaoTools.util.typeUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
	 * [获取字段对应的Type类型](Gets the type corresponding to the field)
	 * @description zh - 获取字段对应的Type类型
	 * @description en - Gets the type corresponding to the field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 07:35:48
	 * @param field 字段
	 * @return java.lang.reflect.Type
	 */
	public static Type getType(Field field) {
		return null == field ? null : field.getGenericType();
	}

	/**
	 * [获得字段的泛型类型](Gets the generic type of the field)
	 * @description zh - 获得字段的泛型类型
	 * @description en - Gets the generic type of the field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 07:38:24
	 * @param clazz Bean
	 * @param fieldName 字段名
	 * @return java.lang.reflect.Type
	 */
	public static Type getFieldType(Class<?> clazz, String fieldName) {
		return getType(ReflectUtil.getField(clazz, fieldName));
	}

	/**
	 * [获得Field对应的原始类](Get the original class corresponding to field)
	 * @description zh - 获得Field对应的原始类
	 * @description en - Get the original class corresponding to field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 07:39:47
	 * @param field 字段
	 * @return java.lang.Class<?>
	 */
	public static Class<?> getClass(Field field) {
		return null == field ? null : field.getType();
	}

	/*参数类型--------------------------------------------------------------------Param Type*/

	/**
	 * [获取方法的第一个参数类型](Gets the first parameter type of the method)
	 * @description zh - 获取方法的第一个参数类型
	 * @description en - Gets the first parameter type of the method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 07:42:46
	 * @param method 方法
	 * @return java.lang.reflect.Type
	 */
	public static Type getFirstParamType(Method method) {
		return getParamType(method, Constant.ZERO);
	}

	/**
	 * [获取方法的第一个参数类](Gets the first parameter class of the method)
	 * @description zh - 获取方法的第一个参数类
	 * @description en - Gets the first parameter class of the method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 07:43:38
	 * @param method 方法
	 * @return java.lang.Class<?>
	 */
	public static Class<?> getFirstParamClass(Method method) {
		return getParamClass(method, 0);
	}

	/**
	 * [获取方法的参数类型](Gets the parameter type of the method)
	 * @description zh - 获取方法的参数类型
	 * @description en - Gets the parameter type of the method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 07:45:18
	 * @param method 方法
	 * @param index 第几个参数的索引，从0开始计数
	 * @return java.lang.reflect.Type
	 */
	public static Type getParamType(Method method, int index) {
		Type[] types = getParamTypes(method);
		return null != types && types.length > index ? types[index] : null;
	}

	/**
	 * [获取方法的参数类型列表](Gets a list of parameter types for the method)
	 * @description zh - 获取方法的参数类型列表
	 * @description en - Gets a list of parameter types for the method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 07:49:14
	 * @param method 方法
	 * @return java.lang.reflect.Type[]
	 */
	public static Type[] getParamTypes(Method method) {
		return null == method ? null : method.getGenericParameterTypes();
	}

	/**
	 * [获取方法的参数类](Gets the parameter class of the method)
	 * @description zh - 获取方法的参数类
	 * @description en - Gets the parameter class of the method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 07:50:50
	 * @param method 方法
	 * @param index 第几个参数的索引，从0开始计数
	 * @return java.lang.Class<?>
	 */
	public static Class<?> getParamClass(Method method, int index) {
		Class<?>[] classes = getParamClasses(method);
		return null != classes && classes.length > index ? classes[index] : null;

	}

	/**
	 * [解析方法的参数类型列表](A list of parameter types for the resolution method)
	 * @description zh - 解析方法的参数类型列表
	 * @description en - A list of parameter types for the resolution method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 07:51:56
	 * @param method 方法
	 * @return java.lang.Class<?>[]
	 */
	public static Class<?>[] getParamClasses(Method method) {
		return null == method ? null : method.getParameterTypes();
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
