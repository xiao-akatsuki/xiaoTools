package com.xiaoTools.util.ClassLoaderUtilTest;

import com.xiaoTools.util.classLoaderUtil.ClassLoaderUtil;

import org.junit.Test;

public class ClassLoaderUtilTest {

	@Test
	public void test_getClassLoader(){
		// jdk.internal.loader.ClassLoaders$AppClassLoader@55054057
		ClassLoader a = ClassLoaderUtil.getClassLoader();
		System.out.println(a);
	}

}
