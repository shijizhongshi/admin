package com.ola.qh.message.android;

import com.ola.qh.message.AndroidNotification;

public class AndroidUnicast extends AndroidNotification
{
    public AndroidUnicast(String appkey, String appMasterSecret) throws Exception
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