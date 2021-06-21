package com.xiaoTools.util.compareUtil;

import com.xiaoTools.entity.pinyinComparator.PinyinComparator;
import com.xiaoTools.lang.constant.Constant;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

/**
 * [比较工具类](Comparison tools)
 * @description: zh - 比较工具类
 * @description: en - Comparison tools
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/21 9:13 上午
*/
public class CompareUtil {
    /**
     * [对象比较](Object comparison)
     * @description: zh - 对象比较
     * @description: en - Object comparison
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 9:14 上午
     * @param c1: 对象1
     * @param c2: 对象2
     * @param comparator: 比较器
     * @return int
    */
    public static <T> int compare(T c1, T c2, Comparator<T> comparator) {
        return Constant.NULL == comparator ? compare((Comparable) c1, (Comparable) c2) : comparator.compare(c1, c2);
    }

    /**
     * [null安全的对象比较，null对象小于任何对象](Null safe object comparison, null object is smaller than any object)
     * @description: zh - null安全的对象比较，null对象小于任何对象
     * @description: en - Null safe object comparison, null object is smaller than any object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 9:16 上午
     * @param c1: 对象1
     * @param c2: 对象2
     * @return int
    */
    public static <T extends Comparable<? super T>> int compare(T c1, T c2) {
        return compare(c1, c2, Constant.FALSE);
    }

    /**
     * [null安全的对象比较](Comparison of null safe objects)
     * @description: zh - null安全的对象比较
     * @description: en - Comparison of null safe objects
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 9:20 上午
     * @param c1: 对象1，可以为null
     * @param c2: 对象1，可以为null
     * @param isNullGreater: 当被比较对象为null时是否排在前面，true表示null大于任何对象，false
     * @return int
    */
    public static <T extends Comparable<? super T>> int compare(T c1, T c2, boolean isNullGreater) {
        if (c1 == c2) {
            return Constant.ZERO;
        } else if (c1 == Constant.NULL) {
            return isNullGreater ? Constant.ONE : Constant.NEGATIVE_ONE;
        } else if (c2 == Constant.NULL) {
            return isNullGreater ? Constant.NEGATIVE_ONE : Constant.ONE;
        }
        return c1.compareTo(c2);
    }

    /**
     * [自然比较两个对象的大小](Naturally compare the size of two objects)
     * @description: zh - 自然比较两个对象的大小
     * @description: en - Naturally compare the size of two objects
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 9:24 上午
     * @param o1: 对象1
     * @param o2: 对象2
     * @param isNullGreater: null值是否做为最大值
     * @return int
    */
    public static <T> int compare(T o1, T o2, boolean isNullGreater) {
        if (o1 == o2) {
            return Constant.ZERO;
        } else if (Constant.NULL == o1) {
            // null 排在后面
            return isNullGreater ? Constant.ONE : Constant.NEGATIVE_ONE;
        } else if (Constant.NULL == o2) {
            return isNullGreater ? Constant.NEGATIVE_ONE : Constant.ONE;
        }
        if (o1 instanceof Comparable && o2 instanceof Comparable) {
            //如果bean可比较，直接比较bean
            return ((Comparable) o1).compareTo(o2);
        }
        if (o1.equals(o2)) { return Constant.ZERO; }
        int result = Integer.compare(o1.hashCode(), o2.hashCode());
        if (Constant.ZERO == result) {
            result = compare(o1.toString(), o2.toString());
        }
        return result;
    }

    /**
     * [中文比较器](Chinese comparator)
     * @description: zh - 中文比较器
     * @description: en - Chinese comparator
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 9:25 上午
     * @param keyExtractor: 从对象中提取中文(参与比较的内容)
     * @return java.util.Comparator<T>
    */
    public static <T> Comparator<T> comparingPinyin(Function<T, String> keyExtractor) {
        return comparingPinyin(keyExtractor, Constant.FALSE);
    }

    /**
     * [中文比较器](Chinese comparator)
     * @description: zh - 中文比较器
     * @description: en - Chinese comparator
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 9:25 上午
     * @param keyExtractor: 从对象中提取中文(参与比较的内容)
     * @param reverse: 是否反序
     * @return java.util.Comparator<T>
    */
    public static <T> Comparator<T> comparingPinyin(Function<T, String> keyExtractor, boolean reverse) {
        Objects.requireNonNull(keyExtractor);
        PinyinComparator pinyinComparator = new PinyinComparator();
        if (reverse) {
            return (o1, o2) -> pinyinComparator.compare(keyExtractor.apply(o2), keyExtractor.apply(o1));
        }
        return (o1, o2) -> pinyinComparator.compare(keyExtractor.apply(o1), keyExtractor.apply(o2));
    }
}
