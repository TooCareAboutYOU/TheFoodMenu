package com.zhang.service;

import java.io.File;
import org.xutils.x;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;


public class Version {
	
	public Context context;
	public Version(Context context) {
		this.context=context;
	}
	
	public boolean isUpdate(String  appInfUrl){  //���汾���£�֪ͨ��ľ�и���
		
		return true;
	}

	public void updataApp(String  appInfUrl , final String url , final String path) {  //����APP
		if(isUpdate(appInfUrl)){
        //���أ�
			AlertDialog.Builder   dialog  = new AlertDialog.Builder(context);
			dialog.setTitle("�汾����");
			dialog.setMessage("�Ѿ���⵽���°汾���Ƿ����?'");
			dialog.setNegativeButton("ȡ��", new OnClickListener() {	
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			dialog.setPositiveButton("ȷ��", new OnClickListener() {	
				public void onClick(DialogInterface dialog, int which) {
					appDownload (url,path);
				}
			});
			dialog.show();
		}else{
			//�Ѿ������°汾
		}
	}
	
	public void appDownload(String url ,String path) {   //����app   path����·��
		url  ="http://apk.hiapk.com/appdown/com.netease.cartoonreader?webparams=sviptodoc291cmNlPTkz";  //url����·��
		RequestParams params = new RequestParams(url);
        params.setSaveFilePath(path);
        x.http().get(params,new DownloadCallback());
	}

	
	ProgressDialog  pd = null;
	class DownloadCallback implements Callback.CacheCallback<File>,Callback.ProgressCallback<File>{
		@Override
		public void onCancelled(CancelledException arg0) {
			//ȡ��
		}

		@Override
		public void onError(Throwable arg0, boolean arg1) {
			//����
		}

		@Override
		public void onFinished() {
			//���
		}

		@Override
		public void onSuccess(File arg0) {
			pd.dismiss();
		}

		@Override
		public void onLoading(long arg0, long arg1, boolean arg2) {
			//System.out.println(arg0+"/=="+arg1+"/="+arg2);
			if(pd!=null){
				//int max=Integer.parseInt(sizeconvert(arg0));
				//int progress=Integer.parseInt(sizeconvert(arg1));
				System.out.println("----------"+sizeconvert(arg0)+"-----------"+sizeconvert(arg1));
				//pd.setProgressNumberFormat("%1d kb/%2d kb");  
				pd.setMax((int)arg0);
				pd.setProgress((int)arg1);
			}
		}

		@Override
		public void onStarted() {
			//��ʼ����
			 pd = new ProgressDialog(context); 
			 pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			 pd.show();
		}

		@Override
		public void onWaiting() {
			//�ȴ�
		}

		@Override
		public boolean onCache(File arg0) {
			
			return false;
		}
		
	}
	
	
	//��Сת��
	public String sizeconvert(long size){
		 long kb = 1024;
	        long mb = kb * 1024;
	        long gb = mb * 1024;
	        if (size >= gb) {
	            return String.format("%.1f GB", (float) size / gb);
	        } else if (size >= mb) {
	            float f = (float) size / mb;
	            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
	        } else if (size >= kb) {
	            float f = (float) size / kb;
	            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
	        } else
	            return String.format("%d B", size);
	}
}
