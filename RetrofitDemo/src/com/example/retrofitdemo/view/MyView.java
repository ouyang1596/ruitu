package com.example.retrofitdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MyView extends SurfaceView implements Callback, Runnable {
	private Paint mPaint;
	private RectF rectF;
	private float sweepAngle = 0;

	public MyView(Context context) {
		super(context);
		mPaint = new Paint();
		mPaint.setColor(0xffff0000);
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.STROKE);
		rectF = new RectF(100, 100, 500, 500);
		getHolder().addCallback(this);
	}

	@Override
	public void run() {
		while (true) {
			draw();
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		new Thread(this).start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}

	public void draw() {
		if (sweepAngle < 360) {
			sweepAngle++;
			Canvas canvas = getHolder().lockCanvas();
			canvas.drawColor(0xffffffff);
			canvas.drawArc(rectF, 0, sweepAngle, false, mPaint);
			getHolder().unlockCanvasAndPost(canvas);
		} else {
			while (true) {
				if (sweepAngle > 0) {
					draw2();
				} else {
					break;
				}
			}
		}

	}

	public void draw2() {
		Canvas canvas = getHolder().lockCanvas();
		canvas.drawColor(0xffffffff);
		sweepAngle--;
		canvas.drawArc(rectF, 0, sweepAngle, false, mPaint);
		getHolder().unlockCanvasAndPost(canvas);
	}
}
