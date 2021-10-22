package com.xiaoTools.core.io.fastByteArrayOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.xiaoTools.core.exception.iORuntimeException.IORuntimeException;
import com.xiaoTools.core.io.fastByteBuffer.FastByteBuffer;
import com.xiaoTools.util.charsetUtil.CharsetUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;

/**
 * [基于快速缓冲FastByteBuffer的OutputStream](OutputStream based on fastbytebuffer)
 * @description zh - 基于快速缓冲FastByteBuffer的OutputStream
 * @description en - OutputStream based on fastbytebuffer
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-22 12:35:17
 */
public class FastByteArrayOutputStream extends OutputStream {

	private final FastByteBuffer buffer;

	public FastByteArrayOutputStream() {
		this(1024);
	}

	public FastByteArrayOutputStream(int size) {
		buffer = new FastByteBuffer(size);
	}

	@Override
	public void write(byte[] b, int off, int len) {
		buffer.append(b, off, len);
	}

	@Override
	public void write(int b) {
		buffer.append((byte) b);
	}

	public int size() {
		return buffer.size();
	}

	@Override
	public void close() {
		// nop
	}

	public void reset() {
		buffer.reset();
	}

	/**
	 * [写出](Write)
	 * @description zh - 写出
	 * @description en - Write
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 12:32:17
	 * @param out 输出流
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 */
	public void writeTo(OutputStream out) throws IORuntimeException {
		final int index = buffer.index();
		byte[] buf;
		try {
			for (int i = 0; i < index; i++) {
				buf = buffer.array(i);
				out.write(buf);
			}
			out.write(buffer.array(index), 0, buffer.offset());
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	/**
	 * [转为Byte数组](Convert to byte array)
	 * @description zh - 转为Byte数组
	 * @description en - Convert to byte array
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 12:33:03
	 * @return byte[]
	 */
	public byte[] toByteArray() {
		return buffer.toArray();
	}

	@Override
	public String toString() {
		return toString(CharsetUtil.defaultCharset());
	}

	/**
	 * [转为字符串](Convert to string)
	 * @description zh - 转为字符串
	 * @description en - Convert to string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 12:33:29
	 * @param charsetName 编码
	 * @return java.lang.String
	 */
	public String toString(String charsetName) {
		return toString(CharsetUtil.charset(charsetName));
	}

	/**
	 * [转为字符串](Convert to string)
	 * @description zh - 转为字符串
	 * @description en - Convert to string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 12:34:14
	 * @param charset 编码
	 * @return java.lang.String
	 */
	public String toString(Charset charset) {
		return new String(toByteArray(),
				ObjectUtil.defaultIfNull(charset, CharsetUtil.defaultCharset()));
	}
}
