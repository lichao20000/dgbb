package com.dglt.comm.util.gson;

import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	private static GsonBuilder gsonBuilder = new GsonBuilder();
	private static Gson gson;
	static
	{
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		gsonBuilder.registerTypeAdapter(Date.class,new DateSerializer());
		gsonBuilder.registerTypeAdapter(Map.class,new MapSerializer());
		gson=gsonBuilder.create();
	}
	
	public static String toJson(Object obj)
	{
		return gson.toJson(obj);
	}

	public static String toJson(Object[] arr)
	{
		return gson.toJson(arr);
	}
}
