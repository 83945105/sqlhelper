package pub.avalonframework.sqlhelper.core.exception;

/**
 * 表数据异常
 *
 * @author baichao
 * @since 2018/7/10
 */
public class TableDataException extends SqlException {

    public TableDataException() {
    }

    public TableDataException(String message) {
        super(message);
    }

    public TableDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public TableDataException(Throwable cause) {
        super(cause);
    }

    public TableDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
