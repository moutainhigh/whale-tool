package org.thatbug.whale.core.secure.exception;

import lombok.Getter;
import org.thatbug.whale.core.tool.api.IResultCode;
import org.thatbug.whale.core.tool.api.ResultCode;

/**
 * SecureException
 *
 * @author qzl
 * @date 15:41 2019/9/17
 */
public class SecureException extends RuntimeException {
    private static final long serialVersionUID = 2359767895161832954L;

    @Getter
    private final IResultCode resultCode;

    public SecureException(String message) {
        super(message);
        this.resultCode = ResultCode.UN_AUTHORIZED;
    }

    public SecureException(IResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public SecureException(IResultCode resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
