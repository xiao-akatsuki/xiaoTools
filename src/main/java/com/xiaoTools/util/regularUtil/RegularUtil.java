package com.xiaoTools.util.regularUtil;

import com.xiaoTools.core.regular.patternPool.PatternPool;
import com.xiaoTools.entity.holder.Holder;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.strUtil.StrUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * [正则工具类](Regular tool class)
 * @description: zh - 正则工具类
 * @description: en - Regular tool class
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/21 6:08 下午
*/
public class RegularUtil {

    /**
     * 正则表达式匹配中文汉字
     */
    public final static String RE_CHINESE = Constant.STRING_REGULAR_CHINA;

    /**
     * 正则表达式匹配中文字符串
     */
    public final static String RE_CHINESES = RE_CHINESE + Constant.STRING_PLUS;

    /**
     * 正则中需要被转义的关键字
     */
    public final static Set<Character> RE_KEYS = CollUtil.newHashSet('$', '(', ')', '*', '+', '.', '[', ']', '?', '\\', '^', '{', '}', '|');

    /*获得匹配的字符串-----------------------------------------------------------get Group*/

    /**
     * [获得匹配的字符串，获得正则中分组0的内容](Get the matching string, get the content of 0 in the regular group)
     * @description: zh - 获得匹配的字符串，获得正则中分组0的内容
     * @description: en - Get the matching string, get the content of 0 in the regular group
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 6:23 下午
     * @param regex: 匹配的正则
     * @param content: 被匹配的内容
     * @return java.lang.String
    */
    public static String getGroup0(String regex, CharSequence content) {
        return get(regex, content, Constant.ZERO);
    }

    /**
     * [获得匹配的字符串，获得正则中分组0的内容](Get the matching string, get the content of 0 in the regular group)
     * @description: zh - 获得匹配的字符串，获得正则中分组0的内容
     * @description: en - Get the matching string, get the content of 0 in the regular group
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:23 下午
     * @param pattern: 编译后的正则模式
     * @param content: 被匹配的内容
     * @return java.lang.String
    */
    public static String getGroup0(Pattern pattern, CharSequence content) {
        return get(pattern, content, Constant.ZERO);
    }

    /**
     * [获得匹配的字符串，获得正则中分组1的内容](Get the matching string, get the content of group 1 in the regular)
     * @description: zh - 获得匹配的字符串，获得正则中分组1的内容
     * @description: en - Get the matching string, get the content of group 1 in the regular
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 6:47 下午
     * @param regex: 匹配的正则
     * @param content: 被匹配的内容
     * @return java.lang.String
    */
    public static String getGroup1(String regex, CharSequence content) {
        return get(regex, content, Constant.ONE);
    }

    /**
     * [获得匹配的字符串，获得正则中分组1的内容](Get the matching string, get the content of group 1 in the regular)
     * @description: zh - 获得匹配的字符串，获得正则中分组1的内容
     * @description: en - Get the matching string, get the content of group 1 in the regular
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:25 下午
     * @param pattern: 编译后的正则模式
     * @param content: 被匹配的内容
     * @return java.lang.String
    */
    public static String getGroup1(Pattern pattern, CharSequence content) {
        return get(pattern, content, Constant.ONE);
    }

    /*获得匹配的字符串-----------------------------------------------------------get*/

    /**
     * [获得匹配的字符串](Get the matching string)
     * @description: zh - 获得匹配的字符串
     * @description: en - Get the matching string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 6:48 下午
     * @param regex: 匹配的正则
     * @param content: 被匹配的内容
     * @param groupIndex: 匹配正则的分组序号
     * @return java.lang.String
    */
    public static String get(String regex, CharSequence content, int groupIndex) {
        if (Constant.NULL == content || Constant.NULL == regex) {
            return Constant.STRING_NULL;
        }
        final Pattern pattern = PatternPool.get(regex, Pattern.DOTALL);
        return get(pattern, content, groupIndex);
    }

    /**
     * [获得匹配的字符串](Get the matching string)
     * @description: zh - 获得匹配的字符串
     * @description: en - Get the matching string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:14 下午
     * @param pattern: 匹配的正则
     * @param content: 被匹配的内容
     * @param groupIndex: 匹配正则的分组序号
     * @return java.lang.String
    */
    public static String get(Pattern pattern, CharSequence content, int groupIndex) {
        if (Constant.NULL == content || Constant.NULL == pattern) {
            return Constant.STRING_NULL;
        }
        final Matcher matcher = pattern.matcher(content);
        return matcher.find() ? matcher.group(groupIndex) : Constant.STRING_NULL;
    }

    /**
     * [获得匹配的字符串匹配到的所有分组](Get all the groups matched by the matched string)
     * @description: zh - 获得匹配的字符串匹配到的所有分组
     * @description: en - Get all the groups matched by the matched string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:29 下午
     * @param pattern: 编译后的正则模式
     * @param content: 被匹配的内容
     * @return java.util.List<java.lang.String>
    */
    public static List<String> getAllGroups(Pattern pattern, CharSequence content) {
        return getAllGroups(pattern, content, Constant.TRUE);
    }

    /*获得匹配的字符串匹配到的所有分组-----------------------------------------------------------get All Groups*/

    /**
     * [获得匹配的字符串匹配到的所有分组](Get all the groups matched by the matched string)
     * @description: zh - 获得匹配的字符串匹配到的所有分组
     * @description: en - Get all the groups matched by the matched string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:31 下午
     * @param pattern: 编译后的正则模式
     * @param content: 被匹配的内容
     * @param withGroup: 是否包括分组，此分组表示全匹配的信息
     * @return java.util.List<java.lang.String>
    */
    public static List<String> getAllGroups(Pattern pattern, CharSequence content, boolean withGroup) {
        if (Constant.NULL == content || Constant.NULL == pattern) {
            return Constant.LIST_STRING_NULL;
        }
        ArrayList<String> result = new ArrayList<>();
        final Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            final int startGroup = withGroup ? Constant.ZERO : Constant.ONE;
            final int groupCount = matcher.groupCount();
            for (int i = startGroup; i <= groupCount; i++) {
                result.add(matcher.group(i));
            }
        }
        return result;
    }

    /*提取多个-----------------------------------------------------------extract Multi*/

    /**
     * [从content中匹配出多个值并根据template生成新的字符串](Match multiple values from the content and generate a new string according to the template)
     * @description: zh - 从content中匹配出多个值并根据template生成新的字符串
     * @description: en - Match multiple values from the content and generate a new string according to the template
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 8:09 下午
     * @param pattern: 匹配正则
     * @param content: 被匹配的内容
     * @param template: 生成内容模板
     * @return java.lang.String
    */
    public static String extractMulti(Pattern pattern, CharSequence content, String template) {
        if (Constant.NULL == content || Constant.NULL == pattern || Constant.NULL == template) {
            return Constant.STRING_NULL;
        }
        //提取模板中的编号
        final TreeSet<Integer> varNums = new TreeSet<>((o1, o2) -> ObjectUtil.compare(o2, o1));
        final Matcher matcherForTemplate = PatternPool.GROUP_VAR.matcher(template);
        while (matcherForTemplate.find()) {
            varNums.add(Integer.parseInt(matcherForTemplate.group(Constant.ONE)));
        }
        final Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            for (Integer group : varNums) {
                template = template.replace(Constant.STRING_DOLLAR + group, matcher.group(group));
            }
            return template;
        }
        return Constant.STRING_NULL;
    }

    /**
     * [从content中匹配出多个值并根据template生成新的字符串](Match multiple values from the content and generate a new string according to the template)
     * @description: zh - 从content中匹配出多个值并根据template生成新的字符串
     * @description: en - Match multiple values from the content and generate a new string according to the template
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 8:11 下午
     * @param regex: 匹配正则字符串
     * @param content: 被匹配的内容
     * @param template: 生成内容模板
     * @return java.lang.String
    */
    public static String extractMulti(String regex, CharSequence content, String template) {
        if (Constant.NULL == content || Constant.NULL == regex || Constant.NULL == template) {
            return Constant.STRING_NULL;
        }
        final Pattern pattern = PatternPool.get(regex, Pattern.DOTALL);
        return extractMulti(pattern, content, template);
    }

    /**
     * [从content中匹配出多个值并根据template生成新的字符串](Match multiple values from the content and generate a new string according to the template)
     * @description: zh - 从content中匹配出多个值并根据template生成新的字符串
     * @description: en - Match multiple values from the content and generate a new string according to the template
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 8:40 下午
     * @param pattern: 匹配正则
     * @param contentHolder: 被匹配的内容的Holder，value为内容正文，经过这个方法的原文将被去掉匹配之前的内容
     * @param template: 生成内容模板
     * @return java.lang.String
    */
    public static String extractMultiAndDelPre(Pattern pattern, Holder<CharSequence> contentHolder, String template) {
        if (Constant.NULL == contentHolder || Constant.NULL == pattern || Constant.NULL == template) {
            return Constant.STRING_NULL;
        }

        HashSet<String> varNums = findAll(PatternPool.GROUP_VAR, template, Constant.ONE, new HashSet<>());

        final CharSequence content = contentHolder.get();
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            for (String var : varNums) {
                int group = Integer.parseInt(var);
                template = template.replace(Constant.STRING_DOLLAR + var, matcher.group(group));
            }
            contentHolder.set(StrUtil.sub(content, matcher.end(), content.length()));
            return template;
        }
        return Constant.STRING_NULL;
    }

    /*取得内容中匹配的所有结果-----------------------------------------------------------findAll*/

    /**
     * [取得内容中匹配的所有结果](Get all the results that match in the content)
     * @description: zh - 取得内容中匹配的所有结果
     * @description: en - Get all the results that match in the content
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 8:41 下午
     * @param pattern: 编译后的正则模式
     * @param content: 被查找的内容
     * @param group: 正则的分组
     * @return java.util.List<java.lang.String>
    */
    public static List<String> findAll(Pattern pattern, CharSequence content, int group) {
        return findAll(pattern, content, group, new ArrayList<>());
    }

    /**
     * [取得内容中匹配的所有结果](Get all the results that match in the content)
     * @description: zh - 取得内容中匹配的所有结果
     * @description: en - Get all the results that match in the content
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 8:40 下午
     * @param pattern: 编译后的正则模式
     * @param content: 被查找的内容
     * @param group: 正则的分组
     * @param collection: 返回的集合类型
     * @return T
    */
    public static <T extends Collection<String>> T findAll(Pattern pattern, CharSequence content, int group, T collection) {
        if (Constant.NULL == pattern || Constant.NULL == content) {
            return null;
        }
        if (Constant.NULL == collection) {
            throw new NullPointerException("Null collection param provided!");
        }
        final Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            collection.add(matcher.group(group));
        }
        return collection;
    }

}
