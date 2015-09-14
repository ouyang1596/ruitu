package com.example.retrofitdemo.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofitdemo.R;

public class MyEditText extends EditText {
	private Drawable d;
	private int width;

	public MyEditText(Context context) {
		super(context);
		init();
	}

	public MyEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private void init() {
		Log.e("bm", "==getWidth==" + getWidth() + "===isShown==" + this.isShown());
		Drawable drawable = getResources().getDrawable(R.drawable.emotionstore_progresscancelbtn);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		setCompoundDrawables(null, null, drawable, null);
		d = getCompoundDrawables()[2];
		if (d != null) {
			width = d.getIntrinsicWidth();
			// d.setVisible(true, true);
		}
		ViewTreeObserver vo = this.getViewTreeObserver();
		vo.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				Log.e("bm", "==getWidth==" + getWidth() + "===isShown==" + MyEditText.this.isShown());
				MyEditText.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			if ((event.getX() < getWidth() - getPaddingRight()) && (event.getX() > getWidth() - width - getPaddingRight())) {
				Toast.makeText(getContext(), "点击成功", Toast.LENGTH_SHORT).show();
			}
			break;
		}
		return super.onTouchEvent(event);
	}
}
