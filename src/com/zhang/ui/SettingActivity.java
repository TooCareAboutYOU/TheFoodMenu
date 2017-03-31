package com.zhang.ui;

import org.xutils.x;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import com.umeng.analytics.MobclickAgent;
import com.zhang.service.Version;
import com.zhang.utils.IntentUtils;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.View;
import android.view.Window;

@ContentView(R.layout.activity_setting)
public class SettingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		x.view().inject(this);
	}

	//点击事件
	@Event(value={R.id.btn_update,R.id.return_of_set,R.id.btn_aboutUs})
	public void BtnOnClick(View v){
		switch (v.getId()) {
		case R.id.return_of_set:
			SettingActivity.this.finish();
			break;
		case R.id.btn_update:
			VersionInfo();  //APP版本更新
			break;
		case R.id.btn_aboutUs:
			IntentUtils.getIntent(SettingActivity.this, AboutActivity.class);
			break;
		}
	}
	
	
	
	
	/**版本更新操作*/
	public void VersionInfo(){
		Version app = new Version(this);
		String url ="";
		String path =Environment.getExternalStorageDirectory().getAbsolutePath()+"/Android/data/"+getPackageName()+"/file/a.apk";
		app.updataApp("",url,path);
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
