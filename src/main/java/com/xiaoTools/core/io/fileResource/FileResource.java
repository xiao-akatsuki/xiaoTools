package com.xiaoTools.core.io.fileResource;
import java.net.URL;
import java.io.File;
import java.nio.file.Path;
import java.io.InputStream;
import java.io.Serializable;

import com.xiaoTools.core.exception.noResourceException.NoResourceException;
import com.xiaoTools.core.io.resource.Resource;
import com.xiaoTools.util.fileUtil.fileUtil.FileUtil;
import com.xiaoTools.util.urlUtil.URLUtil;

/**
 * [文件资源访问对象](File resource access object)
 * @description zh - 文件资源访问对象
 * @description en - File resource access object
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-29 09:58:59
 */
public class FileResource implements Resource, Serializable  {

	private static final long serialVersionUID = 1L;

	private final File file;

	public FileResource(Path path) {
		this(path.toFile());
	}

	public FileResource(File file) {
		this(file, file.getName());
	}

	public FileResource(File file, String fileName) {
		this.file = file;
	}

	public FileResource(String path) {
		this(FileUtil.file(path));
	}

	@Override
	public String getName() {
		return this.file.getName();
	}

	@Override
	public URL getUrl(){
		return URLUtil.getURL(this.file);
	}

	@Override
	public InputStream getStream() throws NoResourceException {
		return FileUtil.getInputStream(this.file);
	}

	@Override
	public String toString() {
		return (null == this.file) ? "null" : this.file.toString();
	}

	/**
	 * [获取文件](get files)
	 * @description zh - 获取文件
	 * @description en - get files
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 10:00:25
	 * @return java.io.File
	 */
	public File getFile() {
		return this.file;
	}

}
