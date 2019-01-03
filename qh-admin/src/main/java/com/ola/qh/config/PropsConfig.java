package com.ola.qh.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("systemConfigProperties")
@Scope("singleton")
public final class PropsConfig
{

    private final static String PROPERTIES_FILE_LOCATION = "config.properties";

    public final static String GOBAL_SEPARATOR = "global.separator";
    // The key name of the values which specify the mobiles of the admin.
    // the mobiles are separated by <code>GOBAL_SEPARATOR</code>
    // public final static String ADMIN_MOBILE = "admin.mobile";
    // The key name of the values which specify the emails of the admin.
    // the emails are separated by <code>GOBAL_SEPARATOR</code>
    // public final static String ADMIN_EMAIL = "admin.email";

    public final static ClassLoader RESOURCE_LOADER = PropsConfig.class.getClassLoader();

    public static String getString(String key)
    {
	return refresh().getProperty(key, "");
    }

    public static String[] getStrings(String key)
    {
	Properties p = refresh();
	String r = p.getProperty(key, "");
	return r.split(p.getProperty(GOBAL_SEPARATOR, ","));
    }

    public static int getInt(String key)
    {
	Properties p = refresh();
	String r = p.getProperty(key, "0");
	return Integer.parseInt(r.trim());
    }

    public static long getLong(String key)
    {
	Properties p = refresh();
	String r = p.getProperty(key, "0");
	return Long.parseLong(r.trim());
    }

    public static double getDouble(String key)
    {
	Properties p = refresh();
	String r = p.getProperty(key, "0.0");
	return Double.parseDouble(r.trim());
    }

    public static boolean getBoolean(String key)
    {
	Properties p = refresh();
	String r = p.getProperty(key, "false");
	return r.trim().equals("true");
    }

    public static Map<String, String> toMap()
    {
	Properties pr = refresh();
	Map<String, String> rm = new HashMap<>();
	pr.forEach((k, v) -> {
	    rm.put((String) k, (String) v);
	});
	return rm;
    }

    private static Properties refresh()
    {
	Properties props = new Properties();
	InputStream is = RESOURCE_LOADER.getResourceAsStream(PROPERTIES_FILE_LOCATION); // RESOURCE_LOADER.getResourceAsStream(PROPERTIES_FILE_LOCATION);
	try
	{
	    props.load(is);
	    is.close();
	}
	catch (IOException e)
	{
	    // consume the exception silently.
	}

	return props;
    }

}
