package com.xiaoTools.util.strUtil;

import com.xiaoTools.core.stringFormatter.StrFormatter;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.charUtil.CharUtil;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.MessageFormat;

/**
 * [字符串工具类](String tool class)
 * @author HCY
 * @since 2021/5/14 3:42 下午
*/
public class StrUtil {

    /**
     * [初始化方法](Initialization method)
     * @author HCY
     * @since 2021/5/14 3:42 下午
    */
    public StrUtil() {
    }

    /**
     * [[判断字符串是否为空](只判断是否为空或者是否为null)]([judge whether the string is empty] (only judge whether it is empty or null))
     * @description: zh - 输入字符串判断是否为空
     * @description: en - Input string to determine whether it is empty
     * @version: V1.0
     * @author HCY
     * @since 2021/5/14 3:55 下午
     * @param value: [需要判断的字符串](String to judge)
     * @return boolean
    */
    public static boolean isEmpty(CharSequence value){
        return value == Constant.NULL || value.length() == Constant.ZERO;
    }

    public static boolean isEmpty(CharSequence... strs) {
        if (ArrayUtil.isEmpty(strs)) {
            return Constant.TRUE;
        }

        for (CharSequence str : strs) {
            if (isEmpty(str)) {
                return Constant.TRUE;
            }
        }
        return Constant.FALSE;
    }

    /**
     * [字符串是否为非空白](Is the string non blank)
     * @description: zh - 字符串是否为非空白
     * @description: en - Is the string non blank
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 7:42 下午
     * @param str: 被检测的字符串
     * @return boolean
    */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * [判断字符串中是否有空的字符串](Determine whether there is an empty string in the string)
     * @description: zh - 输入字符串中是否有空的字符串
     * @description: en - Is there an empty string in the input string
     * @author HCY
     * @since 2021/5/14 5:04 下午
     * @param value: [需要判断的字符串](String to judge)
     * @return boolean
    */
    public static boolean isBlank(CharSequence value){
        int length = Constant.ZERO;
        if (value != null && (length = value.length()) != Constant.ZERO){
            for (int i = Constant.ZERO; i < length; i++) {
                if (!CharUtil.isBlankChar(value.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * [指定字符串数组中，是否包含空字符串。](Specifies whether the string array contains an empty string.)
     * @description: zh - 指定字符串数组中，是否包含空字符串。
     * @description: en - Specifies whether the string array contains an empty string.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:36 上午
     * @param values: 字符串列表
     * @return boolean
    */
    public static boolean isBlank(CharSequence... values){
        if (ArrayUtil.isEmpty(values)) { return Constant.TRUE; }
        for (CharSequence value : values) {
            if (isBlank(value)) { return Constant.TRUE; }
        }
        return Constant.FALSE;
    }

    /**
     * [字符串是否为非空白](Is the string non blank)
     * @description: zh - 字符串是否为非空白
     * @description: en - Is the string non blank
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 7:51 下午
     * @param str: 被检测的字符串
     * @return boolean
    */
    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    /**
     * [判断字符串是否为空](Determine whether the string is empty)
     * @description: zh - 输入的字符串，判断是否为空
     * @description: en - Input string to determine whether it is empty
     * @version: V1.0
     * @author HCY
     * @since 2021/5/14 7:10 下午
     * @param value: [需要的字符串](Required string)
     * @return java.lang.String
    */
    public static String str(CharSequence value) {
        return null == value ? null : value.toString();
    }

    public static String str(Object obj, Charset charset) {
        if (null == obj) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        } else if (obj instanceof byte[]) {
            return str((byte[]) obj, charset);
        } else if (obj instanceof Byte[]) {
            return str((Byte[]) obj, charset);
        } else if (obj instanceof ByteBuffer) {
            return str((ByteBuffer) obj, charset);
        } else if (ArrayUtil.isArray(obj)) {
            return ArrayUtil.toString(obj);
        }

        return obj.toString();
    }

    /**
     * [解码字节码](Decode bytecode)
     * @description: zh - 解码字节码
     * @description: en - Decode bytecode
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/14 10:31 上午
     * @param data: 字符串
     * @param charset: 字符集，如果此字段为空，则解码的结果取决于平台
     * @return java.lang.String
    */
    public static String str(byte[] data, Charset charset) {
        if (data == Constant.NULL) { return Constant.STRING_NULL; }
        if (null == charset) { return new String(data); }
        return new String(data, charset);
    }

    /**
     * [解码字节码](Decode bytecode)
     * @description: zh - 解码字节码
     * @description: en - Decode bytecode
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/14 10:34 上午
     * @param data: 字符串
     * @param charset: 字符集，如果此字段为空，则解码的结果取决于平台
     * @return java.lang.String
    */
    public static String str(Byte[] data, Charset charset) {
        if (data == Constant.NULL) { return Constant.STRING_NULL; }
        byte[] bytes = new byte[data.length];
        Byte dataByte;
        for (int i = Constant.ZERO; i < data.length; i++) {
            dataByte = data[i];
            bytes[i] = (null == dataByte) ? Constant.NEGATIVE_ONE : dataByte;
        }
        return str(bytes, charset);
    }

    /**
     * [将编码的byteBuffer数据转换为字符串](Converts the encoded ByteBuffer data to a string)
     * @description: zh - 将编码的byteBuffer数据转换为字符串
     * @description: en - Converts the encoded ByteBuffer data to a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/14 10:35 上午
     * @param data: 数据
     * @param charset: 字符集，如果为空使用当前系统字符集
     * @return java.lang.String
    */
    public static String str(ByteBuffer data, Charset charset) {
        if (null == charset) {
            charset = Charset.defaultCharset();
        }
        return charset.decode(data).toString();
    }

    /**
     * [截取字符串](Intercept string)
     * @description: zh - 输入的字符串截取规定某一段的字符串
     * @description: en - The input string intercepts the specified string
     * @version: V1.0
     * @author HCY
     * @since 2021/5/14 8:01 下午
     * @param value: [需要截取的字符串](String to intercept)
     * @param formIndex: [开始位置](Start position)
     * @param toIndex: [结束位置](End position)
     * @return java.lang.String
    */
    public static String sub(CharSequence value,int formIndex,int toIndex){
        if (isEmpty(value)){
            return str(value);
        }else {
            int length = value.length();
            if (formIndex < Constant.ZERO) {
                formIndex += length;
                if (formIndex < Constant.ZERO) {
                    formIndex = Constant.ZERO;
                }
            }else if (formIndex > length) {
                formIndex = length;
            }

            if (toIndex < Constant.ZERO) {
                toIndex += length;
                if (toIndex < Constant.ZERO) {
                    toIndex = length;
                }
            } else if (toIndex > length) {
                toIndex = length;
            }

            if (toIndex < formIndex) {
                int tmp = formIndex;
                formIndex = toIndex;
                toIndex = tmp;
            }
            return formIndex == toIndex ? Constant.EMPTY : value.toString().substring(formIndex, toIndex);
        }
    }

    /**
     * [删除前缀](Remove prefix)
     * @description: zh - 删除字符串的指定前缀
     * @description: en - Removes the specified prefix of a string
     * @version: V1.0
     * @author HCY
     * @since 2021/5/14 8:16 下午
     * @param value: [需要删除前缀的字符串](The prefix string needs to be removed)
     * @param prefix: [前缀](prefix)
     * @return java.lang.String
    */
    public static String delPrefix(CharSequence value,CharSequence prefix){
        if (!isEmpty(value) && !isEmpty(prefix)) {
            String str2 = value.toString();
            return str2.startsWith(prefix.toString()) ? subSuf(str2, prefix.length()) : str2;
        } else {
            return str(value);
        }
    }

    /**
     * [创建StringBuilder对象](Create StringBuilder object)
     * @description: zh - 创建StringBuilder对象
     * @description: en - Create StringBuilder object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:40 上午
     * @return java.lang.StringBuilder
    */
    public static StringBuilder builder() {
        return new StringBuilder();
    }

    /**
     * [删除后缀](Delete suffix)
     * @description: zh - 删除字符串中指定的后缀
     * @description: en - Removes the suffix specified in the string
     * @version: V1.0
     * @author HCY
     * @since 2021/5/14 8:25 下午
     * @param value: [需要删除后缀的字符串](Need to delete suffix string)
     * @param suffix: [后缀](suffix)
     * @return java.lang.String
    */
    public static String delSuffix(CharSequence value,CharSequence suffix){
        if (!isEmpty(value) && !isEmpty(suffix)) {
            String str2 = value.toString();
            return str2.endsWith(suffix.toString()) ? subPre(str2, str2.length() - suffix.length()) : str2;
        } else {
            return str(value);
        }
    }

    /**
     * [截取字符串](Intercept string)
     * @description: zh - 截取字符串中的结束位置
     * @description: en - Intercepts the end position in a string
     * @version: V1.0
     * @author HCY
     * @since 2021/5/14 8:18 下午
     * @param string: [字符串](character string)
     * @param fromIndex: [截取的位置](Location of interception)
     * @return java.lang.String
    */
    public static String subSuf(CharSequence string, int fromIndex) {
        return isEmpty(string) ? Constant.STRING_NULL : sub(string, fromIndex, string.length());
    }

    /**
     * [截取字符串](Intercept string)
     * @description: zh - 截取字符串的开始位置
     * @description: en - The start position of the intercepted string
     * @version: V1.0
     * @author HCY
     * @since 2021/5/14 8:24 下午
     * @param string: [字符串](character string)
     * @param toIndexExclude: [截取的位置](Location of interception)
     * @return java.lang.String
    */
    public static String subPre(CharSequence string, int toIndexExclude) {
        return sub(string, Constant.ZERO, toIndexExclude);
    }

    /**
     * [输入所需要判断的内容，是否包含了所需要的内容](Input the content to be judged, whether it contains the required content)
     * @description: zh - 输入所需要判断的内容，是否包含了所需要的内容
     * @description: en - Input the content to be judged, whether it contains the required content
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/24 9:08 上午
     * @param string: [需要判断的内容](What needs to be judged)
     * @param testChars: [包含的内容](Content included)
     * @return boolean
    */
    public static boolean containsAny(CharSequence string, char... testChars) {
        if (!isEmpty(string)) {
            int len = string.length();
            for(int i = Constant.ZERO; i < len; ++i) {
                if (ArrayUtil.contains(testChars, string.charAt(i))) {
                    return Constant.TRUE;
                }
            }
        }
        return Constant.FALSE;
    }

    /**
     * [通过容量进行建设](Building through capacity)
     * @description: zh - 通过容量进行建设
     * @description: en - Building through capacity
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/6 3:12 下午
     * @param capacity: [容量](capacity)
     * @return java.lang.StringBuilder
    */
    public static StringBuilder builder(int capacity) {
        return new StringBuilder(capacity);
    }

    /**
     * [移除字符串中所有给定字符串](Removes all given strings from a string)
     * @description: zh - 移除字符串中所有给定字符串
     * @description: en - Removes all given strings from a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 9:38 上午
     * @param str: 需要移除的字符串
     * @param strToRemove: 被移除的字符串
     * @return java.lang.String
    */
    public static String removeAll(CharSequence str, CharSequence strToRemove) {
        // strToRemove如果为空， 也不用继续后面的逻辑
        if (isEmpty(str) || isEmpty(strToRemove)) {
            return str(str);
        }
        return str.toString().replace(strToRemove, Constant.EMPTY);
    }

    /**
     * [去除字符串中指定的多个字符，如有多个则全部去除](Remove multiple characters specified in the string. If there are multiple characters, remove them all)
     * @description: zh - 去除字符串中指定的多个字符，如有多个则全部去除
     * @description: en - Remove multiple characters specified in the string. If there are multiple characters, remove them all
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 9:09 上午
     * @param str: 字符串
     * @param chars: 字符列表
     * @return java.lang.String
    */
    public static String removeAll(CharSequence str, char... chars) {
        if (null == str || ArrayUtil.isEmpty(chars)) {
            return str(str);
        }
        final int len = str.length();
        if (Constant.ZERO == len) {
            return str(str);
        }
        final StringBuilder builder = new StringBuilder(len);
        char c;
        for (int i = Constant.ZERO; i < len; i++) {
            c = str.charAt(i);
            if (!ArrayUtil.contains(chars, c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * [指定字符是否在字符串中出现过](Specifies whether the character has ever appeared in a string)
     * @description: zh - 指定字符是否在字符串中出现过
     * @description: en - Specifies whether the character has ever appeared in a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/8 9:57 上午
     * @param str: [字符串](character string)
     * @param searchChar: [被查找的字符串](The string being looked up)
     * @return boolean
    */
    public static boolean contains(CharSequence str, char searchChar) {
        return indexOf(str, searchChar) > Constant.NEGATIVE_ONE;
    }

    /**
     * [指定字符是否在字符串中出现过](Specifies whether the character has ever appeared in a string)
     * @description: zh - 指定字符是否在字符串中出现过
     * @description: en - Specifies whether the character has ever appeared in a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/8 10:00 上午
     * @param str: [字符串](character string)
     * @param searchChar: [被查找的字符串](The string being looked up)
     * @return int
    */
    public static int indexOf(final CharSequence str, char searchChar) {
        return indexOf(str, searchChar, Constant.ZERO);
    }

    /**
     * [指定范围内查找指定字符](Find the specified character in the specified range)
     * @description: zh - 指定范围内查找指定字符
     * @description: en - Find the specified character in the specified range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/8 10:01 上午
     * @param str: [字符串](character string)
     * @param searchChar: [被查找的字符](The character being looked up)
     * @param start: [起始位置，如果小于0，则从0开始进行查找](Starting position. If it is less than 0, the search starts from 0)
     * @return int
    */
    public static int indexOf(CharSequence str, char searchChar, int start) {
        if (str instanceof String) {
            return ((String) str).indexOf(searchChar, start);
        } else {
            return indexOf(str, searchChar, start, Constant.NEGATIVE_ONE);
        }
    }

    /**
     * [指定范围内查找指定字符](Find the specified character in the specified range)
     * @description: zh - 指定范围内查找指定字符
     * @description: en - Find the specified character in the specified range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/8 10:02 上午
     * @param str: [字符串](character string)
     * @param searchChar: [被查找的字符](The character being looked up)
     * @param start: [起始位置](Starting position)
     * @param end: [终止位置](Termination position)
     * @return int
    */
    public static int indexOf(final CharSequence str, char searchChar, int start, int end) {
        if (isEmpty(str)) { return Constant.NEGATIVE_ONE; }
        final int len = str.length();
        if (start < Constant.ZERO || start > len) { start = Constant.ZERO; }
        if (end > len || end < Constant.ZERO) { end = len; }
        for (int i = start; i < end; i++) {
            if (str.charAt(i) == searchChar) { return i; }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [简单将占位符 {} 按照顺序替换为参数](Simply replace the placeholder {} with a parameter in order)
     * @description: zh - 简单将占位符 {} 按照顺序替换为参数
     * @description: en - Simply replace the placeholder {} with a parameter in order
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 10:11 下午
     * @param template: 文本模板，被替换的部分用 {} 表示，如果模板为null，返回"null"
     * @param params: 参数值
     * @return java.lang.String
    */
    public static String format(String template, Object... params) {
        if (null == template) {
            return Constant.STRING_NULL;
        }
        if (ArrayUtil.isEmpty(params) || isBlank(template)) {
            return template.toString();
        }
        return StrFormatter.format(template.toString(), params);
    }

    /**
     * [有序的格式化文本，使用{number}做为占位符](Orderly formatted text, using {number} as a placeholder)
     * @description: zh - 有序的格式化文本，使用{number}做为占位符
     * @description: en - Orderly formatted text, using {number} as a placeholder
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 10:11 下午
     * @param pattern: 文本格式
     * @param arguments: 参数
     * @return java.lang.String
    */
    public static String indexedFormat(CharSequence pattern, Object... arguments) {
        return MessageFormat.format(pattern.toString(), arguments);
    }

    // 从…开始 ------------------------------------------------------------------------ startWith

    /**
     * [是否以指定字符串开头，忽略大小写](Whether to start with the specified string, ignoring case)
     * @description: zh - 是否以指定字符串开头，忽略大小写
     * @description: en - Whether to start with the specified string, ignoring case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 10:15 下午
     * @param str: 被监测字符串
     * @param prefix: 开头字符串
     * @return boolean
    */
    public static boolean startWithIgnoreCase(CharSequence str, CharSequence prefix) {
        return startWith(str, prefix, true);
    }

    /**
     * [是否以指定字符串开头，忽略相等字符串的情况](Whether to start with the specified string, ignoring the case of equal strings)
     * @description: zh - 是否以指定字符串开头，忽略相等字符串的情况
     * @description: en - Whether to start with the specified string, ignoring the case of equal strings
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 11:12 上午
     * @param str: 被监测字符串
     * @param prefix: 开头字符串
     * @return boolean
    */
    public static boolean startWithIgnoreEquals(CharSequence str, CharSequence prefix) {
        return startWith(str, prefix, Constant.FALSE, Constant.TRUE);
    }

    /**
     * [是否以指定字符串开头](Whether to start with the specified string)
     * @description: zh - 是否以指定字符串开头
     * @description: en - Whether to start with the specified string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 10:16 下午
     * @param str: 被监测字符串
     * @param prefix: 开头字符串
     * @param ignoreCase: 是否忽略大小写
     * @return boolean
    */
    public static boolean startWith(CharSequence str, CharSequence prefix, boolean ignoreCase) {
        return startWith(str, prefix, ignoreCase, false);
    }

    /**
     * [是否以指定字符串开头](Whether to start with the specified string)
     * @description: zh - 是否以指定字符串开头
     * @description: en - Whether to start with the specified string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 10:19 下午
     * @param str: 被监测字符串
     * @param prefix: 开头字符串
     * @param ignoreCase: 是否忽略大小写
     * @param ignoreEquals: 是否忽略字符串相等的情况
     * @return boolean
    */
    public static boolean startWith(CharSequence str, CharSequence prefix, boolean ignoreCase, boolean ignoreEquals) {
        if (Constant.NULL == str || Constant.NULL == prefix) {
            if (!ignoreEquals) {
                return Constant.FALSE;
            }
            return Constant.NULL == str && Constant.NULL == prefix;
        }
        boolean isStartWith;
        if (ignoreCase) {
            isStartWith = str.toString().toLowerCase().startsWith(prefix.toString().toLowerCase());
        } else {
            isStartWith = str.toString().startsWith(prefix.toString());
        }
        if (isStartWith) {
            return (!ignoreEquals) || (!equals(str, prefix, ignoreCase));
        }
        return Constant.FALSE;
    }

    // 比较 ------------------------------------------------------------------------ equals

    /**
     * [比较两个字符串是否相等。](Compares two strings for equality.)
     * @description: zh - 比较两个字符串是否相等。
     * @description: en - Compares two strings for equality.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 10:20 下午
     * @param str1: 要比较的字符串1
     * @param str2: 要比较的字符串2
     * @param ignoreCase: 是否忽略大小写
     * @return boolean
    */
    public static boolean equals(CharSequence str1, CharSequence str2, boolean ignoreCase) {
        // 只有两个都为null才判断相等
        if (Constant.NULL == str1) { return str2 == Constant.NULL; }
        // 字符串2空，字符串1非空，直接false
        if (Constant.NULL == str2) { return Constant.FALSE; }
        return ignoreCase ? str1.toString().equalsIgnoreCase(str2.toString()) : str1.toString().contentEquals(str2);
    }

    /**
     * [去掉指定前缀](Remove the specified prefix)
     * @description: zh - 去掉指定前缀
     * @description: en - Remove the specified prefix
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 11:13 上午
     * @param str: 字符串
     * @param prefix: 前缀
     * @return java.lang.String
    */
    public static String removePrefix(CharSequence str, CharSequence prefix) {
        if (isEmpty(str) || isEmpty(prefix)) { return str(str); }
        final String str2 = str.toString();
        return str2.startsWith(prefix.toString()) ? subSuf(str2, prefix.length()) : str2;
    }

    // 重复 ------------------------------------------------------------------------ repeat

    /**
     * [重复某个字符](Repeat a character)
     * @description: zh - 重复某个字符
     * @description: en - Repeat a character
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 11:14 上午
     * @param c: 被重复的字符
     * @param count: 重复的数目
     * @return java.lang.String
    */
    public static String repeat(char c, int count) {
        if (count <= Constant.ZERO) { return Constant.EMPTY; }
        char[] result = new char[count];
        for (int i = Constant.ZERO; i < count; i++) { result[i] = c; }
        return new String(result);
    }

    /**
     * [重复某个字符串](Repeat a string)
     * @description: zh - 重复某个字符串
     * @description: en - Repeat a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 11:22 上午
     * @param str: 被重复的字符
     * @param count: 重复的数目
     * @return java.lang.String
    */
    public static String repeat(CharSequence str, int count) {
        if (Constant.NULL == str) { return Constant.STRING_NULL; }
        if (count <= Constant.ZERO || str.length() == Constant.ZERO) { return Constant.EMPTY; }
        if (count == Constant.ONE) { return str.toString(); }
        // 检查
        final int len = str.length();
        final long longSize = (long) len * (long) count;
        final int size = (int) longSize;
        if (size != longSize) {
            throw new ArrayIndexOutOfBoundsException("Required String length is too large: " + longSize);
        }
        final char[] array = new char[size];
        str.toString().getChars(Constant.ZERO, len, array, Constant.ZERO);
        int n;
        for (n = len; n < size - n; n <<= Constant.ONE) {
            // n <<= 1相当于n *2
            System.arraycopy(array, Constant.ZERO, array, n, n);
        }
        System.arraycopy(array, Constant.ZERO, array, n, size - n);
        return new String(array);
    }

    /**
     * [是否包含特定字符，忽略大小写，如果给定两个参数都为null，返回true](Whether to include specific characters, regardless of case. If both parameters are null, return true)
     * @description: zh - 是否包含特定字符，忽略大小写，如果给定两个参数都为null，返回true
     * @description: en - Whether to include specific characters, regardless of case. If both parameters are null, return true
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 11:22 上午
     * @param str: 被检测字符串
     * @param testStr: 被测试是否包含的字符串
     * @return boolean
    */
    public static boolean containsIgnoreCase(CharSequence str, CharSequence testStr) {
        if (Constant.NULL == str) {
            // 如果被监测字符串和
            return Constant.NULL == testStr;
        }
        return str.toString().toLowerCase().contains(testStr.toString().toLowerCase());
    }

    /**
     * [查找指定字符串是否包含指定字符串列表中的任意一个字符串,忽略大小写](Finds whether the specified string contains any string in the specified string list, regardless of case)
     * @description: zh - 查找指定字符串是否包含指定字符串列表中的任意一个字符串,忽略大小写
     * @description: en - Finds whether the specified string contains any string in the specified string list, regardless of case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 11:19 上午
     * @param str: 指定字符串
     * @param testStrs: 需要检查的字符串数组
     * @return boolean
    */
    public static boolean containsAnyIgnoreCase(CharSequence str, CharSequence... testStrs) {
        return Constant.NULL != getContainsStrIgnoreCase(str, testStrs);
    }

    /**
     * [查找指定字符串是否包含指定字符串列表中的任意一个字符串，如果包含返回找到的第一个字符串,忽略大小写](Find whether the specified string contains any string in the specified string list. If it contains the first string found, ignore case)
     * @description: zh - 查找指定字符串是否包含指定字符串列表中的任意一个字符串，如果包含返回找到的第一个字符串,忽略大小写
     * @description: en - Find whether the specified string contains any string in the specified string list. If it contains the first string found, ignore case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 11:21 上午
     * @param str: 指定字符串
     * @param testStrs: 需要检查的字符串数组
     * @return java.lang.String
    */
    public static String getContainsStrIgnoreCase(CharSequence str, CharSequence... testStrs) {
        if (isEmpty(str) || ArrayUtil.isEmpty(testStrs)) {
            return Constant.STRING_NULL;
        }
        for (CharSequence testStr : testStrs) {
            if (containsIgnoreCase(str, testStr)) {
                return testStr.toString();
            }
        }
        return Constant.STRING_NULL;
    }

    // 总数 ------------------------------------------------------------------------ count

    /**
     * [统计指定内容中包含指定字符的数量](Count the number of specified characters in the specified content)
     * @description: zh - 统计指定内容中包含指定字符的数量
     * @description: en - Count the number of specified characters in the specified content
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 11:26 上午
     * @param content: 内容
     * @param charForSearch: 被统计的字符
     * @return int
    */
    public static int count(CharSequence content, char charForSearch) {
        int count = Constant.ZERO;
        if (isEmpty(content)) {
            return Constant.ZERO;
        }
        int contentLength = content.length();
        for (int i = Constant.ZERO; i < contentLength; i++) {
            if (charForSearch == content.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * [统计指定内容中包含指定字符串的数量](Counts the number of specified strings in the specified content)
     * @description: zh - 统计指定内容中包含指定字符串的数量
     * @description: en - Counts the number of specified strings in the specified content
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 11:31 上午
     * @param content: 被查找的字符串
     * @param strForSearch: 需要查找的字符串
     * @return int
    */
    public static int count(CharSequence content, CharSequence strForSearch) {
        if (isEmpty(content, strForSearch) || strForSearch.length() > content.length()) {
            return Constant.ZERO;
        }

        int count = Constant.ZERO;
        int idx = Constant.ZERO;
        final String content2 = content.toString();
        final String strForSearch2 = strForSearch.toString();
        while ((idx = content2.indexOf(strForSearch2, idx)) > Constant.NEGATIVE_ONE) {
            count++;
            idx += strForSearch.length();
        }
        return count;
    }


}
