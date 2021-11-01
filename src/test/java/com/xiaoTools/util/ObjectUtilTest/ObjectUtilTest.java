package com.xiaoTools.util.ObjectUtilTest;

import java.util.HashMap;
import java.util.Map;

import com.xiaoTools.util.objectUtil.ObjectUtil;

import org.junit.Test;

public class ObjectUtilTest {

	@Test
	public void test_equal(){
		Object a = null;
		Object b = new Object();
		boolean c = ObjectUtil.equal(a, b);
		// `a` equal `b` --> false
		System.out.println( "`a` equal `b` --> " + c);
	}

	@Test
	public void test_length(){
		int[] a = new int[]{0,1,2,3};
		int b = ObjectUtil.length(a);
		// `a` array length --> 4
		System.out.println( "`a` array length --> " + b);

		Map<Integer,Integer> map = new HashMap<>();
		map.put(1,1);
		map.put(2,2);
		map.put(3,3);
		int c = ObjectUtil.length(map);
		// `map` is length --> 3
		System.out.println("`map` is length --> " + c);
	}

}
