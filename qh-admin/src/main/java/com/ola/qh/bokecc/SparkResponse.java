package com.ola.qh.bokecc;

public class SparkResponse {
	
	private UploadInfo uploadinfo;
	
	public SparkResponse() {
		
	}
	
	public SparkResponse(UploadInfo uploadinfo) {
		this.uploadinfo = uploadinfo;
	}
	
	public UploadInfo getUploadinfo() {
		return uploadinfo;
	}
	
	public void setUploadinfo(UploadInfo uploadinfo) {
		this.uploadinfo = uploadinfo;
	}
	
	public String getVideoid() {
		return uploadinfo.getVideoid();
	}
	
	public String getServicetype() {
		return uploadinfo.getServicetype();
	}
	
	public String getMetaurl() {
		return uploadinfo.getMetaurl();
	}
	
	public String getChunkurl() {
		return uploadinfo.getChunkurl();
	}
	
	public static class UploadInfo {
		
		private String videoid;
		
		private String userid;
		
		private String servicetype;
		
		private String metaurl;
		
		private String chunkurl;
		
		public UploadInfo() {
			
		}
		
		public String getVideoid() {
			return videoid;
		}
	
		public void setVideoid(String videoid) {
			this.videoid = videoid;
		}
	
		public String getUserid() {
			return userid;
		}
	
		public void setUserid(String userid) {
			this.userid = userid;
		}
	
		public String getServicetype() {
			return servicetype;
		}
	
		public void setServicetype(String servicetype) {
			this.servicetype = servicetype;
		}
	
		public String getMetaurl() {
			return metaurl;
		}
	
		public void setMetaurl(String metaurl) {
			this.metaurl = metaurl;
		}
	
		public String getChunkurl() {
			return chunkurl;
		}
	
		public void setChunkurl(String chunkurl) {
			this.chunkurl = chunkurl;
		}
	}
}
