package com.xiaoTools.core.io.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;

import com.xiaoTools.core.exception.iORuntimeException.IORuntimeException;
import com.xiaoTools.util.charsetUtil.CharsetUtil;
import com.xiaoTools.util.ioUtil.IoUtil;

/**
 * [资源接口定义](Resource interface definition)
 * @description zh - 资源接口定义
 * @description en - Resource interface definition
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-29 09:49:25
 */
public interface Resource {

	/**
	 * [获取资源名](Get resource name)
	 * @description zh - 获取资源名
	 * @description en - Get resource name
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:49:54
	 * @return java.lang
	 */
	String getName();

	/**
	 * [获得解析后的 URL](Get the parsed URL)
	 * @description zh - 获得解析后的 URL
	 * @description en - Get the parsed URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:50:27
	 */
	URL getUrl();

	/**
	 * [获得 InputStream](Get InputStream)
	 * @description zh - 获得 InputStream
	 * @description en - Get InputStream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:50:59
	 * @return java.io.InputStream
	 */
	InputStream getStream();

	/**
	 * [将资源内容写出到流](Write out resource content to stream)
	 * @description zh - 将资源内容写出到流
	 * @description en - Write out resource content to stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:52:09
	 * @param out 输出流
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 */
	default void writeTo(OutputStream out) throws IORuntimeException {
		try (InputStream in = getStream()) {
			IoUtil.copy(in, out);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	/**
	 * [获得Reader](Get reader)
	 * @description zh - 获得Reader
	 * @description en - Get reader
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:54:40
	 * @return java.io.BufferedReader
	 */
	default BufferedReader getReader(Charset charset) {
		return IoUtil.getReader(getStream(), charset);
	}

	/**
	 * [读取资源内容](Read resource content)
	 * @description zh - 读取资源内容
	 * @description en - Read resource content
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:56:29
	 * @param charset Charset
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return java.lang.String
	 */
	default String readStr(Charset charset) throws IORuntimeException {
		return IoUtil.read(getReader(charset));
	}

	/**
	 * [读取资源内容](Read resource content)
	 * @description zh - 读取资源内容
	 * @description en - Read resource content
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:57:19
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return java.lang.String
	 */
	default String readUtf8Str() throws IORuntimeException {
		return readStr(CharsetUtil.CHARSET_UTF_8);
	}

	/**
	 * [读取资源内容](Read resource content)
	 * @description zh - 读取资源内容
	 * @description en - Read resource content
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:57:34
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return byte[]
	 */
	default byte[] readBytes() throws IORuntimeException {
		return IoUtil.readBytes(getStream());
	}

}
