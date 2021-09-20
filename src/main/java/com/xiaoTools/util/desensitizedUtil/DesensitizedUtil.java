package com.xiaoTools.util.desensitizedUtil;

/**
 * [脱敏工具类](Desensitization tools)
 * @description zh - 脱敏工具类
 * @description en - Desensitization tools
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-09-20 17:50:55
 */
public class DesensitizedUtil {
    /**
     * [脱敏类型枚举](Desensitization type enumeration)
     * @description zh - 脱敏类型枚举
     * @description en - Desensitization type enumeration
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-20 17:52:09
     */
    public enum DesensitizedType {
		//用户id
		USER_ID,
		//中文名
		CHINESE_NAME,
		//身份证号
		ID_CARD,
		//座机号
		FIXED_PHONE,
		//手机号
		MOBILE_PHONE,
		//地址
		ADDRESS,
		//电子邮件
		EMAIL,
		//密码
		PASSWORD
	}
}
