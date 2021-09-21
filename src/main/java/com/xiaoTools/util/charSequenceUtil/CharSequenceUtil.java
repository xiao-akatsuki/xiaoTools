package com.xiaoTools.util.charSequenceUtil;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.filter.Filter;
import com.xiaoTools.core.stringFormatter.StrFormatter;
import com.xiaoTools.core.text.strSpliter.StrSpliter;
import com.xiaoTools.core.text.stringBuilder.StrBuilder;
import com.xiaoTools.core.versionComparator.VersionComparator;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.charUtil.CharUtil;
import com.xiaoTools.util.charsetUtil.CharsetUtil;
import com.xiaoTools.util.desensitizedUtil.DesensitizedUtil;
import com.xiaoTools.util.numUtil.NumUtil;
import com.xiaoTools.util.regularUtil.RegularUtil;
import com.xiaoTools.util.regularUtil.method.Func1;
import com.xiaoTools.util.strUtil.StrUtil;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Matcher;

/**
 * [CharSequence 相关工具类封装](Encapsulation of CharSequence related tool classes)
 * @description: zh - CharSequence 相关工具类封装
 * @description: en - Encapsulation of CharSequence related tool classes
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/7/4 7:34 下午
*/
public class CharSequenceUtil {

    /**
     * [字符串是否为空白](Is the string blank)
     * @description: zh - 字符串是否为空白
     * @description: en - Is the string blank
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 8:51 下午
     * @param str: 被检测的字符串
     * @return boolean
    */
    public static boolean isBlank(CharSequence str) {
        int length;
        if ((str == Constant.NULL) || ((length = str.length()) == Constant.ZERO)) {
            return Constant.TRUE;
        }
        for (int i = Constant.ZERO; i < length; i++) {
            // 只要有一个非空字符即为非空字符串
            if (!CharUtil.isBlankChar(str.charAt(i))) {
                return Constant.FALSE;
            }
        }
        return Constant.TRUE;
    }

    /**
     * [字符串是否为非空白](Is the string non blank)
     * @description: zh - 字符串是否为非空白
     * @description: en - Is the string non blank
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 9:03 下午
     * @param str: 被检测的字符串
     * @return boolean
    */
    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    /**
     * [指定字符串数组中，是否包含空字符串](Specifies whether the string array contains an empty string)
     * @description: zh - 指定字符串数组中，是否包含空字符串
     * @description: en - Specifies whether the string array contains an empty string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 9:05 下午
     * @param strs: 字符串列表
     * @return boolean
    */
    public static boolean hasBlank(CharSequence... strs) {
        if (ArrayUtil.isEmpty(strs)) {
            return Constant.TRUE;
        }
        for (CharSequence str : strs) {
            if (isBlank(str)) {
                return Constant.TRUE;
            }
        }
        return Constant.FALSE;
    }

    /**
     * [指定字符串数组中的元素，是否全部为空字符串。](Specifies whether all the elements in the string array are empty strings.)
     * @description: zh - 指定字符串数组中的元素，是否全部为空字符串。
     * @description: en - Specifies whether all the elements in the string array are empty strings.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 9:08 下午
     * @param strs: 字符串列表
     * @return boolean
    */
    public static boolean isAllBlank(CharSequence... strs) {
        if (ArrayUtil.isEmpty(strs)) {
            return Constant.TRUE;
        }

        for (CharSequence str : strs) {
            if (isNotBlank(str)) {
                return Constant.FALSE;
            }
        }
        return Constant.TRUE;
    }

    /**
     * [字符串是否为空](Is the string empty)
     * @description: zh - 字符串是否为空
     * @description: en - Is the string empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 9:08 下午
     * @param str: 被检测的字符串
     * @return boolean
    */
    public static boolean isEmpty(CharSequence str) {
        return str == Constant.NULL || str.length() == Constant.ZERO;
    }

    /**
     * [字符串是否为非空白](Whether the string is non-blank)
     * @description: zh - 字符串是否为非空白
     * @description: en - Whether the string is non-blank
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/5 8:32 上午
     * @param str: 被检测的字符串
     * @return boolean
    */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * [当给定字符串为null时，转换为Empty](When the given string is 「null」, it is converted to 「empty」)
     * @description: zh - 当给定字符串为null时，转换为Empty
     * @description: en - When the given string is 「null」, it is converted to 「empty」
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/5 8:37 上午
     * @param str: 被检测的字符串
     * @return java.lang.String
    */
    public static String emptyIfNull(CharSequence str) {
        return nullToEmpty(str);
    }

    /**
     * [当给定字符串为null时，转换为Empty](When the given string is null, it is converted to empty)
     * @description: zh - 当给定字符串为null时，转换为Empty
     * @description: en - When the given string is null, it is converted to empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/5 8:39 上午
     * @param str: 被检查的字符串
     * @return java.lang.String
    */
    public static String nullToEmpty(CharSequence str) {
        return nullToDefault(str, Constant.EMPTY);
    }

    /**
     * [当给定的字符串为「null」时，转换成为默认字符串](When the given string is 「null」, it is converted to the default string)
     * @description: zh - 当给定的字符串为「null」时，转换成为默认字符串
     * @description: en - When the given string is 「null」, it is converted to the default string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/5 8:41 上午
     * @param str: 被检查的字符串
     * @param defaultStr: 默认字符串
     * @return java.lang.String
    */
    public static String nullToDefault(CharSequence str, String defaultStr) {
        return (str == Constant.NULL) ? defaultStr : str.toString();
    }

    /**
     * [如果字符串是null或者""，则返回指定默认字符串，否则返回字符串本身。](If the string is null or '', the specified default string is returned; otherwise, the string itself is returned.)
     * @description: zh - 如果字符串是null或者""，则返回指定默认字符串，否则返回字符串本身。
     * @description: en - If the string is null or '', the specified default string is returned; otherwise, the string itself is returned.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/5 8:43 上午
     * @param str: 要转换的字符串
     * @param defaultStr: 默认字符串
     * @return java.lang.String
    */
    public static String emptyToDefault(CharSequence str, String defaultStr) {
        return isEmpty(str) ? defaultStr : str.toString();
    }

    /**
     * [如果字符串是null或者""或者空白，则返回指定默认字符串，否则返回字符串本身。](If the string is null or "" or blank, the specified default string is returned; otherwise, the string itself is returned.)
     * @description: zh - 如果字符串是null或者""或者空白，则返回指定默认字符串，否则返回字符串本身。
     * @description: en - If the string is null or "" or blank, the specified default string is returned; otherwise, the string itself is returned.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/5 8:44 上午
     * @param str: 要转换的字符串
     * @param defaultStr: 默认字符串
     * @return java.lang.String
    */
    public static String blankToDefault(CharSequence str, String defaultStr) {
        return isBlank(str) ? defaultStr : str.toString();
    }

    /**
     * [当给定字符串为空字符串时，转换为null](When the given string is an empty string, it is converted to null)
     * @description: zh - 当给定字符串为空字符串时，转换为null
     * @description: en - When the given string is an empty string, it is converted to null
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/5 8:56 上午
     * @param str: 被转换的字符串
     * @return java.lang.String
    */
    public static String emptyToNull(CharSequence str) {
        return isEmpty(str) ? null : str.toString();
    }

    /**
     * [是否包含空字符串](Contains an empty string)
     * @description: zh - 是否包含空字符串
     * @description: en - Contains an empty string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/5 8:58 上午
     * @param strs: 字符串列表
     * @return boolean
    */
    public static boolean hasEmpty(CharSequence... strs) {
        if (ArrayUtil.isEmpty(strs)) { return Constant.TRUE; }
        for (CharSequence str : strs) {
            if (isEmpty(str)) { return Constant.TRUE; }
        }
        return Constant.FALSE;
    }

    /**
     * [指定字符串数组中的元素，是否全部为空字符串。](Specifies whether all the elements in the string array are empty strings.)
     * @description: zh - 指定字符串数组中的元素，是否全部为空字符串。
     * @description: en - Specifies whether all the elements in the string array are empty strings.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/5 9:03 上午
     * @param strs: 字符串列表
     * @return boolean
    */
    public static boolean isAllEmpty(CharSequence... strs) {
        if (ArrayUtil.isEmpty(strs)) { return Constant.TRUE; }
        for (CharSequence str : strs) {
            if (isNotEmpty(str)) { return Constant.FALSE; }
        }
        return Constant.TRUE;
    }

    /**
     * [指定字符串数组中的元素，是否都不为空字符串。](Specifies whether the elements in the string array are not empty strings.)
     * @description: zh - 指定字符串数组中的元素，是否都不为空字符串。
     * @description: en - Specifies whether the elements in the string array are not empty strings.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/5 9:04 上午
     * @param args: 字符串数组
     * @return boolean
    */
    public static boolean isAllNotEmpty(CharSequence... args) {
        return !hasEmpty(args);
    }

    /**
     * [是否存都不为null或空对象或空白符的对象](Do you want to save objects that are not null or empty or blank)
     * @description: zh - 是否存都不为null或空对象或空白符的对象
     * @description: en - Do you want to save objects that are not null or empty or blank
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/5 9:05 上午
     * @param args: 字符串数组
     * @return boolean
    */
    public static boolean isAllNotBlank(CharSequence... args) {
        return !hasBlank(args);
    }

    /**
     * [检查字符串是否为null、“null”、“undefined”](Check whether the string is null, "null", undefined)
     * @description: zh - 检查字符串是否为null、“null”、“undefined”
     * @description: en - Check whether the string is null, "null", undefined
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/5 9:06 上午
     * @param str: 被检查的字符串
     * @return boolean
    */
    public static boolean isNullOrUndefined(CharSequence str) {
        return Constant.NULL == str ? Constant.TRUE : isNullOrUndefinedStr(str);
    }

    /**
     * [检查字符串是否为null、“null”、“undefined”](Check whether the string is null, "null", undefined)
     * @description: zh - 检查字符串是否为null、“null”、“undefined”
     * @description: en - Check whether the string is null, "null", undefined
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/5 9:09 上午
     * @param str: 被检查的字符串
     * @return boolean
    */
    public static boolean isEmptyOrUndefined(CharSequence str) {
        return isEmpty(str) ? Constant.TRUE : isNullOrUndefinedStr(str);
    }

    /**
     * [检查字符串是否为null、“null”、“undefined”](Check whether the string is null, "null", undefined)
     * @description: zh - 检查字符串是否为null、“null”、“undefined”
     * @description: en - Check whether the string is null, "null", undefined
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/5 9:15 上午
     * @param str: 被检查的字符串
     * @return boolean
    */
    public static boolean isBlankOrUndefined(CharSequence str) {
        return isBlank(str) ? Constant.TRUE : isNullOrUndefinedStr(str);
    }

    /**
     * [是否为“null”、“undefined”，不做空指针检查](Whether it is "null" or "undefined", do not check the short pointer)
     * @description: zh - 是否为“null”、“undefined”，不做空指针检查
     * @description: en - Whether it is "null" or "undefined", do not check the short pointer
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/5 9:16 上午
     * @param str: 字符串
     * @return boolean
    */
    private static boolean isNullOrUndefinedStr(CharSequence str) {
        String strString = str.toString().trim();
        return Constant.STRING_NULL_OUT.equals(strString) || Constant.STRING_UNDEFINED.equals(strString);
    }

    /*截取-----------------------------------------------------------trim*/

    /**
     * [除去字符串头尾部的空白，如果字符串是null，依然返回null。](Remove the blanks at the beginning and end of the string. If the string is null, null is still returned.)
     * @description: zh - 除去字符串头尾部的空白，如果字符串是null，依然返回null。
     * @description: en - Remove the blanks at the beginning and end of the string. If the string is null, null is still returned.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/6 7:45 上午
     * @param str: 要处理的字符串
     * @return java.lang.String
    */
    public static String trim(CharSequence str) {
        return (Constant.NULL == str) ? Constant.STRING_NULL : trim(str, 0);
    }

    /**
     * [除去字符串头尾部的空白，如果字符串是null，则返回""。](Remove the blanks at the beginning and end of the string, and return "" if the string is null.)
     * @description: zh - 除去字符串头尾部的空白，如果字符串是null，则返回""。
     * @description: en - Remove the blanks at the beginning and end of the string, and return "" if the string is null.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/6 7:46 上午
     * @param str: 要处理的字符串
     * @return java.lang.String
    */
    public static String trimToEmpty(CharSequence str) {
        return str == Constant.NULL ? Constant.EMPTY : trim(str);
    }

    /**
     * [除去字符串头尾部的空白，如果字符串是null或者""，返回null](Remove the blanks at the beginning and end of the string. If the string is null or '', null is returned)
     * @description: zh - 除去字符串头尾部的空白，如果字符串是null或者""，返回null
     * @description: en - Remove the blanks at the beginning and end of the string. If the string is null or '', null is returned
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/6 7:48 上午
     * @param str: 要处理的字符串
     * @return java.lang.String
    */
    public static String trimToNull(CharSequence str) {
        final String trimStr = trim(str);
        return Constant.EMPTY.equals(trimStr) ? Constant.STRING_NULL : trimStr;
    }

    /**
     * [除去字符串头部的空白，如果字符串是null，则返回null。](Remove the blanks in the head of the string, and return null if the string is null.)
     * @description: zh - 除去字符串头部的空白，如果字符串是null，则返回null。
     * @description: en - Remove the blanks in the head of the string, and return null if the string is null.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/6 7:49 上午
     * @param str: 要处理的字符串
     * @return java.lang.String
    */
    public static String trimStart(CharSequence str) {
        return trim(str, -1);
    }

    /**
     * [除去字符串尾部的空白，如果字符串是null，则返回null。](Remove the blanks at the end of the string and return null if the string is null.)
     * @description: zh - 除去字符串尾部的空白，如果字符串是null，则返回null。
     * @description: en - Remove the blanks at the end of the string and return null if the string is null.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/6 7:50 上午
     * @param str: 要处理的字符串
     * @return java.lang.String
    */
    public static String trimEnd(CharSequence str) {
        return trim(str, 1);
    }

    /**
     * [除去字符串头尾部的空白符，如果字符串是null，依然返回null。](Remove the blanks at the beginning and end of the string, and return null if the string is null.)
     * @description: zh - 除去字符串头尾部的空白符，如果字符串是null，依然返回null。
     * @description: en - Remove the blanks at the beginning and end of the string, and return null if the string is null.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/6 7:51 上午
     * @param str: 要处理的字符串
     * @param mode: -1表示trimStart，0表示trim全部， 1表示trimEnd
     * @return java.lang.String
    */
    public static String trim(CharSequence str, int mode) {
        String result;
        if (str == Constant.NULL) {
            result = Constant.STRING_NULL;
        } else {
            int length = str.length();
            int start = Constant.ZERO;
            // 扫描字符串头部
            int end = length;
            if (mode <= Constant.ZERO) {
                while ((start < end) && (CharUtil.isBlankChar(str.charAt(start)))) {
                    start++;
                }
            }// 扫描字符串尾部
            if (mode >= Constant.ZERO) {
                while ((start < end) && (CharUtil.isBlankChar(str.charAt(end - Constant.ONE)))) {
                    end--;
                }
            }
            if ((start > Constant.ZERO) || (end < length)) {
                result = str.toString().substring(start, end);
            } else {
                result = str.toString();
            }
        }
        return result;
    }

    /*是否开始-----------------------------------------------------------startWith*/

    /**
     * [字符串是否以给定字符开始](Whether the string starts with the given character)
     * @description: zh - 字符串是否以给定字符开始
     * @description: en - Whether the string starts with the given character
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/7 9:45 下午
     * @param str: 字符串
     * @param c: 字符
     * @return boolean
    */
    public static boolean startWith(CharSequence str, char c) {
        return isEmpty(str) ? Constant.FALSE : c == str.charAt(Constant.ZERO);
    }

    /**
     * [是否以指定字符串开头](Whether to start with the specified string)
     * @description: zh - 是否以指定字符串开头
     * @description: en - Whether to start with the specified string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/7 9:55 下午
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
     * @since 2021/7/7 9:57 下午
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
        isStartWith = ignoreCase ?
                str.toString().toLowerCase().startsWith(prefix.toString().toLowerCase()) :
                str.toString().startsWith(prefix.toString());

        return isStartWith ? (!ignoreEquals) || (!equals(str, prefix, ignoreCase)) : Constant.FALSE;
    }

    /**
     * [是否以指定字符串开头](Whether to start with the specified string)
     * @description: zh - 是否以指定字符串开头
     * @description: en - Whether to start with the specified string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/7 10:05 下午
     * @param str: 被监测字符串
     * @param prefix: 开头字符串
     * @return boolean
    */
    public static boolean startWith(CharSequence str, CharSequence prefix) {
        return startWith(str, prefix, Constant.FALSE);
    }

    /**
     * [是否以指定字符串开头，忽略相等字符串的情况](Whether to start with the specified string, ignoring the case of equal strings)
     * @description: zh - 是否以指定字符串开头，忽略相等字符串的情况
     * @description: en - Whether to start with the specified string, ignoring the case of equal strings
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/7 10:06 下午
     * @param str: 被监测字符串
     * @param prefix: 开头字符串
     * @return boolean
    */
    public static boolean startWithIgnoreEquals(CharSequence str, CharSequence prefix) {
        return startWith(str, prefix, Constant.FALSE, Constant.TRUE);
    }

    /**
     * [是否以指定字符串开头，忽略大小写](Whether to start with the specified string, ignoring case)
     * @description: zh - 是否以指定字符串开头，忽略大小写
     * @description: en - Whether to start with the specified string, ignoring case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/7 10:07 下午
     * @param str: 被监测字符串
     * @param prefix: 开头字符串
     * @return boolean
    */
    public static boolean startWithIgnoreCase(CharSequence str, CharSequence prefix) {
        return startWith(str, prefix, Constant.TRUE);
    }

    /**
     * [给定字符串是否以任何一个字符串开始](Whether the given string starts with any string)
     * @description: zh - 给定字符串是否以任何一个字符串开始
     * @description: en - Whether the given string starts with any string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/7 10:10 下午
     * @param str: 给定字符串
     * @param prefixes: 需要检测的开始字符串
     * @return boolean
    */
    public static boolean startWithAny(CharSequence str, CharSequence... prefixes) {
        if (isEmpty(str) || ArrayUtil.isEmpty(prefixes)) {
            return Constant.FALSE;
        }

        for (CharSequence suffix : prefixes) {
            if (startWith(str, suffix, Constant.FALSE)) {
                return Constant.TRUE;
            }
        }
        return Constant.FALSE;
    }

    /*是否结束-----------------------------------------------------------endWith*/

    /**
     * [字符串是否以给定字符结尾](Whether the string ends with the given character)
     * @description: zh - 字符串是否以给定字符结尾
     * @description: en - Whether the string ends with the given character
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 1:39 下午
     * @param str: 字符串
     * @param c: 字符
     * @return boolean
    */
    public static boolean endWith(CharSequence str, char c) {
        return isEmpty(str) ? Constant.FALSE : c == str.charAt(str.length() - Constant.ONE);
    }

    /**
     * [是否以指定字符串结尾](Whether to end with the specified string)
     * @description: zh - 是否以指定字符串结尾
     * @description: en - Whether to end with the specified string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 1:45 下午
     * @param str: 被监测字符串
     * @param suffix: 结尾字符串
     * @param isIgnoreCase: 是否忽略大小写
     * @return boolean
    */
    public static boolean endWith(CharSequence str, CharSequence suffix, boolean isIgnoreCase) {
        if (Constant.NULL == str || Constant.NULL == suffix) {
            return Constant.NULL == str && Constant.NULL == suffix;
        }
        return isIgnoreCase ? str.toString().toLowerCase().endsWith(suffix.toString().toLowerCase()) : str.toString().endsWith(suffix.toString());
    }

    /**
     * [是否以指定字符串结尾](Whether to end with the specified string)
     * @description: zh - 是否以指定字符串结尾
     * @description: en - Whether to end with the specified string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 1:59 下午
     * @param str: 被监测字符串
     * @param suffix: 结尾字符串
     * @return boolean
    */
    public static boolean endWith(CharSequence str, CharSequence suffix) {
        return endWith(str, suffix, Constant.FALSE);
    }

    /**
     * [是否以指定字符串结尾，忽略大小写](Whether to end with the specified string, ignoring case)
     * @description: zh - 是否以指定字符串结尾，忽略大小写
     * @description: en - Whether to end with the specified string, ignoring case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 3:59 下午
     * @param str: 被监测字符串
     * @param suffix: 结尾字符串
     * @return boolean
    */
    public static boolean endWithIgnoreCase(CharSequence str, CharSequence suffix) {
        return endWith(str, suffix, Constant.TRUE);
    }

    /**
     * [给定字符串是否以任何一个字符串结尾](Whether the given string ends with any string)
     * @description: zh - 给定字符串是否以任何一个字符串结尾
     * @description: en - Whether the given string ends with any string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 4:01 下午
     * @param str: 给定字符串
     * @param suffixes: 需要检测的结尾字符串
     * @return boolean
    */
    public static boolean endWithAny(CharSequence str, CharSequence... suffixes) {
        if (isEmpty(str) || ArrayUtil.isEmpty(suffixes)) {
            return Constant.FALSE;
        }
        for (CharSequence suffix : suffixes) {
            if (endWith(str, suffix, Constant.FALSE)) {
                return Constant.TRUE;
            }
        }
        return  Constant.FALSE;
    }

    /**
     * [给定字符串是否以任何一个字符串结尾（忽略大小写）](Whether the given string ends with any string (case ignored))
     * @description: zh - 给定字符串是否以任何一个字符串结尾（忽略大小写）
     * @description: en - Whether the given string ends with any string (case ignored)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 4:05 下午
     * @param str: 给定字符串
     * @param suffixes: 需要检测的结尾字符串
     * @return boolean
    */
    public static boolean endWithAnyIgnoreCase(CharSequence str, CharSequence... suffixes) {
        if (isEmpty(str) || ArrayUtil.isEmpty(suffixes)) {
            return Constant.FALSE;
        }

        for (CharSequence suffix : suffixes) {
            if (endWith(str, suffix, Constant.TRUE)) {
                return Constant.TRUE;
            }
        }
        return Constant.FALSE;
    }

    /*包含-----------------------------------------------------------contains*/

    /**
     * [指定字符是否在字符串中出现过](Specifies whether the character has ever appeared in a string)
     * @description: zh - 指定字符是否在字符串中出现过
     * @description: en - Specifies whether the character has ever appeared in a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 4:13 下午
     * @param str: 字符串
     * @param searchChar: 被查找的字符
     * @return boolean
    */
    public static boolean contains(CharSequence str, char searchChar) {
        return indexOf(str, searchChar) > Constant.NEGATIVE_ONE;
    }

    /**
     * [指定字符串是否在字符串中出现过](Specifies whether the string ever appears in the string)
     * @description: zh - 指定字符串是否在字符串中出现过
     * @description: en - Specifies whether the string ever appears in the string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 4:27 下午
     * @param str: 字符串
     * @param searchStr: 被查找的字符串
     * @return boolean
    */
    public static boolean contains(CharSequence str, CharSequence searchStr) {
        return Constant.NULL == str || Constant.NULL == searchStr ? Constant.FALSE : str.toString().contains(searchStr);
    }

    /**
     * [查找指定字符串是否包含指定字符串列表中的任意一个字符串](Finds whether the specified string contains any string in the specified string list)
     * @description: zh - 查找指定字符串是否包含指定字符串列表中的任意一个字符串
     * @description: en - Finds whether the specified string contains any string in the specified string list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 4:28 下午
     * @param str: 指定字符串
     * @param strArray: 需要检查的字符串数组
     * @return boolean
    */
    public static boolean containsAny(CharSequence str, CharSequence... strArray) {
        return Constant.NULL != getContainsStr(str, strArray);
    }

    /**
     * [查找指定字符串是否包含指定字符列表中的任意一个字符](Finds whether the specified string contains any character in the specified character list)
     * @description: zh - 查找指定字符串是否包含指定字符列表中的任意一个字符
     * @description: en - Finds whether the specified string contains any character in the specified character list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 4:29 下午
     * @param str: 指定字符串
     * @param strArray: 需要检查的字符数组
     * @return boolean
    */
    public static boolean containsAny(CharSequence str, char... strArray) {
        if (!isEmpty(str)) {
            int len = str.length();
            for (int i = Constant.ZERO; i < len; i++) {
                if (ArrayUtil.contains(strArray, str.charAt(i))) {
                    return Constant.TRUE;
                }
            }
        }
        return Constant.FALSE;
    }

    /**
     * [检查指定字符串中是否只包含给定的字符](Checks whether the specified string contains only the given character)
     * @description: zh - 检查指定字符串中是否只包含给定的字符
     * @description: en - Checks whether the specified string contains only the given character
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 4:35 下午
     * @param str: 字符串
     * @param strArray: 检查的字符
     * @return boolean
    */
    public static boolean containsOnly(CharSequence str, char... strArray) {
        if (!isEmpty(str)) {
            int len = str.length();
            for (int i = Constant.ZERO; i < len; i++) {
                if (!ArrayUtil.contains(strArray, str.charAt(i))) {
                    return Constant.FALSE;
                }
            }
        }
        return Constant.TRUE;
    }

    /**
     * [给定字符串是否包含空白符（空白符包括空格、制表符、全角空格和不间断空格）](Whether the given string contains white space (white space includes spaces, tabs, full width spaces, and uninterrupted spaces))
     * @description: zh - 给定字符串是否包含空白符（空白符包括空格、制表符、全角空格和不间断空格）
     * @description: en - Whether the given string contains white space (white space includes spaces, tabs, full width spaces, and uninterrupted spaces)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 5:19 下午
     * @param str: 字符串
     * @return boolean
    */
    public static boolean containsBlank(CharSequence str) {
        if (Constant.NULL == str) {
            return Constant.FALSE;
        }
        final int length = str.length();
        if (Constant.ZERO == length) {
            return Constant.FALSE;
        }

        for (int i = Constant.ZERO; i < length; i += Constant.ONE) {
            if (CharUtil.isBlankChar(str.charAt(i))) {
                return Constant.TRUE;
            }
        }
        return Constant.FALSE;
    }

    /**
     * [查找指定字符串是否包含指定字符串列表中的任意一个字符串，如果包含返回找到的第一个字符串](Finds whether the specified string contains any string in the specified string list, and returns the first string found if it contains)
     * @description: zh - 查找指定字符串是否包含指定字符串列表中的任意一个字符串，如果包含返回找到的第一个字符串
     * @description: en - Finds whether the specified string contains any string in the specified string list, and returns the first string found if it contains
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 5:21 下午
     * @param str: 指定字符串
     * @param strArray: 需要检查的字符串数组
     * @return java.lang.String
    */
    public static String getContainsStr(CharSequence str, CharSequence... strArray) {
        if (isEmpty(str) || ArrayUtil.isEmpty(strArray)) {
            return Constant.STRING_NULL;
        }
        for (CharSequence checkStr : strArray) {
            if (str.toString().contains(checkStr)) {
                return checkStr.toString();
            }
        }
        return Constant.STRING_NULL;
    }

    /**
     * [是否包含特定字符，忽略大小写，如果给定两个参数都为null，返回true](Whether to include specific characters, regardless of case. If both parameters are null, return true)
     * @description: zh - 是否包含特定字符，忽略大小写，如果给定两个参数都为null，返回true
     * @description: en - Whether to include specific characters, regardless of case. If both parameters are null, return true
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 5:41 下午
     * @param str: 被检测字符串
     * @param value: 被测试是否包含的字符串
     * @return boolean
    */
    public static boolean containsIgnoreCase(CharSequence str, CharSequence value) {
        return Constant.NULL == str ?
                Constant.NULL == value :
                str.toString().toLowerCase().contains(value.toString().toLowerCase());
    }

    /**
     * [查找指定字符串是否包含指定字符串列表中的任意一个字符串](Finds whether the specified string contains any string in the specified string list)
     * @description: zh - 查找指定字符串是否包含指定字符串列表中的任意一个字符串
     * @description: en - Finds whether the specified string contains any string in the specified string list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 5:43 下午
     * @param str: 指定字符串
     * @param strArray: 需要检查的字符串数组
     * @return boolean
    */
    public static boolean containsAnyIgnoreCase(CharSequence str, CharSequence... strArray) {
        return Constant.NULL != getContainsStrIgnoreCase(str, strArray);
    }

    /**
     * [查找指定字符串是否包含指定字符串列表中的任意一个字符串，如果包含返回找到的第一个字符串](Finds whether the specified string contains any string in the specified string list, and returns the first string found if it contains)
     * @description: zh - 查找指定字符串是否包含指定字符串列表中的任意一个字符串，如果包含返回找到的第一个字符串
     * @description: en - Finds whether the specified string contains any string in the specified string list, and returns the first string found if it contains
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 5:44 下午
     * @param str: 指定字符串
     * @param strArray: 需要检查的字符串数组
     * @return java.lang.String
    */
    public static String getContainsStrIgnoreCase(CharSequence str, CharSequence... strArray) {
        if (isEmpty(str) || ArrayUtil.isEmpty(strArray)) {
            return Constant.STRING_NULL;
        }
        for (CharSequence testStr : strArray) {
            if (containsIgnoreCase(str, testStr)) {
                return testStr.toString();
            }
        }
        return Constant.STRING_NULL;
    }

    /*查找-----------------------------------------------------------indexOf*/

    /**
     * [指定范围内查找指定字符](Find the specified character in the specified range)
     * @description: zh - 指定范围内查找指定字符
     * @description: en - Find the specified character in the specified range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 5:57 下午
     * @param str: 字符串
     * @param searchValue: 被查找的字符
     * @return int
    */
    public static int indexOf(final CharSequence str, char searchValue) {
        return indexOf(str, searchValue, Constant.ZERO);
    }

    /**
     * [指定范围内查找指定字符](Find the specified character in the specified range)
     * @description: zh - 指定范围内查找指定字符
     * @description: en - Find the specified character in the specified range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 6:05 下午
     * @param str:  字符串
     * @param searchValue: 被查找的字符
     * @param start: 起始位置，如果小于0，从0开始查找
     * @return int
    */
    public static int indexOf(CharSequence str, char searchValue, int start) {
        return str instanceof String ? ((String) str).indexOf(searchValue, start) : indexOf(str, searchValue, start, -1);
    }

    /**
     * [指定范围内查找指定字符](Find the specified character in the specified range)
     * @description: zh - 指定范围内查找指定字符
     * @description: en - Find the specified character in the specified range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 6:07 下午
     * @param str: 字符串
     * @param searchValue: 被查找的字符
     * @param start: 起始位置，如果小于0，从0开始查找
     * @param end: 终止位置，如果超过str.length()则默认查找到字符串末尾
     * @return int
    */
    public static int indexOf(final CharSequence str, char searchValue, int start, int end) {
        if (isEmpty(str)) { return Constant.NEGATIVE_ONE; }
        final int len = str.length();
        if (start < Constant.ZERO || start > len) { start = Constant.ZERO; }
        if (end > len || end < Constant.ZERO) { end = len; }
        for (int i = start; i < end; i++) {
            if (str.charAt(i) == searchValue) { return i; }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [指定范围内查找字符串，忽略大小写](Find string in specified range, ignore case)
     * @description: zh - 指定范围内查找字符串，忽略大小写
     * @description: en - Find string in specified range, ignore case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 6:28 下午
     * @param str: 字符串
     * @param searchValue: 需要查找位置的字符串
     * @return int
    */
    public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchValue) {
        return indexOfIgnoreCase(str, searchValue, Constant.ZERO);
    }

    /**
     * [指定范围内查找字符串](Finds a string in the specified range)
     * @description: zh - 指定范围内查找字符串
     * @description: en - Finds a string in the specified range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 6:29 下午
     * @param str: 字符串
     * @param searchValue: 需要查找位置的字符串
     * @param fromIndex: 起始位置
     * @return int
    */
    public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchValue, int fromIndex) {
        return indexOf(str, searchValue, fromIndex, Constant.TRUE);
    }

    /**
     * [指定范围内查找字符串](Finds a string in the specified range)
     * @description: zh - 指定范围内查找字符串
     * @description: en - Finds a string in the specified range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 6:46 下午
     * @param str: 字符串
     * @param searchValue: 需要查找位置的字符串
     * @param fromIndex: 起始位置
     * @param ignoreCase: 是否忽略大小写
     * @return int
    */
    public static int indexOf(final CharSequence str, CharSequence searchValue, int fromIndex, boolean ignoreCase) {
        if (str == Constant.NULL || searchValue == Constant.NULL) { return Constant.NEGATIVE_ONE; }
        if (fromIndex < Constant.ZERO) { fromIndex = Constant.ZERO; }
        final int endLimit = str.length() - searchValue.length() + Constant.ONE;
        if (fromIndex > endLimit) { return Constant.NEGATIVE_ONE; }
        if (searchValue.length() == Constant.ZERO) { return fromIndex; }

        if (!ignoreCase) {
            // 不忽略大小写调用JDK方法
            return str.toString().indexOf(searchValue.toString(), fromIndex);
        }

        for (int i = fromIndex; i < endLimit; i++) {
            if (isSubEquals(str, i, searchValue, Constant.ZERO, searchValue.length(), Constant.TRUE)) {
                return i;
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [指定范围内查找字符串，忽略大小写](Find string in specified range, ignore case)
     * @description: zh - 指定范围内查找字符串，忽略大小写
     * @description: en - Find string in specified range, ignore case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 6:48 下午
     * @param str: 字符串
     * @param searchValue: 需要查找位置的字符串
     * @return int
    */
    public static int lastIndexOfIgnoreCase(final CharSequence str, final CharSequence searchValue) {
        return lastIndexOfIgnoreCase(str, searchValue, str.length());
    }

    /**
     * [指定范围内查找字符串，忽略大小写](Find string in specified range, ignore case)
     * @description: zh - 指定范围内查找字符串，忽略大小写
     * @description: en - Find string in specified range, ignore case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 6:53 下午
     * @param str: 字符串
     * @param searchValue: 需要查找位置的字符串
     * @param fromIndex: 起始位置，从后往前计数
     * @return int
    */
    public static int lastIndexOfIgnoreCase(final CharSequence str, final CharSequence searchValue, int fromIndex) {
        return lastIndexOf(str, searchValue, fromIndex, Constant.TRUE);
    }

    /**
     * [指定范围内查找字符串](Finds a string in the specified range)
     * @description: zh - 指定范围内查找字符串
     * @description: en - Finds a string in the specified range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 7:05 下午
     * @param str: 字符串
     * @param searchValue: 需要查找位置的字符串
     * @param fromIndex: 起始位置，从后往前计数
     * @param ignoreCase: 是否忽略大小写
     * @return int
    */
    public static int lastIndexOf(final CharSequence str, final CharSequence searchValue, int fromIndex, boolean ignoreCase) {
        if (str == Constant.NULL || searchValue == Constant.NULL) { return Constant.NEGATIVE_ONE; }
        if (fromIndex < Constant.ZERO) { fromIndex = Constant.ZERO; }
        fromIndex = Math.min(fromIndex, str.length());
        if (searchValue.length() == Constant.ZERO) { return fromIndex; }

        if (!ignoreCase) {
            // 不忽略大小写调用JDK方法
            return str.toString().lastIndexOf(searchValue.toString(), fromIndex);
        }

        for (int i = fromIndex; i >= Constant.ZERO; i--) {
            if (isSubEquals(str, i, searchValue, Constant.ZERO, searchValue.length(), Constant.TRUE)) {
                return i;
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [返回字符串 searchValue 在字符串 str 中第 ordinal 次出现的位置](Returns the position of the ordinal occurrence of the string searchValue in the string str)
     * @description: zh - 返回字符串 searchValue 在字符串 str 中第 ordinal 次出现的位置
     * @description: en - Returns the position of the ordinal occurrence of the string searchValue in the string str
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 7:11 下午
     * @param str: 被检查的字符串，可以为null
     * @param searchValue: 被查找的字符串，可以为null
     * @param ordinal: 第几次出现的位置
     * @return int
    */
    public static int ordinalIndexOf(CharSequence str, CharSequence searchValue, int ordinal) {
        if (str == Constant.NULL || searchValue == Constant.NULL || ordinal <= Constant.ZERO) {
            return Constant.NEGATIVE_ONE;
        }
        if (searchValue.length() == Constant.ZERO) { return Constant.ZERO; }
        int found = Constant.ZERO;
        int index = Constant.NEGATIVE_ONE;
        do {
            index = indexOf(str, searchValue, index + Constant.ONE, Constant.FALSE);
            if (index < Constant.ZERO) { return index; }
            found++;
        } while (found < ordinal);
        return index;
    }

    /*移除字符串中给定字符串-----------------------------------------------------------remove*/

    /**
     * [移除字符串中所有给定字符串](Removes all given strings from a string)
     * @description: zh - 移除字符串中所有给定字符串
     * @description: en - Removes all given strings from a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 7:16 下午
     * @param str: 字符串
     * @param removeValue: 被移除的字符串
     * @return java.lang.String
    */
    public static String removeAll(CharSequence str, CharSequence removeValue) {
        // strToRemove如果为空， 也不用继续后面的逻辑
        return isEmpty(str) || isEmpty(removeValue) ? str(str) : str.toString().replace(removeValue, Constant.EMPTY);

    }

    /**
     * [移除字符串中所有给定字符串，当某个字符串出现多次，则全部移除](Remove all given strings from a string. When a string appears more than once, remove all strings)
     * @description: zh - 移除字符串中所有给定字符串，当某个字符串出现多次，则全部移除
     * @description: en - Remove all given strings from a string. When a string appears more than once, remove all strings
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 7:18 下午
     * @param str: 字符串
     * @param removeArray: 被移除的字符串
     * @return java.lang.String
    */
    public static String removeAny(CharSequence str, CharSequence... removeArray) {
        String result = str(str);
        if (isNotEmpty(str)) {
            for (CharSequence strToRemove : removeArray) {
                result = removeAll(result, strToRemove);
            }
        }
        return result;
    }

    /**
     * [去除字符串中指定的多个字符，如有多个则全部去除](Remove multiple characters specified in the string. If there are multiple characters, remove them all)
     * @description: zh - 去除字符串中指定的多个字符，如有多个则全部去除
     * @description: en - Remove multiple characters specified in the string. If there are multiple characters, remove them all
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 7:22 下午
     * @param str: 字符串
     * @param chars: 字符列表
     * @return java.lang.String
    */
    public static String removeAll(CharSequence str, char... chars) {
        if (Constant.NULL == str || ArrayUtil.isEmpty(chars)) {
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
     * [去除所有换行符](Remove all line breaks)
     * @description: zh - 去除所有换行符
     * @description: en - Remove all line breaks
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 7:24 下午
     * @param str: 字符串
     * @return java.lang.String
    */
    public static String removeAllLineBreaks(CharSequence str) {
        return removeAll(str, Constant.CHAR_CR, Constant.CHAR_CN);
    }

    /**
     * [去掉首部指定长度的字符串并将剩余字符串首字母小写](Remove the specified length of the first string and lowercase the first letter of the remaining string)
     * @description: zh - 去掉首部指定长度的字符串并将剩余字符串首字母小写
     * @description: en - Remove the specified length of the first string and lowercase the first letter of the remaining string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 7:27 下午
     * @param str: 被处理的字符串
     * @param preLength: 去掉的长度
     * @return java.lang.String
    */
    public static String removePreAndLowerFirst(CharSequence str, int preLength) {
        if (str == Constant.NULL) { return Constant.STRING_NULL; }
        if (str.length() > preLength) {
            char first = Character.toLowerCase(str.charAt(preLength));
            return str.length() > preLength + Constant.ONE ?
                    first + str.toString().substring(preLength + Constant.ONE) :
                    String.valueOf(first);
        } else {
            return str.toString();
        }
    }

    /**
     * [去掉首部指定长度的字符串并将剩余字符串首字母小写](Remove the specified length of the first string and lowercase the first letter of the remaining string)
     * @description: zh - 去掉首部指定长度的字符串并将剩余字符串首字母小写
     * @description: en - Remove the specified length of the first string and lowercase the first letter of the remaining string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 7:30 下午
     * @param str: 被处理的字符串
     * @param prefix: 前缀
     * @return java.lang.String
    */
    public static String removePreAndLowerFirst(CharSequence str, CharSequence prefix) {
        return lowerFirst(removePrefix(str, prefix));
    }

    /**
     * [去掉指定前缀](Remove the specified prefix)
     * @description: zh - 去掉指定前缀
     * @description: en - Remove the specified prefix
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 7:32 下午
     * @param str: 字符串
     * @param prefix: 前缀
     * @return java.lang.String
    */
    public static String removePrefix(CharSequence str, CharSequence prefix) {
        if (isEmpty(str) || isEmpty(prefix)) {
            return str(str);
        }

        final String str2 = str.toString();
        return str2.startsWith(prefix.toString()) ?
                subStringSuf(str2, prefix.length()) :
                str2;
    }

    /**
     * [忽略大小写去掉指定前缀](Ignore case and remove the specified prefix)
     * @description: zh - 忽略大小写去掉指定前缀
     * @description: en - Ignore case and remove the specified prefix
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 7:36 下午
     * @param str: 字符串
     * @param prefix: 前缀
     * @return java.lang.String
    */
    public static String removePrefixIgnoreCase(CharSequence str, CharSequence prefix) {
        if (isEmpty(str) || isEmpty(prefix)) { return str(str); }

        final String str2 = str.toString();
        return str2.toLowerCase().startsWith(prefix.toString().toLowerCase()) ?
                subStringSuf(str2, prefix.length()) :
                str2;
    }

    /**
     *
     * @description: zh - 去掉指定后缀
     * @description: en - Remove the specified suffix
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 7:38 下午
     * @param str: 字符串
     * @param suffix: 后缀
     * @return java.lang.String
    */
    public static String removeSuffix(CharSequence str, CharSequence suffix) {
        if (isEmpty(str) || isEmpty(suffix)) { return str(str); }

        final String str2 = str.toString();
        return str2.endsWith(suffix.toString()) ?
                subStringPre(str2, str2.length() - suffix.length()) :
                str2;
    }

    /**
     * 去掉指定后缀，并小写首字母
     *
     * @param str    字符串
     * @param suffix 后缀
     * @return 切掉后的字符串，若后缀不是 suffix， 返回原字符串
     */
    public static String removeSufAndLowerFirst(CharSequence str, CharSequence suffix) {
        return lowerFirst(removeSuffix(str, suffix));
    }

    /**
     *
     * @description: zh - 忽略大小写去掉指定后缀
     * @description: en - Ignore case and remove the specified suffix
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 7:38 下午
     * @param str: 字符串
     * @param suffix: 后缀
     * @return java.lang.String
    */
    public static String removeSuffixIgnoreCase(CharSequence str, CharSequence suffix) {
        if (isEmpty(str) || isEmpty(suffix)) { return str(str); }

        final String str2 = str.toString();
        return str2.toLowerCase().endsWith(suffix.toString().toLowerCase()) ?
                subStringPre(str2, str2.length() - suffix.length()) :
                str2;
    }

    /**
     * [清理空白字符](Clean up white space)
     * @description: zh - 清理空白字符
     * @description: en - Clean up white space
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/8 7:39 下午
     * @param str: 被清理的字符串
     * @return java.lang.String
    */
    public static String cleanBlank(CharSequence str) {
        return filter(str, c -> !CharUtil.isBlankChar(c));
    }

    /*去除两边的指定字符串-----------------------------------------------------------strip*/

    /**
     * [去除两边的指定字符串](Remove both sides of the specified string)
     * @description: zh - 去除两边的指定字符串
     * @description: en - Remove both sides of the specified string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/9 9:09 上午
     * @param str: 被处理的字符串
     * @param prefixOrSuffix: 前缀或后缀
     * @return java.lang.String
    */
    public static String strip(CharSequence str, CharSequence prefixOrSuffix) {
        return equals(str, prefixOrSuffix) ? Constant.EMPTY : strip(str, prefixOrSuffix, prefixOrSuffix);
    }

    /**
     * [去除两边的指定字符串](Remove both sides of the specified string)
     * @description: zh - 去除两边的指定字符串
     * @description: en - Remove both sides of the specified string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 10:09 上午
     * @param str: 被处理的字符串
     * @param prefix: 前缀
     * @param suffix: 后缀
     * @return java.lang.String
    */
    public static String strip(CharSequence str, CharSequence prefix, CharSequence suffix) {
        if (isEmpty(str)) { return str(str); }

        int from = Constant.ZERO;
        int to = str.length();

        String str2 = str.toString();
        if (startWith(str2, prefix)) {
            from = prefix.length();
        }
        if (endWith(str2, suffix)) {
            to -= suffix.length();
        }

        return str2.substring(Math.min(from, to), Math.max(from, to));
    }

    /**
     * [去除两边的指定字符串，忽略大小写](Remove both sides of the specified string, ignoring case)
     * @description: zh - 去除两边的指定字符串，忽略大小写
     * @description: en - Remove both sides of the specified string, ignoring case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 10:10 上午
     * @param str: 被处理的字符串
     * @param prefixOrSuffix: 前缀或后缀
     * @return java.lang.String
    */
    public static String stripIgnoreCase(CharSequence str, CharSequence prefixOrSuffix) {
        return stripIgnoreCase(str, prefixOrSuffix, prefixOrSuffix);
    }

    /**
     * [去除两边的指定字符串，忽略大小写](Remove both sides of the specified string, ignoring case)
     * @description: zh - 去除两边的指定字符串，忽略大小写
     * @description: en - Remove both sides of the specified string, ignoring case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 10:20 上午
     * @param str: 被处理的字符串
     * @param prefix: 前缀
     * @param suffix: 后缀
     * @return java.lang.String
    */
    public static String stripIgnoreCase(CharSequence str, CharSequence prefix, CharSequence suffix) {
        if (isEmpty(str)) {
            return str(str);
        }
        int from = 0;
        int to = str.length();

        String str2 = str.toString();
        if (startWithIgnoreCase(str2, prefix)) {
            from = prefix.length();
        }
        if (endWithIgnoreCase(str2, suffix)) {
            to -= suffix.length();
        }
        return str2.substring(from, to);
    }

    /*添加字符串-----------------------------------------------------------add*/

    /**
     * [如果给定字符串不是以prefix开头的，在开头补充 prefix](If the given string does not start with prefix, prefix is added at the beginning)
     * @description: zh - 如果给定字符串不是以prefix开头的，在开头补充 prefix
     * @description: en - If the given string does not start with prefix, prefix is added at the beginning
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 10:30 上午
     * @param str: 字符串
     * @param prefix: 前缀
     * @return java.lang.String
    */
    public static String addPrefixIfNot(CharSequence str, CharSequence prefix) {
        if (isEmpty(str) || isEmpty(prefix)) { return str(str); }

        final String str2 = str.toString();
        final String prefix2 = prefix.toString();
        return !str2.startsWith(prefix2) ? prefix2.concat(str2) : str2;
    }

    /**
     * [如果给定字符串不是以suffix结尾的，在尾部补充 suffix](If the given string does not end with suffix, suffix is added at the end)
     * @description: zh - 如果给定字符串不是以suffix结尾的，在尾部补充 suffix
     * @description: en - If the given string does not end with suffix, suffix is added at the end
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 10:36 上午
     * @param str: 字符串
     * @param suffix: 后缀
     * @return java.lang.String
    */
    public static String addSuffixIfNot(CharSequence str, CharSequence suffix) {
        if (isEmpty(str) || isEmpty(suffix)) { return str(str); }

        final String str2 = str.toString();
        final String suffix2 = suffix.toString();
        return !str2.endsWith(suffix2) ? str2.concat(suffix2) : str2;
    }

    /*切分字符串-----------------------------------------------------------split*/

    /**
     * [切分字符串](Segmentation string)
     * @description: zh - 切分字符串
     * @description: en - Segmentation string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 10:50 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @return java.lang.String[]
    */
    public static String[] splitToArray(CharSequence str, char separator) {
        return splitToArray(str, separator, Constant.ZERO);
    }

    /**
     * [切分字符串](Segmentation string)
     * @description: zh - 切分字符串
     * @description: en - Segmentation string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 11:23 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @param limit: 限制分片数
     * @return java.lang.String[]
    */
    public static String[] splitToArray(CharSequence str, char separator, int limit) {
        return Constant.NULL == str ? new String[]{} : StrSpliter.splitToArray(str.toString(), separator, limit, Constant.FALSE, Constant.FALSE);
    }

    /**
     * [切分字符串为long数组](Splitting strings into long arrays)
     * @description: zh - 切分字符串为long数组
     * @description: en - Splitting strings into long arrays
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 10:57 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符
     * @return long[]
    */
    public static long[] splitToLong(CharSequence str, char separator) {
        return Convert.convert(long[].class, splitTrim(str, separator));
    }

    /**
     * [切分字符串为long数组](Splitting strings into long arrays)
     * @description: zh - 切分字符串为long数组
     * @description: en - Splitting strings into long arrays
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 11:02 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符串
     * @return long[]
    */
    public static long[] splitToLong(CharSequence str, CharSequence separator) {
        return Convert.convert(long[].class, splitTrim(str, separator));
    }

    /**
     * [切分字符串为int数组](Splits a string into an int array)
     * @description: zh - 切分字符串为int数组
     * @description: en - Splits a string into an int array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 11:04 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符
     * @return int[]
    */
    public static int[] splitToInt(CharSequence str, char separator) {
        return Convert.convert(int[].class, splitTrim(str, separator));
    }

    /**
     * [切分字符串为int数组](Splits a string into an int array)
     * @description: zh - 切分字符串为int数组
     * @description: en - Splits a string into an int array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 11:06 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符
     * @return int[]
    */
    public static int[] splitToInt(CharSequence str, CharSequence separator) {
        return Convert.convert(int[].class, splitTrim(str, separator));
    }

    /**
     * [切分字符串，去除切分后每个元素两边的空白符，去除空白项](Cut string, remove the blank character on both sides of each element after cutting, remove the blank item)
     * @description: zh - 切分字符串，去除切分后每个元素两边的空白符，去除空白项
     * @description: en - Cut string, remove the blank character on both sides of each element after cutting, remove the blank item
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 11:42 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @return java.util.List<java.lang.String>
    */
    public static List<String> splitTrim(CharSequence str, char separator) {
        return splitTrim(str, separator, Constant.NEGATIVE_ONE);
    }

    /**
     * [切分字符串，去除切分后每个元素两边的空白符，去除空白项](Cut string, remove the blank character on both sides of each element after cutting, remove the blank item)
     * @description: zh - 切分字符串，去除切分后每个元素两边的空白符，去除空白项
     * @description: en - Cut string, remove the blank character on both sides of each element after cutting, remove the blank item
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 11:43 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @return java.util.List<java.lang.String>
    */
    public static List<String> splitTrim(CharSequence str, CharSequence separator) {
        return splitTrim(str, separator, Constant.NEGATIVE_ONE);
    }

    /**
     * [切分字符串，去除切分后每个元素两边的空白符，去除空白项](Cut string, remove the blank character on both sides of each element after cutting, remove the blank item)
     * @description: zh - 切分字符串，去除切分后每个元素两边的空白符，去除空白项
     * @description: en - Cut string, remove the blank character on both sides of each element after cutting, remove the blank item
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 11:49 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @param limit: 限制分片数，-1不限制
     * @return java.util.List<java.lang.String>
    */
    public static List<String> splitTrim(CharSequence str, char separator, int limit) {
        return split(str, separator, limit, Constant.TRUE, Constant.TRUE);
    }

    /**
     * [切分字符串，去除切分后每个元素两边的空白符，去除空白项](Cut string, remove the blank character on both sides of each element after cutting, remove the blank item)
     * @description: zh - 切分字符串，去除切分后每个元素两边的空白符，去除空白项
     * @description: en - Cut string, remove the blank character on both sides of each element after cutting, remove the blank item
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 3:33 下午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @param limit: 限制分片数，-1不限制
     * @return java.util.List<java.lang.String>
    */
    public static List<String> splitTrim(CharSequence str, CharSequence separator, int limit) {
        return split(str, separator, limit, Constant.TRUE, Constant.TRUE);
    }



    /**
     * [切分字符串](Segmentation string)
     * @description: zh - 切分字符串
     * @description: en - Segmentation string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 11:10 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @return java.util.List<java.lang.String>
    */
    public static List<String> split(CharSequence str, char separator) {
        return split(str, separator, Constant.ZERO);
    }

    /**
     * [切分字符串，不去除切分后每个元素两边的空白符，不去除空白项](Cut string, do not remove the white space on both sides of each element after cutting, do not remove the white space)
     * @description: zh - 切分字符串，不去除切分后每个元素两边的空白符，不去除空白项
     * @description: en - Cut string, do not remove the white space on both sides of each element after cutting, do not remove the white space
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 11:25 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @param limit: 限制分片数，-1不限制
     * @return java.util.List<java.lang.String>
    */
    public static List<String> split(CharSequence str, char separator, int limit) {
        return split(str, separator, limit, Constant.FALSE, Constant.FALSE);
    }

    /**
     * [切分字符串，不限制分片数量](Cut string, unlimited number of pieces)
     * @description: zh - 切分字符串，不限制分片数量
     * @description: en - Cut string, unlimited number of pieces
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 11:26 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> split(CharSequence str, char separator, boolean isTrim, boolean ignoreEmpty) {
        return split(str, separator, Constant.ZERO, isTrim, ignoreEmpty);
    }

    /**
     * [切分字符串](Segmentation string)
     * @description: zh - 切分字符串
     * @description: en - Segmentation string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 11:28 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @param limit: 限制分片数，-1不限制
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> split(CharSequence str, char separator, int limit, boolean isTrim, boolean ignoreEmpty) {
        return Constant.NULL == str ? new ArrayList<>(Constant.ZERO) : StrSpliter.split(str.toString(), separator, limit, isTrim, ignoreEmpty);
    }

    /**
     * [切分字符串](Segmentation string)
     * @description: zh - 切分字符串
     * @description: en - Segmentation string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 11:33 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @param limit: 限制分片数，-1不限制
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> split(CharSequence str, CharSequence separator, int limit, boolean isTrim, boolean ignoreEmpty) {
        if (Constant.NULL == str) { return new ArrayList<>(Constant.ZERO); }
        final String separatorStr = (Constant.NULL == separator) ? Constant.STRING_NULL : separator.toString();
        return StrSpliter.split(str.toString(), separatorStr, limit, isTrim, ignoreEmpty);
    }

    /**
     * [根据给定长度，将给定字符串截取为多个部分](According to the given length, the given string is intercepted into multiple parts)
     * @description: zh - 根据给定长度，将给定字符串截取为多个部分
     * @description: en - According to the given length, the given string is intercepted into multiple parts
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 11:35 上午
     * @param str: 字符串
     * @param separator: 每一个小节的长度
     * @return java.lang.String[]
    */
    public static String[] split(CharSequence str, CharSequence separator) {
        if (str == Constant.NULL) {
            return new String[]{};
        }

        final String separatorStr = (Constant.NULL == separator) ? Constant.STRING_NULL : separator.toString();
        return StrSpliter.splitToArray(str.toString(), separatorStr, Constant.ZERO, Constant.FALSE, Constant.FALSE);
    }

    /**
     * [根据给定长度，将给定字符串截取为多个部分](According to the given length, the given string is intercepted into multiple parts)
     * @description: zh - 根据给定长度，将给定字符串截取为多个部分
     * @description: en - According to the given length, the given string is intercepted into multiple parts
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 11:36 上午
     * @param str: 字符串
     * @param len: 每一个小节的长度
     * @return java.lang.String[]
    */
    public static String[] split(CharSequence str, int len) {
        return Constant.NULL == str ? new String[]{} : StrSpliter.splitByLength(str.toString(), len);
    }

    /**
     * [将字符串切分为N等份](Divide a string into N equal parts)
     * @description: zh - 将字符串切分为N等份
     * @description: en - Divide a string into N equal parts
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 11:38 上午
     * @param str: 字符串
     * @param partLength: 每等份的长度
     * @return java.lang.String[]
    */
    public static String[] cut(CharSequence str, int partLength) {
        if (Constant.NULL == str) {
            return Constant.STRINGS_NULL;
        }
        int len = str.length();
        if (len < partLength) {
            return new String[]{str.toString()};
        }
        int part = NumUtil.count(len, partLength);
        final String[] array = new String[part];

        final String str2 = str.toString();
        for (int i = Constant.ZERO; i < part; i++) {
            array[i] = str2.substring(i * partLength, (i == part - Constant.ONE) ? len : (partLength + i * partLength));
        }
        return array;
    }

    /*截取字符串-----------------------------------------------------------intercept*/

    /**
     * [截取字符串](Intercept string)
     * @description: zh - 截取字符串
     * @description: en - Intercept string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 4:36 下午
     * @param value: String
     * @param beginIndex: 开始的index（包括）
     * @param endIndex: 结束的index（不包括）
     * @return java.lang.String
    */
    public static String subString(CharSequence value, int beginIndex, int endIndex) {
        if (isEmpty(value)) {
            return str(value);
        }
        int len = value.length();

        if (beginIndex < Constant.ZERO) {
            beginIndex = len + beginIndex;
            if (beginIndex < Constant.ZERO) {
                beginIndex = Constant.ZERO;
            }
        } else if (beginIndex > len) {
            beginIndex = len;
        }

        if (endIndex < Constant.ZERO) {
            endIndex = len + endIndex;
            if (endIndex < Constant.ZERO) {
                endIndex = len;
            }
        } else if (endIndex > len) {
            endIndex = len;
        }

        if (endIndex < beginIndex) {
            int tmp = beginIndex;
            beginIndex = endIndex;
            endIndex = tmp;
        }
        return beginIndex == endIndex ? Constant.EMPTY : value.toString().substring(beginIndex, endIndex);
    }

    /**
     * [通过CodePoint截取字符串，可以截断Emoji](You can truncate Emoji by intercepting strings through codepoint)
     * @description: zh - 通过CodePoint截取字符串，可以截断Emoji
     * @description: en - You can truncate Emoji by intercepting strings through codepoint
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 4:39 下午
     * @param value: String
     * @param beginIndex: 开始的index（包括）
     * @param endIndex: 结束的index（不包括）
     * @return java.lang.String
    */
    public static String subStringByCodePoint(CharSequence value, int beginIndex, int endIndex) {
        if (isEmpty(value)) { return str(value); }
        if (beginIndex < Constant.ZERO || beginIndex > endIndex) {
            throw new IllegalArgumentException();
        }
        if (beginIndex == endIndex) { return Constant.EMPTY; }

        final StringBuilder sb = new StringBuilder();
        final int subLen = endIndex - beginIndex;
        value.toString().codePoints().skip(beginIndex).limit(subLen).forEach(v -> sb.append(Character.toChars(v)));
        return sb.toString();
    }

    /**
     * [截取部分字符串，这里一个汉字的长度认为是2](Intercept part of the string, where the length of a Chinese character is considered to be 2)
     * @description: zh - 截取部分字符串，这里一个汉字的长度认为是2
     * @description: en - Intercept part of the string, where the length of a Chinese character is considered to be 2
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 4:43 下午
     * @param str: 字符串
     * @param length: 切割的位置
     * @param suffix: 切割后加上后缀
     * @return java.lang.String
    */
    public static String subStringPreGbk(CharSequence str, int length, CharSequence suffix) {
        if (isEmpty(str)) { return str(str); }

        byte[] b;
        int counterOfDoubleByte = Constant.ZERO;
        b = str.toString().getBytes(CharsetUtil.CHARSET_GBK);
        if (b.length <= length) { return str.toString(); }
        for (int i = Constant.ZERO; i < length; i++) {
            if (b[i] < Constant.ZERO) { counterOfDoubleByte++; }
        }

        if (counterOfDoubleByte % Constant.TWO != Constant.ZERO) {
            length += Constant.ONE;
        }
        return new String(b, Constant.ZERO, length, CharsetUtil.CHARSET_GBK) + suffix;
    }

    /**
     * [切割指定位置之前部分的字符串](Cuts the string before the specified position)
     * @description: zh - 切割指定位置之前部分的字符串
     * @description: en - Cuts the string before the specified position
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 4:48 下午
     * @param string: 字符串
     * @param toIndexExclude: 切割到的位置（不包括）
     * @return java.lang.String
    */
    public static String subStringPre(CharSequence string, int toIndexExclude) {
        return subString(string, Constant.ZERO, toIndexExclude);
    }

    /**
     * [切割指定位置之后部分的字符串](Cuts the string after the specified position)
     * @description: zh - 切割指定位置之后部分的字符串
     * @description: en - Cuts the string after the specified position
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 4:54 下午
     * @param string: 字符串
     * @param fromIndex: 切割开始的位置（包括）
     * @return java.lang.String
    */
    public static String subStringSuf(CharSequence string, int fromIndex) {
        return isEmpty(string) ? Constant.STRING_NULL : subString(string, fromIndex, string.length());
    }

    /**
     * [切割指定长度的后部分的字符串](A string that cuts the last part of the specified length)
     * @description: zh - 切割指定长度的后部分的字符串
     * @description: en - A string that cuts the last part of the specified length
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 4:56 下午
     * @param string:  字符串
     * @param length: 切割长度
     * @return java.lang.String
    */
    public static String subStringSufByLength(CharSequence string, int length) {
        return isEmpty(string) ?
                Constant.STRING_NULL :
                length <= Constant.ZERO ? Constant.EMPTY : subString(string, -length, string.length());
    }

    /**
     * [截取字符串,从指定位置开始,截取指定长度的字符串](Intercepts the string, starts from the specified position, intercepts the specified length string)
     * @description: zh - 截取字符串,从指定位置开始,截取指定长度的字符串
     * @description: en - Intercepts the string, starts from the specified position, intercepts the specified length string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 5:04 下午
     * @param input: 原始字符串
     * @param fromIndex: 开始的index,包括
     * @param length: 要截取的长度
     * @return java.lang.String
    */
    public static String subStringWithLength(String input, int fromIndex, int length) {
        return subString(input, fromIndex, fromIndex + length);
    }

    /**
     * [截取分隔字符串之前的字符串，不包括分隔字符串](Intercepts the string before the delimited string, excluding the delimited string)
     * @description: zh - 截取分隔字符串之前的字符串，不包括分隔字符串
     * @description: en - Intercepts the string before the delimited string, excluding the delimited string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 5:07 下午
     * @param string: 被查找的字符串
     * @param separator: 分隔字符串（不包括）
     * @param isLastSeparator: 是否查找最后一个分隔字符串（多次出现分隔字符串时选取最后一个），true为选取最后一个
     * @return java.lang.String
    */
    public static String subStringBefore(CharSequence string, CharSequence separator, boolean isLastSeparator) {
        if (isEmpty(string) || separator == Constant.NULL) {
            return Constant.NULL == string ? Constant.STRING_NULL : string.toString();
        }

        final String str = string.toString();
        final String sep = separator.toString();
        if (sep.isEmpty()) { return Constant.EMPTY; }
        final int pos = isLastSeparator ? str.lastIndexOf(sep) : str.indexOf(sep);
        return Constant.NEGATIVE_ONE == pos ? str : Constant.ZERO == pos ? Constant.EMPTY : str.substring(Constant.ZERO, pos);
    }

    /**
     * [截取分隔字符串之前的字符串，不包括分隔字符串](Intercepts the string before the delimited string, excluding the delimited string)
     * @description: zh - 截取分隔字符串之前的字符串，不包括分隔字符串
     * @description: en - Intercepts the string before the delimited string, excluding the delimited string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 5:10 下午
     * @param string: 被查找的字符串
     * @param separator: 分隔字符串（不包括）
     * @param isLastSeparator: 是否查找最后一个分隔字符串（多次出现分隔字符串时选取最后一个），true为选取最后一个
     * @return java.lang.String
    */
    public static String subStringBefore(CharSequence string, char separator, boolean isLastSeparator) {
        if (isEmpty(string)) {
            return Constant.NULL == string ? Constant.STRING_NULL : Constant.EMPTY;
        }

        final String str = string.toString();
        final int pos = isLastSeparator ? str.lastIndexOf(separator) : str.indexOf(separator);
        return Constant.NEGATIVE_ONE == pos ? str :
                Constant.ZERO == pos ? Constant.EMPTY : str.substring(Constant.ZERO, pos);
    }

    /**
     * [截取分隔字符串之后的字符串，不包括分隔字符串](Intercepts the string after the delimited string, excluding the delimited string)
     * @description: zh - 截取分隔字符串之后的字符串，不包括分隔字符串
     * @description: en - Intercepts the string after the delimited string, excluding the delimited string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 5:35 下午
     * @param string: 被查找的字符串
     * @param separator: 分隔字符串（不包括）
     * @param isLastSeparator: 是否查找最后一个分隔字符串（多次出现分隔字符串时选取最后一个），true为选取最后一个
     * @return java.lang.String
    */
    public static String subStringAfter(CharSequence string, CharSequence separator, boolean isLastSeparator) {
        if (isEmpty(string)) {
            return Constant.NULL == string ? Constant.STRING_NULL : Constant.EMPTY;
        }
        if (separator == Constant.NULL) {
            return Constant.EMPTY;
        }
        final String str = string.toString();
        final String sep = separator.toString();
        final int pos = isLastSeparator ? str.lastIndexOf(sep) : str.indexOf(sep);
        if (Constant.NEGATIVE_ONE == pos || (string.length() - 1) == pos) {
            return Constant.EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    /**
     * [截取分隔字符串之后的字符串，不包括分隔字符串](Intercepts the string after the delimited string, excluding the delimited string)
     * @description: zh - 截取分隔字符串之后的字符串，不包括分隔字符串
     * @description: en - Intercepts the string after the delimited string, excluding the delimited string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 5:53 下午
     * @param string: 被查找的字符串
     * @param separator: 分隔字符串（不包括）
     * @param isLastSeparator: 是否查找最后一个分隔字符串（多次出现分隔字符串时选取最后一个），true为选取最后一个
     * @return java.lang.String
    */
    public static String subStringAfter(CharSequence string, char separator, boolean isLastSeparator) {
        if (isEmpty(string)) {
            return Constant.NULL == string ? Constant.STRING_NULL : Constant.EMPTY;
        }
        final String str = string.toString();
        final int pos = isLastSeparator ? str.lastIndexOf(separator) : str.indexOf(separator);
        return Constant.NEGATIVE_ONE == pos ? Constant.EMPTY :  str.substring(pos + Constant.ONE);
    }

    /**
     * [截取指定字符串中间部分，不包括标识字符串](Intercepts the middle part of the specified string, excluding the identification string)
     * @description: zh - 截取指定字符串中间部分，不包括标识字符串
     * @description: en - Intercepts the middle part of the specified string, excluding the identification string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 5:54 下午
     * @param str: 被切割的字符串
     * @param before: 截取开始的字符串标识
     * @param after: 截取到的字符串标识
     * @return java.lang.String
    */
    public static String subStringBetween(CharSequence str, CharSequence before, CharSequence after) {
        if (str == Constant.NULL || before == Constant.NULL || after == Constant.NULL) {
            return Constant.STRING_NULL;
        }

        final String str2 = str.toString();
        final String before2 = before.toString();
        final String after2 = after.toString();

        final int start = str2.indexOf(before2);
        if (start != Constant.NEGATIVE_ONE) {
            final int end = str2.indexOf(after2, start + before2.length());
            if (end != Constant.NEGATIVE_ONE) {
                return str2.substring(start + before2.length(), end);
            }
        }
        return Constant.STRING_NULL;
    }

    /**
     * [截取指定字符串中间部分，不包括标识字符串](Intercepts the middle part of the specified string, excluding the identification string)
     * @description: zh - 截取指定字符串中间部分，不包括标识字符串
     * @description: en - Intercepts the middle part of the specified string, excluding the identification string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 5:55 下午
     * @param str: 被切割的字符串
     * @param beforeAndAfter: 截取开始和结束的字符串标识
     * @return java.lang.String
    */
    public static String subStringBetween(CharSequence str, CharSequence beforeAndAfter) {
        return subStringBetween(str, beforeAndAfter, beforeAndAfter);
    }

    /**
     * [截取指定字符串多段中间部分，不包括标识字符串](Intercepts the middle part of the specified string, excluding the identification string)
     * @description: zh - 截取指定字符串多段中间部分，不包括标识字符串
     * @description: en - Intercepts the middle part of the specified string, excluding the identification string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 5:58 下午
     * @param str: 被切割的字符串
     * @param prefix: 截取开始的字符串标识
     * @param suffix: 截取到的字符串标识
     * @return java.lang.String[]
    */
    public static String[] subStringBetweenAll(CharSequence str, CharSequence prefix, CharSequence suffix) {
        if (hasEmpty(str, prefix, suffix) ||
                // 不包含起始字符串，则肯定没有子串
                !contains(str, prefix)) {
            return new String[Constant.ZERO];
        }

        final List<String> result = new LinkedList<>();
        final String[] split = split(str, prefix);
        if (prefix.equals(suffix)) {
            // 前后缀字符相同，单独处理
            for (int i = Constant.ONE, length = split.length - Constant.ONE; i < length; i += Constant.TWO) {
                result.add(split[i]);
            }
        } else {
            int suffixIndex;
            for (String fragment : split) {
                suffixIndex = fragment.indexOf(suffix.toString());
                if (suffixIndex > Constant.ZERO) {
                    result.add(fragment.substring(Constant.ZERO, suffixIndex));
                }
            }
        }

        return result.toArray(new String[Constant.ZERO]);
    }

    /**
     * [截取指定字符串多段中间部分，不包括标识字符串](Intercepts the middle part of the specified string, excluding the identification string)
     * @description: zh - 截取指定字符串多段中间部分，不包括标识字符串
     * @description: en - Intercepts the middle part of the specified string, excluding the identification string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 5:59 下午
     * @param str: 被切割的字符串
     * @param prefixAndSuffix: 截取开始和结束的字符串标识
     * @return java.lang.String[]
    */
    public static String[] subStringBetweenAll(CharSequence str, CharSequence prefixAndSuffix) {
        return subStringBetweenAll(str, prefixAndSuffix, prefixAndSuffix);
    }

    /*重复的字符串 -----------------------------------------------------------repeat*/

    /**
     * [重复某一个字符](Repeat a character)
     * @description: zh - 重复某一个字符
     * @description: en - Repeat a character
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 6:08 下午
     * @param repeatValue: 被重复的字符
     * @param count: 重复的数目，如果小于等于0则返回""
     * @return java.lang.String
    */
    public static String repeat(char repeatValue, int count) {
        if (count <= Constant.ZERO) {
            return Constant.EMPTY;
        }

        char[] result = new char[count];
        for (int i = Constant.ZERO; i < count; i++) {
            result[i] = repeatValue;
        }
        return new String(result);
    }

    /**
     * [重复某个字符串](Repeat a string)
     * @description: zh - 重复某个字符串
     * @description: en - Repeat a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 6:14 下午
     * @param str: 被重复的字符
     * @param count: 重复的数目
     * @return java.lang.String
    */
    public static String repeat(CharSequence str, int count) {
        if (null == str) {
            return null;
        }
        if (count <= Constant.ZERO || str.length() == Constant.ZERO) {
            return Constant.EMPTY;
        }
        if (count == Constant.ONE) {
            return str.toString();
        }

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
     * [重复某个字符串到指定长度](Repeats a string to a specified length)
     * @description: zh - 重复某个字符串到指定长度
     * @description: en - Repeats a string to a specified length
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 6:34 下午
     * @param str: 被重复的字符
     * @param padLen: 指定长度
     * @return java.lang.String
    */
    public static String repeatByLength(CharSequence str, int padLen) {
        if (Constant.NULL == str) { return Constant.STRING_NULL; }
        if (padLen <= Constant.ZERO) { return Constant.EMPTY; }
        final int strLen = str.length();
        if (strLen == padLen) {
            return str.toString();
        } else if (strLen > padLen) {
            return subStringPre(str, padLen);
        }

        // 重复，直到达到指定长度
        final char[] padding = new char[padLen];
        for (int i = Constant.ZERO; i < padLen; i++) {
            padding[i] = str.charAt(i % strLen);
        }
        return new String(padding);
    }

    /**
     * [重复某个字符串并通过分界符连接](Repeats a string and concatenates it with a separator)
     * @description: zh - 重复某个字符串并通过分界符连接
     * @description: en - Repeats a string and concatenates it with a separator
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 6:36 下午
     * @param str: 被重复的字符串
     * @param count: 数量
     * @param conjunction: 分界符
     * @return java.lang.String
    */
    public static String repeatAndJoin(CharSequence str, int count, CharSequence conjunction) {
        if (count <= Constant.ZERO) {
            return Constant.EMPTY;
        }
        final StrBuilder builder = StrBuilder.create();
        boolean isFirst = true;
        while (count-- > 0) {
            if (isFirst) {
                isFirst = false;
            } else if (isNotEmpty(conjunction)) {
                builder.append(conjunction);
            }
            builder.append(str);
        }
        return builder.toString();
    }

    /*对比的字符串 -----------------------------------------------------------equals*/

    /**
     * [比较两个字符串（大小写敏感）。](Compare two strings (case sensitive).)
     * @description: zh - 比较两个字符串（大小写敏感）。
     * @description: en - Compare two strings (case sensitive).
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 6:48 下午
     * @param str1: 要比较的字符串1
     * @param str2: 要比较的字符串2
     * @return boolean
    */
    public static boolean equals(CharSequence str1, CharSequence str2) {
        return equals(str1, str2, Constant.FALSE);
    }

    /**
     * [比较两个字符串是否相等。](Compares two strings for equality.)
     * @description: zh - 比较两个字符串是否相等。
     * @description: en - Compares two strings for equality.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 6:52 下午
     * @param str1: str1 – 要比较的字符串1
     * @param str2: 要比较的字符串2
     * @param ignoreCase: 是否忽略大小写
     * @return boolean
    */
    public static boolean equals(CharSequence str1, CharSequence str2, boolean ignoreCase) {
        if (Constant.NULL == str1) {
            // 只有两个都为null才判断相等
            return str2 == Constant.NULL;
        }
        if (Constant.NULL == str2) {
            // 字符串2空，字符串1非空，直接false
            return Constant.FALSE;
        }
        return  ignoreCase ? str1.toString().equalsIgnoreCase(str2.toString()) : str1.toString().contentEquals(str2);
    }

    /**
     * [比较两个字符串（大小写不敏感）。](Compares two strings (case insensitive).)
     * @description: zh - 比较两个字符串（大小写不敏感）。
     * @description: en - Compares two strings (case insensitive).
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 6:59 下午
     * @param str1: 要比较的字符串1
     * @param str2: 要比较的字符串2
     * @return boolean
    */
    public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
        return equals(str1, str2, Constant.TRUE);
    }

    /**
     * [给定字符串是否与提供的中任一字符串相同（忽略大小写），相同则返回true，没有相同的返回false](If the given string is the same as any of the provided strings (regardless of case), true will be returned if it is the same, and false will be returned if it is not the same)
     * @description: zh - 给定字符串是否与提供的中任一字符串相同（忽略大小写），相同则返回true，没有相同的返回false
     * @description: en - If the given string is the same as any of the provided strings (regardless of case), true will be returned if it is the same, and false will be returned if it is not the same
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 7:00 下午
     * @param str1: 给定需要检查的字符串
     * @param arrays: 需要参与比对的字符串列表
     * @return boolean
    */
    public static boolean equalsAnyIgnoreCase(CharSequence str1, CharSequence... arrays) {
        return equalsAny(str1, Constant.TRUE, arrays);
    }

    /**
     * [给定字符串是否与提供的中任一字符串相同，相同则返回true，没有相同的返回false](If the given string is the same as any of the provided strings, true will be returned if it is the same, and false will be returned if it is not the same)
     * @description: zh - 给定字符串是否与提供的中任一字符串相同，相同则返回true，没有相同的返回false
     * @description: en - If the given string is the same as any of the provided strings, true will be returned if it is the same, and false will be returned if it is not the same
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 7:01 下午
     * @param str1: 给定需要检查的字符串
     * @param arrays: 需要参与比对的字符串列表
     * @return boolean
    */
    public static boolean equalsAny(CharSequence str1, CharSequence... arrays) {
        return equalsAny(str1, Constant.FALSE, arrays);
    }

    /**
     * [给定字符串是否与提供的中任一字符串相同，相同则返回true，没有相同的返回false](If the given string is the same as any of the provided strings, true will be returned if it is the same, and false will be returned if it is not the same)
     * @description: zh - 给定字符串是否与提供的中任一字符串相同，相同则返回true，没有相同的返回false
     * @description: en - If the given string is the same as any of the provided strings, true will be returned if it is the same, and false will be returned if it is not the same
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 7:02 下午
     * @param str1: 给定需要检查的字符串
     * @param ignoreCase: 是否忽略大小写
     * @param array: 需要参与比对的字符串列表
     * @return boolean
    */
    public static boolean equalsAny(CharSequence str1, boolean ignoreCase, CharSequence... array) {
        if (ArrayUtil.isEmpty(array)) {
            return Constant.FALSE;
        }

        for (CharSequence str : array) {
            if (equals(str1, str, ignoreCase)) {
                return Constant.TRUE;
            }
        }
        return Constant.FALSE;
    }

    /**
     * [字符串指定位置的字符是否与给定字符相同](Whether the character at the specified position of the string is the same as the given character)
     * @description: zh - 字符串指定位置的字符是否与给定字符相同
     * @description: en - Whether the character at the specified position of the string is the same as the given character
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 7:06 下午
     * @param string: 字符串
     * @param position: 位置
     * @param value: 需要对比的字符
     * @return boolean
    */
    public static boolean equalsCharAt(CharSequence string, int position, char value) {
        return Constant.NULL == string || position < Constant.ZERO ? Constant.FALSE : string.length() > position && value == string.charAt(position);
    }

    /**
     * [截取两个字符串的不同部分（长度一致），判断截取的子串是否相同](Intercepts different parts of two strings (the same length) to determine whether the intercepted substrings are the same)
     * @description: zh - 截取两个字符串的不同部分（长度一致），判断截取的子串是否相同
     * @description: en - Intercepts different parts of two strings (the same length) to determine whether the intercepted substrings are the same
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/10 7:10 下午
     * @param str1: 第一个字符串
     * @param start1: 第一个字符串开始的位置
     * @param str2: 第二个字符串
     * @param start2: 第二个字符串开始的位置
     * @param length: 截取长度
     * @param ignoreCase: 是否忽略大小写
     * @return boolean
    */
    public static boolean isSubEquals(CharSequence str1, int start1, CharSequence str2, int start2, int length, boolean ignoreCase) {
        return Constant.NULL == str1 || Constant.NULL == str2 ? Constant.FALSE : str1.toString().regionMatches(ignoreCase, start1, str2.toString(), start2, length);
    }

    /*格式化字符串 -----------------------------------------------------------format*/

    /**
     * [格式化文本, {} 表示占位符](Formatted text, {} represents a placeholder)
     * @description: zh - 格式化文本, {} 表示占位符
     * @description: en - Formatted text, {} represents a placeholder
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/11 8:00 下午
     * @param template:文本模板，被替换的部分用 {} 表示，如果模板为null，返回"null"
     * @param params: 参数值
     * @return java.lang.String
    */
    public static String format(CharSequence template, Object... params) {
        return Constant.NULL == template ? Constant.STRING_NULL :
                ArrayUtil.isEmpty(params) || isBlank(template) ? template.toString() : StrFormatter.format(template.toString(), params);
    }

    /**
     * [有序的格式化文本，使用{number}做为占位符](Orderly formatted text, using {number} as a placeholder)
     * @description: zh - 有序的格式化文本，使用{number}做为占位符
     * @description: en - Orderly formatted text, using {number} as a placeholder
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/11 8:00 下午
     * @param pattern: 文本格式
     * @param arguments: 参数
     * @return java.lang.String
    */
    public static String indexedFormat(CharSequence pattern, Object... arguments) {
        return MessageFormat.format(pattern.toString(), arguments);
    }

    /*编码字符串 -----------------------------------------------------------bytes*/

    /**
     * [编码字符串，编码为UTF-8](Encoding string, encoded as UTF-8)
     * @description: zh - 编码字符串，编码为UTF-8
     * @description: en - Encoding string, encoded as UTF-8
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/11 8:43 下午
     * @param value: 字符串
     * @return byte[]
    */
    public static byte[] utf8Bytes(CharSequence value) {
        return bytes(value, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * [编码字符串 使用系统默认编码](The encoding string uses the system default encoding)
     * @description: zh - 编码字符串 使用系统默认编码
     * @description: en - The encoding string uses the system default encoding
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/11 8:44 下午
     * @param value: 字符串
     * @return byte[]
    */
    public static byte[] bytes(CharSequence value) {
        return bytes(value, Charset.defaultCharset());
    }

    /**
     * [编码字符串](Encoding string)
     * @description: zh - 编码字符串
     * @description: en - Encoding string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/11 8:46 下午
     * @param value: 字符串
     * @param charset: 字符集，如果此字段为空，则解码的结果取决于平台
     * @return byte[]
    */
    public static byte[] bytes(CharSequence value, String charset) {
        return bytes(value, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    /**
     * [编码字符串](Encoding string)
     * @description: zh - 编码字符串
     * @description: en - Encoding string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/11 8:49 下午
     * @param value: 字符串
     * @param charset: 字符集，如果此字段为空，则解码的结果取决于平台
     * @return byte[]
    */
    public static byte[] bytes(CharSequence value, Charset charset) {
        return value == Constant.NULL ? Constant.BYTES_NULL :
                Constant.NULL == charset ? value.toString().getBytes() : value.toString().getBytes(charset);
    }

    /**
     * [字符串转换为byteBuffer](String to ByteBuffer)
     * @description: zh - 字符串转换为byteBuffer
     * @description: en - String to ByteBuffer
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/11 8:51 下午
     * @param value: 字符串
     * @param charset: 编码
     * @return java.nio.ByteBuffer
    */
    public static ByteBuffer byteBuffer(CharSequence value, String charset) {
        return ByteBuffer.wrap(bytes(value, charset));
    }

    /*包装指定字符串 -----------------------------------------------------------wrap*/

    /**
     * [包装指定字符串](Wrapper specified string)
     * @description: zh - 包装指定字符串
     * @description: en - Wrapper specified string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 8:54 上午
     * @param value: 被包装的字符串
     * @param prefixAndSuffix: 前缀和后缀
     * @return java.lang.String
    */
    public static String wrap(CharSequence value, CharSequence prefixAndSuffix) {
        return wrap(value, prefixAndSuffix, prefixAndSuffix);
    }

    /**
     * [包装指定字符串](Wrapper specified string)
     * @description: zh - 包装指定字符串
     * @description: en - Wrapper specified string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 8:56 上午
     * @param value: 被包装的字符串
     * @param prefix: 前缀
     * @param suffix: 后缀
     * @return java.lang.String
    */
    public static String wrap(CharSequence value, CharSequence prefix, CharSequence suffix) {
        return nullToEmpty(prefix).concat(nullToEmpty(value)).concat(nullToEmpty(suffix));
    }

    /**
     * [使用单个字符包装多个字符串](Wrapping multiple strings with a single character)
     * @description: zh - 使用单个字符包装多个字符串
     * @description: en - Wrapping multiple strings with a single character
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 8:57 上午
     * @param prefixAndSuffix: 前缀和后缀
     * @param values: 多个字符串
     * @return java.lang.String[]
    */
    public static String[] wrapAllWithPair(CharSequence prefixAndSuffix, CharSequence... values) {
        return wrapAll(prefixAndSuffix, prefixAndSuffix, values);
    }

    /**
     * [包装多个字符串](Wrapping multiple strings)
     * @description: zh - 包装多个字符串
     * @description: en - Wrapping multiple strings
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 8:59 上午
     * @param prefix: 前缀
     * @param suffix: 后缀
     * @param values: 多个字符串
     * @return java.lang.String[]
    */
    public static String[] wrapAll(CharSequence prefix, CharSequence suffix, CharSequence... values) {
        final String[] results = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            results[i] = wrap(values[i], prefix, suffix);
        }
        return results;
    }

    /**
     * [包装指定字符串，如果前缀或后缀已经包含对应的字符串，则不再包装](Wrap the specified string. If the prefix or suffix already contains the corresponding string, it is no longer wrapped)
     * @description: zh - 包装指定字符串，如果前缀或后缀已经包含对应的字符串，则不再包装
     * @description: en - Wrap the specified string. If the prefix or suffix already contains the corresponding string, it is no longer wrapped
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 9:00 上午
     * @param value: 被包装的字符串
     * @param prefix: 前缀
     * @param suffix: 后缀
     * @return java.lang.String
    */
    public static String wrapIfMissing(CharSequence value, CharSequence prefix, CharSequence suffix) {
        int len = Constant.ZERO;
        if (isNotEmpty(value)) {
            len += value.length();
        }
        if (isNotEmpty(prefix)) {
            len += value.length();
        }
        if (isNotEmpty(suffix)) {
            len += value.length();
        }
        StringBuilder sb = new StringBuilder(len);
        if (isNotEmpty(prefix) && !startWith(value, prefix)) {
            sb.append(prefix);
        }
        if (isNotEmpty(value)) {
            sb.append(value);
        }
        if (isNotEmpty(suffix) && !endWith(value, suffix)) {
            sb.append(suffix);
        }
        return sb.toString();
    }

    /**
     * [使用成对的字符包装多个字符串，如果已经包装，则不再包装](Use pairs of characters to wrap multiple strings. If they are already wrapped, they are no longer wrapped)
     * @description: zh - 使用成对的字符包装多个字符串，如果已经包装，则不再包装
     * @description: en - Use pairs of characters to wrap multiple strings. If they are already wrapped, they are no longer wrapped
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 9:01 上午
     * @param prefixAndSuffix: 前缀和后缀
     * @param values: 多个字符串
     * @return java.lang.String[]
    */
    public static String[] wrapAllWithPairIfMissing(CharSequence prefixAndSuffix, CharSequence... values) {
        return wrapAllIfMissing(prefixAndSuffix, prefixAndSuffix, values);
    }

    /**
     * [使用成对的字符包装多个字符串，如果已经包装，则不再包装](Use pairs of characters to wrap multiple strings. If they are already wrapped, they are no longer wrapped)
     * @description: zh - 使用成对的字符包装多个字符串，如果已经包装，则不再包装
     * @description: en - Use pairs of characters to wrap multiple strings. If they are already wrapped, they are no longer wrapped
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 9:05 上午
     * @param prefix: 前缀
     * @param suffix: 后缀
     * @param values: 多个字符串
     * @return java.lang.String[]
    */
    public static String[] wrapAllIfMissing(CharSequence prefix, CharSequence suffix, CharSequence... values) {
        final String[] results = new String[values.length];
        for (int i = Constant.ZERO; i < values.length; i++) {
            results[i] = wrapIfMissing(values[i], prefix, suffix);
        }
        return results;
    }

    /**
     * [去掉字符包装，如果未被包装则返回原字符串](Remove the character wrapping, and return the original string if it is not wrapped)
     * @description: zh - 去掉字符包装，如果未被包装则返回原字符串
     * @description: en - Remove the character wrapping, and return the original string if it is not wrapped
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 9:06 上午
     * @param value: 字符串
     * @param prefix: 前置字符串
     * @param suffix: 后置字符串
     * @return java.lang.String
    */
    public static String unWrap(CharSequence value, String prefix, String suffix) {
        return isWrap(value, prefix, suffix) ? subString(value, prefix.length(), value.length() - suffix.length()) : value.toString();
    }

    /**
     * [去掉字符包装，如果未被包装则返回原字符串](Remove the character wrapping, and return the original string if it is not wrapped)
     * @description: zh - 去掉字符包装，如果未被包装则返回原字符串
     * @description: en - Remove the character wrapping, and return the original string if it is not wrapped
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 9:10 上午
     * @param value: 字符串
     * @param prefix: 前置字符
     * @param suffix: 后置字符
     * @return java.lang.String
    */
    public static String unWrap(CharSequence value, char prefix, char suffix) {
        return isEmpty(value) ? str(value) :
                value.charAt(Constant.ZERO) == prefix && value.charAt(value.length() - Constant.ONE) == suffix ? subString(value, Constant.ONE, value.length() - Constant.ONE) : value.toString();
    }

    /**
     * [去掉字符包装，如果未被包装则返回原字符串](Remove the character wrapping, and return the original string if it is not wrapped)
     * @description: zh - 去掉字符包装，如果未被包装则返回原字符串
     * @description: en - Remove the character wrapping, and return the original string if it is not wrapped
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 9:11 上午
     * @param value: 字符串
     * @param prefixAndSuffix: 前置和后置字符
     * @return java.lang.String
    */
    public static String unWrap(CharSequence value, char prefixAndSuffix) {
        return unWrap(value, prefixAndSuffix, prefixAndSuffix);
    }

    /**
     * [指定字符串是否被包装](Specifies whether the string is wrapped)
     * @description: zh - 指定字符串是否被包装
     * @description: en - Specifies whether the string is wrapped
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 9:12 上午
     * @param value: 字符串
     * @param prefix: 前缀
     * @param suffix: 后缀
     * @return boolean
    */
    public static boolean isWrap(CharSequence value, String prefix, String suffix) {
        if (ArrayUtil.hasNull(value, prefix, suffix)) {
            return false;
        }
        final String str2 = value.toString();
        return str2.startsWith(prefix) && str2.endsWith(suffix);
    }

    /**
     * [指定字符串是否被同一字符包装（前后都有这些字符串）](Specifies whether the string is wrapped by the same character (both before and after the string))
     * @description: zh - 指定字符串是否被同一字符包装（前后都有这些字符串）
     * @description: en - Specifies whether the string is wrapped by the same character (both before and after the string)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 9:14 上午
     * @param str: 字符串
     * @param wrapper: 包装字符串
     * @return boolean
    */
    public static boolean isWrap(CharSequence str, String wrapper) {
        return isWrap(str, wrapper, wrapper);
    }

    /**
     * [指定字符串是否被同一字符包装（前后都有这些字符串）](Specifies whether the string is wrapped by the same character (both before and after the string))
     * @description: zh - 指定字符串是否被同一字符包装（前后都有这些字符串）
     * @description: en - Specifies whether the string is wrapped by the same character (both before and after the string)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 9:15 上午
     * @param str: 字符串
     * @param wrapper: 包装字符
     * @return boolean
    */
    public static boolean isWrap(CharSequence str, char wrapper) {
        return isWrap(str, wrapper, wrapper);
    }

    /**
     * [指定字符串是否被包装](Specifies whether the string is wrapped)
     * @description: zh - 指定字符串是否被包装
     * @description: en - Specifies whether the string is wrapped
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 9:16 上午
     * @param str: 字符串
     * @param prefixChar: 前缀
     * @param suffixChar: 后缀
     * @return boolean
    */
    public static boolean isWrap(CharSequence str, char prefixChar, char suffixChar) {
        return Constant.NULL == str ? Constant.FALSE : str.charAt(Constant.ZERO) == prefixChar && str.charAt(str.length() - Constant.ONE) == suffixChar;
    }

    /*补充字符串 -----------------------------------------------------------pad*/

    /**
     * [补充字符串以满足指定长度，如果提供的字符串大于指定长度，截断之](The string is supplemented to meet the specified length. If the supplied string is larger than the specified length, it is truncated)
     * @description: zh - 补充字符串以满足指定长度，如果提供的字符串大于指定长度，截断之
     * @description: en - The string is supplemented to meet the specified length. If the supplied string is larger than the specified length, it is truncated
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 9:28 上午
     * @param value: 字符串
     * @param length: 长度
     * @param padStr: 补充的字符
     * @return java.lang.String
    */
    public static String padPre(CharSequence value, int length, CharSequence padStr) {
        if (Constant.NULL == value) {
            return Constant.STRING_NULL;
        }
        final int strLen = value.length();
        return strLen == length ? value.toString() : strLen > length ?
                subStringPre(value, length) : repeatByLength(padStr, length - strLen).concat(value.toString());
    }

    /**
     * [补充字符串以满足指定长度，如果提供的字符串大于指定长度，截断之](The string is supplemented to meet the specified length. If the supplied string is larger than the specified length, it is truncated)
     * @description: zh - 补充字符串以满足指定长度，如果提供的字符串大于指定长度，截断之
     * @description: en - The string is supplemented to meet the specified length. If the supplied string is larger than the specified length, it is truncated
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 10:33 上午
     * @param value: 字符串
     * @param length: 长度
     * @param padChar: 补充的字符
     * @return java.lang.String
    */
    public static String padPre(CharSequence value, int length, char padChar) {
        if (Constant.NULL == value) {
            return Constant.STRING_NULL;
        }
        final int strLen = value.length();
        return strLen == length ? value.toString() :
                strLen > length ? subStringPre(value, length) : repeat(padChar, length - strLen).concat(value.toString());
    }

    /**
     * [补充字符串以满足指定长度，如果提供的字符串大于指定长度，截断之](The string is supplemented to meet the specified length. If the supplied string is larger than the specified length, it is truncated)
     * @description: zh - 补充字符串以满足指定长度，如果提供的字符串大于指定长度，截断之
     * @description: en - The string is supplemented to meet the specified length. If the supplied string is larger than the specified length, it is truncated
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 10:36 上午
     * @param value: 字符串，如果为null，直接返回null
     * @param length: 长度
     * @param padChar: 补充的字符
     * @return java.lang.String
    */
    public static String padAfter(CharSequence value, int length, char padChar) {
        if (Constant.NULL == value) {
            return Constant.STRING_NULL;
        }
        final int strLen = value.length();
        return strLen == length ? value.toString() :
                strLen > length ? subString(value, strLen - length, strLen) :
                        value.toString().concat(repeat(padChar, length - strLen));
    }

    /**
     * [补充字符串以满足最小长度](Supplement string to meet minimum length)
     * @description: zh - 补充字符串以满足最小长度
     * @description: en - Supplement string to meet minimum length
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 10:50 上午
     * @param value: 字符串，如果为null，直接返回null
     * @param length: 长度
     * @param padStr: 补充的字
     * @return java.lang.String
    */
    public static String padAfter(CharSequence value, int length, CharSequence padStr) {
        if (Constant.NULL == value) { return Constant.STRING_NULL; }
        final int strLen = value.length();
        return strLen == length ? value.toString() :
                strLen > length ? subStringSufByLength(value, length) :
                        value.toString().concat(repeatByLength(padStr, length - strLen));
    }

    /*居中字符串 -----------------------------------------------------------center*/

    /**
     * [居中字符串，两边补充指定字符串，如果指定长度小于字符串，则返回原字符串](Center the string and supplement the specified string on both sides. If the specified length is less than the string, the original string will be returned)
     * @description: zh - 居中字符串，两边补充指定字符串，如果指定长度小于字符串，则返回原字符串
     * @description: en - Center the string and supplement the specified string on both sides. If the specified length is less than the string, the original string will be returned
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 2:15 下午
     * @param value: 字符串
     * @param size: 指定长度
     * @param padStr: 两边补充的字符串
     * @return java.lang.String
    */
    public static String center(CharSequence value, final int size, CharSequence padStr) {
        if (value == Constant.NULL || size <= Constant.ZERO) {
            return str(value);
        }
        if (isEmpty(padStr)) { padStr = Constant.STRING_SPACE; }
        final int strLen = value.length();
        final int pads = size - strLen;
        if (pads <= Constant.ZERO) { return value.toString(); }
        value = padPre(value, strLen + pads / Constant.TWO, padStr);
        value = padAfter(value, size, padStr);
        return value.toString();
    }

    /**
     * [居中字符串，两边补充指定字符串，如果指定长度小于字符串，则返回原字符串](Center the string and supplement the specified string on both sides. If the specified length is less than the string, the original string will be returned)
     * @description: zh - 居中字符串，两边补充指定字符串，如果指定长度小于字符串，则返回原字符串
     * @description: en - Center the string and supplement the specified string on both sides. If the specified length is less than the string, the original string will be returned
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 2:17 下午
     * @param value: 字符串
     * @param size: 指定长度
     * @param padChar: 两边补充的字符
     * @return java.lang.String
    */
    public static String center(CharSequence value, final int size, char padChar) {
        if (value == Constant.NULL || size <= Constant.ZERO) { return str(value); }
        final int strLen = value.length();
        final int pads = size - strLen;
        if (pads <= Constant.ZERO) { return value.toString(); }
        value = padPre(value, strLen + pads / Constant.TWO, padChar);
        value = padAfter(value, size, padChar);
        return value.toString();
    }

    /**
     * [居中字符串，两边补充指定字符串，如果指定长度小于字符串，则返回原字符串](Center the string and supplement the specified string on both sides. If the specified length is less than the string, the original string will be returned)
     * @description: zh - 居中字符串，两边补充指定字符串，如果指定长度小于字符串，则返回原字符串
     * @description: en - Center the string and supplement the specified string on both sides. If the specified length is less than the string, the original string will be returned
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 2:18 下午
     * @param value: 字符串
     * @param size: 指定长度
     * @return java.lang.String
    */
    public static String center(CharSequence value, final int size) {
        return center(value, size, Constant.STRING_SPACE);
    }

    /*转为字符串 -----------------------------------------------------------str*/

    /**
     * [CharSequence 转为字符串，null安全](CharSequence to string, null safe)
     * @description: zh - CharSequence 转为字符串，null安全
     * @description: en - CharSequence to string, null safe
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 2:26 下午
     * @param value: CharSequence
     * @return java.lang.String
    */
    public static String str(CharSequence value) {
        return Constant.NULL == value ? Constant.STRING_NULL : value.toString();
    }

    /*统计字符串 -----------------------------------------------------------count*/

    /**
     * [统计指定内容中包含指定字符串的数量](Counts the number of specified strings in the specified content)
     * @description: zh - 统计指定内容中包含指定字符串的数量
     * @description: en - Counts the number of specified strings in the specified content
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 2:31 下午
     * @param content: 被查找的字符串
     * @param strForSearch: 需要查找的字符串
     * @return int
    */
    public static int count(CharSequence content, CharSequence strForSearch) {
        if (hasEmpty(content, strForSearch) || strForSearch.length() > content.length()) {
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

    /**
     * [统计指定内容中包含指定字符串的数量](Counts the number of specified strings in the specified content)
     * @description: zh - 统计指定内容中包含指定字符串的数量
     * @description: en - Counts the number of specified strings in the specified content
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/12 2:32 下午
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

    /*比较字符串 -----------------------------------------------------------compare*/

    /**
     * [比较两个字符串，用于排序](Compare two strings for sorting)
     * @description: zh - 比较两个字符串，用于排序
     * @description: en - Compare two strings for sorting
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/13 2:41 下午
     * @param str1: 字符串1
     * @param str2: 字符串2
     * @param nullIsLess: null 值是否排在前（null是否小于非空值
     * @return int
    */
    public static int compare(final CharSequence str1, final CharSequence str2, final boolean nullIsLess) {
        return str1 == str2 ? Constant.ZERO :
                str1 == Constant.NULL ? nullIsLess ? Constant.NEGATIVE_ONE : Constant.ONE :
                        str2 == Constant.NULL ? nullIsLess ? Constant.ONE : Constant.NEGATIVE_ONE :
                                str1.toString().compareTo(str2.toString());
    }

    /**
     * [比较两个字符串，用于排序，大小写不敏感](Compare two strings for sorting, case insensitive)
     * @description: zh - 比较两个字符串，用于排序，大小写不敏感
     * @description: en - Compare two strings for sorting, case insensitive
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/13 3:18 下午
     * @param str1: 字符串1
     * @param str2: 字符串2
     * @param nullIsLess: null 值是否排在前（null是否小于非空值）
     * @return int
    */
    public static int compareIgnoreCase(CharSequence str1, CharSequence str2, boolean nullIsLess) {
        return str1 == str2 ? Constant.ZERO :
                str1 == Constant.NULL ? nullIsLess ? Constant.NEGATIVE_ONE : Constant.ONE :
                        str2 == Constant.NULL ? nullIsLess ? Constant.ONE : Constant.NEGATIVE_ONE :
                                str1.toString().compareToIgnoreCase(str2.toString());
    }

    /**
     * [比较两个版本](Compare the two versions)
     * @description: zh - 比较两个版本
     * @description: en - Compare the two versions
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/13 3:20 下午
     * @param value1: 版本1
     * @param value2: 版本2
     * @return int
    */
    public static int compareVersion(CharSequence value1, CharSequence value2) {
        return VersionComparator.INSTANCE.compare(str(value1), str(value2));
    }

    /*添加字符串 -----------------------------------------------------------append*/

    /**
     * [如果给定字符串不是以给定的一个或多个字符串为结尾，则在尾部添加结尾字符串](If the given string does not end with one or more of the given strings, the ending string is added at the end)
     * @description: zh - 如果给定字符串不是以给定的一个或多个字符串为结尾，则在尾部添加结尾字符串
     * @description: en - If the given string does not end with one or more of the given strings, the ending string is added at the end
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/13 8:08 下午
     * @param value: 被检查的字符串
     * @param suffix: 需要添加到结尾的字符串
     * @param suffixes: 需要额外检查的结尾字符串，如果以这些中的一个为结尾，则不再添加
     * @return java.lang.String
    */
    public static String appendIfMissing(final CharSequence value, final CharSequence suffix, final CharSequence... suffixes) {
        return appendIfMissing(value, suffix, Constant.FALSE, suffixes);
    }

    /**
     * [如果给定字符串不是以给定的一个或多个字符串为结尾，则在尾部添加结尾字符串](If the given string does not end with one or more of the given strings, the ending string is added at the end)
     * @description: zh - 如果给定字符串不是以给定的一个或多个字符串为结尾，则在尾部添加结尾字符串
     * @description: en - If the given string does not end with one or more of the given strings, the ending string is added at the end
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/13 8:10 下午
     * @param value: 被检查的字符串
     * @param suffix: 需要添加到结尾的字符串
     * @param suffixes: 需要额外检查的结尾字符串，如果以这些中的一个为结尾，则不再添加
     * @return java.lang.String
    */
    public static String appendIfMissingIgnoreCase(final CharSequence value, final CharSequence suffix, final CharSequence... suffixes) {
        return appendIfMissing(value, suffix, Constant.TRUE, suffixes);
    }

    /**
     * [如果给定字符串不是以给定的一个或多个字符串为结尾，则在尾部添加结尾字符串](If the given string does not end with one or more of the given strings, the ending string is added at the end)
     * @description: zh - 如果给定字符串不是以给定的一个或多个字符串为结尾，则在尾部添加结尾字符串
     * @description: en - If the given string does not end with one or more of the given strings, the ending string is added at the end
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/13 8:12 下午
     * @param value: 被检查的字符串
     * @param suffix: 需要添加到结尾的字符串
     * @param ignoreCase: 检查结尾时是否忽略大小写
     * @param suffixes: 需要额外检查的结尾字符串，如果以这些中的一个为结尾，则不再添加
     * @return java.lang.String
    */
    public static String appendIfMissing(final CharSequence value, final CharSequence suffix, final boolean ignoreCase, final CharSequence... suffixes) {
        if (value == Constant.NULL || isEmpty(suffix) || endWith(value, suffix, ignoreCase)) {
            return str(value);
        }
        if (suffixes != Constant.NULL && suffixes.length > Constant.ZERO) {
            for (final CharSequence s : suffixes) {
                if (endWith(value, s, ignoreCase)) {
                    return value.toString();
                }
            }
        }
        return value.toString().concat(suffix.toString());
    }

    /**
     * [如果给定字符串不是以给定的一个或多个字符串为开头，则在首部添加起始字符串](If the given string does not start with one or more given strings, the starting string is added at the beginning)
     * @description: zh - 如果给定字符串不是以给定的一个或多个字符串为开头，则在首部添加起始字符串
     * @description: en - If the given string does not start with one or more given strings, the starting string is added at the beginning
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/13 8:13 下午
     * @param value: 被检查的字符串
     * @param prefix: 需要添加到首部的字符串
     * @param prefixes: 需要额外检查的首部字符串，如果以这些中的一个为起始，则不再添加
     * @return java.lang.String
    */
    public static String prependIfMissing(final CharSequence value, final CharSequence prefix, final CharSequence... prefixes) {
        return prependIfMissing(value, prefix, Constant.FALSE, prefixes);
    }

    /**
     * [如果给定字符串不是以给定的一个或多个字符串为开头，则在首部添加起始字符串](If the given string does not start with one or more given strings, the starting string is added at the beginning)
     * @description: zh - 如果给定字符串不是以给定的一个或多个字符串为开头，则在首部添加起始字符串
     * @description: en - If the given string does not start with one or more given strings, the starting string is added at the beginning
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/13 8:14 下午
     * @param str: 被检查的字符串
     * @param prefix: 需要添加到首部的字符串
     * @param prefixes: 需要额外检查的首部字符串，如果以这些中的一个为起始，则不再添加
     * @return java.lang.String
    */
    public static String prependIfMissingIgnoreCase(final CharSequence str, final CharSequence prefix, final CharSequence... prefixes) {
        return prependIfMissing(str, prefix, Constant.TRUE, prefixes);
    }

    /**
     * [如果给定字符串不是以给定的一个或多个字符串为开头，则在首部添加起始字符串](If the given string does not start with one or more given strings, the starting string is added at the beginning)
     * @description: zh - 如果给定字符串不是以给定的一个或多个字符串为开头，则在首部添加起始字符串
     * @description: en - If the given string does not start with one or more given strings, the starting string is added at the beginning
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/13 8:15 下午
     * @param str: 被检查的字符串
     * @param prefix: 需要添加到首部的字符串
     * @param ignoreCase: 检查结尾时是否忽略大小写
     * @param prefixes: 需要额外检查的首部字符串，如果以这些中的一个为起始，则不再添加
     * @return java.lang.String
    */
    public static String prependIfMissing(final CharSequence str, final CharSequence prefix, final boolean ignoreCase, final CharSequence... prefixes) {
        if (str == Constant.NULL || isEmpty(prefix) || startWith(str, prefix, ignoreCase)) {
            return str(str);
        }
        if (prefixes != Constant.NULL && prefixes.length > Constant.ZERO) {
            for (final CharSequence s : prefixes) {
                if (startWith(str, s, ignoreCase)) {
                    return str.toString();
                }
            }
        }
        return prefix.toString().concat(str.toString());
    }

    /*替换字符串 -----------------------------------------------------------replace*/

    /**
     * [替换字符串中的指定字符串，忽略大小写](Replaces the specified string in the string, ignoring case)
     * @description: zh - 替换字符串中的指定字符串，忽略大小写
     * @description: en - Replaces the specified string in the string, ignoring case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 6:28 下午
     * @param value: 字符串
     * @param search: 被查找的字符串
     * @param replacement: 被替换的字符串
     * @return java.lang.String
    */
    public static String replaceIgnoreCase(CharSequence value, CharSequence search, CharSequence replacement) {
        return replace(value, Constant.ZERO, search, replacement, Constant.TRUE);
    }

    /**
     * [替换字符串中的指定字符串](Replaces the specified string in the string)
     * @description: zh - 替换字符串中的指定字符串
     * @description: en - Replaces the specified string in the string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 6:38 下午
     * @param value: 字符串
     * @param search: 被查找的字符串
     * @param replacement: 被替换的字符串
     * @return java.lang.String
    */
    public static String replace(CharSequence value, CharSequence search, CharSequence replacement) {
        return replace(value, Constant.ZERO, search, replacement, Constant.FALSE);
    }

    /**
     * [替换字符串中的指定字符串](Replaces the specified string in the string)
     * @description: zh - 替换字符串中的指定字符串
     * @description: en - Replaces the specified string in the string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 6:39 下午
     * @param value: 字符串
     * @param search: 被查找的字符串
     * @param replacement: 被替换的字符串
     * @param ignoreCase: 是否忽略大小写
     * @return java.lang.String
    */
    public static String replace(CharSequence value, CharSequence search, CharSequence replacement, boolean ignoreCase) {
        return replace(value, Constant.ZERO, search, replacement, ignoreCase);
    }

    /**
     * [替换字符串中的指定字符串](Replaces the specified string in the string)
     * @description: zh - 替换字符串中的指定字符串
     * @description: en - Replaces the specified string in the string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 6:42 下午
     * @param value: 字符串
     * @param fromIndex: 开始位置（包括）
     * @param search: 被查找的字符串
     * @param replacement: 被替换的字符串
     * @param ignoreCase: 是否忽略大小写
     * @return java.lang.String
    */
    public static String replace(CharSequence value, int fromIndex, CharSequence search, CharSequence replacement, boolean ignoreCase) {
        if (isEmpty(value) || isEmpty(search)) {
            return str(value);
        }
        if (Constant.NULL == replacement) {
            replacement = Constant.EMPTY;
        }

        final int strLength = value.length();
        final int searchStrLength = search.length();
        if (fromIndex > strLength) {
            return str(value);
        } else if (fromIndex < Constant.ZERO) {
            fromIndex = Constant.ZERO;
        }

        final StrBuilder result = StrBuilder.create(strLength + Constant.SIXTEEN);
        if (Constant.ZERO != fromIndex) {
            result.append(value.subSequence(Constant.ZERO, fromIndex));
        }

        int preIndex = fromIndex;
        int index;
        while ((index = indexOf(value, search, preIndex, ignoreCase)) > Constant.NEGATIVE_ONE) {
            result.append(value.subSequence(preIndex, index));
            result.append(replacement);
            preIndex = index + searchStrLength;
        }

        if (preIndex < strLength) {
            // 结尾部分
            result.append(value.subSequence(preIndex, strLength));
        }
        return result.toString();
    }

    /**
     * [替换指定字符串的指定区间内字符为固定字符](Replace the characters in the specified interval of the specified string as fixed characters)
     * @description: zh - 替换指定字符串的指定区间内字符为固定字符
     * @description: en - Replace the characters in the specified interval of the specified string as fixed characters
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 6:46 下午
     * @param value: 字符串
     * @param startInclude: 开始位置（包含）
     * @param endExclude: 结束位置（不包含）
     * @param replacedChar: 被替换的字符
     * @return java.lang.String
    */
    public static String replace(CharSequence value, int startInclude, int endExclude, char replacedChar) {
        if (isEmpty(value)) {
            return str(value);
        }
        final int strLength = value.length();
        if (startInclude > strLength) {
            return str(value);
        }
        if (endExclude > strLength) {
            endExclude = strLength;
        }
        if (startInclude > endExclude) {
            // 如果起始位置大于结束位置，不替换
            return str(value);
        }

        final char[] chars = new char[strLength];
        for (int i = Constant.ZERO; i < strLength; i++) {
            if (i >= startInclude && i < endExclude) {
                chars[i] = replacedChar;
            } else {
                chars[i] = value.charAt(i);
            }
        }
        return new String(chars);
    }

    /**
     * [替换所有正则匹配的文本，并使用自定义函数决定如何替换](Replace all regular matched text and use custom functions to decide how to replace it)
     * @description: zh - 替换所有正则匹配的文本，并使用自定义函数决定如何替换
     * @description: en - Replace all regular matched text and use custom functions to decide how to replace it
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 6:58 下午
     * @param value: 要替换的字符串
     * @param pattern: 用于匹配的正则式
     * @param replaceFun: 决定如何替换的函数
     * @return java.lang.String
    */
    public static String replace(CharSequence value, java.util.regex.Pattern pattern, Func1<Matcher, String> replaceFun) {
        return RegularUtil.replaceAll(value, pattern, replaceFun);
    }

    /**
     * [替换所有正则匹配的文本，并使用自定义函数决定如何替换](Replace all regular matched text and use custom functions to decide how to replace it)
     * @description: zh - 替换所有正则匹配的文本，并使用自定义函数决定如何替换
     * @description: en - Replace all regular matched text and use custom functions to decide how to replace it
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 6:59 下午
     * @param value: 要替换的字符串
     * @param regex: 用于匹配的正则式
     * @param replaceFun: 决定如何替换的函数
     * @return java.lang.String
    */
    public static String replace(CharSequence value, String regex, Func1<java.util.regex.Matcher, String> replaceFun) {
        return RegularUtil.replaceAll(value, regex, replaceFun);
    }

    /*脱敏字符串 -----------------------------------------------------------desensitized*/

    /**
     * [替换指定字符串的指定区间内字符为"*" 俗称：脱敏功能，后面其他功能](Replace the characters in the specified interval of the specified string as "*", commonly known as desensitization function, followed by other functions)
     * @description: zh - 替换指定字符串的指定区间内字符为"*" 俗称：脱敏功能，后面其他功能
     * @description: en - Replace the characters in the specified interval of the specified string as "*", commonly known as desensitization function, followed by other functions
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 7:14 下午
     * @param value: 字符串
     * @param startInclude: 开始位置（包含）
     * @param endExclude: 结束位置（不包含）
     * @return java.lang.String
    */
    public static String hide(CharSequence value, int startInclude, int endExclude) {
        return replace(value, startInclude, endExclude, Constant.CHAR_STAR);
    }

    /**
     * [脱敏，使用默认的脱敏策略](Desensitization, using the default desensitization strategy)
     * @description: zh - 脱敏，使用默认的脱敏策略
     * @description: en - Desensitization, using the default desensitization strategy
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 7:18 下午
     * @param value: 字符串
     * @param desensitizedType: 脱敏类型;可以脱敏：用户id、中文名、身份证号、座机号、手机号、地址、电子邮件、密码
     * @return java.lang.String
    */
    public static String desensitized(CharSequence value, DesensitizedUtil.DesensitizedType desensitizedType) {
        return DesensitizedUtil.desensitized(value, desensitizedType);
    }

    /**
     * [替换字符字符数组中所有的字符为 replaced](Replace all characters in the character array as replaced)
     * @description: zh - 替换字符字符数组中所有的字符为 replaced
     * @description: en - Replace all characters in the character array as replaced
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 7:20 下午
     * @param value: 被检查的字符串
     * @param chars: 需要替换的字符列表，用一个字符串表示这个字符列表
     * @param replaced: 替换成的字符串
     * @return java.lang.String
    */
    public static String replaceChars(CharSequence value, String chars, CharSequence replaced) {
        return isEmpty(value) || isEmpty(chars) ? str(value) : replaceChars(value, chars.toCharArray(), replaced);
    }

    /**
     * [替换字符字符数组中所有的字符为 replaced](Replace all characters in the character array as replaced)
     * @description: zh - 替换字符字符数组中所有的字符为 replaced
     * @description: en - Replace all characters in the character array as replaced
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 7:24 下午
     * @param value: 被检查的字符串
     * @param chars: 需要替换的字符列表
     * @param replaced: 替换成的字符串
     * @return java.lang.String
    */
    public static String replaceChars(CharSequence value, char[] chars, CharSequence replaced) {
        if (isEmpty(value) || ArrayUtil.isEmpty(chars)) {
            return str(value);
        }

        final Set<Character> set = new HashSet<>(chars.length);
        for (char c : chars) {
            set.add(c);
        }
        int strLen = value.length();
        final StringBuilder builder = new StringBuilder();
        char c;
        for (int i = Constant.ZERO; i < strLen; i++) {
            c = value.charAt(i);
            builder.append(set.contains(c) ? replaced : c);
        }
        return builder.toString();
    }

    /*获取字符串长度 -----------------------------------------------------------length*/

    /**
     * [获取字符串的长度，如果为null返回0](Gets the length of the string, and returns 0 if NULL)
     * @description: zh - 获取字符串的长度，如果为null返回0
     * @description: en - Gets the length of the string, and returns 0 if NULL
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 7:31 下午
     * @param value: 字符串
     * @return int
    */
    public static int length(CharSequence value) {
        return value == Constant.NULL ? Constant.ZERO : value.length();
    }

    /**
     * [给定字符串转为bytes后的byte数（byte长度）](The number of bytes (byte length) after the given string is converted to bytes)
     * @description: zh - 给定字符串转为bytes后的byte数（byte长度）
     * @description: en - The number of bytes (byte length) after the given string is converted to bytes
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 7:32 下午
     * @param cs: 字符串
     * @param charset: 编码
     * @return int
    */
    public static int byteLength(CharSequence cs, Charset charset) {
        return cs == Constant.NULL ? Constant.ZERO : cs.toString().getBytes(charset).length;
    }

    /**
     * [给定字符串数组的总长度](The total length of the given string array)
     * @description: zh - 给定字符串数组的总长度
     * @description: en - The total length of the given string array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 7:35 下午
     * @param values: 字符串数组
     * @return int
    */
    public static int totalLength(CharSequence... values) {
        int totalLength = Constant.ZERO;
        for (CharSequence value : values) {
            totalLength += (Constant.NULL == value ? Constant.ZERO : value.length());
        }
        return totalLength;
    }

    /**
     *
     * @description: zh - 限制字符串长度，如果超过指定长度，截取指定长度并在末尾加"..."
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 7:38 下午
     * @param string:
     * @param length:
     * @return java.lang.String
    */
    public static String maxLength(CharSequence string, int length) {
        Assertion.isTrue(length > 0);
        if (Constant.NULL == string) {
            return Constant.STRING_NULL;
        }
        if (string.length() <= length) {
            return string.toString();
        }
        return subString(string, Constant.ZERO, length) + "...";
    }

    /*判断第一个字符串 -----------------------------------------------------------first*/

    /**
     * [返回第一个非 null 元素](Returns the first non null element)
     * @description: zh - 返回第一个非 null 元素
     * @description: en - Returns the first non null element
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 8:02 下午
     * @param values: 多个元素
     * @return T
    */
    @SuppressWarnings("unchecked")
    public <T extends CharSequence> T firstNonNull(T... values) {
        return ArrayUtil.firstNonNull(values);
    }

    /**
     * [返回第一个非 empty 元素](Returns the first non empty element)
     * @description: zh - 返回第一个非 empty 元素
     * @description: en - Returns the first non empty element
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 8:04 下午
     * @param values: 多个元素
     * @return T
    */
    @SuppressWarnings("unchecked")
    public <T extends CharSequence> T firstNonEmpty(T... values) {
        return ArrayUtil.firstMatch(StrUtil::isNotEmpty, values);
    }

    /**
     * [返回第一个非 blank 元素](Returns the first non blank element)
     * @description: zh - 返回第一个非 blank 元素
     * @description: en - Returns the first non blank element
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 8:07 下午
     * @param values: 多个元素
     * @return T
    */
    @SuppressWarnings("unchecked")
    public <T extends CharSequence> T firstNonBlank(T... values) {
        return ArrayUtil.firstMatch(StrUtil::isNotBlank, values);
    }

    /*添加指定字符串 -----------------------------------------------------------append*/

    /**
     * [原字符串首字母大写并在其首部添加指定字符串](Capitalize the original string and add the specified string at the beginning)
     * @description: zh - 原字符串首字母大写并在其首部添加指定字符串
     * @description: en - Capitalize the original string and add the specified string at the beginning
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 8:22 下午
     * @param value: 被处理的字符串
     * @param pre: 添加的首部
     * @return java.lang.String
    */
    public static String upperFirstAndAddPre(CharSequence value, String pre) {
        return value == Constant.NULL || pre == Constant.NULL ? Constant.STRING_NULL : pre + upperFirst(value);
    }

    /**
     * [大写首字母](Capital initial)
     * @description: zh - 大写首字母
     * @description: en - Capital initial
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 8:23 下午
     * @param value: 字符串
     * @return java.lang.String
    */
    public static String upperFirst(CharSequence value) {
        if (Constant.NULL == value) {
            return Constant.STRING_NULL;
        }
        if (value.length() > Constant.ZERO) {
            char firstChar = value.charAt(Constant.ZERO);
            if (Character.isLowerCase(firstChar)) {
                return Character.toUpperCase(firstChar) + subStringSuf(value, Constant.ONE);
            }
        }
        return value.toString();
    }

    /**
     * [小写首字母](Small initial)
     * @description: zh - 小写首字母
     * @description: en - Small initial
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 8:24 下午
     * @param value: 字符串
     * @return java.lang.String
    */
    public static String lowerFirst(CharSequence value) {
        if (Constant.NULL == value) {
            return Constant.STRING_NULL;
        }
        if (value.length() > Constant.ZERO) {
            char firstChar = value.charAt(Constant.ZERO);
            if (Character.isUpperCase(firstChar)) {
                return Character.toLowerCase(firstChar) + subStringSuf(value, Constant.ONE);
            }
        }
        return value.toString();
    }

    /*过滤字符串字符串 -----------------------------------------------------------filter*/

    /**
     * [过滤字符串](Filter strings)
     * @description: zh - 过滤字符串
     * @description: en - Filter strings
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/14 9:47 下午
     * @param value: 字符串
     * @param filter: 过滤器
     * @return java.lang.String
    */
    public static String filter(CharSequence value, final Filter<Character> filter) {
        if (value == Constant.NULL || filter == Constant.NULL) {
            return str(value);
        }

        int len = value.length();
        final StringBuilder sb = new StringBuilder(len);
        char c;
        for (int i = Constant.ZERO; i < len; i++) {
            c = value.charAt(i);
            if (filter.accept(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /*过滤字符串字符串 -----------------------------------------------------------filter*/

    /**
     * [给定字符串中的字母是否全部为大写](Whether all the letters in the given string are uppercase)
     * @description: zh - 给定字符串中的字母是否全部为大写
     * @description: en - Whether all the letters in the given string are uppercase
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 2:01 下午
     * @param value: 被检查的字符串
     * @return boolean
    */
    public static boolean isUpperCase(CharSequence value) {
        if (Constant.NULL == value) { return Constant.FALSE; }
        final int len = value.length();
        for (int i = Constant.ZERO; i < len; i++) {
            if (Character.isLowerCase(value.charAt(i))) { return Constant.FALSE; }
        }
        return Constant.TRUE;
    }

    /*判断字符串是否是大小写的 -----------------------------------------------------------case*/

    /**
     * [给定字符串中的字母是否全部为小写](Whether all the letters in the given string are lowercase)
     * @description: zh - 给定字符串中的字母是否全部为小写
     * @description: en - Whether all the letters in the given string are lowercase
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 2:12 下午
     * @param value: 被检查的字符串
     * @return boolean
    */
    public static boolean isLowerCase(CharSequence value) {
        if (Constant.NULL == value) {
            return Constant.FALSE;
        }
        final int len = value.length();
        for (int i = Constant.ZERO; i < len; i++) {
            if (Character.isUpperCase(value.charAt(i))) {
                return Constant.FALSE;
            }
        }
        return Constant.TRUE;
    }

    /**
     * [切换给定字符串中的大小写](Toggles the case of a given string)
     * @description: zh - 切换给定字符串中的大小写
     * @description: en - Toggles the case of a given string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 4:00 下午
     * @param value: 字符串
     * @return java.lang.String
    */
    public static String swapCase(final String value) {
        if (isEmpty(value)) { return value; }
        final char[] buffer = value.toCharArray();
        for (int i = Constant.ZERO; i < buffer.length; i++) {
            final char ch = buffer[i];
            if (Character.isUpperCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
            } else if (Character.isLowerCase(ch)) {
                buffer[i] = Character.toUpperCase(ch);
            }
        }
        return new String(buffer);
    }

    /**
     * [将驼峰式命名的字符串转换为下划线方式](Convert hump named strings to underscores)
     * @description: zh - 将驼峰式命名的字符串转换为下划线方式
     * @description: en - Convert hump named strings to underscores
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 4:04 下午
     * @param value: 转换前的驼峰式命名的字符串，也可以为下划线形式
     * @return java.lang.String
    */
    public static String toUnderlineCase(CharSequence value) {
        return toSymbolCase(value, Constant.CHAR_UNDERLINE);
    }

    /**
     * [将驼峰式命名的字符串转换为使用符号连接方式](Convert hump named string to symbolic connection)
     * @description: zh - 将驼峰式命名的字符串转换为使用符号连接方式
     * @description: en - Convert hump named string to symbolic connection
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 4:10 下午
     * @param value: 转换前的驼峰式命名的字符串，也可以为符号连接形式
     * @param symbol: 连接符
     * @return java.lang.String
    */
    public static String toSymbolCase(CharSequence value, char symbol) {
        if (value == Constant.NULL) { return Constant.STRING_NULL; }

        final int length = value.length();
        final StrBuilder sb = new StrBuilder();
        char c;
        for (int i = Constant.ZERO; i < length; i++) {
            c = value.charAt(i);
            final Character preChar = (i > Constant.ZERO) ? value.charAt(i - Constant.ONE) : Constant.CHARACTER_NULL;
            if (Character.isUpperCase(c)) {
                // 遇到大写字母处理
                final Character nextChar = (i < value.length() - Constant.ONE) ? value.charAt(i + Constant.ONE) : Constant.CHARACTER_NULL;
                if (Constant.NULL != preChar && Character.isUpperCase(preChar)) {
                    // 前一个字符为大写，则按照一个词对待，例如AB
                    sb.append(c);
                } else if (Constant.NULL != nextChar && (!Character.isLowerCase(nextChar))) {
                    // 后一个为非小写字母，按照一个词对待
                    if (Constant.NULL != preChar && symbol != preChar) {
                        // 前一个是非大写时按照新词对待，加连接符，例如xAB
                        sb.append(symbol);
                    }
                    sb.append(c);
                } else {
                    // 前后都为非大写按照新词对待
                    if (Constant.NULL != preChar && symbol != preChar) {
                        // 前一个非连接符，补充连接符
                        sb.append(symbol);
                    }
                    sb.append(Character.toLowerCase(c));
                }
            } else {
                if (symbol != c
                        && sb.length() > Constant.ZERO
                        && Character.isUpperCase(sb.charAt(Constant.NEGATIVE_ONE))
                        && Character.isLowerCase(c)) {
                    // 当结果中前一个字母为大写，当前为小写(非数字或字符)，说明此字符为新词开始（连接符也表示新词）
                    sb.append(symbol);
                }
                // 小写或符号
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * [将下划线方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。](Converts a string named by underline to hump. If the string named by the pre conversion underline capitalization method is empty, the empty string is returned.)
     * @description: zh - 将下划线方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。
     * @description: en - Converts a string named by underline to hump. If the string named by the pre conversion underline capitalization method is empty, the empty string is returned.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/17 9:01 下午
     * @param name: 转换前的下划线大写方式命名的字符串
     * @return java.lang.String
    */
    public static String toCamelCase(CharSequence name) {
        if (Constant.NULL == name) {
            return Constant.STRING_NULL;
        }

        final String name2 = name.toString();
        if (contains(name2, Constant.CHAR_UNDERLINE)) {
            final int length = name2.length();
            final StringBuilder sb = new StringBuilder(length);
            boolean upperCase = Constant.FALSE;
            for (int i = Constant.ZERO; i < length; i++) {
                char c = name2.charAt(i);

                if (c == Constant.CHAR_UNDERLINE) {
                    upperCase = Constant.TRUE;
                } else if (upperCase) {
                    sb.append(Character.toUpperCase(c));
                    upperCase = Constant.FALSE;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
            return sb.toString();
        } else {
            return name2;
        }
    }

    /*给定字符串是否被字符包围 -----------------------------------------------------------isSurround*/

    /**
     * [给定字符串是否被字符包围](Whether the given string is surrounded by characters)
     * @description: zh - 给定字符串是否被字符包围
     * @description: en - Whether the given string is surrounded by characters
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 9:26 上午
     * @param str: 字符串
     * @param prefix: 前缀
     * @param suffix: 后缀
     * @return boolean
    */
    public static boolean isSurround(CharSequence str, CharSequence prefix, CharSequence suffix) {
        if (StrUtil.isBlank(str)) {
            return Constant.FALSE;
        }
        if (str.length() < (prefix.length() + suffix.length())) {
            return Constant.FALSE;
        }

        final String str2 = str.toString();
        return str2.startsWith(prefix.toString()) && str2.endsWith(suffix.toString());
    }

    /**
     * [给定字符串是否被字符包围](Whether the given string is surrounded by characters)
     * @description: zh - 给定字符串是否被字符包围
     * @description: en - Whether the given string is surrounded by characters
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 9:30 上午
     * @param str: 字符串
     * @param prefix: 前缀
     * @param suffix: 后缀
     * @return boolean
    */
    public static boolean isSurround(CharSequence str, char prefix, char suffix) {
        return StrUtil.isBlank(str) ? Constant.FALSE :
                str.length() < Constant.TWO ? Constant.FALSE :
                        str.charAt(Constant.ZERO) == prefix && str.charAt(str.length() - Constant.ONE) == suffix;
    }

    /*创建StringBuilder对象 -----------------------------------------------------------StringBuild*/

    /**
     * [创建StringBuilder对象](Create StringBuilder object)
     * @description: zh - 创建StringBuilder对象
     * @description: en - Create StringBuilder object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 9:36 上午
     * @param values: 初始字符串列表
     * @return java.lang.StringBuilder
    */
    public static StringBuilder builder(CharSequence... values) {
        final StringBuilder result = new StringBuilder();
        for (CharSequence value : values) {
            result.append(value);
        }
        return result;
    }

    /**
     * [创建 StrBuilder 对象](Creating StrBuilder objects)
     * @description: zh - 创建 StrBuilder 对象
     * @description: en - Creating StrBuilder objects
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 9:37 上午
     * @param values: 初始字符串列表
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public static StrBuilder strBuilder(CharSequence... values) {
        return StrBuilder.create(values);
    }

    /*标准属性名 -----------------------------------------------------------getter*/

    /**
     * [获得 set 或 get 或 is 方法对应的标准属性名](Get the standard property name corresponding to the set or get or is method)
     * @description: zh - 获得 set 或 get 或 is 方法对应的标准属性名
     * @description: en - Get the standard property name corresponding to the set or get or is method
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 9:40 上午
     * @param getOrSetMethodName: Get或Set方法名
     * @return java.lang.String
    */
    public static String getGeneralField(CharSequence getOrSetMethodName) {
        final String getOrSetMethodNameStr = getOrSetMethodName.toString();
        if (getOrSetMethodNameStr.startsWith("get") || getOrSetMethodNameStr.startsWith("set")) {
            return removePreAndLowerFirst(getOrSetMethodName, Constant.THREE);
        } else if (getOrSetMethodNameStr.startsWith("is")) {
            return removePreAndLowerFirst(getOrSetMethodName, Constant.TWO);
        }
        return Constant.STRING_NULL;
    }

    /**
     * [生成 set 方法名](Generate set method name)
     * @description: zh - 生成 set 方法名
     * @description: en - Generate set method name
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 9:41 上午
     * @param fieldName: 属性名
     * @return java.lang.String
    */
    public static String genSetter(CharSequence fieldName) {
        return upperFirstAndAddPre(fieldName, "set");
    }

    /**
     * [生成 get 方法名](Generate get method name)
     * @description: zh - 生成 get 方法名
     * @description: en - Generate get method name
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 9:41 上午
     * @param fieldName: 属性名
     * @return java.lang.String
    */
    public static String genGetter(CharSequence fieldName) {
        return upperFirstAndAddPre(fieldName, "get");
    }

    /*其他 -----------------------------------------------------------other*/

    /**
     * [连接多个字符串为一个](Connect multiple strings into one)
     * @description: zh - 连接多个字符串为一个
     * @description: en - Connect multiple strings into one
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 10:03 上午
     * @param isNullToEmpty: 是否null转为""
     * @param values: 字符串数组
     * @return java.lang.String
    */
    public static String concat(boolean isNullToEmpty, CharSequence... values) {
        final StrBuilder result = new StrBuilder();
        for (CharSequence value : values) {
            result.append(isNullToEmpty ? nullToEmpty(value) : value);
        }
        return result.toString();
    }

    /**
     * [将给定字符串，变成 "xxx...xxx" 形式的字符串](Change the given string into a string in the form of "XXX... XXX")
     * @description: zh - 将给定字符串，变成 "xxx...xxx" 形式的字符串
     * @description: en - Change the given string into a string in the form of "XXX... XXX"
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 10:05 上午
     * @param value: 字符串
     * @param maxLength: 最大长度
     * @return java.lang.String
    */
    public static String brief(CharSequence value, int maxLength) {
        if (Constant.NULL == value) {
            return Constant.STRING_NULL;
        }
        if (maxLength <= Constant.ZERO || value.length() <= maxLength) {
            return value.toString();
        }
        int w = maxLength / Constant.TWO;
        int l = value.length() + Constant.THREE;

        final String str2 = value.toString();
        return format("{}...{}", str2.substring(Constant.ZERO, maxLength - w), str2.substring(l - w));
    }

    /**
     * [以 conjunction 为分隔符将多个对象转换为字符串](Convert multiple objects to strings with conjunction as a delimiter)
     * @description: zh - 以 conjunction 为分隔符将多个对象转换为字符串
     * @description: en - Convert multiple objects to strings with conjunction as a delimiter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 10:06 上午
     * @param conjunction: 分隔符
     * @param values: 数组
     * @return java.lang.String
    */
    public static String join(CharSequence conjunction, Object... values) {
        return ArrayUtil.join(values, conjunction);
    }

    /**
     * [字符串的每一个字符是否都与定义的匹配器匹配](Does each character of the string match the defined matcher)
     * @description: zh - 字符串的每一个字符是否都与定义的匹配器匹配
     * @description: en - Does each character of the string match the defined matcher
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 10:07 上午
     * @param value: 字符串
     * @param matcher: 匹配器
     * @return boolean
    */
    public static boolean isAllCharMatch(CharSequence value, com.xiaoTools.core.matcher.Matcher<Character> matcher) {
        if (StrUtil.isBlank(value)) {
            return Constant.FALSE;
        }
        int len = value.length();
        for (int i = Constant.ZERO; i < len; i++) {
            if (!matcher.match(value.charAt(i))) {
                return Constant.FALSE;
            }
        }
        return Constant.TRUE;
    }

    /**
     * [循环位移指定位置的字符串为指定距离](Circular displacement the string at the specified position is the specified distance)
     * @description: zh - 循环位移指定位置的字符串为指定距离
     * @description: en - Circular displacement the string at the specified position is the specified distance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 10:09 上午
     * @param str: 字符串
     * @param startInclude: 起始位置（包括）
     * @param endExclude: 结束位置（不包括）
     * @param moveLength: 移动距离，负数表示左移，正数为右移
     * @return java.lang.String
    */
    public static String move(CharSequence str, int startInclude, int endExclude, int moveLength) {
        if (isEmpty(str)) {
            return str(str);
        }
        int len = str.length();
        if (Math.abs(moveLength) > len) {
            // 循环位移，当越界时循环
            moveLength = moveLength % len;
        }
        final StrBuilder strBuilder = StrBuilder.create(len);
        if (moveLength > Constant.ZERO) {
            int endAfterMove = Math.min(endExclude + moveLength, str.length());
            strBuilder.append(str.subSequence(Constant.ZERO, startInclude))
                    .append(str.subSequence(endExclude, endAfterMove))
                    .append(str.subSequence(startInclude, endExclude))
                    .append(str.subSequence(endAfterMove, str.length()));
        } else if (moveLength < Constant.ZERO) {
            int startAfterMove = Math.max(startInclude + moveLength, Constant.ZERO);
            strBuilder.append(str.subSequence(Constant.ZERO, startAfterMove))
                    .append(str.subSequence(startInclude, endExclude))
                    .append(str.subSequence(startAfterMove, startInclude))
                    .append(str.subSequence(endExclude, str.length()));
        } else {
            return str(str);
        }
        return strBuilder.toString();
    }

}
