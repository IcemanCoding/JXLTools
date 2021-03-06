package com.juxinli.tools.web.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class HttpClientUtils {
	
public static void main( String[] args ) {
		
		String res = sendGet( "http://172.16.1.15:81/", "service/billing/user", "org=yangqianguan&env=www&rpt_version=4.1&st=2016-09-28&et=2016-09-29" );
//		System.out.println( res );
		JSONObject json = JSONObject.parseObject( res );
//		System.out.println( json + "---" );
		JSONObject json2 = json.getJSONObject( "result" );
		JSONArray array = json2.getJSONArray( "details" );
//		JSONObject argsd = json.getJSONObject( "request_args" );
		System.out.println( array.size() + "====" + json2.getInteger( "user_cnt" ) );
//		System.out.println( array );
		
	}

	public static String sendGet( String url, String method, String param ) {

		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + method + "?" + param;
			URL realUrl = new URL( urlNameString );
			// 打开和URL之间的连接
			HttpURLConnection connection = ( HttpURLConnection ) realUrl
					.openConnection();
			connection.setConnectTimeout( 60000 );
			// 设置通用的请求属性
			connection.setRequestProperty( "accept", "*/*" );
			connection.setRequestProperty( "connection", "Keep-Alive" );
			connection.setRequestProperty( "user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)" );

			// 建立实际的连接
			connection.connect();
			
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader( new InputStreamReader(
					connection.getInputStream() ) );
			String line;
			while ( ( line = in.readLine() ) != null ) {
				result += line;
			}
		} catch ( Exception e ) {

		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if ( in != null ) {
					in.close();
				}
			} catch ( Exception e2 ) {
				e2.printStackTrace();
			}
		}

		return result;
	}

}
