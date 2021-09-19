package com.xiaoTools.core.collection.transIter;

import java.util.Iterator;
import java.util.function.Function;

import com.xiaoTools.assertion.Assertion;

/** 
 * [使用给定的转换函数](Use the given conversion function)
 * @description zh - 使用给定的转换函数
 * @description en - Use the given conversion function
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-09-19 22:01:14
 */
public class TransIter<F, T> implements Iterator<T> {

	private final Iterator<? extends F> backingIterator;
	private final Function<? super F, ? extends T> func;

	public TransIter(Iterator<? extends F> backingIterator, Function<? super F, ? extends T> func) {
		this.backingIterator = Assertion.notNull(backingIterator);
		this.func = Assertion.notNull(func);
	}

	@Override
	public final boolean hasNext() {
		return backingIterator.hasNext();
	}

	@Override
	public final T next() {
		return func.apply(backingIterator.next());
	}

	@Override
	public final void remove() {
		backingIterator.remove();
	}
}
