package org.sopt.Seminar.global.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sopt.Seminar.global.common.exception.SoptException;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int errorCode;
    private String errorType;
    private String detail;

    public ErrorResponse(SoptException soptException) {
        this.errorCode = soptException.getErrorCode();
        this.errorType = soptException.getErrorType().toString();
        this.detail = soptException.getDetail();
    }
}
