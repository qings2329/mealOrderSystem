package practise;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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
	
	public static void main(String[] args) {
		//实际使用时,请设置正确的变量值
		//用户名
		String username = "Harris";
		//密码
		String pwd = "20150414";
		//服务地址
		String serviceUrl = "http://www.cascc.cn/";
//		String serviceUrl = "http://espeed.travelsky.com/develop/xml/AirFareFlightShop/I";
		
		
		
		//请求 XML
		
		String reqStr = "<TSK_AirfareFlightShop> <TSK_AirfareFlightShopRQ> <OTA_AirLowFareSearchRQ> <POS> <PseudoCityCode>PEK099</PseudoCityCode> <AirportCode>PEK</AirportCode> <ChannelID>1E</ChannelID> </POS> <OriginDestinationInformation> <DepartureDate> <Date>2013-10-15</Date> </DepartureDate> <OriginLocationCode>PEK</OriginLocationCode> <DestinationLocationCode>HKG</DestinationLocationCode> </OriginDestinationInformation> <OriginDestinationInformation> <DepartureDate> <Date>2013-10-25</Date> </DepartureDate> <OriginLocationCode>HKG</OriginLocationCode> <DestinationLocationCode>PEK</DestinationLocationCode> </OriginDestinationInformation> <TravelPreferences /> </OTA_AirLowFareSearchRQ> <AdditionalShopRQData> <IncludeFlightAvailability>true</IncludeFlightAvailability> </AdditionalShopRQData> </TSK_AirfareFlightShopRQ> </TSK_AirfareFlightShop>";
		
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
		HttpState state = new HttpState();
		Credentials credentials = new UsernamePasswordCredentials(username, pwd);
		state.setCredentials(AuthScope.ANY, credentials);
		httpClient.setState(state);
		// 创建 POST 方法的实例
		PostMethod postMethod = new PostMethod(serviceUrl);
		// 使用系统提供的默认的恢复策略
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
		new DefaultHttpMethodRetryHandler());
		try {
			// 请求参数的数据压缩
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(out);
			if (reqStr != null && !"".equals(reqStr)) {
				gzip.write(reqStr.getBytes());
			}
			gzip.close();
			RequestEntity requestEntity = new ByteArrayRequestEntity(out
			.toByteArray());
			postMethod.setRequestEntity(requestEntity);
			postMethod.addRequestHeader("Content-Type",
			"text/html;charset=UTF-8");
			postMethod.addRequestHeader("accept-encoding", "gzip");
			postMethod.addRequestHeader("content-encoding", "gzip");
			// 执行 getMethod
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("Invoke Get Method Failed, HttpStatus = " + statusCode);
			}
			// 返回结果的数据解压
			InputStream is = postMethod.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(
			new GZIPInputStream(is)));
			StringBuffer respStr = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				respStr.append(line);
			}
			// 打印结果
			System.out.println(respStr.toString());
			out.flush();
			out.close();
			is.close();
		} catch (Exception e) {
			// 发生致命的异常,可能是协议不对或者返回的内容有问题
			e.printStackTrace();
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
	}
}
