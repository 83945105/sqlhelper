package pub.avalon.sqlhelper.core.utils;

import pub.avalon.sqlhelper.core.exception.ComparisonRuleNullException;

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

    public static void columnHelperNullException() {
        throw new RuntimeException("columnHelper is null.");
    }

    public static void groupHelperNullException() {
        throw new RuntimeException("groupHelper is null.");
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

    public static void groupTypeNotSupportException() {
        throw new RuntimeException("GroupType's enumeration value is not supported for the time being.");
    }
}