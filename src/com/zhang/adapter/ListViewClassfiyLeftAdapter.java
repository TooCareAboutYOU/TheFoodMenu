package com.zhang.adapter;

import java.util.List;
import com.loopj.android.image.SmartImageView;
import com.zhang.adapter.ListViewItemAdapter.HorldView;
import com.zhang.entity.ClassifyEntity;
import com.zhang.entity.MenuEntity;
import com.zhang.ui.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
/**
 * 主界面的分类一级菜单
 * */
public class ListViewClassfiyLeftAdapter extends BaseAdapter{

	public List<ClassifyEntity> list;
	public Context context;

	public ListViewClassfiyLeftAdapter(Context context, List<ClassifyEntity> list) {
		this.context = context;
		this.list = list;
	}
	
	class HorldView {
		public TextView menuname;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HorldView h=null;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.activity_listview_item_classfiy_leff, null);
	     		h=new HorldView();			
	     		h.menuname=(TextView) convertView.findViewById(R.id.item_classfiy_left);
				convertView.setTag(h); 
		}else{
			h=(HorldView) convertView.getTag();
		}
		
		h.menuname.setText(list.get(position).getName());

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
	}
	
}
