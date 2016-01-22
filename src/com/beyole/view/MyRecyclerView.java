package com.beyole.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.view.View;

/**
 * ��д��RecyclerView
 * 
 * @date 2016/01/22
 * @author Iceberg
 * 
 *         ע����� 1.�����޷�implements
 *         RecyclerView.OnScrollListener�������,��targetSdkVersion���ó�5
 *         .0���¾Ϳ�����,5.0�����޷�implements �������ӿ�
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
	 * ����ʱ�жϵ�ǰ��һ��view�Ƿ�ı䣬�ı�ʱ�Żص�
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
