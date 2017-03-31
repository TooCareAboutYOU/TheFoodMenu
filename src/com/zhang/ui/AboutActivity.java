package com.zhang.ui;

import org.xutils.x;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import com.zhang.app.BaseTools;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

@ContentView(R.layout.activity_about)
public class AboutActivity extends Activity {

	/**°æ±¾ºÅ*/
	@ViewInject(R.id.version_name)
	private TextView versionName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		x.view().inject(this);
		init();
	}
	
	private void init() {
		versionName.setText(BaseTools.getAppVersion()); 
	}
	
	
	@Event(value={R.id.return_of_about})
	private void  CareOnClick(View v){
		switch (v.getId()) {
			case R.id.return_of_about:
				AboutActivity.this.finish();
				break;
		}
	}

	
	
}
