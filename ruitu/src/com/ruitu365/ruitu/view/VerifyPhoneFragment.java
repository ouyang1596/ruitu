package com.ruitu365.ruitu.view;

import org.codehaus.jackson.JsonNode;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.ruitu365.ruitu.activity.UserInfoActivity;
import com.ruitu365.ruitu.model.UserInfo;
import com.ruitu365.ruitu.network.NetworkReturn;
import com.ruitu365.ruitu.network.NewNetwork;
import com.ruitu365.ruitu.network.OnResponse;
import com.ruitu365.ruitu.network.RetrofitUtils;

public class VerifyPhoneFragment extends Fragment {
	private TextView mTvPasswordLogin, mTvTopical;
	private EditText mEtMobile, mEtIdentifyingCode;
	private Button mBtnEnsure;
	private Button mBtnSend;
	private LinearLayout mLlBack;
	private OnVpfClickListener mOnVpfClickListener;
	private String mIdentifyingCode = "";

	public interface OnVpfClickListener {
		void OnVpfClick(View view);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_verify_phone, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {
		mTvTopical = (TextView) getView().findViewById(R.id.txtv_top_alert_title);
		mTvTopical.setText(R.string.verify_phone);
		mLlBack = (LinearLayout) getView().findViewById(R.id.ll_top_alert_back);
		mLlBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mOnVpfClickListener != null) {
					mOnVpfClickListener.OnVpfClick(v);
				}
			}
		});
		mEtMobile = (EditText) getView().findViewById(R.id.et_mobile);
		mEtIdentifyingCode = (EditText) getView().findViewById(R.id.et_identyfying_code);
		mTvPasswordLogin = (TextView) getView().findViewById(R.id.txtv_password_login);
		mBtnSend = (Button) getView().findViewById(R.id.btn_send);
		mBtnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String mobile = mEtMobile.getText().toString();
				if (mobile.length() <= 0) {
					showToastInt(R.string.input_mobile_number);
					return;
				}
				submitIdentifyingCode(mobile);
			}
		});
		mTvPasswordLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mOnVpfClickListener != null) {
					mOnVpfClickListener.OnVpfClick(v);
				}
			}
		});
		mBtnEnsure = (Button) getView().findViewById(R.id.btn_ensure);
		mBtnEnsure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String mobile = mEtMobile.getText().toString();
				String identifyingCode = mEtIdentifyingCode.getText().toString();
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
				vertifyPhoneLogin(mobile);
			}
		});
	}

	public void setOnVpfClickListener(OnVpfClickListener onVpfClickListener) {
		mOnVpfClickListener = onVpfClickListener;
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

	public void vertifyPhoneLogin(String mobile) {
		NewNetwork.vertifyPhoneLogin(mobile, new OnResponse<UserInfo>("") {
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
