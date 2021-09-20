package com.xiaoTools.util.desensitizedUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.strUtil.StrUtil;

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
		//电子邮件
		EMAIL,
		//密码
		PASSWORD
	}

    /**
     * [脱敏](Desensitization)
     * @description zh - 脱敏
     * @description en - Desensitization
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-20 17:56:14
     */
    public static String desensitized(CharSequence str, DesensitizedUtil.DesensitizedType desensitizedType) {
		if (StrUtil.isBlank(str)) {
			return Constant.EMPTY;
		}
		String newStr = String.valueOf(str);
		switch (desensitizedType) {
			case USER_ID:
				newStr = String.valueOf(DesensitizedUtil.userId());
				break;
			case EMAIL:
				newStr = DesensitizedUtil.email(String.valueOf(str));
				break;
			case PASSWORD:
				newStr = DesensitizedUtil.password(String.valueOf(str));
				break;
			default:
		}
		return newStr;
	}

    /**
     * [不对外提供userId](Do not provide userid externally)
     * @description zh - 不对外提供userId
     * @description en - Do not provide userid externally
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-20 17:58:37
     * @return java.lang.Long
     */
    public static Long userId() {
		return 0L;
	}


}
