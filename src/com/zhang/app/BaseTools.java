package com.zhang.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class BaseTools {
	/**
	 * 获取当前应用版本号
	 * @param context
	 * @return version
	 * @throws Exception
	 */
	public static String getAppVersion(){
		String versionName="";
		try {
			// 获取packagemanager的实例
			PackageManager packageManager = MainApplication.context.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(MainApplication.context.getPackageName(),0);
			versionName = packInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}
	
	
	/**
	 * 获取当前系统SDK版本号
	 */
	public static int getSystemVersion(){
		/*获取当前系统的android版本号*/
		int version= android.os.Build.VERSION.SDK_INT;
		return version;
	}
}
