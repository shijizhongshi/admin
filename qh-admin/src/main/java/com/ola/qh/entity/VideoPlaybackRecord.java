package com.ola.qh.entity;

public class VideoPlaybackRecord {

	private PlayLogs play_logs;

	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public PlayLogs getPlay_logs() {
		return play_logs;
	}

	public void setPlay_logs(PlayLogs play_logs) {
		this.play_logs = play_logs;
	}

}