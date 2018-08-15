package com.dt.core.exception;

/**
 * 比较异常
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class ComparisonException extends DtException {

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
