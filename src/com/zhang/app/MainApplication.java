package com.zhang.app;

import java.io.File;
import org.xutils.x;
import com.zhang.DataBaseDo.DBMenuInfo;
import android.app.Application;
import android.content.Context;
import android.os.Environment;

public class MainApplication extends Application {

	public static Context context;
	public final String PATH=Environment.getExternalStorageDirectory().getAbsolutePath();
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		x.Ext.setDebug(true);
		context=this;
		
		if(!new File(PATH+"/MYMenu.db").exists()){
			DBMenuInfo.getInstance().init();
		}
	}

	
	
}
