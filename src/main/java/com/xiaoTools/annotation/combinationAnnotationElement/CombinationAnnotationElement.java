package com.xiaoTools.annotation.combinationAnnotationElement;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.collUtil.CollUtil;

public class CombinationAnnotationElement implements AnnotatedElement, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 元注解
	 */
	private static final Set<Class<? extends Annotation>> META_ANNOTATIONS = CollUtil.newHashSet(
			Target.class,
			Retention.class,
			Inherited.class,
			Documented.class,
			SuppressWarnings.class,
			Override.class,
			Deprecated.class
	);

	/**
	 * 注解类型与注解对象对应表
	 */
	private Map<Class<? extends Annotation>, Annotation> annotationMap;

	/**
	 * 直接注解类型与注解对象对应表
	 */
	private Map<Class<? extends Annotation>, Annotation> declaredAnnotationMap;

	public CombinationAnnotationElement(AnnotatedElement element) {
		init(element);
	}

	/**
	 * [初始化](initialization)
	 * @description zh - 初始化
	 * @description en - initialization
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 21:33:43
	 * @param element 元素
	 */
	private void init(AnnotatedElement element) {
		final Annotation[] declaredAnnotations = element.getDeclaredAnnotations();
		this.declaredAnnotationMap = new HashMap<>();
		parseDeclared(declaredAnnotations);

		final Annotation[] annotations = element.getAnnotations();
		if(Arrays.equals(declaredAnnotations, annotations)) {
			this.annotationMap = this.declaredAnnotationMap;
		}else {
			this.annotationMap = new HashMap<>();
			parse(annotations);
		}
	}

	/**
	 * [进行递归解析注解](Recursive parsing annotation)
	 * @description zh - 进行递归解析注解
	 * @description en - Recursive parsing annotation
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 21:35:37
	 * @param annotations Class, Method, Field等
	 */
	private void parseDeclared(Annotation[] annotations) {
		Class<? extends Annotation> annotationType;
		for (Annotation annotation : annotations) {
			annotationType = annotation.annotationType();
			if (Constant.FALSE == META_ANNOTATIONS.contains(annotationType)) {
				declaredAnnotationMap.put(annotationType, annotation);
				parseDeclared(annotationType.getDeclaredAnnotations());
			}
		}
	}

	/**
	 * [进行递归解析注解](Recursive parsing annotation)
	 * @description zh - 进行递归解析注解
	 * @description en - Recursive parsing annotation
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 21:37:41
	 * @param annotations Class, Method, Field等
	 */
	private void parse(Annotation[] annotations) {
		Class<? extends Annotation> annotationType;
		for (Annotation annotation : annotations) {
			annotationType = annotation.annotationType();
			if (Constant.FALSE == META_ANNOTATIONS.contains(annotationType)) {
				annotationMap.put(annotationType, annotation);
				parse(annotationType.getAnnotations());
			}
		}
	}

	@Override
	public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
		return annotationMap.containsKey(annotationClass);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
		Annotation annotation = annotationMap.get(annotationClass);
		return (annotation == null) ? null : (T) annotation;
	}

	@Override
	public Annotation[] getAnnotations() {
		final Collection<Annotation> annotations = this.annotationMap.values();
		return annotations.toArray(new Annotation[Constant.ZERO]);
	}

	@Override
	public Annotation[] getDeclaredAnnotations() {
		final Collection<Annotation> annotations = this.declaredAnnotationMap.values();
		return annotations.toArray(new Annotation[Constant.ZERO]);
	}

}
