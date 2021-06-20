package com.xiaoTools.util.arrayUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.editor.Editor;
import com.xiaoTools.util.arrayUtil.filter.Filter;
import com.xiaoTools.util.arrayUtil.match.Match;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [数组工具类](Array tool class)
 * @author HCY
 * @since 2021/5/16 2:21 [下午](afternoon)
*/
public class ArrayUtil {
    /**
     * [初始化方法](Initialization method)
     * @author HCY
     * @since 2021/5/16 2:21 [下午](afternoon)
    */
    public ArrayUtil() { }

    /**
     * [判断数组是否为空](Determine whether the array is empty)
     * @description: zh - 判断数组是否为空
     * @description: en - Determine whether the array is empty
     * @author HCY
     * @since 2021/5/16 2:21 下午
     * @param array: [数组](array)
     * @return boolean
    */
    public static <T> boolean isEmpty(T[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /**
     * [判断数组是否为空](Determine whether the array is empty)
     * @description: zh - 判断数组是否为空
     * @description: en - Determine whether the array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/11 7:59 下午
     * @param array: [数组](array)
     * @return boolean
    */
    public static boolean isEmpty(Object array){
        return array == Constant.NULL || (isArray(array) && Array.getLength(array) == Constant.ZERO );
    }

    /**
     * [查看数组是否不为空](Check if the array is not empty)
     * @description: zh - 查看数组是否不为空
     * @description: en - Check if the array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/11 7:38 下午
     * @param array: [数组](array)
     * @return boolean
    */
    public static <T> boolean isNotEmpty(T[] array){
        return array != Constant.NULL && array.length != 0  ;
    }

    /**
     * [查看数组是否不为空](Check if the array is not empty)
     * @description: zh - 查看数组是否不为空
     * @description: en - Check if the array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/11 9:13 下午
     * @param array: [数组](array)
     * @return boolean
    */
    public static boolean isNotEmpty(Object array){
        return !isEmpty(array);
    }

    /**
     * [如果提交的检查数组为空，则返回默认的数组。](If the submitted check array is empty, the default array is returned.)
     * @description: zh - 如果提交的检查数组为空，则返回默认的数组。
     * @description: en - If the submitted check array is empty, the default array is returned.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 9:08 上午
     * @param array: [检查的数组](Array of checks)
     * @param defaultArray: [默认的的数组](Default array)
     * @return T[]
     */
    public static <T> T[] defaultIfEmpty(T[] array,T[] defaultArray){
        return isEmpty(array) ? defaultArray : array;
    }

    /**
     * [检查数组是否含有「null」](Check whether the array contains 「null」)
     * @description: zh - 检查数组是否含有「null」
     * @description: en - Check whether the array contains 「null」
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/11 9:28 下午
     * @param array: [被检查的数组](Array being checked)
     * @return boolean
    */
    @SafeVarargs
    public static <T> boolean hasNull(T... array) {
        //判断数组是否为空
        if (isNotEmpty(array)) {
            //查看数组内的单个元素是否为「 null 」
            for (T element : array) {
                if ( Constant.NULL == element) {
                    return Constant.TRUE;
                }
            }
        }
        return Constant.FALSE;
    }

    /**
     * [输入数组，判断是否包含所需要的值](Input the array to determine whether it contains the required value)
     * @description: zh - 输入数组，判断是否包含所需要的值
     * @description: en - Input the array to determine whether it contains the required value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/24 9:14 上午
     * @param arrays: [数组](Array)
     * @param value: [包含的值](Values contained)
     * @return boolean
    */
    public static <T> boolean contains(T[] arrays, T value) {
        return indexOf(arrays, value) > Constant.NEGATIVE_ONE;
    }

    public static boolean contains(int[] array, int value) {
        return indexOf(array, value) > Constant.NEGATIVE_ONE;
    }


    /**
     * [数组中是否包含指定元素中的任意一个](Whether the array contains any of the specified elements)
     * @description: zh - 数组中是否包含指定元素中的任意一个
     * @description: en - Whether the array contains any of the specified elements
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/13 11:23 上午
     * @param array: 数组
     * @param values: 被检查的多个元素
     * @return boolean
    */
    public static <T> boolean containsAny(T[] array, T... values){
        for (T value : values) {
            if (contains(array,value)) {
                return Constant.TRUE;
            }
        }
        return Constant.FALSE;
    }

    /**
     * [数组中是否包含指定元素中的全部](Whether the array contains all the elements in the specified element)
     * @description: zh - 数组中是否包含指定元素中的全部
     * @description: en - Whether the array contains all the elements in the specified element
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/13 11:27 上午
     * @param array: 数组
     * @param values: 被检查的多个元素
     * @return boolean
    */
    public static <T> boolean containsAll(T[] array, T... values) {
        for (T value : values) {
            if (!contains(array, value)) {
                return Constant.FALSE;
            }
        }
        return Constant.TRUE;
    }


    /**
     * [数组中是否包含元素](Does the array contain elements)
     * @description: zh - 数组中是否包含元素
     * @description: en - Does the array contain elements
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/13 11:14 上午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return boolean
    */
    public static boolean contains(char[] array, char value) {
        return indexOf(array, value) > Constant.NEGATIVE_ONE;
    }

    /**
     * [判断对象是否为数组](Determine whether the object is an array)
     * @description: zh - 判断对象是否为数组
     * @description: en - Determine whether the object is an array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/11 8:02 下午
     * @param obj: [数组](array)
     * @return boolean
    */
    public static boolean isArray(Object obj) {
        return Constant.NULL != obj && obj.getClass().isArray();
    }

    /**
     * [通过数组获取索引的值](Gets the value of the index through an array)
     * @description: zh - 通过数组获取索引的值
     * @description: en - Gets the value of the index through an array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/24 9:22 上午
     * @param array: [数组](Array)
     * @param value: [单个值](Single value)
     * @return int
    */
    public static <T> int indexOf(T[] array, Object value) {
        if (Constant.NULL != array) {
            for(int i = Constant.ZERO; i < array.length; ++i) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回数组中指定元素所在位置，未找到返回 -1](Returns the location of the specified element in the array, and - 1 is not found)
     * @description: zh - 返回数组中指定元素所在位置，未找到返回 -1
     * @description: en - Returns the location of the specified element in the array, and - 1 is not found
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 5:29 下午
     * @param array: 数组
     * @param value: 被检查的元素
     * @return int
    */
    public static int indexOf(int[] array, int value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [通过数组获取索引的值](Gets the value of the index through an array)
     * @description: zh - 通过数组获取索引的值
     * @description: en - Gets the value of the index through an array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/24 9:22 上午
     * @param array: [数组](Array)
     * @param value: [单个值](Single value)
     * @return int
     */
    public static int indexOf(char[] array, char value) {
        if (Constant.NULL != array) {
            for (int i = Constant.ZERO; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return Constant.NEGATIVE_ONE;
    }


    /**
     * [新建一个空数组](Create a new empty array)
     * @description: zh - 新建一个空数组
     * @description: en - Create a new empty array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 9:27 上午
     * @param classType: 创建新的数组的类型
     * @param newSize: 数组的长度
     * @return T[]
    */
    public static <T> T[] newArray(Class<?> classType, int newSize){
        return (T[]) Array.newInstance(classType,newSize);
    }

    /**
     * [创建一个新的数组](Create a new array)
     * @description: zh - 创建一个新的数组
     * @description: en - Create a new array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 9:35 上午
     * @param newSize: 数组的长度
     * @return java.lang.Object[]
    */
    public static Object[] newArray(int newSize){ return new Object[newSize]; }

    /**
     * [判断数组中是否多个字段是否全为null](Determine whether multiple fields in the array are null)
     * @description: zh - 判断数组中是否多个字段是否全为null
     * @description: en - Determine whether multiple fields in the array are null
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 2:46 下午
     * @param array: 数组
     * @return boolean
    */
    @SafeVarargs
    public static <T> boolean isAllNull(T... array){
        return firstNull(array) == Constant.NULL;
    }

    /**
     * [返回数组中第一个非空元素](Returns the first non empty element in an array)
     * @description: zh - 返回数组中第一个非空元素
     * @description: en - Returns the first non empty element in an array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 2:48 下午
     * @param array: 数组
     * @return T
    */
    @SafeVarargs
    public static <T> T firstNull(T... array){
        return firstMatch(Objects::nonNull, array);
    }

    /**
     * [返回数组中第一个匹配规则的值](Returns the value of the first matching rule in the array)
     * @description: zh - 返回数组中第一个匹配规则的值
     * @description: en - Returns the value of the first matching rule in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 2:41 下午
     * @param match: 匹配接口，实现此接口自定义匹配规则
     * @param array: 数组
     * @return T
    */
    public static <T> T firstMatch(Match<T> match, T... array) {
        if (isNotEmpty(array)) {
            for (final T val : array) {
                if (match.match(val)) {
                    return val;
                }
            }
        }
        return (T) Constant.NULL;
    }

    /**
     * [获取数组对象的元素类型](Gets the element type of an array object)
     * @description: zh - 获取数组对象的元素类型
     * @description: en - Gets the element type of an array object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 10:21 上午
     * @param array: [获取数组的类型](Gets the type of the array)
     * @return java.lang.Class<?>
    */
    public static Class<?> getComponentType(Object array){
        return isEmpty(array) ? (Class<?>) Constant.NULL : array.getClass().getComponentType();
    }

    /**
     * [将新元素插入到到已有数组中的某个位置](Insert a new element into an existing array)
     * @description: zh - 将新元素插入到到已有数组中的某个位置
     * @description: en - Insert a new element into an existing array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 11:35 上午
     * @param array: 需要插入的数组
     * @param index: 插入位置，此位置为对应此位置元素之前的空档
     * @param newElements: 插入的元素
     * @return T[]
    */
    @SafeVarargs
    public static <T> T[] insert(T[] array, int index, T... newElements){
         return (T[])insert((Object) array, index, newElements);
    }

    /**
     * [将新元素插入到到已有数组中的某个位置](Insert a new element into an existing array)
     * @description: zh - 将新元素插入到到已有数组中的某个位置
     * @description: en - Insert a new element into an existing array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 11:56 上午
     * @param array: 需要插入的数组
     * @param index: 插入位置，此位置为对应此位置元素之前的空档
     * @param newElements: 插入的元素
     * @return java.lang.Object
    */
    @SafeVarargs
    public static <T> Object insert(Object array , int index , T... newElements){
        if (isEmpty(newElements)) { return array; }
        if (isEmpty(array)) { return newElements; }
        final int length = length(array);
        if (index < Constant.ZERO){ index = (index % length) + length; }
        Object[] result = newArray(array.getClass().getComponentType(), Math.max(length, index) + newElements.length);
        System.arraycopy(array, Constant.ZERO, result, Constant.ZERO, Math.min(length, index));
        System.arraycopy(newElements, Constant.ZERO, result, index, newElements.length);
        if (index < length) {
            System.arraycopy(array, index, result, index + newElements.length, length - index);
        }
        return result;
    }

    /**
     * [获取数组的长度](Gets the length of the array)
     * @description: zh - 获取数组的长度
     * @description: en - Gets the length of the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 10:59 上午
     * @param array: [数组](array)
     * @return int
    */
    public static int length(Object array){
        return isEmpty(array) ? Constant.ZERO : Array.getLength(array);
    }

    /**
     * [生成一个新的重新设置大小的数组](Generates a new resized array)
     * @description: zh - 生成一个新的重新设置大小的数组
     * @description: en - Generates a new resized array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 1:52 下午
     * @param array: [数组](array)
     * @param newSize: [新的数组长度](New array length)
     * @return T[]
    */
    public static <T> T[] reSize(T[] array, int newSize){
        if (newSize < Constant.ZERO) { return array; }
        final T[] newArray = newArray(array.getClass().getComponentType(), newSize);
        if (newSize > Constant.ZERO && isNotEmpty(array)) {
            System.arraycopy(array, Constant.ZERO, newArray, Constant.ZERO, Math.min(array.length, newSize));
        }
        return newArray;
    }

    /**
     * [生成一个新的重新设置大小的数组](Generates a new resized array)
     * @description: zh - 生成一个新的重新设置大小的数组
     * @description: en - Generates a new resized array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 1:44 下午
     * @param array: [数组](array)
     * @param newSize: [新的数组长度](New array length)
     * @return java.lang.Object
    */
    public static Object reSize(Object array, int newSize){
        if (newSize < Constant.ZERO || array == Constant.NULL){ return array; }
        final int length = length(array);
        final Object result = Array.newInstance(array.getClass().getComponentType(), newSize);
        if (newSize > Constant.ZERO && isNotEmpty(array)) {
            System.arraycopy(array, Constant.ZERO, result, Constant.ZERO, Math.min(length, newSize));
        }
        return result;
    }

    /**
     * [强转数组类型](Strongly convert array type)
     * @description: zh - 强转数组类型
     * @description: en - Strongly convert array type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 3:16 下午
     * @param array: 数组
     * @param classType: 数组类型或数组元素类型
     * @return java.lang.Object[]
    */
    public static Object[] cast(Object array, Class<?> classType){
        if (array == Constant.NULL){ return (Object[]) Constant.NULL; }
        if (!array.getClass().isArray()){ return (Object[]) Constant.NULL; }
        if (classType == Constant.NULL){ return (Object[])array; }
        final Class<?> componentType = classType.isArray() ? classType.getComponentType() : classType;
        final Object[] arrayObj = (Object[]) array;
        final Object[] result = ArrayUtil.newArray(componentType, arrayObj.length);
        System.arraycopy(arrayObj, Constant.ZERO, result, Constant.ZERO, arrayObj.length);
        return result;
    }

    /**
     * [将元素添加进入数组](Adding elements to an array)
     * @description: zh - 将元素添加进入数组
     * @description: en - Adding elements to an array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 3:44 下午
     * @param array: 需要插入的数组
     * @param newElement: 需要插入的元素
     * @return T[]
    */
    @SafeVarargs
    public static <T> T[] append(T[] array,T... newElement){
        return isEmpty(array) ? newElement : insert(array, array.length, newElement);
    }

    /**
     * [将元素添加进入元素](Add element to element)
     * @description: zh - 将元素添加进入元素
     * @description: en - Add element to element
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 3:50 下午
     * @param array: 需要插入元素的数组
     * @param newElement: 需要插入的元素
     * @return java.lang.Object
    */
    @SafeVarargs
    public static <T> Object append(Object array,T... newElement){
        return isEmpty(array) ? newElement : insert(array, length(array), newElement);
    }

    /**
     * [将元素值设置为数组的某个位置，当给定的index大于数组长度，则追加 ](Set the element value to a certain position of the array. When the given index is greater than the length of the array, append)
     * @description: zh - 将元素值设置为数组的某个位置，当给定的index大于数组长度，则追加
     * @description: en - Set the element value to a certain position of the array. When the given index is greater than the length of the array, append
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 4:11 下午
     * @param array: 存在的数组
     * @param index: 位置
     * @param value: 加入的值
     * @return T[]
    */
    public static <T> T[] setOrAppend(T[] array, int index, T value){
        if (array.length < index){
            Array.set(array,index,value);
            return array;
        }else {
            return append(array, value);
        }
    }

    /**
     * [将元素值设置为数组的某个位置，当给定的index大于数组长度，则追加 ](Set the element value to a certain position of the array. When the given index is greater than the length of the array, append)
     * @description: zh - 将元素值设置为数组的某个位置，当给定的index大于数组长度，则追加
     * @description: en - Set the element value to a certain position of the array. When the given index is greater than the length of the array, append
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 4:29 下午
     * @param array: 数组
     * @param index: 位置
     * @param value: 加入的值
     * @return java.lang.Object
    */
    public static Object setOrAppend(Object array, int index, Object value){
        if (index < length(array)) {
            Array.set(array, index, value);
            return array;
        } else {
            return append(array, value);
        }
    }

    /**
     * [将多个数组合并在一起](Merging multiple arrays together)
     * @description: zh - 将多个数组合并在一起
     * @description: en - Merging multiple arrays together
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 4:43 下午
     * @param arrays: 数组集合
     * @return T[]
    */
    @SafeVarargs
    public static <T> T[] addAll(T[]... arrays){
        if (arrays.length == Constant.ONE) { return arrays[Constant.ZERO]; }
        int length = Constant.ZERO;
        for (T[] array : arrays) {
            if (Constant.NULL != array) { length += array.length; }
        }
        T[] result = newArray(arrays.getClass().getComponentType().getComponentType(), length);
        length = Constant.ZERO;
        for (T[] array : arrays) {
            if (null != array) {
                System.arraycopy(array, Constant.ZERO, result, length, array.length);
                length += array.length;
            }
        }
        return result;
    }

    /**
     * [数组复制](Array copy)
     * @description: zh -
     * @description: en - Array copy
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 4:51 下午
     * @param src: 源数组
     * @param srcPos: 源目标开始的位置
     * @param dest: 目标数组
     * @param destPos: 目标数组开始位置
     * @param length: 拷贝数组长度
     * @return java.lang.Object
    */
    public static Object copy(Object src, int srcPos, Object dest, int destPos, int length){
        System.arraycopy(src, srcPos, dest, destPos, length);
        return dest;
    }

    /**
     * [数组复制，源数组和目标数组都是从位置0开始复制](Array copy, source array and target array are copied from position 0)
     * @description: zh - 数组复制，源数组和目标数组都是从位置0开始复制
     * @description: en - Array copy, source array and target array are copied from position 0
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 5:15 下午
     * @param src: 源数组
     * @param dest: 目标数组
     * @param length: 拷贝数组长度
     * @return java.lang.Object
    */
    public static Object copy(Object src, Object dest, int length) {
        System.arraycopy(src, Constant.ZERO, dest, Constant.ZERO, length);
        return dest;
    }

    /**
     * [克隆的数组](Cloned array)
     * @description: zh - 克隆的数组
     * @description: en - Cloned array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 5:17 下午
     * @param array: 被克隆的数组
     * @return T[]
    */
    public static <T> T[] clone(T[] array) {
        return array == Constant.NULL ? (T[]) Constant.NULL : array.clone();
    }

    /**
     * [过滤过程通过传入的Editor实现来返回需要的元素内容](The filtering process returns the required element content through the passed in editor implementation)
     * @description: zh - 过滤过程通过传入的Editor实现来返回需要的元素内容
     * @description: en - The filtering process returns the required element content through the passed in editor implementation
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 5:39 下午
     * @param array: 数组
     * @param editor: 编辑器接口
     * @return T[]
    */
    public static <T> T[] filter(T[] array, Editor<T> editor) {
        ArrayList<T> list = new ArrayList<>(array.length);
        T modified;
        for (T t : array) {
            modified = editor.edit(t);
            if (null != modified) {
                list.add(modified);
            }
        }
        return list.toArray(Arrays.copyOf(array, list.size()));
    }

    /**
     * [过滤过程通过传入的Filter实现来过滤返回需要的元素内容](The filtering process is implemented by passing in the filter to filter and return the required element content)
     * @description: zh - 过滤过程通过传入的Filter实现来过滤返回需要的元素内容
     * @description: en - The filtering process is implemented by passing in the filter to filter and return the required element content
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 7:46 下午
     * @param array: 数组
     * @param filter: 过滤器接口，用于定义过滤规则，null表示不过滤，返回原数组
     * @return T[]
    */
    public static <T> T[] filter(T[] array, Filter<T> filter) {
        if (null == filter) { return array; }
        final ArrayList<T> list = new ArrayList<>(array.length);
        for (T t : array) {
            if (filter.accept(t)) {
                list.add(t);
            }
        }
        final T[] result = newArray(array.getClass().getComponentType(), list.size());
        return list.toArray(result);
    }

    /**
     * [编辑过程通过传入的Editor实现来返回需要的元素内容](The editing process returns the required element content through the passed in editor implementation)
     * @description: zh - 编辑过程通过传入的Editor实现来返回需要的元素内容
     * @description: en - The editing process returns the required element content through the passed in editor implementation
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 7:28 下午
     * @param array: 数组
     * @param editor: 编辑器接口
    */
    public static <T> void edit(T[] array, Editor<T> editor){
        for (int i = Constant.ZERO; i < array.length; i++) {
            array[i] = editor.edit(array[i]);
        }
    }

    /**
     * [删除数组中的「null」元素](Delete the 「null」 element in the array)
     * @description: zh - 删除数组中的「null」元素
     * @description: en - Delete the 「null」 element in the array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 7:37 下午
     * @param array: 数组
     * @return T[]
    */
    public static <T> T[] removeNull(T[] array){
        return filter(array, (Editor<T>)t -> {
            return t;
        });
    }

    /**
     * [去除「null」或者「""」元素](Remove the 「null」 or 「""」element)
     * @description: zh - 去除「null」或者「""」元素
     * @description: en - Remove the 「null」 or 「""」element
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 7:47 下午
     * @param array: 数组
     * @return T[]
    */
    public static <T extends CharSequence> T[] removeEmpty(T[] array) {
        return filter(array, StrUtil::isNotEmpty);
    }

    /**
     * [去除 「null」或者「""」或者「空白字符串」元素](Remove 「null」 or 「blank string」or 「""」 elements)
     * @description: zh - 去除 「null」或者「""」或者「空白字符串」元素
     * @description: en - Remove 「null」 or 「blank string」or 「""」 elements
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 7:48 下午
     * @param array: 数组
     * @return T[]
    */
    public static <T extends CharSequence> T[] removeBlank(T[] array) {
        return filter(array, StrUtil::isNotBlank);
    }

    /**
     * [数组元素中的「null」转换为「""」](「Null」 in array element is converted to 「""」)
     * @description: zh - 数组元素中的「null」转换为「""」
     * @description: en - 「Null」 in array element is converted to 「""」
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 7:55 下午
     * @param array: 数组
     * @return java.lang.String[]
    */
    public static String[] nullToEmpty(String[] array) {
        return filter(array, (Editor<String>) t -> null == t ? Constant.EMPTY : t);
    }

    /**
     * [获取数组对象中指定index的值，支持负数，例如-1表示倒数第一个值](Gets the value of the specified index in the array object, and supports negative numbers. For example, - 1 represents the first value from the bottom)
     * @description: zh - 获取数组对象中指定index的值，支持负数，例如-1表示倒数第一个值
     * @description: en - Gets the value of the specified index in the array object, and supports negative numbers. For example, - 1 represents the first value from the bottom
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/13 11:55 上午
     * @param array: 数组对象
     * @param index: 位置下标，支持负数
     * @return T
    */
    public static <T> T get(Object array, int index) {
        if (null == array) { return null; }
        if (index < Constant.ZERO) { index += Array.getLength(array); }
        try {
            return (T) Array.get(array, index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * [数组或集合转String](Array or set to string)
     * @description: zh - 数组或集合转String
     * @description: en - Array or set to string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/14 10:22 上午
     * @param obj: 集合或数组对象
     * @return java.lang.String
    */
    public static String toString(Object obj) {
        if (null == obj) { return null; }
        if (obj instanceof long[]) {
            return Arrays.toString((long[]) obj);
        } else if (obj instanceof int[]) {
            return Arrays.toString((int[]) obj);
        } else if (obj instanceof short[]) {
            return Arrays.toString((short[]) obj);
        } else if (obj instanceof char[]) {
            return Arrays.toString((char[]) obj);
        } else if (obj instanceof byte[]) {
            return Arrays.toString((byte[]) obj);
        } else if (obj instanceof boolean[]) {
            return Arrays.toString((boolean[]) obj);
        } else if (obj instanceof float[]) {
            return Arrays.toString((float[]) obj);
        } else if (obj instanceof double[]) {
            return Arrays.toString((double[]) obj);
        } else if (ArrayUtil.isArray(obj)) {
            // 对象数组
            try {
                return Arrays.deepToString((Object[]) obj);
            } catch (Exception ignore) {
                //ignore
            }
        }
        return obj.toString();
    }
}
