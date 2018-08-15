package com.dt.beans;

import com.esotericsoftware.reflectasm.MethodAccess;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实体工具类
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class BeanUtils {

    private static final Map<String, String> GETTER_METHOD_NAME_CACHE = new ConcurrentHashMap<>();
    private static final Map<String, String> SETTER_METHOD_NAME_CACHE = new ConcurrentHashMap<>();

    /**
     * 根据属性名获取对应的get方法名
     *
     * @param property      属性名
     * @param isBooleanType 是否是boolean类型属性
     * @return get方法名
     */
    public static String getGetterMethodName(String property, boolean isBooleanType) {
        if (property == null || property.trim().length() == 0) {
            return null;
        }
        String name = GETTER_METHOD_NAME_CACHE.get(property);
        if (name != null) {
            return name;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }
        if (isBooleanType) {
            sb.insert(0, "is");
        } else {
            sb.insert(0, "get");
        }
        GETTER_METHOD_NAME_CACHE.put(property, sb.toString());
        return sb.toString();
    }

    /**
     * 根据属性名称获取对应的setter方法名称
     *
     * @param property 属性名称
     * @return
     */
    public static String getSetterMethodName(String property) {
        if (property == null || property.trim().trim().length() == 0) {
            return null;
        }
        String name = SETTER_METHOD_NAME_CACHE.get(property);
        if (name != null) {
            return name;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }
        sb.insert(0, "set");
        SETTER_METHOD_NAME_CACHE.put(property, sb.toString());
        return sb.toString();
    }

    /**
     * 执行setter方法
     *
     * @param methodAccess MethodAccess对象
     * @param object       容器对象
     * @param property     属性名称
     * @param value        属性值
     */
    public static void invokeSetter(MethodAccess methodAccess, Object object, String property, Object value) {
        String setterName = BeanUtils.getSetterMethodName(property);
        try {
            methodAccess.invoke(object, setterName, value);
        } catch (ClassCastException e) {
            methodAccess.invoke(object, setterName, value.toString());
        }
    }
}
