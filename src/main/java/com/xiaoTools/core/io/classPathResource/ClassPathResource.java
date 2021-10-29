package com.xiaoTools.core.io.classPathResource;

import java.net.URL;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.core.exception.noResourceException.NoResourceException;
import com.xiaoTools.core.io.urlResource.UrlResource;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.classUtil.ClassUtil;
import com.xiaoTools.util.fileUtil.fileUtil.FileUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.strUtil.StrUtil;
import com.xiaoTools.util.urlUtil.URLUtil;

/**
 * [ClassPath单一资源访问类](Classpath single resource access class)
 * @description zh - ClassPath单一资源访问类
 * @description en - Classpath single resource access class
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-29 10:14:37
 */
public class ClassPathResource extends UrlResource {
	private static final long serialVersionUID = 1L;

	private final String path;
	private final ClassLoader classLoader;
	private final Class<?> clazz;

	public ClassPathResource(String path) {
		this(path, null, null);
	}

	public ClassPathResource(String path, ClassLoader classLoader) {
		this(path, classLoader, null);
	}

	public ClassPathResource(String path, Class<?> clazz) {
		this(path, null, clazz);
	}

	public ClassPathResource(String pathBaseClassLoader, ClassLoader classLoader, Class<?> clazz) {
		super((URL) null);
		Assertion.notNull(pathBaseClassLoader, "Path must not be null");

		final String path = normalizePath(pathBaseClassLoader);
		this.path = path;
		this.name = StrUtil.isBlank(path) ? null : FileUtil.getName(path);

		this.classLoader = ObjectUtil.defaultIfNull(classLoader, ClassUtil.getClassLoader());
		this.clazz = clazz;
		initUrl();
	}

	/**
	 * [获得Path](Get path)
	 * @description zh - 获得Path
	 * @description en - Get path
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 10:17:50
	 * @return java.lang.String
	 */
	public final String getPath() {
		return this.path;
	}

	/**
	 * [获得绝对路径Path](Get absolute path path)
	 * @description zh - 获得绝对路径Path
	 * @description en - Get absolute path path
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 10:20:06
	 * @return java.lang.String
	 */
	public final String getAbsolutePath() {
		return FileUtil.isAbsolutePath(this.path) ? this.path :
			FileUtil.normalize(URLUtil.getDecodedPath(this.url));
	}

	/**
	 * [获得ClassLoader](Get classloader)
	 * @description zh - 获得ClassLoader
	 * @description en - Get classloader
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 10:20:53
	 * @return java.lang.ClassLoader
	 */
	public final ClassLoader getClassLoader() {
		return this.classLoader;
	}

	/**
	 * [根据给定资源初始化URL](Initializes the URL based on the given resource)
	 * @description zh - 根据给定资源初始化URL
	 * @description en - Initializes the URL based on the given resource
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 10:22:35
	 */
	private void initUrl() {
		super.url = null != this.clazz ? this.clazz.getResource(this.path) :
			null != this.classLoader ? this.classLoader.getResource(this.path) :
				ClassLoader.getSystemResource(this.path);
		if (null == super.url) {
			throw new NoResourceException("Resource of path [{}] not exist!", this.path);
		}
	}

	/**
	 * [标准化Path格式](Standardized path format)
	 * @description zh - 标准化Path格式
	 * @description en - Standardized path format
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 10:23:46
	 * @param path Path
	 * @return java.lang.String
	 */
	private String normalizePath(String path) {
		path = FileUtil.normalize(path);
		path = StrUtil.removePrefix(path, Constant.SINGLE_STRING_SLASH);

		Assertion.isFalse(FileUtil.isAbsolutePath(path), "Path [{}] must be a relative path !", path);
		return path;
	}

	@Override
	public String toString() {
		return (null == this.path) ? super.toString() : "classpath:" + this.path;
	}

}
