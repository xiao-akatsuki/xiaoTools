package com.xiaoTools.util.annotationUtil;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import com.xiaoTools.annotation.combinationAnnotationElement.CombinationAnnotationElement;
import com.xiaoTools.core.exception.utilException.UtilException;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;

/**
 * [注解工具类](Annotation tool class)
 * @description zh - 注解工具类
 * @description en - Annotation tool class
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-28 21:25:07
 */
public class AnnotationUtil {

	/**
	 * [将指定的被注解的元素转换为组合注解元素](Converts the specified annotated element to a composite annotation element)
	 * @description zh - 将指定的被注解的元素转换为组合注解元素
	 * @description en - Converts the specified annotated element to a composite annotation element
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 21:39:25
	 * @param com.xiaoTools.annotation.combinationAnnotationElement.CombinationAnnotationElement
	 */
	public static CombinationAnnotationElement toCombination(AnnotatedElement annotationEle) {
		return annotationEle instanceof CombinationAnnotationElement ?
			(CombinationAnnotationElement) annotationEle :
				new CombinationAnnotationElement(annotationEle);
	}

	/**
	 * [获取指定注解](Gets the specified annotation)
	 * @description zh - 获取指定注解
	 * @description en - Gets the specified annotation
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 21:40:55
	 * @param annotationEle AnnotatedElement
	 * @param isToCombination 是否为转换为组合注解
	 * @return java.lang.annotation.Annotation[]
	 */
	public static Annotation[] getAnnotations(AnnotatedElement annotationEle, boolean isToCombination) {
		return (null == annotationEle) ? null :
			(isToCombination ? toCombination(annotationEle) :
			annotationEle).getAnnotations();
	}

	/**
	 * [检查是否包含指定注解指定注解](Check whether the specified annotation is included)
	 * @description zh - 检查是否包含指定注解指定注解
	 * @description en - Check whether the specified annotation is included
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 22:04:52
	 * @param annotationEle AnnotatedElement
	 * @param annotationType 注解类型
	 * @return boolean
	 */
	public static boolean hasAnnotation(AnnotatedElement annotationEle, Class<? extends Annotation> annotationType) {
		return null != getAnnotation(annotationEle, annotationType);
	}

	/**
	 * [获取指定注解](Gets the specified annotation)
	 * @description zh - 获取指定注解
	 * @description en - Gets the specified annotation
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 21:41:57
	 * @param annotationEle AnnotatedElement
	 * @param annotationType 注解类型
	 * @return A
	 */
	public static <A extends Annotation> A getAnnotation(AnnotatedElement annotationEle, Class<A> annotationType) {
		return (null == annotationEle) ? null : toCombination(annotationEle).getAnnotation(annotationType);
	}

	/**
	 * [获取指定注解默认值](Gets the default value of the specified annotation)
	 * @description zh - 获取指定注解默认值
	 * @description en - Gets the default value of the specified annotation
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 21:43:42
	 * @param annotationEle AnnotatedElement
	 * @param annotationType 注解类型
	 * @return T
	 */
	public static <T> T getAnnotationValue(AnnotatedElement annotationEle, Class<? extends Annotation> annotationType) throws UtilException {
		return getAnnotationValue(annotationEle, annotationType, "value");
	}

	/**
	 * [获取指定注解属性的值](Gets the value of the specified annotation property)
	 * @description zh - 获取指定注解属性的值
	 * @description en - Gets the value of the specified annotation property
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 21:45:36
	 * @param annotationEle AnnotatedElement
	 * @param annotationType 注解类型
	 * @param propertyName 属性名
	 * @return T
	 */
	public static <T> T getAnnotationValue(AnnotatedElement annotationEle, Class<? extends Annotation> annotationType, String propertyName) throws UtilException {
		final Annotation annotation = getAnnotation(annotationEle, annotationType);
		if (null == annotation) {return null;}
		final Method method = ReflectUtil.getMethodOfObj(annotation, propertyName);
		return null == method ? null : ReflectUtil.invoke(annotation, method);
	}

	/**
	 * [获取指定注解中所有属性值](Gets all attribute values in the specified annotation)
	 * @description zh - 获取指定注解中所有属性值
	 * @description en - Gets all attribute values in the specified annotation
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 21:49:03
	 * @param annotationEle AnnotatedElement
	 * @param annotationType 注解类型
	 * @return java.util.Map<java.lang.String, java.lang.Object>
	 */
	public static Map<String, Object> getAnnotationValueMap(AnnotatedElement annotationEle, Class<? extends Annotation> annotationType) throws UtilException {
		final Annotation annotation = getAnnotation(annotationEle, annotationType);
		if (null == annotation) {
			return null;
		}

		final Method[] methods = ReflectUtil.getMethods(annotationType, t -> {
			if (ArrayUtil.isEmpty(t.getParameterTypes())) {
				final String name = t.getName();
				return (Constant.FALSE == "hashCode".equals(name))
						&& (Constant.FALSE == "toString".equals(name))
						&& (Constant.FALSE == "annotationType".equals(name));
			}
			return Constant.FALSE;
		});

		final HashMap<String, Object> result = new HashMap<>(methods.length, Constant.ONE);
		for (Method method : methods) {
			result.put(method.getName(), ReflectUtil.invoke(annotation, method));
		}
		return result;
	}

	/**
	 * [获取注解类的保留时间](Gets the retention time of the annotation class)
	 * @description zh - 获取注解类的保留时间
	 * @description en - Gets the retention time of the annotation class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 21:59:25
	 * @param annotationType 注解类型
	 * @return java.lang.annotation.RetentionPolicy
	 */
	public static RetentionPolicy getRetentionPolicy(Class<? extends Annotation> annotationType) {
		final Retention retention = annotationType.getAnnotation(Retention.class);
		return null == retention ? RetentionPolicy.CLASS : retention.value();
	}

	/**
	 * [获取注解类可以用来修饰哪些程序元素](Gets which program elements the annotation class can be used to modify)
	 * @description zh - 获取注解类可以用来修饰哪些程序元素
	 * @description en - Gets which program elements the annotation class can be used to modify
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 22:00:38
	 * @param annotationType 注解类型
	 * @return java.lang.annotation.ElementType[]
	 */
	public static ElementType[] getTargetType(Class<? extends Annotation> annotationType) {
		final Target target = annotationType.getAnnotation(Target.class);
		if (null == target) {
			return new ElementType[]{ElementType.TYPE,
					ElementType.FIELD,
					ElementType.METHOD,
					ElementType.PARAMETER,
					ElementType.CONSTRUCTOR,
					ElementType.LOCAL_VARIABLE,
					ElementType.ANNOTATION_TYPE,
					ElementType.PACKAGE
			};
		}
		return target.value();
	}

	/**
	 * [是否会保存到 Javadoc 文档中](Will it be saved to the Javadoc document)
	 * @description zh - 是否会保存到 Javadoc 文档中
	 * @description en - Will it be saved to the Javadoc document
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 22:01:58
	 * @param annotationType 注解类型
	 * @return boolean
	 */
	public static boolean isDocumented(Class<? extends Annotation> annotationType) {
		return annotationType.isAnnotationPresent(Documented.class);
	}

	/**
	 * [是否可以被继承](Can it be inherited)
	 * @description zh - 是否可以被继承
	 * @description en - Can it be inherited
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 22:02:50
	 * @param annotationType 注解类型
	 * @return boolean
	 */
	public static boolean isInherited(Class<? extends Annotation> annotationType) {
		return annotationType.isAnnotationPresent(Inherited.class);
	}

	/**
	 * [设置新的注解的属性（字段）值](Set the attribute (field) value of the new annotation)
	 * @description zh - 设置新的注解的属性（字段）值
	 * @description en - Set the attribute (field) value of the new annotation
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 22:03:22
	 * @param annotation 注解对象
	 * @param annotationField 注解名称
	 * @param value 要更新的属性值
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static void setValue(Annotation annotation, String annotationField, Object value) {
		final Map memberValues = (Map) ReflectUtil.getFieldValue(Proxy.getInvocationHandler(annotation), "memberValues");
		memberValues.put(annotationField, value);
	}
}
