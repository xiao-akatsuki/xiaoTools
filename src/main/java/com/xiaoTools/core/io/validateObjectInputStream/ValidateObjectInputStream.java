package com.xiaoTools.core.io.validateObjectInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.HashSet;
import java.util.Set;

import com.xiaoTools.util.collUtil.CollUtil;

/**
 * [带有类验证的对象流](Object flow with class validation)
 * @description zh - 带有类验证的对象流
 * @description en - Object flow with class validation
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-23 17:32:26
 */
public class ValidateObjectInputStream extends ObjectInputStream {

	private Set<String> whiteClassSet;
	private Set<String> blackClassSet;

	public ValidateObjectInputStream(InputStream inputStream, Class<?>... acceptClasses) throws IOException {
		super(inputStream);
		accept(acceptClasses);
	}

	public void refuse(Class<?>... refuseClasses) {
		if(null == this.blackClassSet){
			this.blackClassSet = new HashSet<>();
		}
		for (Class<?> acceptClass : refuseClasses) {
			this.blackClassSet.add(acceptClass.getName());
		}
	}

	public void accept(Class<?>... acceptClasses) {
		if(null == this.whiteClassSet){
			this.whiteClassSet = new HashSet<>();
		}
		for (Class<?> acceptClass : acceptClasses) {
			this.whiteClassSet.add(acceptClass.getName());
		}
	}

	@Override
	protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
		validateClassName(desc.getName());
		return super.resolveClass(desc);
	}

	private void validateClassName(String className) throws InvalidClassException {
		if(CollUtil.isNotEmpty(this.blackClassSet)){
			if(this.blackClassSet.contains(className)){
				throw new InvalidClassException("Unauthorized deserialization attempt by black list", className);
			}
		}

		if(CollUtil.isEmpty(this.whiteClassSet)){
			return;
		}
		if(className.startsWith("java.")){
			return;
		}
		if(this.whiteClassSet.contains(className)){
			return;
		}

		throw new InvalidClassException("Unauthorized deserialization attempt", className);
	}
}
