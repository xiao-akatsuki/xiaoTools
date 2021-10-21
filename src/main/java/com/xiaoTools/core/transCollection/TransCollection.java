package com.xiaoTools.core.transCollection;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.util.iterUtil.IterUtil;
import com.xiaoTools.util.spliteratorUtil.SpliteratorUtil;

public class TransCollection<F, T> extends AbstractCollection<T> {

	private final Collection<F> fromCollection;
	private final Function<? super F, ? extends T> function;

	public TransCollection(Collection<F> fromCollection, Function<? super F, ? extends T> function) {
		this.fromCollection = Assertion.notNull(fromCollection);
		this.function = Assertion.notNull(function);
	}

	@Override
	public Iterator<T> iterator() {
		return IterUtil.trans(fromCollection.iterator(), function);
	}

	@Override
	public void clear() {
		fromCollection.clear();
	}

	@Override
	public boolean isEmpty() {
		return fromCollection.isEmpty();
	}

	@Override
	public void forEach(Consumer<? super T> action) {
		Assertion.notNull(action);
		fromCollection.forEach((f) -> action.accept(function.apply(f)));
	}

	@Override
	public boolean removeIf(Predicate<? super T> filter) {
		Assertion.notNull(filter);
		return fromCollection.removeIf(element -> filter.test(function.apply(element)));
	}

	@Override
	public Spliterator<T> spliterator() {
		return SpliteratorUtil.trans(fromCollection.spliterator(), function);
	}

	@Override
	public int size() {
		return fromCollection.size();
	}

}
