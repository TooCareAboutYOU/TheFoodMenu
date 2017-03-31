package com.zhang.ui;

import java.util.ArrayList;
import java.util.List;
import com.mypublictools.loadingpage.LoadingPager;
import com.mypublictools.loadingpage.XListView;
import com.mypublictools.loadingpage.XListView.IXListViewListener;
import com.umeng.analytics.MobclickAgent;
import com.zhang.DataBaseDo.DBMenuInfo;
import com.zhang.adapter.ListviewAdapter;
import com.zhang.data.HttpGetSearchData;
import com.zhang.data.HttpListViewData;
import com.zhang.entity.MenuEntity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;


public class ListiewClassifyActivity extends Activity {

	private XListView xlistview;
	private LoadingPager loadingpager;
	private ListviewAdapter adapter;
	private List<MenuEntity> list =new ArrayList<MenuEntity>();
	private int pn=1;
	
	private ImageView mReturn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_listview);
		init();
	}
	
	
	private void init(){
		mReturn=(ImageView) findViewById(R.id.return_of_listview);
		mReturn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ListiewClassifyActivity.this.finish();
			}
		});
		
		loadingpager=(LoadingPager) findViewById(R.id.xlist);
		xlistview =new XListView(this); 
		xlistview.setDivider(null);
		xlistview.setPullLoadEnable(true);
		xlistview.setPullRefreshEnable(true);
		xlistview.setOnItemClickListener(new Listener());
		xlistview.setXListViewListener(new IXListViewListener() {
			@Override
			public void onRefresh() {
				Log.v("TAG", "刷新...");
				list.clear();
				getItemData();
				getSearchData(pn);
				xlistview.stopRefresh();
			}
			@Override
			public void onLoadMore() {
				Log.v("TAG","下拉加载");
				pn++;
				getSearchData(pn);
				xlistview.stopLoadMore();
			}
		});
		adapter=new ListviewAdapter(ListiewClassifyActivity.this,list);
		xlistview.setAdapter(adapter);
		loadingpager.setContent(xlistview);

		getItemData();
		getSearchData(pn);
	}

	
	//获取查询数据
	public void getSearchData(int pn){
		String name=getIntent().getExtras().getString("MenuName");
		if (name!=null) {
			pn=(pn-1)*10+1;
			HttpGetSearchData hcI=new HttpGetSearchData(this,handler);
			hcI.getMenuData(name,pn);
		}
		
	}

	//获取分类数据
	public void getItemData(){
		String cid=getIntent().getExtras().getString("cid");
		if (cid!=null) {
			HttpListViewData hcI=new HttpListViewData(this,handler);
			hcI.getListViewData(cid);
		}
		
	}
	
	
	//监听事件
	class Listener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			Intent intent=new Intent(ListiewClassifyActivity.this,StepsActivity.class);
			intent.putExtra("StepId", list.get(position-1).getmId());
			startActivity(intent);
		}
	}
	
	

	
	//Handler——消息队列。接受子线程发送的数据和在子线程中发送消息。
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {   //接收handler发送的消息
			switch (msg.what) {
				case 1:  //来自搜索的返回list
					//list.clear();	
					 List<MenuEntity> slist=(List<MenuEntity>) msg.obj;
						if(slist!=null){
							list.clear();
							list.addAll(slist);
							DBMenuInfo.getInstance().saveCollect(list);  //保存数据库
							adapter.notifyDataSetChanged();
						}else{
							xlistview.setPullLoadEnable(false);
							xlistview.stopLoadMore();
						}
						loadingpager.setVisibilityContent();
					break;
					
				/*case 2:   //来之主界面分类选项查询的list
					//list.clear();	
					 List<MenuEntity> mlist=(List<MenuEntity>) msg.obj;
						if(mlist!=null){
							list.addAll(mlist);
							DBMenuInfo.getInstance().saveCollect(list);  //保存数据库
							adapter.notifyDataSetChanged();
						}else{
							xlistview.setPullLoadEnable(false);
							xlistview.stopLoadMore();
						}
						loadingpager.setVisibilityContent();
					break;*/
					}
				}
		};
	
		
		
		public void onResume() {
		    super.onResume();
		    MobclickAgent.onPageStart("SplashScreen"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
		    MobclickAgent.onResume(this);          //统计时长
		}
		public void onPause() {
		    super.onPause();
		    MobclickAgent.onPageEnd("SplashScreen"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
		    MobclickAgent.onPause(this);
		}
}
