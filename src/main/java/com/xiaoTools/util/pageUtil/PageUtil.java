package com.xiaoTools.util.pageUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.lang.segment.Segment;
import com.xiaoTools.lang.segment.defaultSegment.DefaultSegment;

/**
 *[分页工具类](Paging tool class)
 * @description zh - 分页工具类
 * @description en - Paging tool class
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-18 07:39:45
 */
public class PageUtil {
	private static int firstPageNo = 0;

	/**
	 * [获得首页的页码](Get the page number of the first page)
	 * @description zh - 获得首页的页码
	 * @description en - Get the page number of the first page
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 07:41:51
	 * @return int
	 */
	public static int getFirstPageNo() {
		return firstPageNo;
	}

	/**
	 * [设置首页的页码](Set the page number of the first page)
	 * @description zh - 设置首页的页码
	 * @description en - Set the page number of the first page
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 07:42:45
	 * @param customFirstPageNo 自定义的首页页码
	 */
	public static void setFirstPageNo(int customFirstPageNo) {
		firstPageNo = customFirstPageNo;
	}

	/**
	 * [设置首页页码为1](Set the first page number to 1)
	 * @description zh - 设置首页页码为1
	 * @description en - Set the first page number to 1
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 07:44:24
	 */
	public static void setOneAsFirstPageNo() {
		setFirstPageNo(1);
	}

	/**
	 * [将页数和每页条目数转换为开始位置](Convert the number of pages and entries per page to the start position)
	 * @description zh - 将页数和每页条目数转换为开始位置
	 * @description en - Convert the number of pages and entries per page to the start position
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 07:45:49
	 * @param current 页码
	 * @param size 每页条目数
	 * @return int
	 */
	public static int getStart(int current, int size) {
		if (current < firstPageNo) {
			current = firstPageNo;
		}

		if (size < Constant.ONE) {
			size = Constant.ZERO;
		}

		return (current - firstPageNo) * size;
	}

	/**
	 * [将页数和每页条目数转换为结束位置](Convert the number of pages and entries per page to the end position)
	 * @description zh - 将页数和每页条目数转换为结束位置
	 * @description en - Convert the number of pages and entries per page to the end position
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 07:49:48
	 * @param current 页码
	 * @param size 每页条目数
	 * @return int
	 */
	public static int getEnd(int current, int size) {
		final int start = getStart(current, size);
		return getEndByStart(start, size);
	}

	/**
	 * [将页数和每页条目数转换为开始位置和结束位置](Convert the number of pages and entries per page to the start and end positions)
	 * @description zh - 将页数和每页条目数转换为开始位置和结束位置
	 * @description en - Convert the number of pages and entries per page to the start and end positions
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 07:52:53
	 * @param current 页码
	 * @param size 每页条目数
	 * @return int[]
	 */
	public static int[] transToStartEnd(int current, int size) {
		final int start = getStart(current, size);
		return new int[]{start, getEndByStart(start, size)};
	}

	/**
	 * [将页数和每页条目数转换为开始位置和结束位置](Convert the number of pages and entries per page to the start and end positions)
	 * @description zh - 将页数和每页条目数转换为开始位置和结束位置
	 * @description en - Convert the number of pages and entries per page to the start and end positions
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 07:57:11
	 * @param current 页码
	 * @param size 每页条目数
	 */
	public static Segment<Integer> toSegment(int current, int size) {
		final int[] startEnd = transToStartEnd(current, size);
		return new DefaultSegment<>(startEnd[0], startEnd[1]);
	}

	/**
	 * [根据总数计算总页数](Calculate the total number of pages according to the total number)
	 * @description zh - 根据总数计算总页数
	 * @description en - Calculate the total number of pages according to the total number
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 07:58:54
	 * @param count 总数
	 * @param size 页数
	 * @return int
	 */
	public static int totalPage(int count, int size) {
		if (size == 0) {
			return 0;
		}
		return count % size == 0 ? (count / size) : (count / size + 1);
	}

	/**
	 * [分页彩虹算法](Paging rainbow algorithm)
	 * @description zh - 分页彩虹算法
	 * @description en - Paging rainbow algorithm
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 08:02:11
	 * @param current 当前页
	 * @param totalPage 总页数
	 * @param displayCount 每屏展示的页数
	 * @return int[]
	 */
	public static int[] rainbow(int current, int totalPage, int displayCount) {
		// displayCount % 2
		boolean isEven = (displayCount & 1) == 0;
		int left = displayCount >> 1;
		int right = displayCount >> 1;

		int length = displayCount;
		if (isEven) {
			right++;
		}
		if (totalPage < displayCount) {
			length = totalPage;
		}
		int[] result = new int[length];
		if (totalPage >= displayCount) {
			if (current <= left) {
				for (int i = 0; i < result.length; i++) {
					result[i] = i + 1;
				}
			} else if (current > totalPage - right) {
				for (int i = 0; i < result.length; i++) {
					result[i] = i + totalPage - displayCount + 1;
				}
			} else {
				for (int i = 0; i < result.length; i++) {
					result[i] = i + current - left + (isEven ? 1 : 0);
				}
			}
		} else {
			for (int i = 0; i < result.length; i++) {
				result[i] = i + 1;
			}
		}
		return result;

	}

	/**
	 * [分页彩虹算法](Paging rainbow algorithm)
	 * @description zh - 分页彩虹算法
	 * @description en - Paging rainbow algorithm
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 08:03:34
	 * @param currentPage 当前页
	 * @param totalPage 总页数
	 * @return int[]
	 */
	public static int[] rainbow(int currentPage, int totalPage) {
		return rainbow(currentPage, totalPage, 10);
	}

	/**
	 *
	 * @description zh - 根据起始位置获取结束位置
	 * @description en - Get the end position according to the start position
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 08:04:42
	 * @param start 起始位置
	 * @param size 每页条目数
	 * @return int
	 */
	private static int getEndByStart(int start, int size) {
		if (size < 1) {
			size = 0;
		}
		return start + size;
	}
}
