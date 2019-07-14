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

    public static void tableAliasNullException() {
        throw new RuntimeException("tableAlias is null.");
    }

}
