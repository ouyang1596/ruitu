package com.example.retrofitdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.retrofitdemo.R;

public class MyAdapetr extends BaseAdapter {
	private Context mContext;
	private ListView mLv;
	private String data;

	public MyAdapetr(Context context, ListView lv) {
		mContext = context;
		mLv = lv;
		mLv.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				switch (scrollState) {
				case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:// 停止
					data = new String("停止");
					break;
				case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 触摸滑动
					data = new String("触摸滑动");
					break;
				case AbsListView.OnScrollListener.SCROLL_STATE_FLING:// 快速滑动
					data = new String("快速滑动");
					break;

				default:
					break;
				}
				notifyDataSetChanged();
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View con, ViewGroup parent) {
		ViewHolder vh;
		if (con == null) {
			con = View.inflate(mContext, R.layout.item, null);
			vh = new ViewHolder();
			vh.tv = (TextView) con.findViewById(R.id.tv);
			con.setTag(vh);
		} else {
			vh = (ViewHolder) con.getTag();
		}
		vh.tv.setText("oooooooo==" + data);
		return con;
	}

	class ViewHolder {
		TextView tv;
	}
}
