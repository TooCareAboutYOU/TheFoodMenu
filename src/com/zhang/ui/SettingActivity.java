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

	//����¼�
	@Event(value={R.id.btn_update,R.id.return_of_set,R.id.btn_aboutUs})
	public void BtnOnClick(View v){
		switch (v.getId()) {
		case R.id.return_of_set:
			SettingActivity.this.finish();
			break;
		case R.id.btn_update:
			VersionInfo();  //APP�汾����
			break;
		case R.id.btn_aboutUs:
			IntentUtils.getIntent(SettingActivity.this, AboutActivity.class);
			break;
		}
	}
	
	
	
	
	/**�汾���²���*/
	public void VersionInfo(){
		Version app = new Version(this);
		String url ="";
		String path =Environment.getExternalStorageDirectory().getAbsolutePath()+"/Android/data/"+getPackageName()+"/file/a.apk";
		app.updataApp("",url,path);
	}
	
	
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
