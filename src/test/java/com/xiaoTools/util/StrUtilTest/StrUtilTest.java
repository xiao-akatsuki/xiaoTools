package com.xiaoTools.util.StrUtilTest;

import com.xiaoTools.util.strUtil.StrUtil;

import org.junit.Test;

public class StrUtilTest {

	@Test
	public void text_isBlankIfStr(){
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

}
