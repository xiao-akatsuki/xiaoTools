package com.xiaoTools.core.collection.transSpliterator;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

public class TransSpliterator<F, T> implements Spliterator<T> {

	private final Spliterator<F> fromSpliterator;
	private final Function<? super F, ? extends T> function;

	public TransSpliterator(Spliterator<F> fromSpliterator, Function<? super F, ? extends T> function) {
		this.fromSpliterator = fromSpliterator;
		this.function = function;
	}

	@Override
	public boolean tryAdvance(Consumer<? super T> action) {
		return fromSpliterator.tryAdvance(
				fromElement -> action.accept(function.apply(fromElement)));
	}

	@Override
	public void forEachRemaining(Consumer<? super T> action) {
		fromSpliterator.forEachRemaining(fromElement -> action.accept(function.apply(fromElement)));
	}

	@Override
	public Spliterator<T> trySplit() {
		Spliterator<F> fromSplit = fromSpliterator.trySplit();
		return (fromSplit != null) ? new TransSpliterator<>(fromSplit, function) : null;
	}

	@Override
	public long estimateSize() {
		return fromSpliterator.estimateSize();
	}

	@Override
	public int characteristics() {
		return fromSpliterator.characteristics()
				& ~(Spliterator.DISTINCT | Spliterator.NONNULL | Spliterator.SORTED);
	}

}
