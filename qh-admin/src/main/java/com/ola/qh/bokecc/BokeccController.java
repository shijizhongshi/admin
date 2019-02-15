package com.ola.qh.bokecc;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.util.Results;
@RestController
public class BokeccController {

	@RequestMapping(value = "/api/upload/video", method = RequestMethod.POST, consumes = "multipart/form-data")
	public Results<Videos> uploadVideo(@RequestParam(value = "file", required = true) MultipartFile file,HttpServletRequest request) throws IOException{
		
		Results<Videos> result=new Results<Videos>();
		 if(!file.isEmpty()) {
			 InputStream  IS =  new ByteArrayInputStream(file.getBytes());
			 BufferedReader in = new BufferedReader(new InputStreamReader(IS));
			   StringBuffer buffer = new StringBuffer();
			   String line = "";
			   while ((line = in.readLine()) != null){
			     buffer.append(line);
			   }

			System.out.println(buffer.toString());
		 }
		return result;
	}
}
