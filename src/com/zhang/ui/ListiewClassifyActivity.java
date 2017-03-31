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
				Log.v("TAG", "ˢ��...");
				list.clear();
				getItemData();
				getSearchData(pn);
				xlistview.stopRefresh();
			}
			@Override
			public void onLoadMore() {
				Log.v("TAG","��������");
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

	
	//��ȡ��ѯ����
	public void getSearchData(int pn){
		String name=getIntent().getExtras().getString("MenuName");
		if (name!=null) {
			pn=(pn-1)*10+1;
			HttpGetSearchData hcI=new HttpGetSearchData(this,handler);
			hcI.getMenuData(name,pn);
		}
		
	}

	//��ȡ��������
	public void getItemData(){
		String cid=getIntent().getExtras().getString("cid");
		if (cid!=null) {
			HttpListViewData hcI=new HttpListViewData(this,handler);
			hcI.getListViewData(cid);
		}
		
	}
	
	
	//�����¼�
	class Listener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			Intent intent=new Intent(ListiewClassifyActivity.this,StepsActivity.class);
			intent.putExtra("StepId", list.get(position-1).getmId());
			startActivity(intent);
		}
	}
	
	

	
	//Handler������Ϣ���С��������̷߳��͵����ݺ������߳��з�����Ϣ��
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {   //����handler���͵���Ϣ
			switch (msg.what) {
				case 1:  //���������ķ���list
					//list.clear();	
					 List<MenuEntity> slist=(List<MenuEntity>) msg.obj;
						if(slist!=null){
							list.clear();
							list.addAll(slist);
							DBMenuInfo.getInstance().saveCollect(list);  //�������ݿ�
							adapter.notifyDataSetChanged();
						}else{
							xlistview.setPullLoadEnable(false);
							xlistview.stopLoadMore();
						}
						loadingpager.setVisibilityContent();
					break;
					
				/*case 2:   //��֮���������ѡ���ѯ��list
					//list.clear();	
					 List<MenuEntity> mlist=(List<MenuEntity>) msg.obj;
						if(mlist!=null){
							list.addAll(mlist);
							DBMenuInfo.getInstance().saveCollect(list);  //�������ݿ�
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
		    MobclickAgent.onPageStart("SplashScreen"); //ͳ��ҳ��(����Activity��Ӧ����SDK�Զ����ã�����Ҫ����д��"SplashScreen"Ϊҳ�����ƣ����Զ���)
		    MobclickAgent.onResume(this);          //ͳ��ʱ��
		}
		public void onPause() {
		    super.onPause();
		    MobclickAgent.onPageEnd("SplashScreen"); // ������Activity��Ӧ����SDK�Զ����ã�����Ҫ����д����֤ onPageEnd ��onPause ֮ǰ����,��Ϊ onPause �лᱣ����Ϣ��"SplashScreen"Ϊҳ�����ƣ����Զ���
		    MobclickAgent.onPause(this);
		}
}
