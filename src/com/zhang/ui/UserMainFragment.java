package com.zhang.ui;

import com.umeng.analytics.MobclickAgent;
import com.zhang.utils.IntentUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class UserMainFragment extends Fragment {

	public View mparents;
	
	private RelativeLayout user_care;
	private ImageView mSet;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mparents=inflater.inflate(R.layout.activity_main_fragment_user, container, false);
		init();
		
		
		return mparents;
	}
	
	private void init(){
		user_care=(RelativeLayout) mparents.findViewById(R.id.relayout_shoucang);
		user_care.setOnClickListener(new UserOnClick());
		mSet=(ImageView) mparents.findViewById(R.id.set);
		mSet.setOnClickListener(new UserOnClick());
		
	}
	
	
	class UserOnClick implements OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.relayout_shoucang:
				IntentUtils.getIntent(getActivity(), CareListActivity.class);
				break;
			case R.id.set:
				IntentUtils.getIntent(getActivity(), SettingActivity.class);
				/*Intent intent=new Intent(getActivity(), SettingActivity.class);
				startActivity(intent);*/
				break;
			}
		}
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
