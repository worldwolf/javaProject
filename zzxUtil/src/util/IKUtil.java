package com.fid.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.dic.Dictionary;

import com.ytwl.cms.robots.common.param.RbsCommonParams;

public class IKUtil {
	public static void main(String[] args) {
		initDcitionnary();
		String content = "营业利润同比下降";
		content = content.replaceAll(RbsCommonParams.REGEX, RbsCommonParams.REGEX_DB);
		System.out.println(content);
		addIKword(RbsCommonParams.REGEX_DB);
		addIKword(RbsCommonParams.REGEX);
		IKAnalysis(content);
	}
		
	public static void initDcitionnary(){
		Dictionary.initial(DefaultConfig.getInstance());
	}
	public static void addIKwords(Collection<String> collectionKeys){
		Dictionary.getSingleton().addWords(collectionKeys);
	}
	
	public static void addIKword(String key){
		Set<String> keySet = new  HashSet<>();
		keySet.add(key);
		addIKwords(keySet);
	}
	
	 public static List<String> IKAnalysis(Object obj) {  
	        List<String> keywordList = new ArrayList<String>();
	        if(obj == null) return keywordList;
	        String str = obj.toString();
	        try {  
	            byte[] bt = str.getBytes();  
	            InputStream ip = new ByteArrayInputStream(bt);  
	            Reader read = new InputStreamReader(ip);  
	            IKSegmenter iks = new IKSegmenter(read,true);//true开启只能分词模式，如果不设置默认为false，也就是细粒度分割  
	            Lexeme t;  
	            while ((t = iks.next()) != null) {  
	                keywordList.add(t.getLexemeText());  
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }   
	        System.out.println(keywordList);
	        return keywordList;  
	    }  
}
