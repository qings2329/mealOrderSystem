package practise;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class DragonpassInterface {
	
	public static void main(String[] args) {
		String apiName = "callVip";
		apiName = "getAirport";
		String url = "http://interfacetest.dragonpass.com.cn:8664/extinf/ws/services/" + apiName + "?sign=";
		//参数拼接
		//time参数为当前系统毫秒数
		long timeMillis = System.currentTimeMillis();
		String data = "agentid=112&name=张三&mobile=13512345678&expiry=20160101&point=2&time=" + timeMillis + "&ver=1.0";
		data = "channel=75&cardno=6168201300000214&time=" + timeMillis + "&ver=1.0";
		data = "agentid=122&type=0&time=" + timeMillis + "&ver=1.0";
		HttpURLConnection http = null;
		try{
			//参数转码
			data =  URLEncoder.encode(data,"UTF-8");
//			Log4jUtil.info("client data: "+data);
			//秘钥key的截取值,这里的KEY由龙腾出行给出
			
			String KEY = "MCwCFD0zwdmA47L63RaVKNfVgYP0HkAhQqzpAwdA0wrS5nCDxTiu1pymQ0CFdaAqC99GJND6NAKZe5J2GIEY0KAUAlox9qYInmG193v5U6smFq6VIwCFCLJQJ0HZkdHWpqn8pqRzba1zCTAhRg9RzFV2u4eyiP209lVDv28QwCFCxEiX5e65H2Z2BKnybXuNGx7M4AhRL3L6mApWMHjtdmg3Nqysq3zrhAwCFGblBYAQpjOVHoeIF3JADN1jUFBAhRmXaLDwsafZ0JlWIFCPX628GmnAgwCFGClbFmQnTAP4j7SoMon6mSy4ev5AhRn7mNpBd2xs6IhsX4ZMmyL7G9JZg4CFQCBK5V7qyZpGMkNjq91FApd5qcQIVAI3az6jYTv5YPqMqXUGUInHqfsjwCFH9LdfVtsvfXjb2fLSJT0AmLLBBAhQ9XOhocJ9h94mbbOa68uhznLTmoQwCFC1Qk8RzXVFyf8E2IIUX4TayVZgkAhQ3AkQLHcYGBAS96te3MIk82tckDwwCFHnSHVdCb6ViMrXnVi9aRuRZajyZAhQAE6laNozVRCq0BDWx3vDJC1zbwwCFFRqHHiiHSasikDKsAn62RA5bwCeAhR9zoLyXGqWVUieg5EGDjQhRpf2gwCFC3NZnLSW3vYL3KIffYd0QFGid49AhQtvysjIQMx87gAO7SClQSiDWI8A0CFBIVKINMORAGwpTEAZkLGgromsPpAhUAgfA3zUcmEjWxGKKLH9LV3b1iQwCFCvQl8NkT0tf1h8qa0WnRNOenAhRgGDCmH2RnzjCHE3Hmkv9tTbtJag0CFFD3qfhqXuMGMSBXUdQraHD2CcBAhUAkXHF0dGxQHgPLYWKj3FlD8UVoyk0CFQCXARhEUHSyZW2VmfG0jx5If50PwIUHmGUAgji5k9Vf8ETa9QLnSmTCYwCFFbJTTtp5nInqzUSiMBz2VfjLvDnAhRzHYyQ3qVTXKIy6NCSKyTq6qgGK1Lxni1jAf3L9GQ";
			
			String subKey = KEY.substring((int)(timeMillis % 1000));
			//MD5生成签名
			String send = MD5Encrypt.encrypt(data+subKey);
			System.out.println("client sign: "+send);
			url += send;
			URL ur = new URL(url);
			http = (HttpURLConnection)ur.openConnection();
			http.setConnectTimeout(10000);
			http.setRequestProperty("content-type", "text/html");
			http.setRequestMethod("POST");// 设定请求的方法为"POST"
			http.setDoInput(true);// 设置是否从httpUrlConnection读入
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在 http正文内，因此需要设为true, 默认情况下是false; 
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.connect();
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(http.getOutputStream(), "UTF-8"));  
	        out.write(data);      
	        out.flush(); 
			//接收返回数据
			InputStream is = http.getInputStream();
			byte [] b = new byte[1024];
			int c = -1;
			StringBuilder ret = new StringBuilder();
			while((c=is.read(b))!= -1){
				ret.append(new String(b,0,c,"UTF-8"));
			}
			System.out.println (URLDecoder.decode(ret.toString(), "UTF-8"));
			//反序列化
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
}
