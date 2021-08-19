package com.xiaoTools.util.arrayUtil;

import java.lang.reflect.Array;
import java.util.Objects;

import com.xiaoTools.core.matcher.Matcher;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.numUtil.NumUtil;
import com.xiaoTools.util.primitiveArrayUtil.PrimitiveArrayUtil;

/**
 * [数组工具类](Array tool class)
 * @author HCY
 * @since 2021/5/16 2:21 [下午](afternoon)
*/
public class ArrayUtil extends PrimitiveArrayUtil {

    /* 数组是否为空 ------------------------------------------------------------------------------- Is Empty */

    /**
     * [数组是否为空](Is the array empty)
     * @description zh - 数组是否为空
     * @description en - Is the array empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-18 14:31:59
     * @param array 数组
     * @return boolean
     */
    public static <T> boolean isEmpty(T[] array) {
		return array == Constant.NULL || array.length == Constant.ZERO;
	}

    /**
     * [数组是否为空](Is the array empty)
     * @description zh - 数组是否为空
     * @description en - Is the array empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-18 14:33:32
     * @param array 数组
     * @return boolean
     */
    public static boolean isEmpty(Object array) {
		if (array != Constant.NULL) {
			if (isArray(array)) {
				return Constant.ZERO == Array.getLength(array);
			}
			return Constant.FALSE;
		}
		return Constant.TRUE;
	}

    /* 如果给定数组为空 ------------------------------------------------------------------------------- default array */

    /**
     * [如果给定数组为空，返回默认数组](If the given array is empty, the default array is returned)
     * @description zh - 如果给定数组为空，返回默认数组
     * @description en - If the given array is empty, the default array is returned
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-18 15:00:54
     */
    public static <T> T[] defaultIfEmpty(T[] array, T[] defaultArray) {
		return isEmpty(array) ? defaultArray : array;
	}

    /* 数组是否为非空 ------------------------------------------------------------------------------- isNotEmpty */

    /**
     * [数组是否为非空](Is the array non empty)
     * @description zh - 数组是否为非空
     * @description en - Is the array non empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-18 15:02:55
     * @param array 数组
     * @return T
     */
    public static <T> boolean isNotEmpty(T[] array) {
		return (Constant.NULL != array && array.length != Constant.ZERO);
	}

    /**
     * [数组是否为非空](Is the array non empty)
     * @description zh - 数组是否为非空
     * @description en - Is the array non empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-18 15:04:32
     * @param array 数组
     * @return boolean
     */
    public static boolean isNotEmpty(Object array) {
		return Constant.FALSE == isEmpty(array);
	}

    /* 是否包含 null 元素 ------------------------------------------------------------------------------- hasNull */

    /**
     * [是否包含 null 元素](Contains null elements)
     * @description zh - 是否包含 null 元素
     * @description en - Contains null elements
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-18 19:41:43
     * @param array 数组
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean hasNull(T... array) {
		if (isNotEmpty(array)) {
			for (T element : array) {
				if (Constant.NULL == element) {
					return Constant.TRUE;
				}
			}
		}
		return Constant.FALSE;
	}

    /* 多个字段是否全为 null ------------------------------------------------------------------------------- isAllNull */

    /**
     * [多个字段是否全为 null](Are multiple fields all null)
     * @description zh - 多个字段是否全为 null
     * @description en - Are multiple fields all null
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-18 20:14:56
     * @param array 数组
     * @return boolean
     */
    @SuppressWarnings("unchecked")
	public static <T> boolean isAllNull(T... array) {
		return Constant.NULL == firstNonNull(array);
	}

    /**
     * [返回数组中第一个匹配规则的值](Returns the value of the first matching rule in the array)
     * @description zh - 返回数组中第一个非空元素
     * @description en - Returns the value of the first matching rule in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-18 20:18:19
     * @param array 数组
     * @return T
     */
    @SuppressWarnings("unchecked")
	public static <T> T firstNonNull(T... array) {
		return firstMatch(Objects::nonNull, array);
	}

    /**
     * [返回数组中第一个匹配规则的值](Returns the value of the first matching rule in the array)
     * @description zh - 返回数组中第一个匹配规则的值
     * @description en - Returns the value of the first matching rule in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-18 20:22:49
     * @param match
     * @param array 数组
     * @return T
     */
    @SuppressWarnings("unchecked")
	public static <T> T firstMatch(Matcher<T> matcher, T... array) {
		if (isNotEmpty(array)) {
			for (final T val : array) {
				if (matcher.match(val)) {
					return val;
				}
			}
		}
		return null;
	}

    /* 新建一个空数组 ------------------------------------------------------------------------------- newArray */

    /**
     * [新建一个空数组](Create a new empty array)
     * @description zh - 新建一个空数组
     * @description en - Create a new empty array 
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-18 20:54:36
     * @param componentType 类型
     * @param size 数组大小
     * @return T[]
     */
    @SuppressWarnings("unchecked")
	public static <T> T[] newArray(Class<?> componentType, int size) {
		return (T[]) Array.newInstance(componentType, size);
	}

    /**
     * [新建一个空数组](Create a new empty array)
     * @description zh - 新建一个空数组
     * @description en - Create a new empty array 
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-18 21:47:45
     * @param size 数组大小
     * @return Object[]
     */
    public static Object[] newArray(int size) {
		return new Object[size];
	}

    /* 获取数组对象的元素类型 ------------------------------------------------------------------------------- type */

    /**
     * [获取数组对象的元素类型](Gets the element type of the array object)
     * @description zh - 获取数组对象的元素类型
     * @description en - Gets the element type of the array object
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-19 14:12:43
     * @param array 数组
     * @return class
     */
    public static Class<?> getComponentType(Object array) {
		return Constant.NULL == array ? null : array.getClass().getComponentType();
	}

    /**
     * [获取数组对象的元素类型](Gets the element type of the array object)
     * @description zh - 获取数组对象的元素类型
     * @description en - Gets the element type of the array object
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-19 14:16:17
     * @param arratClass 数组类
     * @return Class
     */
    public static Class<?> getComponentType(Class<?> arrayClass) {
		return Constant.NULL == arrayClass ? null : arrayClass.getComponentType();
	}

    /**
     * [获取数组对象的元素类型](Gets the element type of the array object)
     * @description zh - 获取数组对象的元素类型
     * @description en - Gets the element type of the array object
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-19 14:18:32
     * @param type 数组元素类型
     * @return Class
     */
    public static Class<?> getArrayType(Class<?> type) {
		return Array.newInstance(type, Constant.ZERO).getClass();
	}

    /* 强转数组类型 ------------------------------------------------------------------------------- cast */

    /**
     * [强转数组类型](Strong conversion array type)
     * @description zh - 强转数组类型
     * @description en - Strong conversion array type
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-19 18:38:48
     * @param type 数组类型或数组元素类型
     * @param arrayObj 原数组
     * @return java.lang.Object[]
     */
    public static Object[] cast(Class<?> type, Object arrayObj) throws NullPointerException, IllegalArgumentException {
		if (Constant.NULL == arrayObj) {
			throw new NullPointerException("Argument [arrayObj] is null");
		}
		if (Constant.FALSE == arrayObj.getClass().isArray()) {
			throw new IllegalArgumentException("Argument [arrayObj] is not array");
		}
		if (Constant.NULL == type) { return (Object[]) arrayObj; }

		final Class<?> componentType = type.isArray() ? type.getComponentType() : type;
		final Object[] array = (Object[]) arrayObj;
		final Object[] result = ArrayUtil.newArray(componentType, array.length);
		System.arraycopy(array, Constant.ZERO, result, Constant.ZERO, array.length);
		return result;
	}

    /* 将新元素添加到已有数组中 -------------------------------------------------------------- append */

    /**
     * [将新元素添加到已有数组中](Adds a new element to an existing array)
     * @description zh - 将新元素添加到已有数组中
     * @description en - Adds a new element to an existing array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-19 19:05:05
     * @param buffer 已有数组
     * @param elements 新元素
     * @return T[]
     */
    @SafeVarargs
	public static <T> T[] append(T[] buffer, T... elements) {
        return isEmpty( buffer ) ? elements : insert(buffer, buffer.length, elements);
	}

    /**
     * [将新元素添加到已有数组中](Adds a new element to an existing array)
     * @description zh - 将新元素添加到已有数组中
     * @description en - Adds a new element to an existing array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-19 19:10:02
     * @param array 已有数组
     * @param elements 新元素
     * @return java.lang.Object
     */
    @SafeVarargs
	public static <T> Object append(Object array, T... elements) {
        return isEmpty(array) ? elements : insert(array, length(array), elements);
	}

    /**
     * [将元素值设置为数组的某个位置](Set the element value to a position in the array)
     * @description zh - 将元素值设置为数组的某个位置
     * @description en - Set the element value to a position in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-19 19:12:16
     * @param buffer 已有数组
     * @param index 位置，大于长度追加，否则替换
     * @param value 新值
     * @return T[]
     */
    public static <T> T[] setOrAppend(T[] buffer, int index, T value) {
		if (index < buffer.length) {
			Array.set(buffer, index, value);
			return buffer;
		} else {
			return append(buffer, value);
		}
	}

    /**
     * [将元素值设置为数组的某个位置](Set the element value to a position in the array)
     * @description zh - 将元素值设置为数组的某个位置
     * @description en - Set the element value to a position in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-19 19:42:00
     * @param buffer 已有数组
     * @param index 位置，大于长度追加，否则替换
     * @param value 新值
     * @return java.lang.Object
     */
    public static Object setOrAppend(Object array, int index, Object value) {
		if (index < length(array)) {
			Array.set(array, index, value);
			return array;
		} else {
			return append(array, value);
		}
	}

    /**
     * [将新元素插入到到已有数组中的某个位置](Inserts a new element into an existing array)
     * @description zh - 将新元素插入到到已有数组中的某个位置
     * @description en - Inserts a new element into an existing array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-19 19:43:55
     * @param buffer 已有数组
     * @param index 插入位置，此位置为对应此位置元素之前的空档
     * @param elements 新元素
     * @return T[]
     */
    @SuppressWarnings("unchecked")
	public static <T> T[] insert(T[] buffer, int index, T... elements) {
		return (T[]) insert((Object) buffer, index, elements);
	}

    /**
     * [将新元素插入到到已有数组中的某个位置](Inserts a new element into an existing array)
     * @description zh - 将新元素插入到到已有数组中的某个位置
     * @description en - Inserts a new element into an existing array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-19 19:49:23
     * @param array 已有数组
     * @param index 插入位置，此位置为对应此位置元素之前的空档
     * @param elements 新元素
     * @return java.lang.Object
     */
    @SuppressWarnings({"unchecked", "SuspiciousSystemArraycopy"})
	public static <T> Object insert(Object array, int index, T... elements) {
		if (isEmpty(elements)) {
			return array;
		}
		if (isEmpty(array)) {
			return elements;
		}

		final int len = length(array);
		if (index < Constant.ZERO) {
			index = (index % len) + len;
		}

		final T[] result = newArray(array.getClass().getComponentType(), NumUtil.max(len, index) + elements.length);
		System.arraycopy(array, Constant.ZERO, result, Constant.ZERO, Math.min(len, index));
		System.arraycopy(elements, Constant.ZERO, result, index, elements.length);
		if (index < len) {
			System.arraycopy(array, index, result, index + elements.length, len - index);
		}
		return result;
	}

    /* 生成一个新的重新设置大小的数组 -------------------------------------------------------------- resize */

    /**
     * [生成一个新的重新设置大小的数组](Generates a new resized array)
     * @description zh - 生成一个新的重新设置大小的数组
     * @description en - Generates a new resized array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-19 19:55:30
     * @param data 原数组
     * @param size 新的数组大小
     * @param type 数组元素类型
     * @return T[]
     */
    public static <T> T[] resize(T[] data, int size, Class<?> type) {
		if (size < Constant.ZERO) {
			return data;
		}
		final T[] newArray = newArray(type, size);
		if (size > Constant.ZERO && isNotEmpty(data)) {
			System.arraycopy(data, Constant.ZERO, newArray, Constant.ZERO, Math.min(data.length, size));
		}
		return newArray;
	}

    /**
     * [生成一个新的重新设置大小的数组](Generates a new resized array)
     * @description zh - 生成一个新的重新设置大小的数组
     * @description en - Generates a new resized array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-19 20:00:12
     * @param array 原有数组
     * @param size 数组大小
     * @return java.lang.Object
     */
    public static Object resize(Object array, int size) {
		if (size < Constant.ZERO) {
			return array;
		}
		if (Constant.NULL == array) {
			return Constant.NULL;
		}
		final int length = length(array);
		final Object newArray = Array.newInstance(array.getClass().getComponentType(), size);
		if (size > Constant.ZERO && isNotEmpty(array)) {
			//noinspection SuspiciousSystemArraycopy
			System.arraycopy(array, Constant.ZERO, newArray, Constant.ZERO, Math.min(length, size));
		}
		return newArray;
	}

    /**
     * [生成一个新的重新设置大小的数组](Generates a new resized array)
     * @description zh - 生成一个新的重新设置大小的数组
     * @description en - Generates a new resized array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-19 20:02:55
     * @param buffer 原数组
     * @param size 新的数组大小
     * @return T[]
     */
    public static <T> T[] resize(T[] buffer, int size) {
		return resize(buffer, size, buffer.getClass().getComponentType());
	}
}
