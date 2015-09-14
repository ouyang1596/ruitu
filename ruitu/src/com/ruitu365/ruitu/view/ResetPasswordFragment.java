package com.ruitu365.ruitu.view;

import org.codehaus.jackson.JsonNode;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ruitu365.ruitu.R;
import com.ruitu365.ruitu.activity.PasswordLoginActivity;
import com.ruitu365.ruitu.network.NetworkReturn;
import com.ruitu365.ruitu.network.NewNetwork;
import com.ruitu365.ruitu.network.OnResponse;

public class ResetPasswordFragment extends Fragment {
	private LinearLayout mLlBack;
	private TextView mTvTopical;
	private TimeCount mTimeCount;
	private EditText mEtMobile, mEtIdentifyCode, mEtNewPwd;
	private Button mBtnEnsure, mBtnResend;
	private OnRpfClickListener mOnRpfClickListener;
	private String mIdentifyingCode = "";

	public interface OnRpfClickListener {
		void OnRpfClick(View view);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_reset_password, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {
		mTvTopical = (TextView) getView().findViewById(R.id.txtv_top_alert_title);
		mTvTopical.setText(R.string.reset_password);
		mLlBack = (LinearLayout) getView().findViewById(R.id.ll_top_alert_back);
		mLlBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mOnRpfClickListener != null) {
					mOnRpfClickListener.OnRpfClick(v);
				}
			}
		});
		mEtMobile = (EditText) getView().findViewById(R.id.et_mobile);
		mEtIdentifyCode = (EditText) getView().findViewById(R.id.et_identify_code);
		mEtNewPwd = (EditText) getView().findViewById(R.id.et_new_pwd);
		mBtnResend = (Button) getView().findViewById(R.id.btn_resend);
		mBtnResend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String mobile = mEtMobile.getText().toString();
				if (mobile.length() <= 0) {
					showToastInt(R.string.input_mobile_number);
					return;
				}
				submitIdentifyingCode(mobile);
				timeCountDown();
			}
		});
		mBtnEnsure = (Button) getView().findViewById(R.id.btn_next);
		mBtnEnsure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String mobile = mEtMobile.getText().toString();
				String identifyingCode = mEtIdentifyCode.getText().toString();
				String newPwd = mEtNewPwd.getText().toString();
				if (mobile.length() <= 0) {
					showToastInt(R.string.input_mobile_number);
					return;
				}
				if (identifyingCode.length() <= 0) {
					showToastInt(R.string.input_identifying_code);
					return;
				}
				if (!mIdentifyingCode.equals(identifyingCode)) {
					showToastInt(R.string.identifying_code_wrong);
					return;
				}
				if (newPwd.length() <= 0) {
					showToastInt(R.string.input_password);
					return;
				}
				resetPassword(mobile, newPwd);
			}
		});
	}

	public void setOnRpfClickListener(OnRpfClickListener onRpfClickListener) {
		mOnRpfClickListener = onRpfClickListener;
	}

	public void showToastInt(int resId) {
		((PasswordLoginActivity) getActivity()).showToastInt(resId);
	}

	public void showToastStr(String text) {
		((PasswordLoginActivity) getActivity()).showToastStr(text);
	}

	public void submitIdentifyingCode(String mobile) {
		NewNetwork.submitIdentifyingCode(mobile, new OnResponse<NetworkReturn>("") {
			@Override
			public void success(NetworkReturn result, Response response) {
				super.success(result, response);
				if (result.result != 1) {
					Toast.makeText(getActivity(), result.msg, Toast.LENGTH_SHORT).show();
					return;
				}
				JsonNode data = result.data;
				mIdentifyingCode = data.get("auth").getValueAsText();
			}

			@Override
			public void failure(RetrofitError error) {
				super.failure(error);
				showToastInt(R.string.network_request_failure);
			}
		});
	}

	public void resetPassword(String mobile, String new_pwd) {
		NewNetwork.resetPassword(mobile, new_pwd, new OnResponse<NetworkReturn>("") {
			@Override
			public void success(NetworkReturn result, Response response) {
				super.success(result, response);
				if (result.result != 1) {
					showToastStr(result.msg);
					return;
				}
				showToastStr(result.msg);
			}

			@Override
			public void failure(RetrofitError error) {
				super.failure(error);
				showToastInt(R.string.network_request_failure);
			}
		});
	}

	public void timeCountDown() {
		mBtnResend.setBackgroundResource(R.drawable.new_black_three_radius_bg);
		mBtnResend.setEnabled(false);
		if (mTimeCount == null) {
			mTimeCount = new TimeCount(60000, 1000);
		}
		mTimeCount.start();
	}

	/**
	 * 倒计时类
	 * */
	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
		}

		@Override
		public void onFinish() {// 计时完毕时触发
			mBtnResend.setText("重新发送");
			mBtnResend.setBackgroundResource(R.drawable.new_orange_radius_bg);
			mBtnResend.setEnabled(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {// 计时过程显示
			// checking.setClickable(false);
			// checking.setText(millisUntilFinished / 1000 + "秒");
			mBtnResend.setText(millisUntilFinished / 1000 + "秒");
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		if (mTimeCount != null) {
			mTimeCount.cancel();
			mTimeCount = null;
		}
	}
}
