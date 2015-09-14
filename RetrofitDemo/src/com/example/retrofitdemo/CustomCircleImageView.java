package com.example.retrofitdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CustomCircleImageView extends ImageView {
	// private Bitmap mBitmap = BitmapFactory.decodeResource(getResources(),
	// R.drawable.icon_login).copy(Bitmap.Config.ARGB_8888, true);
	private Paint mPaint;
	private static final Xfermode MASK_XFERMODE;
	private Bitmap mMask;

	static {
		PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
		MASK_XFERMODE = new PorterDuffXfermode(localMode);
	}

	// private Xfermode mXfermode = new PorterDuffXfermode(Mode.DST_IN);
	public CustomCircleImageView(Context context) {
		super(context);
	}

	public CustomCircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomCircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Drawable localDrawable = getDrawable();
		if (localDrawable == null)
			return;
		try {
			if (this.mPaint == null) {
				Paint localPaint1 = new Paint();
				this.mPaint = localPaint1;
				this.mPaint.setFilterBitmap(false);
				Paint localPaint2 = this.mPaint;
				Xfermode localXfermode1 = MASK_XFERMODE;
				@SuppressWarnings("unused")
				Xfermode localXfermode2 = localPaint2.setXfermode(localXfermode1);
			}
			float f1 = getWidth();
			float f2 = getHeight();
			int i = canvas.saveLayer(0.0F, 0.0F, f1, f2, null, 31);
			int j = getWidth();
			int k = getHeight();
			localDrawable.setBounds(0, 0, j, k);
			localDrawable.draw(canvas);
			if ((this.mMask == null) || (this.mMask.isRecycled())) {
				Bitmap localBitmap1 = createMask();
				this.mMask = localBitmap1;
			}
			Bitmap localBitmap2 = this.mMask;
			Paint localPaint3 = this.mPaint;
			canvas.drawBitmap(localBitmap2, 0.0F, 0.0F, localPaint3);
			canvas.restoreToCount(i);
			// canvas.restore();
			return;
		} catch (Exception localException) {
			StringBuilder localStringBuilder = new StringBuilder().append("Attempting to draw with recycled bitmap. View ID = ");
			System.out.println("localStringBuilder==" + localStringBuilder);
		}
	}

	// private void drawCircleImageView(Canvas canvas) {
	// Paint p = new Paint();
	// p.setAntiAlias(true); // 去锯齿
	// p.setColor(Color.BLACK);
	// p.setStyle(Paint.Style.STROKE);
	// Canvas canvas1 = new Canvas(mBitmap); // bitmap就是我们原来的图,比如头像
	// p.setXfermode(new PorterDuffXfermode(Mode.DST_IN)); // 因为我们先画了图所以DST_IN
	// Bitmap bitmap = getBitmap();// 绘制圆形
	// canvas1.drawBitmap(bitmap, 0, 0, p);
	// // 在canvas1执行的过程中，mBitmap已经发生了改变
	// canvas.drawBitmap(mBitmap, 0, 0, null);
	// }

	public Bitmap createMask() {
		int i = getWidth();
		int j = getHeight();
		Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
		// 位图位数越高代表其可以存储的颜色信息越多，图像也就越逼真，占用内存更多。
		Bitmap localBitmap = Bitmap.createBitmap(i, j, localConfig);
		Canvas localCanvas = new Canvas(localBitmap);
		Paint localPaint = new Paint(1);
		localPaint.setColor(-16777216);
		float f1 = getWidth();
		float f2 = getHeight();
		RectF localRectF = new RectF(0.0F, 0.0F, f1, f2);
		localCanvas.drawOval(localRectF, localPaint);
		return localBitmap;
	}

	/**
	 * 绘制形状
	 * 
	 * @return
	 */
	// public Bitmap getBitmap() {
	// Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(),
	// Bitmap.Config.ARGB_8888);
	// Canvas canvas = new Canvas(bitmap);
	// Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	// paint.setColor(Color.WHITE);
	// canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2, paint);
	// return bitmap;
	// }
}
