package com.zhang.adapter;

import java.util.List;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Ö÷½çÃæËéÆ¬
 * */
public class ViewPagerMenuFragAdapter extends PagerAdapter {

	public List<View> list;
	public ViewPagerMenuFragAdapter(List<View> list){
		this.list=list;
	}
	
	@Override
	public int getCount() {return Integer.MAX_VALUE;}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {return arg0==arg1;}
	
	@Override    
	public void destroyItem(ViewGroup container, int position,Object object) {
		//position 0,1,2,3....
		((ViewGroup) container).removeView(list.get(position%list.size()));
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		
		container.addView(list.get(position%list.size()));
		
		 /*ViewGroup parent = (ViewGroup) container.getParent();
		 if (parent != null) {
			 parent.removeAllViews();
		 } */
		//container.addView(list.get(position%list.size()));
		
		/*View v=list.get(position);
		if (v.getParent() != null) {
			 ((ViewGroup) v).removeAllViews();
		} 
		container.addView(v);*/
		
		return list.get(position%list.size()); //v  //list.get(position%list.size())
	}

	
}
