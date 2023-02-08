package com.web.blog.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
	
	@GetMapping("/isblog/back/test")
	public String selectTestMsg() {
		
		return "Hello, Spring Boot!";
	}

}
