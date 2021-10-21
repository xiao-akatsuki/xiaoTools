package com.xiaoTools.util.spliteratorUtil;

import java.util.Spliterator;
import java.util.function.Function;

import com.xiaoTools.core.collection.transSpliterator.TransSpliterator;

/**
 * [Spliterator相关工具类](Splitter related tool classes)
 * @description zh - Spliterator相关工具类
 * @description en - Splitter related tool classes
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-21 12:45:27
 */
public class SpliteratorUtil {

	/**
	 * 使用给定的转换函数，转换源{@link Spliterator}为新类型的{@link Spliterator}
	 *
	 * @param <F> 源元素类型
	 * @param <T> 目标元素类型
	 * @param fromSpliterator 源{@link Spliterator}
	 * @param function 转换函数
	 * @return 新类型的{@link Spliterator}
	 */
	/**
	 * [使用给定的转换函数](Use the given conversion function)
	 * @description zh - 使用给定的转换函数
	 * @description en - Use the given conversion function
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 12:44:41
	 * @param fromSpliterator 源
	 * @param function 转换函数
	 * @return java.util.Spliterator<T>
	 */
	public static <F, T> Spliterator<T> trans(Spliterator<F> fromSpliterator, Function<? super F, ? extends T> function) {
		return new TransSpliterator<>(fromSpliterator, function);
	}

}
