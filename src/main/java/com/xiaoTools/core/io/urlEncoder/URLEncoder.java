package com.xiaoTools.core.io.urlEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.BitSet;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.hexUtil.HexUtil;

/**
 * [URL编码](URL encoding)
 * @description zh - URL编码
 * @description en - URL encoding
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-29 19:04:19
 */
public class URLEncoder implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 默认URLEncoder
	 */
	public static final URLEncoder DEFAULT = createDefault();

	/**
	 * 用于查询语句的URLEncoder
	 */
	public static final URLEncoder QUERY = createQuery();

	/**
	 * 全编码的URLEncoder
	 */
	public static final URLEncoder ALL = createAll();

	/**
	 * [创建默认URLEncoder](Create default urlencoder)
	 * @description zh - 创建默认URLEncoder
	 * @description en - Create default urlencoder
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:05:53
	 * @return com.xiaoTools.core.io.urlEncoder.URLEncoder
	 */
	public static URLEncoder createDefault() {
		final URLEncoder encoder = new URLEncoder();
		encoder.addSafeCharacter('-');
		encoder.addSafeCharacter('.');
		encoder.addSafeCharacter('_');
		encoder.addSafeCharacter('~');
		encoder.addSafeCharacter('!');
		encoder.addSafeCharacter('$');
		encoder.addSafeCharacter('&');
		encoder.addSafeCharacter('\'');
		encoder.addSafeCharacter('(');
		encoder.addSafeCharacter(')');
		encoder.addSafeCharacter('*');
		encoder.addSafeCharacter('+');
		encoder.addSafeCharacter(',');
		encoder.addSafeCharacter(';');
		encoder.addSafeCharacter('=');
		encoder.addSafeCharacter(':');
		encoder.addSafeCharacter('@');
		encoder.addSafeCharacter('/');

		return encoder;
	}

	/**
	 * [创建用于查询语句的URLEncoder](Create urlencoders for query statements)
	 * @description zh - 创建用于查询语句的URLEncoder
	 * @description en - Create urlencoders for query statements
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:06:42
	 * @return com.xiaoTools.core.io.urlEncoder.URLEncoder
	 */
	public static URLEncoder createQuery() {
		final URLEncoder encoder = new URLEncoder();
		encoder.setEncodeSpaceAsPlus(true);
		encoder.addSafeCharacter('*');
		encoder.addSafeCharacter('-');
		encoder.addSafeCharacter('.');
		encoder.addSafeCharacter('_');
		encoder.addSafeCharacter('=');
		encoder.addSafeCharacter('&');

		return encoder;
	}

	/**
	 * [创建URLEncoder](Create urlencoder)
	 * @description zh - 创建URLEncoder
	 * @description en - Create urlencoder
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:07:28
	 * @return com.xiaoTools.core.io.urlEncoder.URLEncoder
	 */
	public static URLEncoder createAll() {
		final URLEncoder encoder = new URLEncoder();
		encoder.addSafeCharacter('*');
		encoder.addSafeCharacter('-');
		encoder.addSafeCharacter('.');
		encoder.addSafeCharacter('_');

		return encoder;
	}

	/**
	 * 存放安全编码
	 */
	private final BitSet safeCharacters;

	/**
	 * 是否编码空格为+
	 */
	private boolean encodeSpaceAsPlus = Constant.FALSE;

	public URLEncoder() {
		this(new BitSet(256));

		for (char i = 'a'; i <= 'z'; i++) {
			addSafeCharacter(i);
		}
		for (char i = 'A'; i <= 'Z'; i++) {
			addSafeCharacter(i);
		}
		for (char i = '0'; i <= '9'; i++) {
			addSafeCharacter(i);
		}
	}

	private URLEncoder(BitSet safeCharacters) {
		this.safeCharacters = safeCharacters;
	}

	/**
	 * [增加安全字符](Add security characters)
	 * @description zh - 增加安全字符
	 * @description en - Add security characters
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:09:04
	 * @param c 字符
	 */
	public void addSafeCharacter(char c) {
		safeCharacters.set(c);
	}

	/**
	 * [移除安全字符](Remove security characters)
	 * @description zh - 移除安全字符
	 * @description en - Remove security characters
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:09:40
	 * @param c 字符
	 */
	public void removeSafeCharacter(char c) {
		safeCharacters.clear(c);
	}

	/**
	 * [是否将空格编码为+](Encode spaces as+)
	 * @description zh - 是否将空格编码为+
	 * @description en - Encode spaces as+
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:10:12
	 * @param encodeSpaceAsPlus 是否将空格编码为+
	 */
	public void setEncodeSpaceAsPlus(boolean encodeSpaceAsPlus) {
		this.encodeSpaceAsPlus = encodeSpaceAsPlus;
	}

	/**
	 * [将URL中的字符串编码为%形式](Encode the string in the URL as%)
	 * @description zh - 将URL中的字符串编码为%形式
	 * @description en - Encode the string in the URL as%
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:11:27
	 * @param path 需要编码的字符串
	 * @param charset 编码
	 * @return java.lang.String
	 */
	public String encode(String path, Charset charset) {
		final StringBuilder rewrittenPath = new StringBuilder(path.length());
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		OutputStreamWriter writer = new OutputStreamWriter(buf, charset);

		int c;
		for (int i = Constant.ZERO; i < path.length(); i++) {
			c = path.charAt(i);
			if (safeCharacters.get(c)) {
				rewrittenPath.append((char) c);
			} else if (encodeSpaceAsPlus && c == Constant.CHAR_SPACE) {
				rewrittenPath.append('+');
			} else {
				try {
					writer.write((char) c);
					writer.flush();
				} catch (IOException e) {
					buf.reset();
					continue;
				}

				byte[] ba = buf.toByteArray();
				for (byte toEncode : ba) {
					rewrittenPath.append('%');
					HexUtil.appendHex(rewrittenPath, toEncode, false);
				}
				buf.reset();
			}
		}
		return rewrittenPath.toString();
	}
}
