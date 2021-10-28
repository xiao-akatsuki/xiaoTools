package com.xiaoTools.util.modifierUtil.modifierType;

import java.lang.reflect.Modifier;

/**
 * [修饰符枚举](Modifier enumeration)
 * @description zh - 修饰符枚举
 * @description en - Modifier enumeration
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-28 09:28:27
 */
public enum ModifierType {

	/**
	 * public修饰符，所有类都能访问
	 */
	PUBLIC(Modifier.PUBLIC),

	/**
	 * private修饰符，只能被自己访问和修改
	 */
	PRIVATE(Modifier.PRIVATE),

	/**
	 * protected修饰符，自身、子类及同一个包中类可以访问
	 */
	PROTECTED(Modifier.PROTECTED),

	/**
	 * static修饰符，（静态修饰符）指定变量被所有对象共享，即所有实例都可以使用该变量。变量属于这个类
	 */
	STATIC(Modifier.STATIC),

	/**
	 * final修饰符，最终修饰符，指定此变量的值不能变，使用在方法上表示不能被重载
	 */
	FINAL(Modifier.FINAL),

	/**
	 * synchronized，同步修饰符，在多个线程中，该修饰符用于在运行前，对他所属的方法加锁，以防止其他线程的访问，运行结束后解锁。
	 */
	SYNCHRONIZED(Modifier.SYNCHRONIZED),

	/**
	 * （易失修饰符）指定该变量可以同时被几个线程控制和修改
	 */
	VOLATILE(Modifier.VOLATILE),

	/**
	 * （过度修饰符）指定该变量是系统保留，暂无特别作用的临时性变量，序列化时忽略
	 */
	TRANSIENT(Modifier.TRANSIENT),

	/**
	 * native，本地修饰符。指定此方法的方法体是用其他语言在程序外部编写的。
	 */
	NATIVE(Modifier.NATIVE),

	/**
	 * abstract，将一个类声明为抽象类，没有实现的方法，需要子类提供方法实现。
	 */
	ABSTRACT(Modifier.ABSTRACT),

	/**
	 * strictfp，一旦使用了关键字strictfp来声明某个类、接口或者方法时，那么在这个关键字所声明的范围内所有浮点运算都是精确的，符合IEEE-754规范的。
	 */
	STRICT(Modifier.STRICT);

	/**
	 * 修饰符枚举对应的int修饰符值
	 */
	private final int value;

	ModifierType(int modifier) {
		this.value = modifier;
	}

	/**
	 * [获取修饰符枚举对应的int修饰符值](Gets the int modifier value corresponding to the modifier enumeration)
	 * @description zh - 获取修饰符枚举对应的int修饰符值
	 * @description en - Gets the int modifier value corresponding to the modifier enumeration
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:30:51
	 * @return int
	 */
	public int getValue() {
		return this.value;
	}
}
