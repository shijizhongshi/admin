package com.ola.qh.service.imp;

import java.io.ByteArrayInputStream;

import org.springframework.stereotype.Service;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.ola.qh.service.IStoreService;
import com.ola.qh.util.FileStorageException;
import com.ola.qh.util.FileStore;

@Service
public class StoreService implements IStoreService {

	private static String accesskey = "LTAIvYOv6NLHmNnA";
	private static String accesskeysecret = "oVZqnj6eQqKIUkdLylp04PMIIzYP9Y";
	private static String endpoint = "oss-cn-qingdao.aliyuncs.com";
	private static String bucketname = "shijizhongshi-image";
	private static String domain = "https://shijizhongshi-image.oss-cn-qingdao.aliyuncs.com/";

	@Override
	public String storeUrl(String fname, byte[] content) throws FileStorageException {
		// TODO Auto-generated method stub
		fname = FileStore.rename(fname);
		String dpath = FileStore.buildpath();
		/// static/upload/2015/11/16/random_sequence_code/random_sequece.name
		String fpath = dpath + fname;

		// Create a new OSSClient instance with CNAME support
		OSSClient client = new OSSClient(endpoint, accesskey, accesskeysecret,
				new ClientConfiguration().setSupportCname(true));

		// Do some operations with the instance...
		// 构造上传请求
		PutObjectRequest put = new PutObjectRequest(bucketname, fpath, new ByteArrayInputStream(content));
		// 文件元信息的设置是可选的
		// ObjectMetadata metadata = new ObjectMetadata();
		// metadata.setContentType("application/octet-stream"); //
		// 设置content-type
		// metadata.setContentMD5(BinaryUtil.calculateBase64Md5(uploadFilePath));
		// // 校验MD5
		// put.setMetadata(metadata);

		try {
			client.putObject(put);
		} catch (Exception e) {
			throw new FileStorageException(e);
		}
		// Shutdown the instance to release any allocated resources
		client.shutdown();

		return domain + fpath;
	}

}
