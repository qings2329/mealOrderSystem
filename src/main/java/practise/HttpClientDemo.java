package practise;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpClientDemo {
	
	
	public static String escapeXMLText(String str) {
		if (str == null)
			return null;
//		str = str.replaceAll("&", "&amp;");
//		str = str.replaceAll("\"", "&quot;");
//		str = str.replaceAll("'", "&apos;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		return str;
	}
	
	
	public static String gzipHandle(PostMethod postMethod) throws IOException{
		// 返回结果的数据解压
		InputStream is = postMethod.getResponseBodyAsStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(
		new GZIPInputStream(is)));
		StringBuffer respStr = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			respStr.append(line);
		}
		is.close();
		return respStr.toString();
	}
	
	public static String handle(PostMethod postMethod) throws IOException{
		InputStream is = postMethod.getResponseBodyAsStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer respStr = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			respStr.append(line);
			respStr.append("\n");
		}
		is.close();
		return respStr.toString();
	}
	
	public static void main(String[] args) {
		//实际使用时,请设置正确的变量值
		//用户名
		String username = "Harris";
		//密码
		String pwd = "20150414";
		//服务地址
		String serviceUrl = "http://www.cascc.cn/XMLServices/XMLService.asmx";
//		String serviceUrl = "http://espeed.travelsky.com/develop/xml/AirFareFlightShop/I";
		
		
		
		
		  String reqXml = "<IBEPlus_1_0> <IBEPlusRQ> <TSK_AirfareFlightShop> <TSK_AirfareFlightShopRQ> <OTA_AirLowFareSearchRQ> "
					+ "<MaxResponses>100</MaxResponses> <DirectFlightsOnly>false</DirectFlightsOnly> <POS> <PseudoCityCode>BJS191</PseudoCityCode> "
					+ "<AirportCode>PEK</AirportCode> <ChannelID>1E</ChannelID> <IataNo>08300025</IataNo> </POS> <OriginDestinationInformation> <OriginLocationCode>SHA</OriginLocationCode> <DestinationLocationCode>YVR</DestinationLocationCode>"
					+ " <DepartureDate> <Date>2015-07-01</Date> </DepartureDate> </OriginDestinationInformation> <OriginDestinationInformation> <OriginLocationCode>YVR</OriginLocationCode> <DestinationLocationCode>SHA</DestinationLocationCode> <DepartureDate> <Date>2015-07-10</Date> </DepartureDate> </OriginDestinationInformation> <TravelPreferences/> <TravelerInfoSummary> <AirTravelerAvail> <PassengerTypeQuantity> <Code>ADT</Code> <Quantity>1</Quantity> </PassengerTypeQuantity> </AirTravelerAvail> <PriceRequestInformation> <PricingSource>Both</PricingSource> </PriceRequestInformation> </TravelerInfoSummary> </OTA_AirLowFareSearchRQ> <AdditionalShopRQData> <IncludeTax>true</IncludeTax> <IncludeFlightAvailability>true</IncludeFlightAvailability> <IncludeBaggage>true</IncludeBaggage> <IncludeMileage>true</IncludeMileage> </AdditionalShopRQData> </TSK_AirfareFlightShopRQ> </TSK_AirfareFlightShop> </IBEPlusRQ>"
					+ " <OrderPath>AirFareFlightShop/I</OrderPath> </IBEPlus_1_0>";
			
				String identity = "<?xml version=\"1.0\" encoding=\"gb2312\"?><Identity_1_0><User>Harris</User><Pwd>DDBEACBF90F2AFF7777900F8A576614A</Pwd></Identity_1_0>";
				String reqStrSoap = "<?xml version=\"1.0\" encoding=\"gb2312\"?> <soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"> <soap:Body> <DoXml xmlns=\"http://ibe.casagt.com/\"><identity>" + escapeXMLText(identity) +"</identity>"
						+ "<request>" + escapeXMLText(reqXml) + "</request> </DoXml> </soap:Body> </soap:Envelope>";
		
		
		//请求 XML
		
		//String reqStr = "<TSK_AirfareFlightShop> <TSK_AirfareFlightShopRQ> <OTA_AirLowFareSearchRQ> <POS> <PseudoCityCode>PEK099</PseudoCityCode> <AirportCode>PEK</AirportCode> <ChannelID>1E</ChannelID> </POS> <OriginDestinationInformation> <DepartureDate> <Date>2013-10-15</Date> </DepartureDate> <OriginLocationCode>PEK</OriginLocationCode> <DestinationLocationCode>HKG</DestinationLocationCode> </OriginDestinationInformation> <OriginDestinationInformation> <DepartureDate> <Date>2013-10-25</Date> </DepartureDate> <OriginLocationCode>HKG</OriginLocationCode> <DestinationLocationCode>PEK</DestinationLocationCode> </OriginDestinationInformation> <TravelPreferences /> </OTA_AirLowFareSearchRQ> <AdditionalShopRQData> <IncludeFlightAvailability>true</IncludeFlightAvailability> </AdditionalShopRQData> </TSK_AirfareFlightShopRQ> </TSK_AirfareFlightShop>";
		
		
		String reqStr1 = "<IBEPlus_1_0> <IBEPlusRQ> <TSK_AirfareFlightShop> <TSK_AirfareFlightShopRQ> <OTA_AirLowFareSearchRQ> "
				+ "<MaxResponses>100</MaxResponses> <DirectFlightsOnly>false</DirectFlightsOnly> <POS> <PseudoCityCode>BJS191</PseudoCityCode> "
				+ "<AirportCode>PEK</AirportCode> <ChannelID>1E</ChannelID> <IataNo>08300025</IataNo> </POS> <OriginDestinationInformation> <OriginLocationCode>SHA</OriginLocationCode> <DestinationLocationCode>YVR</DestinationLocationCode>"
				+ " <DepartureDate> <Date>2015-7-1</Date> </DepartureDate> </OriginDestinationInformation> <OriginDestinationInformation> <OriginLocationCode>YVR</OriginLocationCode> <DestinationLocationCode>SHA</DestinationLocationCode> <DepartureDate> <Date>2015-12-26</Date> </DepartureDate> </OriginDestinationInformation> <TravelPreferences/> <TravelerInfoSummary> <AirTravelerAvail> <PassengerTypeQuantity> <Code>ADT</Code> <Quantity>1</Quantity> </PassengerTypeQuantity> </AirTravelerAvail> <PriceRequestInformation> <PricingSource>Both</PricingSource> </PriceRequestInformation> </TravelerInfoSummary> </OTA_AirLowFareSearchRQ> <AdditionalShopRQData> <IncludeTax>true</IncludeTax> <IncludeFlightAvailability>true</IncludeFlightAvailability> <IncludeBaggage>true</IncludeBaggage> <IncludeMileage>true</IncludeMileage> </AdditionalShopRQData> </TSK_AirfareFlightShopRQ> </TSK_AirfareFlightShop> </IBEPlusRQ>"
				+ " <OrderPath>AirFareFlightShop/I</OrderPath> </IBEPlus_1_0>";
		
		
		
		
		//String identity = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Identity_1_0><User>Harris</User><Pwd>DDBEACBF90F2AFF7777900F8A576614A</Pwd></Identity_1_0>";
		
		String params = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <FlightShop_1_0> <BordPoint>PEK</BordPoint> <OffPoint>XMN</OffPoint> <DepartDate>2014-08-12</DepartDate> <DepartTimeBegin>0000</DepartTimeBegin> <DepartTimeEnd>2359</DepartTimeEnd> <Carrier>MF</Carrier> <NeedAV>1</NeedAV> <NeedRule>0</NeedRule> <OwLowest>1</OwLowest> <CabinClass/> <IsDirect>1</IsDirect> <JouryType>OW</JouryType> <PsgrType>AD</PsgrType> <PsgrNum>1</PsgrNum> <NeedCheck>1</NeedCheck> </FlightShop_1_0>";
		
		String reqStr2 = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"> <soap:Body>"
				+ " <DoXml xmlns=\"http://ibe.casagt.com/\"> <identity>" + escapeXMLText(identity) + "</identity>"
				+ " <request> " + escapeXMLText(reqStr1) + "</request> </DoXml> </soap:Body> </soap:Envelope>";
		
		String reqStr = reqStrSoap;
		
//		<TSK_AirfareFlightShop>
//			<TSK_AirfareFlightShopRQ>
//				<OTA_AirLowFareSearchRQ>
//					<POS>
//						<PseudoCityCode>PEK099</PseudoCityCode>
//						<AirportCode>PEK</AirportCode>
//						<ChannelID>1E</ChannelID>
//					</POS>
//					<OriginDestinationInformation>
//						<DepartureDate>
//							<Date>2013-10-15</Date>
//						</DepartureDate>
//						<OriginLocationCode>PEK</OriginLocationCode>
//						<DestinationLocationCode>HKG</DestinationLocationCode>
//					</OriginDestinationInformation>
//					<OriginDestinationInformation>
//						<DepartureDate>
//							<Date>2013-10-25</Date>
//						</DepartureDate>
//						<OriginLocationCode>HKG</OriginLocationCode>
//						<DestinationLocationCode>PEK</DestinationLocationCode>
//					</OriginDestinationInformation>
//					<TravelPreferences />
//				</OTA_AirLowFareSearchRQ>
//				<AdditionalShopRQData>
//					<IncludeFlightAvailability>true</IncludeFlightAvailability>
//				</AdditionalShopRQData>
//			</TSK_AirfareFlightShopRQ>
//		</TSK_AirfareFlightShop>
		
		
		// 构造 HttpClient 的实例
		HttpClient httpClient = new HttpClient();
		
		//调用验证信息
//		HttpState state = new HttpState();
//		Credentials credentials = new UsernamePasswordCredentials(username, pwd);
//		state.setCredentials(AuthScope.ANY, credentials);
//		httpClient.setState(state);
		
		// 创建 POST 方法的实例
		PostMethod postMethod = new PostMethod(serviceUrl);
		// 使用系统提供的默认的恢复策略
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
		new DefaultHttpMethodRetryHandler());
		try {
			// 请求参数的数据压缩
//			ByteArrayOutputStream out = new ByteArrayOutputStream();
//			GZIPOutputStream gzip = new GZIPOutputStream(out);
//			if (reqStr != null && !"".equals(reqStr)) {
//				gzip.write(reqStr.getBytes());
//			}
//			gzip.close();
//			
//			RequestEntity requestEntity = new ByteArrayRequestEntity(out.toByteArray());
				
			RequestEntity requestEntity = new ByteArrayRequestEntity(reqStr.getBytes());
			
			
			postMethod.setRequestEntity(requestEntity);
			postMethod.addRequestHeader("Content-Type", "text/xml;charset=UTF-8");
//			postMethod.addRequestHeader("accept-encoding", "gzip");
//			postMethod.addRequestHeader("content-encoding", "gzip");
			// 执行 getMethod
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				
				// 打印结果
				System.out.println( handle(postMethod) );
				throw new Exception("Invoke Get Method Failed, HttpStatus = " + statusCode);
			}

			// 打印结果
			System.out.println( handle(postMethod) );
			
//			out.flush();
//			out.close();
		} catch (Exception e) {
			// 发生致命的异常,可能是协议不对或者返回的内容有问题
			e.printStackTrace();
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
	}
}
