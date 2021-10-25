package com.xiaoTools.core.convert.arrayConverter;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.byteUtil.ByteUtil;
import com.xiaoTools.util.iterUtil.IterUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.strUtil.StrUtil;

public class ArrayConverter extends AbstractConverter<Object> {

	private static final long serialVersionUID = 1L;

	private final Class<?> targetType;
	/**
	 * 目标元素类型
	 */
	private final Class<?> targetComponentType;

	/**
	 * 是否忽略元素转换错误
	 */
	private boolean ignoreElementError;

	public ArrayConverter(Class<?> targetType) {
		this(targetType, Constant.FALSE);
	}


	public ArrayConverter(Class<?> targetType, boolean ignoreElementError) {
		if (null == targetType) {
			targetType = Object[].class;
		}

		if (targetType.isArray()) {
			this.targetType = targetType;
			this.targetComponentType = targetType.getComponentType();
		} else {
			this.targetComponentType = targetType;
			this.targetType = ArrayUtil.getArrayType(targetType);
		}

		this.ignoreElementError = ignoreElementError;
	}

	@Override
	protected Object convertInternal(Object value) {
		return value.getClass().isArray() ? convertArrayToArray(value) : convertObjectToArray(value);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public Class getTargetType() {
		return this.targetType;
	}

	/**
	 * [设置是否忽略元素转换错误](Sets whether element conversion errors are ignored)
	 * @description zh - 设置是否忽略元素转换错误
	 * @description en - Sets whether element conversion errors are ignored
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 08:44:34
	 * @param ignoreElementError 是否忽略元素转换错误
	 */
	public void setIgnoreElementError(boolean ignoreElementError) {
		this.ignoreElementError = ignoreElementError;
	}

	/**
	 * [数组对数组转换](Array to array conversion)
	 * @description zh - 数组对数组转换
	 * @description en - Array to array conversion
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 08:45:00
	 */
	private Object convertArrayToArray(Object array) {
		final Class<?> valueComponentType = ArrayUtil.getComponentType(array);

		if (valueComponentType == targetComponentType) {
			return array;
		}

		final int len = ArrayUtil.length(array);
		final Object result = Array.newInstance(targetComponentType, len);

		for (int i = 0; i < len; i++) {
			Array.set(result, i, convertComponentType(Array.get(array, i)));
		}
		return result;
	}

	/**
	 * [非数组对数组转换](Non array to array conversion)
	 * @description zh - 非数组对数组转换
	 * @description en - Non array to array conversion
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 08:45:54
	 * @param value 对象
	 * @retun java.lang.Object
	 */
	private Object convertObjectToArray(Object value) {
		if (value instanceof CharSequence) {
			if (targetComponentType == char.class || targetComponentType == Character.class) {
				return convertArrayToArray(value.toString().toCharArray());
			}

			final String[] strings = StrUtil.split(value.toString(), Constant.STRING_COMMA);
			return convertArrayToArray(strings);
		}

		Object result;
		if (value instanceof List) {
			final List<?> list = (List<?>) value;
			result = Array.newInstance(targetComponentType, list.size());
			for (int i = 0; i < list.size(); i++) {
				Array.set(result, i, convertComponentType(list.get(i)));
			}
		} else if (value instanceof Collection) {
			final Collection<?> collection = (Collection<?>) value;
			result = Array.newInstance(targetComponentType, collection.size());

			int i = 0;
			for (Object element : collection) {
				Array.set(result, i, convertComponentType(element));
				i++;
			}
		} else if (value instanceof Iterable) {
			// 可循环对象转数组，可循环对象无法获取长度，因此先转为List后转为数组
			final List<?> list = IterUtil.toList((Iterable<?>) value);
			result = Array.newInstance(targetComponentType, list.size());
			for (int i = 0; i < list.size(); i++) {
				Array.set(result, i, convertComponentType(list.get(i)));
			}
		} else if (value instanceof Iterator) {
			// 可循环对象转数组，可循环对象无法获取长度，因此先转为List后转为数组
			final List<?> list = IterUtil.toList((Iterator<?>) value);
			result = Array.newInstance(targetComponentType, list.size());
			for (int i = 0; i < list.size(); i++) {
				Array.set(result, i, convertComponentType(list.get(i)));
			}
		}else if (value instanceof Number && byte.class == targetComponentType) {
			result = ByteUtil.numberToBytes((Number)value);
		} else if (value instanceof Serializable && byte.class == targetComponentType) {
			result = ObjectUtil.serialize(value);
		} else {
			// everything else:
			result = convertToSingleElementArray(value);
		}

		return result;
	}

	/**
	 * [单元素数组](Single element array)
	 * @description zh - 单元素数组
	 * @description en - Single element array
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 08:48:28
	 * @param value 对象
	 * @return java.lang.Object[]
	 */
	private Object[] convertToSingleElementArray(Object value) {
		final Object[] singleElementArray = ArrayUtil.newArray(targetComponentType, 1);
		singleElementArray[0] = convertComponentType(value);
		return singleElementArray;
	}

	/**
	 * [转换元素类型](Convert element type)
	 * @description zh - 转换元素类型
	 * @description en - Convert element type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 08:49:03
	 * @param value 对象
	 * @return java.lang.Object
	 */
	private Object convertComponentType(Object value) {
		return Convert.convertWithCheck(this.targetComponentType, value, null, this.ignoreElementError);
	}
}
