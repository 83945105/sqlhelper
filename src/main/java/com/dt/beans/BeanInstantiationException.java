package com.dt.beans;

/**
 * @author 白超
 * @date 2018/8/15
 */
public class BeanInstantiationException extends RuntimeException {

    private Class<?> beanClass;

    public BeanInstantiationException(Class<?> beanClass, String message) {
        this(beanClass, message, null);
    }

    public BeanInstantiationException(Class<?> beanClass, String message, Throwable cause) {
        super("Failed to instantiate [" + beanClass.getName() + "]: " + message, cause);
        this.beanClass = beanClass;
    }

}
