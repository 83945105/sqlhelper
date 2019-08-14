package pub.avalon.sqlhelper.core.utils;

/**
 * 异常工具类
 *
 * @author baichao
 * @date 2019/7/14
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
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
