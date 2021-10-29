package com.xiaoTools.core.io.fileWriter;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import com.xiaoTools.core.exception.iORuntimeException.IORuntimeException;
import com.xiaoTools.core.io.fileWrapper.FileWrapper;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.charsetUtil.CharsetUtil;
import com.xiaoTools.util.fileUtil.fileUtil.FileUtil;
import com.xiaoTools.util.ioUtil.IoUtil;

public class FileWriter extends FileWrapper {

	private static final long serialVersionUID = 1L;

	public FileWriter(File file, Charset charset) {
		super(file, charset);
		checkFile();
	}

	public FileWriter(File file, String charset) {
		this(file, CharsetUtil.charset(charset));
	}

	public FileWriter(String filePath, Charset charset) {
		this(FileUtil.file(filePath), charset);
	}

	public FileWriter(String filePath, String charset) {
		this(FileUtil.file(filePath), CharsetUtil.charset(charset));
	}

	public FileWriter(File file) {
		this(file, DEFAULT_CHARSET);
	}

	public FileWriter(String filePath) {
		this(filePath, DEFAULT_CHARSET);
	}

	/**
	 * [创建 FileWriter](Create filewriter)
	 * @description zh - 创建 FileWriter
	 * @description en - Create filewriter
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 21:13:20
	 * @param file 文件
	 * @param charset 编码
	 * @return com.xiaoTools.core.io.fileWriter.FileWriter
	 */
	public static FileWriter create(File file, Charset charset){
		return new FileWriter(file, charset);
	}

	/**
	 * [创建 FileWriter](Create filewriter)
	 * @description zh - 创建 FileWriter
	 * @description en - Create filewriter
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 21:14:02
	 * @param file 文件
	 * @return com.xiaoTools.core.io.fileWriter.FileWriter
	 */
	public static FileWriter create(File file){
		return new FileWriter(file);
	}

	/**
	 * [将String写入文件](Write string to file)
	 * @description zh - 将String写入文件
	 * @description en - Write string to file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 21:14:41
	 * @param content 内容
	 * @param isAppend 是否追加
	 * @return java.io.File
	 */
	public File write(String content, boolean isAppend) throws IORuntimeException {
		BufferedWriter writer = null;
		try {
			writer = getWriter(isAppend);
			writer.write(content);
			writer.flush();
		}catch(IOException e){
			throw new IORuntimeException(e);
		}finally {
			IoUtil.close(writer);
		}
		return file;
	}

	/**
	 * [将String写入文件](Write string to file)
	 * @description zh - 将String写入文件
	 * @description en - Write string to file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 21:15:33
	 * @param content 内容
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return java.io.File
	 */
	public File write(String content) throws IORuntimeException {
		return write(content, Constant.FALSE);
	}

	/**
	 * [将String写入文件](Write string to file)
	 * @description zh - 将String写入文件
	 * @description en - Write string to file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 21:17:29
	 * @param content 内容
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return java.io.File
	 */
	public File append(String content) throws IORuntimeException {
		return write(content, Constant.TRUE);
	}

	/**
	 * [将列表写入文件](Write list to file)
	 * @description zh - 将列表写入文件
	 * @description en - Write list to file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 21:17:53
	 * @param list 列表
	 * @return java.io.File
	 */
	public <T> File writeLines(Collection<T> list) throws IORuntimeException {
		return writeLines(list, Constant.FALSE);
	}


}
