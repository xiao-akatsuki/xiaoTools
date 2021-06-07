package com.xiaoTools.core.strUtil;

import com.xiaoTools.core.arrayUtil.ArrayUtil;
import com.xiaoTools.core.charUtil.CharUtil;

import java.util.List;

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
        return value == null || value.length() == 0;
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
        int length = 0;
        if (value != null && (length = value.length()) != 0){
            for (int i = 0; i < length; i++) {
                if (!CharUtil.isBlankChar(value.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
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
            if (formIndex < 0) {
                formIndex += length;
                if (formIndex < 0) {
                    formIndex = 0;
                }
            }else if (formIndex > length) {
                formIndex = length;
            }

            if (toIndex < 0) {
                toIndex += length;
                if (toIndex < 0) {
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
            return formIndex == toIndex ? "" : value.toString().substring(formIndex, toIndex);
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
        return isEmpty(string) ? null : sub(string, fromIndex, string.length());
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
        return sub(string, 0, toIndexExclude);
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
            for(int i = 0; i < len; ++i) {
                if (ArrayUtil.contains(testChars, string.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
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
        return str.toString().replace(strToRemove, "");
    }
}
