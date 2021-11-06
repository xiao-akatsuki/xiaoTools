package com.xiaoTools.util.BooleanUtilTest;

import com.xiaoTools.util.booleanUtil.BooleanUtil;

import org.junit.Test;

public class BooleanUtilTest {

	@Test
	public void test_toBoolean(){
		// true
		boolean a = BooleanUtil.toBoolean("true");
		//true
		boolean b = BooleanUtil.toBoolean("1");
	}

}
