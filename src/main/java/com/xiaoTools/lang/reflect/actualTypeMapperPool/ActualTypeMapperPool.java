package com.xiaoTools.lang.reflect.actualTypeMapperPool;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

import com.xiaoTools.cache.simple.SimpleCache;
import com.xiaoTools.util.typeUtil.TypeUtil;

/**
 * [泛型变量和泛型实际类型映射关系缓存](Cache of mapping relationships between generic variables and generic actual types)
 * @description zh - 泛型变量和泛型实际类型映射关系缓存
 * @description en - Cache of mapping relationships between generic variables and generic actual types
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-21 12:27:32
 */
public class ActualTypeMapperPool {

	private static final SimpleCache<Type, Map<Type, Type>> CACHE = new SimpleCache<>();

	/**
	 * [获取泛型变量和泛型实际类型的对应关系Map](Get the mapping between generic variables and generic actual types)
	 * @description zh - 获取泛型变量和泛型实际类型的对应关系Map
	 * @description en - Get the mapping between generic variables and generic actual types
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:28:49
	 * @param type 类型
	 * @return java.util.Map<java.lang.reflect.Type, java.lang.reflect.Type>
	 */
	public static Map<Type, Type> get(Type type) {
		return CACHE.get(type, () -> createTypeMap(type));
	}

	/**
	 * [获得泛型变量对应的泛型实际类型](Get the generic actual type corresponding to the generic variable)
	 * @description zh - 获得泛型变量对应的泛型实际类型
	 * @description en - Get the generic actual type corresponding to the generic variable
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:31:36
	 * @param type 类
	 * @param typeVariable 泛型变量
	 * @return java.lang.reflect.Type
	 */
	public static Type getActualType(Type type, TypeVariable<?> typeVariable) {
		final Map<Type, Type> typeTypeMap = get(type);
		Type result = typeTypeMap.get(typeVariable);
		while (result instanceof TypeVariable) {
			result = typeTypeMap.get(result);
		}
		return result;
	}

	/**
	 * [获取指定泛型变量对应的真实类型](Gets the real type corresponding to the specified generic variable)
	 * @description zh - 获取指定泛型变量对应的真实类型
	 * @description en - Gets the real type corresponding to the specified generic variable
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:32:40
	 * @param type 真实类型所在类
	 * @param typeVariables 泛型变量
	 * @return java.lang.reflect.Type[]
	 */
	public static Type[] getActualTypes(Type type, Type... typeVariables) {
		// 查找方法定义所在类或接口中此泛型参数的位置
		final Type[] result = new Type[typeVariables.length];
		for (int i = 0; i < typeVariables.length; i++) {
			result[i] = (typeVariables[i] instanceof TypeVariable)
					? getActualType(type, (TypeVariable<?>) typeVariables[i])
					: typeVariables[i];
		}
		return result;
	}

	/**
	 * [创建类中所有的泛型变量和泛型实际类型的对应关系Map](Create a map of the correspondence between all generic variables and generic actual types in the class)
	 * @description zh - 创建类中所有的泛型变量和泛型实际类型的对应关系Map
	 * @description en - Create a map of the correspondence between all generic variables and generic actual types in the class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:33:54
	 * @param type 类型
	 * @return java.util.Map<java.lang.reflect.Type, java.lang.reflect.Type>
	 */
	private static Map<Type, Type> createTypeMap(Type type) {
		final Map<Type, Type> typeMap = new HashMap<>();

		while (null != type) {
			final ParameterizedType parameterizedType = TypeUtil.toParameterizedType(type);
			if(null == parameterizedType){
				break;
			}
			final Type[] typeArguments = parameterizedType.getActualTypeArguments();
			final Class<?> rawType = (Class<?>) parameterizedType.getRawType();
			final Type[] typeParameters = rawType.getTypeParameters();

			for (int i = 0; i < typeParameters.length; i++) {
				typeMap.put(typeParameters[i], typeArguments[i]);
			}

			type = rawType;
		}

		return typeMap;
	}

}
