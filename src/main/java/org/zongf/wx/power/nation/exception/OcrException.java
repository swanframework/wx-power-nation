package org.zongf.wx.power.nation.exception;

/** Ocr异常
 * @author: zongf
 * @created: 2019-10-25
 * @since 1.0
 */
public class OcrException extends RuntimeException {

    public OcrException() {
        super();
    }

    public OcrException(String message) {
        super(message);
    }

    public OcrException(String message, Throwable cause) {
        super(message, cause);
    }

    public OcrException(Throwable cause) {
        super(cause);
    }

    protected OcrException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
