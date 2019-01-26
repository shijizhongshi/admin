package com.ola.qh.bokecc.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import org.codehaus.classworlds.BytesURLConnection;

public class BytesURLStreamHandler extends URLStreamHandler{

	           byte[] content;
	/*    */   int offset;
	/*    */   int length;
	/*    */   
	/*    */   public BytesURLStreamHandler(byte[] content)
	/*    */   {
	/* 22 */     this.content = content;
	/*    */   }
	/*    */   
	/*    */   public URLConnection openConnection(URL url)
	/*    */   {
	/* 27 */     return new BytesURLConnection(url, content);
	/*    */   }

	
}
