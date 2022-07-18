/*
 * Copyright 2016-2022 the original author.All rights reserved.
 * Kingstar(honeysoft@126.com)
 * The license,see the LICENSE file.
 */

package com.kingstar.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.teasoft.honey.osql.core.Logger;

/**
 * @author Kingstar
 * @since  1.0
 */
public class HttpclientUtil {
	
	private HttpclientUtil() {}
	
	public static String getJson(String urlStr) {
		String json = "";
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		// 构造httpGet请求对象
		HttpGet httpGet = new HttpGet(urlStr);
		// 可关闭的响应
		CloseableHttpResponse response = null;
		try {
			response = closeableHttpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					json = EntityUtils.toString(entity);
				}
			}

		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
		} finally {
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
				Logger.error(e.getMessage(), e);
			}
			if (response != null) {
				try {
					response.close();
				} catch (Exception e) {
					Logger.error(e.getMessage(), e);
				}
			}
		}

		return json;
	}
}
