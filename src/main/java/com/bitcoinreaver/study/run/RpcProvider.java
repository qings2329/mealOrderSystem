package com.bitcoinreaver.study.run;

import com.bitcoinreaver.study.HelloService;
import com.bitcoinreaver.study.HelloServiceImpl;
import com.bitcoinreaver.study.RpcFramework;

public class RpcProvider {
	
	public static void main(String[] args) throws Exception {  
        HelloService service = new HelloServiceImpl();  
        RpcFramework.export(service, 1234);  
    }

}
