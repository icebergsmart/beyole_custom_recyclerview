package com.beyole.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.view.View;

/**
 * 复写的RecyclerView
 * 
 * @date 2016/01/22
 * @author Iceberg
 * 
 *         注意事项： 1.关于无法implements
 *         RecyclerView.OnScrollListener这个问题,把targetSdkVersion设置成5
 *         .0以下就可以了,5.0以上无法implements 这个抽象接口
 * 
 */
public class MyRecyclerView extends RecyclerView implements OnScrollListener {

	public MyRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnScrollListener(this);
	}

	private View mCurrentView;
	private OnItemChangedListener mOnItemChangedListener;

	public interface OnItemChangedListener {
		void onChanged(View view, int position);
	}

	public void setOnItemChangedListener(OnItemChangedListener listener) {
		this.mOnItemChangedListener = listener;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		mCurrentView = getChildAt(0);
		if (mOnItemChangedListener != null) {
			mOnItemChangedListener.onChanged(mCurrentView, getChildPosition(mCurrentView));
		}
	}

	@Override
	public void onScrollStateChanged(int arg0) {

	}

	/**
	 * 滚动时判断当前第一个view是否改变，改变时才回调
	 */
	@Override
	public void onScrolled(int arg0, int arg1) {
		View newView = getChildAt(0);
		if (mOnItemChangedListener != null) {
			if (newView != null && newView != mCurrentView) {
				mCurrentView = newView;
				mOnItemChangedListener.onChanged(mCurrentView, getChildPosition(mCurrentView));
			}
		}
	}
}
