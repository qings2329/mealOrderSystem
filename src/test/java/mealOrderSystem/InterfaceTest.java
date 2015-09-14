package mealOrderSystem;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.junit.Test;

public class InterfaceTest {

	@Test
	public void test_decode() throws UnsupportedEncodingException{
		String encodedString= "%7B%22result%22%3A%220%22%2C%22message%22%3A%22Success%22%7D";
//		encodedString = "%7B%22result%22%3A%220%22%2C%22message%22%3A%22Success%22%7D";
		encodedString = "%e7%bb%93%e7%ae%97%e7%bc%96%e5%8f%b7%e4%b8%8d%e8%83%bd%e6%89%be%e5%88%b0%e7%9b%b8%e5%ba%94%e5%8d%8f%e8%ae%ae";
					   //%e7%bb%93%e7%ae%97%e7%bc%96%e5%8f%b7%e4%b8%8d%e8%83%bd%e6%89%be%e5%88%b0%e7%9b%b8%e5%ba%94%e5%8d%8f%e8%ae%ae
		System.out.println(URLDecoder.decode(encodedString, "UTF-8"));
		
//		String encodedParam = "channel%3D75%26cardno%3D6168201300000214%26time%3D15468755524034%26ver%3D1.0";
//		System.out.println(URLDecoder.decode(encodedParam, "UTF-8"));
	}
}
