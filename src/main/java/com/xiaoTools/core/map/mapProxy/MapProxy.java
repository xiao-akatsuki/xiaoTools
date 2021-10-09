package com.xiaoTools.core.map.mapProxy;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.xiaoTools.core.getter.optNullBasicTypeFromObjectGetter.OptNullBasicTypeFromObjectGetter;

/**
 * [Map代理](Map proxy)
 * @description zh - Map代理
 * @description en - Map proxy
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-08 07:38:06
 */
public class MapProxy implements Map<Object, Object>, OptNullBasicTypeFromObjectGetter<Object>, InvocationHandler, Serializable {
    
}
