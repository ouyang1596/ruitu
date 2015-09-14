package com.example.retrofitdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
	private Paint mPaint;
	private int sweepAngle = 0;
	private float left = 60, bottom = 60;
	private RectF rectFCircle = new RectF(60, 60, 160, 160);
	private RectF rectFRec = new RectF(0, 60, left, bottom);
	Path path = new Path();
	private int length = 100;

	public CustomView(Context context) {
		super(context);
		initPaint();
		path.moveTo(100, 100);
		new MyThread().start();
	}

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPaint();
	}

	@Override
	protected void onDraw(Canvas canvas) {

		// path.lineTo(100,200);
		// path.lineTo(200,200);
		// path.lineTo(200,300);
		// canvas.drawArc(rectFCircle, 0, sweepAngle, false, mPaint);
		// canvas.drawRect(rectFRec, mPaint);
		canvas.drawPath(path, mPaint);
	}

	private void initPaint() {
		mPaint = new Paint();
		mPaint.setColor(0xff000000);
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.STROKE);
	}

	private class MyThread extends Thread {
		@Override
		public void run() {
			super.run();
			while (true) {
				// sweepAngle++;
				// postInvalidate();
				// if (sweepAngle >= 360) {
				// sweepAngle = 0;
				// }
				// try {
				// Thread.sleep(50);
				// } catch (InterruptedException e) {
				// }
				// left++;
				// bottom++;
				// rectFRec.set(60, 60, left, bottom);
				// postInvalidate();
				// if (left >= 160) {
				// left = 60;
				// bottom = 60;
				// }
				length++;
				path.lineTo(100, length);
				postInvalidate();
				if (length >= 300) {
					while (true) {
						length--;
						path.reset();
						path.moveTo(100, 100);
						path.lineTo(100, length);
						postInvalidate();
						if (length <= 100) {
							break;
						}
						 try {
						 Thread.sleep(30);
						 } catch (InterruptedException e) {
						 }
					}
				}
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
				}
			}
		}
	}
}
