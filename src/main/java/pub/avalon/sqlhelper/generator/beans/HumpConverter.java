package pub.avalon.sqlhelper.generator.beans;

import pub.avalon.holygrail.utils.ClassUtil;

/**
 * 驼峰转换
 *
 * @author 白超
 * @date 2019/6/9
 */
public class HumpConverter implements StringConverter {

    @Override
    public String converterColumnName(String str) {
        String[] names = str.trim().split("_");
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
    public String converterColumnGetterMethodName(String str, boolean isBoolean) {
        return ClassUtil.getGetterMethodName(this.converterColumnName(str), isBoolean);
    }

    @Override
    public String converterColumnSetterMethodName(String str) {
        return ClassUtil.getSetterMethodName(this.converterColumnName(str));
    }

}
