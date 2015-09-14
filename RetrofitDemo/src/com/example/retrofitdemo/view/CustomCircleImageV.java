package com.example.retrofitdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import com.example.retrofitdemo.R;

public class CustomCircleImageV extends View {
	private Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_login).copy(Bitmap.Config.ARGB_8888, true);
	private Paint mPaint = new Paint();
	private Xfermode mXfermode = new PorterDuffXfermode(Mode.DST_IN);

	public CustomCircleImageV(Context context) {
		super(context);
		mPaint.setAntiAlias(true);
	}

	public CustomCircleImageV(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint.setAntiAlias(true);
	}

	public CustomCircleImageV(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mPaint.setAntiAlias(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint p = new Paint();
		p.setAntiAlias(true); // 去锯齿
		p.setColor(Color.BLACK);
		p.setStyle(Paint.Style.STROKE);
		Canvas canvas1 = new Canvas(mBitmap); // bitmap就是我们原来的图,比如头像
		p.setXfermode(new PorterDuffXfermode(Mode.DST_IN)); // 因为我们先画了图所以DST_IN
		Bitmap bitmap = getBitmap();// 绘制圆形
		canvas1.drawBitmap(bitmap, 0, 0, p);
		// 在canvas1执行的过程中，mBitmap已经发生了改变
		canvas.drawBitmap(mBitmap, 0, 0, null);
	}

	/**
	 * 绘制形状
	 * 
	 * @return
	 */
	public Bitmap getBitmap() {
		Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLACK);
		canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2, paint);
		return bitmap;
	}
}