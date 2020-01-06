package com.hrc.commons.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class JsonUtils {

	public static Map<String,String> jsonStringToMap(String json){
		return new Gson().fromJson(json, new TypeToken<HashMap<String, String>>(){}.getType());
	}

	public static Map<String,Map<String,String>> jsonStringArrayToMap(String jsonArrays){
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonArrays);
		Map<String,Map<String,String>> arrayMap = new HashMap<String,Map<String,String>>();
		for(int i=0 ;i< jsonArray.size() ;i++) {
			HashMap<String,String> map = new Gson().fromJson(jsonArray.get(i), new TypeToken<HashMap<String, String>>(){}.getType());
			arrayMap.put(String.valueOf(i), map);
		}
		return arrayMap;
	}

}