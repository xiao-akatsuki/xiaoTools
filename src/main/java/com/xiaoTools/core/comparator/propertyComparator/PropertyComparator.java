package com.xiaoTools.core.comparator.propertyComparator;

import java.io.Serializable;
import java.util.Comparator;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.compareUtil.CompareUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;

/**
 * [Bean属性排序器](Bean property sorter)
 * @description zh - Bean属性排序器
 * @description en - Bean property sorter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-09-01 19:00:25
 */
public class PropertyComparator<T> implements Comparator<T>, Serializable {
    private static final long serialVersionUID = 9157326766723846313L;

    /**
     * 属性名
     */
	private final String property;

    /**
     * null值是否排在后（从小到大排序）
     */
	private final boolean isNullGreater;

    /**
     *
     * @description zh - 构造
     * @description en - structure
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-01 19:01:57
     * @param property 属性名
     */
	public PropertyComparator(String property) {
		this(property, true);
	}

    @Override
	public int compare(T o1, T o2) {
		if (o1 == o2) {
			return Constant.ZERO;
		} else if (Constant.NULL == o1) {// null 排在后面
			return isNullGreater ? Constant.ONE : Constant.NEGATIVE_ONE;
		} else if (Constant.NULL == o2) {
			return isNullGreater ? Constant.NEGATIVE_ONE : Constant.ONE;
		}

		Comparable<?> v1;
		Comparable<?> v2;
		try {
			v1 = BeanUtil.getProperty(o1, property);
			v2 = BeanUtil.getProperty(o2, property);
		} catch (Exception e) {
			throw new ComparatorException(e);
		}

		return compare(o1, o2, v1, v2);
	}

    @SuppressWarnings({"rawtypes", "unchecked"})
	private int compare(T o1, T o2, Comparable fieldValue1, Comparable fieldValue2) {
		int result = ObjectUtil.compare(fieldValue1, fieldValue2, isNullGreater);
		if(Constant.ZERO == result) {
			//避免TreeSet / TreeMap 过滤掉排序字段相同但是对象不相同的情况
			result = CompareUtil.compare(o1, o2, this.isNullGreater);
		}
		return result;
	}
}
