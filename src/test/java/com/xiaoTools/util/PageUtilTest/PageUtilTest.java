package com.xiaoTools.util.PageUtilTest;

import com.xiaoTools.util.pageUtil.PageUtil;

import org.junit.Test;

public class PageUtilTest {

	@Test
	public void test_transToStartEnd(){
		// 10 20
		int[] a = PageUtil.transToStartEnd(1, 10);
		for(int i : a){
			System.out.print(i + " ");
		}

		System.out.println();

		// 0 10
		int[] b = PageUtil.transToStartEnd(0, 10);
		for(int i : b){
			System.out.print(i + " ");
		}
	}

	@Test
	public void test_totalPage(){
		int a = PageUtil.totalPage(10, 3);
		// 4
		System.out.println(a);
	}

}
