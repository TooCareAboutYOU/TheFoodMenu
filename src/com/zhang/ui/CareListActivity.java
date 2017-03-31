package com.zhang.ui;


import java.util.List;
import org.xutils.x;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import com.loopj.android.image.SmartImageView;
import com.umeng.analytics.MobclickAgent;
import com.zhang.DataBaseDo.DBMenuInfo;
import com.zhang.entity.MenuEntity;
import com.zhang.net.HttpIP;
import com.zhang.utils.InternetUtils;
import com.zhang.utils.ListSlideView;
import com.zhang.utils.ListSlideView.OnSlideListener;
import com.zhang.utils.ListViewCompatUtils;
import com.zhang.utils.ToastUtils;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;


@ContentView(R.layout.activity_care_list)
public class CareListActivity extends Activity  implements OnItemClickListener, OnClickListener, OnSlideListener{

	/*private ListView listview;
	private CareAdapter adapter;
	private ImageView mReturn;*/
	private List<MenuEntity> list=DBMenuInfo.getInstance().getCollect();
	private ListViewCompatUtils mListView;
	private ListSlideView mLastSlideViewWithStatusOn;
	private DeleteCareAdapter adapter;
	private String mID;
	private String menuname;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		x.view().inject(this);
		init();
		mListView = (ListViewCompatUtils) findViewById(R.id.listSlide);	
		adapter=new DeleteCareAdapter();
		mListView.setAdapter(adapter);
		//mListView.setOnClickListener(this);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long id) {
				init();
				if(HttpIP.NET_STATE){
					Intent intents=new Intent(CareListActivity.this,StepsActivity.class);
					intents.putExtra("StepId", list.get(position).getmId());
					startActivity(intents);
				}else{
					ToastUtils.showToastInThread(CareListActivity.this, "网络异常");
				}
			}
		});
		
	}
	
	/**数据初始化*/
	private void  init(){
		judgeInternet();
		listviewOpt();

		
	}
	
	
	
	public class DeleteCareAdapter extends BaseAdapter   {

		public LayoutInflater mInflater;
		
		DeleteCareAdapter(){ super(); mInflater = getLayoutInflater(); }
		
		@Override
		public int getCount() { return list.size(); }

		@Override
		public Object getItem(int position) { return list.get(position); }

		@Override
		public long getItemId(int position) { return position; }

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			HorldView h;
			ListSlideView slideView=(ListSlideView) convertView;
			if(slideView==null){
				View itemView=LayoutInflater.from(CareListActivity.this).inflate(R.layout.activity_care_list_item, null);
				slideView=new ListSlideView(CareListActivity.this);
				slideView.setContentView(itemView);
		     	h=new HorldView(slideView);
		     	slideView.setOnSlideListener(CareListActivity.this);
		     	slideView.setTag(h); 
			}else{
				h=(HorldView) slideView.getTag();
			}
			
			MenuEntity me=list.get(position);
			me.slideView=slideView;
			me.slideView.shrink();
			h.tvtags.setText(list.get(position).getTags());
			h.tvname.setText(list.get(position).getTitle());
			h.listimg.setImageUrl(list.get(position).getAlbums());
			h.deleteHolder.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {					
					
					AlertDialog.Builder dialog=new Builder(CareListActivity.this);
					dialog.setTitle("提   示");
					dialog.setMessage("确定要删除"+list.get(position).getTitle()+"？");
					dialog.setNegativeButton("取消", new  DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							arg0.dismiss();
						}
					});
					dialog.setPositiveButton("确定",  new  DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							MenuEntity me=list.get(position);
							me.setCare(false);
							DBMenuInfo.getInstance().Delcollection(me);
							list.remove(position);
							DeleteCareAdapter.this.notifyDataSetChanged();
							ToastUtils.showToastInThread(CareListActivity.this, "删除成功");
							arg0.dismiss();
						}

					}).show();
					//list.clear();
					
					init();
				}
			});
			
			return slideView;
		}
	}
	
	private static class HorldView {
		public SmartImageView listimg;
		public TextView tvtags,tvname;
		public ViewGroup deleteHolder;
		HorldView(View view){
			listimg = (SmartImageView) view.findViewById(R.id.care_image);
			tvname = (TextView) view.findViewById(R.id.care_menuname);
     		tvtags = (TextView) view.findViewById(R.id.care_tags);
			deleteHolder=(ViewGroup) view.findViewById(R.id.holder);
		}
	}
	
	@Override
	public void onSlide(View view, int status) {
		if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
            mLastSlideViewWithStatusOn.shrink();
        }

        if (status == SLIDE_STATUS_ON) {
            mLastSlideViewWithStatusOn = (ListSlideView) view;
        }
		
	}
	
	@Override
	public void onClick(View arg0) { }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) { }
	

	/**=============================================================*/
	
	
	@Event(value={R.id.return_of_care})
	private void  CareOnClick(View v){
		switch (v.getId()) {
			case R.id.return_of_care:
				CareListActivity.this.finish();
				break;
		}
	}
	
	
	/*private void DialogView(final String mID,String name){
		AlertDialog.Builder dialog=new Builder(CareListActivity.this);
		dialog.setTitle("提   示");
		dialog.setMessage("确定要删除"+name+"？");
		dialog.setNegativeButton("取消", new  DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		});
		dialog.setPositiveButton("确定",  new  DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				System.out.println("删除===mID"+mID);
				MenuEntity me=list.get(position);
				me.setCare(false);
				DBMenuInfo.getInstance().Delcollection(me);
				ToastUtils.showToastInThread(CareListActivity.this, "删除成功");
				arg0.dismiss();
				
			}

		}).show();
		
	}*/
	
	/**ListView操作*/
	private void listviewOpt(){
		/*listview=(ListView) findViewById(R.id.care_listview);
		if(HttpIP.NET_STATE!=false){
			listview.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position,long id) {
					Intent intents=new Intent(CareListActivity.this,StepsActivity.class);
					intents.putExtra("StepId", list.get(position).getmId());
					startActivity(intents);
				}
			});
		}

		adapter=new CareAdapter(this,list);
		listview.setAdapter(adapter);*/
	}

	/**网络判断*/
    public void judgeInternet(){
        if(HttpIP.NET_STATE){
        	boolean netWork=InternetUtils.isNet(this);
            boolean wifiWork=InternetUtils.isWIFI(this);
            boolean gprsWork=InternetUtils.isGPRS(this);
            if(netWork){/*ToastUtils.showToastInThread(this, "网络连接成功!"); */HttpIP.NET_STATE=true;} 
            if(wifiWork){/*ToastUtils.showToastInThread(this, "当前WIFI连接"); */HttpIP.NET_STATE=true;}  
            if(gprsWork){/*ToastUtils.showToastInThread(this, "当前移动数据连接");*/ HttpIP.NET_STATE=true; }
            if(netWork==false){ ToastUtils.showToastInThread(this, "网络异常!"); HttpIP.NET_STATE=false; }
        }
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

