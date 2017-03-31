package com.zhang.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.umeng.analytics.MobclickAgent;
import com.zhang.adapter.ListViewClassfiyLeftAdapter;
import com.zhang.data.HttpGetClassifyData;
import com.zhang.entity.ClassifyEntity;
import com.zhang.net.HttpIP;
import com.zhang.utils.InternetUtils;
import com.zhang.utils.ToastUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class ClassifyMainFragment extends Fragment {

	public View mparents;

	private GridView gridview;
	private List<Map<String, Object>> gv_list;
	private SimpleAdapter sim_adapter;
	private List<ClassifyEntity> list=new ArrayList<ClassifyEntity>();

	private ListView listview;
	private ListViewClassfiyLeftAdapter adapter;
	
	private ImageView Net_Off;  //没有网络显示
	private LinearLayout Net_On;  //有网络显示
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mparents = inflater.inflate(R.layout.activity_main_fragment_classify,container, false);
		listview=new ListView(getActivity());
		listview = (ListView) mparents.findViewById(R.id.mainClassify_listview);
		gridview = (GridView) mparents.findViewById(R.id.frag01_gv_view);
		
		Net_Off=(ImageView) mparents.findViewById(R.id.Tag_of_net_off);
		Net_Off.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				init();
			}
		});
		Net_On=(LinearLayout) mparents.findViewById(R.id.Tag_of_net_on);
		
		init();
		
		return mparents;
	}




	/**初始化所有方法*/
	private void init() {
		judgeInternet();
		if(HttpIP.NET_STATE){
			Net_Off.setVisibility(View.GONE);
			Net_On.setVisibility(View.VISIBLE);
			//GETClassifyData();  //获取分类
			//HolderView();
		}else{
			Net_Off.setVisibility(View.VISIBLE);
			Net_On.setVisibility(View.GONE);
		}
		
		
		
	}
	
	/**设置控件*/
	public void HolderView() {
		/**一级菜单listview*/
		adapter=new ListViewClassfiyLeftAdapter(getActivity(), list);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
				getGridviewInfo(position);  //将数据设置到控件中
			}
		});
		
		
	}

	public void GETClassifyData(){
		HttpGetClassifyData data=new HttpGetClassifyData(getActivity(),handler);
		data.getClassifyData();
	}

	//Handler——消息队列。接受子线程发送的数据和在子线程中发送消息。
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {   //接收handler发送的消息
			switch (msg.what) {
			case 1:
				 List<ClassifyEntity> mlist=(List<ClassifyEntity>) msg.obj;
					if(mlist!=null){
						list.addAll(mlist); 
						HolderView();
						getGridviewInfo(0);
					}
			}
		}
	};

	
	/** 二级菜单GridVeiw布局 */
	public void getGridviewInfo(final int index) {
		gridview.setFocusable(false);
		String[] str=new String[list.get(index).getClassList().size()];
		for (int i = 0; i < list.get(index).getClassList().size(); i++) {
			str[i]=list.get(index).getClassList().get(i).getName();
		}
		gv_list = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < str.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			//map.put("image", img[i]);
			map.put("txt", str[i]);
			gv_list.add(map);
		}
		String[] from = { "txt" };  //"image",
		int[] to = { R.id.tv_gv };  //R.id.img_gridv,
		sim_adapter = new SimpleAdapter(getActivity(), gv_list,R.layout.activity_gridview, from, to);
		gridview.setAdapter(sim_adapter);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				Intent intent=new Intent(getActivity(),ListiewClassifyActivity.class);
				intent.putExtra("cid", list.get(index).getClassList().get(position).getId());
				startActivity(intent);
			}
		});
	}
	
	
	/**网络判断*/
    public void judgeInternet(){
    	boolean netWork=InternetUtils.isNet(getActivity());
        boolean wifiWork=InternetUtils.isWIFI(getActivity());
        boolean gprsWork=InternetUtils.isGPRS(getActivity());
        if(netWork){/*ToastUtils.showToastInThread(this, "网络连接成功!"); */HttpIP.NET_STATE=true;} 
        if(wifiWork){/*ToastUtils.showToastInThread(this, "当前WIFI连接"); */HttpIP.NET_STATE=true;}  
        if(gprsWork){/*ToastUtils.showToastInThread(this, "当前移动数据连接");*/ HttpIP.NET_STATE=true; }
        if(netWork==false){ ToastUtils.showToastInThread(getActivity(), "网络异常!"); HttpIP.NET_STATE=false; }
      
    	
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("zhang"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("zhang"); 
    }
    

}
