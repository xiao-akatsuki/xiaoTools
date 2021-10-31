package com.xiaoTools.util.StrUtilTest;

import org.junit.Test;

import java.util.ArrayList;

import com.xiaoTools.util.strUtil.StrUtil;

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

		//`a` is --> abc.jpg
		System.out.println( " `a` is --> " + a);
		//
		System.out.println( " `a` is remove suffix --> " + StrUtil.removeSuffix(a, ".jpg"));
	}

	@Test
	public void test_Empty_And_blank(){
		String a = "";
		String b = " ";
		String c = "hello world";
		String d = "hello";

		// is `a` hasEmpty --> true
		System.out.println( "is `a` hasEmpty --> " + StrUtil.hasEmpty(a));
		// is `b` hasEmpty --> false
		System.out.println( "is `b` hasEmpty --> " + StrUtil.hasEmpty(b));
		// is `c` hasEmpty --> false
		System.out.println( "is `c` hasEmpty --> " + StrUtil.hasEmpty(c));
		// is `d` hasEmpty --> false
		System.out.println( "is `d` hasEmpty --> " + StrUtil.hasEmpty(d));

		// is `a` hasBlank --> true
		System.out.println( "is `a` hasBlank --> " + StrUtil.hasBlank(a));
		// is `b` hasBlank --> true
		System.out.println( "is `b` hasBlank --> " + StrUtil.hasBlank(b));
		// is `c` hasBlank --> false
		System.out.println( "is `c` hasBlank --> " + StrUtil.hasBlank(c));
		// is `d` hasBlank --> false
		System.out.println( "is `d` hasBlank --> " + StrUtil.hasBlank(d));
	}

	@Test
	public void test_removePrefix_and_removeSuffix(){
		String fileName = "portrait.jpg";
		// is `fileName` removeSuffix --> portrait
		System.out.println(StrUtil.removeSuffix( "is `fileName` removeSuffix --> " + fileName, ".jpg"));
	}

	@Test
	public void test_subString(){
		String a = "abcdefg";

		// `a` subString 2 and 3 --> c
		System.out.println( "`a` subString 2 and 3 --> " + StrUtil.subString(a, 2, 3));
		// `a` subString -1 and 1 --> bcdef
		System.out.println( "`a` subString -1 and 1 --> " + StrUtil.subString(a, -1, 1));
		// `a` subString 2 and -3 --> cd
		System.out.println( "`a` subString 2 and -3 --> " + StrUtil.subString(a, 2, -3));
	}

	@Test
	public void test_format(){
		System.out.println(StrUtil.format("{} is {} method","this","format"));
	}

	@Test
	public void test_eval(){
		String a = "(1 + 2) * 4";

		System.out.println( "(1 + 2) * 4 = " + StrUtil.eval(a));
	}
}
