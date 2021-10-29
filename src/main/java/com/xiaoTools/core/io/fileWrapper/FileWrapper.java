package com.xiaoTools.core.io.fileWrapper;

import java.io.File;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.xiaoTools.util.fileUtil.fileUtil.FileUtil;

/**
 * [文件包装器](File wrapper)
 * @description zh - 文件包装器
 * @description en - File wrapper
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-29 21:08:14
 */
public class FileWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

	protected File file;
	protected Charset charset;

	/**
	 * 默认编码：UTF-8
	 */
	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

	public FileWrapper(File file, Charset charset) {
		this.file = file;
		this.charset = charset;
	}

	public File getFile() {
		return file;
	}

	public FileWrapper setFile(File file) {
		this.file = file;
		return this;
	}

	public Charset getCharset() {
		return charset;
	}

	public FileWrapper setCharset(Charset charset) {
		this.charset = charset;
		return this;
	}

	/**
	 * [可读的文件大小](Readable file size)
	 * @description zh - 可读的文件大小
	 * @description en - Readable file size
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 21:11:05
	 * @return java.lang.String
	 */
	public String readableFileSize() {
		return FileUtil.readableFileSize(file.length());
	}

}
