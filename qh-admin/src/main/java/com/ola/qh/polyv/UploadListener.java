package com.ola.qh.polyv;

public interface UploadListener {
	public void fail(Exception ex);
	public void success(String body);
}
