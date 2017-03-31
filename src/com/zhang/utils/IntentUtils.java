package com.zhang.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class IntentUtils  {

	public static void getIntent(Context packageContext,Class<?> cls){
		if(packageContext==null){
			return;
		}
		if(cls==null){
			return;
		}
		Intent intent=new Intent(packageContext,cls);
		packageContext.startActivity(intent);
		//overridePendingTransition(R.anim.slide_up_in,R.anim.zoom_exit);
	}
}
