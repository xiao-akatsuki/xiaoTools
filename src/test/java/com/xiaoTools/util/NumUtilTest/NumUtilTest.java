package com.xiaoTools.util.NumUtilTest;

import com.xiaoTools.util.numUtil.NumUtil;

import org.junit.Test;

public class NumUtilTest {

	@Test
	public void basic_operation(){
		// addition
		double a = NumUtil.addition(1.2, 2.3);
		// 1.2 + 2.3 = 3.5
		System.out.println( "1.2 + 2.3 = " + a);

		// subtraction
		double b = NumUtil.subtraction(3.2, 2.1);
		// 3.2 - 2.1 = 1.1
		System.out.println( "3.2 - 2.1 = " + b);

		// multiple
		double c = NumUtil.multiplication(2.3, 4.0);
		// 2.3 * 4.0 = 9.2
		System.out.println( "2.3 * 4.0 = " + c);

		// division
		double d = NumUtil.division(4.2,2.0);
		// 4.2 / 2.0 = 2.1
		System.out.println( "4.2 / 2.0 = " + d );
	}

	@Test
	public void test_retainDecimalPoint(){
		double a = 123456.123456;
		double b = NumUtil.retainDecimalPoint(a,2);
		// 123456.12
		System.out.println(b);
	}

	@Test
	public void test_roman(){
		// 18 to roman --> XVIII
		System.out.println("18 to roman --> " + NumUtil.intToRoman(18));
		// X to int --> 10
		System.out.println("X to int --> " + NumUtil.romanToInt("X"));
	}

}
