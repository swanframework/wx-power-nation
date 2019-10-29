package org.zongf.wx.power.nation.exception;

public class RobotException extends RuntimeException {
    public RobotException() {
        super();
    }

    public RobotException(String message) {
        super(message);
    }

    public RobotException(String message, Throwable cause) {
        super(message, cause);
    }

    public RobotException(Throwable cause) {
        super(cause);
    }

    protected RobotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
