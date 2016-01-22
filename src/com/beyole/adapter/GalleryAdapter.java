package com.beyole.adapter;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyole.customgalleryrecyclerview.R;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

	public interface OnItemClickListener {
		void onItemClick(View view, int position);
	}

	private OnItemClickListener onItemClickListener;

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.onItemClickListener = listener;
	}

	private List<Integer> mDatas;
	private LayoutInflater inflater;

	public GalleryAdapter(Context context, List<Integer> datas) {
		inflater = LayoutInflater.from(context);
		this.mDatas = datas;

	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public ViewHolder(View arg0) {
			super(arg0);
		}

		ImageView imageView;
		TextView textView;
	}

	@Override
	public int getItemCount() {
		return mDatas.size();
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
		viewHolder.imageView.setImageResource(mDatas.get(i));
		if (onItemClickListener != null) {
			viewHolder.itemView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					onItemClickListener.onItemClick(viewHolder.itemView, i);
				}
			});
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View view = inflater.inflate(R.layout.item, viewGroup, false);
		ViewHolder holder = new ViewHolder(view);
		holder.imageView = (ImageView) view.findViewById(R.id.id_index_gallery_item_image);
		return holder;
	}
}
