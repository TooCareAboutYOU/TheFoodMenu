package com.zhang.adapter;

import java.util.List;

import com.loopj.android.image.SmartImageView;
import com.zhang.adapter.ListviewAdapter.HorldView;
import com.zhang.entity.MenuEntity;
import com.zhang.ui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 我的收藏列表
 * */
public class CareAdapter  {  //extends BaseAdapter

	/*public List<MenuEntity> list;
	public Context context;

	public CareAdapter(Context context, List<MenuEntity> list) {
		this.context = context;
		this.list = list;
	}
	
	class HorldView {
		public SmartImageView listimg;
		public TextView tvtags,tvname;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HorldView h=null;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.activity_care_list_item, null);
	     		h=new HorldView();			
	     		h.listimg = (SmartImageView) convertView.findViewById(R.id.care_image);
	     		h.tvname = (TextView) convertView.findViewById(R.id.care_menuname);
	     		h.tvtags = (TextView) convertView.findViewById(R.id.care_tags);
				convertView.setTag(h); 
		}else{
			h=(HorldView) convertView.getTag();
		}
		
		MenuEntity me=list.get(position);
		h.tvtags.setText(list.get(position).getTags());
		h.tvname.setText(list.get(position).getTitle());
		h.listimg.setImageUrl(list.get(position).getAlbums());
		
		
		return convertView;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}*/
	
}
