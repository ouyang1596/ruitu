package com.ruitu365.ruitu.activity;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ruitu365.ruitu.R;
import com.ruitu365.ruitu.network.NetworkReturn;
import com.ruitu365.ruitu.network.NewNetwork;
import com.ruitu365.ruitu.network.OnResponse;
import com.ruitu365.ruitu.view.DialogView;
import com.ruitu365.ruitu.view.TopAlert;

public class UserInfoActivity extends BaseActivity {
	private TopAlert mTopAlert;
	private Button mBtnExit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		initView();
	}

	private void initView() {
		mTopAlert = new TopAlert(this);
		mTopAlert.getmTvTopical().setText(R.string.me);
		mTopAlert.getmTvMore().setText("租车记录");
		mTopAlert.getmTvMore().setVisibility(View.VISIBLE);
		mTopAlert.getmLlBack().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mBtnExit = (Button) findViewById(R.id.btn_exit);
		mBtnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final DialogView dialogView = new DialogView(mContext);
				View view = View.inflate(mContext, R.layout.dialog_exit_set_password, null);
				final EditText pwd = (EditText) view.findViewById(R.id.et_pwd);
				final EditText rePwd = (EditText) view.findViewById(R.id.et_re_pwd);
				Button btnEnsure = (Button) view.findViewById(R.id.btn_ensure);
				btnEnsure.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						String password = pwd.getText().toString();
						String rePassword = rePwd.getText().toString();
						if (password.length() <= 0) {
							Toast.makeText(mContext, R.string.input_password, Toast.LENGTH_SHORT).show();
							return;
						}
						if (password.length() < 6) {
							Toast.makeText(mContext, R.string.password_not_less_than_six, Toast.LENGTH_SHORT).show();
							return;
						}
						if (rePassword.length() <= 0) {
							Toast.makeText(mContext, R.string.input_password, Toast.LENGTH_SHORT).show();
							return;
						}
						if (!password.equals(rePassword)) {
							Toast.makeText(mContext, R.string.password_not_same, Toast.LENGTH_SHORT).show();
							return;
						}
						dialogView.cancel();
						setPassword(password);
					}
				});
				dialogView.setView(new EditText(mContext));
				dialogView.showDialog(view);
			}
		});
	}

	public void setPassword(String pwd) {
		NewNetwork.setPassword(pwd, new OnResponse<NetworkReturn>("") {
			@Override
			public void success(NetworkReturn result, Response response) {
				super.success(result, response);
				if (result.result != 1) {
					Toast.makeText(mContext, result.msg, Toast.LENGTH_SHORT).show();
					return;
				}
				Toast.makeText(mContext, result.msg, Toast.LENGTH_SHORT).show();
				finish();
			}

			@Override
			public void failure(RetrofitError error) {
				super.failure(error);
				Toast.makeText(mContext, R.string.network_request_failure, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

}
