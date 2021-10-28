package com.xiaoTools.util.urlUtil;

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




}
