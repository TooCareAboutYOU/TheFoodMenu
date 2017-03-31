package com.zhang.utils;

import com.zhang.entity.MenuEntity;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

public class ListViewCompatUtils extends ListView {

	private static final String TAG = "ListViewCompat";

    private ListSlideView mFocusedItemView;

    public ListViewCompatUtils(Context context) {
        super(context);
    }

    public ListViewCompatUtils(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewCompatUtils(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void shrinkListItem(int position) {
        View item = getChildAt(position);

        if (item != null) {
            try {
                ((ListSlideView) item).shrink();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN: {
            int x = (int) event.getX();
            int y = (int) event.getY();
            int position = pointToPosition(x, y);
            //Log.e(TAG, "postion=" + position);
            if (position != INVALID_POSITION) {
                MenuEntity data = (MenuEntity) getItemAtPosition(position);
                mFocusedItemView = data.slideView;
            }
        }
        default:
            break;
        }

        if (mFocusedItemView != null) {
            mFocusedItemView.onRequireTouchEvent(event);
        }

        return super.onTouchEvent(event);
    }
}