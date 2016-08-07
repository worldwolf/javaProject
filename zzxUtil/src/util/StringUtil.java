/*    */ package com.fid.util;
import java.io.UnsupportedEncodingException;
/*    */ 
/*    */ import java.net.URLDecoder;
/*    */ import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*    */ 
/*    */
import java.util.zip.CRC32; public class StringUtil
/*    */ {
/*    */   public static boolean isNullOrEmpty(String input)
/*    */   {
/* 11 */     return (input == null) || (input.isEmpty());
/*    */   }
/*    */ 
/*    */   public static String toCamelCase(String input)
/*    */   {
/* 17 */     if (input.contains("_")) {
/* 18 */       input = input.replace("_", "-");
/*    */     }
/*    */ 
/* 21 */     if (input.contains("-")) {
/* 22 */       String[] words = input.split("-");
/* 23 */       input = "";
/* 24 */       for (String word : words) {
/* 25 */         input = input + word.substring(0, 1).toUpperCase() + word.substring(1);
/*    */       }
/*    */     }
/*    */     else
/*    */     {
/* 30 */       input = input.substring(0, 1).toUpperCase() + input.substring(1);
/*    */     }
/* 32 */     return input;
/*    */   }
/*    */ 
/*    */   public static String decodeURL(String value) {
/* 36 */     if (value != null)
/*    */     {
/*    */       try
/*    */       {
/* 40 */         value = URLDecoder.decode(value, "utf-8");
/*    */       }
/*    */       catch (Exception e) {
/* 43 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */ 
/* 47 */     return value;
/*    */   }
/*    */ 
/*    */   public static String encodeURL(String value) {
/* 51 */     if (value != null)
/*    */     {
/*    */       try
/*    */       {
/* 55 */         value = URLEncoder.encode(value, "utf-8");
/*    */       }
/*    */       catch (Exception e) {
/* 58 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */ 
/* 62 */     return value;
/*    */   }
/*    */ 
/*    */   public static String trimPunctuation(String value) {
/* 66 */     if (value == null) return null;
/*    */ 
/* 68 */     return value.replaceAll("\\pP", "").replaceAll("\\s*", "");
/*    */   }

		   /**
		    * 判断是否为空白字符串 或为null
		    * @param value
		    * @return
		    */
		   public static boolean isBlank(String value){
			   return value == null || value.trim().equals("");
		   }
		   
		   /**
		    * crc32
		    * @param bytes
		    * @return
		    */
		   public static Long getCheckCode(byte[] bytes){
			   CRC32 crc32 = new CRC32();
			   crc32.update(bytes);
			   Long checkCode = Long.valueOf(crc32.getValue());
			   return checkCode;
		   }
		   
		   
		   
		   /**
			 * 过滤4个字节字符
			 * @param text
			 * @return
			 * @throws UnsupportedEncodingException
			 */
			 public static String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException {
		        byte[] bytes = text.getBytes("utf-8");
		        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
		        int i = 0;
		        while (i < bytes.length) {
		            short b = bytes[i];
		            if (b > 0) {
		                buffer.put(bytes[i++]);
		                continue;
		            }
		            b += 256;
		            if ((b ^ 0xC0) >> 4 == 0) {
		                buffer.put(bytes, i, 2);
		                i += 2;
		            }
		            else if ((b ^ 0xE0) >> 4 == 0) {
		                buffer.put(bytes, i, 3);
		                i += 3;
		            }
		            else if ((b ^ 0xF0) >> 4 == 0) {
		                i += 4;
		            }
		        }
		        buffer.flip();
		        return new String(buffer.array(), "utf-8");
		    }
		   
			 
			 
			//向后推移，双个双引号遇见， 为分割条件  处理csv
			public static List<String> analysisCsv(String data){
					List<String> resultList = new ArrayList<>();
					if(data == null) return resultList;
					if(data.equals("")) resultList.add("");
					String result = null;
					int signIndex = 0;
					int beginIndex = 0;
					int endIndex = 0;
					for (int i = 0; i < data.length(); i++) {
						if(data.charAt(i) == '"'){
							signIndex++;
						}
						if(signIndex %2 == 0){
							if(i == data.length() -1 || data.charAt(i+1) == ','){
								endIndex = i+1;
								result = data.substring(beginIndex,endIndex);
								if(result.startsWith("\"")){
									result = result.substring(1, result.length()-1);
									result = result.replace("\"\"", "\"");
								}
								resultList.add(result);
								beginIndex = endIndex+1;
							}
						}
					}
					return resultList;
				}
		  
			
			/**
			 * 队列比较
			 * @param <T>
			 * @param a
			 * @param b
			 * @return
			 */
			public static <T extends Comparable<T>> boolean compare(List<T> a, List<T> b) {
			  if(a.size() != b.size())
			    return false;
			  Collections.sort(a);
			  Collections.sort(b);
			  for(int i=0;i<a.size();i++){
			    if(!a.get(i).equals(b.get(i)))
			      return false;
			  }
			  return true;
			}
		}
