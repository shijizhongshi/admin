package com.ola.qh.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class Json
{

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T from(String str, Class<T> clazz) throws IOException
    {
	return mapper.readValue(str, clazz);
    }

    public static String to(Object json) throws IOException
    {
	return mapper.writeValueAsString(json);
    }
}
