package com.ruitu365.ruitu.view;

import android.app.Activity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruitu365.ruitu.R;

/**
 * 标题
 * */
public class TopAlert {
	private Activity mActivity;
	private LinearLayout mLlBack;
	private TextView mTvTopical;
	private FrameLayout mRelMore;
	private ImageView mImgvMore;
	private TextView mTvMore;

	public TopAlert(Activity context) {
		mActivity = context;
		initView();
	}

	private void initView() {
		mTvTopical = (TextView) mActivity.findViewById(R.id.txtv_top_alert_title);
		mLlBack = (LinearLayout) mActivity.findViewById(R.id.ll_top_alert_back);
		mRelMore = (FrameLayout) mActivity.findViewById(R.id.rel_top_alert_more);
		mImgvMore = (ImageView) mActivity.findViewById(R.id.imgv_top_alert_more);
		mTvMore = (TextView) mActivity.findViewById(R.id.txtv_top_alert_more);
	}

	public LinearLayout getmLlBack() {
		return mLlBack;
	}

	public TextView getmTvTopical() {
		return mTvTopical;
	}

	public FrameLayout getmRelMore() {
		return mRelMore;
	}

	public ImageView getmImgvMore() {
		return mImgvMore;
	}

	public TextView getmTvMore() {
		return mTvMore;
	}

}
