package com.xiaoTools.core.io.urlResource;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

import com.xiaoTools.core.exception.noResourceException.NoResourceException;
import com.xiaoTools.core.io.resource.Resource;
import com.xiaoTools.util.fileUtil.fileUtil.FileUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.urlUtil.URLUtil;

/**
 * [URL资源访问类](URL resource access class)
 * @description zh - URL资源访问类
 * @description en - URL resource access class
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-29 10:10:12
 */
public class UrlResource implements Resource, Serializable {

	private static final long serialVersionUID = 1L;

	protected URL url;
	protected String name;

	public UrlResource(URL url) {
		this(url, null);
	}

	public UrlResource(URL url, String name) {
		this.url = url;
		this.name = ObjectUtil.defaultIfNull(name, (null != url) ? FileUtil.getName(url.getPath()) : null);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public URL getUrl(){
		return this.url;
	}

	@Override
	public InputStream getStream() throws NoResourceException{
		if(null == this.url){
			throw new NoResourceException("Resource URL is null!");
		}
		return URLUtil.getStream(url);
	}

	@Override
	public String toString() {
		return (null == this.url) ? "null" : this.url.toString();
	}

	/**
	 * [获得File](Get file)
	 * @description zh - 获得File
	 * @description en - Get file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 10:11:46
	 * @return java.io.File
	 */
	public File getFile(){
		return FileUtil.file(this.url);
	}

}
