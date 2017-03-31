package com.zhang.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xutils.x;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import com.umeng.analytics.MobclickAgent;
import com.zhang.utils.ToastUtils;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;


@ContentView(R.layout.activity_search)
public class SearchActivity extends Activity {

	@ViewInject(R.id.return_of_search)
	public ImageView mReturn;
	
	@ViewInject(R.id.search_menuName)
	public EditText search_menuName;
	
	@ViewInject(R.id.search_Btnsearch)
	public TextView search_Btnsearch;
	
	@ViewInject(R.id.gv_menu_name)
	public GridView gridview;
	
	private List<Map<String,Object>> data_list;
	private SimpleAdapter sim_adapter;
	
	Intent intent=new Intent();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		x.view().inject(this);
		init();
	}

	//初始化数据
	private void init() {
		MygridViewInfo();
	}
	
	@Event(value={R.id.return_of_search,R.id.search_Btnsearch})
	private void SearchOnClick(View v){
		switch (v.getId()) {
		case R.id.return_of_search:
			SearchActivity.this.finish();
			break;
		case R.id.search_Btnsearch:
			if (search_menuName.getText().toString()!=null) {
				intent.setClass(SearchActivity.this,ListiewClassifyActivity.class);
				intent.putExtra("MenuName", search_menuName.getText().toString());
				startActivity(intent);
			}else{
				ToastUtils.showToastInThread(this, "菜名不能为空！");
			}
			
			break;
		}
	}
	
	
	
	private void MygridViewInfo() {
		
		String[] str = new String[] { "肉","素菜","湘菜","粤菜", "面食", "川菜", "汤", "火锅","凉菜","烘焙","家常菜","快手菜"};
		data_list=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < str.length; i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("txt", str[i]);
			data_list.add(map);
		}
		
		String[] from={"txt"};
		int[] to={R.id.tv_gv_search};
		sim_adapter=new SimpleAdapter(this, data_list, R.layout.activity_gridview_search, from, to);
		gridview.setAdapter(sim_adapter);
		
		// listview的点击事件
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				//System.out.println("这个位于"+(id+1)+"号位置;"+"position="+position+";"+data_list.get(position).toString());
				//String str=data_list.get(position).get("txt").toString();				
				intent.setClass(SearchActivity.this,ListiewClassifyActivity.class);
				intent.putExtra("MenuName", data_list.get(position).get("txt").toString());
				startActivity(intent);
				
			}
		});

	}
	
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
