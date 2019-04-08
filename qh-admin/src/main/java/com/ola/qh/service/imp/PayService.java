package com.ola.qh.service.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.ola.qh.entity.BizContent;
import com.ola.qh.service.IPayService;
import com.ola.qh.util.Json;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.MD5;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.RBuilder;

/**
 * 调用支付宝和微信的接口
* @ClassName: PayService  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author guoyuxue  
* @date 2018年11月28日  
*
 */
@Service
public class PayService implements IPayService {


	public static DefaultAlipayClient alipayclient() {

		DefaultAlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", Patterns.ALI_APPID, Patterns.ALIPAY_PRIVATE_KEY,
				"json", "utf-8",
				// 支付宝公钥 (ALIPAY_PUBLIC_KEY)，RSA支付宝公钥固定的，推荐使用rsa2的，这里先使用的rsa测试
				Patterns.ALIPAY_PUBLIC_KEY, "RSA2");
		return alipayClient;
	}
	@Override
	public Map<String, String> aliOrdersRefund(String extranceno,double money) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		BizContent bigContent = new BizContent();
		bigContent.setOut_trade_no(extranceno);
		bigContent.setOut_request_no(KeyGen.uuid()+"");
		
		bigContent.setRefund_amount(0.01);
		//bigContent.setRefund_amount(money);
		bigContent.setRefund_reason("正常退款");
		String biz_content=Json.to(bigContent);
		request.setBizContent(biz_content);
		
		AlipayTradeRefundResponse response = alipayclient().execute(request);
		if(response.isSuccess()){
		    map.put("status", "0");
		} else {
		    map.put("status", "1");
		    map.put("error", response.getSubMsg());
		}
		return map;
	}
	 
	 
	
	
	 
    @Override
    public Map<String, String> wxOrderRefund(String extranceno,int totalfee,int refundfee) throws Exception
    {
	
	Map<String, String> message = new HashMap<String, String>();
	KeyStore keyStore = KeyStore.getInstance("PKCS12");
	FileInputStream instream = new FileInputStream(new File("/home/apiclient_cert.p12"));
	/*FileInputStream instream = new FileInputStream(new File("/common/apiclient_cert.p12"));*/
	try
	{
	    keyStore.load(instream, Patterns.wxmchId.toCharArray());
	}
	finally
	{
	    instream.close();
	}

	// Trust own CA and all self-signed certs
	SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, Patterns.wxmchId.toCharArray()).build();
	// Allow TLSv1 protocol only
	SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]
	{
		"TLSv1"
	}, null, new DefaultHostnameVerifier());
	CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
	try
	{
	    Map<String, String> requestParams = new HashMap<String, String>();
	    requestParams.put("appid", Patterns.wxappId);// 公众账号ID
	    requestParams.put("mch_id", Patterns.wxmchId);// 商户号
	    requestParams.put("nonce_str", new String(new RBuilder().length(16).hasletter(true).next()));// 随机字符串，不长于32位
	    requestParams.put("out_trade_no", extranceno);// 商户系统内部的订单号
	    requestParams.put("out_refund_no", KeyGen.uuid() + "");
	    requestParams.put("total_fee", "" + 1);// 单位分
	    requestParams.put("refund_fee", "" + 1);// APP和网页支付提交用户端ip
	    
	   /* requestParams.put("total_fee", "" + totalfee);// 单位分
	    requestParams.put("refund_fee", "" + refundfee);// APP和网页支付提交用户端ip
*/	    requestParams.put("op_user_id", Patterns.wxmchId);// 交易类型

	    String signkey = Patterns.wxsignkey;/////秘钥
	    requestParams.put("sign", MD5.digest(compositeWXPayKeyValuePaires(requestParams, signkey)).toUpperCase());

	    HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
	    StringBuilder xml = creatPostEntity(requestParams);
	    httppost.setEntity(new StringEntity(xml.toString(), "utf-8"));
	    System.out.println("executing request" + httppost.getRequestLine());
	    CloseableHttpResponse response = httpclient.execute(httppost);
	    try
	    {
		HttpEntity entity = response.getEntity();

		System.out.println("----------------------------------------");
		System.out.println(response.getStatusLine());
		if (entity != null)
		{
		    System.out.println("Response content length: " + entity.getContentLength());
		    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
		    String text;
		    
		    SAXReader saxReader = new SAXReader();

		    org.dom4j.Document document = saxReader.read(entity.getContent());
		    org.dom4j.Element root = document.getRootElement();
		    // 获取所有子元素
		    List<org.dom4j.Element> childList = root.elements();
		   
		    // 迭代输出
		    for (Iterator<org.dom4j.Element> iter = root.elementIterator(); iter.hasNext();)
		    {
			org.dom4j.Element e = (org.dom4j.Element) iter.next();
			message.put(e.getName(), e.getText());
		    }
		    
		   if("SUCCESS".equals(message.get("result_code"))){
		       message.put("status", "0");
		       
		   }else{
		       message.put("status", "1");
		       message.put("error", message.get("err_code_des"));
		   }
		    
		}
		EntityUtils.consume(entity);
	    }
	    finally
	    {
		response.close();
	    }
	}
	finally
	{
	    httpclient.close();
	}
	return message;
    }
    
    private StringBuilder creatPostEntity(Map<String, String> result)
    {
	StringBuilder xml = new StringBuilder();
	xml.append("<xml>\n");
	result.entrySet().stream().sorted((v1, v2) -> v1.getKey().compareTo(v2.getKey())).forEach(entry -> {
	    if ("body".equals(entry.getKey()) || "sign".equals(entry.getKey()))
	    {
		xml.append("<" + entry.getKey() + "><![CDATA[").append(entry.getValue()).append("]]></" + entry.getKey() + ">\n");
	    }
	    else
	    {
		xml.append("<" + entry.getKey() + ">").append(entry.getValue()).append("</" + entry.getKey() + ">\n");
	    }
	});
	xml.append("</xml>");
	return xml;
    }
    private String compositeWXPayKeyValuePaires(Map<String, String> result, String key)
    {
	StringBuilder builder = new StringBuilder();
	result.entrySet().stream().filter(x -> !x.getKey().equals("sign") && !x.getKey().equals("class")).sorted((x, y) -> x.getKey().compareTo(y.getKey())).forEach((x) -> {
	    builder.append(x.getKey()).append("=").append(x.getValue()).append("&");
	});
	builder.append("key=").append(key);
	return builder.toString();
    }
}