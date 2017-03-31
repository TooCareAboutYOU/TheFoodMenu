package com.zhang.service;

import com.zhang.app.MainApplication;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class BaseTools {
	/**
	 * ��ȡ��ǰӦ�ð汾��
	 * @param context
	 * @return version
	 * @throws Exception
	 */
	public static String getAppVersion(){
		String versionName="";
		try {
			// ��ȡpackagemanager��ʵ��
			PackageManager packageManager = MainApplication.context.getPackageManager();
			// getPackageName()���㵱ǰ��İ�����0�����ǻ�ȡ�汾��Ϣ
			PackageInfo packInfo = packageManager.getPackageInfo(MainApplication.context.getPackageName(),0);
			versionName = packInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}
	
	
	/**
	 * ��ȡ��ǰϵͳSDK�汾��
	 */
	public static int getSystemVersion(){
		/*��ȡ��ǰϵͳ��android�汾��*/
		int version= android.os.Build.VERSION.SDK_INT;
		return version;
	}
}
