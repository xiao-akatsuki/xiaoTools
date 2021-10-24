package com.xiaoTools.lang.jarClassLoader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import com.xiaoTools.core.exception.utilException.UtilException;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.classUtil.ClassUtil;
import com.xiaoTools.util.fileUtil.fileUtil.FileUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;

/**
 * [外部Jar的类加载器](Class loader for external jar)
 * @description zh - 外部Jar的类加载器
 * @description en - Class loader for external jar
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-24 18:31:04
 */
public class JarClassLoader extends URLClassLoader {

	public JarClassLoader() {
		this(new URL[]{});
	}

	public JarClassLoader(URL[] urls) {
		super(urls, ClassUtil.getClassLoader());
	}

	/**
	 * [加载Jar文件](Load jar file)
	 * @description zh - 加载Jar文件
	 * @description en - Load jar file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:37:34
	 * @param jarFileOrDir jar文件或者jar文件所在目录
	 * @return com.xiaoTools.lang.jarClassLoader.JarClassLoader
	 */
	public JarClassLoader addJar(File jarFileOrDir) {
		if (isJarFile(jarFileOrDir)) {
			return addURL(jarFileOrDir);
		}
		final List<File> jars = loopJar(jarFileOrDir);
		for (File jar : jars) {
			addURL(jar);
		}
		return this;
	}

	@Override
	public void addURL(URL url) {
		super.addURL(url);
	}

	public JarClassLoader addURL(File dir) {
		super.addURL(URLUtil.getURL(dir));
		return this;
	}

	/**
	 * [递归获得Jar文件](Get jar file recursively)
	 * @description zh - 递归获得Jar文件
	 * @description en - Get jar file recursively
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:39:28
	 * @param file jar文件或者包含jar文件的目录
	 */
	private static List<File> loopJar(File file) {
		return FileUtil.loopFiles(file, JarClassLoader::isJarFile);
	}

	/**
	 * [是否为jar文件](Is it a jar file)
	 * @description zh - 是否为jar文件
	 * @description en - Is it a jar file
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:40:45
	 * @param file 文件
	 * @return boolean
	 */
	private static boolean isJarFile(File file) {
		return Constant.FALSE == FileUtil.isFile(file) ? Constant.FALSE : file.getPath().toLowerCase().endsWith(".jar");
	}

	/**
	 * [加载Jar到ClassPath](Load jar to classpath)
	 * @description zh - 加载Jar到ClassPath
	 * @description en - Load jar to classpath
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:31:59
	 * @param dir 文件路径
	 * @return com.xiaoTools.lang.jarClassLoader.JarClassLoader
	 */
	public static JarClassLoader load(File dir) {
		final JarClassLoader loader = new JarClassLoader();
		loader.addJar(dir);//查找加载所有jar
		loader.addURL(dir);//查找加载所有class
		return loader;
	}

	/**
	 * [加载Jar到ClassPath](Load jar to classpath)
	 * @description zh - 加载Jar到ClassPath
	 * @description en - Load jar to classpath
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:33:02
	 * @param jarFile jar文件或所在目录
	 * @return com.xiaoTools.lang.jarClassLoader.JarClassLoader
	 */
	public static JarClassLoader loadJar(File jarFile) {
		final JarClassLoader loader = new JarClassLoader();
		loader.addJar(jarFile);
		return loader;
	}

	/**
	 * [加载Jar文件到指定loader中](Load the jar file into the specified loader)
	 * @description zh - 加载Jar文件到指定loader中
	 * @description en - Load the jar file into the specified loader
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:34:45
	 * @param loader URLClassLoader
	 * @param jarFile 被加载的jar
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 */
	public static void loadJar(URLClassLoader loader, File jarFile) throws UtilException {
		try {
			final Method method = ClassUtil.getDeclaredMethod(URLClassLoader.class, "addURL", URL.class);
			if (null != method) {
				method.setAccessible(true);
				final List<File> jars = loopJar(jarFile);
				for (File jar : jars) {
					ReflectUtil.invoke(loader, method, jar.toURI().toURL());
				}
			}
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}

	/**
	 * [加载Jar文件到System ClassLoader中](Load jar file into system classloader)
	 * @description zh - 加载Jar文件到System ClassLoader中
	 * @description en - Load jar file into system classloader
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:36:04
	 * @param jarFile 被加载的jar
	 * @return java.net.URLClassLoader
	 */
	public static URLClassLoader loadJarToSystemClassLoader(File jarFile) {
		URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		loadJar(urlClassLoader, jarFile);
		return urlClassLoader;
	}
}
