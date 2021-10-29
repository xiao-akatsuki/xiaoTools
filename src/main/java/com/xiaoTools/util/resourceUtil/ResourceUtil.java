package com.xiaoTools.util.resourceUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;

import com.xiaoTools.core.collection.enumerationIter.EnumerationIter;
import com.xiaoTools.core.exception.iORuntimeException.IORuntimeException;
import com.xiaoTools.core.exception.noResourceException.NoResourceException;
import com.xiaoTools.core.io.classPathResource.ClassPathResource;
import com.xiaoTools.core.io.fileResource.FileResource;
import com.xiaoTools.core.io.resource.Resource;
import com.xiaoTools.util.charsetUtil.CharsetUtil;
import com.xiaoTools.util.classLoaderUtil.ClassLoaderUtil;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.fileUtil.fileUtil.FileUtil;
import com.xiaoTools.util.strUtil.StrUtil;
import com.xiaoTools.util.urlUtil.URLUtil;

/**
 * [Resource资源工具类](Resource tool class)
 * @description zh - Resource资源工具类
 * @description en - Resource tool class
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-29 09:27:09
 */
public class ResourceUtil {

	/**
	 * [读取Classpath下的资源为字符串](Read the resource under classpath as a string)
	 * @description zh - 读取Classpath下的资源为字符串
	 * @description en - Read the resource under classpath as a string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:27:47
	 * @param resource 资源路径
	 * @return java.lang.String
	 */
	public static String readUtf8Str(String resource) {
		return getResourceObj(resource).readUtf8Str();
	}

	/**
	 * [读取Classpath下的资源为字符串](Read the resource under classpath as a string)
	 * @description zh - 读取Classpath下的资源为字符串
	 * @description en - Read the resource under classpath as a string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:29:51
	 * @param resource 资源路径
	 * @param charset 编码
	 * @return java.lang.String
	 */
	public static String readStr(String resource, Charset charset) {
		return getResourceObj(resource).readStr(charset);
	}

	/**
	 * [读取Classpath下的资源为byte[]](Read the resource under classpath as byte [])
	 * @description zh - 读取Classpath下的资源为byte[]
	 * @description en - Read the resource under classpath as byte []
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:30:49
	 * @param resource 资源路径
	 * @return byte[]
	 */
	public static byte[] readBytes(String resource) {
		return getResourceObj(resource).readBytes();
	}

	/**
	 * [从ClassPath资源中获取 InputStream](Get InputStream from classpath resource)
	 * @description zh - 从ClassPath资源中获取 InputStream
	 * @description en - Get InputStream from classpath resource
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:33:42
	 * @param resource 资源路径
	 * @throws
	 * @return java.io.InputStream
	 */
	public static InputStream getStream(String resource) throws NoResourceException {
		return getResourceObj(resource).getStream();
	}

	/**
	 * [从ClassPath资源中获取 InputStream](Get InputStream from classpath resource)
	 * @description zh - 从ClassPath资源中获取 InputStream
	 * @description en - Get InputStream from classpath resource
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:34:46
	 * @param resource 资源路径
	 * @return java.io.InputStream
	 */
	public static InputStream getStreamSafe(String resource) {
		try {
			return getResourceObj(resource).getStream();
		} catch (NoResourceException e) {
		}
		return null;
	}

	/**
	 * [从ClassPath资源中获取BufferedReader](Get BufferedReader from classpath resource)
	 * @description zh - 从ClassPath资源中获取BufferedReader
	 * @description en - Get BufferedReader from classpath resource
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:35:37
	 * @param resource 资源路径
	 * @return java.io.BufferedReader
	 */
	public static BufferedReader getUtf8Reader(String resource) {
		return getReader(resource, CharsetUtil.CHARSET_UTF_8);
	}

	/**
	 * [从ClassPath资源中获取BufferedReader](Get BufferedReader from classpath resource)
	 * @description zh - 从ClassPath资源中获取BufferedReader
	 * @description en - Get BufferedReader from classpath resource
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:36:37
	 * @param resource 资源路径
	 * @param charset 字符编码
	 * @return java.io.BufferedReader
	 */
	public static BufferedReader getReader(String resource, Charset charset) {
		return getResourceObj(resource).getReader(charset);
	}

	/**
	 * [获得资源的URL](Get the URL of the resource)
	 * @description zh - 获得资源的URL
	 * @description en - Get the URL of the resource
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:37:34
	 * @param resource 资源路径
	 * @throws com.xiaoTools.core.exception.iORuntimeException.IORuntimeException
	 * @return java.net.URL
	 */
	public static URL getResource(String resource) throws IORuntimeException {
		return getResource(resource, null);
	}

	/**
	 * [获取指定路径下的资源列表](Gets the resource list under the specified path)
	 * @description zh - 获取指定路径下的资源列表
	 * @description en - Gets the resource list under the specified path
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:39:17
	 * @param resource 资源路径
	 * @return java.util.List<java.net.URL>
	 */
	public static List<URL> getResources(String resource) {
		final Enumeration<URL> resources;
		try {
			resources = ClassLoaderUtil.getClassLoader().getResources(resource);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
		return CollUtil.newArrayList(resources);
	}

	/**
	 * [获取指定路径下的资源Iterator](Gets the resource iterator under the specified path)
	 * @description zh - 获取指定路径下的资源Iterator
	 * @description en - Gets the resource iterator under the specified path
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:40:46
	 * @param resource 资源路径
	 * @return com.xiaoTools.core.collection.enumerationIter.EnumerationIter<java.net.URL>
	 */
	public static EnumerationIter<URL> getResourceIter(String resource) {
		final Enumeration<URL> resources;
		try {
			resources = ClassLoaderUtil.getClassLoader().getResources(resource);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
		return new EnumerationIter<>(resources);
	}

	/**
	 * [获得资源相对路径对应的URL](Get the URL corresponding to the relative path of the resource)
	 * @description zh - 获得资源相对路径对应的URL
	 * @description en - Get the URL corresponding to the relative path of the resource
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:41:40
	 * @param resource 资源路径
	 * @param baseClass 基准Class
	 * @return java.net.URL
	 */
	public static URL getResource(String resource, Class<?> baseClass) {
		return (null != baseClass) ? baseClass.getResource(resource) : ClassLoaderUtil.getClassLoader().getResource(resource);
	}

	/**
	 * [获取 Resource 资源对象](Get resource object)
	 * @description zh - 获取 Resource 资源对象
	 * @description en - Get resource object
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 09:43:24
	 * @param path
	 * @return
	 */
	public static Resource getResourceObj(String path) {
		if(StrUtil.isNotBlank(path)) {
			if(path.startsWith(URLUtil.FILE_URL_PREFIX) || FileUtil.isAbsolutePath(path)) {
				return new FileResource(path);
			}
		}
		return new ClassPathResource(path);
	}

}
