package com.zhang.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetUtils {
	
	
	
	/*1���ж��Ƿ�������*/
	public static boolean isNet(Context context){
	        ConnectivityManager manager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
	        NetworkInfo WInfo=manager.getActiveNetworkInfo();
	        return WInfo!=null && WInfo.isAvailable();
	 }

	/*2���ж���WIFI��*/
	public static boolean isWIFI(Context context){
	        ConnectivityManager manager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
	        NetworkInfo WInfo=manager.getActiveNetworkInfo();
	        return WInfo!=null && WInfo.getType()==ConnectivityManager.TYPE_WIFI;
	 }

	/*3���ж�����������*/
	public static boolean isGPRS(Context context){
	        ConnectivityManager manager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
	        NetworkInfo WInfo=manager.getActiveNetworkInfo();
	        return WInfo!=null && WInfo.getType()==ConnectivityManager.TYPE_MOBILE;
	}
	
}
