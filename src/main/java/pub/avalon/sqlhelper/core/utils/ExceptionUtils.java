package pub.avalon.sqlhelper.core.utils;

/**
 * 异常工具类
 *
 * @author 白超
 * @date 2019/7/14
 */
public class ExceptionUtils {

    private ExceptionUtils() {
    }

    public static void tableHelperClassNullException() {
        throw new RuntimeException("tableHelperClass is null.");
    }

    public static void tableAliasNullException() {
        throw new RuntimeException("tableAlias is null.");
    }

    public static void comparisonRuleNotSupportException() {
        throw new RuntimeException("ComparisonRule's enumeration value is not supported for the time being.");
    }

    public static void groupTypeNullException() {
        throw new RuntimeException("groupType is null.");
    }

    public static void groupTypeNotSupportException() {
        throw new RuntimeException("GroupType's enumeration value is not supported for the time being.");
    }

}
