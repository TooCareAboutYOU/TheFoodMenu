package com.zhang.net;

import com.loopj.android.http.AsyncHttpClient;


/**
 * 异步任务类、获取数据
 * */
public class HttpClientContext {

	public AsyncHttpClient client=null;
	public static HttpClientContext Hclientcontext=null;
	
	public static HttpClientContext getInstance(){
		if(Hclientcontext==null){
			Hclientcontext=new HttpClientContext();
		}
		return Hclientcontext;
	}
	
	//由于HttpClientContext只能创建一次，所以AsyncHttpClient也只能创建一次
	HttpClientContext(){  
		if(Hclientcontext==null){
			client=new AsyncHttpClient();
		}
	}
	
}
