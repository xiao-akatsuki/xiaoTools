package com.xiaoTools.core.text.strSpliter;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.numUtil.NumUtil;
import com.xiaoTools.util.strUtil.StrUtil;

import java.util.ArrayList;
import java.util.List;

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
        return split(str, separator, 0, true, ignoreEmpty);
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
}
