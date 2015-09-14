package com.example.retrofitdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retrofitdemo.RecycleViewActivity.MyAdapter.MyViewHolder;

public class RecycleViewActivity extends Activity {
	private RecyclerView mRv;
	private String[] stringArr = { "ai", "zuo", "wn", "g黄真伊", "he河智苑", "@", "&&*(*", "?? ??? ???", "?", "擬好", "上饶", "厦门", "深圳", "武林",
			"text1", "+*())*&%$^", "11112", "6666", "898和", "阿拉伯", "阿镇", "下午", "责打", "浙江", "浙江", "阿布", "北京", "北城", "成", "城市", "123a",
			"234b", "678c", "得", "额", "方", "搞", "广州", "黄石", "黄冈", "杭州", "上海", "武林" };
	private RecyclerView.LayoutManager mLm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycle_view);
		initView();
	}

	private void initView() {
		mRv = (RecyclerView) findViewById(R.id.rv);
		mLm = new LinearLayoutManager(this);
		((LinearLayoutManager) mLm).setOrientation(LinearLayoutManager.HORIZONTAL);
		mRv.setLayoutManager(mLm);
		MyAdapter adapter = new MyAdapter(stringArr);
		mRv.setAdapter(adapter);
	}

	class MyAdapter extends Adapter<MyViewHolder> {
		String[] data;

		public MyAdapter(String[] data) {
			this.data = data;
		}

		@Override
		public int getItemCount() {
			return data == null ? 0 : data.length;
		}

		@Override
		public void onBindViewHolder(MyViewHolder view, int position) {
			view.mTv.setText(data[position]);
		}

		@Override
		public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
			View view = View.inflate(RecycleViewActivity.this, R.layout.item, null);
			return new MyViewHolder(view);
		}

		public class MyViewHolder extends RecyclerView.ViewHolder {
			public TextView mTv;

			public MyViewHolder(View view) {
				super(view);
				mTv = (TextView) view.findViewById(R.id.tv);
			}

		}
	}
}
