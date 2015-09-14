package com.ruitu365.ruitu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ruitu365.ruitu.R;

public class AboutAdapter extends BaseAdapter {
	private Context mContext;
	private String[] mAboutStrings = { "功能介绍", "常见问题", "应用评分", "应用二维码" };

	public AboutAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mAboutStrings == null ? 0 : mAboutStrings.length;
	}

	@Override
	public Object getItem(int position) {
		return mAboutStrings[position];
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View con, ViewGroup parent) {
		ViewHolder vh;
		if (con == null) {
			con = View.inflate(mContext, R.layout.about_item, null);
			vh = new ViewHolder();
			vh.mTextView = (TextView) con.findViewById(R.id.txtv_about);
			con.setTag(vh);
		} else {
			vh = (ViewHolder) con.getTag();
		}
		vh.mTextView.setText(mAboutStrings[position]);
		return con;
	}

	class ViewHolder {
		TextView mTextView;
	}
}
