package com.ruitu365.ruitu.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ruitu365.ruitu.R;
import com.ruitu365.ruitu.activity.ChooseServiceActivity;
import com.ruitu365.ruitu.view.CalendarView;

public class CarTypeAdapter extends BaseAdapter {
	private Context mContext;
	private List<String> mCityDatas;
	private static final int POSITION = R.id.txtv_rent_inquiry;
	private Map<Integer, Integer> mMapIsExpanded;

	public CarTypeAdapter(Context context, List<String> cityDatas) {
		mContext = context;
		mCityDatas = cityDatas;
		mMapIsExpanded = new HashMap<Integer, Integer>();
	}

	@Override
	public int getCount() {
		// return mCityDatas == null ? 0 : mCityDatas.size();
		return mCityDatas == null ? 0 : 20;
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
			con = View.inflate(mContext, R.layout.car_type_item, null);
			vh = new ViewHolder();
			vh.mCalendarView = (CalendarView) con.findViewById(R.id.calendar_view);
			vh.mBtnRentalCar = (Button) con.findViewById(R.id.btn_rental_car);
			vh.mTv = (TextView) con.findViewById(R.id.txtv_rent_inquiry);
			con.setTag(vh);
		} else {
			vh = (ViewHolder) con.getTag();
		}
		vh.mTv.setTag(vh.mCalendarView);
		vh.mTv.setTag(POSITION, position);
		vh.mTv.setOnClickListener(mRentInquiryClickListener);
		if (null != mMapIsExpanded.get(position)) {
			vh.mCalendarView.setVisibility(View.VISIBLE);
		} else {
			vh.mCalendarView.setVisibility(View.GONE);
		}
		vh.mBtnRentalCar.setOnClickListener(mRentalCarClickListener);
		return con;
	}

	private OnClickListener mRentInquiryClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			CalendarView cv = (CalendarView) v.getTag();
			int position = (Integer) v.getTag(POSITION);
			Log.i("bm", "position::" + position);
			if (mMapIsExpanded.get(position) != null) {
				cv.setVisibility(View.GONE);
				mMapIsExpanded.remove(position);
			} else {
				cv.setVisibility(View.VISIBLE);
				mMapIsExpanded.put(position, position);
			}
			notifyDataSetChanged();
		}
	};
	private OnClickListener mRentalCarClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(mContext, ChooseServiceActivity.class);
			mContext.startActivity(intent);
		}
	};

	class ViewHolder {
		TextView mTv;
		Button mBtnRentalCar;
		CalendarView mCalendarView;
	}
}
