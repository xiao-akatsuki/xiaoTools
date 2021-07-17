package com.xiaoTools.core.text.stringBuilder;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;

import java.io.Serial;
import java.io.Serializable;

/**
 * [可复用的字符串生成器，非线程安全](Reusable string generator, non thread safe)
 * @description: zh - 可复用的字符串生成器，非线程安全
 * @description: en - Reusable string generator, non thread safe
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/7/17 9:14 下午
*/
public class StrBuilder implements CharSequence, Appendable, Serializable {

    @Serial
    private static final long serialVersionUID = 6341229705927508451L;


    /**
     * 默认容量
     */
    public static final int DEFAULT_CAPACITY = 16;

    /**
     * 存放的字符数组
     */
    private char[] value;
    /**
     * 当前指针位置，或者叫做已经加入的字符数，此位置总在最后一个字符之后
     */
    private int position;

    /*创建字符串构建器 -----------------------------------------------------------create*/

    /**
     * [创建字符串构建器](Create string builder)
     * @description: zh - 创建字符串构建器
     * @description: en - Create string builder
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 9:16 下午
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public static StrBuilder create() {
        return new StrBuilder();
    }

    /**
     * [创建字符串构建器](Create string builder)
     * @description: zh - 创建字符串构建器
     * @description: en - Create string builder
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 9:16 下午
     * @param initialCapacity: 初始容量
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public static StrBuilder create(int initialCapacity) {
        return new StrBuilder(initialCapacity);
    }

    /**
     * [创建字符串构建器](Create string builder)
     * @description: zh - 创建字符串构建器
     * @description: en - Create string builder
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 9:18 下午
     * @param values: 初始字符串数组
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public static StrBuilder create(CharSequence... values) {
        return new StrBuilder(values);
    }

    /*构造 -----------------------------------------------------------structure*/

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 9:27 下午
    */
    public StrBuilder() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 9:26 下午
     * @param initialCapacity: 初始容量
    */
    public StrBuilder(int initialCapacity) {
        value = new char[initialCapacity];
    }

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 9:26 下午
     * @param values: 初始字符串
    */
    public StrBuilder(CharSequence... values) {
        this(ArrayUtil.isEmpty(values) ? DEFAULT_CAPACITY : (totalLength(values) + DEFAULT_CAPACITY));
        for (CharSequence str : values) {
            append(str);
        }
    }

    /*追加 -----------------------------------------------------------append*/

    /**
     * [追加对象，对象会被转换为字符串](Appends an object, which is converted to a string)
     * @description: zh - 追加对象，对象会被转换为字符串
     * @description: en - Appends an object, which is converted to a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 9:38 下午
     * @param value: 对象
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public StrBuilder append(Object value) {
        return insert(this.position, value);
    }

    /**
     * [追加一个字符](Append a character)
     * @description: zh - 追加一个字符
     * @description: en - Append a character
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 9:40 下午
     * @param c: 字符
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    @Override
    public StrBuilder append(char c) {
        return insert(this.position, c);
    }

    /**
     * [追加一个字符数组](Appending a character array)
     * @description: zh - 追加一个字符数组
     * @description: en - Appending a character array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 9:41 下午
     * @param values: 字符数组
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public StrBuilder append(char[] values) {
        if (ArrayUtil.isEmpty(values)) {
            return this;
        }
        return append(values, Constant.ZERO, values.length);
    }

    /**
     * [追加一个字符数组](Appending a character array)
     * @description: zh - 追加一个字符数组
     * @description: en - Appending a character array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 9:42 下午
     * @param src: 字符数组
     * @param srcPos: 开始位置（包括）
     * @param length: 长度
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public StrBuilder append(char[] src, int srcPos, int length) {
        return insert(this.position, src, srcPos, length);
    }

    @Override
    public StrBuilder append(CharSequence csq) {
        return insert(this.position, csq);
    }

    @Override
    public StrBuilder append(CharSequence csq, int start, int end) {
        return insert(this.position, csq, start, end);
    }



}
