package mealOrderSystem;

import static com.bitcoinreaver.utils.Utils.getOriginalStringFromFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mealOrderSystem.FlightOrderStatusResult.Passenger;

import org.junit.Test;

/**`
 * @author qings2329
 *
 * @since 2015年10月27日 上午1:59:51
 */
public class ExtractPnrInfo {
	/**
	 * 需要提取的信息：
	 * 1. 姓和名；
	 * 2. 身份证号或者护照号；
	 * 3. 机票号码；
	 *
	 * 需要考虑的问题：
	 * 1. 乘客可能有一个或者多个；
	 * 2. 可能未出票、可能已出票；
	 * 3. 证件号可能是身份证号或者护照号, 也可能两者都有；
	 * 
	 * 
	 * @throws IOException 
	 */
	@Test
	public void test_extract_pnr_info() throws IOException{
		
//		String path = System.getProperty("user.home") + "/ZhfTestData/ZhfpnrResponse.xml";
//		String path = System.getProperty("user.home") + "/ZhfTestData/ZhfPnrQuery_ticketnum.xml";
		
		String path = System.getProperty("user.home") + "/ZhfTestData/Zhf_pnr_tow_passenger";
		String pnr = "KDTDZ8";
		String xml = getOriginalStringFromFile(path);
//		System.out.println(xml);
		List<FlightOrderStatusResult.Passenger> ret = extractPnrInfo(xml, pnr);
//		System.out.println( JsonUtils.toJson2(ret, true, false));
		
		for(Passenger item : ret){ 
			System.out.println(item);
		}
		
	}
	
	public List<FlightOrderStatusResult.Passenger> extractPnrInfo(String pnfInfo, String pnr){
		List<FlightOrderStatusResult.Passenger> pList = new ArrayList<FlightOrderStatusResult.Passenger>();
		
		//识别姓名
		int pnrPosition = pnfInfo.indexOf(pnr);
		String[] rawNameData = pnfInfo.substring(0, pnrPosition).split("/");
		for(int count = 0; count < rawNameData.length - 1; ){
			FlightOrderStatusResult.Passenger p = new FlightOrderStatusResult.Passenger();
			pList.add(p);
			int start = rawNameData[count].indexOf('.');
			p.firstName = rawNameData[count].substring(start + 1);
			count++;
			int end = rawNameData[count].indexOf(' ');
			p.lastName = rawNameData[count].substring(0, end);
		}
		
		String remaining = pnfInfo.substring(pnrPosition);
		
//		String[] lines = pnfInfo.split("\n");
//		Arrays.asList(lines).forEach( item -> {System.out.println(item);} );
//		int status = -1;
//		for(int i = 0; i < lines.length; i++){
//			if(status == -1){
//				int index = lines[i].indexOf("1.");
//				if(index > -1){
//					status = 0;
//					//识别姓名, 可能有多个人
//					String[] rawNameData = lines[i].split("/");
//					for(int count = 0; count < rawNameData.length - 1;){
//						Passenger p = new Passenger();
//						pList.add(p);
//						int start = rawNameData[count].indexOf('.');
//						p.firstName = rawNameData[count].substring(start + 1);
//						count++;
//						int end = rawNameData[count].indexOf(' ');
//						p.lastName = rawNameData[count].substring(0, end);
//					}
//				}
//			}
//		}
		return pList;
	}
}
