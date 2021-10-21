package com.xiaoTools.core.collection.copiedIter;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.xiaoTools.util.collUtil.CollUtil;

/**
 * [复制Iterator](Copy iterator)
 * @description zh - 复制Iterator
 * @description en - Copy iterator
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-21 18:07:28
 */
public class CopiedIter<E> implements Iterator<E>, Iterable<E>, Serializable {
	private static final long serialVersionUID = 1L;

	private final Iterator<E> listIterator;

	public static <V> CopiedIter<V> copyOf(Iterator<V> iterator){
		return new CopiedIter<>(iterator);
	}

	public CopiedIter(Iterator<E> iterator) {
		final List<E> eleList = CollUtil.newArrayList(iterator);
		this.listIterator = eleList.iterator();
	}

	@Override
	public boolean hasNext() {
		return this.listIterator.hasNext();
	}

	@Override
	public E next() {
		return this.listIterator.next();
	}

	@Override
	public void remove() throws UnsupportedOperationException{
		throw new UnsupportedOperationException("This is a read-only iterator.");
	}

	@Override
	public Iterator<E> iterator() {
		return this;
	}
}
