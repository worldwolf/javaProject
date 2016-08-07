package com.fid.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.ytwl.cms.robots.common.domain.RbsConclusion;
import com.ytwl.cms.robots.common.domain.RbsConclusionTags;
import com.ytwl.cms.robots.common.domain.RbsTags;
import com.ytwl.cms.robots.common.domain.RbsTagsWords;
import com.ytwl.cms.robots.common.domain.RbsWords;

import net.sf.json.JSONObject;

/**
 * 将实体反转成reids数据
 * @description:
 * @author ZhangZhenxing
 * @email: 1245812397@qq.com
 */
public class RedisEntityUtil {
	
	
	/**
	 * 规则： 实体名称+ _字段名 ，根据字段名去找id，id去找实体
	 * @param entityName
	 * @param object
	 */
	public  static  void insertEntityToRedis(Object object){
		JSONObject jsonObject = JSONObject.fromObject(object);
		Iterator keys = jsonObject.keys();
		while(keys.hasNext()){
			Object key = keys.next();
			String value = RedisAPI.getMapValue(object.getClass().getSimpleName()+"_"+key, ""+jsonObject.get(key)); 
			if(value == null){
				RedisAPI.setMapValue(object.getClass().getSimpleName()+"_"+key, ""+jsonObject.get(key),jsonObject.get("id").toString());
			} else{
				Set<String> ids = new HashSet<>();
				CollectionUtils.addAll(ids, value.split(","));  
				if(!ids.contains(jsonObject.get("id"))){
					ids.add(jsonObject.get("id").toString());
					RedisAPI.setMapValue(object.getClass().getSimpleName()+"_"+key, ""+jsonObject.get(key),String.join(",", ids));
				}
			}
		}
		RedisAPI.setMapValue(object.getClass().getSimpleName(), jsonObject.get("id").toString(),jsonObject.toString());
	}
	
	/**
	 * 获取JSONObject对象
	 * @param clz
	 * @param columnName
	 * @param columnValue
	 * @return
	 */
	public static List<JSONObject> getJSONObjectByColumn(Class clz ,String columnName, String columnValue){
		String ids = RedisAPI.getMapValue(clz.getSimpleName()+"_"+columnName, columnValue);
		if(ids == null || ids.trim().equals(""))return null;
		List<JSONObject> list = new ArrayList<>();
		for(String id :ids.split(",")){
			String objectStr =  RedisAPI.getMapValue(clz.getSimpleName(), id);
			if(objectStr == null || objectStr.trim().equals(""))return null;
			JSONObject jsonObject = JSONObject.fromObject(objectStr);
			list.add(jsonObject);
		}
		return list;
	}
	
	/**
	 * 获取Object对象
	 * @param clz
	 * @param columnName
	 * @param columnValue
	 * @return
	 */
	public static List<Object> getObjectByColumn(Class clz ,String columnName, String columnValue){
		String ids = RedisAPI.getMapValue(clz.getSimpleName()+"_"+columnName, columnValue);
		if(ids == null || ids.trim().equals(""))return null;
		List<Object> list = new ArrayList<>();
		for(String id :ids.split(",")){
			String objectStr =  RedisAPI.getMapValue(clz.getSimpleName(), id);
			if(objectStr == null || objectStr.trim().equals(""))return null;
			JSONObject jsonObject = JSONObject.fromObject(objectStr);
			try {
				list.add(JsonWithObjectUtil.fromJsonToJava(jsonObject, clz));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 获取想要的字段数据
	 * @param clz
	 * @param columnName
	 * @param columnValue
	 * @param wantColumnName
	 * @return
	 */
	public static List<String> getWantColumnByColumn(Class clz ,String columnName, String columnValue,String wantColumnName){
		List<String> wantColumns = new ArrayList<>();
		List<JSONObject> jsonObjects = getJSONObjectByColumn(clz,columnName,columnValue);
		for (JSONObject jsonObject : jsonObjects) {
			String wantColumn = jsonObject.getString(wantColumnName);
			wantColumns.add(wantColumn);
		}
		return wantColumns;
	}
	
	public static void main(String[] args) {
		
//		String str = RbsTags.class.getSimpleName()+ "_id";
//		System.out.println(RedisAPI.getMapValue(str,"2048"));
//		System.out.println(RedisAPI.getMap(RbsWords.class.getSimpleName()));
//		System.out.println(RedisAPI.getMap(RbsTagsWords.class.getSimpleName()));
		System.out.println(RedisAPI.getMap(RbsTags.class.getSimpleName()));
//		System.out.println(RedisAPI.getMap(RbsConclusionTags.class.getSimpleName()));
//		System.out.println(RedisAPI.getMap(RbsConclusion.class.getSimpleName()));
	}
}
