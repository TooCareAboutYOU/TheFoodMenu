package com.zhang.app;

import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.os.Bundle;


public abstract class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setView();
		initView();
		setListener();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	/**
	 * ���ò����ļ�
	 */
	public abstract void setView();

	/**
	 * ��ʼ�������ļ��еĿؼ�
	 */
	public abstract void initView();

	/**
	 * ���ÿؼ��ļ���
	 */
	public abstract void setListener();
	
}
