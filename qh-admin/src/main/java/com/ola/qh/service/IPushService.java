package com.ola.qh.service;

public interface IPushService{

	public void send(String userId, String title, String content) throws Exception;
}
