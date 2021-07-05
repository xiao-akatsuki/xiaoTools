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


}
