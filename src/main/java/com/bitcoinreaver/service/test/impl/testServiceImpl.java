package com.bitcoinreaver.service.test.impl;

import org.springframework.stereotype.Service;

import com.bitcoinreaver.service.test.TestService;

@Service
public class testServiceImpl implements TestService  {

	@Override
	public String getMsg(){
		return "TestService: success.";
	}
}
