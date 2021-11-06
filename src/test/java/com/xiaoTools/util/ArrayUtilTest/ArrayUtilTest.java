package com.xiaoTools.util.ArrayUtilTest;

import com.xiaoTools.util.arrayUtil.ArrayUtil;

import org.junit.Test;

public class ArrayUtilTest {

	@Test
	public void test_empty(){
		int[] a = {};
		int[] b = null;
		// `a` is empty --> true
		System.out.println( "`a` is empty --> " + ArrayUtil.isEmpty(a));
		// `b` is empty --> true
		System.out.println( "`b` is empty --> " + ArrayUtil.isEmpty(b));
	}

	@Test
	public void test_newArray(){
		String[] array = ArrayUtil.newArray(String.class, 10);
		// `array` length --> 10
		System.out.println( "`array` length --> " + array.length);
	}

}
