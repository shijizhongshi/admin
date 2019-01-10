package com.ola.qh.polyv.sdk;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.ola.qh.polyv.PolyvClient;
import com.ola.qh.polyv.Progress;
import com.ola.qh.polyv.UploadListener;
import com.ola.qh.polyv.sdk.ProgressOutHttpEntity.ProgressListener;



public class PolyvSDKClient {
	

	public String getReadtoken() {
		return readtoken;
	}


	public String getWritetoken() {
		return writetoken;
	}

	private String readtoken;
	private String writetoken;
	private boolean isSign;
	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}

	private String userid;
	public String getSecretkey() {
		return secretkey;
	}


	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}

	private String secretkey;
	public void setReadtoken(String readtoken) {
		this.readtoken = readtoken;
	}

	
	public boolean isSign() {
		return isSign;
	}
	

	public void setSign(boolean isSign) {
		this.isSign = isSign;
	}


	public void setWritetoken(String writetoken) {
		this.writetoken = writetoken;
	}

	private static PolyvSDKClient instance;

	public static synchronized PolyvSDKClient getInstance() {
		if (instance == null) {
			instance = new PolyvSDKClient();
		}
		return instance;
	}
	/**
	 * 
	 * @param page ҳ���������ȡ��һҳӦ�ô�1
	 * @param size ��ȡ��Ƶ����
	 * @return
	 */
	public List<Video> getVideoList(int page,int size) {
	    List<Video> list = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://v.polyv.net/uc/services/rest?method=getNewList&readtoken="+readtoken+"&pageNum="+page + "&numPerPage="+ size);
            CloseableHttpResponse response=null;
            try {
            	response = httpclient.execute(httpget);

                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    list = SDKUtil.convertJsonToVideoList(EntityUtils.toString(resEntity,"UTF-8"));
                }
                EntityUtils.consume(resEntity);
            }catch(Exception e){
            	e.printStackTrace();
            } finally {
            	if(response!=null){
            		try {
						response.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
                
            }
        } finally {
            try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return list;
	}
	/*
	 * ɾ����Ƶ
	 */
	public boolean deleteVideo(String vid){
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://v.polyv.net/uc/services/rest?method=delVideoById&writetoken="+writetoken+"&vid="+vid);
            CloseableHttpResponse response=null;
            try {
            	response = httpclient.execute(httpget);

                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                	int error = SDKUtil.getErrorCodeFromJson(EntityUtils.toString(resEntity,"UTF-8"));
                	if(error==0){
                		return true;
                	}
                	//return SDKUtil.convertJsonToVideo(EntityUtils.toString(resEntity,"UTF-8"));
                }
                EntityUtils.consume(resEntity);
            }catch(Exception e){
            	
            } finally {
            	if(response!=null){
            		try {
						response.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
                
            }
        } finally {
            try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return false;
	}
	/**
	 *  ��ȡ��Ƶ��ϸ��Ϣ
	 * @param vid
	 * @return
	 */
	public Video getVideo(String vid) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://v.polyv.net/uc/services/rest?method=getById&readtoken="+readtoken+"&vid="+vid);
            CloseableHttpResponse response=null;
            try {
            	response = httpclient.execute(httpget);

                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                	return SDKUtil.convertJsonToVideo(EntityUtils.toString(resEntity,"UTF-8"));
                }
                EntityUtils.consume(resEntity);
            }catch(Exception e){
            	
            } finally {
            	if(response!=null){
            		try {
						response.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
                
            }
        } finally {
            try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return null;
	}
	private long totalSize;
	private int percent;

	public int getPercent() {
		return percent;
	}


	public void setPercent(int percent) {
		this.percent = percent;
	}

	/**
	 * �ϴ���Ƶ
	 * @param filename Ҫ�ϴ�����Ƶ�ļ���
	 * @param title ����
	 * @param tag ��ǩ
	 * @param desc ����
	 * @param cataid ���
	 * @return
	 * @throws Exception
	 */
	public Video upload(String filename,String title,String tag,String desc,long cataid) throws Exception{
		percent = 0;
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
        	String json = "{\"title\": \""+title+"\", \"tag\": \""+tag+"\", \"desc\": \""+ desc + "\"}";
			String hash = "";
    		if(isSign){
    			hash = SDKUtil.sha1("cataid=" + cataid + "&JSONRPC=" + json + "&writetoken=" + this.writetoken + this.secretkey);
    		}
    		
            HttpPost httppost = new HttpPost("http://v.polyv.net/uc/services/rest?method=uploadfile");

            ContentType contentType = ContentType.create("text/plain", Charset.forName("UTF-8"));
            FileBody filedata = new FileBody(new File(filename));
            StringBody jsonrpc = new StringBody(json, contentType);
            StringBody _writetoken = new StringBody(this.writetoken, ContentType.TEXT_PLAIN);
            StringBody _cataid = new StringBody(cataid+"", ContentType.TEXT_PLAIN);
            StringBody _hash = new StringBody(hash, ContentType.TEXT_PLAIN);
            
            HttpEntity reqEntity = MultipartEntityBuilder.create()
            		.addPart("writetoken", _writetoken)
                    .addPart("Filedata", filedata)
                    .addPart("JSONRPC", jsonrpc)
                    .addPart("cataid", _cataid)
                    .addPart("sign", _hash)
                    .build();

            totalSize = reqEntity.getContentLength();
            httppost.setEntity(reqEntity);
            ProgressOutHttpEntity progressHttpEntity = new ProgressOutHttpEntity(
					reqEntity, new ProgressListener() {
	                    @Override
	                    public void transferred(long transferedBytes) {
	                    	percent = (int) (100 * transferedBytes / totalSize);
	                    	//System.out.println(percent);
	                    }
	        });
			
			httppost.setEntity(progressHttpEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {

                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    return SDKUtil.convertJsonToVideo(EntityUtils.toString(resEntity,"UTF-8"));
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return null;
        

		
		
		
	}
	
	public Video upload1(String filename,String title,String tag,String desc,long cataid) throws Exception{
		percent = 0;
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
        	String json = "{\"title\": \""+title+"\", \"tag\": \""+tag+"\", \"desc\": \""+ desc + "\"}";
			String hash = "";
    		if(isSign){
    			hash = SDKUtil.sha1("cataid=" + cataid + "&JSONRPC=" + json + "&writetoken=" + this.writetoken + this.secretkey);
    		}
    		
            HttpPost httppost = new HttpPost("http://v.polyv.net/uc/services/rest?method=uploadfile");

            ContentType contentType = ContentType.create("text/plain", Charset.forName("UTF-8"));
            FileBody filedata = new FileBody(new File(filename));
            StringBody jsonrpc = new StringBody(json, contentType);
            StringBody _writetoken = new StringBody(this.writetoken, ContentType.TEXT_PLAIN);
            StringBody _cataid = new StringBody(cataid+"", ContentType.TEXT_PLAIN);
            StringBody _hash = new StringBody(hash, ContentType.TEXT_PLAIN);
            
            HttpEntity reqEntity = MultipartEntityBuilder.create()
            		.addPart("writetoken", _writetoken)
                    .addPart("Filedata", filedata)
                    .addPart("JSONRPC", jsonrpc)
                    .addPart("cataid", _cataid)
                    .addPart("sign", _hash)
                    .build();

            totalSize = reqEntity.getContentLength();
            httppost.setEntity(reqEntity);
            ProgressOutHttpEntity progressHttpEntity = new ProgressOutHttpEntity(
					reqEntity, new ProgressListener() {
	                    @Override
	                    public void transferred(long transferedBytes) {
	                    	percent = (int) (100 * transferedBytes / totalSize);
	                    	//System.out.println(percent);
	                    }
	        });
			
			httppost.setEntity(progressHttpEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {

                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    return SDKUtil.convertJsonToVideo(EntityUtils.toString(resEntity,"UTF-8"));
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return null;
        

		
		
		
	}
	/**
	 * �ϵ������ӿ�
	 * @param filename
	 * @param title
	 * @param tag
	 * @param desc
	 * @param cataid
	 * @return
	 * @throws Exception
	 */
	public String resumableUpload(String filename,String title,String tag,String desc,long cataid, Progress progress,UploadListener uploadListener) throws Exception{

		PolyvClient client = new PolyvClient(filename,title,tag,desc,cataid);
		//client.setFilename(filename);
		
		client.setProgress(progress);
		client.setUploadListener(uploadListener);
		client.upload();
		
		//System.out.println(client.getLocation());
		//System.out.println(client.getJson());
		return client.getVid();
		
        

		
		
		
	}

}
