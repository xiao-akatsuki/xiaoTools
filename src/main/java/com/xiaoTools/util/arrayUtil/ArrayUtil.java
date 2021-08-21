package com.xiaoTools.util.arrayUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import com.xiaoTools.core.editor.Editor;
import com.xiaoTools.core.filter.Filter;
import com.xiaoTools.core.matcher.Matcher;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.numUtil.NumUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.primitiveArrayUtil.PrimitiveArrayUtil;
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
				return true;
			}
		}
		return false;
	}

    
}