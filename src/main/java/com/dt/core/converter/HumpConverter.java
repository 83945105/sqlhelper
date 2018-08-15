package com.dt.core.converter;

/**
 * 驼峰转换器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class HumpConverter implements ColumnFieldConverter {

    @Override
    public String columnToField(String column) {
        String[] names = column.trim().split("_");
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
    public String fieldToColumn(String field) {
        if (field == null || "".equals(field.trim())) {
            return null;
        }
        int len = field.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = field.charAt(i);
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
