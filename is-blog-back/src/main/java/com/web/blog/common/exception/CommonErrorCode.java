package com.web.blog.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode{
	
	NOT_FOUND(HttpStatus.NOT_FOUND, "요청하신 페이지를 찾을 수 없습니다."), 
	FORBIDDEN(HttpStatus.FORBIDDEN, "허용되지 않은 요청입니다."), 
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "요청된 리소스에 대한 유효한 인증 자격이 없습니다.");
	
	
    private final HttpStatus resultCode;
    private final String resultMsg;

}
