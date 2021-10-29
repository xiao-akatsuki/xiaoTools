package com.xiaoTools.util.urlUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.jar.JarFile;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.core.exception.utilException.UtilException;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.charUtil.CharUtil;
import com.xiaoTools.util.classLoaderUtil.ClassLoaderUtil;
import com.xiaoTools.util.resourceUtil.ResourceUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [URL统一资源定位符相关工具类](URL uniform resource locator related tool classes)
 * @description zh - URL统一资源定位符相关工具类
 * @description en - URL uniform resource locator related tool classes
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-28 22:15:10
 */
public class URLUtil {

	/**
	 * 针对ClassPath路径的伪协议前缀（兼容Spring）: "classpath:"
	 */
	public static final String CLASSPATH_URL_PREFIX = "classpath:";

	/**
	 * URL 前缀表示文件: "file:"
	 */
	public static final String FILE_URL_PREFIX = "file:";

	/**
	 * URL 前缀表示jar: "jar:"
	 */
	public static final String JAR_URL_PREFIX = "jar:";

	/**
	 * URL 前缀表示war: "war:"
	 */
	public static final String WAR_URL_PREFIX = "war:";

	/**
	 * URL 协议表示文件: "file"
	 */
	public static final String URL_PROTOCOL_FILE = "file";

	/**
	 * URL 协议表示Jar文件: "jar"
	 */
	public static final String URL_PROTOCOL_JAR = "jar";

	/**
	 * URL 协议表示zip文件: "zip"
	 */
	public static final String URL_PROTOCOL_ZIP = "zip";

	/**
	 * URL 协议表示WebSphere文件: "wsjar"
	 */
	public static final String URL_PROTOCOL_WSJAR = "wsjar";

	/**
	 * URL 协议表示JBoss zip文件: "vfszip"
	 */
	public static final String URL_PROTOCOL_VFSZIP = "vfszip";

	/**
	 * URL 协议表示JBoss文件: "vfsfile"
	 */
	public static final String URL_PROTOCOL_VFSFILE = "vfsfile";

	/**
	 * URL 协议表示JBoss VFS资源: "vfs"
	 */
	public static final String URL_PROTOCOL_VFS = "vfs";

	/**
	 * Jar路径以及内部文件路径的分界符: "!/"
	 */
	public static final String JAR_URL_SEPARATOR = "!/";

	/**
	 * WAR路径及内部文件路径分界符
	 */
	public static final String WAR_URL_SEPARATOR = "*/";

	/**
	 * [通过一个字符串形式的URL地址创建URL对象](Create a URL object from a URL address in the form of a string)
	 * @description zh - 通过一个字符串形式的URL地址创建URL对象
	 * @description en - Create a URL object from a URL address in the form of a string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 08:47:39
	 * @param url URL
	 * @return java.net.URL
	 */
	public static URL url(String url) {
		return url(url, null);
	}

	/**
	 * [通过一个字符串形式的URL地址创建URL对象](Create a URL object from a URL address in the form of a string)
	 * @description zh - 通过一个字符串形式的URL地址创建URL对象
	 * @description en - Create a URL object from a URL address in the form of a string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 08:49:40
	 * @param url URL
	 * @param handler URLStreamHandler
	 * @return java.net.URL
	 */
	public static URL url(String url, URLStreamHandler handler) {
		Assertion.notNull(url, "URL must not be null");

		if (url.startsWith(CLASSPATH_URL_PREFIX)) {
			url = url.substring(CLASSPATH_URL_PREFIX.length());
			return ClassLoaderUtil.getClassLoader().getResource(url);
		}

		try {
			return new URL(null, url, handler);
		} catch (MalformedURLException e) {
			try {
				return new File(url).toURI().toURL();
			} catch (MalformedURLException ex2) {
				throw new UtilException(e);
			}
		}
	}

	/**
	 * [获取string协议的URL](Gets the URL of the string protocol)
	 * @description zh - 获取string协议的URL
	 * @description en - Gets the URL of the string protocol
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 08:53:10
	 * @param content CharSequence
	 * @return java.net.URI
	 */
	public static URI getStringURI(CharSequence content) {
		final String contentStr = StrUtil.addPrefixIfNot(content, "string:///");
		return URI.create(contentStr);
	}

	/**
	 * [将URL字符串转换为URL对象](Converts a URL string to a URL object)
	 * @description zh - 将URL字符串转换为URL对象
	 * @description en - Converts a URL string to a URL object
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 08:54:17
	 * @param urlStr URL
	 * @return java.net.URL
	 */
	public static URL toUrlForHttp(String urlStr) {
		return toUrlForHttp(urlStr, null);
	}

	/**
	 * [将URL字符串转换为URL对象](Converts a URL string to a URL object)
	 * @description zh - 将URL字符串转换为URL对象
	 * @description en - Converts a URL string to a URL object
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 08:56:10
	 * @param urlStr URL
	 * @param handler URLStreamHandler
	 * @return java.net.URL
	 */
	public static URL toUrlForHttp(String urlStr, URLStreamHandler handler) {
		Assertion.notBlank(urlStr, "Url is blank !");
		urlStr = encodeBlank(urlStr);
		try {
			return new URL(null, urlStr, handler);
		} catch (MalformedURLException e) {
			throw new UtilException(e);
		}
	}

	/**
	 * [单独编码URL中的空白符](Whitespace in individually encoded URLs)
	 * @description zh - 单独编码URL中的空白符
	 * @description en - Whitespace in individually encoded URLs
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 08:58:34
	 * @param urlStr URL
	 * @return java.lang.String
	 */
	public static String encodeBlank(CharSequence urlStr) {
		if (urlStr == null) {
			return null;
		}

		int len = urlStr.length();
		final StringBuilder sb = new StringBuilder(len);
		char c;
		for (int i = Constant.ZERO; i < len; i++) {
			c = urlStr.charAt(i);
			if (CharUtil.isBlankChar(c)) {
				sb.append("%20");
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * [获得URL](Get URL)
	 * @description zh - 获得URL
	 * @description en - Get URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 08:59:47
	 * @param pathBaseClassLoader 相对路径
	 * @return java.net.URL
	 */
	public static URL getURL(String pathBaseClassLoader) {
		return ResourceUtil.getResource(pathBaseClassLoader);
	}

	/**
	 * [获得URL](Get URL)
	 * @description zh - 获得URL
	 * @description en - Get URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:01:07
	 * @param path 相对给定 class所在的路径
	 * @param clazz 指定class
	 * @return java.net.URL
	 */
	public static URL getURL(String path, Class<?> clazz) {
		return ResourceUtil.getResource(path, clazz);
	}

	/**
	 * [获得URL](Get URL)
	 * @description zh - 获得URL
	 * @description en - Get URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:02:47
	 * @param file URL对应的文件对象
	 * @return java.net.URL
	 */
	public static URL getURL(File file) {
		Assertion.notNull(file, "File is null !");
		try {
			return file.toURI().toURL();
		} catch (MalformedURLException e) {
			throw new UtilException(e, "Error occured when get URL!");
		}
	}

	/**
	 * [获得URL](Get URL)
	 * @description zh - 获得URL
	 * @description en - Get URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:21:38
	 * @param files URL对应的文件对象集合
	 * @return java.net.URL[]
	 */
	public static URL[] getURLs(File... files) {
		final URL[] urls = new URL[files.length];
		try {
			for (int i = Constant.ZERO; i < files.length; i++) {
				urls[i] = files[i].toURI().toURL();
			}
		} catch (MalformedURLException e) {
			throw new UtilException(e, "Error occured when get URL!");
		}

		return urls;
	}

	/**
	 * [获取URL中域名部分](Get the domain name part of the URL)
	 * @description zh - 获取URL中域名部分
	 * @description en - Get the domain name part of the URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:23:08
	 * @param url URL
	 * @return java.net.URI
	 */
	public static URI getHost(URL url) {
		if (null == url) {
			return null;
		}

		try {
			return new URI(url.getProtocol(), url.getHost(), null, null);
		} catch (URISyntaxException e) {
			throw new UtilException(e);
		}
	}

}
