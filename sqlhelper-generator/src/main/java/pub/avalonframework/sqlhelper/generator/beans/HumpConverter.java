package pub.avalonframework.sqlhelper.generator.beans;

/**
 * @author baichao
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
        return getGetterMethodName(this.converterColumnName(str), isBoolean);
    }

    @Override
    public String converterColumnSetterMethodName(String str) {
        return getSetterMethodName(this.converterColumnName(str));
    }

    private static String getGetterMethodName(String propertyName, boolean isBoolean) {
        if (propertyName == null || "".equals(propertyName.trim())) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(propertyName);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }
        if (isBoolean) {
            sb.insert(0, "is");
        } else {
            sb.insert(0, "get");
        }
        return sb.toString();
    }

    private static String getSetterMethodName(String propertyName) {
        if (propertyName == null || "".equals(propertyName.trim())) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(propertyName);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }
        sb.insert(0, "set");
        return sb.toString();
    }
}