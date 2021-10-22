package com.xiaoTools.util.nioUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.core.exception.iORuntimeException.IORuntimeException;
import com.xiaoTools.core.io.streamProgress.StreamProgress;

/**
 * [NIO相关工具封装](NiO related tool packaging)
 * @description zh - NIO相关工具封装
 * @description en - NiO related tool packaging
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-22 12:23:20
 */
public class NioUtil{

	/**
	 * 默认缓存大小 8192
	 */
	public static final int DEFAULT_BUFFER_SIZE = 2 << 12;
	/**
	 * 默认中等缓存大小 16384
	 */
	public static final int DEFAULT_MIDDLE_BUFFER_SIZE = 2 << 13;
	/**
	 * 默认大缓存大小 32768
	 */
	public static final int DEFAULT_LARGE_BUFFER_SIZE = 2 << 14;

	/**
	 * 数据流末尾
	 */
	public static final int EOF = -1;

	/**
	 * [拷贝流](Copy stream)
	 * @description zh - 拷贝流
	 * @description en - Copy stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 12:27:12
	 * @param in 输入流
	 * @param out 输出流
	 * @param bufferSize 缓存大小
	 * @param streamProgress 进度条
	 * @return long
	 */
	public static long copyByNIO(InputStream in, OutputStream out, int bufferSize, StreamProgress streamProgress) throws IORuntimeException {
		return copy(Channels.newChannel(in), Channels.newChannel(out), bufferSize, streamProgress);
	}

	/**
	 * [拷贝文件Channel](Copy file channel)
	 * @description zh - 拷贝文件Channel
	 * @description en - Copy file channel
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 12:41:50
	 * @param inChannel FileChannel
	 * @param outChannel FileChannel
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return long
	 */
	public static long copy(FileChannel inChannel, FileChannel outChannel) throws IORuntimeException {
		Assertion.notNull(inChannel, "In channel is null!");
		Assertion.notNull(outChannel, "Out channel is null!");

		try {
			return inChannel.transferTo(0, inChannel.size(), outChannel);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	/**
	 * [拷贝流](Copy stream)
	 * @description zh - 拷贝流
	 * @description en - Copy stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 12:43:18
	 * @param in ReadableByteChannel
	 * @param out WritableByteChannel
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return long
	 */
	public static long copy(ReadableByteChannel in, WritableByteChannel out) throws IORuntimeException {
		return copy(in, out, DEFAULT_BUFFER_SIZE);
	}

	/**
	 * [拷贝流](Copy stream)
	 * @description zh - 拷贝流
	 * @description en - Copy stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 12:45:35
	 * @param in ReadableByteChannel
	 * @param out WritableByteChannel
	 * @param bufferSize 缓冲大小
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return long
	 */
	public static long copy(ReadableByteChannel in, WritableByteChannel out, int bufferSize) throws IORuntimeException {
		return copy(in, out, bufferSize, null);
	}

	/**
	 * [拷贝流](Copy stream)
	 * @description zh - 拷贝流
	 * @description en - Copy stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 12:46:49
	 * @param in
	 * @param out
	 * @param bufferSize
	 * @param streamProgress
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return long
	 */
	public static long copy(ReadableByteChannel in, WritableByteChannel out, int bufferSize, StreamProgress streamProgress) throws IORuntimeException {
		Assertion.notNull(in, "InputStream is null !");
		Assertion.notNull(out, "OutputStream is null !");

		ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize <= 0 ? DEFAULT_BUFFER_SIZE : bufferSize);
		long size = 0;
		if (null != streamProgress) {
			streamProgress.start();
		}
		try {
			while (in.read(byteBuffer) != EOF) {
				byteBuffer.flip();
				size += out.write(byteBuffer);
				byteBuffer.clear();
				if (null != streamProgress) {
					streamProgress.progress(size);
				}
			}
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
		if (null != streamProgress) {
			streamProgress.finish();
		}

		return size;
	}
}
