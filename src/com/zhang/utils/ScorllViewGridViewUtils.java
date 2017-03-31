package com.zhang.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class ScorllViewGridViewUtils extends GridView {

	public ScorllViewGridViewUtils(Context context) {
		super(context);
	}

	public ScorllViewGridViewUtils(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ScorllViewGridViewUtils(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	
	

}
