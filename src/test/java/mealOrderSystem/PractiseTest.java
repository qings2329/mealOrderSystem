package mealOrderSystem;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;

import practise.JDKTest;

/**
 * @author qings2329
 *
 * @date 5:33:55 PM Aug 13, 2015
 */

public class PractiseTest {
	
	@Test
	public void test_greeting(){
		JDKTest jdkTest = new JDKTest();
		jdkTest.greeting();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test_marcher(){
		String msg = "1.HUANG/SIYUE MS 2.WU/YANYAN MS JR95Q4"                                      
				  + "3.  CX255  N   TU29SEP HKGLHR HK2   0035 0620      SEAME"                       
				  + "4.  CX238  N  TU06OCT  LHRHKG HK2   1700 1155+1    SEAME"                       
				 + "5.BJS/T BJS/T-65550619/CHINA AIR SERVICE LTD./ZHENGWEI ABCDEFG"                 
				 + "6.15001113133"                                                                 
				 + "7.T"                                                                            
				 + "8.SSR ADTK 1E ADV TKT NBR TO CX/KA BY 28AUG GMT 2359 OR SUBJECT TO CANCEL"      
				 + "9.SSR TKNE CX HK1 HKGLHR 255 N29SEP 1609381067892/1/P2"                         
				+ "10.SSR TKNE CX HK1 HKGLHR 255 N29SEP 1609381067891/1/P1"                         
				+ "11.SSR TKNE CX HK1 LHRHKG 238 N06OCT 1609381067891/2/P1"                         
				+ "12.SSR TKNE CX HK1 LHRHKG 238 N06OCT 1609381067892/2/P2";
		
		Pattern pattern = Pattern.compile("\\d{13}");
		Matcher matcher = pattern.matcher(msg);
		System.out.println("########## Start match ##########");
		Set<String> ticketSet = new HashSet<String>();
		while(matcher.find()){
			String ticket = matcher.group();
			ticketSet.add(ticket);
			System.out.println(ticket); 
		}
		System.out.println("########## end match ##########");
		Assert.assertTrue(ticketSet.size() > 0);;
	}
	
	
	
	
	
}
