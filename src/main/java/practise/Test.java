package practise;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class Test {
	
	public static String parseXMLText(String str) {
		if (str == null)
			return null;
		str = str.replaceAll("&amp;", "&");
		str = str.replaceAll("&quot;", "\"");
		str = str.replaceAll("&apos;", "'");
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		return str;
	}

	public static String escapeXMLText(String str) {
		if (str == null)
			return null;
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("'", "&apos;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		return str;
	}
	
	static String rulesReq = "<IBEPlus_1_0><IBEPlusRQ>"
     		+ "<TSK_AirfarePrice>"
			     + "<Request>"
				     + "<SITA_AirfareRulesRQ Version=\"1.000\">"
				     	+ "<ota:OTA_AirRulesRQ xmlns:ota=\"http://www.opentravel.org/OTA/2003/05\" EchoToken=\"TravelSky_IBE_LINE\" Target=\"Test\">"
				     		+ "<ota:POS>"
				                 + "<ota:Source AirportCode=\"PEK\" />"
				                 + "<ota:Source>"
				                     + "<ota:RequestorID Type=\"13\" ID_Context=\"IATA_Number\" ID=\"08300025\" />"
				                 + "</ota:Source>"
				                 + "<ota:Source PseudoCityCode=\"BJS191\" />"
				             + "</ota:POS>"
				             + "<ota:RuleReqInfo>"
				                 + "<ota:DepartureDate>2015-07-01T13:30:00</ota:DepartureDate>"
				                 + "<ota:FareReference>QKXEESE</ota:FareReference>"
				                 + "<ota:RuleInfo />"
				                 + "<ota:FilingAirline Code=\"KE\" />"
				                 + "<ota:DepartureAirport LocationCode=\"SHA\" CodeContext=\"IATA\" />"
				                 + "<ota:ArrivalAirport LocationCode=\"YVR\" CodeContext=\"IATA\" />"
				             + "</ota:RuleReqInfo>"
				         + "</ota:OTA_AirRulesRQ>"
				         + "<AdditionalRulesRQData>"
				             + "<References>"
				                 + "<Ref1>GEPY01(USER(BJS,'1E',&lt;&gt;,Y,DEPT(&lt;&gt;,&lt;&gt;),&lt;&gt;,&lt;&gt;,&lt;&gt;),PF2(Y,[(AGENCY(BJS191),IATANUM('08300025'),N)],[],[],[],N,&lt;&gt;),&lt;&gt;)</Ref1>" 
				                 + "<Ref2>003S001PANADTN0010000200ATP27</Ref2>"
				             + "</References>"
				         + "</AdditionalRulesRQData>"
				         + "<TSK_Extensions>"
				             + "<ResultType>1</ResultType>"
				         + "</TSK_Extensions>"
			     + "</SITA_AirfareRulesRQ>"
			 + "</Request>"
			+ "</TSK_AirfarePrice>"
			+ "</IBEPlusRQ>"
			+ "<OrderPath>AirFareRules/I</OrderPath>"
			+ "</IBEPlus_1_0>";
	
	
	public static InputStreamReader getSoapInputStream()
			throws Exception {
		try {
//			String doxml = "<DoXml xmlns=\"http://ibe.casagt.com/\"><identity>&lt;?xml version=\"1.0\" encoding=\"gb2312\"?>&lt;Identity_1_0>&lt;User>Harris&lt;/User>&lt;Pwd>DDBEACBF90F2AFF7777900F8A576614A&lt;/Pwd>&lt;/Identity_1_0></identity><request>&lt;IBEPlus_1_0> &lt;IBEPlusRQ> &lt;TSK_AirfareFlightShop> &lt;TSK_AirfareFlightShopRQ> &lt;OTA_AirLowFareSearchRQ> &lt;MaxResponses>100&lt;/MaxResponses> &lt;DirectFlightsOnly>false&lt;/DirectFlightsOnly> &lt;POS> &lt;PseudoCityCode>BJS191&lt;/PseudoCityCode> &lt;AirportCode>PEK&lt;/AirportCode> &lt;ChannelID>1E&lt;/ChannelID> &lt;IataNo>08300025&lt;/IataNo> &lt;/POS> &lt;OriginDestinationInformation> &lt;OriginLocationCode>SHA&lt;/OriginLocationCode> &lt;DestinationLocationCode>YVR&lt;/DestinationLocationCode> &lt;DepartureDate> &lt;Date>2015-07-01&lt;/Date> &lt;/DepartureDate> &lt;/OriginDestinationInformation> &lt;OriginDestinationInformation> &lt;OriginLocationCode>YVR&lt;/OriginLocationCode> &lt;DestinationLocationCode>SHA&lt;/DestinationLocationCode> &lt;DepartureDate> &lt;Date>2015-07-10&lt;/Date> &lt;/DepartureDate> &lt;/OriginDestinationInformation> &lt;TravelPreferences/> &lt;TravelerInfoSummary> &lt;AirTravelerAvail> &lt;PassengerTypeQuantity> &lt;Code>ADT&lt;/Code> &lt;Quantity>1&lt;/Quantity> &lt;/PassengerTypeQuantity> &lt;/AirTravelerAvail> &lt;PriceRequestInformation> &lt;PricingSource>Both&lt;/PricingSource> &lt;/PriceRequestInformation> &lt;/TravelerInfoSummary> &lt;/OTA_AirLowFareSearchRQ> &lt;AdditionalShopRQData> &lt;IncludeTax>true&lt;/IncludeTax> &lt;IncludeFlightAvailability>true&lt;/IncludeFlightAvailability> &lt;IncludeBaggage>true&lt;/IncludeBaggage> &lt;IncludeMileage>true&lt;/IncludeMileage> &lt;/AdditionalShopRQData> &lt;/TSK_AirfareFlightShopRQ> &lt;/TSK_AirfareFlightShop> &lt;/IBEPlusRQ> &lt;OrderPath>AirFareFlightShop/I&lt;/OrderPath> &lt;/IBEPlus_1_0></request></DoXml>";
//
//	        String soap2 = "<?xml version=\"1.0\" encoding=\"gb2312\"?> <soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"> <soap:Body> "
//	                +doxml+" </soap:Body> </soap:Envelope>";
	        
	        String reqXml = "<IBEPlus_1_0> <IBEPlusRQ> <TSK_AirfareFlightShop> <TSK_AirfareFlightShopRQ> <OTA_AirLowFareSearchRQ> "
				+ "<MaxResponses>100</MaxResponses> <DirectFlightsOnly>false</DirectFlightsOnly> <POS> <PseudoCityCode>BJS191</PseudoCityCode> "
				+ "<AirportCode>PEK</AirportCode> <ChannelID>1E</ChannelID> <IataNo>08300025</IataNo> </POS> <OriginDestinationInformation> <OriginLocationCode>SHA</OriginLocationCode> <DestinationLocationCode>YVR</DestinationLocationCode>"
				+ " <DepartureDate> <Date>2015-07-01</Date> </DepartureDate> </OriginDestinationInformation> <OriginDestinationInformation> <OriginLocationCode>YVR</OriginLocationCode> <DestinationLocationCode>SHA</DestinationLocationCode> <DepartureDate> <Date>2015-07-10</Date> </DepartureDate> </OriginDestinationInformation> <TravelPreferences/> <TravelerInfoSummary> <AirTravelerAvail> <PassengerTypeQuantity> <Code>ADT</Code> <Quantity>1</Quantity> </PassengerTypeQuantity> </AirTravelerAvail> <PriceRequestInformation> <PricingSource>Both</PricingSource> </PriceRequestInformation> </TravelerInfoSummary> </OTA_AirLowFareSearchRQ> <AdditionalShopRQData> <IncludeTax>true</IncludeTax> <IncludeFlightAvailability>true</IncludeFlightAvailability> <IncludeBaggage>true</IncludeBaggage> <IncludeMileage>true</IncludeMileage> </AdditionalShopRQData> </TSK_AirfareFlightShopRQ> </TSK_AirfareFlightShop> </IBEPlusRQ>"
				+ " <OrderPath>AirFareFlightShop/I</OrderPath> </IBEPlus_1_0>";
	        
	       		
			String identity = "<?xml version=\"1.0\" encoding=\"gb2312\"?><Identity_1_0><User>Harris</User><Pwd>DDBEACBF90F2AFF7777900F8A576614A</Pwd></Identity_1_0>";
//			String soap = "<?xml version=\"1.0\" encoding=\"gb2312\"?> <soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"> <soap:Body> <DoXml xmlns=\"http://ibe.casagt.com/\"><identity>"
//							+ escapeXMLText(identity) +"</identity>"
//							+ "<request>" + escapeXMLText(rulesReq) + "</request>"
//						  + "</DoXml> </soap:Body> </soap:Envelope>";
			
			String queryPNR = "<?xml version=\"1.0\" encoding=\"gb2312\"?><RTPNR_1_0><PNRNo>KPXT53</PNRNo><OfficeCode>BJS191</OfficeCode></RTPNR_1_0>";
			
			String soap = "<?xml version=\"1.0\" encoding=\"gb2312\"?> <soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"> <soap:Body>"
					+ " <DoXml xmlns=\"http://ibe.casagt.com/\">"
					+ "<identity>" + escapeXMLText(identity) +"</identity>"
					+ "<request>" + escapeXMLText(queryPNR) + "</request>"
				  + "</DoXml> </soap:Body> </soap:Envelope>";
			
//			soap = queryPNR;
			
			
			
			URL url = new URL("http://www.cascc.cn/XMLServices/XMLService.asmx");
			URLConnection conn = url.openConnection();
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Content-Length", Integer.toString(soap
					.length()));
			conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			conn.setRequestProperty("SOAPAction", "http://ibe.casagt.com/DoXml");

			OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
			osw.write(soap);
			osw.flush();
			osw.close();

			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");

			return isr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getSoapResponse() throws Exception {
		InputStreamReader isr = getSoapInputStream();
		StringBuffer result = new StringBuffer();
		int b = -1;
		while ((b = isr.read()) != -1) {
			result.append((char) b);
		}
		
//		while(isr.)
		
		
		isr.close();
		return result.toString();
	}
	
	public static void main(String[] args) {
		try {
			String result = getSoapResponse();
			System.out.println(result);
			
//			System.out.println(rulesReq);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
