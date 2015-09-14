package com.ruitu365.ruitu.view;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruitu365.ruitu.R;
import com.ruitu365.ruitu.activity.PasswordLoginActivity;
import com.ruitu365.ruitu.activity.UserInfoActivity;
import com.ruitu365.ruitu.model.UserInfo;
import com.ruitu365.ruitu.network.NewNetwork;
import com.ruitu365.ruitu.network.OnResponse;
import com.ruitu365.ruitu.network.RetrofitUtils;

public class PasswordLoginFragment extends Fragment {
	private TextView mTvForgetPassword, mTvTopical;
	private LinearLayout mLlBack;
	private EditText mEtMobile, mEtPwd;
	private Button mBtnLogin;
	private OnPlfOnClickListener mOnPlfOnClickListener;

	public interface OnPlfOnClickListener {
		void OnPlfOnClick(View view);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_password_login, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {
		mTvTopical = (TextView) getView().findViewById(R.id.txtv_top_alert_title);
		mTvTopical.setText(R.string.password_login);
		mLlBack = (LinearLayout) getView().findViewById(R.id.ll_top_alert_back);
		mLlBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mOnPlfOnClickListener != null) {
					mOnPlfOnClickListener.OnPlfOnClick(v);
				}
			}
		});
		mEtMobile = (EditText) getView().findViewById(R.id.et_mobile_number);
		mEtPwd = (EditText) getView().findViewById(R.id.et_password);
		mBtnLogin = (Button) getView().findViewById(R.id.btn_login);
		mBtnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String mobile = mEtMobile.getText().toString();
				String pwd = mEtPwd.getText().toString();
				if (mobile.length() <= 0) {
					showToastInt(R.string.input_mobile_number);
					return;
				}
				if (pwd.length() <= 0) {
					showToastInt(R.string.input_password);
					return;
				}
				if (pwd.length() < 6) {
					showToastInt(R.string.password_not_less_than_six);
					return;
				}
				passwordLogin(mobile, pwd);
			}
		});
		mTvForgetPassword = (TextView) getView().findViewById(R.id.txtv_forget_password);
		mTvForgetPassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mOnPlfOnClickListener != null) {
					mOnPlfOnClickListener.OnPlfOnClick(v);
				}
			}
		});
	}

	public void setOnPlfClickListener(OnPlfOnClickListener onPlfOnClickListener) {
		mOnPlfOnClickListener = onPlfOnClickListener;
	}

	public void passwordLogin(String mobile, String password) {
		NewNetwork.passwordLogin(mobile, password, new OnResponse<UserInfo>("") {
			@Override
			public void success(UserInfo result, Response response) {
				super.success(result, response);
				if (result.result != 1) {
					showToastStr(result.msg);
					return;
				}
				RetrofitUtils.setCookies(response);
				startActivity(new Intent(getActivity(), UserInfoActivity.class));
				getActivity().finish();
			}

			@Override
			public void failure(RetrofitError error) {
				super.failure(error);
				showToastInt(R.string.network_request_failure);
			}
		});
	}

	public void showToastInt(int resId) {
		((PasswordLoginActivity) getActivity()).showToastInt(resId);
	}

	public void showToastStr(String text) {
		((PasswordLoginActivity) getActivity()).showToastStr(text);
	}
}
