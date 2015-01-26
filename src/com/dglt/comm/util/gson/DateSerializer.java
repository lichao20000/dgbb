package com.dglt.comm.util.gson;

import java.lang.reflect.Type;
import java.util.Date;

import com.dglt.comm.util.DateTimeUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateSerializer implements JsonSerializer<Date> 
{
	public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context)
	{
	    return new JsonPrimitive(DateTimeUtil.formatDateTime(src));
	}
}
