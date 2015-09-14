package com.ruitu365.ruitu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruitu365.ruitu.R;
import com.ruitu365.ruitu.view.PasswordLoginFragment;
import com.ruitu365.ruitu.view.PasswordLoginFragment.OnPlfOnClickListener;
import com.ruitu365.ruitu.view.ResetPasswordFragment;
import com.ruitu365.ruitu.view.ResetPasswordFragment.OnRpfClickListener;
import com.ruitu365.ruitu.view.VerifyPhoneFragment;
import com.ruitu365.ruitu.view.VerifyPhoneFragment.OnVpfClickListener;

public class PasswordLoginActivity extends BaseActivity implements OnPlfOnClickListener, OnRpfClickListener, OnVpfClickListener {
	private PasswordLoginFragment mPlf;
	private ResetPasswordFragment mRpf;
	private VerifyPhoneFragment mVpf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);
		mVpf = new VerifyPhoneFragment();
		mVpf.setOnVpfClickListener(this);
		getSupportFragmentManager().beginTransaction().add(R.id.fl_contain, mVpf, "vpf").commit();
	}

	@Override
	public void OnPlfOnClick(View view) {
		if (view instanceof LinearLayout) {
			getSupportFragmentManager().popBackStack();
		} else if (view instanceof TextView) {
			mRpf = new ResetPasswordFragment();
			mRpf.setOnRpfClickListener(this);
			getSupportFragmentManager().beginTransaction()
					.setCustomAnimations(android.R.anim.slide_in_left, 0, 0, android.R.anim.slide_out_right).hide(mPlf)
					.add(R.id.fl_contain, mRpf, "rpf").addToBackStack(null).commit();
		}
	}

	@Override
	public void OnRpfClick(View view) {
		if (view instanceof LinearLayout) {
			getSupportFragmentManager().popBackStack();
		} else if (view instanceof Button) {
			startActivity(new Intent(this, DefaultActivity.class));
		}
	}

	@Override
	public void OnVpfClick(View view) {
		if (view instanceof LinearLayout) {
			finish();
		} else if (view instanceof TextView) {
			mPlf = new PasswordLoginFragment();
			mPlf.setOnPlfClickListener(this);
			getSupportFragmentManager().beginTransaction()
					.setCustomAnimations(android.R.anim.slide_in_left, 0, 0, android.R.anim.slide_out_right).hide(mVpf)
					.add(R.id.fl_contain, mPlf, "plf").addToBackStack(null).commit();
		}
	}
}
