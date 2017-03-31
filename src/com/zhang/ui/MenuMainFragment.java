package com.zhang.ui;

import java.util.ArrayList;
import java.util.List;
import com.style.viewpager.RotateDownPageTransformer;
import com.umeng.analytics.MobclickAgent;
import com.zhang.adapter.ViewPagerMenuFragAdapter;
import com.zhang.utils.ToastUtils;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ImageView.ScaleType;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MenuMainFragment extends Fragment {

	public View mparents;
	private Intent intent = new Intent();

	private ViewPager mViewPager;
	private int index,page;
	
	private RadioGroup rg;
	private RadioButton rb01,rb02,rb03,rb04;  //中、西、法快餐
	private RadioButton[] rbs=new RadioButton[4]; 
	private LinearLayout GOsearch;  //跳转至查询界面
	/**滚动文字----------------------*/
	private TextView tvGunggong;  
	private TextView tvTuijian;
	private boolean isloop = true;
	private String [] s1  = {"2016人人都是美食家","最后欢迎的千道家常宴客菜","365天，从零开始学拼盘"};
	private String [] s2  = {"菜谱征集","待客清单","今日推荐"};
	private int item = 0;
	private AnimationSet set = new AnimationSet(true);
	private AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
	private TranslateAnimation ta = new TranslateAnimation(
			Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF,
			0, Animation.RELATIVE_TO_SELF, 2.0f,
			Animation.RELATIVE_TO_SELF, 0);
	private Handler hand = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				if(item<3){
					if(isloop){
						tvGunggong.setText(s1[item]);
						tvGunggong.setAnimation(set);
						tvGunggong.startAnimation(set);
						tvTuijian.setText(s2[item]);
						tvTuijian.setAnimation(set);
						tvTuijian.startAnimation(set);
					 item+=1;
					}
				}else{
					item = 0;
					tvGunggong.setText(s1[item]);
					tvGunggong.setAnimation(set);
					tvGunggong.startAnimation(set);
					tvTuijian.setText(s2[item]);
					tvTuijian.setAnimation(set);
					tvTuijian.startAnimation(set);
					item+=1;
				}
				break;
			}
		};
	};


    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mparents=inflater.inflate(R.layout.activity_main_fragment_menu, container, false);
		init();
		return mparents;
	}
	
	//初始化
	public void init() {
		//viewpager广告轮播图
		viewpager();
		//二级四选下面菜单
		HoldrView();  
		//滚动文字标题
		CustomTextView();

	}

	
	/**标题文字滚动*/
	private void CustomTextView(){
		tvGunggong=(TextView) mparents.findViewById(R.id.tv_gunggong);
		tvTuijian=(TextView) mparents.findViewById(R.id.tv_tuijian);
		tvGunggong.setText(s1[0]);
		tvTuijian.setText(s2[0]);
		set.addAnimation(animation);
		set.addAnimation(ta);
		set.setDuration(1000);
		set.setRepeatMode(Animation.REVERSE);

		tvGunggong.setAnimation(set);
		tvTuijian.setAnimation(set);
		//实现自动切换界面
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isloop) {
					SystemClock.sleep(3500);
					hand.sendEmptyMessage(1);
				}
			}
		}).start();
	}
	
	
	//intent.setClass(getActivity(), TestActivity.class);
	// startActivity(intent);


	//viewpager下面菜单
	public void HoldrView(){
		GOsearch=(LinearLayout) mparents.findViewById(R.id.ll_search);GOsearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				intent.setClass(getActivity(), SearchActivity.class);
				startActivity(intent);
			}
		});
		rg=(RadioGroup) mparents.findViewById(R.id.rg_group);
		rbs[0]=(RadioButton) mparents.findViewById(R.id.rb_zhongcan);
		rbs[1]=(RadioButton) mparents.findViewById(R.id.rb_xican);
		rbs[2]=(RadioButton) mparents.findViewById(R.id.rb_facan);
		rbs[3]=(RadioButton) mparents.findViewById(R.id.rb_kuaican);
		
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup arg0, int checkedId) {
				switch (checkedId) {
				case R.id.rb_zhongcan: ToastUtils.showToastInThread(getActivity(), "中餐") ; break;
				case R.id.rb_xican: ToastUtils.showToastInThread(getActivity(), "西餐"); break;
				case R.id.rb_facan: ToastUtils.showToastInThread(getActivity(), "法餐"); break;
				case R.id.rb_kuaican: ToastUtils.showToastInThread(getActivity(), "快餐");  break;
				}
			}
		});
	}
	
	
	
	
	
	
	/**ViewPager布局*/
	public void viewpager() {
		setPicture();
		setRadioSelected(0);
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int i) {
				setRadioSelected(i);   //滑动到第几页 :arg0
				index=i;
			
				if(i>4){
					i=i%4;
					setRadioSelected(i);
				}
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});
		
		//线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {Thread.currentThread().sleep(5000);}   //耗时操作 
					catch (Exception e) {e.printStackTrace();}
					handler.sendEmptyMessage(1);  //利用handler发送一个空的消息
				}
			} 
		}).start();

	}
	
	//Handler——消息队列。接受子线程发送的数据和在子线程中发送消息。
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {   //接收handler发送的消息
			mViewPager.setCurrentItem(index+1);  //设置当前ViewPger显示第几页
		}
	};
	
	//填充图片的ViewPager
	public void setPicture(){
		mViewPager=(ViewPager) mparents.findViewById(R.id.frag01_viewpager);
		List<View> list=new ArrayList<View>();
		int[] mImg=new int[]{R.drawable.default01,R.drawable.default02,R.drawable.default03,R.drawable.default04};
		for(int i=0;i<mImg.length;i++){
			ImageView imageview=new ImageView(getActivity());
			imageview.setImageResource(mImg[i]);
			imageview.setScaleType(ScaleType.CENTER_CROP);
			list.add(imageview);
		}
		//位ViewPager添加切换动画效果(3.0以上)
		//特效1
		//mViewPager.setPageTransformer(true, new DepthPageTransformer());
		//特效2
		//mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
		//特效3
		mViewPager.setPageTransformer(true, new RotateDownPageTransformer());
		
		mViewPager.setAdapter(new ViewPagerMenuFragAdapter(list));

	}
	
	//引导页按钮标识
	public void setRadioSelected(int index){
		LinearLayout linearL=(LinearLayout) mparents.findViewById(R.id.frag01_ll_radiobutton);
		RadioButton[] rb=new RadioButton[linearL.getChildCount()];
		for(int i=0;i<rb.length;i++){
			rb[i]=(RadioButton) linearL.getChildAt(i);
			if(index==i){  //给要设置的index等于当前的下标，那么就设置当前下标所对应的RadioButton
				rb[i].setChecked(true);
				rb[i].setButtonDrawable(R.drawable.point_red);
			}else{
				rb[i].setChecked(false);
				rb[i].setButtonDrawable(R.drawable.point_gray);
			}
		}
		
	}
	
	
	public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("zhang"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("zhang"); 
    }

    
    
}
