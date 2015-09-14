package com.ruitu365.ruitu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore.Images.ImageColumns;

import com.deshang365.util.Installation;
import com.deshang365.util.MD5Util;
import com.ruitu365.ruitu.baselib.RuiTuApp;
import com.ruitu365.ruitu.model.Constants;

public class MeetingUtils {
	/** 获取当前时间 */
	public static String getCurTime(String timeFormat) {
		SimpleDateFormat format = new SimpleDateFormat(timeFormat);
		String curTime = format.format(new Date());
		return curTime;
	}

	public static long timeCastToMillionSecond(String time, String timeFormat) {
		SimpleDateFormat format = new SimpleDateFormat(timeFormat);
		try {
			return format.parse(time).getTime();
		} catch (ParseException e) {
			return 0;
		}
	}

	private static Collator collator = Collator.getInstance();

	/** 解析uri获取图片绝对路径 */
	public static String getRealFilePath(Context context, Uri uri) {
		if (null == uri)
			return null;
		final String scheme = uri.getScheme();
		String data = null;
		if (scheme == null) {
			data = uri.getPath();
		} else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
			data = uri.getPath();
		} else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
			Cursor cursor = context.getContentResolver().query(uri, new String[] { ImageColumns.DATA }, null, null, null);
			if (null != cursor) {
				if (cursor.moveToFirst()) {
					int index = cursor.getColumnIndex(ImageColumns.DATA);
					if (index > -1) {
						data = cursor.getString(index);
					}
				}
				cursor.close();
			}
		}

		return data;
	}

	/** 保存Bitmap文件 */
	public static String saveBitmap(String picPath, Bitmap bm) {
		File f = new File(picPath);
		try {
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			return picPath;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}

	}

	public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}
		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;
		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}
		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	/** 图片二次采样 */
	public static Bitmap scalePic(String filePath) {
		Bitmap bitmap = null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, opts);

		opts.inSampleSize = computeSampleSize(opts, -1, 128 * 128);
		opts.inJustDecodeBounds = false;

		try {
			bitmap = BitmapFactory.decodeFile(filePath, opts);
		} catch (Exception e) {
		}
		return bitmap;
	}

	/** 计算文件大小 */
	public static int fileLength(String absolutePath) {

		int len = 0;
		try {
			File file = new File(absolutePath);
			FileInputStream fis = new FileInputStream(file);
			len = fis.available();
		} catch (IOException e) {
			return -1;
		}
		return len;
	}

	/** 计算图片的压缩比例 */
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		int height = options.outHeight;
		int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			int heightRatio = Math.round((float) height / (float) reqHeight);
			int widthRatio = Math.round((float) width / (float) reqWidth);
			// 选择长宽高较小的比例，成为压缩比例
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	/**
	 * 生成字符串的md5校验值
	 * */
	public static String getMD5HXID() {
		// 产生0和100000之间的整数
		long round = Math.round(Math.random() * 100000);
		// 系统当前时间
		long currentTimeMillis = System.currentTimeMillis();
		String hxid = "" + currentTimeMillis + round;
		return MD5Util.getMD5String(hxid);
	}

	/** 获取Android唯一标识码 */
	public static String getAndroidID(Context context) {
		return Installation.id(context);
	}

	public static void saveParams(int params) {
		Editor edit = RuiTuApp.mParamsSharePrefreces.edit();
		edit.putInt(Constants.KEY_SIGN_MODE, params);
		edit.commit();
	}

	public static int getParams() {
		return RuiTuApp.mParamsSharePrefreces.getInt(Constants.KEY_SIGN_MODE, 0);
	}
}
