package com.fid.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.el.parser.ParseException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class GrapUtil {
	private static final Logger log = Logger.getLogger(GrapUtil.class);
	
	private static HttpClient httpClient = new DefaultHttpClient();  
	

	
	public static String get(String url, List<NameValuePair> params) {  
        String body = null;  
        try {  
            // Get请求  
            HttpGet httpget = new HttpGet(url);  
            // 设置参数  
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params));  
            httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));  
            // 发送请求  
            HttpResponse httpresponse = httpClient.execute(httpget);  
            // 获取返回数据  
            HttpEntity entity = httpresponse.getEntity();  
            body = EntityUtils.toString(entity);  
            if (entity != null) {  
                entity.consumeContent();  
            }  
        }catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (URISyntaxException e) {  
            e.printStackTrace();  
        }  
        return body;  
    }  
	
	/**
	 * 描述：sendUrl 发送URL的post请求
	 * 
	 * @param urlStr
	 * @param params
	 * @return
	 * @CreateOn 2010-10-28 下午01:39:00
	 * @author zzx
	 */
	public static String sendUrl(String urlStr, String params) {
		log.info(urlStr + "?" + params);
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		try {
			URL realUrl = new URL(urlStr);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			if(params != null){
				out.print(params);
			}
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			log.info(result);
			System.out.println(result);
		} catch (Exception e) {
			log.error(GrapUtil.class.getName(), e);
		} finally {
			try {
				if (out != null) {
					out.close();
					out = null;
				}
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception ex) {
				log.error(GrapUtil.class.getName(), ex);
			}
		}
		return result.toString();
	}
	
	
	public static String sendUrl(String url,List <NameValuePair> params){
		String msg = "请求异常";
		 try {
	            HttpClient client = HttpClients.createDefault(); 
	            HttpPost httpPost = new HttpPost(url);
	            httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));  
	            HttpResponse response=client.execute(httpPost);
	            msg ="推送返回的结果串:"+ EntityUtils.toString(response.getEntity());
	            System.out.println(msg);
	            return msg;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return msg;
	        }
	}
	
	public static JSONObject sendUrlRetJson(String url,List <NameValuePair> params){
		 try {
	            HttpClient client = HttpClients.createDefault(); 
	            HttpPost httpPost = new HttpPost(url);
	            httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));  
	            HttpResponse response=client.execute(httpPost);
	            return JSONObject.fromObject(EntityUtils.toString(response.getEntity()));
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
		 
	}
	
	public static JSONArray sendUrlRetJsonArray(String url,List <NameValuePair> params){
		 try {
	            HttpClient client = HttpClients.createDefault(); 
	            HttpPost httpPost = new HttpPost(url);
	            httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));  
	            HttpResponse response=client.execute(httpPost);
	            return JSONArray.fromObject(EntityUtils.toString(response.getEntity()));
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
		 
	}
	
	public static void test(){
		 try {
	            String url="http://finance.woqutz.inzwc.com/push/topic";
	            HttpClient client = HttpClients.createDefault(); 
	            HttpPost httpPost = new HttpPost(url);
	            List <NameValuePair> params = new ArrayList<NameValuePair>();  
	            params.add(new BasicNameValuePair("oper", "add"));
	            params.add(new BasicNameValuePair("obj", "{'id':'0','channelId':'2','labelColor':'#000','name':'测试主题upate','hotOrder':'2'}"));
	            httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));  
	            HttpResponse response=client.execute(httpPost);
	            System.out.println("通知返回结果串:"+ EntityUtils.toString(response.getEntity()));
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("请求异常");
	        }
	}
	
	public static void main(String[] args) {
		String url = "http://news.21fid.com/initHistoryIndex?topicId=171";
		String params = null;
		GrapUtil.sendUrl(url, params);
	}
	
}
