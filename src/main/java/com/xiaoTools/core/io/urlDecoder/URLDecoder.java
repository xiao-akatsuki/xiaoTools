package com.xiaoTools.core.io.urlDecoder;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.charUtil.CharUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [URL解码](URL decoding)
 * @description zh - URL解码
 * @description en - URL decoding
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-29 20:15:38
 */
public class URLDecoder implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final byte ESCAPE_CHAR = '%';

	/**
	 * [解码](decode)
	 * @description zh - 解码
	 * @description en - decode
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 20:10:01
	 * @param value 包含URL编码后的字符串
	 * @param charset 字符集
	 * @return java.lang.String
	 */
	public static String decodeForPath(String value, Charset charset) {
		return decode(value, charset, Constant.FALSE);
	}

	/**
	 * [解码](decode)
	 * @description zh - 解码
	 * @description en - decode
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 20:11:04
	 * @param value 包含URL编码后的字符串
	 * @param charset 字符集
	 * @return java.lang.String
	 */
	public static String decode(String value, Charset charset) {
		return decode(value, charset, Constant.TRUE);
	}

	/**
	 * [解码](decode)
	 * @description zh - 解码
	 * @description en - decode
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 20:12:03
	 * @param value 包含URL编码后的字符串
	 * @param charset 是否+转换为空格
	 * @param isPlusToSpace 编码
	 * @return java.lang.String
	 */
	public static String decode(String value, Charset charset, boolean isPlusToSpace) {
		return StrUtil.string(decode(StrUtil.bytes(value, charset), isPlusToSpace), charset);
	}

	/**
	 * [解码](decode)
	 * @description zh - 解码
	 * @description en - decode
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 20:13:21
	 * @param bytes url编码的bytes
	 * @return byte[]
	 */
	public static byte[] decode(byte[] bytes) {
		return decode(bytes, true);
	}

	/**
	 * [解码](decode)
	 * @description zh - 解码
	 * @description en - decode
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 20:14:20
	 * @param bytes url编码的bytes
	 * @param isPlusToSpace 是否+转换为空格
	 * @return byte[]
	 */
	public static byte[] decode(byte[] bytes, boolean isPlusToSpace) {
		if (bytes == null) {
			return null;
		}
		final ByteArrayOutputStream buffer = new ByteArrayOutputStream(bytes.length);
		int b;
		for (int i = 0; i < bytes.length; i++) {
			b = bytes[i];
			if (b == '+') {
				buffer.write(isPlusToSpace ? Constant.CHAR_SPACE : b);
			} else if (b == ESCAPE_CHAR) {
				if (i + 1 < bytes.length) {
					final int u = CharUtil.digit16(bytes[i + 1]);
					if (u >= 0 && i + 2 < bytes.length) {
						final int l = CharUtil.digit16(bytes[i + 2]);
						if (l >= 0) {
							buffer.write((char) ((u << 4) + l));
							i += 2;
							continue;
						}
					}
				}
				buffer.write(b);
			} else {
				buffer.write(b);
			}
		}
		return buffer.toByteArray();
	}
}
