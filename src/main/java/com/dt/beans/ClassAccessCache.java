package com.dt.beans;

import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于缓存Access
 *
 * @author 白超
 * @version 1.0
 * @see MethodAccess
 * @since 2018/7/10
 */
public class ClassAccessCache {

    /**
     * MethodAccess缓存
     * {@link MethodAccess}
     */
    private static final Map<Class, MethodAccess> METHOD_ACCESS_MAP = new ConcurrentHashMap<>();

    /**
     * FieldAccess缓存
     * {@link FieldAccess}
     */
    private static final Map<Class, FieldAccess> FIELD_ACCESS_MAP = new ConcurrentHashMap<>();

    /**
     * 获取指定类的MethodAccess
     * <p>存在返回、不存在新建
     *
     * @param clazz 类的Class属性
     * @return {@link MethodAccess}
     */
    public static MethodAccess getMethodAccess(Class clazz) {
        MethodAccess methodAccess = METHOD_ACCESS_MAP.get(clazz);
        if (methodAccess == null) {
            methodAccess = MethodAccess.get(clazz);
            METHOD_ACCESS_MAP.put(clazz, methodAccess);
        }
        return methodAccess;
    }

    /**
     * 获取指定类的FieldAccess
     * <p>存在返回、不存在新建
     *
     * @param clazz 类的Class属性
     * @return {@link FieldAccess}
     */
    public FieldAccess getFieldAccess(Class clazz) {
        FieldAccess fieldAccess = FIELD_ACCESS_MAP.get(clazz);
        if (fieldAccess == null) {
            fieldAccess = FieldAccess.get(clazz);
            FIELD_ACCESS_MAP.put(clazz, fieldAccess);
        }
        return fieldAccess;
    }

}
