package com.dglt.comm.util.gson;

import java.util.Date;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil 
{
	private static GsonBuilder gsonBuilder;
	private static Gson gson;
	
	static
	{
		gsonBuilder=new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
		gsonBuilder.registerTypeAdapter(Map.class, new MapSerializer());
		gson=gsonBuilder.create();
	}
	
	public static String toJson(Object object)
	{
		return gson.toJson(object);
	}
	
	public static String toJson(Object[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static String toJson(char[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static String toJson(String[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static String toJson(short[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static String toJson(int[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static String toJson(long[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static String toJson(float[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static String toJson(double[] arr)
	{
		return gson.toJson(arr);
	}
	
	public static JSONObject getJSONObject(String jsonString)
	{
		JSONObject o=null;
		try
		{
			o=new JSONObject(jsonString);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return o;
	}
	
	public static JSONArray getJSONArray(String jsonString)
	{
		JSONArray o=null;
		try
		{
			o=new JSONArray(jsonString);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return o;
	}
	
	public static Object fromJson(String jsonString,Class _class)
	{
		return gson.fromJson(jsonString,_class);
	}
}
