package com.xiaoTools.core.regular.validation;

import com.xiaoTools.core.regular.patternPool.PatternPool;

import java.util.regex.Pattern;

/**
 * [字段验证器](Field verifier)
 * @description: zh - 字段验证器
 * @description: en - Field verifier
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/10 5:20 下午
*/
public class Validation {

    /**
     * [进行正则判断](Make regular judgment)
     * @description: zh - 进行正则判断
     * @description: en - Make regular judgment
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/10 7:06 下午
     * @param pattern: [正则语句](Regular statement)
     * @param value: [需要判断的值](Value to judge)
     * @return boolean
    */
    public static boolean isMatchRegex(Pattern pattern,CharSequence value){
        if (value == null || pattern == null) {
            // 提供null的字符串为不匹配
            return false;
        }
        return pattern.matcher(value).matches();
    }

    /**
     * [判断是否是英文字母 、数字和下划线](Judge whether it is English letters, numbers and underscores)
     * @description: zh - 判断是否是英文字母 、数字和下划线
     * @description: en - Judge whether it is English letters, numbers and underscores
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/10 5:55 下午
     * @param value: 需要判断是否是英文字母 、数字和下划线
     * @return boolean
    */
    public static boolean isGeneral(CharSequence value){
        return isMatchRegex(PatternPool.GENERAL,value);
    }

    /**
     * [判断是否是数字](Judge whether it's a number)
     * @description: zh - 判断是否是数字
     * @description: en - Judge whether it's a number
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/10 7:12 下午
     * @param value: 需要判断是否是数字
     * @return boolean
    */
    public static boolean isNumbers(CharSequence value){
        return isMatchRegex(PatternPool.NUMBERS,value);
    }

    /**
     * [判断是否是字母](Judge whether it is a letter or not)
     * @description: zh - 判断是否是字母
     * @description: en - Judge whether it is a letter or not
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/10 7:15 下午
     * @param value: 需要判断是否是字母的值
     * @return boolean
    */
    public static boolean isWord(CharSequence value){
        return isMatchRegex(PatternPool.WORD,value);
    }

    /**
     * [判断输入的地址是否是IPV4地址](Judge whether the input address is an IPv4 address)
     * @description: zh - 判断输入的地址是否是IPV4地址
     * @description: en - Judge whether the input address is an IPv4 address
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/10 7:34 下午
     * @param value: 需要判断是否是IPV4地址
     * @return boolean
    */
    public static boolean isIpv4(CharSequence value){
        return isMatchRegex(PatternPool.IPV4,value);
    }

    /**
     * [判断输入的字符串是否是IPV6地址](Determine whether the input string is an IPv6 address)
     * @description: zh - 判断输入的字符串是否是IPV6地址
     * @description: en - Determine whether the input string is an IPv6 address
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/10 7:56 下午
     * @param value: 需要判断是否IPV6地址
     * @return boolean
    */
    public static boolean isIpv6(CharSequence value){
        return isMatchRegex(PatternPool.IPV6,value);
    }

    /**
     * [判断输入的字符串是否符合货币](Determine whether the input string matches the currency)
     * @description: zh - 判断输入的字符串是否符合货币
     * @description: en - Determine whether the input string matches the currency
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/10 8:02 下午
     * @param value: 判断输入的数值是否符合金融
     * @return boolean
    */
    public static boolean isMoney(CharSequence value){
        return isMatchRegex(PatternPool.MONEY,value);
    }

    /**
     * [判断输入的字符串是否是邮箱格式](Determine whether the input string is in mailbox format)
     * @description: zh - 判断输入的字符串是否是邮箱格式
     * @description: en - Determine whether the input string is in mailbox format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/10 8:05 下午
     * @param value: 判断字符串是否是邮箱格式
     * @return boolean
    */
    public static boolean isEmail(CharSequence value){
        return isMatchRegex(PatternPool.EMAIL,value);
    }

    /**
     * [判断字符串是否是电话号码](Determine whether the string is a phone number)
     * @description: zh - 判断字符串是否是电话号码
     * @description: en - Determine whether the string is a phone number
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/10 8:09 下午
     * @param value: 需要判断字符串是否为电话
     * @return boolean
    */
    public static boolean isMobile(CharSequence value){
        return isMatchRegex(PatternPool.MOBILE, value)
               || isMatchRegex(PatternPool.MOBILE_HK, value)
               || isMatchRegex(PatternPool.TEL, value);
    }

    /**
     * [判断输入的字符串是否是18位的身份证号](Determine whether the input string is 18 digit ID number.)
     * @description: zh - 判断输入的字符串是否是18位的身份证号
     * @description: en - Determine whether the input string is 18 digit ID number.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/10 8:49 下午
     * @param value: 需要判断是否是18位身份证
     * @return boolean
    */
    public static boolean isCitizenId(CharSequence value){
        return isMatchRegex(PatternPool.CITIZEN_ID,value);
    }

    /**
     * [判断所需要的字符串是否是中国的邮编](Determine whether the required string is a Chinese zip code)
     * @description: zh - 判断所需要的字符串是否是中国的邮编
     * @description: en - Determine whether the required string is a Chinese zip code
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/10 9:49 下午
     * @param value: 需要判断是否是中国的邮编
     * @return boolean
    */
    public static boolean isPostcode(CharSequence value){
        return isMatchRegex(PatternPool.POSTCODE,value);
    }

    /**
     * [判断输入的字符串是否符合日期格式](Determine whether the input string conforms to the date format)
     * @description: zh - 判断输入的字符串是否符合日期格式
     * @description: en - Determine whether the input string conforms to the date format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/10 9:52 下午
     * @param value: 判断是否是生日的合法
     * @return boolean
    */
    public static boolean isBirthday(CharSequence value){
        return isMatchRegex(PatternPool.BIRTHDAY,value);
    }

    /**
     * [判断字符串是否是UUD](Determine whether the string is uud)
     * @description: zh - 判断字符串是否是UUD
     * @description: en - Determine whether the string is uud
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/10 9:59 下午
     * @param value: 需要判断字符串是否是UUID的格式
     * @return boolean
    */
    public static boolean isUuid(CharSequence value){
        return isMatchRegex(PatternPool.UUID,value);
    }

    /**
     * [判断字符串是否不带横线的UUID](Determine whether a string has a UUID without a horizontal line)
     * @description: zh - 判断字符串是否不带横线的UUID
     * @description: en - Determine whether a string has a UUID without a horizontal line
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/10 10:02 下午
     * @param value: 需要判断字符串是否是不带横线的UUID
     * @return boolean
    */
    public static boolean isSimpleUuid(CharSequence value){
        return isMatchRegex(PatternPool.UUID_SIMPLE,value);
    }

    /**
     * [判断字符串是否是MAC地址](Determine whether the string is a MAC address)
     * @description: zh - 判断字符串是否是MAC地址
     * @description: en - Determine whether the string is a MAC address
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/10 10:03 下午
     * @param value: mac地址
     * @return boolean
    */
    public static boolean isMac(CharSequence value){
        return isMatchRegex(PatternPool.MAC_ADDRESS,value);
    }
}
