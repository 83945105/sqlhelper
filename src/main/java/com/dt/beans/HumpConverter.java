package com.dt.beans;

/**
 * 驼峰转换器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class HumpConverter implements ColumnFieldConverter {

    @Override
    public String columnNameToFieldName(String columnName) {
        String[] names = columnName.trim().split("_");
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            if (name.length() == 0) {
                continue;
            }
            char[] cs = name.toLowerCase().toCharArray();
            cs[0] -= 32;
            sb.append(String.valueOf(cs));
        }
        char[] cs = sb.toString().toCharArray();
        cs[0] += 32;
        return String.valueOf(cs);
    }

    @Override
    public String fieldNameToColumnName(String fieldName) {
        if (fieldName == null || "".equals(fieldName.trim())) {
            return null;
        }
        int len = fieldName.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = fieldName.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
