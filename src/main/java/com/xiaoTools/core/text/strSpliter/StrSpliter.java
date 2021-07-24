package com.xiaoTools.core.text.strSpliter;

import com.xiaoTools.core.regular.patternPool.PatternPool;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.charUtil.CharUtil;
import com.xiaoTools.util.numUtil.NumUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * [字符串切割工具类](String cutting tool class)
 * @description: zh - 字符串切割工具类
 * @description: en - String cutting tool class
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/25 8:27 上午
*/
public class StrSpliter {

    // 拆分字符串---------------------------------------------------------------------------------------------- Split by char

    /**
     * [切分字符串路径，仅支持Unix分界符：/](Split string path, only UNIX separators are supported:/)
     * @description: zh - 切分字符串路径，仅支持Unix分界符：/
     * @description: en - Split string path, only UNIX separators are supported:/
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/25 8:12 下午
     * @param str: 被切分的字符串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> splitPath(String str) {
        return splitPath(str, Constant.ZERO);
    }

    /**
     * [切分字符串路径，仅支持Unix分界符：/ ](Split string path, only UNIX separators are supported:/ )
     * @description: zh - 切分字符串路径，仅支持Unix分界符：/
     * @description: en - Split string path, only UNIX separators are supported:/
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/25 8:14 下午
     * @param str: 被切分的字符串
     * @return java.lang.String[]
    */
    public static String[] splitPathToArray(String str) {
        return toArray(splitPath(str));
    }

    /**
     * [切分字符串路径，仅支持Unix分界符：/](Split string path, only UNIX separators are supported:/)
     * @description: zh - 切分字符串路径，仅支持Unix分界符：/
     * @description: en - Split string path, only UNIX separators are supported:/
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/25 8:16 下午
     * @param str: 被切分的字符串
     * @param limit: 限制分片数
     * @return java.util.List<java.lang.String>
    */
    public static List<String> splitPath(String str, int limit) {
        return split(str, Constant.CHAR_VERTICAL_LINE, limit, Constant.TRUE, Constant.TRUE);
    }

    /**
     * [切分字符串路径，仅支持Unix分界符：/](Split string path, only UNIX separators are supported:/)
     * @description: zh - 切分字符串路径，仅支持Unix分界符：/
     * @description: en - Split string path, only UNIX separators are supported:/
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/25 8:17 下午
     * @param str: 被切分的字符串
     * @param limit: 限制分片数
     * @return java.lang.String[]
    */
    public static String[] splitPathToArray(String str, int limit) {
        return toArray(splitPath(str, limit));
    }

    /**
     * [切分字符串](Segmentation string)
     * @description: zh - 切分字符串
     * @description: en - Segmentation string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/25 8:18 下午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> splitTrim(String str, char separator, boolean ignoreEmpty) {
        return split(str, separator, Constant.ZERO, Constant.TRUE, ignoreEmpty);
    }

    /**
     * [切分字符串](Segmentation string)
     * @description: zh - 切分字符串
     * @description: en - Segmentation string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/25 8:18 下午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> split(String str, char separator, boolean isTrim, boolean ignoreEmpty) {
        return split(str, separator, 0, isTrim, ignoreEmpty);
    }

    /**
     * [切分字符串，大小写敏感，去除每个元素两边空白符](String segmentation, case sensitive, remove each element on both sides of the white space)
     * @description: zh - 切分字符串，大小写敏感，去除每个元素两边空白符
     * @description: en - String segmentation, case sensitive, remove each element on both sides of the white space
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/25 8:20 下午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @param limit: 限制分片数，-1不限制
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> splitTrim(String str, char separator, int limit, boolean ignoreEmpty) {
        return split(str, separator, limit, true, ignoreEmpty, false);
    }

    /**
     * [切分字符串，大小写敏感](String segmentation, case sensitive)
     * @description: zh - 切分字符串，大小写敏感
     * @description: en - String segmentation, case sensitive
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 8:53 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @param limit: 限制分片数，-1不限制
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> split(String str, char separator, int limit, boolean isTrim, boolean ignoreEmpty) {
        return split(str, separator, limit, isTrim, ignoreEmpty, false);
    }

    /**
     * [切分字符串，忽略大小写](Split string, ignore case)
     * @description: zh - 切分字符串，忽略大小写
     * @description: en - Split string, ignore case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 8:54 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @param limit: 限制分片数，-1不限制
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> splitIgnoreCase(String str, char separator, int limit, boolean isTrim, boolean ignoreEmpty) {
        return split(str, separator, limit, isTrim, ignoreEmpty, true);
    }

    /**
     * [切分字符串](Segmentation string)
     * @description: zh - 切分字符串
     * @description: en - Segmentation string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 8:56 上午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @param limit: 限制分片数，-1不限制
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @param ignoreCase: 是否忽略大小写
     * @return java.util.List<java.lang.String>
    */
    public static List<String> split(String str, char separator, int limit, boolean isTrim, boolean ignoreEmpty, boolean ignoreCase) {
        if (StrUtil.isEmpty(str)) {
            return new ArrayList<>(Constant.ZERO);
        }
        if (limit == Constant.ONE) {
            return addToList(new ArrayList<>(Constant.ONE), str, isTrim, ignoreEmpty);
        }

        final ArrayList<String> list = new ArrayList<>(limit > Constant.ZERO ? limit : Constant.SIXTEEN);
        int len = str.length();
        //切分后每个部分的起始
        int start = Constant.ZERO;
        for (int i = Constant.ZERO; i < len; i++) {
            if (NumUtil.equals(separator, str.charAt(i), ignoreCase)) {
                addToList(list, str.substring(start, i), isTrim, ignoreEmpty);
                //i+1同时将start与i保持一致
                start = i + Constant.ONE;

                //检查是否超出范围（最大允许limit-1个，剩下一个留给末尾字符串）
                if (limit > Constant.ZERO && list.size() > limit - Constant.TWO) {
                    break;
                }
            }
        }
        //收尾
        return addToList(list, str.substring(start, len), isTrim, ignoreEmpty);
    }

    /**
     * [切分字符串为字符串数组](Splitting strings into string arrays)
     * @description: zh - 切分字符串为字符串数组
     * @description: en - Splitting strings into string arrays
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 8:18 下午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @param limit: 限制分片数
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @return java.lang.String[]
    */
    public static String[] splitToArray(String str, char separator, int limit, boolean isTrim, boolean ignoreEmpty) {
        return toArray(split(str, separator, limit, isTrim, ignoreEmpty));
    }

    /**
     * [切分字符串，不忽略大小写](Split string, not ignore case)
     * @description: zh - 切分字符串，不忽略大小写
     * @description: en - Split string, not ignore case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 9:02 下午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符串
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> split(String str, String separator, boolean isTrim, boolean ignoreEmpty) {
        return split(str, separator, Constant.NEGATIVE_ONE, isTrim, ignoreEmpty, false);
    }

    /**
     * [切分字符串，去除每个元素两边空格，忽略大小写](Cut the string, remove the space on both sides of each element, ignore the case)
     * @description: zh - 切分字符串，去除每个元素两边空格，忽略大小写
     * @description: en - Cut the string, remove the space on both sides of each element, ignore the case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 9:03 下午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符串
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> splitTrim(String str, String separator, boolean ignoreEmpty) {
        return split(str, separator, Constant.TRUE, ignoreEmpty);
    }

    /**
     * [切分字符串，不忽略大小写](Split string, not ignore case)
     * @description: zh - 切分字符串，不忽略大小写
     * @description: en - Split string, not ignore case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 9:04 下午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符串
     * @param limit: 限制分片数
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> split(String str, String separator, int limit, boolean isTrim, boolean ignoreEmpty) {
        return split(str, separator, limit, isTrim, ignoreEmpty, false);
    }

    /**
     * [切分字符串，去除每个元素两边空格，忽略大小写](Cut the string, remove the space on both sides of each element, ignore the case)
     * @description: zh - 切分字符串，去除每个元素两边空格，忽略大小写
     * @description: en - Cut the string, remove the space on both sides of each element, ignore the case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 9:05 下午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符串
     * @param limit: 限制分片数
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> splitTrim(String str, String separator, int limit, boolean ignoreEmpty) {
        return split(str, separator, limit, true, ignoreEmpty);
    }

    /**
     * [切分字符串，忽略大小写](Split string, ignore case)
     * @description: zh - 切分字符串，忽略大小写
     * @description: en - Split string, ignore case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 9:07 下午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符串
     * @param limit: 限制分片数
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> splitIgnoreCase(String str, String separator, int limit, boolean isTrim, boolean ignoreEmpty) {
        return split(str, separator, limit, isTrim, ignoreEmpty, true);
    }

    /**
     * [切分字符串，去除每个元素两边空格，忽略大小写](Cut the string, remove the space on both sides of each element, ignore the case)
     * @description: zh - 切分字符串，去除每个元素两边空格，忽略大小写
     * @description: en - Cut the string, remove the space on both sides of each element, ignore the case
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 9:08 下午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符串
     * @param limit: 限制分片数
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> splitTrimIgnoreCase(String str, String separator, int limit, boolean ignoreEmpty) {
        return split(str, separator, limit, true, ignoreEmpty, true);
    }

    /**
     * [切分字符串](Segmentation string)
     * @description: zh - 切分字符串
     * @description: en - Segmentation string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 9:20 下午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符串
     * @param limit: 限制分片数
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @param ignoreCase: 是否忽略大小写
     * @return java.util.List<java.lang.String>
    */
    public static List<String> split(String str, String separator, int limit, boolean isTrim, boolean ignoreEmpty, boolean ignoreCase) {
        if (StrUtil.isEmpty(str)) {
            return new ArrayList<>(Constant.ZERO);
        }
        if (limit == Constant.ONE) {
            return addToList(new ArrayList<>(Constant.ONE), str, isTrim, ignoreEmpty);
        }
        //分隔符为空时按照空白符切分
        if (StrUtil.isEmpty(separator)) {
            return split(str, limit);
        } else if (separator.length() == Constant.ONE) {
            //分隔符只有一个字符长度时按照单分隔符切分
            return split(str, separator.charAt(Constant.ZERO), limit, isTrim, ignoreEmpty, ignoreCase);
        }

        final ArrayList<String> list = new ArrayList<>();
        int len = str.length();
        int separatorLen = separator.length();
        int start = Constant.ZERO;
        int i = Constant.ZERO;
        while (i < len) {
            i = StrUtil.indexOf(str, separator, start, ignoreCase);
            if (i > Constant.NEGATIVE_ONE) {
                addToList(list, str.substring(start, i), isTrim, ignoreEmpty);
                start = i + separatorLen;

                //检查是否超出范围（最大允许limit-1个，剩下一个留给末尾字符串）
                if (limit > Constant.ZERO && list.size() > limit - Constant.TWO) {
                    break;
                }
            } else {
                break;
            }
        }
        return addToList(list, str.substring(start, len), isTrim, ignoreEmpty);
    }

    /**
     * [使用空白符切分字符串](Use white space character to segment string)
     * @description: zh - 使用空白符切分字符串
     * @description: en - Use white space character to segment string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 9:14 下午
     * @param str: 被切分的字符串
     * @param limit: 限制分片数
     * @return java.util.List<java.lang.String>
    */
    public static List<String> split(String str, int limit) {
        if (StrUtil.isEmpty(str)) {
            return new ArrayList<>(0);
        }
        if (limit == 1) {
            return addToList(new ArrayList<>(1), str, true, true);
        }

        final ArrayList<String> list = new ArrayList<>();
        int len = str.length();
        //切分后每个部分的起始
        int start = 0;
        for (int i = 0; i < len; i++) {
            if (CharUtil.isBlankChar(str.charAt(i))) {
                addToList(list, str.substring(start, i), true, true);
                //i+1同时将start与i保持一致
                start = i + 1;
                //检查是否超出范围（最大允许limit-1个，剩下一个留给末尾字符串）
                if (limit > 0 && list.size() > limit - 2) {
                    break;
                }
            }
        }
        return addToList(list, str.substring(start, len), true, true);//收尾
    }

    /**
     * [切分字符串为字符串数组](Splitting strings into string arrays)
     * @description: zh - 切分字符串为字符串数组
     * @description: en - Splitting strings into string arrays
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 9:15 下午
     * @param str: 被切分的字符串
     * @param limit: 限制分片数
     * @return java.lang.String[]
    */
    public static String[] splitToArray(String str, int limit) {
        return toArray(split(str, limit));
    }

    // 通过正则切分字符串 ---------------------------------------------------------------------------------------------- Split by regex

    /**
     * [通过正则切分字符串](Segmentation of strings by regular segmentation)
     * @description: zh - 通过正则切分字符串
     * @description: en - Segmentation of strings by regular segmentation
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 8:58 上午
     * @param str: 字符串
     * @param separatorRegex: 分隔符正则
     * @param limit: 限制分片数
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> splitByRegex(String str, String separatorRegex, int limit, boolean isTrim, boolean ignoreEmpty) {
        final Pattern pattern = PatternPool.get(separatorRegex);
        return split(str, pattern, limit, isTrim, ignoreEmpty);
    }

    /**
     * [切分字符串为字符串数组](Splitting strings into string arrays)
     * @description: zh - 切分字符串为字符串数组
     * @description: en - Splitting strings into string arrays
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 9:10 下午
     * @param str: 被切分的字符串
     * @param separator: 分隔符字符
     * @param limit: 限制分片数
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @return java.lang.String[]
    */
    public static String[] splitToArray(String str, String separator, int limit, boolean isTrim, boolean ignoreEmpty) {
        return toArray(split(str, separator, limit, isTrim, ignoreEmpty));
    }

    /**
     * [通过正则切分字符串为字符串数组](Regular segmentation of strings into string arrays)
     * @description: zh - 通过正则切分字符串为字符串数组
     * @description: em - Regular segmentation of strings into string arrays
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 6:43 下午
     * @param str: 被切分的字符串
     * @param separatorPattern: 分隔符正则Pattern
     * @param limit: 限制分片数
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @return java.lang.String[]
    */
    public static String[] splitToArray(String str, Pattern separatorPattern, int limit, boolean isTrim, boolean ignoreEmpty) {
        return toArray(split(str, separatorPattern, limit, isTrim, ignoreEmpty));
    }

    /**
     * [通过正则切分字符串](Segmentation of strings by regular segmentation)
     * @description: zh - 通过正则切分字符串
     * @description: en - Segmentation of strings by regular segmentation
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 9:18 下午
     * @param str: 字符串
     * @param separatorPattern: 分隔符正则Pattern
     * @param limit: 限制分片数
     * @param isTrim: 是否去除切分字符串后每个元素两边的空格
     * @param ignoreEmpty: 是否忽略空串
     * @return java.util.List<java.lang.String>
    */
    public static List<String> split(String str, Pattern separatorPattern, int limit, boolean isTrim, boolean ignoreEmpty) {
        if (StrUtil.isEmpty(str)) {
            return new ArrayList<>(Constant.ZERO);
        }
        if (limit == Constant.ONE) {
            return addToList(new ArrayList<>(Constant.ONE), str, isTrim, ignoreEmpty);
        }

        if (Constant.NULL == separatorPattern) {
            //分隔符为空时按照空白符切分
            return split(str, limit);
        }

        final Matcher matcher = separatorPattern.matcher(str);
        final ArrayList<String> list = new ArrayList<>();
        int len = str.length();
        int start = Constant.ZERO;
        while (matcher.find()) {
            addToList(list, str.substring(start, matcher.start()), isTrim, ignoreEmpty);
            start = matcher.end();

            //检查是否超出范围（最大允许limit-1个，剩下一个留给末尾字符串）
            if (limit > Constant.ZERO && list.size() > limit - Constant.TWO) {
                break;
            }
        }
        return addToList(list, str.substring(start, len), isTrim, ignoreEmpty);
    }

    // 将给定字符串截取为多个部分 ---------------------------------------------------------------------------------------------- Split by length

    /**
     * [根据给定长度，将给定字符串截取为多个部分](According to the given length, the given string is intercepted into multiple parts)
     * @description: zh - 根据给定长度，将给定字符串截取为多个部分
     * @description: em - According to the given length, the given string is intercepted into multiple parts
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 6:45 下午
     * @param str: 字符串
     * @param len: 每一个小节的长度
     * @return java.lang.String[]
    */
    public static String[] splitByLength(String str, int len) {
        int partCount = str.length() / len;
        int lastPartCount = str.length() % len;
        int fixPart = Constant.ZERO;
        if (lastPartCount != Constant.ZERO) {
            fixPart = Constant.ONE;
        }

        final String[] strs = new String[partCount + fixPart];
        for (int i = Constant.ZERO; i < partCount + fixPart; i++) {
            if (i == partCount + fixPart - Constant.ONE && lastPartCount != Constant.ZERO) {
                strs[i] = str.substring(i * len, i * len + lastPartCount);
            } else {
                strs[i] = str.substring(i * len, i * len + len);
            }
        }
        return strs;
    }

    /**
     * [将字符串加入List中](Add string to list)
     * @description: zh - 将字符串加入List中
     * @description: en - Add string to list
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 6:45 下午
     * @param list: 列表
     * @param part: 被加入的部分
     * @param isTrim: 是否去除两端空白符
     * @param ignoreEmpty: 是否略过空字符串（空字符串不做为一个元素）
     * @return java.util.List<java.lang.String>
    */
    private static List<String> addToList(List<String> list, String part, boolean isTrim, boolean ignoreEmpty) {
        if (isTrim) {
            part = StrUtil.trim(part);
        }
        if (!ignoreEmpty || !part.isEmpty()) {
            list.add(part);
        }
        return list;
    }

    /**
     * [List转Array](List to Array)
     * @description: zh - List转Array
     * @description: en - List to Array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/26 6:46 下午
     * @param list: 集合
     * @return java.lang.String[]
    */
    private static String[] toArray(List<String> list) {
        return list.toArray(new String[Constant.ZERO]);
    }
}
