package com.example.retrofitdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class CustomCircleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_custom_circle);
		setContentView(R.layout.activity_custom_circle);
	}

	private static class SampleView extends View {
		private static final int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
				| Canvas.FULL_COLOR_LAYER_SAVE_FLAG | Canvas.CLIP_TO_LAYER_SAVE_FLAG;

		private Paint mPaint;

		public SampleView(Context context) {
			super(context);
			setFocusable(true);

			mPaint = new Paint();
			mPaint.setAntiAlias(true);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawColor(Color.WHITE);
			canvas.translate(10, 10);
			mPaint.setColor(Color.RED);
			canvas.drawCircle(75, 75, 75, mPaint);
			int save = canvas.save();
			// canvas.saveLayerAlpha(0, 0, 200, 200, 0x88, LAYER_FLAGS);
			int saveLayer = canvas.saveLayer(0.0f, 0.0f, 200.0f, 200.0f, mPaint, LAYER_FLAGS);
			Log.i("bm", "num=" + saveLayer);
			mPaint.setColor(Color.BLUE);
			canvas.drawCircle(125, 125, 75, mPaint);
			canvas.restore();
		}
	}
}
