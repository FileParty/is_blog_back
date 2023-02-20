package com.web.blog.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.web.blog.common.component.GsonUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        ErrorCode errorCode = CommonErrorCode.NOT_FOUND;
        return handleExceptionInternal(errorCode);
    }
	
    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getResultCode())
                .body(makeErrorResponse(errorCode));
    }
    
    private String makeErrorResponse(ErrorCode errorCode) {
        HeaderVO header = new HeaderVO();
        header.setError(errorCode.name());
        header.setStatus(errorCode.getResultCode().value());
        header.setMessage(errorCode.getResultMsg());
        return GsonUtils.GSON.toJson(ErrorResponse.builder().header(header).build());
    }

}
