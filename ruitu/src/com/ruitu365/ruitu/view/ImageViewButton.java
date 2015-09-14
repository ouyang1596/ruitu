package com.ruitu365.ruitu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruitu365.ruitu.R;

public class ImageViewButton extends RelativeLayout {
	public CircularImageView mImgv;
	private TextView mTv;
	private int imageResource;
	private String txt;

	/**
	 * 代码中创建对象的时候调用
	 * */
	public ImageViewButton(Context context) {
		super(context);
	}

	/**
	 * xml布局中调用
	 * */
	public ImageViewButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.imgv_txtv_combine, this);
		mImgv = (CircularImageView) findViewById(R.id.imgv_signing_group);
		mTv = (TextView) findViewById(R.id.txtv_signing_group);
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ImageViewButton);
		imageResource = ta.getResourceId(R.styleable.ImageViewButton_imageResource, R.drawable.default_head_portrait);
		setImageResource(imageResource);
		txt = ta.getString(R.styleable.ImageViewButton_txt);
		setTextViewText(txt);
		ta.recycle();
	}

	/**
	 * 设置图片资源
	 */
	public void setImageResource(int resId) {
		mImgv.setImageResource(resId);
	}

	/**
	 * 设置图片资源
	 */
	public void setImageBitmap(Bitmap bitmap) {
		mImgv.setImageBitmap(bitmap);
	}

	/**
	 * 设置显示的文字
	 */
	public void setTextViewText(String text) {
		mTv.setText(text);
	}

}
