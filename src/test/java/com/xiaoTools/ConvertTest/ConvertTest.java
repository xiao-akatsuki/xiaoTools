package com.xiaoTools.ConvertTest;

import com.xiaoTools.core.convert.Convert;

import org.junit.Test;

public class ConvertTest {

	@Test
	public void test_demo(){
		int a = 1;
		//"1"
		String b = Convert.toStr(a);

		long[] c = {1,2,3,4};
		//"{1,2,3,4}"
		String d = Convert.toStr(c);
	}

}
