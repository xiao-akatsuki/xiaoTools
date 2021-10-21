package com.xiaoTools.util.typeUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.lang.parameterizedTypeImpl.ParameterizedTypeImpl;
import com.xiaoTools.lang.reflect.actualTypeMapperPool.ActualTypeMapperPool;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Map;

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

	/*获取返回值类型--------------------------------------------------------------------get return type*/

	/**
	 * [获取方法的返回值类型](Gets the return value type of the method)
	 * @description zh - 获取方法的返回值类型
	 * @description en - Gets the return value type of the method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:04:14
	 * @param method 方法
	 * @return java.lang.reflect.Type
	 */
	public static Type getReturnType(Method method) {
		return null == method ? null : method.getGenericReturnType();
	}

	/**
	 * [解析方法的返回类型类列表](List of return type classes of the resolution method)
	 * @description zh - 解析方法的返回类型类列表
	 * @description en - List of return type classes of the resolution method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:05:36
	 * @param method 方法
	 * @return java.lang.Class<?>
	 */
	public static Class<?> getReturnClass(Method method) {
		return null == method ? null : method.getReturnType();
	}

	/** 泛型类型 ----------------------------------------------------------------------------------- Type Argument */

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

	/**
	 * [是否未知类型](Unknown type)
	 * @description zh - 是否未知类型
	 * @description en - Unknown type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:07:53
	 * @param type 类型
	 * @return boolean
	 */
	public static boolean isUnknown(Type type) {
		return null == type || type instanceof TypeVariable;
	}

	/**
	 * [指定泛型数组中是否含有泛型变量](Specifies whether the generic array contains generic variables)
	 * @description zh - 指定泛型数组中是否含有泛型变量
	 * @description en - Specifies whether the generic array contains generic variables
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:10:13
	 * @param types 泛型数组
	 * @return boolean
	 */
	public static boolean hasTypeVariable(Type... types) {
		for (Type type : types) {
			if (type instanceof TypeVariable) {
				return Constant.TRUE;
			}
		}
		return Constant.FALSE;
	}

	/**
	 * [获取泛型变量和泛型实际类型的对应关系Map](Get the mapping between generic variables and generic actual types)
	 * @description zh - 获取泛型变量和泛型实际类型的对应关系Map
	 * @description en - Get the mapping between generic variables and generic actual types
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:11:26
	 * @param clazz 被解析的包含泛型参数的类
	 * @return java.util.Map<Type, Type>
	 */
	public static Map<Type, Type> getTypeMap(Class<?> clazz) {
		return ActualTypeMapperPool.get(clazz);
	}

	/**
	 * [获得泛型字段对应的泛型实际类型](Get the generic actual type corresponding to the generic field)
	 * @description zh - 获得泛型字段对应的泛型实际类型
	 * @description en - Get the generic actual type corresponding to the generic field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:18:38
	 * @param type 实际类型明确的类
	 * @param field 字段
	 * @return java.lang.reflect.Type
	 */
	public static Type getActualType(Type type, Field field) {
		return null == field ? null : getActualType(ObjectUtil.defaultIfNull(type, field.getDeclaringClass()), field.getGenericType());
	}

	/**
	 * [获得泛型字段对应的泛型实际类型](Get the generic actual type corresponding to the generic field)
	 * @description zh - 获得泛型字段对应的泛型实际类型
	 * @description en - Get the generic actual type corresponding to the generic field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:20:39
	 * @param type 类
	 * @param typeVariable 泛型变量
	 * @return java.lang.reflect.Type
	 */
	public static Type getActualType(Type type, Type typeVariable) {
		return typeVariable instanceof ParameterizedType ? getActualType(type, (ParameterizedType) typeVariable) :
			typeVariable instanceof TypeVariable ? ActualTypeMapperPool.getActualType(type, (TypeVariable<?>) typeVariable) :
			typeVariable;
	}

	/**
	 * [获得泛型字段对应的泛型实际类型](Get the generic actual type corresponding to the generic field)
	 * @description zh - 获得泛型字段对应的泛型实际类型
	 * @description en - Get the generic actual type corresponding to the generic field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:22:07
	 * @param type 类型
	 * @param parameterizedType 泛型变量
	 * @return java.lang.reflect.Type
	 */
	public static Type getActualType(Type type, ParameterizedType parameterizedType) {
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

		if (TypeUtil.hasTypeVariable(actualTypeArguments)) {
			actualTypeArguments = getActualTypes(type, parameterizedType.getActualTypeArguments());
			if (ArrayUtil.isNotEmpty(actualTypeArguments)) {
				parameterizedType = new ParameterizedTypeImpl(actualTypeArguments, parameterizedType.getOwnerType(), parameterizedType.getRawType());
			}
		}

		return parameterizedType;
	}

	/**
	 * [获得泛型变量对应的泛型实际类型](Get the generic actual type corresponding to the generic variable)
	 * @description zh - 获得泛型变量对应的泛型实际类型
	 * @description en - Get the generic actual type corresponding to the generic variable
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:22:59
	 * @param type 类
	 * @param typeVariables 泛型变量数组
	 * @return java.lang.reflect.Type[]
	 */
	public static Type[] getActualTypes(Type type, Type... typeVariables) {
		return ActualTypeMapperPool.getActualTypes(type, typeVariables);
	}
}
