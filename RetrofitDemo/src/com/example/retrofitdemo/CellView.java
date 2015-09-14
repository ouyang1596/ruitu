package com.example.retrofitdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class CellView extends View {

	public CellView(Context context) {
		super(context);

	}

	public CellView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		int width = getWidth();
		int height = getHeight();
		int cell_width = width / 7;
		int cell_height = height / 7;

	}
}
