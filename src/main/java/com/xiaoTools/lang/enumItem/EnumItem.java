package com.xiaoTools.lang.enumItem;

import java.io.Serializable;

public interface EnumItem<E extends EnumItem<E>> extends Serializable {

	String name();

	default String text() {
		return name();
	}

	int intVal();

	/**
	 * [获取所有枚举对象](Get all enumerated objects)
	 * @description zh - 获取所有枚举对象
	 * @description en - Get all enumerated objects
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 08:22:59
	 * @return E[]
	 */
	@SuppressWarnings("unchecked")
	default E[] items() {
		return (E[]) this.getClass().getEnumConstants();
	}

	/**
	 * [通过int类型值查找其他枚举](Find other enumerations by int type values)
	 * @description zh - 通过int类型值查找其他枚举
	 * @description en - Find other enumerations by int type values
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 08:22:14
	 * @param intVal int值
	 * @return E
	 */
	default E fromInt(Integer intVal) {
		if (intVal == null) {
			return null;
		}
		E[] vs = items();
		for (E enumItem : vs) {
			if (enumItem.intVal() == intVal) {
				return enumItem;
			}
		}
		return null;
	}

	/**
	 * [通过String类型的值转换](Value conversion through string type)
	 * @description zh - 通过String类型的值转换
	 * @description en - Value conversion through string type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 08:21:42
	 * @param strVal String值
	 * @return E
	 */
	default E fromStr(String strVal) {
		if (strVal == null) {
			return null;
		}
		E[] vs = items();
		for (E enumItem : vs) {
			if (strVal.equalsIgnoreCase(enumItem.name())) {
				return enumItem;
			}
		}
		return null;
	}

}
