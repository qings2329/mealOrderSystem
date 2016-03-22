package com.bitcoinreaver.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author qings2329
 *
 * @date 3:09:57 PM Sep 17, 2015
 */
public class Utils {
	
	/**
	 * serialize param obj to url param string
	 * @param param
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static String serializeParamObj(Object param) throws IllegalArgumentException, IllegalAccessException{
    	StringBuilder result = new StringBuilder();
    	Class paramCls = param.getClass();
    	Field[] fields = paramCls.getFields();
    	
    	if(fields != null){
    		for(int i = 0; i < fields.length; i++){
    			result.append("&" + fields[i].getName() + "=" + fields[i].get(param));
    		}
    	}
    	return result.toString();
    }
	
	public static String serializeParamMap(Map paramMap){
		StringBuilder result = new StringBuilder();
		if(paramMap != null){
			for(Object key : paramMap.keySet()){
				result.append("&" + key + "=" + paramMap.get(key));
			}
		}
		
		return result.toString();
	}
	
	/**
	 * 读取文本文件，保留换行
	 * @param path
	 * @return
	 */
	public static String getOriginalStringFromFile(String path) {
        StringBuilder strB = new StringBuilder();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                strB.append(scanner.nextLine());
                //保留换行；
                strB.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return strB.toString();
    }
	
	public static void postRequest(){
		
	}
	
	public static void main(String[] args){
		Calendar calendar = Calendar.getInstance();
		try {
			System.out.println(serializeParamObj(calendar));
			System.out.print("\n");
			Map map = new HashMap<String, String>();
			map.put("a", "1");
			map.put("b", "2");
			System.out.println(serializeParamMap(map));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
