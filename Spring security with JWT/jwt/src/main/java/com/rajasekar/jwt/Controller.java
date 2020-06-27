package com.rajasekar.jwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Controller {

	@GetMapping("/hello")
	public String home() {
		return "Hello World";
	}
	
}
