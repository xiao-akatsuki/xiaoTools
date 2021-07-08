package com.xiaoTools.util.charSequenceUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.charUtil.CharUtil;

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


}
