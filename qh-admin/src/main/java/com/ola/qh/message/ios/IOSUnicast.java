package com.ola.qh.message.ios;

import com.ola.qh.message.IOSNotification;

public class IOSUnicast extends IOSNotification
{
    public IOSUnicast(String appkey, String appMasterSecret) throws Exception
    {
	setAppMasterSecret(appMasterSecret);
	setPredefinedKeyValue("appkey", appkey);
	this.setPredefinedKeyValue("type", "unicast");
    }

    public void setDeviceToken(String token) throws Exception
    {
	setPredefinedKeyValue("device_tokens", token);
    }
}
