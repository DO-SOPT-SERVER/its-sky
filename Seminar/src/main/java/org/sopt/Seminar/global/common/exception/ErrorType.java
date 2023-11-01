package org.sopt.Seminar.global.common.exception;

public interface ErrorType {
    int getCode();

    enum BadRequest implements ErrorType {
        ;

        private final int errorCode;

        BadRequest(int errorCode) {
            this.errorCode = errorCode;
        }

        @Override
        public int getCode() {
            return this.errorCode;
        }
    }

    enum NotFound implements ErrorType {
        POST_NOT_FOUND(4001),
        USER_NOT_FOUND(4002)
        ;

        private final int errorCode;

        NotFound(int errorCode) {
            this.errorCode = errorCode;
        }

        @Override
        public int getCode() {
            return this.errorCode;
        }
    }

    enum ServerError implements ErrorType {
        SERVER_ERROR_DEFAULT(5000)
        ;

        private final int errorCode;

        ServerError(int errorCode) {
            this.errorCode = errorCode;
        }


        @Override
        public int getCode() {
            return this.errorCode;
        }
    }
}
