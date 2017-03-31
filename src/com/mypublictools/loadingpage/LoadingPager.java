package com.mypublictools.loadingpage;

import com.zhang.ui.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public class LoadingPager extends FrameLayout{

	private LayoutInflater inflater;
	public View loading;
	public View error;
	public View content;
	
	/*������*/
	//��Ҫ��ʾ�����ݵ���ͼ���ؽ���������ȷ���������ݵ�view��ʲô������дview
	public void setContent(View content){
		this.content=content;
		if(content!=null){
			addView(content);
		}
	}
	
	/*���岽*/
	//���������쳣�ĵ���¼�
	public void setOnClickError(OnClickListener listener){
		error.setOnClickListener(listener);
	}
	
	
	/*���Ĳ�*/
	//д���������ֱ���ʾ������ͬ״̬�Ľ���
	public void setVisibilityLoading(){  //��ʾ���ڼ���
		content.setVisibility(View.GONE);
		error.setVisibility(View.GONE);
		loading.setVisibility(View.VISIBLE);
	}
	public void setVisibilityError(){    //��ʾ�����쳣
		content.setVisibility(View.GONE);
		error.setVisibility(View.VISIBLE);
		loading.setVisibility(View.GONE);
	}
	public void setVisibilityContent(){    //��ʾ��������
		content.setVisibility(View.VISIBLE);
		error.setVisibility(View.GONE);
		loading.setVisibility(View.GONE);
	}
	
	/*��һ��*/
	//�������ڼ��ء��͡������쳣���ӵ�FrameLayout��
	public void inti(Context context){
		inflater=LayoutInflater.from(context);
		loading=inflater.inflate(R.layout.loadingpage, null);  //xml�ļ�ת��Ϊ��ͼ
		error=inflater.inflate(R.layout.errorpage, null);
		this.addView(loading);
		this.addView(error);
	}
	
	
	/*�ڶ���*/
	//��java�д�����ʱ��Ҫ���õ�
	public LoadingPager(Context context) {
		super(context);
		inti(context);  //������ؼ���������ʱ��͵��ó�ʼ������
	}
	//��xml�д�����ʱ�����
	public LoadingPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		inti(context);  //������ؼ���������ʱ��͵��ó�ʼ������
	}
	
}
