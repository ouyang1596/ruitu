package com.example.retrofitdemo.view;

import com.example.retrofitdemo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Toast;

public class ScaleView extends View implements OnGlobalLayoutListener {
	private int STATUS_MOVE = 1;
	private int state = -1;
	private int mWidth = 100, mHeigth = 100;
	private Matrix mMatrix = new Matrix();
	private Paint mPaint;
	private int cx, cy;
	private float lastPointDis, newPointDis;
	private float totalTranslateX, totalTranslateY;
	private float translateX, translateY;
	private float lastPointX = -1, lastPointY = -1, newPointX, newPointY;
	private float initScale = 1;
	private float scale = 1;
	private float oldScale = 1;
	private Bitmap mBitmap;
	private Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.icon_login).copy(Config.ARGB_8888, true);
	private boolean flag = false;
	float centerX;
	float centerY;

	public ScaleView(Context context) {
		super(context);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		ViewTreeObserver observer = this.getViewTreeObserver();
		observer.addOnGlobalLayoutListener(this);
		mBitmap = getBitmap();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(mBitmap, mMatrix, mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_POINTER_DOWN:
			if (event.getPointerCount() == 2) {
				lastPointDis = distanceBetweenTwoPoint(event);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (event.getPointerCount() == 1) {
				newPointX = event.getX();
				newPointY = event.getY();
				if (lastPointX == -1 && lastPointY == -1) {
					lastPointX = newPointX;
					lastPointY = newPointY;
					Log.e("bm", "lastPointX=" + lastPointX + "lastPointY=" + lastPointY);
				}
				// Log.e("bm", "newPointX=" + newPointX + "newPointY=" +
				// newPointY);
				translateX = newPointX - lastPointX;
				translateY = newPointY - lastPointY;
				state = STATUS_MOVE;
				totalTranslateX += translateX;
				totalTranslateY += translateY;
				// Log.e("bm", "totalTranslateX==" + totalTranslateX +
				// "translateX=" + translateX);
				if (totalTranslateX <= 0) {
					totalTranslateX = 0;
				}
				if (screenWidth - totalTranslateX <= mBitmap.getWidth() * oldScale) {
					totalTranslateX = screenWidth - mBitmap.getWidth() * oldScale;
				}
				if (totalTranslateY <= 0) {
					totalTranslateY = 0;
				}
				if (screenHeight - totalTranslateY <= mBitmap.getHeight() * oldScale) {
					totalTranslateY = screenHeight - mBitmap.getHeight() * oldScale;
				}
				// Log.e("bm", "translateX=" + translateX + "translateY=" +
				// translateY);
				mMatrix.reset();
				mMatrix.postScale(oldScale, oldScale);
				mMatrix.postTranslate(totalTranslateX, totalTranslateY);
				invalidate();
				lastPointX = newPointX;
				lastPointY = newPointY;
			} else if (event.getPointerCount() == 2) {

				if (!flag) {
					centerX = totalTranslateX + mBitmap.getWidth() * oldScale / 2;
					centerY = totalTranslateY + mBitmap.getHeight() * oldScale / 2;
					Log.e("bm", "flas==" + flag);
					flag = true;
				}
				newPointDis = distanceBetweenTwoPoint(event);
				scale = newPointDis / lastPointDis;
				Log.e("bm", "centerX" + centerX + "centerY" + centerY);
				mMatrix.postScale(scale, scale, centerX, centerY);
				invalidate();
				lastPointDis = newPointDis;
				// 原先缩放高度
				float bitmapLastWidth = mBitmap.getWidth() * oldScale;
				float bitmapLastHeight = mBitmap.getHeight() * oldScale;
				// 新的缩放高度
				float bitmapNewWidth = mBitmap.getWidth() * oldScale * scale;
				float bitmapNewHeight = mBitmap.getHeight() * oldScale * scale;
				totalTranslateX += (bitmapLastHeight - bitmapNewWidth) / 2;
				totalTranslateY += (bitmapLastHeight - bitmapNewHeight) / 2;
				oldScale *= scale;
			}
		case MotionEvent.ACTION_POINTER_UP:
			if (event.getPointerCount() == 2) {
				// 手指离开屏幕时将临时值还原
				lastPointX = -1;
				lastPointY = -1;
				// flag = false;
			}
			break;
		case MotionEvent.ACTION_UP:
			if (state != STATUS_MOVE && event.getX() < totalTranslateX + mBitmap.getWidth() * oldScale && event.getX() > totalTranslateX
					&& event.getY() < totalTranslateY + mBitmap.getHeight() * oldScale && event.getY() > totalTranslateY) {
				Toast.makeText(getContext(), "点中", Toast.LENGTH_SHORT).show();
			}
			// 手指离开屏幕时将临时值还原
			lastPointX = -1;
			lastPointY = -1;
			state = -1;
			flag = false;
			break;
		}

		return true;
	}

	private float distanceBetweenTwoPoint(MotionEvent event) {
		float lengthX = Math.abs(event.getX(0) - event.getX(1));
		float lengthY = Math.abs(event.getY(0) - event.getY(1));
		float distance = (float) Math.sqrt(lengthX * lengthX + lengthY * lengthY);
		return distance;
	}

	/**
	 * 绘制圆形
	 * 
	 * @return
	 * */
	public Bitmap getBitmap() {
		Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeigth, Bitmap.Config.ARGB_8888);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.YELLOW);
		Canvas canvas = new Canvas(bitmap);
		// int i = canvas.saveLayer(0, 0, mWidth, mHeigth, paint,
		// Canvas.ALL_SAVE_FLAG);
		canvas.drawBitmap(b, null, new RectF(0, 0, mWidth, mHeigth), null);
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		canvas.drawBitmap(getMaskBitmap(), 0, 0, paint);
		// canvas.restoreToCount(i);
		return bitmap;
	}

	/**
	 * 绘制圆形遮罩
	 * 
	 * @return
	 * */
	public Bitmap getMaskBitmap() {
		Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeigth, Bitmap.Config.ARGB_8888);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.YELLOW);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2, paint);
		return bitmap;
	}

	private int screenWidth, screenHeight;

	@Override
	public void onGlobalLayout() {
		this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
		screenWidth = getWidth();
		screenHeight = getHeight();
		cx = screenWidth / 2;
		cy = screenHeight / 2;
		// mMatrix.postTranslate(cx - mWidth / 2, cy - mHeigth / 2);
		// mMatrix.postScale(3, 3, cx, cy);
	}
}
