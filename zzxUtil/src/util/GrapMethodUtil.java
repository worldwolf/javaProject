package com.fid.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;


public class GrapMethodUtil {
	
	Logger logger = Logger.getLogger(GrapMethodUtil.class);
	
	/**
	 * 根据method获取字符串
	 * 
	 * @param method
	 * @return
	 */
	/**
	 * 根据method获取字符串
	 * 
	 * @param method
	 * @return
	 */
	public String urlGetString(String strURL, String encode,Map<String,String>  param) {
		encode = encode == null ? "gb2312" : encode;
		StringBuffer stringBuffer = new StringBuffer("");
		InputStream inputStream = null;
		BufferedReader br = null;
		try {
			URL url = new URL(strURL);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; 4399Box.1334; 4399Box.1334)");
			httpConn.setRequestProperty("Host", "eclick.baidu.com");
			httpConn.setConnectTimeout(10000);
			httpConn.setReadTimeout(15000);
			if (httpConn.getResponseCode() != httpConn.HTTP_OK)
				return stringBuffer.toString();
			inputStream = httpConn.getInputStream();
			br = new BufferedReader(new InputStreamReader(inputStream, encode));
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
		} catch (IOException e) {
			logger.error(GrapMethodUtil.class.getName() + "  urlGetString抓取网页失败:" + strURL + "\r\n" + e.getMessage());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					logger.error(
							GrapMethodUtil.class.getName() + "  urlGetString流未关闭:" + strURL + "\r\n" + e.getMessage());
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					logger.error(
							GrapMethodUtil.class.getName() + "  urlGetString流未关闭:" + strURL + "\r\n" + e.getMessage());
				}
			}
		}
		return stringBuffer.toString();
	}

}
