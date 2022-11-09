package com.lschaan.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloWorldCommand extends HystrixCommand<String> {

	public HelloWorldCommand() {
		super(HystrixCommandGroupKey.Factory.asKey("HelloWorld"));
	}

	@Override
	public String run() {
		return "Hello World!";
	}

	public String getFallback() {
		return "Something went wrong.";
	}
}
