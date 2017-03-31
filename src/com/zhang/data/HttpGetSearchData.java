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
import com.zhang.entity.MenuEntity;
import com.zhang.entity.StepsEntity;
import com.zhang.net.HttpClientContext;
import com.zhang.net.HttpIP;
import com.zhang.utils.ToastUtils;


public class HttpGetSearchData {

	private Context context;
	private Handler handler;
	private AsyncHttpClient client;
	public Message msg = new Message();
	
	public HttpGetSearchData(Context context, Handler handler) {
		this.context = context;
		this.handler = handler;
	}
	
	public static List<MenuEntity> list=new ArrayList<MenuEntity>();
	
	public void getMenuData(String name,int pn){
		RequestParams params = new RequestParams();
		params.put("menu",name+"");
		params.put("pn",pn+"");
		params.put("key",HttpIP.key);
		params.put("rn",HttpIP.rn);
		client=HttpClientContext.getInstance().client;
		client.post(HttpIP.URL,params,new JsonHttpResponseHandler("utf-8"){

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
				ToastUtils.showToastInThread(context, "网络异常");
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				super.onSuccess(statusCode, headers, response);				
				try {
					list.clear();
					JSONObject result=response.getJSONObject("result");
					JSONArray data=result.getJSONArray("data");
					for(int i=0;i<data.length();i++){
						JSONObject obj=data.getJSONObject(i);
						MenuEntity me=new MenuEntity();
						me.setmId(obj.optString("id"));
						me.setTitle(obj.optString("title"));
						me.setTags(obj.optString("tags"));
						me.setImtro(obj.optString("imtro"));
						me.setIngredients(obj.optString("ingredients"));
						me.setBurden(obj.optString("burden"));
						me.setAlbums(obj.optJSONArray("albums").getString(0));

						List<StepsEntity> Slist=new ArrayList<StepsEntity>();
						JSONArray steps=obj.getJSONArray("steps");
						for(int j=0;j<steps.length();j++){
							JSONObject step=steps.getJSONObject(j);
							StepsEntity se=new StepsEntity();
							se.setImg(step.optString("img"));
							se.setStep(step.optString("step"));
							Slist.add(se);
						}
						me.setSteps(Slist);
						
						list.add(me);
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				msg.what = 1;
				msg.obj = list;
				handler.sendMessage(msg);
				
			}
			
		});
	}
	
}
