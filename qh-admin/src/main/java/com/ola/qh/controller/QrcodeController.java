package com.ola.qh.controller;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ola.qh.service.IStoreService;
import com.ola.qh.util.Bytes;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
@RestController
public class QrcodeController {
	
	@Autowired
	private IStoreService storeService;
	
	@RequestMapping(value="/api/qrcode/geturl")
	 public Results<String> seeUrl(@RequestParam(value = "imgUrl", required = true) String imgUrl,
			 @RequestParam(value = "id", required = true) String id,@RequestParam(value = "address", required = true) String address,
			 @RequestParam(value = "name", required = true) String name) throws Exception{
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
		String content=new String();
		content="http://59dae85d.ngrok.io/web/salesmanQRcode/register?id="+id+"&imgUrl="+imgUrl+"&address="+address+"&name="+name;
        byte[] bytes = Bytes.qrcode(content, 300, 300,data);
		String fname = KeyGen.gen() + ".jpg";
		String url  = storeService.storeUrl(fname, bytes);
		
		results.setData(url);
		results.setStatus("0");
		return results;
	 }
	
}
