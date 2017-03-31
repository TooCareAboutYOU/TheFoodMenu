package com.zhang.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.xutils.x;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import com.loopj.android.image.SmartImageView;
import com.umeng.analytics.MobclickAgent;
import com.zhang.DataBaseDo.DBMenuInfo;
import com.zhang.adapter.ListViewItemAdapter;
import com.zhang.data.HttpGetStepsData;
import com.zhang.entity.MenuEntity;
import com.zhang.utils.ToastUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

@ContentView(R.layout.activity_steps)
public class StepsActivity extends Activity {

	@ViewInject(R.id.steps_scrollview)
	public ScrollView mScrollview;
	
	@ViewInject(R.id.steps_return)
	private ImageButton steps_return;
	
	@ViewInject(R.id.steps_menu_care)
	private TextView steps_menu_care;

	
	public List<Map<String, Object>> gv_list;
	public SimpleAdapter sim_adapter;
	public List<MenuEntity> list=new ArrayList<MenuEntity>();
	
	@ViewInject(R.id.step_menu_id)
	private TextView tvid;
	
	@ViewInject(R.id.step_menu_albums)
	private SmartImageView aibums;
	
	@ViewInject(R.id.step_menu_title)
	private TextView tvtitle;
	
	@ViewInject(R.id.step_menu_tags)
	private TextView tvtags;
	
	@ViewInject(R.id.step_menu_imtros)
	private TextView tvimtros;
	
	@ViewInject(R.id.step_menu_ingredients)
	private TextView tvingredients;
	
	@ViewInject(R.id.step_menu_burden)
	private TextView tvburden;

	
	@ViewInject(R.id.step_list)
	private ListView listview;
	private ListViewItemAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		x.view().inject(this);
		mScrollview.smoothScrollTo(0, 20);
		mScrollview.setFocusable(true);
		listview.setFocusable(false);
		adapter=new ListViewItemAdapter(StepsActivity.this,list);
		init();
	}
	

	//初始化数据
	private void init() {
		String myID=getIntent().getExtras().getString("StepId");
		getStepInfo(Integer.parseInt(myID));
	}
	

	//网络获取数据
	private void getStepInfo(int id){
		HttpGetStepsData hstep=new HttpGetStepsData(this,handler);
		hstep.getStepsData(id);
	}
	

	//Handler——消息队列。接受子线程发送的数据和在子线程中发送消息。
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {   //接收handler发送的消息
			switch (msg.what) {
			case 3:
				 List<MenuEntity> mlist=(List<MenuEntity>) msg.obj;
					if(mlist!=null){
						list.addAll(mlist); 
						setData();
					}
			}
		}
	};

	
	//设置控件数据
	private void setData(){
		tvid.setText(list.get(0).getId()+"");
		aibums.setImageUrl(list.get(0).getAlbums()+"");
		tvtitle.setText(list.get(0).getTitle()+"");
		tvtags.setText(list.get(0).getTags()+"");
		tvimtros.setText(list.get(0).getImtro()+"");
		tvingredients.setText(list.get(0).getIngredients()+"");
		tvburden.setText(list.get(0).getBurden()+"");		
		listview.setAdapter(adapter);
			
	}
	
	@Event(value={R.id.steps_return,R.id.steps_menu_care})
	private void StepOnClick(View v){
		switch (v.getId()) {
			case R.id.steps_return:
				StepsActivity.this.finish();
				break;
			case R.id.steps_menu_care:  //添加收藏
				MenuEntity me=list.get(0);
				me.setCare(true);
				DBMenuInfo.getInstance().SaveCollection(me);
				ToastUtils.showToastInThread(this, "收藏成功！");
				list.clear();
				break;
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
