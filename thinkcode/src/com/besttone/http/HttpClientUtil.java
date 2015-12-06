package com.besttone.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * http client
 * @author Lenovo
 *
 */
public class HttpClientUtil {
	
	/**
	 * REST
	 * @param url
	 * @param params
	 * @return
	 */
	public static String executeHttpInvoke(String url,Map<String,String> params){
		String resultJson = null;
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : params.entrySet()) {
			nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		try{
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			resultJson = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			httpPost.releaseConnection();
		}
		return resultJson;
	}
}
