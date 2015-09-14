package com.example.retrofitdemo;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.ImageSpan;
import android.text.style.UnderlineSpan;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class OkHttpActivity extends Activity {
	private OkHttpClient client = new OkHttpClient();
	private TextView mTv;
	private EditText mEt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ok_http);
		mTv = (TextView) findViewById(R.id.tv);
		mEt = (EditText) findViewById(R.id.et);
		loadImg();
		underline(0,8);
		new Thread(new Runnable() {

			@Override
			public void run() {
				final String response = request("https://raw.github.com/square/okhttp/master/README.md");
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						mTv.setText(response);
					}
				});
			}
		}).start();
	}

	private void loadImg() {
		Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		// 需要处理的文本，[smile]是需要被替代的文本
		SpannableString spannable = new SpannableString(mEt.getText().toString() + "[smile]");
		// 要让图片替代指定的文字就要用ImageSpan
		ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
		// 开始替换，注意第2和第3个参数表示从哪里开始替换到哪里替换结束（start和end）
		// 最后一个参数类似数学中的集合,[5,12)表示从5到12，包括5但不包括12
		spannable.setSpan(span, mEt.getText().length(), mEt.getText().length() + "[smile]".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		mEt.setText(spannable);
	}

	public void underline(int start, int end) {
		SpannableStringBuilder spannable = new SpannableStringBuilder(mEt.getText().toString());
		UnderlineSpan span = new UnderlineSpan();
		TextPaint paint = new TextPaint();
		paint.setUnderlineText(false);
		paint.setColor(0xffff0000);
		span.updateDrawState(paint);
		spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		mEt.setText(spannable);
	}

	public String request(String url) {
		Request request = new Request.Builder().url(url).build();
		Response response;
		try {
			response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			return e.getLocalizedMessage();
		}
	}
}
