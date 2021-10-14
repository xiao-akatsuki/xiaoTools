package com.xiaoTools.core.map.mapProxy;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.getter.optNullBasicTypeFromObjectGetter.OptNullBasicTypeFromObjectGetter;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.booleanUtil.BooleanUtil;
import com.xiaoTools.util.classLoaderUtil.ClassLoaderUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [Map代理](Map proxy)
 * @description zh - Map代理
 * @description en - Map proxy
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-08 07:38:06
 */
public class MapProxy implements Map<Object, Object>, OptNullBasicTypeFromObjectGetter<Object>, InvocationHandler, Serializable {
    private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	Map map;

    /**
     * [创建代理Map](Create proxy map)
     * @description zh - 创建代理Map
     * @description en - Create proxy map
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-14 13:58:10
     * @param map 被代理的Map
     * @return com.xiaoTools.core.map.mapProxy.MapProxy
     */
	public static MapProxy create(Map<?, ?> map) {
		return (map instanceof MapProxy) ? (MapProxy) map : new MapProxy(map);
	}

	public MapProxy(Map<?, ?> map) {
		this.map = map;
	}

	@Override
	public Object getObj(Object key, Object defaultValue) {
		final Object value = map.get(key);
		return null != value ? value : defaultValue;
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public Object get(Object key) {
		return map.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	@SuppressWarnings({"unchecked", "NullableProblems"})
	@Override
	public void putAll(Map<?, ?> m) {
		map.putAll(m);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@SuppressWarnings({"unchecked", "NullableProblems"})
	@Override
	public Set<Object> keySet() {
		return map.keySet();
	}

	@SuppressWarnings({"unchecked", "NullableProblems"})
	@Override
	public Collection<Object> values() {
		return map.values();
	}

	@SuppressWarnings({"unchecked", "NullableProblems"})
	@Override
	public Set<Entry<Object, Object>> entrySet() {
		return map.entrySet();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		final Class<?>[] parameterTypes = method.getParameterTypes();
		if (ArrayUtil.isEmpty(parameterTypes)) {
			final Class<?> returnType = method.getReturnType();
			if (void.class != returnType) {
				// 匹配Getter
				final String methodName = method.getName();
				String fieldName = null;
				if (methodName.startsWith("get")) {
					// 匹配getXXX
					fieldName = StrUtil.removePreAndLowerFirst(methodName, Constant.THREE);
				} else if (BooleanUtil.isBoolean(returnType) && methodName.startsWith("is")) {
					// 匹配isXXX
					fieldName = StrUtil.removePreAndLowerFirst(methodName, Constant.TWO);
				}else if ("hashCode".equals(methodName)) {
					return this.hashCode();
				} else if ("toString".equals(methodName)) {
					return this.toString();
				}
				
				if (StrUtil.isNotBlank(fieldName)) {
					if (false == this.containsKey(fieldName)) {
						// 驼峰不存在转下划线尝试
						fieldName = StrUtil.toUnderlineCase(fieldName);
					}
					return Convert.convert(method.getGenericReturnType(), this.get(fieldName));
				}
			}

		} else if (1 == parameterTypes.length) {
			// 匹配Setter
			final String methodName = method.getName();
			if (methodName.startsWith("set")) {
				final String fieldName = StrUtil.removePreAndLowerFirst(methodName, 3);
				if (StrUtil.isNotBlank(fieldName)) {
					this.put(fieldName, args[Constant.ZERO]);
				}
			} else if ("equals".equals(methodName)) {
				return this.equals(args[Constant.ZERO]);
			}
		}

		throw new UnsupportedOperationException(method.toGenericString());
	}
	
    /**
     * [将Map代理为指定接口的动态代理对象](Proxy map as a dynamic proxy object for the specified interface)
     * @description zh - 将Map代理为指定接口的动态代理对象
     * @description en - Proxy map as a dynamic proxy object for the specified interface
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-14 13:59:42
     * @param interfaceClass 接口
     * @return T
     */
	@SuppressWarnings("unchecked")
	public <T> T toProxyBean(Class<T> interfaceClass) {
		return (T) Proxy.newProxyInstance(ClassLoaderUtil.getClassLoader(), new Class<?>[]{interfaceClass}, this);
	}
}
