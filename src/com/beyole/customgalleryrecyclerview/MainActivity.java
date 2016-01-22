package com.beyole.customgalleryrecyclerview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.beyole.adapter.GalleryAdapter;
import com.beyole.adapter.GalleryAdapter.OnItemClickListener;
import com.beyole.view.MyRecyclerView;
import com.beyole.view.MyRecyclerView.OnItemChangedListener;

public class MainActivity extends Activity {

	private MyRecyclerView recyclerView;
	private GalleryAdapter adapter;
	private List<Integer> mDatas;
	private ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initDatas();
		recyclerView = (MyRecyclerView) findViewById(R.id.id_recyclerview);
		mImageView = (ImageView) findViewById(R.id.id_content);
		// 设置布局管理器
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		recyclerView.setLayoutManager(linearLayoutManager);
		// 设置适配器
		adapter = new GalleryAdapter(this, mDatas);
		adapter.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(View view, int position) {
				mImageView.setImageResource(mDatas.get(position));
			}
		});
		recyclerView.setAdapter(adapter);
		recyclerView.setOnItemChangedListener(new OnItemChangedListener() {

			@Override
			public void onChanged(View view, int position) {
				mImageView.setImageResource(mDatas.get(position));
			}
		});
	}

	private void initDatas() {
		mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.l));

	}

}