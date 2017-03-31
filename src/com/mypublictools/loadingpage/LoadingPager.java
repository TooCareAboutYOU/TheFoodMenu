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
	
	/*第三步*/
	//将要显示的内容的试图加载进来，因不能确定加载内容的view是什么，所以写view
	public void setContent(View content){
		this.content=content;
		if(content!=null){
			addView(content);
		}
	}
	
	/*第五步*/
	//设置网络异常的点击事件
	public void setOnClickError(OnClickListener listener){
		error.setOnClickListener(listener);
	}
	
	
	/*第四步*/
	//写三个方法分别显示三个不同状态的界面
	public void setVisibilityLoading(){  //显示正在加载
		content.setVisibility(View.GONE);
		error.setVisibility(View.GONE);
		loading.setVisibility(View.VISIBLE);
	}
	public void setVisibilityError(){    //显示网络异常
		content.setVisibility(View.GONE);
		error.setVisibility(View.VISIBLE);
		loading.setVisibility(View.GONE);
	}
	public void setVisibilityContent(){    //显示加载文字
		content.setVisibility(View.VISIBLE);
		error.setVisibility(View.GONE);
		loading.setVisibility(View.GONE);
	}
	
	/*第一步*/
	//将“正在加载”和“网络异常”加到FrameLayout中
	public void inti(Context context){
		inflater=LayoutInflater.from(context);
		loading=inflater.inflate(R.layout.loadingpage, null);  //xml文件转化为视图
		error=inflater.inflate(R.layout.errorpage, null);
		this.addView(loading);
		this.addView(error);
	}
	
	
	/*第二步*/
	//在java中创建的时候要调用的
	public LoadingPager(Context context) {
		super(context);
		inti(context);  //当这个控件被创建的时候就调用初始化方法
	}
	//在xml中创建的时候调用
	public LoadingPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		inti(context);  //当这个控件被创建的时候就调用初始化方法
	}
	
}
