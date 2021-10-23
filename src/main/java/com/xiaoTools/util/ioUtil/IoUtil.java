package com.xiaoTools.util.ioUtil;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.core.exception.iORuntimeException.IORuntimeException;
import com.xiaoTools.core.exception.utilException.UtilException;
import com.xiaoTools.core.io.bomInputStream.BOMInputStream;
import com.xiaoTools.core.io.fastByteArrayOutputStream.FastByteArrayOutputStream;
import com.xiaoTools.core.io.lineHandler.LineHandler;
import com.xiaoTools.core.io.streamProgress.StreamProgress;
import com.xiaoTools.core.io.validateObjectInputStream.ValidateObjectInputStream;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.charsetUtil.CharsetUtil;
import com.xiaoTools.util.fileUtil.fileUtil.FileUtil;
import com.xiaoTools.util.hexUtil.HexUtil;
import com.xiaoTools.util.nioUtil.NioUtil;
import com.xiaoTools.util.strUtil.StrUtil;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Collection;

/**
 * [Io流封装的工具类NIO](Tool class NiO encapsulated by IO stream)
 * @description: zh - Io流封装的工具类NIO
 * @description: en - Tool class NiO encapsulated by IO stream
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/5/24 1:23 下午
*/
public class IoUtil extends NioUtil {

	// 复制 ----------------------------------------- Copy

	/**
	 * [将Reader中的内容复制到Writer中使用默认缓存大小](Copy the contents of the reader to the writer using the default cache size)
	 * @description zh - 将Reader中的内容复制到Writer中使用默认缓存大小
	 * @description en - Copy the contents of the reader to the writer using the default cache size
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 21:48:02
	 * @param reader Reader
	 * @param weiter Writer
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return long
	 */
	public static long copy(Reader reader, Writer writer) throws IORuntimeException {
		return copy(reader, writer, DEFAULT_BUFFER_SIZE);
	}

	/**
	 * [将Reader中的内容复制到Writer中](Copy the contents of the reader to the writer)
	 * @description zh - 将Reader中的内容复制到Writer中
	 * @description en - Copy the contents of the reader to the writer
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 22:16:33
	 * @param reader Reader
	 * @param writer Writer
	 * @param bufferSize 缓存大小
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return long
	 */
	public static long copy(Reader reader, Writer writer, int bufferSize) throws IORuntimeException {
		return copy(reader, writer, bufferSize, null);
	}

	/**
	 * [将Reader中的内容复制到Writer中](Copy the contents of the reader to the writer)
	 * @description zh - 将Reader中的内容复制到Writer中
	 * @description en - Copy the contents of the reader to the writer
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 22:18:31
	 * @param reader Reader
	 * @param writer Writer
	 * @param bufferSize 缓存大小
	 * @param streamProgress 进度处理器
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return long
	 */
	public static long copy(Reader reader, Writer writer, int bufferSize, StreamProgress streamProgress) throws IORuntimeException {
		char[] buffer = new char[bufferSize];
		long size = 0;
		int readSize;
		if (null != streamProgress) {
			streamProgress.start();
		}
		try {
			while ((readSize = reader.read(buffer, 0, bufferSize)) != EOF) {
				writer.write(buffer, 0, readSize);
				size += readSize;
				writer.flush();
				if (null != streamProgress) {
					streamProgress.progress(size);
				}
			}
		} catch (Exception e) {
			throw new IORuntimeException(e);
		}
		if (null != streamProgress) {
			streamProgress.finish();
		}
		return size;
	}

	/**
	 * [拷贝流](Copy stream)
	 * @description zh - 拷贝流
	 * @description en - Copy stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 22:20:38
	 * @param in 输入流
	 * @param out 输出流
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return long
	 */
	public static long copy(InputStream in, OutputStream out) throws IORuntimeException {
		return copy(in, out, DEFAULT_BUFFER_SIZE);
	}

	/**
	 * [拷贝流](Copy stream)
	 * @description zh - 拷贝流
	 * @description en - Copy stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 22:21:27
	 * @param in 输入流
	 * @param out 输出流
	 * @param bufferSize 缓存大小
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return long
	 */
	public static long copy(InputStream in, OutputStream out, int bufferSize) throws IORuntimeException {
		return copy(in, out, bufferSize, null);
	}

	/**
	 * [拷贝流](Copy stream)
	 * @description zh - 拷贝流
	 * @description en - Copy stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 22:22:23
	 * @param in 输入流
	 * @param out 输出流
	 * @param bufferSize 缓存大小
	 * @param streamProgress 进度条工具
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return long
	 */
	public static long copy(InputStream in, OutputStream out, int bufferSize, StreamProgress streamProgress) throws IORuntimeException {
		Assertion.notNull(in, "InputStream is null !");
		Assertion.notNull(out, "OutputStream is null !");
		if (bufferSize <= 0) {
			bufferSize = DEFAULT_BUFFER_SIZE;
		}

		byte[] buffer = new byte[bufferSize];
		if (null != streamProgress) {
			streamProgress.start();
		}
		long size = 0;
		try {
			for (int readSize; (readSize = in.read(buffer)) != EOF; ) {
				out.write(buffer, 0, readSize);
				size += readSize;
				if (null != streamProgress) {
					streamProgress.progress(size);
				}
			}
			out.flush();
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
		if (null != streamProgress) {
			streamProgress.finish();
		}
		return size;
	}

	/**
	 * [拷贝文件流](Copy file stream)
	 * @description zh - 拷贝文件流
	 * @description en - Copy file stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 22:23:17
	 * @param in 输入
	 * @param out 输出
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return long
	 */
	public static long copy(FileInputStream in, FileOutputStream out) throws IORuntimeException {
		Assertion.notNull(in, "FileInputStream is null!");
		Assertion.notNull(out, "FileOutputStream is null!");

		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			inChannel = in.getChannel();
			outChannel = out.getChannel();
			return copy(inChannel, outChannel);
		} finally {
			close(outChannel);
			close(inChannel);
		}
	}

	// 写入和输出 ------------------------------------- getReader and getWriter

	/**
	 * [获得一个文件读取器](Get a file reader)
	 * @description zh - 获得一个文件读取器
	 * @description en - Get a file reader
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 22:25:15
	 * @param in 输入流
	 * @return java.io.BufferedReader
	 */
	public static BufferedReader getUtf8Reader(InputStream in) {
		return getReader(in, CharsetUtil.CHARSET_UTF_8);
	}

	/**
	 * [获得一个文件读取器](Get a file reader)
	 * @description zh - 获得一个文件读取器
	 * @description en - Get a file reader
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 22:28:39
	 * @param in 输入流
	 * @param charsetName 字符集名称
	 * @return java.io.BufferedReader
	 */
	public static BufferedReader getReader(InputStream in, String charsetName) {
		return getReader(in, Charset.forName(charsetName));
	}

	/**
	 * [从 BOMInputStream 中获取Reader](Get reader from bominputstream)
	 * @description zh - 从 BOMInputStream 中获取Reader
	 * @description en - Get reader from bominputstream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 22:32:16
	 * @param in BOMInputStream
	 * @return java.io.BufferedReader
	 */
	public static BufferedReader getReader(BOMInputStream in) {
		return getReader(in, in.getCharset());
	}

	/**
	 * [获得一个Reader](Get a reader)
	 * @description zh - 获得一个Reader
	 * @description en - Get a reader
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 22:35:25
	 * @param in 输入流
	 * @param charset 字符集
	 * @return java.io.BufferedReader
	 */
	public static BufferedReader getReader(InputStream in, Charset charset) {
		return null == in ? null :
			new BufferedReader(null == charset ? new InputStreamReader(in) :
				new InputStreamReader(in, charset));
	}

    /**
     * [将InputStream转为BufferedReader用于读取字符流](Converting InputStream to BufferedReader for reading character stream)
     * @description: zh - 将InputStream转为BufferedReader用于读取字符流
     * @description: en - Converting InputStream to BufferedReader for reading character stream
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/26 2:29 下午
     * @param in: InputStream
     * @return java.io.BufferedReader
    */
    public static BufferedReader getReader(InputStream in){
        return in == Constant.NULL ? Constant.BUFFERED_READER_NULL : new BufferedReader(new InputStreamReader(in));
    }


    /**
     * [将Reader转为BufferedReader用于读取字符流](Convert reader to BufferedReader to read character stream)
     * @description: zh - 将Reader转为BufferedReader用于读取字符流
     * @description: en - Convert reader to BufferedReader to read character stream
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/26 2:35 下午
     * @param reader: [以字符为单位的输入流的公共父类](The common parent of the input stream in character units)
     * @return java.io.BufferedReader
    */
    public static BufferedReader getReader(Reader reader){
        return Constant.NULL == reader ? Constant.BUFFERED_READER_NULL : reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader);
    }

	/**
	 * [获得 PushbackReader](Get pushbackreader)
	 * @description zh - 获得 PushbackReader
	 * @description en - Get pushbackreader
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 22:38:54
	 * @param reader Reader
	 * @param pushBackSize 推后的byte数
	 * @return java.io.PushbackReader
	 */
	public static PushbackReader getPushBackReader(Reader reader, int pushBackSize) {
		return (reader instanceof PushbackReader) ? (PushbackReader) reader : new PushbackReader(reader, pushBackSize);
	}

	/**
	 * [获得一个Writer](Get a writer)
	 * @description zh - 获得一个Writer
	 * @description en - Get a writer
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 22:40:14
	 * @param out 输出流
	 * @return java.io.OutputStreamWriter
	 */
	public static OutputStreamWriter getUtf8Writer(OutputStream out) {
		return getWriter(out, CharsetUtil.CHARSET_UTF_8);
	}

	/**
	 * [获得一个Writer](Get a writer)
	 * @description zh - 获得一个Writer
	 * @description en - Get a writer
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 22:42:28
	 * @param out 输出流
	 * @param charsetName 字符集名称
	 * @return java.io.OutputStreamWriter
	 */
	public static OutputStreamWriter getWriter(OutputStream out, String charsetName) {
		return getWriter(out, Charset.forName(charsetName));
	}

	/**
	 * [获得一个Writer](Get a writer)
	 * @description zh - 获得一个Writer
	 * @description en - Get a writer
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-22 22:46:05
	 * @param out 输入流
	 * @param charset 字符集
	 * @return java.io.OutputStreamWriter
	 */
	public static OutputStreamWriter getWriter(OutputStream out, Charset charset) {
		return null == out ? null :
			null == charset ? new OutputStreamWriter(out) :
				new OutputStreamWriter(out, charset);

	}

	// 写入 ------------------------------------- reader

	/**
	 * [从流中读取UTF8编码的内容](Read utf8 encoded content from stream)
	 * @description zh - 从流中读取UTF8编码的内容
	 * @description en - Read utf8 encoded content from stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 10:21:47
	 * @param in 输入流
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return java.lang.String
	 */
	public static String readUtf8(InputStream in) throws IORuntimeException {
		return read(in, CharsetUtil.CHARSET_UTF_8);
	}

	/**
	 * [从流中读取内容](Read content from stream)
	 * @description zh - 从流中读取内容
	 * @description en - Read content from stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 10:28:45
	 * @param in 输入流
	 * @param charset 字符集
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return java.lang.String
	 */
	public static String read(InputStream in, Charset charset) throws IORuntimeException {
		return StrUtil.str(readBytes(in), charset);
	}

	/**
	 * [从流中读取内容](Read content from stream)
	 * @description zh - 从流中读取内容
	 * @description en - Read content from stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 10:24:56
	 * @param in 输入流
	 * @param charsetName 字符集
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return java.lang.String
	 */
	public static String read(InputStream in, String charsetName) throws IORuntimeException {
		final FastByteArrayOutputStream out = read(in);
		return StrUtil.isBlank(charsetName) ? out.toString() : out.toString(charsetName);
	}

	/**
	 * [从流中读取内容](Read content from stream)
	 * @description zh - 从流中读取内容
	 * @description en - Read content from stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 10:29:46
	 * @param in 输入流
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return com.xiaoTools.core.io.fastByteArrayOutputStream.FastByteArrayOutputStream
	 */
	public static FastByteArrayOutputStream read(InputStream in) throws IORuntimeException {
		return read(in, Constant.TRUE);
	}

	/**
	 * [从流中读取内容](Read content from stream)
	 * @description zh - 从流中读取内容
	 * @description en - Read content from stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 10:31:48
	 * @param in 输入流
	 * @param isClose 是否关闭
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return com.xiaoTools.core.io.fastByteArrayOutputStream.FastByteArrayOutputStream
	 */
	public static FastByteArrayOutputStream read(InputStream in, boolean isClose) throws IORuntimeException {
		final FastByteArrayOutputStream out;
		if(in instanceof FileInputStream){
			// 文件流的长度是可预见的，此时直接读取效率更高
			try {
				out = new FastByteArrayOutputStream(in.available());
			} catch (IOException e) {
				throw new IORuntimeException(e);
			}
		} else{
			out = new FastByteArrayOutputStream();
		}
		try {
			copy(in, out);
		} finally {
			if (isClose) {
				close(in);
			}
		}
		return out;
	}

	/**
	 * [从Reader中读取String](Read string from reader)
	 * @description zh - 从Reader中读取String
	 * @description en - Read string from reader
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 10:33:49
	 * @param reader Reader
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return java.lang.String
	 */
	public static String read(Reader reader) throws IORuntimeException {
		return read(reader, true);
	}

	/**
	 * [从 Reader 中读取String](Read string from reader)
	 * @description zh - 从 Reader 中读取String
	 * @description en - Read string from reader
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 10:36:33
	 * @param reader Reader
	 * @param isClose 是否关闭Reader
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return java.lang.String
	 */
	public static String read(Reader reader, boolean isClose) throws IORuntimeException {
		final StringBuilder builder = StrUtil.builder();
		final CharBuffer buffer = CharBuffer.allocate(DEFAULT_BUFFER_SIZE);
		try {
			while (Constant.NEGATIVE_ONE != reader.read(buffer)) {
				builder.append(buffer.flip().toString());
			}
		} catch (IOException e) {
			throw new IORuntimeException(e);
		} finally {
			if (isClose) {
				IoUtil.close(reader);
			}
		}
		return builder.toString();
	}

	/**
	 * [从流中读取bytes](Read bytes from stream)
	 * @description zh - 从流中读取bytes
	 * @description en - Read bytes from stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 10:38:55
	 * @param in InputStream
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return byte[]
	 */
	public static byte[] readBytes(InputStream in) throws IORuntimeException {
		return readBytes(in, true);
	}

	/**
	 * [从流中读取bytes](Read bytes from stream)
	 * @description zh - 从流中读取bytes
	 * @description en - Read bytes from stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 10:40:38
	 * @param in InputStream
	 * @param isClose 是否关闭输入流
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return byte[]
	 */
	public static byte[] readBytes(InputStream in, boolean isClose) throws IORuntimeException {
		if (in instanceof FileInputStream) {
			final byte[] result;
			try {
				final int available = in.available();
				result = new byte[available];
				final int readLength = in.read(result);
				if (readLength != available) {
					throw new IOException(StrUtil.format("File length is [{}] but read [{}]!", available, readLength));
				}
			} catch (IOException e) {
				throw new IORuntimeException(e);
			} finally {
				if (isClose) {
					close(in);
				}
			}
			return result;
		}
		return read(in, isClose).toByteArray();
	}

	/**
	 * [从流中读取bytes](Read bytes from stream)
	 * @description zh - 从流中读取bytes
	 * @description en - Read bytes from stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 10:42:10
	 * @param in InputStream
	 * @param length 长度
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return byte[]
	 */
	public static byte[] readBytes(InputStream in, int length) throws IORuntimeException {
		if (null == in) {
			return null;
		}
		if (length <= Constant.ZERO) {
			return new byte[Constant.ZERO];
		}

		byte[] b = new byte[length];
		int readLength;
		try {
			readLength = in.read(b);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
		if (readLength > Constant.ZERO && readLength < length) {
			byte[] b2 = new byte[readLength];
			System.arraycopy(b, Constant.ZERO, b2, Constant.ZERO, readLength);
			return b2;
		} else {
			return b;
		}
	}

	/**
	 * [读取16进制字符串](Read hexadecimal string)
	 * @description zh - 读取16进制字符串
	 * @description en - Read hexadecimal string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 10:43:47
	 * @param in InputStream
	 * @param length 长度
	 * @param toLowerCase true 传换成小写格式 ， false 传换成大写格式
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return java.lang.String
	 */
	public static String readHex(InputStream in, int length, boolean toLowerCase) throws IORuntimeException {
		return HexUtil.encodeHexStr(readBytes(in, length), toLowerCase);
	}

	/**
	 * [从流中读取前28个byte并转换为16进制，字母部分使用大写](The first 28 bytes are read from the stream and converted to hexadecimal, and the letter part is capitalized)
	 * @description zh - 从流中读取前28个byte并转换为16进制，字母部分使用大写
	 * @description en - The first 28 bytes are read from the stream and converted to hexadecimal, and the letter part is capitalized
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 10:46:50
	 * @param in InputStream
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return java.lang.String
	 */
	public static String readHex28Upper(InputStream in) throws IORuntimeException {
		return readHex(in, Constant.TWENTY_EIGHT, Constant.FALSE);
	}

	/**
	 * [从流中读取前28个byte并转换为16进制，字母部分使用小写](Read the first 28 bytes from the stream and convert them to hexadecimal, and the letter part uses lowercase)
	 * @description zh - 从流中读取前28个byte并转换为16进制，字母部分使用小写
	 * @description en - Read the first 28 bytes from the stream and convert them to hexadecimal, and the letter part uses lowercase
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 10:49:45
	 * @param in InputStream
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return java.lang.String
	 */
	public static String readHex28Lower(InputStream in) throws IORuntimeException {
		return readHex(in, Constant.TWENTY_EIGHT, Constant.TRUE);
	}

	/**
	 * [从流中读取对象](Read object from stream)
	 * @description zh - 从流中读取对象
	 * @description en - Read object from stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 17:23:49
	 * @param in 输入流
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return T
	 */
	public static <T> T readObj(InputStream in) throws IORuntimeException, UtilException {
		return readObj(in, null);
	}

	/**
	 * [从流中读取对象](Read object from stream)
	 * @description zh - 从流中读取对象
	 * @description en - Read object from stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 17:27:22
	 * @param in 输入流
	 * @param clazz 类型
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return T
	 */
	public static <T> T readObj(InputStream in, Class<T> clazz) throws IORuntimeException, UtilException {
		try {
			return readObj((in instanceof ValidateObjectInputStream) ?
							(ValidateObjectInputStream) in : new ValidateObjectInputStream(in),
					clazz);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	/**
	 * [从流中读取对象](Read object from stream)
	 * @description zh - 从流中读取对象
	 * @description en - Read object from stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 17:28:25
	 * @param in ValidateObjectInputStream
	 * @param clazz 类型
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return T
	 */
	public static <T> T readObj(ValidateObjectInputStream in, Class<T> clazz) throws IORuntimeException, UtilException {
		if (in == null) {
			throw new IllegalArgumentException("The InputStream must not be null");
		}
		try {
			return (T) in.readObject();
		} catch (IOException e) {
			throw new IORuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new UtilException(e);
		}
	}

	/**
	 * [从流中读取内容](Read content from stream)
	 * @description zh - 从流中读取内容
	 * @description en - Read content from stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 17:39:56
	 * @param in 输入流
	 * @param collection 返回集合
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return T
	 */
	public static <T extends Collection<String>> T readUtf8Lines(InputStream in, T collection) throws IORuntimeException {
		return readLines(in, CharsetUtil.CHARSET_UTF_8, collection);
	}

	/**
	 * [从流中读取内容](Read content from stream)
	 * @description zh - 从流中读取内容
	 * @description en - Read content from stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 17:45:51
	 * @param in 输入流
	 * @param charsetName 字符集名称
	 * @param cllection 返回集合
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return T
	 */
	public static <T extends Collection<String>> T readLines(InputStream in, String charsetName, T collection) throws IORuntimeException {
		return readLines(in, CharsetUtil.charset(charsetName), collection);
	}

	/**
	 * [从流中读取内容](Read content from stream)
	 * @description zh - 从流中读取内容
	 * @description en - Read content from stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 17:47:01
	 * @param in 输入流
	 * @param charsetName 字符集名称
	 * @param cllection 返回集合
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return T
	 */
	public static <T extends Collection<String>> T readLines(InputStream in, Charset charset, T collection) throws IORuntimeException {
		return readLines(getReader(in, charset), collection);
	}

	/**
	 * [从流中读取内容](Read content from stream)
	 * @description zh - 从流中读取内容
	 * @description en - Read content from stream
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 17:48:25
	 * @param reader Reader
	 * @param collection 返回集合
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return T
	 */
	public static <T extends Collection<String>> T readLines(Reader reader, final T collection) throws IORuntimeException {
		readLines(reader, (LineHandler) collection::add);
		return collection;
	}

	/**
	 * [按行读取UTF-8编码数据](Read UTF-8 encoded data by line)
	 * @description zh - 按行读取UTF-8编码数据
	 * @description en - Read UTF-8 encoded data by line
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 17:50:57
	 * @param in InputStream
	 * @param lineHandler LineHandler
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 */
	public static void readUtf8Lines(InputStream in, LineHandler lineHandler) throws IORuntimeException {
		readLines(in, CharsetUtil.CHARSET_UTF_8, lineHandler);
	}

	/**
	 * [按行读取数据，针对每行的数据做处理](Read data by row and process the data of each row)
	 * @description zh - 按行读取数据，针对每行的数据做处理
	 * @description en - Read data by row and process the data of each row
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 17:51:52
	 * @param in InputStream
	 * @param charset Charset
	 * @param lineHandler LineHandler
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 */
	public static void readLines(InputStream in, Charset charset, LineHandler lineHandler) throws IORuntimeException {
		readLines(getReader(in, charset), lineHandler);
	}

	/**
	 * [按行读取数据，针对每行的数据做处理](Read data by row and process the data of each row)
	 * @description zh - 按行读取数据，针对每行的数据做处理
	 * @description en - Read data by row and process the data of each row
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-23 17:52:53
	 * @param reader InputStream
	 * @param lineHandler LineHandler
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 */
	public static void readLines(Reader reader, LineHandler lineHandler) throws IORuntimeException {
		Assertion.notNull(reader);
		Assertion.notNull(lineHandler);

		// 从返回的内容中读取所需内容
		final BufferedReader bReader = getReader(reader);
		String line;
		try {
			while ((line = bReader.readLine()) != null) {
				lineHandler.handle(line);
			}
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

    /**
     * [将需要写入的文本通过字符编码写入所需要的文件](Write the text to be written to the required file by character encoding)
     * @description: zh - 将需要写入的文本通过字符编码写入所需要的文件
     * @description: en - Write the text to be written to the required file by character encoding
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/25 9:44 上午
     * @param value: [需要写入的数据](Data to be written)
     * @param path: [写入的文件](Files writteCharacter set encodingn to)
     * @param charsetName: [字符集编码](Character set encoding)
    */
    public static void write(String value,String path,String charsetName){
        if (FileUtil.isFile(path)) {
            writeData(value, path, charsetName);
        }
    }

    /**
     * [将写入的文本使用UTF-8写入到文件中](Write the written text to a file using UTF-8)
     * @description: zh - 将写入的文本使用UTF-8写入到文件中
     * @description: en - Write the written text to a file using UTF-8
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/25 9:50 上午
     * @param value: [所需要写入的文本](The text to be written)
     * @param path: [文件的路径](The path to the file)
    */
    public static void writeUTF8(String value,String path){
        write(value,path, Constant.UTF_8);
    }

    /**
     * [将写入的文本使用GBK写入到文件中](Write the written text to a file using GBK)
     * @description: zh - 将写入的文本使用GBK写入到文件中
     * @description: en - Write the written text to a file using GBK
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/25 9:57 上午
     * @param value: [所需要写入的文本](The text to be written)
     * @param path: [文件的路径](The path to the file)
    */
    public static void writeGBK(String value,String path){
        write(value,path,Constant.GBK);
    }

    /**
     *
     * @description: zh - 通过读取输入流和输入自定义的字符编码级进行读取文件
     * @description: en - Read the file by reading the input stream and entering the custom character encoding level
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/25 9:00 上午
     * @param path: [需要读取的文件的绝对路径](The absolute path of the file to be read)
     * @param charsetName: [所需要读取的字符集编码](The character set code to be read)
     * @return java.lang.String
    */
    public static String getContent(String path,String charsetName){
        //判断是否是文件
        if (!FileUtil.isFile(path)) { return Constant.STRING_NULL; }
        try {
            return getContent(new FileInputStream(path), charsetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Constant.STRING_NULL;
    }

    /**
     * [输入需要读取的文件的绝对路径，使用GBK的方式进行内容的读取](Input the absolute path of the file to be read, and use GBK to read the content)
     * @description: zh - 输入需要读取的文件的绝对路径，使用GBK的方式进行内容的读取
     * @description: en - Input the absolute path of the file to be read, and use GBK to read the content
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/25 8:52 上午
     * @param path: [需要读取的文件的绝对路径](The absolute path of the file to be read)
     * @return java.lang.String
    */
    public static String getGBKContent(String path){
        return getContent(path,Constant.GBK);
    }

    /**
     * [输入需要读取的文件的绝对路径，使用UTF-8的方式进行内容的读取](Input the absolute path of the file to be read, and use UTF-8 to read the content)
     * @description: zh - 输入需要读取的文件的绝对路径，使用UTF-8的方式进行内容的读取
     * @description: en - Input the absolute path of the file to be read, and use UTF-8 to read the content
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/25 8:51 上午
     * @param path: [需要读取的文件的绝对路径](The absolute path of the file to be read)
     * @return java.lang.String
    */
    public static String getUTF8Content(String path){
        return getContent(path,Constant.UTF_8);
    }

    /**
     * [将文件输入流，通过指定的字符集进行读取。](Input the file into the stream and read it through the specified character set.)
     * @description: zh - 将文件输入流，通过指定的字符集进行读取。
     * @description: en - Input the file into the stream and read it through the specified character set.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/25 8:36 上午
     * @param value: 文件的输入流
     * @param charsetName: 所需要的读取的字符级
     * @return java.lang.String
    */
    private static String getContent(InputStream value, String charsetName) {
        StringBuilder test = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(value, charsetName))) {
            String str;
            while ((str = reader.readLine()) != Constant.NULL) {
                test.append(str).append(Constant.ENTER);
            }
        } catch (Exception e) {
            test.delete(Constant.ZERO, test.length());
        }
        return test.toString();
    }

    /**
     * [输入所需要的内容将该内容写入文件的地址，使用所指定的字符集编码](Enter the desired content, write it to the address of the file, and encode it with the specified character set)
     * @description: zh - 输入所需要的内容将该内容写入文件的地址，使用所指定的字符集编码
     * @description: en - Enter the desired content, write it to the address of the file, and encode it with the specified character set
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/25 9:27 上午
     * @param value: [所需要写入的数据](Data to be written)
     * @param filePath: [文件的地址](The address of the file)
     * @param charsetName: [字符集编码](Character set encoding)
    */
    private static void writeData(String value,String filePath,String charsetName){
        BufferedWriter bufferedWriter = Constant.BUFFERED_WRITER_NULL;
        try {
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(filePath), charsetName));
            bufferedWriter.write(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != Constant.BUFFERED_WRITER_NULL) {
                    bufferedWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
