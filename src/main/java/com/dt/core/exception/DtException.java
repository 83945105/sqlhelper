package com.dt.core.exception;

/**
 * 数据工具异常
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class DtException extends RuntimeException {

    public DtException() {
    }

    public DtException(String message) {
        super(message);
    }

    public DtException(String message, Throwable cause) {
        super(message, cause);
    }

    public DtException(Throwable cause) {
        super(cause);
    }

    public DtException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
