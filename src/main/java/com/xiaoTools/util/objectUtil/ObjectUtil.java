package com.xiaoTools.util.objectUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.compareUtil.CompareUtil;
import com.xiaoTools.util.numUtil.NumUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;
import com.xiaoTools.util.strUtil.StrUtil;

import org.graalvm.compiler.phases.graph.InferStamps;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;

/**
 * [对象工具类，包括判空、克隆、序列化等操作](Object tool class, including null, clone, serialization and other operations)
 * @description: zh - 对象工具类，包括判空、克隆、序列化等操作
 * @description: en - Object tool class, including null, clone, serialization and other operations
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/13 11:03 上午
*/
public class ObjectUtil {

    /* 是否一致 -------------------------------------------------------------- equals */

    /**
     * [比较两个对象是否一致](Compare two objects for consistency)
     * @description: zh - 比较两个对象是否一致
     * @description: en - Compare two objects for consistency
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/13 3:49 下午
     * @param value1: 比较的对象1
     * @param value2: 比较的对象2
     * @return boolean
    */
    public static boolean equals(Object value1, Object value2){
        return equal(value1,value2);
    }

    /**
     * [比较两个对象是否一样](Compare two objects to see if they are the same)
     * @description: zh - 比较两个对象是否一样
     * @description: en - Compare two objects to see if they are the same
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/13 3:37 下午
     * @param obj1: 比较的对象1
     * @param obj2: 比较的对象2
     * @return boolean
    */
    public static boolean equal(Object obj1, Object obj2){
        if (obj1 instanceof BigDecimal && obj2 instanceof BigDecimal) {
            return NumUtil.equals((BigDecimal) obj1, (BigDecimal) obj2);
        }
        return Objects.equals(obj1, obj2);
    }

    /**
     * [比较两个对象是否不相等](Compare two objects to see if they are not equal)
     * @description: zh - 比较两个对象是否不相等
     * @description: en - Compare two objects to see if they are not equal
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/13 3:52 下午
     * @param value1: 比较的对象1
     * @param value2: 比较的对象2
     * @return boolean
    */
    public static boolean notEquals(Object value1, Object value2){
        return !equal(value1, value2);
    }

    /* 长度 -------------------------------------------------------------- length */
    
    /**
     * [获取对象的长度，如果是字符串调用其length函数，集合类调用其size函数，数组调用其length属性，其他可遍历对象遍历计算长度](Get the length of the object. If it is a string, call its length function, collection class call its size function, array call its length attribute, and other traversable objects traverse the length)
     * @description: zh - 获取对象的长度，如果是字符串调用其length函数，集合类调用其size函数，数组调用其length属性，其他可遍历对象遍历计算长度
     * @description: en - Get the length of the object. If it is a string, call its length function, collection class call its size function, array call its length attribute, and other traversable objects traverse the length
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/13 4:50 下午
     * @param value: 对象
     * @return int
    */
    public static int length(Object value){
        if (null == value){ return Constant.ZERO; }
        if (value instanceof CharSequence) { return ((CharSequence) value).length(); }
        if (value instanceof Collection) { return ((Collection<?>) value).size(); }
        if (value instanceof Map) { return ((Map<?, ?>) value).size(); }
        int count;
        if (value instanceof Iterator) {
            Iterator<?> iter = (Iterator<?>) value;
            count = Constant.ZERO;
            while (iter.hasNext()) {
                count++;
                iter.next();
            }
            return count;
        } else if (value instanceof Enumeration) {
            Enumeration<?> enumeration = (Enumeration<?>) value;
            count = Constant.ZERO;
            while (enumeration.hasMoreElements()) {
                count++;
                enumeration.nextElement();
            }
            return count;
        }else if (value.getClass().isArray()) {
            return Array.getLength(value);
        }
        return Constant.NEGATIVE_ONE;
    }

    /* 判断对象是否为null -------------------------------------------------------------- is NULL */

    /**
     * [判断对象是否为「null」](Judge whether the object is 「null」)
     * @description: zh - 判断对象是否为「null」
     * @description: en - Judge whether the object is 「null」
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/13 4:58 下午
     * @param value: 对象
     * @return boolean
    */
    public static boolean isNull(Object value){
        return Constant.NULL == value || value.equals(Constant.NULL);
    }

    /**
     * [判断对象是否不为「null」](Judge whether the object is not 「null」)
     * @description: zh - 判断对象是否不为「null」
     * @description: en - Judge whether the object is not 「null」
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/13 5:01 下午
     * @param value: 对象
     * @return boolean
    */
    public static boolean isNotNull(Object value){
        return !isNull(value);
    }

    /**
     * [如果给定对象为null返回默认值](Returns the default value if the given object is null)
     * @description: zh - 如果给定对象为null返回默认值
     * @description: en - Returns the default value if the given object is null
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/13 5:15 下午
     * @param object: 被检查对象，可能为null
     * @param defaultValue: 被检查对象为null返回的默认值，可以为null
     * @return T
    */
    public static <T> T defaultIfNull(final T object, final T defaultValue) {
        return (Constant.NULL != object) ? object : defaultValue;
    }

    /**
     * [如果给定对象为null返回默认值](Returns the default value if the given object is null)
     * @description: zh - 如果给定对象为null返回默认值
     * @description: en - Returns the default value if the given object is null
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-25 18:42:29
     * @param source 对象
     * @param handle 自定义的处理方法
     * @param defaultValue 默认值
     * @return T
     */
    public static <T> T defaultIfNull(Object source, Supplier<? extends T> handle, final T defaultValue) {
        return Objects.nonNull(source) ? handle.get() : defaultValue;
	}

    /* 比较 -------------------------------------------------------------- compare */

    /**
     * [null安全的对象比较，null对象排在末尾](Null safe object comparison, null objects at the end)
     * @description: zh - null安全的对象比较，null对象排在末尾
     * @description: en - Null safe object comparison, null objects at the end
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 8:06 下午
     * @param c1: 对象1，可以为null
     * @param c2: 对象2，可以为null
     * @return int
    */
    public static <T extends Comparable<? super T>> int compare(T c1, T c2) {
        return CompareUtil.compare(c1, c2);
    }

    /**
     * [null安全的对象比较](Comparison of null safe objects)
     * @description: zh - null安全的对象比较
     * @description: en - Comparison of null safe objects
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 8:08 下午
     * @param c1: 对象1，可以为null
     * @param c2: 对象2，可以为null
     * @param nullGreater: 当被比较对象为null时是否排在前面
     * @return int
    */
    public static <T extends Comparable<? super T>> int compare(T c1, T c2, boolean nullGreater) {
        return CompareUtil.compare(c1, c2, nullGreater);
    }

    /* 包含 -------------------------------------------------------------- contains */

    /** 
     * [对象中是否包含元素](Does the object contain elements)
     * @description zh - 对象中是否包含元素
     * @description en - Does the object contain elements
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-25 17:51:21
     * @param value 对象
     * @param element 元素
     * @return boolean
     */
    public static boolean contains(Object value, Object element) {
		if (value == Constant.NULL) {
			return Constant.FALSE;
		}
		if (value instanceof String) {
			if (element == Constant.NULL) {
				return Constant.FALSE;
			}
			return ((String) value).contains(element.toString());
		}
		if (value instanceof Collection) {
			return ((Collection<?>) value).contains(element);
		}
		if (value instanceof Map) {
			return ((Map<?, ?>) value).containsValue(element);
		}

		if (value instanceof Iterator) {
			Iterator<?> iter = (Iterator<?>) value;
			while (iter.hasNext()) {
				Object o = iter.next();
				if (equal(o, element)) {
					return Constant.TRUE;
				}
			}
			return Constant.FALSE;
		}
		if (value instanceof Enumeration) {
			Enumeration<?> enumeration = (Enumeration<?>) value;
			while (enumeration.hasMoreElements()) {
				Object o = enumeration.nextElement();
				if (equal(o, element)) {
					return Constant.TRUE;
				}
			}
			return Constant.FALSE;
		}
		if (value.getClass().isArray() == true) {
			int len = Array.getLength(value);
			for (int i = Constant.ZERO; i < len; i++) {
				Object o = Array.get(value, i);
				if (equal(o, element)) {
					return Constant.TRUE;
				}
			}
        }
		return Constant.FALSE;
	}

    /* 是否为空 -------------------------------------------------------------- is Empty */

    /**
     * [判断指定对象是否为空](Judge whether the specified object is empty)
     * @description zh - 判断指定对象是否为空
     * @description en - Judge whether the specified object is empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-25 18:02:53
     * @param value 对象
     * @return boolean
     */
    @SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object value) {
        return Constant.NULL == value ? Constant.TRUE : 
            value instanceof CharSequence ? StrUtil.isEmpty((CharSequence) value) : 
            value instanceof Map ? MapUtil.isEmpty((Map) value) : 
            value instanceof Iterable ? IterUtil.isEmpty((Iterable) value) :
            value instanceof Iterator ? IterUtil.isEmpty((Iterator) value) : 
            ArrayUtil.isArray(value) ? ArrayUtil.isEmpty(value) : 
            Constant.FALSE;
	}

    /**
     * [判断指定对象是否为非空](Judge whether the specified object is non empty)
     * @description zh - 判断指定对象是否为非空
     * @description en - Judge whether the specified object is non empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-25 18:37:01
     * @param value 被判断的对象
     * @return boolean
     */
    public static boolean isNotEmpty(Object value) {
		return Constant.FALSE == isEmpty(value);
	}

    /**
     * [如果给定对象为 null 或者 "" 返回默认值](If the given object is null or '' returns the default value)
     * @description zh - 如果给定对象为 null 或者 "" 返回默认值
     * @description en - If the given object is null or '' returns the default value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-25 20:14:54
     * @param value 文本
     * @param handle 自定义的处理方法
     * @param defaultValue 默认值
     * @return T
     */
    public static <T> T defaultIfEmpty(String value, Supplier<? extends T> handle, final T defaultValue) {
        return StrUtil.isNotBlank(value) ? handle.get() : defaultValue;
	}

    /**
     * [如果给定对象为 null 或者 "" 返回默认值](If the given object is null or '' returns the default value)
     * @description zh - 如果给定对象为 null 或者 "" 返回默认值
     * @description en - If the given object is null or '' returns the default value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-25 20:16:14
     * @param value 对象
     * @param defaultValue 默认值
     * @return T
     */
    public static <T extends CharSequence> T defaultIfEmpty(final T value, final T defaultValue) {
		return StrUtil.isEmpty(value) ? defaultValue : value;
	}

    /**
     * [如果给定对象为 null 或者 "" 或者 空白符 返回默认值](Returns the default value if the given object is null or empty)
     * @description zh - 如果给定对象为 null 或者 "" 或者 空白符 返回默认值
     * @description en - Returns the default value if the given object is null or empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-25 20:17:21
     * @param value 被检查对象
     * @param defaultValue 默认值
     * @return T
     */
    public static <T extends CharSequence> T defaultIfBlank(final T value, final T defaultValue) {
		return StrUtil.isBlank(value) ? defaultValue : value;
	}

    /* 克隆 -------------------------------------------------------------- clone */

    /**
     * [克隆对象](Clone object)
     * @description zh - 克隆对象
     * @description en - Clone object
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-26 18:46:37
     * @param value 对象
     * @return T
     */
    public static <T> T clone(T value) {
		T result = ArrayUtil.clone(value);
		if (Constant.NULL == result) {
            result = value instanceof Cloneable ? 
                ReflectUtil.invoke(value, "clone") : 
                cloneByStream(value);
		}
		return result;
	}

}
