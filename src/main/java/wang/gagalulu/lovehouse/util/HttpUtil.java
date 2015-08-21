package wang.gagalulu.lovehouse.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;


@Service
public class HttpUtil {
	public String getContentByUrl(String urlstr){
		BufferedReader in;
		String result = "";
		try {
			URL url = new URL(urlstr);
			URLConnection conn = url.openConnection();
			StringBuffer sb = new StringBuffer("");
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8") );   
		    String str = null;    
		    while((str = in.readLine()) != null) {    
		    	sb.append( str );     
		    }     
		    result = sb.toString();     
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getContentByPost(String url,String postStr){
		HttpClient httpClient = HttpClients.createDefault();
		String postReturnMsg = null;
		HttpPost post = new HttpPost(url);
		RequestConfig config = RequestConfig.custom()
			    .setConnectionRequestTimeout(10000).setConnectTimeout(10000)
			    .setSocketTimeout(10000).build();
		HttpEntity entity;
		try {
			entity = new StringEntity(postStr,"utf-8");
			post.setEntity(entity);
			post.setConfig(config);
			postReturnMsg = invoke(httpClient, post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return postReturnMsg;
	}

	private String invoke(HttpClient httpclient,  
            HttpUriRequest httpost) throws ParseException, IOException {  
        HttpResponse response = sendRequest(httpclient, httpost);  
        String body = paseResponse(response);  
        return body;  
    }
	
	private HttpResponse sendRequest(HttpClient httpclient,  
            HttpUriRequest httpost) {  
        HttpResponse response = null;  
        try {  
            response = httpclient.execute(httpost);  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return response;  
    }
	
	private String paseResponse(HttpResponse response) throws ParseException, IOException{
		HttpEntity entityRes = response.getEntity();
		String content = EntityUtils.toString(entityRes);
		return content;
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://icarbeta.qunar.com/op/userLogin");
		String json = "{\"bparams\":{\"username\":\"qjia\",\"passwordSign\":\"bb410f138f9f5f5449b9d5646244b599\"},\"cparams\":{\"local_lang\":\"zh-Hans\"}}";
		RequestConfig config = RequestConfig.custom()
			    .setConnectionRequestTimeout(10000).setConnectTimeout(10000)
			    .setSocketTimeout(10000).build();
		HttpResponse response = null;
		HttpEntity entity = new StringEntity(json);
		post.setEntity(entity);
		post.setConfig(config);
		
		response = httpClient.execute(post);
		HttpEntity entityRes = response.getEntity();
		String content = EntityUtils.toString(entityRes);
		System.out.println(content);
	}
}
