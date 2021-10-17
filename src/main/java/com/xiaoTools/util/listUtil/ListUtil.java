package com.xiaoTools.util.listUtil;

import com.xiaoTools.core.comparator.propertyComparator.PropertyComparator;
import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.editor.Editor;
import com.xiaoTools.core.matcher.Matcher;
import com.xiaoTools.entity.pinyinComparator.PinyinComparator;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * [List工具类](List tool class)
 * @description: zh - List工具类
 * @description: en - List tool class
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/24 5:27 下午
*/
public class ListUtil {

    /*新建集合-----------------------------------------------------------new list*/

    /**
     * [新建一个空 List](Create a new empty List)
     * @description: zh - 新建一个空 List
     * @description: eb - Create a new empty List
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 5:28 下午
     * @param isLinked: 是否新建LinkedList
     * @return java.util.List<T>
    */
    public static <T> List<T> list(boolean isLinked) {
        return isLinked ? new LinkedList<>() : new ArrayList<>();
    }

    /**
     * [新建一个List](Create a new List)
     * @description: zh - 新建一个List
     * @description: en - Create a new List
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 5:29 下午
     * @param isLinked: 是否新建LinkedList
     * @param values: 数组
     * @return java.util.List<T>
    */
	@SafeVarargs
    public static <T> List<T> list(boolean isLinked, T... values) {
        if (ArrayUtil.isEmpty(values)) {
            return list(isLinked);
        }
        final List<T> arrayList = isLinked ? new LinkedList<>() : new ArrayList<>(values.length);
        Collections.addAll(arrayList, values);
        return arrayList;
    }

    /**
     * [新建一个List](Create a new list)
     * @description: zh - 新建一个List
     * @description: en - Create a new list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 5:36 下午
     * @param isLinked: 是否新建LinkedList
     * @param collection: 集合
     * @return java.util.List<T>
    */
    public static <T> List<T> list(boolean isLinked, Collection<T> collection) {
        return Constant.NULL == collection ? list(isLinked) : isLinked ? new LinkedList<>(collection) : new ArrayList<>(collection);
    }

    /**
     * [新建一个List](Create a new list)
     * @description: zh - 新建一个List
     * @description: en - Create a new list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 5:46 下午
     * @param isLinked:是否新建LinkedList
     * @param iterable: Iterable
     * @return java.util.List<T>
    */
    public static <T> List<T> list(boolean isLinked, Iterable<T> iterable) {
        return Constant.NULL == iterable ? list(isLinked) : list(isLinked, iterable.iterator());
    }

    /**
     * [新建一个List](Create a new list)
     * @description: zh - 新建一个List
     * @description: en - Create a new list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 5:49 下午
     * @param isLinked: 是否新建LinkedList
     * @param iter: Iterator
     * @return java.util.List<T>
    */
    public static <T> List<T> list(boolean isLinked, Iterator<T> iter) {
        final List<T> list = list(isLinked);
        if (Constant.NULL != iter) {
            while (iter.hasNext()) {
                list.add(iter.next());
            }
        }
        return list;
    }

    /**
     * [新建一个List](Create a new list)
     * @description: zh - 新建一个List
     * @description: en - Create a new list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 5:49 下午
     * @param isLinked: 是否新建LinkedList
     * @param enumration: Enumeration
     * @return java.util.List<T>
    */
    public static <T> List<T> list(boolean isLinked, Enumeration<T> enumration) {
        final List<T> list = list(isLinked);
        if (Constant.NULL != enumration) {
            while (enumration.hasMoreElements()) {
                list.add(enumration.nextElement());
            }
        }
        return list;
    }

    /*新建集合-----------------------------------------------------------new Arraylist*/

    /**
     * [新建一个 ArrayList](Create a new ArrayList)
     * @description: zh - 新建一个 ArrayList
     * @description: en - Create a new ArrayList
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 5:52 下午
     * @param values: 数组
     * @return java.util.ArrayList<T>
    */
	@SafeVarargs
    public static <T> ArrayList<T> toList(T... values) {
        return (ArrayList<T>) list(Constant.FALSE, values);
    }

    /**
     * [新建一个ArrayList](Create a new ArrayList)
     * @description: zh - 新建一个ArrayList
     * @description: en - Create a new ArrayList
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 6:05 下午
     * @param collection: 集合
     * @return java.util.ArrayList<T>
    */
    public static <T> ArrayList<T> toList(Collection<T> collection) {
        return (ArrayList<T>) list(Constant.FALSE, collection);
    }

    /**
     * [新建一个ArrayList](Create a new ArrayList)
     * @description: zh - 新建一个ArrayList
     * @description: en - Create a new ArrayList
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 6:39 下午
     * @param iterable: Iterable
     * @return java.util.ArrayList<T>
    */
    public static <T> ArrayList<T> toList(Iterable<T> iterable) {
        return (ArrayList<T>) list(Constant.FALSE, iterable);

    }

    /**
     * [新建一个ArrayList](Create a new ArrayList)
     * @description: zh - 新建一个ArrayList
     * @description: en - Create a new ArrayList
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 6:40 下午
     * @param iterator: Iterator
     * @return java.util.ArrayList<T>
    */
    public static <T> ArrayList<T> toList(Iterator<T> iterator) {
        return (ArrayList<T>) list(Constant.FALSE, iterator);
    }

    /**
     * [新建一个ArrayList](Create a new ArrayList)
     * @description: zh - 新建一个ArrayList
     * @description: en - Create a new ArrayList
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 6:42 下午
     * @param enumeration: Enumeration
     * @return java.util.ArrayList<T>
    */
    public static <T> ArrayList<T> toList(Enumeration<T> enumeration) {
        return (ArrayList<T>) list(Constant.FALSE, enumeration);
    }

    /**
     * [新建 LinkedList](New LinkedList)
     * @description: zh - 新建 LinkedList
     * @description: en - New LinkedList
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 5:53 下午
     * @param values: 数组
     * @return java.util.LinkedList<T>
    */
	@SafeVarargs
    public static <T> LinkedList<T> toLinkedList(T... values) {
        return (LinkedList<T>) list(Constant.TRUE, values);
    }

    /**
     * [数组转为一个不可变List](Array into an immutable list)
     * @description: zh - 数组转为一个不可变List
     * @description: en - Array into an immutable list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 5:54 下午
     * @param ts: 对象
     * @return java.util.List<T>
    */
	@SafeVarargs
    public static <T> List<T> of(T... ts) {
        return ArrayUtil.isEmpty(ts) ? Collections.emptyList() : Collections.unmodifiableList(toList(ts));
    }

    /**
     * [新建一个 CopyOnWriteArrayList](Create a new CopyOnWriteArrayList)
     * @description: zh - 新建一个 CopyOnWriteArrayList
     * @description: en - Create a new CopyOnWriteArrayList
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 5:56 下午
     * @param collection: 集合
     * @return java.util.concurrent.CopyOnWriteArrayList<T>
    */
    public static <T> CopyOnWriteArrayList<T> toCopyOnWriteArrayList(Collection<T> collection) {
        return (Constant.NULL == collection) ? (new CopyOnWriteArrayList<>()) : (new CopyOnWriteArrayList<>(collection));
    }

    /*排序-----------------------------------------------------------sort*/

    /**
     * [针对List排序，排序会修改原List](For list sorting, sorting will modify the original list)
     * @description: zh - 针对List排序，排序会修改原List
     * @description: en - For list sorting, sorting will modify the original list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 6:52 下午
     * @param list: 被排序的List
     * @param c: Comparator
     * @return java.util.List<T>
    */
    public static <T> List<T> sort(List<T> list, Comparator<? super T> c) {
        list.sort(c);
        return list;
    }

    /**
     * [根据Bean的属性排序](Sort by bean's properties)
     * @description: zh - 根据Bean的属性排序
     * @description: en - Sort by bean's properties
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 6:57 下午
     * @param list: List
     * @param property: 属性名
     * @return java.util.List<T>
    */
    public static <T> List<T> sortByProperty(List<T> list, String property) {
        return sort(list, new PropertyComparator<>(property));
    }

    /**
     * [根据汉字的拼音顺序排序](According to the Pinyin order of Chinese characters)
     * @description: zh - 根据汉字的拼音顺序排序
     * @description: en - According to the Pinyin order of Chinese characters
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 6:59 下午
     * @param list: List
     * @return java.util.List<java.lang.String>
    */
    public static List<String> sortByPinyin(List<String> list) {
        return sort(list, new PinyinComparator());
    }

    /**
     * [反序给定List，会在原List基础上直接修改](Given a list in reverse order, it will be modified directly on the basis of the original list)
     * @description: zh - 反序给定List，会在原List基础上直接修改
     * @description: en - Given a list in reverse order, it will be modified directly on the basis of the original list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 7:04 下午
     * @param list: 元素类型
     * @return java.util.List<T>
    */
    public static <T> List<T> reverse(List<T> list) {
        Collections.reverse(list);
        return list;
    }

    /**
     * [反序给定List，会创建一个新的List，原List数据不变](Given a list in reverse order, a new list will be created, and the data of the original list will remain unchanged)
     * @description: zh - 反序给定List，会创建一个新的List，原List数据不变
     * @description: en - Given a list in reverse order, a new list will be created, and the data of the original list will remain unchanged
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 7:05 下午
     * @param list: 被反转的List
     * @return java.util.List<T>
    */
    public static <T> List<T> reverseNew(List<T> list) {
        final List<T> list2 = ObjectUtil.clone(list);
        return reverse(list2);
    }

    /**
     * [设置或增加元素。当index小于List的长度时，替换指定位置的值，否则在尾部追加](Set or add elements. When the index is less than the length of the list, replace the value at the specified position, otherwise, append at the end)
     * @description: zh - 设置或增加元素。当index小于List的长度时，替换指定位置的值，否则在尾部追加
     * @description: en - Set or add elements. When the index is less than the length of the list, replace the value at the specified position, otherwise, append at the end
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 7:08 下午
     * @param list: List列表
     * @param index: 位置
     * @param element: 新元素
     * @return java.util.List<T>
    */
    public static <T> List<T> setOrAppend(List<T> list, int index, T element) {
        if (index < list.size()) {
            list.set(index, element);
        } else {
            list.add(element);
        }
        return list;
    }

    /*截取-----------------------------------------------------------sub*/

    /**
     * [截取集合的部分](Intercepts part of a set)
     * @description: zh - 截取集合的部分
     * @description: en - Intercepts part of a set
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 7:09 下午
     * @param list: 被截取的数组
     * @param start: 开始位置（包含）
     * @param end: 结束位置（不包含）
     * @return java.util.List<T>
    */
    public static <T> List<T> sub(List<T> list, int start, int end) {
        return sub(list, start, end, Constant.ONE);
    }

    /**
     * [截取集合的部分](Intercepts part of a set)
     * @description: zh - 截取集合的部分
     * @description: en - Intercepts part of a set
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 7:10 下午
     * @param list: 被截取的数组
     * @param start: 开始位置（包含）
     * @param end: 结束位置（不包含）
     * @param step: 步进
     * @return java.util.List<T>
    */
    public static <T> List<T> sub(List<T> list, int start, int end, int step) {
        if (list == Constant.NULL) {
            return null;
        }

        if (list.isEmpty()) {
            return new ArrayList<>(Constant.ZERO);
        }

        final int size = list.size();
        if (start < Constant.ZERO) {
            start += size;
        }
        if (end < Constant.ZERO) {
            end += size;
        }
        if (start == size) {
            return new ArrayList<>(Constant.ZERO);
        }
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }
        if (end > size) {
            if (start >= size) {
                return new ArrayList<>(Constant.ZERO);
            }
            end = size;
        }

        if (step < Constant.ONE) {
            step = Constant.ONE;
        }

        final List<T> result = new ArrayList<>();
        for (int i = start; i < end; i += step) {
            result.add(list.get(i));
        }
        return result;
    }

    /*过滤-----------------------------------------------------------filter*/

    /**
     * [过滤](filter)
     * @description: zh - 过滤
     * @description: en - filter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 7:14 下午
     * @param list: 集合
     * @param editor: 编辑器接口
     * @return java.util.List<T>
    */
    public static <T> List<T> filter(List<T> list, Editor<T> editor) {
        if (Constant.NULL == list || Constant.NULL == editor) {
            return list;
        }
        final List<T> list2 = (list instanceof LinkedList) ? new LinkedList<>() : new ArrayList<>(list.size());
        T modified;
        for (T t : list) {
            modified = editor.edit(t);
            if (Constant.NULL != modified) {
                list2.add(modified);
            }
        }
        return list2;
    }

    /**
     * [获取匹配规则定义中匹配到元素的所有位置](Gets all the positions of the matched elements in the matching rule definition)
     * @description: zh - 获取匹配规则定义中匹配到元素的所有位置
     * @description: en - Gets all the positions of the matched elements in the matching rule definition
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 7:53 下午
     * @param list: 列表
     * @param matcher: 匹配器，为空则全部匹配
     * @return int[]
    */
    public static <T> int[] indexOfAll(List<T> list, Matcher<T> matcher) {
        final List<Integer> indexList = new ArrayList<>();
        if (null != list) {
            int index = Constant.ZERO;
            for (T t : list) {
                if (null == matcher || matcher.match(t)) {
                    indexList.add(index);
                }
                index++;
            }
        }
        return Convert.convert(int[].class, indexList);
    }

    /**
     * [将对应List转换为不可修改的List](Convert the corresponding list to a list that cannot be modified)
     * @description: zh - 将对应List转换为不可修改的List
     * @description: en - Convert the corresponding list to a list that cannot be modified
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 8:02 下午
     * @param list: 集合
     * @return java.util.List<T>
    */
    public static <T> List<T> unmodifiable(List<T> list) {
        return Constant.NULL == list ? null : Collections.unmodifiableList(list);
    }

    /*空的-----------------------------------------------------------empty*/

    /**
     * [获取一个空集合](Get an empty collection)
     * @description: zh - 获取一个空集合
     * @description: en - Get an empty collection
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 8:06 下午
     * @return java.util.List<T>
    */
    public static <T> List<T> empty() {
        return Collections.emptyList();
    }

    /**
     * [对集合按照指定长度分段，每一个段为单独的集合，返回这个集合的列表](The set is segmented according to the specified length, each segment is a separate set, and the list of this set is returned)
     * @description: zh - 对集合按照指定长度分段，每一个段为单独的集合，返回这个集合的列表
     * @description: en - The set is segmented according to the specified length, each segment is a separate set, and the list of this set is returned
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 8:09 下午
     * @param list: 列表
     * @param size: 每个段的长度
     * @return java.util.List<java.util.List<T>>
    */
    public static <T> List<List<T>> split(List<T> list, int size) {
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        final int listSize = list.size();
        final List<List<T>> result = new ArrayList<>(listSize / size + Constant.ONE);
        int offset = Constant.ZERO;
        for (int toIdx = size; toIdx <= listSize; offset = toIdx, toIdx += size) {
            result.add(list.subList(offset, toIdx));
        }
        if (offset < listSize) {
            result.add(list.subList(offset, listSize));
        }
        return result;
    }

	/*分页-----------------------------------------------------------page*/

	/**
	 * [对指定List分页取值](Page values for the specified list)
	 * @description zh - 对指定List分页取值
	 * @description en - Page values for the specified list
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 07:35:50
	 * @param current 页码
	 * @param size 条目数
	 * @param list 集合
	 * @return java.util.List<T>
	 */
	public static <T> List<T> page(int current, int size, List<T> list) {
		if (CollUtil.isEmpty(list)) {
			return new ArrayList<>(Constant.ZERO);
		}

		int resultSize = list.size();
		// 每页条目数大于总数直接返回所有
		if (resultSize <= size) {
			if (current < (PageUtil.getFirstPageNo() + Constant.ONE)) {
				return unmodifiable(list);
			} else {
				// 越界直接返回空
				return new ArrayList<>(Constant.ZERO);
			}
		}
		// 相乘可能会导致越界 临时用long
		if (((long) (current - PageUtil.getFirstPageNo()) * size) > resultSize) {
			// 越界直接返回空
			return new ArrayList<>(Constant.ZERO);
		}

		final int[] startEnd = PageUtil.transToStartEnd(current, size);
		if (startEnd[Constant.ONE] > resultSize) {
			startEnd[Constant.ONE] = resultSize;
			if (startEnd[Constant.ZERO] > startEnd[Constant.ONE]) {
				return new ArrayList<>(Constant.ZERO);
			}
		}

		return sub(list, startEnd[Constant.ZERO], startEnd[Constant.ONE]);
	}
}
