package cn.keepting.family.server.exception;

import lombok.Getter;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 2359767895161832954L;

    @Getter
    private final ResultCode resultCode;

    public ServiceException(String message) {
        super(message);
        this.resultCode = ResultCode.FAILURE;
    }

//    public ServiceException(String code, String message) {
//        super(message);
//        this.resultCode = code;
//    }

    public ServiceException(ResultCode resultCode) {
        super(resultCode.getErrmsg());
        this.resultCode = resultCode;
    }

    public ServiceException(ResultCode resultCode, String msg) {
        super(msg);
        this.resultCode = resultCode;
    }

    public ServiceException(ResultCode resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
    }

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
        this.resultCode = ResultCode.FAILURE;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
