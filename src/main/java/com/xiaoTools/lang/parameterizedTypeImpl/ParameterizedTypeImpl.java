package com.xiaoTools.lang.parameterizedTypeImpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.strUtil.StrUtil;

public class ParameterizedTypeImpl implements ParameterizedType, Serializable {

	private static final long serialVersionUID = 1L;

	private final Type[] actualTypeArguments;
	private final Type ownerType;
	private final Type rawType;

	/**
	 * [构造](structure)
	 * @description zh - 构造
	 * @description en - structure
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:36:48
	 * @param actualTypeArguments 实际的泛型参数类型
	 * @param ownerType 拥有者类型
	 * @param rawType 原始类型
	 */
	public ParameterizedTypeImpl(Type[] actualTypeArguments, Type ownerType, Type rawType) {
		this.actualTypeArguments = actualTypeArguments;
		this.ownerType = ownerType;
		this.rawType = rawType;
	}

	@Override
	public Type[] getActualTypeArguments() {
		return actualTypeArguments;
	}

	@Override
	public Type getOwnerType() {
		return ownerType;
	}

	@Override
	public Type getRawType() {
		return rawType;
	}

	@Override
	public String toString() {
		final StringBuilder buf = new StringBuilder();

		final Type useOwner = this.ownerType;
		final Class<?> raw = (Class<?>) this.rawType;
		if (useOwner == null) {
			buf.append(raw.getName());
		} else {
			if (useOwner instanceof Class<?>) {
				buf.append(((Class<?>) useOwner).getName());
			} else {
				buf.append(useOwner.toString());
			}
			buf.append('.').append(raw.getSimpleName());
		}

		appendAllTo(buf.append('<'), ", ", this.actualTypeArguments).append('>');
		return buf.toString();
	}

	/**
	 * [追加](Add)
	 * @description zh - 追加
	 * @description en - Add
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:38:44
	 * @param buf 目标
	 * @param sep 分隔符
	 * @param types 加入的类型
	 * @return java.lang.StringBuilder
	 */
	private static StringBuilder appendAllTo(final StringBuilder buf, final String sep, final Type... types) {
		if (ArrayUtil.isNotEmpty(types)) {
			boolean isFirst = true;
			for (Type type : types) {
				if (isFirst) {
					isFirst = false;
				} else {
					buf.append(sep);
				}

				String typeStr;
				if(type instanceof Class) {
					typeStr = ((Class<?>)type).getName();
				}else {
					typeStr = StrUtil.toString(type);
				}

				buf.append(typeStr);
			}
		}
		return buf;
	}
}
