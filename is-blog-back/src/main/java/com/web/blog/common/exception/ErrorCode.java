package com.web.blog.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
	
	String name();
	HttpStatus getResultCode();
	String getResultMsg();

}
