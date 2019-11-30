package pub.avalonframework.sqlhelper.generator.beans;

/**
 * @author baichao
 */
public interface StringConverter {

    /**
     * Converter column name.
     *
     * @param columnName The column name.
     * @return The converter column name.
     */
    String converterColumnName(String columnName);

    /**
     * Converter column getter method name.
     *
     * @param columnName The column name.
     * @param isBoolean  Is boolean.
     * @return The converter column getter method name.
     */
    String converterColumnGetterMethodName(String columnName, boolean isBoolean);

    /**
     * Converter column setter method name.
     *
     * @param columnName The column name.
     * @return The converter column setter method name.
     */
    String converterColumnSetterMethodName(String columnName);
}