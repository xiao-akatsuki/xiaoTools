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
import com.xiaoTools.core.exception.iORuntimeException.IORuntimeException;
import com.xiaoTools.core.exception.utilException.UtilException;
import com.xiaoTools.core.io.urlQuery.UrlQuery;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.charUtil.CharUtil;
import com.xiaoTools.util.charsetUtil.CharsetUtil;
import com.xiaoTools.util.classLoaderUtil.ClassLoaderUtil;
import com.xiaoTools.util.ioUtil.IoUtil;
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

	/**
	 * [补全相对路径](Complete relative path)
	 * @description zh - 补全相对路径
	 * @description en - Complete relative path
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 18:59:13
	 * @param baseUrl 基准URL
	 * @param relativePath 相对URL
	 * @return java.lang.String
	 */
	public static String completeUrl(String baseUrl, String relativePath) {
		baseUrl = normalize(baseUrl, Constant.FALSE);
		if (StrUtil.isBlank(baseUrl)) {
			return null;
		}

		try {
			final URL absoluteUrl = new URL(baseUrl);
			final URL parseUrl = new URL(absoluteUrl, relativePath);
			return parseUrl.toString();
		} catch (MalformedURLException e) {
			throw new UtilException(e);
		}
	}

	/**
	 * [编码URL](Encoding URL)
	 * @description zh - 编码URL
	 * @description en - Encoding URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:00:21
	 * @param url URL
	 * @return java.lang.String
	 */
	public static String encodeAll(String url) {
		return encodeAll(url, CharsetUtil.CHARSET_UTF_8);
	}

	/**
	 * [编码URL](Encoding URL)
	 * @description zh - 编码URL
	 * @description en - Encoding URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:02:36
	 * @param url URL
	 * @param charset 字符集
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return java.lang.String
	 */
	public static String encodeAll(String url, Charset charset) throws UtilException {
		return null == charset || StrUtil.isEmpty(url) ? url : com.xiaoTools.core.io.urlEncoder.URLEncoder.ALL.encode(url, charset);
	}

	/**
	 * [编码URL](Encoding URL)
	 * @description zh - 编码URL
	 * @description en - Encoding URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:13:25
	 * @param url URL
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return java.lang.String
	 */
	public static String encode(String url) throws UtilException {
		return encode(url, CharsetUtil.CHARSET_UTF_8);
	}

	/**
	 * [编码URL](Encoding URL)
	 * @description zh - 编码URL
	 * @description en - Encoding URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:14:01
	 * @param url URL
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return java.lang.String
	 */
	public static String encodeQuery(String url) throws UtilException {
		return encodeQuery(url, CharsetUtil.CHARSET_UTF_8);
	}

	/**
	 * [编码字符为 application/x-www-form-urlencoded](The encoding character is application/x-www-form-urlencoded)
	 * @description zh - 编码字符为 application/x-www-form-urlencoded
	 * @description en - The encoding character is application/x-www-form-urlencoded
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:15:20
	 * @param url URL
	 * @param charset 字符集
	 * @return java.lang.String
	 */
	public static String encode(String url, Charset charset) {
		if (StrUtil.isEmpty(url)) {
			return url;
		}
		if (null == charset) {
			charset = CharsetUtil.defaultCharset();
		}
		return com.xiaoTools.core.io.urlEncoder.URLEncoder.DEFAULT.encode(url, charset);
	}

	/**
	 * [编码字符为URL中查询语句](The encoded character is the query statement in the URL)
	 * @description zh - 编码字符为URL中查询语句
	 * @description en - The encoded character is the query statement in the URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:17:31
	 * @param url URL
	 * @param charset 字符集
	 * @return java.lang.String
	 */
	public static String encodeQuery(String url, Charset charset) {
		if (StrUtil.isEmpty(url)) {
			return url;
		}
		if (null == charset) {
			charset = CharsetUtil.defaultCharset();
		}
		return com.xiaoTools.core.io.urlEncoder.URLEncoder.QUERY.encode(url, charset);
	}

	/**
	 * [编码字符为 application/x-www-form-urlencoded](The encoding character is application/x-www-form-urlencoded)
	 * @description zh - 编码字符为 application/x-www-form-urlencoded
	 * @description en - The encoding character is application/x-www-form-urlencoded
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:18:29
	 * @param url URL
	 * @param charset 字符集
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return java.lang.String
	 */
	public static String encode(String url, String charset) throws UtilException {
		return StrUtil.isEmpty(url) ? url :
			encode(url, StrUtil.isBlank(charset) ? CharsetUtil.defaultCharset() : CharsetUtil.charset(charset));
	}

	/**
	 * [编码URL](Encoding URL)
	 * @description zh - 编码URL
	 * @description en - Encoding URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:19:26
	 * @param url URL
	 * @param charset 字符集
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return java.lang.String
	 */
	public static String encodeQuery(String url, String charset) throws UtilException {
		return encodeQuery(url, StrUtil.isBlank(charset) ? CharsetUtil.defaultCharset() : CharsetUtil.charset(charset));
	}

	/**
	 * [解码URL](Decode URL)
	 * @description zh - 解码URL
	 * @description en - Decode URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:20:35
	 * @param url URL
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return java.lang.String
	 */
	public static String decode(String url) throws UtilException {
		return decode(url, CharsetUtil.UTF_8);
	}

	/**
	 * [解码application/x-www-form-urlencoded字符](Decoding application/x-www-form-urlencoded characters)
	 * @description zh - 解码application/x-www-form-urlencoded字符
	 * @description em - Decoding application/x-www-form-urlencoded characters
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:21:55
	 * @param content 被解码内容
	 * @param charset 编码
	 * @return java.lang.String
	 */
	public static String decode(String content, Charset charset) {
		return null == charset ? content :
			URLDecoder.decode(content, charset);
	}

	/**
	 * [解码application/x-www-form-urlencoded字符](Decoding application/x-www-form-urlencoded characters)
	 * @description zh - 解码application/x-www-form-urlencoded字符
	 * @description em - Decoding application/x-www-form-urlencoded characters
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:23:28
	 * @param content 被解码内容
	 * @param charset 编码
	 * @param isPlusToSpace 是否+转换为空格
	 * @return java.lang.String
	 */
	public static String decode(String content, Charset charset, boolean isPlusToSpace) {
		return null == charset ? content :
			com.xiaoTools.core.io.urlDecoder.URLDecoder.decode(content, charset, isPlusToSpace);
	}

	/**
	 * [解码application/x-www-form-urlencoded字符](Decoding application/x-www-form-urlencoded characters)
	 * @description zh - 解码application/x-www-form-urlencoded字符
	 * @description em - Decoding application/x-www-form-urlencoded characters
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:24:11
	 * @param content 被解码内容
	 * @param charset 编码
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return java.lang.String
	 */
	public static String decode(String content, String charset) throws UtilException {
		return decode(content, CharsetUtil.charset(charset));
	}

	/**
	 * [获得path部分](Get path section)
	 * @description zh - 获得path部分
	 * @description en - Get path section
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:24:51
	 * @param uriStr URI路径
	 * @return java.lang.String
	 */
	public static String getPath(String uriStr) {
		return toURI(uriStr).getPath();
	}

	/**
	 * [从URL对象中获取不被编码的路径Path](Gets the path from the URL object that is not encoded)
	 * @description zh - 从URL对象中获取不被编码的路径Path
	 * @description en - Gets the path from the URL object that is not encoded
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:26:18
	 * @param url URL
	 * @return java.lang.String
	 */
	public static String getDecodedPath(URL url) {
		if (null == url) {
			return null;
		}
		String path = null;
		try {
			path = toURI(url).getPath();
		} catch (UtilException e) { }
		return (null != path) ? path : url.getPath();
	}

	/**
	 * [转URL为URI](Convert URL to URI)
	 * @description zh - 转URL为URI
	 * @description en - Convert URL to URI
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:47:20
	 * @param url URL
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return java.net.URI
	 */
	public static URI toURI(URL url) throws UtilException {
		return toURI(url, Constant.FALSE);
	}

	/**
	 * [转URL为URI](Convert URL to URI)
	 * @description zh - 转URL为URI
	 * @description en - Convert URL to URI
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:27:21
	 * @param url URL
	 * @param isEncode 是否编码参数中的特殊字符
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return java.net.URI
	 */
	public static URI toURI(URL url, boolean isEncode) throws UtilException {
		return null == url ? null : toURI(url.toString(), isEncode);
	}

	/**
	 * [转字符串为URI](Convert string to URI)
	 * @description zh - 转字符串为URI
	 * @description en - Convert string to URI
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:28:48
	 * @param location 字符串路径
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return java.net.URI
	 */
	public static URI toURI(String location) throws UtilException {
		return toURI(location, false);
	}

	/**
	 * [转字符串为URI](Convert string to URI)
	 * @description zh - 转字符串为URI
	 * @description en - Convert string to URI
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:29:44
	 * @param location 字符串路径
	 * @param isEncode 是否编码参数中的特殊字符
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return java.net.URI
	 */
	public static URI toURI(String location, boolean isEncode) throws UtilException {
		if (isEncode) {
			location = encode(location);
		}
		try {
			return new URI(StrUtil.trim(location));
		} catch (URISyntaxException e) {
			throw new UtilException(e);
		}
	}

	/**
	 * [提供的URL是否为文件](Is the URL provided a file)
	 * @description zh - 提供的URL是否为文件
	 * @description en - Is the URL provided a file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:30:43
	 * @param url URL
	 * @return boolean
	 */
	public static boolean isFileURL(URL url) {
		String protocol = url.getProtocol();
		return (URL_PROTOCOL_FILE.equals(protocol) ||
				URL_PROTOCOL_VFSFILE.equals(protocol) ||
				URL_PROTOCOL_VFS.equals(protocol));
	}

	/**
	 * [提供的URL是否为jar包URL](is the URL provided a jar package URL)
	 * @description zh - 提供的URL是否为jar包URL
	 * @description en - is the URL provided a jar package URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:31:41
	 * @param url URL
	 * @return boolean
	 */
	public static boolean isJarURL(URL url) {
		final String protocol = url.getProtocol();
		return (URL_PROTOCOL_JAR.equals(protocol) ||
				URL_PROTOCOL_ZIP.equals(protocol) ||
				URL_PROTOCOL_VFSZIP.equals(protocol) ||
				URL_PROTOCOL_WSJAR.equals(protocol));
	}

	/**
	 * [提供的URL是否为jar包URL](is the URL provided a jar package URL)
	 * @description zh - 提供的URL是否为jar包URL
	 * @description en - is the URL provided a jar package URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:33:19
	 * @param url URL
	 * @return boolean
	 */
	public static boolean isJarFileURL(URL url) {
		return (URL_PROTOCOL_FILE.equals(url.getProtocol()) &&
				url.getPath().toLowerCase().endsWith(Constant.JAR_FILE_EXT));
	}

	/**
	 * [从URL中获取流](Get stream from URL)
	 * @description zh - 从URL中获取流
	 * @description en - Get stream from URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:34:07
	 * @param url URL
	 * @return java.io.InputStream
	 */
	public static InputStream getStream(URL url) {
		Assertion.notNull(url);
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	/**
	 * [获得Reader](get Reader)
	 * @description zh - 获得Reader
	 * @description en - get Reader
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:34:54
	 * @param url URL
	 * @param charset 字符集
	 * @return java.io.BufferedReader
	 */
	public static BufferedReader getReader(URL url, Charset charset) {
		return IoUtil.getReader(getStream(url), charset);
	}

	/**
	 * [从URL中获取JarFile](Get jarfile from URL)
	 * @description zh - 从URL中获取JarFile
	 * @description en - Get jarfile from URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:36:04
	 * @param url URL
	 * @return java.util.jar.JarFile
	 */
	public static JarFile getJarFile(URL url) {
		try {
			JarURLConnection urlConnection = (JarURLConnection) url.openConnection();
			return urlConnection.getJarFile();
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	/**
	 * [标准化URL字符串](Normalized URL string)
	 * @description zh - 标准化URL字符串
	 * @description en - Normalized URL string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:36:58
	 * @param url URL
	 * @return java.lang.String
	 */
	public static String normalize(String url) {
		return normalize(url, Constant.FALSE);
	}

	/**
	 * [标准化URL字符串](Normalized URL string)
	 * @description zh - 标准化URL字符串
	 * @description en - Normalized URL string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:37:38
	 * @param url URL
	 * @param isEncodePath 是否对URL中path部分的中文和特殊字符做转义
	 * @return java.lang.String
	 */
	public static String normalize(String url, boolean isEncodePath) {
		return normalize(url, isEncodePath, Constant.FALSE);
	}

	/**
	 * [标准化URL字符串](Normalized URL string)
	 * @description zh - 标准化URL字符串
	 * @description en - Normalized URL string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:40:13
	 * @param url URL
	 * @param isEncodePath 是否对URL中path部分的中文和特殊字符做转义
	 * @param replaceSlash 是否替换url body中的 //
	 * @return java.lang.String
	 */
	public static String normalize(String url, boolean isEncodePath, boolean replaceSlash) {
		if (StrUtil.isBlank(url)) {
			return url;
		}
		final int sepIndex = url.indexOf("://");
		String protocol;
		String body;
		if (sepIndex > Constant.ZERO) {
			protocol = StrUtil.subStringPre(url, sepIndex + Constant.THREE);
			body = StrUtil.subStringSuf(url, sepIndex + Constant.THREE);
		} else {
			protocol = "http://";
			body = url;
		}

		final int paramsSepIndex = StrUtil.indexOf(body, '?');
		String params = null;
		if (paramsSepIndex > Constant.ZERO) {
			params = StrUtil.subStringSuf(body, paramsSepIndex);
			body = StrUtil.subStringPre(body, paramsSepIndex);
		}

		if (StrUtil.isNotEmpty(body)) {
			body = body.replaceAll("^[\\\\/]+", Constant.EMPTY);
			body = body.replace("\\", "/");
			if (replaceSlash) {
				body = body.replaceAll("//+", "/");
			}
		}

		final int pathSepIndex = StrUtil.indexOf(body, '/');
		String domain = body;
		String path = null;
		if (pathSepIndex > Constant.ZERO) {
			domain = StrUtil.subStringPre(body, pathSepIndex);
			path = StrUtil.subStringSuf(body, pathSepIndex);
		}
		if (isEncodePath) {
			path = encode(path);
		}
		return protocol + domain + StrUtil.nullToEmpty(path) + StrUtil.nullToEmpty(params);
	}

	/**
	 * [将Map形式的Form表单数据转换为Url参数形式](Convert form data in map form to URL parameter form)
	 * @description zh - 将Map形式的Form表单数据转换为Url参数形式
	 * @description en - Convert form data in map form to URL parameter form
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:42:18
	 * @param paramMap 表单数据
	 * @param charset 编码
	 * @return java.lang.String
	 */
	public static String buildQuery(Map<String, ?> paramMap, Charset charset) {
		return UrlQuery.of(paramMap).build(charset);
	}

	/**
	 * [获取指定URL对应资源的内容长度](Gets the content length of the resource corresponding to the specified URL)
	 * @description zh - 获取指定URL对应资源的内容长度
	 * @description en - Gets the content length of the resource corresponding to the specified URL
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:43:21
	 * @param url URL
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return long
	 */
	public static long getContentLength(URL url) throws IORuntimeException {
		if (null == url) {
			return Constant.NEGATIVE_ONE;
		}

		URLConnection conn = null;
		try {
			conn = url.openConnection();
			return conn.getContentLengthLong();
		} catch (IOException e) {
			throw new IORuntimeException(e);
		} finally {
			if (conn instanceof HttpURLConnection) {
				((HttpURLConnection) conn).disconnect();
			}
		}
	}

	/**
	 * [Data URI Scheme封装](Data URI scheme encapsulation)
	 * @description zh - Data URI Scheme封装
	 * @description em - Data URI scheme encapsulation
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:44:19
	 * @param mimeType 可选项的数据类型
	 * @param data 编码后的数据
	 * @return java.lang.String
	 */
	public static String getDataUriBase64(String mimeType, String data) {
		return getDataUri(mimeType, null, "base64", data);
	}

	/**
	 * [Data URI Scheme封装](Data URI scheme encapsulation)
	 * @description zh - Data URI Scheme封装
	 * @description em - Data URI scheme encapsulation
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:45:23
	 * @param mimeType 可选项的数据类型
	 * @param encoding 数据编码方式
	 * @param data 编码后的数据
	 * @return java.lang.String
	 */
	public static String getDataUri(String mimeType, String encoding, String data) {
		return getDataUri(mimeType, null, encoding, data);
	}

	/**
	 * [Data URI Scheme封装](Data URI scheme encapsulation)
	 * @description zh - Data URI Scheme封装
	 * @description em - Data URI scheme encapsulation
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 19:45:58
	 * @param mimeType 可选项的数据类型
	 * @param charset 字符集
	 * @param encoding 数据编码方式
	 * @param data 编码后的数据
	 * @return java.lang.String
	 */
	public static String getDataUri(String mimeType, Charset charset, String encoding, String data) {
		final StringBuilder builder = StrUtil.builder("data:");
		if (StrUtil.isNotBlank(mimeType)) {
			builder.append(mimeType);
		}
		if (null != charset) {
			builder.append(";charset=").append(charset.name());
		}
		if (StrUtil.isNotBlank(encoding)) {
			builder.append(';').append(encoding);
		}
		builder.append(',').append(data);

		return builder.toString();
	}
}
