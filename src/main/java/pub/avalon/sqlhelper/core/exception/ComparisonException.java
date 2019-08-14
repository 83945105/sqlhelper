package pub.avalon.sqlhelper.core.exception;

/**
 * 比较异常
 *
 * @author baichao
 * @since 2018/7/10
 */
public class ComparisonException extends SqlException {

    public ComparisonException() {
    }

    public ComparisonException(String message) {
        super(message);
    }

    public ComparisonException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComparisonException(Throwable cause) {
        super(cause);
    }

    public ComparisonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
