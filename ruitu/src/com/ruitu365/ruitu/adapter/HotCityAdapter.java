package com.ruitu365.ruitu.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ruitu365.ruitu.R;

public class HotCityAdapter extends BaseAdapter {
	private Context mContext;
	private List<String> mCityDatas;

	public HotCityAdapter(Context context, List<String> cityDatas) {
		mContext = context;
		mCityDatas = cityDatas;
	}

	@Override
	public int getCount() {
		return mCityDatas == null ? 0 : mCityDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mCityDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View con, ViewGroup parent) {
		ViewHolder vh;
		if (con == null) {
			con = View.inflate(mContext, R.layout.hot_city_item, null);
			vh = new ViewHolder();
			vh.mTv = (TextView) con.findViewById(R.id.txtv_item);
			con.setTag(vh);
		} else {
			vh = (ViewHolder) con.getTag();
		}
		vh.mTv.setText(mCityDatas.get(position));
		return con;
	}

	class ViewHolder {
		TextView mTv;
	}
}
