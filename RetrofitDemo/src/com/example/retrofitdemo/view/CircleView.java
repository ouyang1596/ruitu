package com.example.retrofitdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.retrofitdemo.R;

public class CircleView extends View {
	private Paint mPaint;
	private int cx, cy;
	private Drawable mDrawable;
	private Drawable mDrawable1;
	private Bitmap mBitmap;
	private int width = 100, height = 100;
	private Matrix matrix = new Matrix();

	public CircleView(Context context) {
		super(context);
		init();
		matrix.postRotate(270, 100, 100);
		matrix.postTranslate(100, 300);
		matrix.postScale(2, 2);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		mDrawable.setBounds(width, height - 150, width + 100, height - 50);
		int i = canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), null, Canvas.ALL_SAVE_FLAG);
		// canvas.drawBitmap(getBitmapRecF(), width, width - 150, mPaint);
		mDrawable.draw(canvas);
		mPaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		canvas.drawBitmap(getBitmap(), width, height - 150, mPaint);
		mPaint.setXfermode(null);
		canvas.restoreToCount(i);
		// canvas.drawBitmap(mBitmap, matrix, mPaint);
		canvas.drawBitmap(mBitmap, matrix, mPaint);
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setColor(0xffff0000);
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.STROKE);
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_login).copy(Config.ARGB_8888, true);
		mBitmap = Bitmap.createScaledBitmap(mBitmap, 200, 200, true);
		mDrawable = getResources().getDrawable(R.drawable.icon_login);
	}

	private int pointCount;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		pointCount = event.getPointerCount();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (pointCount == 1) {
				width = (int) event.getX();
				height = (int) event.getY();
				Log.e("bm", "width=" + width + "heigth=" + height);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (pointCount == 1) {
				width = (int) event.getX();
				height = (int) event.getY();
				Log.e("bm", "width=" + width + "heigth=" + height + "pointCount=" + pointCount);
			} else {
			}
			break;

		default:
			break;
		}
		invalidate();
		return true;
	}

	private int mWidth = 100, mHeigth = 100;

	/**
	 * 绘制圆形
	 * 
	 * @return
	 * */
	public Bitmap getBitmap() {
		Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeigth, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.YELLOW);
		canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2, paint);
		return bitmap;
	}

	/**
	 * 绘制正方形
	 * 
	 * @return
	 * */
	public Bitmap getBitmapRecF() {
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLUE);
		canvas.drawRect(new RectF(0, 0, width, height), paint);
		return bitmap;
	}
}
