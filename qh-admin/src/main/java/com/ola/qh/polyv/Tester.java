package com.ola.qh.polyv;

import java.io.File;
import java.util.List;

import com.ola.qh.polyv.sdk.PolyvSDKClient;
import com.ola.qh.polyv.sdk.Video;



public class Tester {
	public static void main(String[] args) {
        PolyvSDKClient client = PolyvSDKClient.getInstance();
        client.setReadtoken("cf962d3e-7986-4302-a0ca-0f82d292e907");
        client.setWritetoken("a1df864b-405e-4782-9494-733e9b51c5d5");
        client.setSecretkey("ucxGUE1iuw");
        client.setUserid("b826fec754");
		
        //testResumableUpload();
		// TODO Auto-generated method stub
		/*testUpload();*/
		//testResumableUpload();
        //testDeleteVideo();
        //testResumableUpload();
        testGet();
	}
	
	public static void testResumableUpload(File file){
		
	    PolyvSDKClient client = PolyvSDKClient.getInstance();

		String vid = "";
		try {
			vid = client.resumableUpload(file.toString(), "标题11", "", "", 1,new Progress(){
				public void run(long offset, long max) {
					// TODO Auto-generated method stub
					int percent = (int)(offset*100/max);
					System.out.println(percent);
				}
				
			},new UploadListener() {
				
				@Override
				public void success(String body) {
					System.out.println("成功"+body);
				}
				
				@Override
				public void fail(Exception ex) {
					System.out.println("失败"+ex.getLocalizedMessage());
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println(vid);
	}
	public static void testGet() {
		try {
			Video v = PolyvSDKClient.getInstance().getVideo("b826fec754598593cd744723b019b0fc_b");
			System.out.println(v.getStatus());
			System.out.println(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testUpload() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				Video v;
				try {
					v = PolyvSDKClient.getInstance().upload("/Users/Administrator/Desktop/tupian/aa83f5af1d79fa84b0a249f4cec9bff8.mp4",
							"标题11", "tag", "desc", 0);
					System.out.println(v.getFirstImage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		t.start();
		
		while(true){
			int percent = PolyvSDKClient.getInstance().getPercent();
			if(percent==100){
				break;
			}
			System.out.println("upload percent: " + percent + "%");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

	public static void testDeleteVideo() {
		try {

			boolean result = PolyvSDKClient.getInstance().deleteVideo(
					"sl8da4jjbxa1077082a56e35adef93c4_s");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testListVideo() {
		try {
			List<Video> list = PolyvSDKClient.getInstance().getVideoList(1, 20);
			for (int i = 0; i < list.size(); i++) {
				Video v = list.get(i);
				System.out.println(v.getVid() + "/" + v.getTitle());
			}
			System.out.println("----�鿴����----");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
