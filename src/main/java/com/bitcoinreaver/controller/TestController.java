package com.bitcoinreaver.controller;

import java.net.URLClassLoader;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

//import org.eclipse.jdt.internal.compiler.batch.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitcoinreaver.service.test.TestService;


@Controller
@RequestMapping(value = "/test")
public class TestController {
	
	@Autowired
	TestService testService;
	
	@RequestMapping("/hello")
	public String hello(HttpServletRequest request){
		
		System.out.println(testService.getMsg());
		
		return "jsp/test";
	}
	
	@RequestMapping("/hi")
	public String hi(){
		
		return "jsp/test";
	}
	
	@RequestMapping("/attack")
	public String attack(){
		return "/jsp/attack";
	}
	
	public static void main(String[] args) {
        //URLClassLoader classLoader = (URLClassLoader)Main.class.getClassLoader();
        //System.out.println(Arrays.toString(classLoader.getURLs()));
	}
	
}
