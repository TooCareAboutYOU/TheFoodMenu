package com.zhang.adapter;

import java.util.List;
import com.loopj.android.image.SmartImageView;
import com.zhang.DataBaseDo.DBMenuInfo;
import com.zhang.entity.MenuEntity;
import com.zhang.ui.R;
import com.zhang.utils.ToastUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 菜品分类查询列表
 * */
public class ListviewAdapter extends BaseAdapter {

	
	public List<MenuEntity> list;
	public Context context;
	public int state=0;
	public HorldView h=null;

	public ListviewAdapter(Context context, List<MenuEntity> list) {
		this.context = context;
		this.list = list;
	}
	
	class HorldView {
		public SmartImageView listimg;
		public TextView tvnum,tvname,tvburden;
		public ImageView imgCare;  //收藏
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.activity_listview_item, null);
	     		h=new HorldView();			
	     		h.tvnum = (TextView) convertView.findViewById(R.id.menu_id);
	     		h.tvname = (TextView) convertView.findViewById(R.id.menu_name);
	     		h.tvburden = (TextView) convertView.findViewById(R.id.menu_burdenss);
	     		h.imgCare=(ImageView) convertView.findViewById(R.id.menu_menu_care_off);
	     		h.listimg = (SmartImageView) convertView.findViewById(R.id.menu_image); 
				convertView.setTag(h); 
		}else{
			h=(HorldView) convertView.getTag();
		}
		
		h.tvnum.setText(list.get(position).getmId()+".");
		h.tvname.setText(list.get(position).getTitle());
		h.listimg.setImageUrl(list.get(position).getAlbums());
		h.tvburden.setText(list.get(position).getBurden());
		
		for (int i = 0; i < list.size(); i++) {
			MenuEntity me=list.get(position);
			me.setmId(list.get(position).getmId());
			
			DBMenuInfo.getInstance().SelectorDBList(list.get(position).getmId());
			
			if (DBMenuInfo.getInstance().SelectorDBList(list.get(position).getmId())) {
				list.get(position).setCare(DBMenuInfo.getInstance().SelectorDBList(list.get(position).getmId()));
				h.imgCare.setImageResource(R.drawable.icon_care_on32);
				
			}else{
				h.imgCare.setImageResource(R.drawable.icon_care_off32);
			}
		}
		
		
		if(list.get(position).getCare()==true){
			h.imgCare.setImageResource(R.drawable.icon_care_on32);
		}else{
			h.imgCare.setImageResource(R.drawable.icon_care_off32);
		}
		
		h.imgCare.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(state==0){
					//System.out.println("list.get(position)====="+list.get(position).getTitle());
					//Toast.makeText(context, "位置="+list.get(position).getTitle(), 0).show();
					ToastUtils.showToastInThread(context, "收藏成功！");
					MenuEntity me=list.get(position);
					me.setCare(true);
					DBMenuInfo.getInstance().SaveCollection(me);
					list.get(position).setCare(true);
					state=1;
					ListviewAdapter.this.notifyDataSetChanged();
				}else{
					//System.out.println("list.get(position)====="+list.get(position));
					ToastUtils.showToastInThread(context, "取消收藏！");
					MenuEntity me=list.get(position);
					me.setCare(false);
					DBMenuInfo.getInstance().SaveCollection(me);
					list.get(position).setCare(false);
					/*h.imgCare.setImageResource(R.drawable.icon_care_off32);*/
					state=0;
					ListviewAdapter.this.notifyDataSetChanged();
				}
			}
		});
		
		
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
