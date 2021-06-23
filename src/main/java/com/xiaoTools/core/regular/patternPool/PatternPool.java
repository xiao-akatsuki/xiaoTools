package com.xiaoTools.core.regular.patternPool;

import com.xiaoTools.cache.simple.SimpleCache;

import java.util.regex.Pattern;

/**
 * [常用正则表达式集合](Collection of common regular expressions)
 * @description: zh - 常用正则表达式集合
 * @description: en - Collection of common regular expressions
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/8 3:53 下午
*/
public class PatternPool {
    /**
     * 英文字母 、数字和下划线
     */
    public final static Pattern GENERAL = Pattern.compile("^\\w+$");
    /**
     * 数字
     */
    public final static Pattern NUMBERS = Pattern.compile("\\d+");
    /**
     * 字母
     */
    public final static Pattern WORD = Pattern.compile("[a-zA-Z]+");

    /**
     * 分组
     */
    public final static Pattern GROUP_VAR = Pattern.compile("\\$(\\d+)");

    /**
     * IP v4
     */
    public final static Pattern IPV4 = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
    /**
     * IP v6
     */
    public final static Pattern IPV6 = Pattern.compile("(([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]+|::(ffff(:0{1,4})?:)?((25[0-5]|(2[0-4]|1?[0-9])?[0-9])\\.){3}(25[0-5]|(2[0-4]|1?[0-9])?[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1?[0-9])?[0-9])\\.){3}(25[0-5]|(2[0-4]|1?[0-9])?[0-9]))");
    /**
     * 货币
     */
    public final static Pattern MONEY = Pattern.compile("^(\\d+(?:\\.\\d+)?)$");
    /**
     * 邮件，符合RFC 5322规范
     */
    public final static Pattern EMAIL = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])", Pattern.CASE_INSENSITIVE);
    /**
     * 移动电话
     */
    public final static Pattern MOBILE = Pattern.compile("(?:0|86|\\+86)?1[3-9]\\d{9}");
    /**
     * 中国香港移动电话
     * eg: 中国香港： +852 5100 4810， 三位区域码+10位数字, 中国香港手机号码8位数
     * eg: 中国大陆： +86  180 4953 1399，2位区域码标示+13位数字
     * 中国大陆 +86 Mainland China
     * 中国香港 +852 Hong Kong
     * 中国澳门 +853 Macau
     * 中国台湾 +886 Taiwan
     */
    public final static Pattern MOBILE_HK = Pattern.compile("(?:0|852|\\+852)?\\d{8}");

    /**
     * 座机号码
     */
    public final static Pattern TEL = Pattern.compile("0\\d{2,3}-[1-9]\\d{6,7}");

    /**
     * 18位身份证号码
     */
    public final static Pattern CITIZEN_ID = Pattern.compile("[1-9]\\d{5}[1-2]\\d{3}((0\\d)|(1[0-2]))(([012]\\d)|3[0-1])\\d{3}(\\d|X|x)");
    /**
     * 邮编，兼容港澳台
     */
    public final static Pattern POSTCODE = Pattern.compile("^(0[1-7]|1[0-356]|2[0-7]|3[0-6]|4[0-7]|5[0-7]|6[0-7]|7[0-5]|8[0-9]|9[0-8])\\d{4}|99907[78]$");
    /**
     * 生日
     */
    public final static Pattern BIRTHDAY = Pattern.compile("^(\\d{2,4})([/\\-.年]?)(\\d{1,2})([/\\-.月]?)(\\d{1,2})日?$");

    /**
     * 中文字、英文字母、数字和下划线
     */
    public final static Pattern GENERAL_WITH_CHINESE = Pattern.compile("^[\u4E00-\u9FFF\\w]+$");
    /**
     * UUID
     */
    public final static Pattern UUID = Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", Pattern.CASE_INSENSITIVE);
    /**
     * 不带横线的UUID
     */
    public final static Pattern UUID_SIMPLE = Pattern.compile("^[0-9a-f]{32}$", Pattern.CASE_INSENSITIVE);
    /**
     * MAC地址正则
     */
    public static final Pattern MAC_ADDRESS = Pattern.compile("((?:[A-F0-9]{1,2}[:-]){5}[A-F0-9]{1,2})|(?:0x)(\\d{12})(?:.+ETHER)", Pattern.CASE_INSENSITIVE);
    /**
     * 16进制字符串
     */
    public static final Pattern HEX = Pattern.compile("^[a-f0-9]+$", Pattern.CASE_INSENSITIVE);

    /**
     * 中国车牌号码（兼容新能源车牌）
     */
    public final static Pattern PLATE_NUMBER = Pattern.compile(
            "^(([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z](([0-9]{5}[ABCDEFGHJK])|([ABCDEFGHJK]([A-HJ-NP-Z0-9])[0-9]{4})))|" +
                    "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领]\\d{3}\\d{1,3}[领])|" +
                    "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z][A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳使领]))$");

    /**
     * 标准日期时间正则
    */
    public final static Pattern REGEX_NORM = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}(\\s\\d{1,2}:\\d{1,2}(:\\d{1,2})?)?(.\\d{1,3})?");

    /**
     * 时间正则
     */
    public static final Pattern TIME = Pattern.compile("\\d{1,2}:\\d{1,2}(:\\d{1,2})?");

    /*方法-----------------------------------------------------------Method*/

    /**
     * [Pattern池](Pattern pool)
     */
    private static final SimpleCache<RegexWithFlag, Pattern> POOL = new SimpleCache<>();

    /**
     * [先从Pattern池中查找正则对应的Pattern，找不到则编译正则表达式并入池](First, find the pattern corresponding to the regular expression from the pattern pool. If not, compile the regular expression into the pool)
     * @description: zh - 先从Pattern池中查找正则对应的Pattern，找不到则编译正则表达式并入池
     * @description: en - First, find the pattern corresponding to the regular expression from the pattern pool. If not, compile the regular expression into the pool
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:01 下午
     * @param regex: 正则表达式
     * @return java.util.regex.Pattern
    */
    public static Pattern get(String regex) {
        return get(regex, 0);
    }

    /**
     * [先从Pattern池中查找正则对应的Pattern，找不到则编译正则表达式并入池。](First, find the pattern corresponding to the regular expression from the pattern pool. If not, compile the regular expression into the pool.)
     * @description: zh - 先从Pattern池中查找正则对应的Pattern，找不到则编译正则表达式并入池。
     * @description: en - First, find the pattern corresponding to the regular expression from the pattern pool. If not, compile the regular expression into the pool.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:04 下午
     * @param regex: 正则表达式
     * @param flags: 正则标识位集合 Pattern
     * @return java.util.regex.Pattern
    */
    public static Pattern get(String regex, int flags) {
        final RegexWithFlag regexWithFlag = new RegexWithFlag(regex, flags);

        Pattern pattern = POOL.get(regexWithFlag);
        if (null == pattern) {
            pattern = Pattern.compile(regex, flags);
            POOL.put(regexWithFlag, pattern);
        }
        return pattern;
    }

    /**
     * [移除缓存](remove cache)
     * @description: zh - 移除缓存
     * @description: en - remove cache
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:10 下午
     * @param regex: 正则
     * @param flags: 标识
     * @return java.util.regex.Pattern
    */
    public static Pattern remove(String regex, int flags) {
        return POOL.remove(new RegexWithFlag(regex, flags));
    }

    /**
     * [清空缓存池](Clear cache pool)
     * @description: zh - 清空缓存池
     * @description: en - Clear cache pool
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:11 下午
    */
    public static void clear() {
        POOL.clear();
    }

    /**
     * [正则表达式和正则标识位的包装](Packaging of regular expressions and regular identifier bits)
     * @description: zh - 正则表达式和正则标识位的包装
     * @description: en - Packaging of regular expressions and regular identifier bits
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:06 下午
    */
    private static class RegexWithFlag {
        private final String regex;
        private final int flag;

        /**
         * [改造函数](Transformation function)
         * @description: zh - 改造函数
         * @description: en - Transformation function
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/8 4:31 下午
         * @param regex: [正则](regular)
         * @param flag: [标识](identification)
        */
        public RegexWithFlag(String regex, int flag) {
            this.regex = regex;
            this.flag = flag;
        }

        /**
         * [重写 hashCode](Rewrite hashcode)
         * @description: zh - 重写 hashCode
         * @description: en - Rewrite hashcode
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/8 5:53 下午
         * @return int
        */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + flag;
            result = prime * result + ((regex == null) ? 0 : regex.hashCode());
            return result;
        }

        /**
         * [重写equals方法](Override the equals method)
         * @description: zh - 重写equals方法
         * @description: en - Override the equals method
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/8 5:54 下午
         * @param obj: [对比的对象](Objects of comparison)
         * @return boolean
        */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) { return true; }
            if (obj == null) { return false; }
            if (getClass() != obj.getClass()) { return false; }
            RegexWithFlag other = (RegexWithFlag) obj;
            if (flag != other.flag) { return false; }
            if (regex == null) {
                return other.regex == null;
            } else {
                return regex.equals(other.regex);
            }
        }

    }
}
