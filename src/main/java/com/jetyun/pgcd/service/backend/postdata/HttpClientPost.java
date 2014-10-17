package com.jetyun.pgcd.service.backend.postdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class HttpClientPost {

	private static HttpClient client = HttpClients.createDefault();

	public static PostResult post(String url, String token,
			String data) throws IOException {
		PostResult result = null;
		HttpPost post = new HttpPost(url);
		RequestConfig config = RequestConfig.custom()
				.setConnectionRequestTimeout(3 * 1000)
				.setSocketTimeout(5 * 1000).setConnectTimeout(5 * 1000).build();
		post.setConfig(config);
		HttpEntity entity = getParameterEntity(data);
		if (entity != null) {
			post.setEntity(entity);
		}
//		post.addHeader("Authorization", token);
		try {
			result = sendPost(post);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (post != null) {
				post.releaseConnection();
			}
		}

		return result;
	}

	private static HttpEntity getParameterEntity(String data)
			throws UnsupportedEncodingException {
		HttpEntity entity = new StringEntity(data, "utf-8");
		return entity;
	}

	private static PostResult sendPost(HttpPost post) throws Exception {
		PostResult result = new PostResult();
		HttpResponse response = client.execute(post);
		StatusLine statusInfo = response.getStatusLine();
		result.setStatus(statusInfo);
		BufferedReader br = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		result.setMessage(sb.toString());
		return result;
	}

}
