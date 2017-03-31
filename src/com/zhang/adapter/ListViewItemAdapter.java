package com.zhang.adapter;

import java.util.List;
import com.loopj.android.image.SmartImageView;
import com.zhang.adapter.ListviewAdapter.HorldView;
import com.zhang.entity.MenuEntity;
import com.zhang.entity.StepsEntity;
import com.zhang.ui.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * ÏêÏ¸½çÃæ²ËÆ×²½Öè
 * */
public class ListViewItemAdapter extends BaseAdapter{

	public List<MenuEntity> list;
	public Context context;

	public ListViewItemAdapter(Context context, List<MenuEntity> list) {
		this.context = context;
		this.list = list;
	}
	
	class HorldView {
		public SmartImageView listimg;
		public TextView tvstep,tvnum;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HorldView h=null;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.activity_steps_item, null);
	     		h=new HorldView();			
	     		h.tvstep = (TextView) convertView.findViewById(R.id.steps_step);
	     		h.tvnum = (TextView) convertView.findViewById(R.id.steps_stepNum);
	     		h.listimg = (SmartImageView) convertView.findViewById(R.id.step_menu_img);
				convertView.setTag(h); 
		}else{
			h=(HorldView) convertView.getTag();
		}

		h.tvnum.setText((position+1)+"");
		h.tvstep.setText(list.get(0).getSteps().get(position).getStep());
		h.listimg.setImageUrl(list.get(0).getSteps().get(position).getImg());
		
		return convertView;
	}

	
	
	

	@Override
	public int getCount() {
		return list.get(0).getSteps().size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(0).getSteps().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
}
