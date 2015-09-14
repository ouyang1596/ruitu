package com.ruitu365.ruitu.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.ruitu365.ruitu.R;

public class PoiInfoAdapter extends BaseAdapter {
	private Context mContext;
	private List<PoiInfo> datas;

	public PoiInfoAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View con, ViewGroup parent) {
		ViewHolder vh;
		if (con == null) {
			con = View.inflate(mContext, R.layout.item_addr_detail, null);
			vh = new ViewHolder();
			vh.mTvCity = (TextView) con.findViewById(R.id.txtv_city);
			vh.mTvName = (TextView) con.findViewById(R.id.txtv_name);
			con.setTag(vh);
		} else {
			vh = (ViewHolder) con.getTag();
		}
		vh.mTvCity.setText(datas.get(position).city);
		vh.mTvName.setText(datas.get(position).name);
		return con;
	}

	public void setData(List<PoiInfo> data) {
		datas = data;
		notifyDataSetChanged();
	}

	public void clearData() {
		if (datas != null) {
			datas.clear();
		}
		notifyDataSetChanged();
	}

	class ViewHolder {
		private TextView mTvCity, mTvName;
	}
}
