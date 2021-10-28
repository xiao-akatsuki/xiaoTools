package com.xiaoTools.util.unicodeUtil;

import com.xiaoTools.core.text.stringBuilder.StrBuilder;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.charUtil.CharUtil;
import com.xiaoTools.util.hexUtil.HexUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [提供Unicode字符串和普通字符串之间的转换](Provides conversion between Unicode strings and normal strings)
 * @description zh - 提供Unicode字符串和普通字符串之间的转换
 * @description en - Provides conversion between Unicode strings and normal strings
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-28 20:41:56
 */
public class UnicodeUtil {

	/**
	 * [Unicode字符串转为普通字符串](Convert Unicode string to normal string)
	 * @description zh - Unicode字符串转为普通字符串
	 * @description en - Convert Unicode string to normal string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:43:58
	 * @param unicode Unicode字符串
	 * @return java.lang.String
	 */
	public static String toString(String unicode) {
		if (StrUtil.isBlank(unicode)) {
			return unicode;
		}

		final int len = unicode.length();
		StrBuilder sb = StrBuilder.create(len);
		int i;
		int pos = Constant.ZERO;
		while ((i = StrUtil.indexOfIgnoreCase(unicode, "\\u", pos)) != Constant.NEGATIVE_ONE) {
			sb.append(unicode, pos, i);//写入Unicode符之前的部分
			pos = i;
			if (i + Constant.FIVE < len) {
				char c;
				try {
					c = (char) Integer.parseInt(unicode.substring(i + Constant.TWO, i + Constant.SIX), Constant.SIXTEEN);
					sb.append(c);
					pos = i + Constant.SIX;
				} catch (NumberFormatException e) {
					sb.append(unicode, pos, i + Constant.TWO);
					pos = i + Constant.TWO;
				}
			} else {
				break;
			}
		}

		if (pos < len) {
			sb.append(unicode, pos, len);
		}
		return sb.toString();
	}


	public static String toUnicode(char c) {
		return HexUtil.toUnicodeHex(c);
	}

	public static String toUnicode(int c) {
		return HexUtil.toUnicodeHex(c);
	}

	public static String toUnicode(String str) {
		return toUnicode(str, Constant.TRUE);
	}

	/**
	 * [字符串编码为Unicode形式](The string is encoded in Unicode)
	 * @description zh - 字符串编码为Unicode形式
	 * @description en - The string is encoded in Unicode
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:46:23
	 * @param str 被编码的字符串
	 * @param isSkipAscii 是否跳过ASCII字符（只跳过可见字符）
	 * @return java.lang.String
	 */
	public static String toUnicode(String str, boolean isSkipAscii) {
		if (StrUtil.isEmpty(str)) {
			return str;
		}

		final int len = str.length();
		final StrBuilder unicode = StrBuilder.create(str.length() * Constant.SIX);
		char c;
		for (int i = Constant.ZERO; i < len; i++) {
			c = str.charAt(i);
			unicode.append(isSkipAscii && CharUtil.isAsciiPrintable(c) ? c : HexUtil.toUnicodeHex(c));
		}
		return unicode.toString();
	}
}
