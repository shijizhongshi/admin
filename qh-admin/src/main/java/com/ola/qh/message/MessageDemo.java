package com.ola.qh.message;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ola.qh.message.android.AndroidUnicast;
import com.ola.qh.message.ios.IOSUnicast;
@RestController
public class MessageDemo
{
	////////安卓的appKey
    private static String appkey = "592e50b4b27b0a33ac000c9e";
    private static String appMasterSecret = "xhrmsdc203gel1ul4zwowda2vzj7prnh";
    
    ///////ios的appkey
    private static String iosappkey = "59687ea4f43e482fc00006fe";
    private static String iosappMasterSecret = "vmkstuxgam6dynskxgiavdxnlv6kwmjm";
    
    private static PushClient client = new PushClient();

    public static void sendAndroidUnicast(String ticker, String title, String text, String deviceToken) throws Exception
    {
	AndroidUnicast unicast = new AndroidUnicast(appkey, appMasterSecret);
	// TODO Set your device token
	unicast.setDeviceToken(deviceToken);
	unicast.setTicker(ticker);
	unicast.setTitle(title);
	unicast.setText(text);
	// 跳转到指定的页面(问安卓他们的这个是什么)
	unicast.goActivityAfterOpen("com.ola.qh.act.personal.HomeMessageAct");
	unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
	unicast.setPlayVibrate(true);
	unicast.setPlaySound(true);
	unicast.setPlayLights(true);
	// TODO Set 'production_mode' to 'false' if it's a test device.
	// For how to register a test device, please see the developer doc.
	unicast.setProductionMode();
	// Set customized fields
	// unicast.setExtraField("test", "helloworld");
	client.send(unicast);
    }

    public static void sendIOSUnicast(String alert, String content, String deviceToken) throws Exception
    {
	IOSUnicast unicast = new IOSUnicast(iosappkey, iosappMasterSecret);
	// TODO Set your device token
	// 设备的型号
	unicast.setDeviceToken(deviceToken);
	unicast.setSound("default");
	unicast.setBadge(1);
	unicast.setBody(content);
	unicast.setTitle(alert);
	/*unicast.setSubtitle("3");*/
	
	// TODO set 'production_mode' to 'true' if your app is under production mode
	//unicast.setTestMode();
	// Set customized fields
	client.send(unicast);
    }
    
    
    @RequestMapping(value = "/api/message/test", method = RequestMethod.GET)
    public void send() throws Exception
    {
	///sendAndroidUnicast("测试通知", "测试通知","请查看通知", "e82ae395580dd0669fe3cf6f9e83743610c4a68d5092398186b7ce41d5712c59");
	sendIOSUnicast("3333","111","e82ae395580dd0669fe3cf6f9e83743610c4a68d5092398186b7ce41d5712c59");
    
    }

}
