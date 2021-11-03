package com.xiaoTools.util.ClassUtilTest;

import com.xiaoTools.util.classUtil.ClassUtil;
import com.xiaoTools.util.strUtil.StrUtil;

import org.junit.Test;

public class ClassUtilTest {

	@Test
	public void test_isAbstract(){
		// false
		boolean a = ClassUtil.isAbstract(StrUtil.class);
		System.out.println(a);

		// true
		boolean b = ClassUtil.isAbstract(int.class);
		System.out.println(b);

	}

	@Test
	public void test_isBasicType(){
		//true
		boolean a = ClassUtil.isBasicType(Integer.class);
		System.out.println(a);

		//false
		boolean b = ClassUtil.isBasicType(StrUtil.class);
		System.out.println(b);
	}



}
