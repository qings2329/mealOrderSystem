package com.bitcoinreaver.unexpectederror;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qings2329
 *
 * @date 9:43:19 PM Dec 21, 2015
 */
public class StringMapLongKey {
	
	public static void main(String[] args){
		Map<String, String> queryPackageIdMapPlaneIds = new HashMap<String, String>();
		
		long key = 123456;
		queryPackageIdMapPlaneIds.put(String.valueOf(key), "1244");
		
		System.out.println( queryPackageIdMapPlaneIds.get(key) );//输出null
	}
	
	
	
}
