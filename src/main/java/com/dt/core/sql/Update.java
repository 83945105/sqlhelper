package com.dt.core.sql;

import java.util.Collection;

/**
 * 更新
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Update<T> extends Sql {

    T updateJavaBean(Object record);

    T updateJavaBeanSelective(Object record);

    T updateOrInsertArgs(Object[] batchArgs);

    T updateOrInsertArgs(Collection<?> batchArgs);

    T updateOrInsertJavaBeans(Object[] records);

    T updateOrInsertJavaBeans(Collection<?> records);
}
