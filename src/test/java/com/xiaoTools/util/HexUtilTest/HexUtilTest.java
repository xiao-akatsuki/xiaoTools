package com.xiaoTools.util.HexUtilTest;

import com.xiaoTools.util.hexUtil.HexUtil;

import org.junit.Test;

public class HexUtilTest {
	@Test
	public void test_encodeHexStr(){
		String a = "this is a xiaoTools";

		// 746869732069732061207869616f546f6f6c73
		System.out.println(HexUtil.encodeHexStr(a));
		// this is a xiaoTools
		System.out.println(HexUtil.decodeHexStr(HexUtil.encodeHexStr(a)));
	}


}
