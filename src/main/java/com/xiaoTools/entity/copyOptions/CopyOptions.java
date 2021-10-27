package com.xiaoTools.entity.copyOptions;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.function.BiPredicate;

import com.xiaoTools.core.editor.Editor;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.mapUtil.MapUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;

/**
 * [属性拷贝选项](Attribute copy options)
 * @description zh - 属性拷贝选项
 * @description en - Attribute copy options
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-27 09:15:16
 */
public class CopyOptions implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 限制的类或接口，必须为目标对象的实现接口或父类，用于限制拷贝的属性，例如一个类我只想复制其父类的一些属性，就可以将editable设置为父类
	 */
	public Class<?> editable;

	/**
	 * 是否忽略空值，当源对象的值为null时，true: 忽略而不注入此值，false: 注入null
	 */
	public boolean ignoreNullValue;

	/**
	 * 属性过滤器，断言通过的属性才会被复制
	 */
	public BiPredicate<Field, Object> propertiesFilter;

	/**
	 * 忽略的目标对象中属性列表，设置一个属性列表，不拷贝这些属性值
	 */
	public String[] ignoreProperties;

	/**
	 * 是否忽略字段注入错误
	 */
	public boolean ignoreError;

	/**
	 * 是否忽略字段大小写
	 */
	public boolean ignoreCase;

	/**
	 * 拷贝属性的字段映射，用于不同的属性之前拷贝做对应表用
	 */
	public Map<String, String> fieldMapping;

	/**
	 * 反向映射表，自动生成用于反向查找
	 */
	public Map<String, String> reversedFieldMapping;

	/**
	 * 字段属性编辑器，用于自定义属性转换规则，例如驼峰转下划线等
	 */
	public Editor<String> fieldNameEditor;

	/**
	 * 是否支持transient关键字修饰和@Transient注解，如果支持，被修饰的字段或方法对应的字段将被忽略。
	 */
	private boolean transientSupport = Constant.TRUE;

	/**
	 * [创建拷贝选项](Create copy options)
	 * @description zh - 创建拷贝选项
	 * @description en - Create copy options
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:19:32
	 * @return com.xiaoTools.entity.copyOptions.CopyOptions
	 */
	public static CopyOptions create() {
		return new CopyOptions();
	}

	/**
	 * [创建拷贝选项](Create copy options)
	 * @description zh - 创建拷贝选项
	 * @description en - Create copy options
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:20:03
	 * @param editable 限制的类或接口，必须为目标对象的实现接口或父类，用于限制拷贝的属性
	 * @param ignoreNullValue  是否忽略空值，当源对象的值为null时，true: 忽略而不注入此值，false: 注入null
	 * @param ignoreProperties 忽略的属性列表，设置一个属性列表，不拷贝这些属性值
	 * @return com.xiaoTools.entity.copyOptions.CopyOptions
	 */
	public static CopyOptions create(Class<?> editable, boolean ignoreNullValue, String... ignoreProperties) {
		return new CopyOptions(editable, ignoreNullValue, ignoreProperties);
	}

	public CopyOptions() {
	}

	/**
	 * [创建拷贝选项](Create copy options)
	 * @description zh - 创建拷贝选项
	 * @description en - Create copy options
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:24:53
	 * @param editable 限制的类或接口，必须为目标对象的实现接口或父类，用于限制拷贝的属性
	 * @param ignoreNullValue  是否忽略空值，当源对象的值为null时，true: 忽略而不注入此值，false: 注入null
	 * @param ignoreProperties 忽略的属性列表，设置一个属性列表，不拷贝这些属性值
	 */
	public CopyOptions(Class<?> editable, boolean ignoreNullValue, String... ignoreProperties) {
		this.propertiesFilter = (f, v) -> true;
		this.editable = editable;
		this.ignoreNullValue = ignoreNullValue;
		this.ignoreProperties = ignoreProperties;
	}

	/**
	 * [设置限制的类或接口](Set restricted classes or interfaces)
	 * @description zh - 设置限制的类或接口
	 * @description en - Set restricted classes or interfaces
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:25:55
	 * @param editable 限制的类或接口
	 * @return com.xiaoTools.entity.copyOptions.CopyOptions
	 */
	public CopyOptions setEditable(Class<?> editable) {
		this.editable = editable;
		return this;
	}

	/**
	 * [设置是否忽略空值](Sets whether null values are ignored)
	 * @description zh - 设置是否忽略空值
	 * @description en - Sets whether null values are ignored
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:26:37
	 * @param ignoreNullVall 是否忽略空值
	 * @return com.xiaoTools.entity.copyOptions.CopyOptions
	 */
	public CopyOptions setIgnoreNullValue(boolean ignoreNullVall) {
		this.ignoreNullValue = ignoreNullVall;
		return this;
	}

	/**
	 * [设置忽略空值](Set ignore null)
	 * @description zh - 设置忽略空值
	 * @description en - Set ignore null
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:27:32
	 * @return com.xiaoTools.entity.copyOptions.CopyOptions
	 */
	public CopyOptions ignoreNullValue() {
		return setIgnoreNullValue(Constant.TRUE);
	}

	/**
	 * [属性过滤器](Attribute filter)
	 * @description zh - 属性过滤器
	 * @description en - Attribute filter
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:29:13
	 * @param propertiesFilter 属性过滤器
	 * @return com.xiaoTools.entity.copyOptions.CopyOptions
	 */
	public CopyOptions setPropertiesFilter(BiPredicate<Field, Object> propertiesFilter) {
		this.propertiesFilter = propertiesFilter;
		return this;
	}

	/**
	 * [设置忽略的目标对象中属性列表](Sets the list of properties in the ignored target object)
	 * @description zh - 设置忽略的目标对象中属性列表
	 * @description en - Sets the list of properties in the ignored target object
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:31:08
	 * @param ignoreProperties 忽略的目标对象中属性列表
	 * @return com.xiaoTools.entity.copyOptions.CopyOptions
	 */
	public CopyOptions setIgnoreProperties(String... ignoreProperties) {
		this.ignoreProperties = ignoreProperties;
		return this;
	}

	/**
	 * [设置是否忽略字段的注入错误](Sets whether to ignore injection errors for fields)
	 * @description zh - 设置是否忽略字段的注入错误
	 * @description en - Sets whether to ignore injection errors for fields
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:31:52
	 * @param ignoreError 是否忽略注入错误
	 * @return com.xiaoTools.entity.copyOptions.CopyOptions
	 */
	public CopyOptions setIgnoreError(boolean ignoreError) {
		this.ignoreError = ignoreError;
		return this;
	}

	/**
	 * [设置忽略字段的注入错误](Set ignore field injection error)
	 * @description zh - 设置忽略字段的注入错误
	 * @description en - Set ignore field injection error
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:32:57
	 * @return com.xiaoTools.entity.copyOptions.CopyOptions
	 */
	public CopyOptions ignoreError() {
		return setIgnoreError(Constant.TRUE);
	}

	/**
	 * [设置是否忽略字段的大小写](Sets whether to ignore the case of the field)
	 * @description zh - 设置是否忽略字段的大小写
	 * @description en - Sets whether to ignore the case of the field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:33:30
	 * @param ignoreCase 是否忽略大小写
	 * @return com.xiaoTools.entity.copyOptions.CopyOptions
	 */
	public CopyOptions setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
		return this;
	}

	/**
	 * [设置忽略字段的大小写](Sets the case of the ignored field)
	 * @description zh - 设置忽略字段的大小写
	 * @description en - Sets the case of the ignored field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:34:20
	 * @return com.xiaoTools.entity.copyOptions.CopyOptions
	 */
	public CopyOptions ignoreCase() {
		return setIgnoreCase(Constant.TRUE);
	}

	/**
	 * [设置拷贝属性的字段映射](Set field mapping for copy properties)
	 * @description zh - 设置拷贝属性的字段映射
	 * @description en - Set field mapping for copy properties
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:35:03
	 * @param fieldMapping 拷贝属性的字段映射
	 * @return com.xiaoTools.entity.copyOptions.CopyOptions
	 */
	public CopyOptions setFieldMapping(Map<String, String> fieldMapping) {
		this.fieldMapping = fieldMapping;
		return this;
	}

	/**
	 * [设置字段属性编辑器](Set Field Attribute Editor)
	 * @description zh - 设置字段属性编辑器
	 * @description en - Set Field Attribute Editor
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:35:40
	 * @param fieldNameEditor 字段属性编辑器
	 * @return com.xiaoTools.entity.copyOptions.CopyOptions
	*/
	public CopyOptions setFieldNameEditor(Editor<String> fieldNameEditor) {
		this.fieldNameEditor = fieldNameEditor;
		return this;
	}

	/**
	 * [是否支持transient关键字修饰和@Transient注解](Support transient keyword modification and @ transient annotation)
	 * @description zh - 是否支持transient关键字修饰和@Transient注解
	 * @description en - Support transient keyword modification and @ transient annotation
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:37:54
	 * @return boolean
	 */
	public boolean isTransientSupport() {
		return this.transientSupport;
	}

	/**
	 * [设置是否支持transient关键字修饰和@Transient注解](Set whether transient keyword modification and @ transient annotation are supported)
	 * @description zh - 设置是否支持transient关键字修饰和@Transient注解
	 * @description en - Set whether transient keyword modification and @ transient annotation are supported
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:38:32
	 * @param transientSupport 是否支持
	 * @return com.xiaoTools.entity.copyOptions.CopyOptions
	 */
	public CopyOptions setTransientSupport(boolean transientSupport) {
		this.transientSupport = transientSupport;
		return this;
	}

	/**
	 *
	 * @description zh - 获得映射后的字段名
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:39:05
	 * @param fieldName 字段名
	 * @param reversed 是否反向映射
	 * @return java.lang.String
	 */
	public String getMappedFieldName(String fieldName, boolean reversed){
		Map<String, String> mapping = reversed ? getReversedMapping() : this.fieldMapping;
		if(MapUtil.isEmpty(mapping)){
			return fieldName;
		}
		return ObjectUtil.defaultIfNull(mapping.get(fieldName), fieldName);
	}

	/**
	 *
	 * @description zh - 转换字段名为编辑后的字段名
	 * @description en - The converted field name is the edited field name
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:39:35
	 * @param fieldName 字段名
	 * @return java.lang.String
	 */
	public String editFieldName(String fieldName){
		return (null != this.fieldNameEditor) ? this.fieldNameEditor.edit(fieldName) : fieldName;
	}

	/**
	 * [获取反转之后的映射](Gets the map after inversion)
	 * @description zh - 获取反转之后的映射
	 * @description en - Gets the map after inversion
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:40:01
	 * @return java.util.Map<java.lang.String, java.lang.String>
	 */
	private Map<String, String> getReversedMapping() {
		if(null == this.fieldMapping){
			return null;
		}
		if(null == this.reversedFieldMapping){
			reversedFieldMapping = MapUtil.reverse(this.fieldMapping);
		}
		return reversedFieldMapping;
	}



}
