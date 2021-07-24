package com.xiaoTools.util.regularUtil;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.exception.utilException.UtilException;
import com.xiaoTools.core.regular.patternPool.PatternPool;
import com.xiaoTools.entity.holder.Holder;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.regularUtil.method.Func1;

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

    /**
     * [取得内容中匹配的所有结果，获得匹配的所有结果中正则对应分组0的内容](Get all the matching results in the content, and get the content of the regular corresponding group 0 in all the matching results)
     * @description: zh - 取得内容中匹配的所有结果，获得匹配的所有结果中正则对应分组0的内容
     * @description: en - Get all the matching results in the content, and get the content of the regular corresponding group 0 in all the matching results
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:49 上午
     * @param regex: 正则
     * @param content: 被查找的内容
     * @return java.util.List<java.lang.String>
    */
    public static List<String> findAllGroup0(String regex, CharSequence content) {
        return findAll(regex, content, Constant.ZERO);
    }

    /**
     * [取得内容中匹配的所有结果，获得匹配的所有结果中正则对应分组1的内容](Get all the matching results in the content, and get the content of the regular corresponding group 1 in all the matching results)
     * @description: zh - 取得内容中匹配的所有结果，获得匹配的所有结果中正则对应分组1的内容
     * @description: en - Get all the matching results in the content, and get the content of the regular corresponding group 1 in all the matching results
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:50 上午
     * @param regex: 正则
     * @param content: 被查找的内容
     * @return java.util.List<java.lang.String>
    */
    public static List<String> findAllGroup1(String regex, CharSequence content) {
        return findAll(regex, content, Constant.ONE);
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

    /**
     * [从字符串中获得第一个整数](Gets the first integer from a string)
     * @description: zh - 从字符串中获得第一个整数
     * @description: en - Gets the first integer from a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 9:28 上午
     * @param StringWithNumber: 带数字的字符串
     * @return java.lang.Integer
    */
    public static Integer getFirstNumber(CharSequence StringWithNumber) {
        return Convert.toInt(get(PatternPool.NUMBERS, StringWithNumber, Constant.ZERO), null);
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

    /**
     * [取得内容中匹配的所有结果](Get all the results that match in the content)
     * @description: zh - 取得内容中匹配的所有结果
     * @description: en - Get all the results that match in the content
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:47 上午
     * @param regex: 正则
     * @param content: 被查找的内容
     * @param group: 正则的分组
     * @return java.util.List<java.lang.String>
    */
    public static List<String> findAll(String regex, CharSequence content, int group) {
        return findAll(regex, content, group, new ArrayList<>());
    }

    /**
     * [取得内容中匹配的所有结果](Get all the results that match in the content)
     * @description: zh - 取得内容中匹配的所有结果
     * @description: en - Get all the results that match in the content
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 9:19 上午
     * @param regex: 正则
     * @param content: 被查找的内容
     * @param group: 正则的分组
     * @param collection: 返回的集合类型
     * @return T
    */
    public static <T extends Collection<String>> T findAll(String regex, CharSequence content, int group, T collection) {
        return Constant.NULL == regex ? collection : findAll(Pattern.compile(regex, Pattern.DOTALL), content, group, collection);
    }

    /*删除匹配的第一个内容-----------------------------------------------------------delete first*/

    /**
     * [删除匹配的第一个内容](Delete the first match)
     * @description: zh - 删除匹配的第一个内容
     * @description: en - Delete the first match
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:33 上午
     * @param regex: 正则
     * @param content: 被匹配的内容
     * @return java.lang.String
    */
    public static String delFirst(String regex, CharSequence content) {
        if (StrUtil.isBlank(regex, content)) {
            return StrUtil.str(content);
        }
        final Pattern pattern = PatternPool.get(regex, Pattern.DOTALL);
        return delFirst(pattern, content);
    }

    /**
     * [删除匹配的第一个内容](Delete the first match)
     * @description: zh - 删除匹配的第一个内容
     * @description: en - Delete the first match
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:38 上午
     * @param pattern: 正则
     * @param content: 被匹配的内容
     * @return java.lang.String
    */
    public static String delFirst(Pattern pattern, CharSequence content) {
        return Constant.NULL == pattern || StrUtil.isBlank(content) ? StrUtil.str(content) : pattern.matcher(content).replaceFirst(Constant.EMPTY);
    }

    /**
     * [删除匹配的全部内容](Delete all matches)
     * @description: zh - 删除匹配的全部内容
     * @description: en - Delete all matches
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:40 上午
     * @param regex: 正则
     * @param content: 被匹配的内容
     * @return java.lang.String
    */
    public static String delAll(String regex, CharSequence content) {
        if (StrUtil.isBlank(regex, content)) { return StrUtil.str(content); }
        final Pattern pattern = PatternPool.get(regex, Pattern.DOTALL);
        return delAll(pattern, content);
    }

    /**
     * [删除匹配的全部内容](Delete all matches)
     * @description: zh - 删除匹配的全部内容
     * @description: en - Delete all matches
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:42 上午
     * @param pattern: 正则
     * @param content: 被匹配的内容
     * @return java.lang.String
    */
    public static String delAll(Pattern pattern, CharSequence content) {
        return Constant.NULL == pattern || StrUtil.isBlank(content) ? StrUtil.str(content) : pattern.matcher(content).replaceAll(Constant.EMPTY);
    }

    /**
     * [删除正则匹配到的内容之前的字符 如果没有找到，则返回原文](Delete the character before the regular matching content. If it is not found, the original text will be returned)
     * @description: zh - 删除正则匹配到的内容之前的字符 如果没有找到，则返回原文
     * @description: en - Delete the character before the regular matching content. If it is not found, the original text will be returned
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:45 上午
     * @param regex: 定位正则
     * @param content: 被查找的内容
     * @return java.lang.String
    */
    public static String delPre(String regex, CharSequence content) {
        if (Constant.NULL == content || Constant.NULL == regex) { return StrUtil.str(content); }
        final Pattern pattern = PatternPool.get(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(content);
        return matcher.find() ? StrUtil.sub(content, matcher.end(), content.length()) : StrUtil.str(content);
    }

    /*匹配pattern的个数-----------------------------------------------------------count*/

    /**
     * [计算指定字符串中，匹配pattern的个数](Calculates the number of matching patterns in the specified string)
     * @description: zh - 计算指定字符串中，匹配pattern的个数
     * @description: en - Calculates the number of matching patterns in the specified string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 9:22 上午
     * @param regex: 正则表达式
     * @param content: 被查找的内容
     * @return int
    */
    public static int count(String regex, CharSequence content) {
        if (Constant.NULL == regex || Constant.NULL == content) {
            return Constant.ZERO;
        }
        final Pattern pattern = PatternPool.get(regex, Pattern.DOTALL);
        return count(pattern, content);
    }

    /**
     * [计算指定字符串中，匹配pattern的个数](Calculates the number of matching patterns in the specified string)
     * @description: zh - 计算指定字符串中，匹配pattern的个数
     * @description: en - Calculates the number of matching patterns in the specified string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 9:23 上午
     * @param pattern: 编译后的正则模式
     * @param content: 被查找的内容
     * @return int
    */
    public static int count(Pattern pattern, CharSequence content) {
        if (Constant.NULL == pattern || Constant.NULL == content) { return Constant.ZERO; }
        int count = Constant.ZERO;
        final Matcher matcher = pattern.matcher(content);
        while (matcher.find()) { count++; }
        return count;
    }

    /*指定内容中是否有表达式匹配的内容-----------------------------------------------------------contains*/

    /**
     * [指定内容中是否有表达式匹配的内容](Specifies whether there is content in the content that matches the expression)
     * @description: zh - 指定内容中是否有表达式匹配的内容
     * @description: en - Specifies whether there is content in the content that matches the expression
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 9:25 上午
     * @param regex: 正则表达式
     * @param content: 被查找的内容
     * @return boolean
    */
    public static boolean contains(String regex, CharSequence content) {
        if (Constant.NULL == regex || Constant.NULL == content) { return Constant.FALSE; }
        final Pattern pattern = PatternPool.get(regex, Pattern.DOTALL);
        return contains(pattern, content);
    }

    /**
     * [指定内容中是否有表达式匹配的内容](Specifies whether there is content in the content that matches the expression)
     * @description: zh - 指定内容中是否有表达式匹配的内容
     * @description: en - Specifies whether there is content in the content that matches the expression
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 9:26 上午
     * @param pattern: 编译后的正则模式
     * @param content: 被查找的内容
     * @return boolean
    */
    public static boolean contains(Pattern pattern, CharSequence content) {
        return Constant.NULL == pattern || Constant.NULL == content ? Constant.FALSE : pattern.matcher(content).find();
    }

    /*是否匹配-----------------------------------------------------------isMatch*/

    /**
     * [给定内容是否匹配正则](Does the given content match)
     * @description: zh - 给定内容是否匹配正则
     * @description: en - Does the given content match
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 9:29 上午
     * @param regex: 正则
     * @param content: 内容
     * @return boolean
    */
    public static boolean isMatch(String regex, CharSequence content) {
        if (content == Constant.NULL) {
            // 提供null的字符串为不匹配
            return Constant.FALSE;
        }
        if (StrUtil.isEmpty(regex)) {
            // 正则不存在则为全匹配
            return Constant.FALSE;
        }
        final Pattern pattern = PatternPool.get(regex, Pattern.DOTALL);
        return isMatch(pattern, content);
    }

    /**
     * [给定内容是否匹配正则](Does the given content match)
     * @description: zh - 给定内容是否匹配正则
     * @description: en - Does the given content match
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 9:30 上午
     * @param pattern: 模式
     * @param content: 内容
     * @return boolean
    */
    public static boolean isMatch(Pattern pattern, CharSequence content) {
        return Constant.NULL == content || Constant.NULL == pattern ? Constant.FALSE : pattern.matcher(content).matches();
    }

    /*替换-----------------------------------------------------------replace All*/

    /**
     * [正则替换指定值](Regular replacement of specified value)
     * @description: zh - 正则替换指定值
     * @description: en - Regular replacement of specified value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 9:31 上午
     * @param content: 文本
     * @param regex: 正则
     * @param replacementTemplate: 替换的文本模板
     * @return java.lang.String
    */
    public static String replaceAll(CharSequence content, String regex, String replacementTemplate) {
        final Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        return replaceAll(content, pattern, replacementTemplate);
    }

    /**
     * [正则替换指定值](Regular replacement of specified value)
     * @description: zh - 正则替换指定值
     * @description: en - Regular replacement of specified value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 9:33 上午
     * @param content: 文本
     * @param pattern: Pattern
     * @param replacementTemplate: 替换的文本模板
     * @return java.lang.String
    */
    public static String replaceAll(CharSequence content, Pattern pattern, String replacementTemplate) {
        if (StrUtil.isEmpty(content)) {
            return StrUtil.str(content);
        }
        final Matcher matcher = pattern.matcher(content);
        boolean result = matcher.find();
        if (result) {
            final Set<String> varNums = findAll(PatternPool.GROUP_VAR, replacementTemplate, Constant.ONE, new HashSet<>());
            final StringBuffer sb = new StringBuffer();
            do {
                String replacement = replacementTemplate;
                for (String var : varNums) {
                    int group = Integer.parseInt(var);
                    replacement = replacement.replace(Constant.STRING_DOLLAR + var, matcher.group(group));
                }
                matcher.appendReplacement(sb, escape(replacement));
                result = matcher.find();
            } while (result);
            matcher.appendTail(sb);
            return sb.toString();
        }
        return StrUtil.str(content);
    }

    /**
     * [替换所有正则匹配的文本](Replace all regular matched text)
     * @description: zh - 替换所有正则匹配的文本
     * @description: en - Replace all regular matched text
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 9:34 上午
     * @param str: 要替换的字符串
     * @param regex: 用于匹配的正则式
     * @param replaceFun: 决定如何替换的函数
     * @return java.lang.String
    */
    public static String replaceAll(CharSequence str, String regex, Func1<Matcher, String> replaceFun) {
        return replaceAll(str, Pattern.compile(regex), replaceFun);
    }

    /**
     * [替换所有正则匹配的文本](Replace all regular matched text)
     * @description: zh - 替换所有正则匹配的文本
     * @description: en - Replace all regular matched text
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 9:34 上午
     * @param str: 要替换的字符串
     * @param pattern: 用于匹配的正则式
     * @param replaceFun: 决定如何替换的函数
     * @return java.lang.String
    */
    public static String replaceAll(CharSequence str, Pattern pattern, Func1<Matcher, String> replaceFun) {
        if (StrUtil.isEmpty(str)) {
            return StrUtil.str(str);
        }
        final Matcher matcher = pattern.matcher(str);
        final StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(buffer, replaceFun.call(matcher));
            } catch (Exception e) {
                throw new UtilException(e);
            }
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    /*转义字符-----------------------------------------------------------escape*/

    /**
     * [转义字符](Escape character)
     * @description: zh - 转义字符
     * @description: en - Escape character
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 9:38 上午
     * @param c: 字符
     * @return java.lang.String
    */
    public static String escape(char c) {
        final StringBuilder builder = new StringBuilder();
        if (RE_KEYS.contains(c)) {
            builder.append(Constant.DOUBLE_CHAR_SLASH);
        }
        builder.append(c);
        return builder.toString();
    }

    /**
     * [转义字符串](escape sequence )
     * @description: zh - 转义字符串
     * @description: en - escape sequence
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 9:42 上午
     * @param content: 文本
     * @return java.lang.String
    */
    public static String escape(CharSequence content) {
        if (StrUtil.isBlank(content)) {
            return StrUtil.str(content);
        }
        final StringBuilder builder = new StringBuilder();
        int len = content.length();
        char current;
        for (int i = Constant.ZERO; i < len; i++) {
            current = content.charAt(i);
            if (RE_KEYS.contains(current)) {
                builder.append(Constant.DOUBLE_CHAR_SLASH);
            }
            builder.append(current);
        }
        return builder.toString();
    }

}
