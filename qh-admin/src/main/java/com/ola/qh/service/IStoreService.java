package com.ola.qh.service;

import com.ola.qh.util.FileStorageException;

public interface IStoreService {

	public String storeUrl(String fname, byte[] content)throws FileStorageException;
}
