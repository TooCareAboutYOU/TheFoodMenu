package com.zhang.ui;

import java.util.ArrayList;
import java.util.List;
import org.xutils.x;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import com.umeng.analytics.MobclickAgent;
import com.zhang.DataBaseDo.DBMenuInfo;
import com.zhang.entity.MenuEntity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


@ContentView(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

	
	@ViewInject(R.id.main_viewpager)
	public ViewPager main_viewpager;
	
	@ViewInject(R.id.main_radiogroup)
	public RadioGroup main_radiogroup;
	
	public RadioButton[] rbs=new RadioButton[3]; 
	
	public List<Fragment> list=new ArrayList<Fragment>();
	
	private List<MenuEntity> mlist=new ArrayList<MenuEntity>();
	
	public void init(){
		rbs[0]=(RadioButton) findViewById(R.id.rbtn_01);
        rbs[1]=(RadioButton) findViewById(R.id.rbtn_02);
        rbs[2]=(RadioButton) findViewById(R.id.rbtn_03);
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        
        DBMenuInfo.getInstance().saveCollect(mlist);

        init();
      //将Fragment设置到viewpager中
        list.add(new MenuMainFragment());
		list.add(new ClassifyMainFragment());
		list.add(new UserMainFragment());
        
        main_viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {	return list.size();	}
			@Override
			public Fragment getItem(int arg0) {	return list.get(arg0);}
		});
        //禁止viewpager左右滑动
        /*main_viewpager.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				return true;
			}
		});*/
        
      //rbs[0].setChecked(true); //默认选中第一个选项  ，但布局已设置
     // 设置viewpager监听事件：当viewpager滑动时改变radiobutton的页面
        main_viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {rbs[arg0].setChecked(true);}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});
        
      //设置radiobutton监听事件：当radiobutton点击时改变viewpager的页面
       main_radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rbtn_01: main_viewpager.setCurrentItem(0); break;
				case R.id.rbtn_02: main_viewpager.setCurrentItem(1); break;
				case R.id.rbtn_03: main_viewpager.setCurrentItem(2); break;
				}
			}
		});

       
    }
    
    
    @Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}


}
