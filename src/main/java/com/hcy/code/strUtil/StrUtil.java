package com.hcy.code.strUtil;

import com.hcy.code.charUtil.CharUtil;

import java.util.List;

/**
 * 字符串工具类
 * @author HCY
 * @since 2021/5/14 3:42 下午
*/
public class StrUtil {
    /**
     * 初始化方法
     * @author HCY
     * @since 2021/5/14 3:42 下午
     * @return null
    */
    public StrUtil() {
    }

    /**
     * [判断字符串是否为空](只判断是否为空或者是否为null)
     * @author HCY
     * @since 2021/5/14 3:55 下午
     * @param value: 需要判断的字符串
     * @return boolean
    */
    public static boolean isEmpty(CharSequence value){
        return value == null || value.length() == 0;
    }

    /**
     * 判断字符串中是否有空的字符串
     * @author HCY
     * @since 2021/5/14 5:04 下午
     * @param value: 需要判断的字符串
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
     * 判断字符串是否为空
     * @author HCY
     * @since 2021/5/14 7:10 下午
     * @param cs: 需要的字符串
     * @return java.lang.String
    */
    public static String str(CharSequence cs) {
        return null == cs ? null : cs.toString();
    }

    /**
     * 截取字符串
     * @author HCY
     * @since 2021/5/14 8:01 下午
     * @param value: 需要截取的字符串
     * @param formIndex: 开始位置
     * @param toIndex: 结束位置
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
     * 删除前缀
     * @author HCY
     * @since 2021/5/14 8:16 下午
     * @param value: 需要删除前缀的字符串
     * @param prefix: 前缀
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
     * 删除后缀
     * @author HCY
     * @since 2021/5/14 8:25 下午
     * @param value: 需要删除后缀的字符串
     * @param suffix: 后缀
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
     * 截取字符串
     * @author HCY
     * @since 2021/5/14 8:18 下午
     * @param string: 字符串
     * @param fromIndex: 截取的位置
     * @return java.lang.String
    */
    public static String subSuf(CharSequence string, int fromIndex) {
        return isEmpty(string) ? null : sub(string, fromIndex, string.length());
    }

    /**
     * 截取字符串
     * @author HCY
     * @since 2021/5/14 8:24 下午
     * @param string: 字符串
     * @param toIndexExclude: 截取的位置
     * @return java.lang.String
    */
    public static String subPre(CharSequence string, int toIndexExclude) {
        return sub(string, 0, toIndexExclude);
    }

    public static boolean startWith(String value, char c) {
        if (isEmpty(value)) {
            return false;
        } else {
            return c == value.charAt(0);
        }
    }

    public static List<String> split(String value, char separator) {
        return null;
    }
}
