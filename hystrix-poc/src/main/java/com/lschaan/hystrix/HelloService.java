package com.lschaan.hystrix;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {

	@RequestMapping("/hello")
	public String getResponse() {
		return new HelloWorldCommand().execute();
	}
}
