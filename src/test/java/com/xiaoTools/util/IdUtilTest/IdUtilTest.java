package com.xiaoTools.util.IdUtilTest;

import com.xiaoTools.util.IdUtil.IdUtil;
import com.xiaoTools.util.IdUtil.snowflake.Snowflake;

import org.junit.Test;

public class IdUtilTest {

	@Test
	public void test_UUID(){
		// simpleUUID --> 2126330ed4bd4e5aa09085cacc27575f
		System.out.println( "simpleUUID --> " + IdUtil.simpleUUID());
		// fastUUID --> d52a62c9-2f88-4c1f-8989-115985b00c58
		System.out.println( "fastUUID --> " + IdUtil.fastUUID());
		// randomUUID --> 80dee6b8-f0f4-4ed4-adb0-d5692104aefe
		System.out.println( "randomUUID --> " + IdUtil.randomUUID());
		// fastSimpleUUID --> accf9816b58a413caf851b58427fcd02
		System.out.println( "fastSimpleUUID --> " + IdUtil.fastSimpleUUID());

	}

	@Test
	public void test_objectId(){
		// objectId --> 61861d37-508ef630-2a5f8662
		System.out.println( "objectId --> " + IdUtil.objectId());
	}

	@Test
	public void test_(){
		Snowflake a = IdUtil.createSnowflakeId(11, 1);
		// SnowflakeId --> 654365844147933184
		System.out.println( "SnowflakeId --> " + a.nextId());
	}

}
