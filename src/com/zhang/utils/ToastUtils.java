package com.zhang.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

	public static void showToastInThread(final Context context,final String msg){
        ((Activity) context).runOnUiThread(new Runnable(){
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
	
}
