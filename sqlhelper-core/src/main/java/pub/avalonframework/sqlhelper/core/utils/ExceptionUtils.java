package pub.avalonframework.sqlhelper.core.utils;

import pub.avalonframework.sqlhelper.core.data.ComparisonType;
import pub.avalonframework.sqlhelper.core.data.beans.ColumnType;
import pub.avalonframework.sqlhelper.core.data.beans.Type;
import pub.avalonframework.sqlhelper.core.data.beans.ValueType;
import pub.avalonframework.sqlhelper.core.exception.ComparisonRuleNullException;

/**
 * @author baichao
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
    }

    public static void aliasAlreadyExistException(String alias) {
        throw new RuntimeException("Alias " + alias + "already exist.");
    }

    public static void aliasCacheDoesNotExistException(String alias) {
        throw new RuntimeException("Alias " + alias + "cache does not exist.");
    }

    public static void classNullPointerException() {
        throw new NullPointerException("Class can not be null.");
    }

    public static void tableHelperClassNullException() {
        throw new RuntimeException("tableHelperClass is null.");
    }

    public static void selectColumnNullException() {
        throw new RuntimeException("No query column specified.");
    }

    public static void multiTableColumnException() {
        throw new RuntimeException("Cannot use columns from multiple tables.");
    }

    public static void columnHelperNullException() {
        throw new RuntimeException("columnHelper is null.");
    }

    public static void groupHelperNullException() {
        throw new RuntimeException("groupHelper is null.");
    }

    public static void havingHelperNullException() {
        throw new RuntimeException("havingHelper is null.");
    }

    public static void onHelperNullException() {
        throw new RuntimeException("onHelper is null.");
    }

    public static void sortHelperNullException() {
        throw new RuntimeException("sortHelper is null.");
    }

    public static void whereHelperNullException() {
        throw new RuntimeException("whereHelper is null.");
    }

    public static void tableAliasNullException() {
        throw new RuntimeException("tableAlias is null.");
    }

    public static void inconsistentAliasException() {
        throw new RuntimeException("inconsistent alias.");
    }

    public static void notJoinException(String alias) {
        throw new RuntimeException("Can not find Alias " + alias + " join class.");
    }

    public static void unsupportedTypeException(Type type) {
        throw new RuntimeException("unsupported type " + type.getClass());
    }

    public static void unsupportedColumnTypeException(ColumnType columnType) {
        throw new RuntimeException("unsupported columnType " + columnType.getClass());
    }

    public static void unsupportedValueTypeException(ValueType valueType) {
        throw new RuntimeException("unsupported valueType " + valueType.getClass());
    }

    public static void errorValueTypeException(Object value) {
        throw new RuntimeException("error valueType " + value.getClass());
    }

    public static void unsupportedComparisonTypeException(ComparisonType comparisonType) {
        throw new RuntimeException("unsupported comparisonType " + comparisonType.getClass());
    }

    public static void valueCountException(long valueCount) {
        throw new RuntimeException("valueCount must be greater than 0, but the actual is " + valueCount);
    }

    public static void columnDataSizeNotEqualException() {
        throw new RuntimeException("columnData size must be equal.");
    }


    public static void comparisonRuleNotSupportException() {
        throw new RuntimeException("ComparisonRule's enumeration value is not supported for the time being.");
    }

    public static void comparisonRuleNullException(Class methodClass, String methodName, String tableName, String tableAlias, String columnName, String columnAlias) {
        throw new ComparisonRuleNullException(methodClass, methodName, "tableName:" + tableName + ", tableAlias:" + tableAlias + ", columnName:" + columnName + ", columnAlias:" + columnAlias);
    }

    public static void columnTypeNotSupportException() {
        throw new RuntimeException("ColumnType's enumeration value is not supported for the time being.");
    }

    public static void groupTypeNullException() {
        throw new RuntimeException("groupType is null.");
    }
}