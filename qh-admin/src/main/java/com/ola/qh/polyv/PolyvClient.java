package com.ola.qh.polyv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.chinanetcenter.api.sliceUpload.JSONObjectRet;
import com.fasterxml.jackson.databind.JsonNode;
import com.ola.qh.polyv.sdk.PolyvSDKClient;


public class PolyvClient {

	private static String api_url = "http://upload.polyv.net:1080/files/";
	private static HttpClient httpClient = HttpClientBuilder.create().build();
	private String readToken = null;
	private String writeToken = null;
	private String filename = null;

	private String vid = null;
	private String location = null;
	private int offset = 0;
	private Progress progress = null;
	private String json = null;
	private String title = "";
	private String desc = "";
	private String tag = "";
	private long cataid = 1L;

	private PolyvBlockUploader polyvBlockUploader;
	private UploadListener uploadListener;
	
	public void setUploadListener(UploadListener uploadListener){
		this.uploadListener=uploadListener;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public void setUploadUrl(String url) {
		this.api_url = url;
	}

	public PolyvClient(String filename, String title, String tag, String desc, long cataid) {
		this.filename = filename;
		String md5 = getMD5(filename);
		this.vid = PolyvSDKClient.getInstance().getUserid() + md5.substring(0, 22) + "_"
				+ PolyvSDKClient.getInstance().getUserid().substring(0, 1);
		this.location = api_url + this.vid;

		this.title = title;
		this.desc = desc;
		this.cataid = cataid;

		this.readToken = PolyvSDKClient.getInstance().getReadtoken();
		this.writeToken = PolyvSDKClient.getInstance().getWritetoken();

	}

	public void setFilename(String filename) {
		String md5 = getMD5(filename);
		this.filename = filename;
		this.vid = PolyvSDKClient.getInstance().getUserid() + md5.substring(0, 22) + "_"
				+ PolyvSDKClient.getInstance().getUserid().substring(0, 1);
		this.location = api_url + this.vid;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	public String upload() {
		if (polyvBlockUploader == null)
			polyvBlockUploader = new PolyvBlockUploader("0", filename, cataid + "", title, tag, vid,
					PolyvSDKClient.getInstance().getUserid(), writeToken, new JSONObjectRet() {

						@Override
						public void onSuccess(byte[] body) {
//						    polyvBlockUploader.deleteInfoFile();
							if(uploadListener!=null)
								uploadListener.success(new String(body));
						}

						@Override
						public void onSuccess(JsonNode obj) {
//							polyvBlockUploader.deleteInfoFile();
							if(uploadListener!=null)
								uploadListener.success(obj.toString());
						}

						@Override
						public void onProcess(long current, long total) {
							if (progress != null)
								progress.run(current, total);
							// System.out.printf("%s\r", current * 100 / total +
							// " %");
						}

						@Override
						public void onPersist(JsonNode obj) {
							String key = polyvBlockUploader.getFileKey().substring(0,
									polyvBlockUploader.getFileKey().lastIndexOf("."));
							File configFile = new File(System.getProperty("user.dir") + File.separator
									+ polyvBlockUploader.getInfofileParent() + File.separator
									+ polyvBlockUploader.getBucketName() + File.separator + key
									+ "_sliceConfig.properties");
							synchronized (configFile) {
								FileOutputStream fileOutputStream = null;
								try {
									if (!configFile.getParentFile().exists()) {
										configFile.getParentFile().mkdirs();
									}
									if (!configFile.exists()) {
										configFile.createNewFile();
									}
									fileOutputStream = new FileOutputStream(configFile);
									fileOutputStream.write(obj.toString().getBytes());
									fileOutputStream.flush();
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									if (fileOutputStream != null) {
										try {
											fileOutputStream.close();
										} catch (IOException e) {
										}
									}
								}
							}
						}

						@Override
						public void onFailure(Exception ex) {
							if(uploadListener!=null)
								uploadListener.fail(ex);
						}
					});
		if (!polyvBlockUploader.upload()) {
			try {
				System.out.println("由于新的上传获取uploadToken响应状态失败,将使用原来的上传");
				int offset = this.offset();
				if (offset == -1) {
					this.create();
					offset = 0;
				}
				this.transfer(offset);
				return this.getJson();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return vid;
		}
	}

	//
	public void create() throws Exception {
		long filesize = new File(this.filename).length();

		HttpPost http = new HttpPost(api_url);
		http.addHeader("Final-Length", String.valueOf(filesize));
		http.addHeader("vid", this.vid);
		http.addHeader("userid", PolyvSDKClient.getInstance().getUserid());
		long ts = System.currentTimeMillis();
		String sign = DigestUtils.md5Hex(ts + this.writeToken);
		http.addHeader("hash", sign);
		http.addHeader("ts", ts + "");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("title", this.title));
		nvps.add(new BasicNameValuePair("desc", this.desc));
		nvps.add(new BasicNameValuePair("tag", this.tag));
		nvps.add(new BasicNameValuePair("cataid", this.cataid + ""));

		http.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

		HttpResponse response = httpClient.execute(http);
		System.out.println(response.toString());
		Header[] headers = response.getHeaders("Location");
		this.location = headers[0].getValue();
	}

	//
	public int offset() throws Exception {
		try {
			HttpHead http = new HttpHead(this.location);
			HttpResponse response = this.httpClient.execute(http);
			Header[] headers = response.getHeaders("Offset");
			return Integer.valueOf(headers[0].getValue());
		} catch (Exception e) {
			// e.printStackTrace();
			return -1;
		}
	}

	//
	public void transfer(int offset) throws Exception {
		long filesize = new File(this.filename).length();
		FileInputStream istream = new FileInputStream(new File(filename));

		HttpPatch http = new HttpPatch(this.location);
		http.addHeader("writeToken", this.writeToken);
		http.addHeader("Offset", String.valueOf(offset));

		http.setEntity(new IStreamEntity(istream, filesize, offset, this.progress));
		// http.setEntity(new InputStreamEntity(istream));
		HttpResponse response = this.httpClient.execute(http);
		HttpEntity entity = response.getEntity();
		this.json = EntityUtils.toString(entity);
	}

	private static String getMD5(String filename) {
		String checksum = "";
		try {
			//checksum = DigestUtils.md5Hex(new FileInputStream(filename));
		    File f = new File(filename);
		    checksum = DigestUtils.md5Hex(filename + System.currentTimeMillis() + f.length());
		    

		} catch (Exception e) {
			e.printStackTrace();
		}
		return checksum;
	}

	public String getLocation() {
		return this.location;
	}

	public String getReadToken() {
		return this.readToken;
	}

	public String getWriteToken() {
		return this.writeToken;
	}

	public String getJson() {
		return this.json;
	}
}
