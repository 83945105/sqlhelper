package com.dt.core.sql;

import java.util.Collection;

/**
 * 插入
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Insert<T> extends Sql {

    T insertArgs(Object[] args);

    T insertArgs(Collection<?> args);

    T insertJavaBean(Object record);

    T insertJavaBeanSelective(Object record);

    T batchInsertJavaBeans(Object[] records);

    T batchInsertJavaBeans(Collection<?> records);

}
