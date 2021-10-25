package com.xiaoTools.entity.dynaBean;


import java.io.Serializable;
import java.util.Map;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.core.exception.beanException.BeanException;
import com.xiaoTools.entity.propDesc.PropDesc;
import com.xiaoTools.lang.clone.cLoneSupport.CloneSupport;
import com.xiaoTools.util.beanUtil.BeanUtil;
import com.xiaoTools.util.classUtil.ClassUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;

public class DynaBean extends CloneSupport<DynaBean> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Class<?> beanClass;
	private final Object bean;

	public DynaBean(Class<?> beanClass, Object... params) {
		this(ReflectUtil.newInstance(beanClass, params));
	}

	public DynaBean(Class<?> beanClass) {
		this(ReflectUtil.newInstance(beanClass));
	}

	public DynaBean(Object bean) {
		Assertion.notNull(bean);
		if (bean instanceof DynaBean) {
			bean = ((DynaBean) bean).getBean();
		}
		this.bean = bean;
		this.beanClass = ClassUtil.getClass(bean);
	}

	/**
	 * [创建一个DynaBean](Create a dynabean)
	 * @description zh - 创建一个DynaBean
	 * @description en - Create a dynabean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 18:25:11
	 * @param bean 实体类
	 * @return com.xiaoTools.entity.dynaBean.DynaBean
	 */
	public static DynaBean create(Object bean) {
		return new DynaBean(bean);
	}

	/**
	 * [创建一个DynaBean](Create a dynabean)
	 * @description zh - 创建一个DynaBean
	 * @description en - Create a dynabean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 18:26:02
	 * @param beanClass bean的实体类
	 * @return com.xiaoTools.entity.dynaBean.DynaBean
	 */
	public static DynaBean create(Class<?> beanClass) {
		return new DynaBean(beanClass);
	}

	/**
	 * [创建一个DynaBean](Create a dynabean)
	 * @description zh - 创建一个DynaBean
	 * @description en - Create a dynabean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 18:27:43
	 * @param beanClass Bean类
	 * @param params 构造Bean所需要的参数
	 * @return com.xiaoTools.entity.dynaBean.DynaBean
	 */
	public static DynaBean create(Class<?> beanClass, Object... params) {
		return new DynaBean(beanClass, params);
	}

	/**
	 * [获得字段对应值](Get the corresponding value of the field)
	 * @description zh - 获得字段对应值
	 * @description en - Get the corresponding value of the field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 18:29:45
	 * @param fieldName 字段名
	 * @throws
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String fieldName) throws BeanException {
		if (Map.class.isAssignableFrom(beanClass)) {
			return (T) ((Map<?, ?>) bean).get(fieldName);
		} else {
			final PropDesc prop = BeanUtil.getBeanDesc(beanClass).getProp(fieldName);
			if (null == prop) {
				throw new BeanException("No public field or get method for {}", fieldName);
			}
			return (T) prop.getValue(bean);
		}
	}

	/**
	 * [检查是否有指定名称的bean属性](Checks whether there is a bean property with the specified name)
	 * @description zh - 检查是否有指定名称的bean属性
	 * @description en - Checks whether there is a bean property with the specified name
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 19:21:14
	 * @param fieldName 字段名
	 * @return boolean
	 */
	public boolean containsProp(String fieldName) {
		return null != BeanUtil.getBeanDesc(beanClass).getProp(fieldName);
	}

	/**
	 * [获得字段对应值](Get the corresponding value of the field)
	 * @description zh - 获得字段对应值
	 * @description en - Get the corresponding value of the field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 19:22:08
	 * @param fieldName 字段名
	 * @return T
	 */
	public <T> T safeGet(String fieldName) {
		try {
			return get(fieldName);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * [设置字段值](Set field value)
	 * @description zh - 设置字段值
	 * @description en - Set field value
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 19:23:22
	 * @param fieldName 字段名
	 * @param value 值
	 * @throws com.xiaoTools.core.exception.beanException.BeanException
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void set(String fieldName, Object value) throws BeanException {
		if (Map.class.isAssignableFrom(beanClass)) {
			((Map) bean).put(fieldName, value);
		} else {
			final PropDesc prop = BeanUtil.getBeanDesc(beanClass).getProp(fieldName);
			if (null == prop) {
				throw new BeanException("No public field or set method for {}", fieldName);
			}
			prop.setValue(bean, value);
		}
	}

	/**
	 * [获得原始Bean](Get the original bean)
	 * @description zh - 获得原始Bean
	 * @description en - Get the original bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 19:24:36
	 * @param methodName 方法名
	 * @param params 参数
	 * @return java.lang.Object
	 */
	public Object invoke(String methodName, Object... params) {
		return ReflectUtil.invoke(this.bean, methodName, params);
	}

	/**
	 * [获得原始Bean](Get the original bean)
	 * @description zh - 获得原始Bean
	 * @description en - Get the original bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 19:25:43
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public <T> T getBean() {
		return (T) this.bean;
	}

	/**
	 * [获得Bean的类型](Get the type of bean)
	 * @description zh - 获得Bean的类型
	 * @description en - Get the type of bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 19:26:19
	 * @return java.lang.Class<T>
	 */
	@SuppressWarnings("unchecked")
	public <T> Class<T> getBeanClass() {
		return (Class<T>) this.beanClass;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bean == null) ? 0 : bean.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DynaBean other = (DynaBean) obj;
		if (bean == null) {
			return other.bean == null;
		} else return bean.equals(other.bean);
	}

	@Override
	public String toString() {
		return this.bean.toString();
	}

}
