package com.xiaoTools.core.text.stringBuilder;

import com.xiaoTools.core.convert.Convert;
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

    /*添加 -----------------------------------------------------------insert*/

    /**
     * [追加对象，对象会被转换为字符串](Appends an object, which is converted to a string)
     * @description: zh - 追加对象，对象会被转换为字符串
     * @description: en - Appends an object, which is converted to a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/18 7:39 下午
     * @param index: 插入位置
     * @param obj: 对象
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public StrBuilder insert(int index, Object obj) {
        if (obj instanceof CharSequence) {
            return insert(index, (CharSequence) obj);
        }
        return insert(index, Convert.toStr(obj));
    }

    /**
     * [插入指定字符](Inserts the specified character)
     * @description: zh - 插入指定字符
     * @description: en - Inserts the specified character
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/19 5:59 下午
     * @param index: 位置
     * @param c: 字符
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public StrBuilder insert(int index, char c) {
        moveDataAfterIndex(index, Constant.ONE);
        value[index] = c;
        this.position = Math.max(this.position, index) + Constant.ONE;
        return this;
    }

    /**
     * [指定位置插入数据](Insert data at specified location)
     * @description: zh - 指定位置插入数据
     * @description: en - Insert data at specified location
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/19 6:02 下午
     * @param index: 插入位置
     * @param src: 源数组
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public StrBuilder insert(int index, char[] src) {
        return ArrayUtil.isEmpty(src) ? this : insert(index, src, Constant.ZERO, src.length);
    }

    /**
     * [指定位置插入数据](Insert data at specified location)
     * @description: zh - 指定位置插入数据
     * @description: en - Insert data at specified location
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/19 6:03 下午
     * @param index: 插入位置
     * @param src: 源数组
     * @param srcPos: 位置
     * @param length: 长度
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public StrBuilder insert(int index, char[] src, int srcPos, int length) {
        if (ArrayUtil.isEmpty(src) || srcPos > src.length || length <= Constant.ZERO) {
            return this;
        }
        if (index < Constant.ZERO) {
            index = Constant.ZERO;
        }
        if (srcPos < Constant.ZERO) {
            srcPos = Constant.ZERO;
        } else if (srcPos + length > src.length) {
            // 长度越界，只截取最大长度
            length = src.length - srcPos;
        }

        moveDataAfterIndex(index, length);
        // 插入数据
        System.arraycopy(src, srcPos, value, index, length);
        this.position = Math.max(this.position, index) + length;
        return this;
    }

    /**
     * [指定位置插入字符串的某个部分](Inserts a part of a string at a specified location)
     * @description: zh - 指定位置插入字符串的某个部分
     * @description: en - Inserts a part of a string at a specified location
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/19 6:10 下午
     * @param index: 位置
     * @param csq: 字符串
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public StrBuilder insert(int index, CharSequence csq) {
        if (null == csq) {
            csq = Constant.EMPTY;
        }
        int len = csq.length();
        moveDataAfterIndex(index, csq.length());
        if (csq instanceof String) {
            ((String) csq).getChars(Constant.ZERO, len, this.value, index);
        } else if (csq instanceof StringBuilder) {
            ((StringBuilder) csq).getChars(Constant.ZERO, len, this.value, index);
        } else if (csq instanceof StringBuffer) {
            ((StringBuffer) csq).getChars(Constant.ZERO, len, this.value, index);
        } else if (csq instanceof StrBuilder) {
            ((StrBuilder) csq).getChars(Constant.ZERO, len, this.value, index);
        } else {
            for (int i = Constant.ZERO, j = this.position; i < len; i++, j++) {
                this.value[j] = csq.charAt(i);
            }
        }
        this.position = Math.max(this.position, index) + len;
        return this;
    }

    /**
     * [指定位置插入字符串的某个部分](Inserts a part of a string at a specified location)
     * @description: zh - 指定位置插入字符串的某个部分
     * @description: en - Inserts a part of a string at a specified location
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/19 6:11 下午
     * @param index: 位置
     * @param csq: 字符串
     * @param start: 字符串开始位置（包括）
     * @param end: 字符串结束位置（不包括）
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public StrBuilder insert(int index, CharSequence csq, int start, int end) {
        if (csq == Constant.NULL) {
            csq = Constant.STRING_NULL_OUT;
        }
        final int csqLen = csq.length();
        if (start > csqLen) {
            return this;
        }
        if (start < Constant.ZERO) {
            start = Constant.ZERO;
        }
        if (end > csqLen) {
            end = csqLen;
        }
        if (start >= end) {
            return this;
        }
        if (index < Constant.ZERO) {
            index = Constant.ZERO;
        }

        final int length = end - start;
        moveDataAfterIndex(index, length);
        for (int i = start, j = this.position; i < end; i++, j++) {
            value[j] = csq.charAt(i);
        }
        this.position = Math.max(this.position, index) + length;
        return this;
    }

    /*将指定段的字符列表写出到目标字符数组中 -----------------------------------------------------------get Chars*/

    /**
     * [将指定段的字符列表写出到目标字符数组中](Writes the character list of the specified segment to the target character array)
     * @description: zh - 将指定段的字符列表写出到目标字符数组中
     * @description: en - Writes the character list of the specified segment to the target character array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/20 5:51 下午
     * @param srcBegin: 起始位置（包括）
     * @param srcEnd: 结束位置（不包括）
     * @param dst: 目标数组
     * @param dstBegin: 目标起始位置（包括）
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public StrBuilder getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        if (srcBegin < Constant.ZERO) {
            srcBegin = Constant.ZERO;
        }
        if (srcEnd < Constant.ZERO) {
            srcEnd = Constant.ZERO;
        } else if (srcEnd > this.position) {
            srcEnd = this.position;
        }
        if (srcBegin > srcEnd) {
            throw new StringIndexOutOfBoundsException("srcBegin > srcEnd");
        }
        System.arraycopy(value, srcBegin, dst, dstBegin, srcEnd - srcBegin);
        return this;
    }



}
