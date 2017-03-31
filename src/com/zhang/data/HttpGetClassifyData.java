package com.zhang.data;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zhang.entity.ClassifyEntity;
import com.zhang.entity.ClassifyItemEntity;
import com.zhang.net.HttpClientContext;
import com.zhang.net.HttpIP;

/**
 * 获取菜谱 类别列表
 * 
 * */
public class HttpGetClassifyData {


	@SuppressWarnings("unused")
	private Context context;
	private Handler handler;
	private AsyncHttpClient client;
	public Message msg = new Message();
	
	public HttpGetClassifyData(Context context, Handler handler) {
		this.context = context;
		this.handler = handler;
	}
	
	public static List<ClassifyEntity> Clist=new ArrayList<ClassifyEntity>();
	
	
	public void getClassifyData(){
		RequestParams params = new RequestParams();
		params.put("key",HttpIP.key);
		client=HttpClientContext.getInstance().client;
		client.get(HttpIP.ClassifyURL,params,new JsonHttpResponseHandler("utf-8"){
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
				System.out.println("网络异常");
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				super.onSuccess(statusCode, headers, response);				
				System.out.println("获取数据");

				try {
					Clist.clear();
					JSONArray result=response.getJSONArray("result");
					for (int i = 0; i < result.length(); i++) {
						JSONObject obj=result.getJSONObject(i);
						ClassifyEntity ce=new ClassifyEntity();   //父类
						ce.setParentId(obj.optString("parentId"));
						ce.setName(obj.optString("name"));
						
						List<ClassifyItemEntity> citemlist=new ArrayList<ClassifyItemEntity>();
						JSONArray array=obj.getJSONArray("list");
						for (int j = 0; j < array.length(); j++) {
							JSONObject o=array.getJSONObject(j);
							ClassifyItemEntity cie=new ClassifyItemEntity();  //子类
							cie.setId(o.optString("id"));
							cie.setName(o.optString("name"));
							cie.setParentId(o.optString("parentId"));
							citemlist.add(cie);
						}
						ce.setClassList(citemlist);
						
						Clist.add(ce);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				msg.what = 1;
				msg.obj = Clist;
				handler.sendMessage(msg);
				
			}
			
		});
	}
	
}
