package com.ola.qh.bokecc;

public class UploadResponse {
	//文件已全部接收，上传成功
	public static final int RESULT_SUCCESS = 1;
	
	//文件仍在上传状态中，成功返回“断点位置”
	public static final int RESULT_OK = 0;
	
	//上传失败，可以放弃"本次"上传,不需要重试了
	public static final int RESULT_FAILED = -1;
	
	//服务器内部错误, 详见msg信息, 可以续传重试
	public static final int RESULT_INTERNAL_ERROR = -2;
	
	//请求参数错误,详见msg信息, 请修正错误后重试
	public static final int RESULT_INVALID_PARAM = -3;
	
	private int result = -1;

	//信息说明,可以参见http://doc.bokecc.com/vod/dev/uploadAPI/upload02/
	//也可以直接将msg发送给客服人员定位问题
	private String msg;

	private long received = -1;
	
	public UploadResponse() {

	}
	
	public UploadResponse(int result, String msg, long received) {
		this.result = result;
		this.msg = msg;
		this.received = received;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getReceived() {
		return received;
	}

	public void setReceived(long received) {
		this.received = received;
	}
	
}
