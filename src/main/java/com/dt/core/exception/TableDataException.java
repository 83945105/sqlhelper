package com.dt.core.exception;

/**
 * 表数据异常
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class TableDataException extends DtException {

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
