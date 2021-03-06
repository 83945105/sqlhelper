package pub.avalon.sqlhelper.core.exception;

/**
 * 数据工具异常
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class SqlException extends RuntimeException {

    public SqlException() {
    }

    public SqlException(String message) {
        super(message);
    }

    public SqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlException(Throwable cause) {
        super(cause);
    }

    public SqlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
