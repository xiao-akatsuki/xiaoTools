package com.xiaoTools.util.arrayUtil;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.core.editor.Editor;
import com.xiaoTools.core.exception.utilException.UtilException;
import com.xiaoTools.core.filter.Filter;
import com.xiaoTools.core.matcher.Matcher;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.compareUtil.CompareUtil;
import com.xiaoTools.util.numUtil.NumUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.primitiveArrayUtil.PrimitiveArrayUtil;
import com.xiaoTools.util.randomUtil.RandomUtil;
import com.xiaoTools.util.strUtil.StrUtil;

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

    /* 将多个数组合并在一起 -------------------------------------------------------------- addAll */

    /**
     * [将多个数组合并在一起](Merge multiple arrays together)
     * @description zh - 将多个数组合并在一起
     * @description en - Merge multiple arrays together
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-19 20:10:27
     * @param arrays 数组
     * @return T[]
     */
    @SafeVarargs
	public static <T> T[] addAll(T[]... arrays) {
		if (arrays.length == Constant.ONE) {
			return arrays[Constant.ZERO];
		}

		int length = Constant.ZERO;
		for (T[] array : arrays) {
			if (Constant.NULL != array) {
				length += array.length;
			}
		}
		T[] result = newArray(arrays.getClass().getComponentType().getComponentType(), length);
		length = Constant.ZERO;
		for (T[] array : arrays) {
			if (Constant.NULL != array) {
				System.arraycopy(array, Constant.ZERO, result, length, array.length);
				length += array.length;
			}
		}
		return result;
	}

    /* 数组复制 -------------------------------------------------------------- copy */

    /**
     * [数组复制](Array copy)
     * @description zh - 数组复制
     * @description en - Array copy
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-20 19:08:57
     * @param src 源数组
     * @param srcPos 源数组开始位置
     * @param dest 目标数组
     * @param destPos 目标数组开始位置
     * @param length 拷贝数组长度
     * @return java.lang.Object
     */
    public static Object copy(Object src, int srcPos, Object dest, int destPos, int length) {
		System.arraycopy(src, srcPos, dest, destPos, length);
		return dest;
	}

    /**
     * [数组复制](Array copy)
     * @description zh - 数组复制
     * @description en - Array copy
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-20 19:21:16
     * @param src 源数组
     * @param dest 目标数组
     * @param length 拷贝数组长度
     * @return Object
     */
    public static Object copy(Object src, Object dest, int length) {
		System.arraycopy(src, Constant.ZERO, dest, Constant.ZERO, length);
		return dest;
	}

    /* 克隆数组 -------------------------------------------------------------- clone */

    /**
     * [克隆数组](Clone array)
     * @description zh - 克隆数组
     * @description en - Clone array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-20 19:49:29
     * @param array 数组
     * @return T[]
     */
    public static <T> T[] clone(T[] array) {
        return array == Constant.NULL ? null : array.clone();
	}

    /**
     * [克隆数组](Clone array)
     * @description zh - 克隆数组
     * @description en - Clone array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-20 19:53:34
     * @param obj 数组对象
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T clone(final T obj) {
		if (Constant.NULL == obj) {
			return null;
		}
		if (isArray(obj)) {
			final Object result;
			final Class<?> componentType = obj.getClass().getComponentType();
			if (componentType.isPrimitive()) {
                // 原始类型
				int length = Array.getLength(obj);
				result = Array.newInstance(componentType, length);
				while (length-- > Constant.ZERO) {
					Array.set(result, length, Array.get(obj, length));
				}
			} else {
				result = ((Object[]) obj).clone();
			}
			return (T) result;
		}
		return null;
	}

    /* 过滤 -------------------------------------------------------------- filter */

    /**
     * [过滤](filter)
     * @description zh - 过滤
     * @description en - filter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-20 19:57:00
     * @param array 数组
     * @param editor 编辑器接口
     * @return T[]
     */
    public static <T> T[] filter(T[] array, Editor<T> editor) {
		ArrayList<T> list = new ArrayList<>(array.length);
		T modified;
		for (T t : array) {
			modified = editor.edit(t);
			if (Constant.NULL != modified) {
				list.add(modified);
			}
		}
		return list.toArray(Arrays.copyOf(array, list.size()));
	}

    /**
     * [过滤](filter)
     * @description zh - 过滤
     * @description en - filter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-20 19:58:23
     * @param array 数组
     * @param editor 编辑器接口
     */
    public static <T> void edit(T[] array, Editor<T> editor) {
		for (int i = Constant.ZERO; i < array.length; i++) {
			array[i] = editor.edit(array[i]);
		}
	}

    /**
     * [过滤](filter)
     * @description zh - 过滤
     * @description en - filter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-20 20:00:01
     * @param array 数组
     * @param filter 过滤器接口，用于定义过滤规则，null表示不过滤，返回原数组
     * @return T[]
     */
    public static <T> T[] filter(T[] array, Filter<T> filter) {
		if (Constant.NULL == filter) { return array; }
		final ArrayList<T> list = new ArrayList<>(array.length);
		for (T t : array) {
			if (filter.accept(t)) {
				list.add(t);
			}
		}
		final T[] result = newArray(array.getClass().getComponentType(), list.size());
		return list.toArray(result);
	}

    /* 去除元素 -------------------------------------------------------------- remove */

    /**
     * [去除 null 元素](Remove null elements)
     * @description zh - 去除 null 元素
     * @description en - Remove null elements
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-20 20:07:44
     * @param array 数组
     * @return T[]
     */
    public static <T> T[] removeNull(T[] array) {
		return filter(array, (Editor<T>) element -> {
			return element;
		});
	}

    /**
     * [去除 null 或者 "" 元素](Remove null or "" elements)
     * @description zh - 去除 null 或者 "" 元素
     * @description en - Remove null or "" elements
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-20 20:09:01
     * @param array 数组
     * @return T[]
     */
    public static <T extends CharSequence> T[] removeEmpty(T[] array) {
		return filter(array, StrUtil::isNotEmpty);
	}

    /**
     * [去除 null 或者 "" 或者 空白字符串 元素](Remove null or '' or blank string elements)
     * @description zh - 去除 null 或者 "" 或者 空白字符串 元素
     * @description en - Remove null or '' or blank string elements
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-20 20:10:37
     * @param array 数组
     * @return T[]
     */
    public static <T extends CharSequence> T[] removeBlank(T[] array) {
		return filter(array, StrUtil::isNotBlank);
	}

    /* XXX转换XXX -------------------------------------------------------------- XXXTOXXX */

    /**
     * [数组元素中的null转换为""](Null in array element converted to '')
     * @description zh - 数组元素中的null转换为""
     * @description en - Null in array element converted to ''
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-20 20:16:39
     * @param array 数组
     * @return java.lang.String[]
     */
    public static String[] nullToEmpty(String[] array) {
		return filter(array, (Editor<String>) element -> Constant.NULL == element ? Constant.EMPTY : element);
	}

    /* 映射键值 -------------------------------------------------------------- Mapping key value */

    /**
     * [映射键值](Mapping key value)
     * @description zh - 映射键值
     * @description en - Mapping key value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-20 20:34:54
     * @param keys 键列表
     * @param values 值列表
     * @param isOrder 是否有序
     * @return java.util.Map<K, V>
     */
    public static <K, V> Map<K, V> zip(K[] keys, V[] values, boolean isOrder) {
		if (isEmpty(keys) || isEmpty(values)) { return null; }
		final int size = Math.min(keys.length, values.length);
		final Map<K, V> map = CollUtil.newHashMap(size, isOrder);
		for (int i = Constant.ZERO; i < size; i++) {
			map.put(keys[i], values[i]);
		}
		return map;
	}

    /**
     * [映射键值](Mapping key value)
     * @description zh - 映射键值
     * @description en - Mapping key value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-20 20:36:03
     * @param keys 键列表
     * @param values 值列表
     * @return java.util.Map<K, V>
     */
    public static <K, V> Map<K, V> zip(K[] keys, V[] values) {
		return zip(keys, values, Constant.FALSE);
	}

    /* 返回数组中指定元素所在位置 -------------------------------------------------------------- index of */

    /**
     * [返回数组中指定元素所在位置](Returns the position of the specified element in the array)
     * @description zh - 返回数组中指定元素所在位置
     * @description en - Returns the position of the specified element in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 10:40:51
     * @param array 数组
     * @param value 被检查的元素
     * @return int
     */
    public static <T> int indexOf(T[] array, Object value) {
		if (Constant.NULL != array) {
			for (int i = Constant.ZERO; i < array.length; i++) {
				if (ObjectUtil.equal(value, array[i])) {
					return i;
				}
			}
		}
		return Constant.NEGATIVE_ONE;
	}

    /**
     * [返回数组中指定元素所在位置](Returns the position of the specified element in the array)
     * @description zh - 返回数组中指定元素所在位置
     * @description en - Returns the position of the specified element in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 10:54:39
     * @param array 数组
     * @param valuue 被检查的元素
     * @return int
     */
    public static int indexOfIgnoreCase(CharSequence[] array, CharSequence value) {
		if (Constant.NULL != array) {
			for (int i = Constant.ZERO; i < array.length; i++) {
				if (StrUtil.equalsIgnoreCase(array[i], value)) {
					return i;
				}
			}
		}
		return Constant.NEGATIVE_ONE;
	}

    /**
     * [返回数组中指定元素所在最后的位置](Returns the last position of the specified element in the array)
     * @description zh - 返回数组中指定元素所在最后的位置
     * @description en - Returns the last position of the specified element in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 10:56:38
     */
    public static <T> int lastIndexOf(T[] array, Object value) {
		if (Constant.NULL != array) {
			for (int i = array.length - Constant.ONE; i >= Constant.ZERO; i--) {
				if (ObjectUtil.equal(value, array[i])) {
					return i;
				}
			}
		}
		return Constant.NEGATIVE_ONE;
	}

    /* 数组中是否包含元素 -------------------------------------------------------------- contains */

    /**
     * [数组中是否包含元素](Does the array contain elements)
     * @description zh - 数组中是否包含元素
     * @description en - Does the array contain elements
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 19:32:42
     * @param array 数组
     * @param value 被检查的元素
     * @return boolean
     */
    public static <T> boolean contains(T[] array, T value) {
		return indexOf(array, value) > Constant.NEGATIVE_ONE;
	}

    /**
     * [数组中是否包含指定元素中的任意一个](Whether the array contains any of the specified elements)
     * @description zh - 数组中是否包含指定元素中的任意一个
     * @description en - Whether the array contains any of the specified elements
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 19:34:03
     * @param array 数组
     * @param values 被检查的多个元素
     * @return boolean
     */
    @SuppressWarnings("unchecked")
	public static <T> boolean containsAny(T[] array, T... values) {
		for (T value : values) {
			if (contains(array, value)) {
				return Constant.TRUE;
			}
		}
		return Constant.FALSE;
	}

    /**
     * [数组中是否包含指定元素中的全部](Whether the array contains all of the specified elements)
     * @description zh - 数组中是否包含指定元素中的全部
     * @description en - Whether the array contains all of the specified elements
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 19:35:22
     * @param array 数组
     * @param value 被检查的多个元素
     * @return boolean
     */
    @SuppressWarnings("unchecked")
	public static <T> boolean containsAll(T[] array, T... values) {
		for (T value : values) {
			if (Constant.FALSE == contains(array, value)) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}

    /**
     * [数组中是否包含元素，忽略大小写](Whether the array contains elements, ignoring case)
     * @description zh - 数组中是否包含元素，忽略大小写
     * @description en - Whether the array contains elements, ignoring case
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 19:36:59
     * @param array 数组
     * @param value 被检查的元素
     * @return boolean
     */
    public static boolean containsIgnoreCase(CharSequence[] array, CharSequence value) {
		return indexOfIgnoreCase(array, value) > Constant.NEGATIVE_ONE;
	}

    /* 包装数组对象 -------------------------------------------------------------- wrap */

    /**
     * [包装数组对象](Wrap array object)
     * @description zh - 包装数组对象
     * @description en - Wrap array object
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 19:46:52
     * @param value 对象，可以是对象数组或者基本类型数组
     * @return java.lang.Object[]
     */
    public static Object[] wrap(Object value) {
		if (Constant.NULL == value) {
			return null;
		}
		if (isArray(value)) {
			try {
				return (Object[]) value;
			} catch (Exception e) {
				final String className = value.getClass().getComponentType().getName();
				switch (className) {
					case "long":
						return wrap((long[]) value);
					case "int":
						return wrap((int[]) value);
					case "short":
						return wrap((short[]) value);
					case "char":
						return wrap((char[]) value);
					case "byte":
						return wrap((byte[]) value);
					case "boolean":
						return wrap((boolean[]) value);
					case "float":
						return wrap((float[]) value);
					case "double":
						return wrap((double[]) value);
					default:
						throw new UtilException(e);
				}
			}
		}
		throw new UtilException(StrUtil.format("[{}] is not Array!", value.getClass()));
	}

    /* 对象是否为数组对象 -------------------------------------------------------------- is Array */

    /**
     * [对象是否为数组对象](Is the object an array object)
     * @description zh - 对象是否为数组对象
     * @description en - Is the object an array object
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 19:48:18
     * @param value 对象
     * @return boolean
     */
    public static boolean isArray(Object value) {
		return Constant.NULL != value && value.getClass().isArray();
	}

    /* 获取下标的值 -------------------------------------------------------------- get */

    /**
     * [获取数组对象中指定index的值](Gets the value of the specified index in the array object)
     * @description zh - 获取数组对象中指定index的值
     * @description en - Gets the value of the specified index in the array object
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 19:51:09
     * @param array 数组
     * @param index 下标
     * @return T
     */
    @SuppressWarnings("unchecked")
	public static <T> T get(Object array, int index) {
		if (Constant.NULL == array) {
			return null;
		}

		if (index < Constant.ZERO) {
			index += Array.getLength(array);
		}
		try {
			return (T) Array.get(array, index);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

    /**
     * [获取数组中指定多个下标元素值，组成新数组](Get the values of multiple subscript elements specified in the array to form a new array)
     * @description zh - 获取数组中指定多个下标元素值，组成新数组
     * @description en - Get the values of multiple subscript elements specified in the array to form a new array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 19:52:14
     * @param array 数组
     * @param indexes 下标列表
     * @return T[]
     */
    public static <T> T[] getAny(Object array, int... indexes) {
		if (Constant.NULL == array) {
			return null;
		}

		final T[] result = newArray(array.getClass().getComponentType(), indexes.length);
		for (int i : indexes) {
			result[i] = get(array, i);
		}
		return result;
	}

    /* 获取子数组 -------------------------------------------------------------- subarray */

    /**
     * [获取子数组](Get subarray)
     * @description zh - 获取子数组
     * @description en - Get subarray
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 19:54:41
     * @param array 数组
     * @param start 开始的位置
     * @param end 结束的位置
     * @return T[]
     */
    public static <T> T[] subarray(T[] array, int start, int end) {
		int length = length(array);
		if (start < Constant.ZERO) {
			start += length;
		}
		if (end < Constant.ZERO) {
			end += length;
		}
		if (start == length) {
			return newArray(array.getClass().getComponentType(), Constant.ZERO);
		}
		if (start > end) {
			int tmp = start;
			start = end;
			end = tmp;
		}
		if (end > length) {
			if (start >= length) {
				return newArray(array.getClass().getComponentType(), Constant.ZERO);
			}
			end = length;
		}
		return Arrays.copyOfRange(array, start, end);
	}

    /**
     * [获取子数组](Get subarray)
     * @description zh - 获取子数组
     * @description en - Get subarray
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 19:56:53
     * @param array 数组
     * @param start 开始的位置
     * @param end 结束的位置
     * @return java.lang.Object[]
     */
    public static Object[] subarray(Object array, int start, int end) {
		return subarray(array, start, end, Constant.ONE);
	}

    /**
     * [获取子数组](Get subarray)
     * @description zh - 获取子数组
     * @description en - Get subarray
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 19:58:33
     * @param array 数组
     * @param start 开始的位置
     * @param end 结束的位置
     * @param step 步长
     * @return java.lang.Object[]
     */
    public static Object[] subarray(Object array, int start, int end, int step) {
		int length = length(array);
		if (start < Constant.ZERO) {
			start += length;
		}
		if (end < Constant.ZERO) {
			end += length;
		}
		if (start == length) {
			return new Object[Constant.ZERO];
		}
		if (start > end) {
			int tmp = start;
			start = end;
			end = tmp;
		}
		if (end > length) {
			if (start >= length) {
				return new Object[Constant.ZERO];
			}
			end = length;
		}

		if (step <= Constant.ONE) {
			step = Constant.ONE;
		}

		final ArrayList<Object> list = new ArrayList<>();
		for (int i = start; i < end; i += step) {
			list.add(get(array, i));
		}

		return list.toArray();
	}

    /* 数组或集合转String -------------------------------------------------------------- toString */

    /**
     * [数组或集合转String](Array or collection to string)
     * @description zh - 数组或集合转String
     * @description en - Array or collection to string
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 20:01:30
     * @param value 集合或数组对象
     * @return java.lang.String
     */
    public static String toString(Object value) {
		if (Constant.NULL == value) {
			return Constant.STRING_NULL;
		}

		if (value instanceof long[]) {
			return Arrays.toString((long[]) value);
		} else if (value instanceof int[]) {
			return Arrays.toString((int[]) value);
		} else if (value instanceof short[]) {
			return Arrays.toString((short[]) value);
		} else if (value instanceof char[]) {
			return Arrays.toString((char[]) value);
		} else if (value instanceof byte[]) {
			return Arrays.toString((byte[]) value);
		} else if (value instanceof boolean[]) {
			return Arrays.toString((boolean[]) value);
		} else if (value instanceof float[]) {
			return Arrays.toString((float[]) value);
		} else if (value instanceof double[]) {
			return Arrays.toString((double[]) value);
		} else if (ArrayUtil.isArray(value)) {
			try {
				return Arrays.deepToString((Object[]) value);
			} catch (Exception ignore) {  }
		}

		return value.toString();
	}

    /* 获取数组长度 -------------------------------------------------------------- length */

    /**
     * [获取数组长度](Get array length)
     * @description zh - 获取数组长度
     * @description en - Get array length
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-21 20:05:56
     * @param array 数组
     * @return int
     */
    public static int length(Object array) throws IllegalArgumentException {
        return Constant.NULL == array ? Constant.ZERO : Array.getLength(array);
	}

    /* 分隔符将数组转换为字符串 -------------------------------------------------------------- join */

    /**
     * [以 conjunction 为分隔符将数组转换为字符串](Converts an array to a string with a conjunction as a delimiter)
     * @description zh - 以 conjunction 为分隔符将数组转换为字符串
     * @description en - Converts an array to a string with a conjunction as a delimiter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-22 16:55:02
     * @param array 数组
     * @param conjunction 分隔符
     * @return java.lang.String
     */
    public static <T> String join(T[] array, CharSequence conjunction) {
		return join(array, conjunction, Constant.NULL, Constant.NULL);
	}

    /**
     * [以 conjunction 为分隔符将数组转换为字符串](Converts an array to a string with a conjunction as a delimiter)
     * @description zh - 以 conjunction 为分隔符将数组转换为字符串
     * @description en - Converts an array to a string with a conjunction as a delimiter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-22 17:00:24
	 * @param array 数组
	 * @param conjunction 分隔符
	 * @param prefix 每个元素添加的前缀，null表示不添加
	 * @param suffix 每个元素添加的后缀，null表示不添加
     * @return java.lang.String
     */
    public static <T> String join(T[] array, CharSequence conjunction, String prefix, String suffix) {
		if (Constant.NULL == array) {
			return Constant.STRING_NULL;
		}

		final StringBuilder sb = new StringBuilder();
		boolean isFirst = Constant.TRUE;
		for (T item : array) {
			if (isFirst) {
				isFirst = Constant.FALSE;
			} else {
				sb.append(conjunction);
			}
			if (ArrayUtil.isArray(item)) {
				sb.append(join(ArrayUtil.wrap(item), conjunction, prefix, suffix));
			} else if (item instanceof Iterable<?>) {
				sb.append(CollUtil.join((Iterable<?>) item, conjunction, prefix, suffix));
			} else if (item instanceof Iterator<?>) {
				sb.append(IterUtil.join((Iterator<?>) item, conjunction, prefix, suffix));
			} else {
				sb.append(StrUtil.wrap(StrUtil.toString(item), prefix, suffix));
			}
		}
		return sb.toString();
	}

    /**
     * [以 conjunction 为分隔符将数组转换为字符串](Converts an array to a string with a conjunction as a delimiter)
     * @description zh - 以 conjunction 为分隔符将数组转换为字符串
     * @description en - Converts an array to a string with a conjunction as a delimiter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-22 17:04:06
     * @param array 数组
     * @param conjunction 分隔符
     * @param editor 每个元素的编辑器，null表示不编辑
     * @return java.lang.String
     */
    public static <T> String join(T[] array, CharSequence conjunction, Editor<T> editor) {
		if (Constant.NULL == array) {
			return Constant.STRING_NULL;
		}

		final StringBuilder sb = new StringBuilder();
		boolean isFirst = Constant.TRUE;
		for (T item : array) {
			if (isFirst) {
				isFirst = Constant.FALSE;
			} else {
				sb.append(conjunction);
			}
			if (Constant.NULL != editor) {
				item = editor.edit(item);
			}
			if (Constant.NULL != item) {
				sb.append(StrUtil.toString(item));
			}
		}
		return sb.toString();
	}

    /**
     * [以 conjunction 为分隔符将数组转换为字符串](Converts an array to a string with a conjunction as a delimiter)
     * @description zh - 以 conjunction 为分隔符将数组转换为字符串
     * @description en - Converts an array to a string with a conjunction as a delimiter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-22 17:06:53
     * @param array 数组
     * @param conjunction 分隔符
     * @return java.lang.String
     */
    public static String join(long[] array, CharSequence conjunction) {
		if (Constant.NULL == array) {
			return Constant.STRING_NULL;
		}

		final StringBuilder sb = new StringBuilder();
		boolean isFirst = Constant.TRUE;
		for (long item : array) {
			if (isFirst) {
				isFirst = Constant.FALSE;
			} else {
				sb.append(conjunction);
			}
			sb.append(item);
		}
		return sb.toString();
	}

    /**
     * [以 conjunction 为分隔符将数组转换为字符串](Converts an array to a string with a conjunction as a delimiter)
     * @description zh - 以 conjunction 为分隔符将数组转换为字符串
     * @description en - Converts an array to a string with a conjunction as a delimiter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-22 17:11:35
     * @param array 数组
     * @param conjunction 分隔符
     * @return java.lang.String
     */
    public static String join(Object array, CharSequence conjunction) {
		if(Constant.NULL == array){
			throw new NullPointerException("Array must be not null!");
		}
		if (Constant.FALSE == isArray(array)) {
			throw new IllegalArgumentException(StrUtil.format("[{}] is not a Array!", array.getClass()));
		}

		final Class<?> componentType = array.getClass().getComponentType();
		if (componentType.isPrimitive()) {
			final String componentTypeName = componentType.getName();
			switch (componentTypeName) {
				case "long":
					return join((long[]) array, conjunction);
				case "int":
					return join((int[]) array, conjunction);
				case "short":
					return join((short[]) array, conjunction);
				case "char":
					return join((char[]) array, conjunction);
				case "byte":
					return join((byte[]) array, conjunction);
				case "boolean":
					return join((boolean[]) array, conjunction);
				case "float":
					return join((float[]) array, conjunction);
				case "double":
					return join((double[]) array, conjunction);
				default:
					throw new UtilException("Unknown primitive type: [{}]", componentTypeName);
			}
		} else {
			return join((Object[]) array, conjunction);
		}
	}

    /* 转数组 -------------------------------------------------------------- to array */

    /**
     * [ByteBuffer 转byte数组](Byte buffer to byte array)
     * @description zh - ByteBuffer 转byte数组
     * @description en - Byte buffer to byte array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 14:58:19
     * @param bytebuffer ByteBuffer
     * @return byte[]
     */
    public static byte[] toArray(ByteBuffer bytebuffer) {
		if (bytebuffer.hasArray()) {
			return Arrays.copyOfRange(bytebuffer.array(), bytebuffer.position(), bytebuffer.limit());
		} else {
			int oldPosition = bytebuffer.position();
			bytebuffer.position(Constant.ZERO);
			byte[] buffers = new byte[bytebuffer.limit()];
			bytebuffer.get(buffers);
			bytebuffer.position(oldPosition);
			return buffers;
		}
	}

    /**
     * [将集合转为数组](Convert collection to array)
     * @description zh - 将集合转为数组
     * @description en - Convert collection to array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 15:00:24
     * @param iterator Iterator
     * @param componentType 集合元素类型
     * @return T[]
     */
    public static <T> T[] toArray(Iterator<T> iterator, Class<T> componentType) {
		return toArray(CollUtil.newArrayList(iterator), componentType);
	}

    /**
     * [将集合转为数组](Convert collection to array)
     * @description zh - 将集合转为数组
     * @description en - Convert collection to array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 15:02:06
     * @param iterator Iterator
     * @param componentType 集合元素类型
     * @return T[]
     */
    public static <T> T[] toArray(Iterable<T> iterable, Class<T> componentType) {
		return toArray(CollectionUtil.toCollection(iterable), componentType);
	}

    /**
     * [将集合转为数组](Convert collection to array)
     * @description zh - 将集合转为数组
     * @description en - Convert collection to array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 15:08:44
     * @param collection 集合
     * @param componentType 集合元素类型
     * @return T[] 
     */
    public static <T> T[] toArray(Collection<T> collection, Class<T> componentType) {
		return collection.toArray(newArray(componentType, 0));
	}

    /* 移除 -------------------------------------------------------------- remove */

    /**
     * [移除数组中对应位置的元素](Remove the element at the corresponding position in the array)
     * @description zh - 移除数组中对应位置的元素
     * @description en - Remove the element at the corresponding position in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 15:14:36
     * @param array 数组
     * @param index 位置
     * @return T[]
     */
    @SuppressWarnings("unchecked")
	public static <T> T[] remove(T[] array, int index) throws IllegalArgumentException {
		return (T[]) remove((Object) array, index);
	}

    /**
     * [移除数组中指定的元素](Removes the specified element from the array)
     * @description zh - 移除数组中指定的元素
     * @description en - Removes the specified element from the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 15:17:57
     * @param array 数组
     * @param element 需要删除的元素
     * @return T[]
     */
    public static <T> T[] remove(T[] array, T element) throws IllegalArgumentException {
		return remove(array, indexOf(array, element));
	}

    /* 反转数组 -------------------------------------------------------------- reverse */

    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 17:27:42
     * @param array 数组
     * @param state 开始位置
     * @param end 结束位置
     * @return T[]
     */
    public static <T> T[] reverse(T[] array, final int state, final int end) {
		if (isEmpty(array)) {
			return array;
		}
		int i = NumUtil.max(state, Constant.ZERO);
		int j = NumUtil.min(array.length, end) - Constant.ONE;
		T tmp;
		while (j > i) {
			tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			j--;
			i++;
		}
		return array;
	}

    /**
     * [反转数组](Invert array)
     * @description zh - 反转数组
     * @description en - Invert array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 17:28:59
     * @param array 数组
     * @return T[]
     */
    public static <T> T[] reverse(T[] array) {
		return reverse(array, Constant.ZERO, array.length);
	}

    /* 取最小值 -------------------------------------------------------------- min */

    /**
     * [取最小值](Take the minimum value)
     * @description zh - 取最小值
     * @description en - Take the minimum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 18:04:47
     * @param array 数组
     * @return T
     */ 
    public static <T extends Comparable<? super T>> T min(T[] array) {
		return min(array, null);
	}

    /**
     * [取最小值](Take the minimum value)
     * @description zh - 取最小值
     * @description en - Take the minimum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 18:06:30
     * @param array 数组
     * @param comparator 比较器，null按照默认比较
     * @return T
     */
    public static <T extends Comparable<? super T>> T min(T[] array, Comparator<T> comparator) {
		if (isEmpty(array)) {
			throw new IllegalArgumentException("Number array must not empty !");
		}
		T min = array[Constant.ZERO];
		for (T t : array) {
			if (CompareUtil.compare(min, t, comparator) > Constant.ZERO) {
				min = t;
			}
		}
		return min;
	}

    /* 取最大值 -------------------------------------------------------------- max */

    /**
     * [取最大值](Take the maximum value)
     * @description zh - 取最大值
     * @description en - Take the maximum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 18:09:10
     * @param array 数组
     * @return T
     */
    public static <T extends Comparable<? super T>> T max(T[] array) {
		return max(array, null);
	}

    /**
     * [取最大值](Take the maximum value)
     * @description zh - 取最大值
     * @description en - Take the maximum value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 18:11:47
     * @param array 数组
     * @param comparator 比较器，null按照默认比较
     * @return T
     */
    public static <T extends Comparable<? super T>> T max(T[] array, Comparator<T> comparator) {
		if (isEmpty(array)) {
			throw new IllegalArgumentException("Number array must not empty !");
		}
		T max = array[Constant.ZERO];
		for (T t : array) {
			if (CompareUtil.compare(max, t, comparator) < Constant.ZERO) {
				max = t;
			}
		}
		return max;
	}

    /* 打乱数组顺序 -------------------------------------------------------------- disorder */

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 18:22:04
     * @param array 数组
     * @return T[]
     */
    public static <T> T[] disorder(T[] array) {
		return disorder(array, RandomUtil.getRandom());
	}

    /**
     * [打乱数组顺序](Disorder array order)
     * @description zh - 打乱数组顺序
     * @description en - Disorder array order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 18:24:35
     * @param array  数组
     * @param random 随机数生成器
     * @return T[]
     */
    public static <T> T[] disorder(T[] array, Random random) {
		if (array == Constant.NULL || random == Constant.NULL || array.length <= Constant.ONE) {
			return array;
		}

		for (int i = array.length; i > Constant.ONE; i--) {
			swap(array, i - 1, random.nextInt(i));
		}

		return array;
	}

    /* 交换数组中两个位置的值 -------------------------------------------------------------- swap */

    /**
     * [交换数组中两个位置的值](Swap the values of two positions in the array)
     * @description zh - 交换数组中两个位置的值
     * @description en - Swap the values of two positions in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 18:56:26
     * @param array 数组
     * @param index1 位置1
     * @param index2 位置2
     * @return T[]
     */
    public static <T> T[] swap(T[] array, int index1, int index2) {
		if (isEmpty(array)) {
			throw new IllegalArgumentException("Array must not empty !");
		}
		T tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
		return array;
	}

    /**
     * [交换数组中两个位置的值](Swap the values of two positions in the array)
     * @description zh - 交换数组中两个位置的值
     * @description en - Swap the values of two positions in the array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 18:58:42
     * @param array 数组
     * @param index1 位置1
     * @param index2 位置2
     * @return java.lang.Object
     */
    public static Object swap(Object array, int index1, int index2) {
		if (isEmpty(array)) {
			throw new IllegalArgumentException("Array must not empty !");
		}
		Object tmp = get(array, index1);
		Array.set(array, index1, Array.get(array, index2));
		Array.set(array, index2, tmp);
		return array;
	}

    /* 是否为空 -------------------------------------------------------------- empty */

    /**
     * [计算 null 或 空元素对象 的个数](Count the number of null or empty element objects)
     * @description zh - 计算 null 或 空元素对象 的个数
     * @description en - Count the number of null or empty element objects
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 19:02:23
     * @param array 数组
     * @return int
     */
    public static int emptyCount(Object... array) {
		int count = Constant.ZERO;
		if (isNotEmpty(array)) {
			for (Object element : array) {
				if (ObjectUtil.isEmpty(element)) {
					count++;
				}
			}
		}
		return count;
	}

    /**
     * [是否存在 null 或 空对象](Is there a null or empty object)
     * @description zh - 是否存在 null 或 空对象
     * @description en - Is there a null or empty object
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 19:04:26
     * @param array 数组
     * @return boolean
     */
    public static boolean hasEmpty(Object... array) {
		if (isNotEmpty(array)) {
			for (Object element : array) {
				if (ObjectUtil.isEmpty(element)) {
					return Constant.TRUE;
				}
			}
		}
		return Constant.FALSE;
	}

    /**
     * [是否存在都为 null或 空对象](Whether there are null or empty objects)
     * @description zh - 是否存在都为 null或 空对象
     * @description en - Whether there are null or empty objects
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 19:06:07
     * @param array 数组
     * @return boolean
     */
    public static boolean isAllEmpty(Object... array) {
		return emptyCount(array) == array.length;
	}

    /**
     * [是否存在都不为 null 或 空对象](Whether there are objects that are not null or empty)
     * @description zh - 是否存在都不为 null 或 空对象
     * @description en - Whether there are objects that are not null or empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 19:09:53
     * @param array 数组
     * @return boolean
     */
    public static boolean isAllNotEmpty(Object... array) {
		return Constant.FALSE == hasEmpty(array);
	}

    /**
     * [多个字段是否全部不为 null](Are multiple fields all non null)
     * @description zh - 多个字段是否全部不为 null
     * @description en - Are multiple fields all non null
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-23 19:11:33
     * @param array 数组
     * @return boolean
     */
    @SuppressWarnings("unchecked")
	public static <T> boolean isAllNotNull(T... array) {
		return Constant.FALSE == hasNull(array);
	}

    /* 去重 -------------------------------------------------------------- distinct */

    /**
     * [去重数组中的元素](De duplication of elements in an array)
     * @description zh - 去重数组中的元素
     * @description en - De duplication of elements in an array
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-24 19:31:24
     * @param array 数组
     * @return T[]
     */
    @SuppressWarnings("unchecked")
	public static <T> T[] distinct(T[] array) {
		if (isEmpty(array)) {
			return array;
		}

		final Set<T> set = new LinkedHashSet<>(array.length, Constant.ONE);
		Collections.addAll(set, array);
		return toArray(set, (Class<T>) getComponentType(array));
	}

    /* 转换 -------------------------------------------------------------- map */        

    /**
     * [一种类型的数组转换为另一种类型](Convert an array of one type to another)
     * @description zh - 一种类型的数组转换为另一种类型
     * @description en - Convert an array of one type to another
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-24 19:34:05
     * @param array 被转换的数组
     * @param type 目标的元素类型
     * @param func 转换规则函数
     * @return R[]
     */
    public static <T, R> R[] map(T[] array, Class<R> type, Function<? super T, ? extends R> func) {
		final R[] result = newArray(type, array.length);
		for (int i = Constant.ZERO; i < array.length; i++) {
			result[i] = func.apply(array[i]);
		}
		return result;
	}

    /**
     * [一种类型的数组转换为另一种类型](Convert an array of one type to another)
     * @description zh - 一种类型的数组转换为另一种类型
     * @description en - Convert an array of one type to another
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-24 19:35:41
     * @param array 被转换的数组
     * @param type 目标的元素类型
     * @param func 转换规则函数
     * @return R[]
     */
    public static <T, R> R[] map(Object array, Class<R> type, Function<? super T, ? extends R> func) {
		final int length = length(array);
		final R[] result = newArray(type, length);
		for (int i = Constant.ZERO; i < length; i++) {
			result[i] = func.apply(get(array, i));
		}
		return result;
	}

    /**
     * [一种类型的数组转换为另一种类型](Convert an array of one type to another)
     * @description zh - 一种类型的数组转换为另一种类型
     * @description en - Convert an array of one type to another
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-24 19:36:43
     * @param array 被转换的数组
     * @param func 转换规则函数
     * @return java.util.List<R>
     */
    public static <T, R> List<R> map(T[] array, Function<? super T, ? extends R> func) {
		return Arrays.stream(array).map(func).collect(Collectors.toList());
	}

    /* 相等 -------------------------------------------------------------- equals */        

    /**
     * [判断两个数组是否相等，判断依据包括数组长度和每个元素都相等。](Judge whether the two arrays are equal. The judgment basis includes the length of the array and the equality of each element.)
     * @description zh - 判断两个数组是否相等，判断依据包括数组长度和每个元素都相等。
     * @description en - Judge whether the two arrays are equal. The judgment basis includes the length of the array and the equality of each element.
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-24 19:47:16
     * @param array1 数组1
     * @param array2 数组2
     * @return boolean
     */
    public static boolean equals(Object array1, Object array2) {
		if (array1 == array2) {
			return Constant.TRUE;
		}
		if (hasNull(array1, array2)) {
			return Constant.FALSE;
		}

		Assertion.isTrue(isArray(array1), "First is not a Array !");
		Assertion.isTrue(isArray(array2), "Second is not a Array !");

		if (array1 instanceof long[]) {
			return Arrays.equals((long[]) array1, (long[]) array2);
		} else if (array1 instanceof int[]) {
			return Arrays.equals((int[]) array1, (int[]) array2);
		} else if (array1 instanceof short[]) {
			return Arrays.equals((short[]) array1, (short[]) array2);
		} else if (array1 instanceof char[]) {
			return Arrays.equals((char[]) array1, (char[]) array2);
		} else if (array1 instanceof byte[]) {
			return Arrays.equals((byte[]) array1, (byte[]) array2);
		} else if (array1 instanceof double[]) {
			return Arrays.equals((double[]) array1, (double[]) array2);
		} else if (array1 instanceof float[]) {
			return Arrays.equals((float[]) array1, (float[]) array2);
		} else if (array1 instanceof boolean[]) {
			return Arrays.equals((boolean[]) array1, (boolean[]) array2);
		} else {
			// Not an array of primitives
			return Arrays.deepEquals((Object[]) array1, (Object[]) array2);
		}
	}

    /* 自数组 -------------------------------------------------------------- find */        

    /**
     * [查找子数组的位置](Find the location of the subarray)
     * @description zh - 查找子数组的位置
     * @description en - Find the location of the subarray
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-24 19:56:52
     * @param array 数组
     * @param subArray 子数组
     * @return boolean
     */
    public static <T> boolean isSubArray(T[] array, T[] subArray) {
		return indexOfSubArray(array, subArray) > Constant.NEGATIVE_ONE;
	}

    /**
     * [查找子数组的位置](Find the location of the subarray)
     * @description zh - 查找子数组的位置
     * @description en - Find the location of the subarray
     * @description en - 
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-24 20:04:26
     * @param array 数组
     * @param subArray 子数组
     * @return int
     */
    public static <T> int indexOfSubArray(T[] array, T[] subArray) {
		if (isEmpty(array) || isEmpty(subArray) || subArray.length > array.length) {
			return Constant.NEGATIVE_ONE;
		}
		int firstIndex = indexOf(array, subArray[Constant.ZERO]);
		if (firstIndex < Constant.ZERO || firstIndex + subArray.length > array.length) {
			return Constant.NEGATIVE_ONE;
		}

		for (int i = Constant.ZERO; i < subArray.length; i++) {
			if (Constant.FALSE == ObjectUtil.equal(array[i + firstIndex], subArray[i])) {
				return Constant.NEGATIVE_ONE;
			}
		}

		return firstIndex;
	}

    /**
     * [查找最后一个子数组的开始位置](Find the start position of the last subarray)
     * @description zh - 查找最后一个子数组的开始位置
     * @description en - Find the start position of the last subarray
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-24 20:08:54
     * @param array 数组
     * @param subArray 子数组
     * @return int
     */
    public static <T> int lastIndexOfSubArray(T[] array, T[] subArray) {
		if (isEmpty(array) || isEmpty(subArray) || subArray.length > array.length) {
			return Constant.NEGATIVE_ONE;
		}

		int firstIndex = lastIndexOf(array, subArray[Constant.ZERO]);
		if (firstIndex < Constant.ZERO || firstIndex + subArray.length > array.length) {
			return Constant.NEGATIVE_ONE;
		}

		for (int i = Constant.ZERO; i < subArray.length; i++) {
			if ( Constant.FALSE == ObjectUtil.equal(array[i + firstIndex], subArray[i])) {
				return Constant.NEGATIVE_ONE;
			}
		}

		return firstIndex;
	}

    /* 排序 -------------------------------------------------------------- sort */        

    /**
     * [检查数组是否有序](Check whether the array is ordered)
     * @description zh - 检查数组是否有序
     * @description en - Check whether the array is ordered
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-24 20:17:43
     * @param array 数组
     * @param comparator 比较器
     * @return boolean
     */
    public static <T> boolean isSorted(T[] array, Comparator<? super T> comparator) {
		if (array == Constant.NULL || comparator == Constant.NULL) {
			return Constant.FALSE;
		}

		for (int i = Constant.ZERO; i < array.length - Constant.ONE; i++) {
			if (comparator.compare(array[i], array[i + Constant.ONE]) > Constant.ONE) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}

    /**
     * [检查数组是否升序](Check whether the array is in ascending order)
     * @description zh - 检查数组是否升序
     * @description en - Check whether the array is in ascending order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-24 20:18:54
     * @param array 数组
     * @return boolean
     */
    public static <T extends Comparable<? super T>> boolean isSorted(T[] array) {
		return isSortedASC(array);
	}

    /**
     * [检查数组是否升序](Check whether the array is in ascending order)
     * @description zh - 检查数组是否升序
     * @description en - Check whether the array is in ascending order
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-24 20:20:33
     * @param array 数组
     * @return boolean
     */
    public static <T extends Comparable<? super T>> boolean isSortedASC(T[] array) {
		if (array == Constant.NULL) {
			return Constant.FALSE;
		}

		for (int i = Constant.ZERO; i < array.length - Constant.ONE; i++) {
			if (array[i].compareTo(array[i + Constant.ONE]) > Constant.ZERO) {
				return Constant.FALSE;
			}
		}

		return Constant.TRUE;
	}
}