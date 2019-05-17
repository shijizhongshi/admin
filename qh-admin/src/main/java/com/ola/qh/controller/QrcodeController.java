package com.ola.qh.controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.entity.Salesman;
import com.ola.qh.service.ISalesmanService;
import com.ola.qh.service.IStoreService;
import com.ola.qh.util.Bytes;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
import com.sun.org.apache.xml.internal.utils.URI;
@RestController
public class QrcodeController {
	
	@Autowired
	private IStoreService storeService;
	
	@Autowired
	private ISalesmanService salesmanService;
	
	@RequestMapping(value="/api/qrcode/geturl")
	 public Results<String> seeUrl(@RequestParam(value = "imgUrl", required = true) String imgUrl,
			 @RequestParam(value = "id", required = true) String id) throws Exception{
		Results<String> results=new Results<String>();
		
		//String[] temp = imgUrl.split("/"); 
		//String logo=temp[temp.length-1];
		/*FileInputStream is = new FileInputStream(new File(process(file)));
		
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int ch;
        while ((ch = is.read(buffer)) != -1) {
            bytestream.write(buffer,0,ch);
        }*/
		//bytestream.close();
		//byte[] data = file.getBytes();
		 URL urlConet = new URL(imgUrl);
	        HttpURLConnection con = (HttpURLConnection)urlConet.openConnection();    
	        con.setRequestMethod("GET");    
	        con.setConnectTimeout(4 * 1000);    
	        InputStream inStream = con .getInputStream();    //通过输入流获取图片数据    
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();    
	        byte[] buffer = new byte[2048];    
	        int len = 0;    
	        while( (len=inStream.read(buffer)) != -1 ){    
	            outStream.write(buffer, 0, len);    
	        }    
	        inStream.close();    
	        byte[] data =  outStream.toByteArray(); 
		 
        byte[] bytes = Bytes.qrcode("https://www.baidu.com/", 300, 300,data);
		String fname = KeyGen.gen() + ".jpg";
		String url  = storeService.storeUrl(fname, bytes);
		
		results.setData(url);
		results.setStatus("0");
		return results;
	 }
	
}
