package pub.avalon.sqlhelper.generator.beans;

/**
 * @author 白超
 * @date 2019/6/9
 */
public interface StringConverter {

    /**
     * 转换列名
     *
     * @param str
     * @return
     */
    String converterColumnName(String str);

    /**
     * 转换为getter方法名
     *
     * @param str
     * @param isBoolean
     * @return
     */
    String converterColumnGetterMethodName(String str, boolean isBoolean);

    /**
     * 转换为setter方法名
     *
     * @param str
     * @return
     */
    String converterColumnSetterMethodName(String str);

}
