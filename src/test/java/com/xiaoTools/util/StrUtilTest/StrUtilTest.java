package com.xiaoTools.util.StrUtilTest;

import java.util.ArrayList;

import com.xiaoTools.util.strUtil.StrUtil;

import org.junit.Test;

public class StrUtilTest {

	@Test
	public void test_isBlankIfStr(){
		String a = "";
		String b = " ";
		String c = "hello xiaoTools";

		// is 'a' blank --> true
		System.out.println(" is 'a' blank --> " + StrUtil.isBlankIfStr(a));
		// is 'b' blank --> true
		System.out.println(" is 'b' blank --> " + StrUtil.isBlankIfStr(b));
		// is 'c' blank --> false
		System.out.println(" is 'c' blank --> " + StrUtil.isBlankIfStr(c));
	}

	@Test
	public void test_isEmptyIfStr(){
		String a = "";
		String b = " ";
		String c = "hello xiaoTools";

		// is 'a' empty --> true
		System.out.println( " is 'a' empty --> " + StrUtil.isEmptyIfStr(a));
		// is 'b' empty --> false
		System.out.println( " is 'b' empty --> " + StrUtil.isEmptyIfStr(b));
		// is 'c' empty --> false
		System.out.println( " is 'c' empty --> " + StrUtil.isEmptyIfStr(c));
	}

	@Test
	public void test_trim(){
		String a = " a ";
		// is ` a ` length --> 3
		System.out.println( "is ` a ` length --> " + a.length());
		// `a` after trim length --> 1
		System.out.println( "`a` after trim length --> " + StrUtil.trim(a).length());
	}

	@Test
	public void test_string(){
		ArrayList<String> a = new ArrayList<String>(2);

		a.add("1");
		a.add("2");

		//is `a` size --> 2
		System.out.println( "is `a` size --> " + a.size());
		//`a` after striing length --> 6
		System.out.println( "`a` after striing length --> " + StrUtil.string(a).length());
	}

	@Test
	public void test_hasBlank(){
		String a = "";
		String b = "b";
		String c = " c ";

		// is `a` blank --> true
		System.out.println( "is `a` blank --> " + StrUtil.hasBlank(a));
		// is `b` blank --> false
		System.out.println( "is `b` blank --> " + StrUtil.hasBlank(b));
		// is `c` blank --> false
		System.out.println( "is `c` blank --> " + StrUtil.hasBlank(c));
	}

	@Test
	public void test_hasEmpty(){
		String a = "";
		String b = "b";
		String c = " c ";

		// is `a` blank --> true
		System.out.println( "is `a` blank --> " + StrUtil.isEmpty(a));
		// is `b` blank --> false
		System.out.println( "is `b` blank --> " + StrUtil.isEmpty(b));
		// is `c` blank --> false
		System.out.println( "is `c` blank --> " + StrUtil.isEmpty(c));
	}

	@Test
	public void test_removeSuffix(){
		String a = "abc.jpg";

		// `a` is --> abc.jpg
		System.out.println( " `a` is --> " + a);
		// `a` is remove suffix --> abc
		System.out.println( " `a` is remove suffix --> " + StrUtil.removeSuffix(a, ".jpg"));
	}
}
