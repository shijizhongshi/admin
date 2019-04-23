package com.ola.qh.weixin.handler;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.ola.qh.util.Results;

public class Requests {
	private static CloseableHttpClient client = HttpClientBuilder.create().build();
	private static final String charset = "UTF-8";

	/**
	 * 发送get 请求
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 */
	public static Results<byte[]> get(String url, Map<String, String> headers, Map<String, String> params) {
		Results<byte[]> result = new Results<byte[]>();
		if (Objects.nonNull(params) && !params.isEmpty()) {
			List<NameValuePair> pairs = map2pairs(params);
			try {
				url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
			} catch (ParseException | IOException e) {
				logger.log(Level.SEVERE, e.toString());
				result.setStatus("1");
				result.setMessage(e.getMessage());
				return result;
			}
		}
		logger.info("request url : " + url);
		HttpGet httpGet = new HttpGet(url);
		if (Objects.nonNull(headers) && !headers.isEmpty()) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if (!Strings.empty(value) && !Strings.empty(key)) {
					logger.info("find header : " + key + " : " + value);
					httpGet.setHeader(key, value);
				}
			}
		}
		CloseableHttpResponse response = null;

		try {
			response = client.execute(httpGet);
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.toString());
			result.setStatus("1");
			result.setMessage(e.getMessage());
			return result;
		}
		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity entity = response.getEntity();
		if (statusCode != 200) {
			httpGet.abort();
			if (Objects.nonNull(entity)) {
				try {
					String message = EntityUtils.toString(entity, charset);
					result.setStatus("1");
					result.setMessage(message);
					return result;
				} catch (ParseException | IOException e) {
					logger.log(Level.SEVERE, e.toString());

					result.setStatus("1");
					result.setMessage(e.getMessage());
					return result;
				}
			}
			result.setStatus("1");
			result.setMessage("request error, status code : " + statusCode);
			return result;
		}
		byte[] results = null;
		if (Objects.nonNull(entity)) {
			try {
				results = EntityUtils.toByteArray(entity);
			} catch (ParseException | IOException e) {
				logger.log(Level.SEVERE, e.toString());

				result.setStatus("1");
				result.setMessage(e.getMessage());
				return result;
			}
		}
		try {
			EntityUtils.consume(entity);
			response.close();
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.toString());
			result.setStatus("1");
			result.setMessage(e.getMessage());
			return result;
		}
		result.setStatus("0");
		result.setData(results);
		return result;
	}
	/**
	 * testGet
	 * @param url
	 * @param headers
	 * @param address
	 * @return
	 * @throws IOException
	 */
	public static Results<byte[]> testGet(String url, Map<String, String> headers, String address) throws IOException {
		Results<byte[]> result = new Results<byte[]>();
		try {
			url += "?" + address;
		} catch (ParseException e) {
			logger.log(Level.SEVERE, e.toString());
			result.setStatus("1");
			result.setMessage(e.getMessage());
			return result;
		}

		logger.info("request url : " + url);
		HttpGet httpGet = new HttpGet(url);
		if (Objects.nonNull(headers) && !headers.isEmpty()) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if (!Strings.empty(value) && !Strings.empty(key)) {
					logger.info("find header : " + key + " : " + value);
					httpGet.setHeader(key, value);
				}
			}
		}
		CloseableHttpResponse response = null;

		try {
			response = client.execute(httpGet);
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.toString());
			result.setStatus("1");
			result.setMessage(e.getMessage());
			return result;
		}
		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity entity = response.getEntity();
		if (statusCode != 200) {
			httpGet.abort();
			if (Objects.nonNull(entity)) {
				try {
					String message = EntityUtils.toString(entity, charset);
					result.setStatus("1");
					result.setMessage(message);
					return result;
				} catch (ParseException | IOException e) {
					logger.log(Level.SEVERE, e.toString());

					result.setStatus("1");
					result.setMessage(e.getMessage());
					return result;
				}
			}
			result.setStatus("1");
			result.setMessage("request error, status code : " + statusCode);
			return result;
		}
		byte[] results = null;
		if (Objects.nonNull(entity)) {
			try {
				results = EntityUtils.toByteArray(entity);
			} catch (ParseException | IOException e) {
				logger.log(Level.SEVERE, e.toString());

				result.setStatus("1");
				result.setMessage(e.getMessage());
				return result;
			}
		}
		try {
			EntityUtils.consume(entity);
			response.close();
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.toString());
			result.setStatus("1");
			result.setMessage(e.getMessage());
			return result;
		}
		result.setStatus("0");
		result.setData(results);
		return result;
	}

	/***
	 * post请求
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @param json
	 * @param forms
	 * @param files
	 * @param data
	 * @return
	 */
	public static Results<byte[]> post(String url, Map<String, String> headers, Map<String, String> params, String json,
			Map<String, String> forms, File[] files, byte[] data) {
		Results<byte[]> result = new Results<byte[]>();
		if (Objects.nonNull(params) && !params.isEmpty()) {
			List<NameValuePair> pairs = map2pairs(params);
			try {
				url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
			} catch (ParseException | IOException e) {
				logger.log(Level.SEVERE, e.toString());
				result.setStatus("1");
				result.setMessage(e.getMessage());
				return result;
			}
		}
		logger.info("request url : " + url);
		HttpPost post = new HttpPost(url);
		if (Objects.nonNull(headers) && !headers.isEmpty()) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if (!Strings.empty(value) && !Strings.empty(key)) {
					logger.info("find header : " + key + " : " + value);
					post.setHeader(key, value);
				}
			}
		}

		if (!Strings.empty(json)) {
			post.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
		}
		if (Objects.nonNull(forms) && !forms.isEmpty()) {
			List<NameValuePair> pairs = map2pairs(forms);
			try {
				post.setEntity(new UrlEncodedFormEntity(pairs, charset));
			} catch (UnsupportedEncodingException e) {
				logger.log(Level.SEVERE, e.toString());
				result.setStatus("1");
				result.setMessage(e.getMessage());
				return result;
			}
		}
		if (Objects.nonNull(files) && files.length > 0) {
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			Arrays.asList(files).forEach(file -> {
				builder.addBinaryBody(file.getName(), file);
			});
			post.setEntity(builder.build());
		}
		if (Objects.nonNull(data) && data.length > 0) {
			post.setEntity(new ByteArrayEntity(data, ContentType.APPLICATION_OCTET_STREAM));
		}

		CloseableHttpResponse response = null;

		try {
			response = client.execute(post);
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.toString());
			result.setStatus("1");
			result.setMessage(e.getMessage());
			return result;
		}
		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity entity = response.getEntity();
		if (statusCode != 200) {
			post.abort();
			if (Objects.nonNull(entity)) {
				try {
					String message = EntityUtils.toString(entity, charset);
					result.setStatus("1");
					result.setMessage(message);
					return result;
				} catch (ParseException | IOException e) {
					logger.log(Level.SEVERE, e.toString());
					result.setStatus("1");
					result.setMessage(e.getMessage());
					return result;
				}
			}
			result.setStatus("1");
			result.setMessage("request error, status code : " + statusCode);
			return result;
		}
		byte[] results = null;
		if (Objects.nonNull(entity)) {
			try {
				results = EntityUtils.toByteArray(entity);
			} catch (ParseException | IOException e) {
				logger.log(Level.SEVERE, e.toString());
				result.setStatus("1");
				result.setMessage(e.getMessage());
				return result;
			}
		}
		try {
			EntityUtils.consume(entity);
			response.close();
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.toString());
			result.setStatus("1");
			result.setMessage(e.getMessage());
			return result;
		}
		result.setStatus("0");
		result.setData(results);
		return result;
	}

	private static List<NameValuePair> map2pairs(Map<String, String> params) {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
		for (Map.Entry<String, String> entry : params.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (!Strings.empty(value) && !Strings.empty(key)) {
				pairs.add(new BasicNameValuePair(key, value));
			}
		}
		return pairs;
	}

	private static Logger logger = Logger.getLogger("co.legu.core.Requests.logger");
}
