package com.xiaoTools.util.objectUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.compareUtil.CompareUtil;
import com.xiaoTools.util.numUtil.NumUtil;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

/**
 * [对象工具类，包括判空、克隆、序列化等操作](Object tool class, including null, clone, serialization and other operations)
 * @description: zh - 对象工具类，包括判空、克隆、序列化等操作
 * @description: en - Object tool class, including null, clone, serialization and other operations
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/13 11:03 上午
*/
public class ObjectUtil {

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


}
