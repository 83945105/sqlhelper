package com.dt.core.converter;

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
     * @param column 列名
     * @return 属性名
     */
    String columnToField(String column);

    /**
     * 属性名转为列名
     *
     * @param field 属性名
     * @return 列名
     */
    String fieldToColumn(String field);
}
