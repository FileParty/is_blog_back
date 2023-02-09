package com.web.blog.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.common.component.CtyptKisaSeedEcbTemplate;

@RestController
public class CommonController {
	
	@Autowired
	CtyptKisaSeedEcbTemplate ctypt;
	
	@GetMapping("/isblog/back/test")
	public String selectTestMsg() {
		
		String test = "Hello, Spring Boot!";
		
		System.out.println("ctypt origin val : " + test);
		
		String ctyTest = ctypt.Encrypt_SEED(test);
		
		System.out.println("ctypt enc val : " + ctyTest);
		
		System.out.println("ctypt dec val : " + ctypt.Decrypt_SEED(ctyTest));
		
		return "Hello, Spring Boot!";
	}

}
