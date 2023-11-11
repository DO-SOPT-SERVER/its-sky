package org.sopt.Seminar.global.common.exception;

import lombok.Getter;

@Getter
public class SoptException extends RuntimeException {
    int errorCode;
    ErrorType errorType;
    String detail;

    public SoptException(ErrorType errorType, String detail) {
        this.errorCode = errorType.getCode();
        this.errorType = errorType;
        this.detail = detail;
    }

    public abstract static class InvalidRequestException extends SoptException {
        public InvalidRequestException(ErrorType.BadRequest errorType, String detail) {
            super(errorType, detail);
        }
    }

    public abstract static class NotFoundException extends SoptException {
        public NotFoundException(ErrorType.NotFound errorType, String detail) {
            super(errorType, detail);
        }
    }

    public abstract static class ServerErrorException extends SoptException {
        public ServerErrorException(ErrorType.ServerError errorType, String detail) {
            super(errorType, detail);
        }
    }
}
