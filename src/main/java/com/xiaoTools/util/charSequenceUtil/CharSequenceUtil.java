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
}
