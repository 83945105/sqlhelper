package com.dt.beans;

/**
 * 列名-属性名 转换器接口
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface ColumnFieldConverter {

    /**
     * 列名转为属性名
     *
     * @param columnName 列名
     * @return 属性名
     */
    String columnNameToFieldName(String columnName);

    /**
     * 属性名转为列名
     *
     * @param fieldName 属性名
     * @return 列名
     */
    String fieldNameToColumnName(String fieldName);
}
