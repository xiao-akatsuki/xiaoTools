package com.xiaoTools.core.eval.operator;

/**
 * [封装每一个操作符以及其索引](Encapsulate each operator and its index)
 * @description zh - 封装每一个操作符以及其索引
 * @description en - Encapsulate each operator and its index
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-19 20:25:15
 */
public class Operator {

	/**
	 * [操作符](Operator)
	 * @description zh - 操作符
	 * @description en - Operator
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-19 20:25:47
	 */
	char oper;

	/**
	 * [索引](Indexes)
	 * @description zh - 索引
	 * @description en - Indexes
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-19 20:26:18
	 */
	int index;

	public Operator() {

	}
	public Operator(char oper, int index) {
		this.oper = oper;
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public char getOper() {
		return oper;
	}
}
