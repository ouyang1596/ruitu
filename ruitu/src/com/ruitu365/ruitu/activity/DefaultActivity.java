package com.ruitu365.ruitu.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.deshang365.util.FileUtils;
import com.ruitu365.ruitu.R;
import com.ruitu365.ruitu.baselib.RuiTuApp;
import com.ruitu365.ruitu.util.LocationUtil;
import com.ruitu365.ruitu.util.LocationUtil.OnLocationListener;
import com.ruitu365.ruitu.view.DialogView;
import com.ruitu365.ruitu.view.TopAlert;

public class DefaultActivity extends BaseActivity {
	private TopAlert mTopAlert;
	private Button mBtnUseCar;
	private LocationUtil mLocationUtil;
	private LinearLayout mLlCarService;
	private ViewPager mViewPager; // android-support-v4中的滑动组件
	private List<ImageView> mImageViews; // 滑动的图片集合
	private int[] mImageResId; // 图片ID
	private List<View> mDots; // 图片标题正文的那些点

	private int mCurrentItem = 0; // 当前图片的索引号
	private ScheduledExecutorService mScheduledExecutorService;// 定时周期执行指定的任务

	// 切换当前显示的图片
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mViewPager.setCurrentItem(mCurrentItem);// 切换当前显示的图片
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_default);
		initView();
		initViewPager();
	}

	private void isNetworkConnected() {
		if (!isNetworkConnected(this) && RuiTuApp.netTips) {
			final DialogView dialogView = new DialogView(mContext);
			View networkTipsView = View.inflate(mContext, R.layout.dialog_network, null);
			dialogView.showDialog(networkTipsView);
			Button btnEnsure = (Button) networkTipsView.findViewById(R.id.btn_ensure);
			Button btnCancel = (Button) networkTipsView.findViewById(R.id.btn_cancel);
			btnEnsure.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					dialogView.cancel();
					openWifiSetting();
				}
			});
			btnCancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					RuiTuApp.netTips = false;
					dialogView.cancel();
				}
			});
		} else {
			initLocation();
		}
	}

	private void initView() {
		mTopAlert = new TopAlert(this);
		mTopAlert.getmLlBack().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, PasswordLoginActivity.class);
				startActivity(intent);
			}
		});
		mTopAlert.getmRelMore().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, CityServedActivity.class);
				startActivity(intent);
			}
		});
		mTopAlert.getmTvMore().setText("深圳");
		mTopAlert.getmTvMore().setVisibility(View.VISIBLE);
		mTopAlert.getmTvTopical().setText(R.string.rt365);
		mBtnUseCar = (Button) findViewById(R.id.btn_use_car);
		mBtnUseCar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, RentalCarActivity.class);
				startActivity(intent);
			}
		});
		mLlCarService = (LinearLayout) findViewById(R.id.ll_car_service);
		mLlCarService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(mContext, CarServiceActivity.class));
			}
		});
	}

	private void initLocation() {
		mLocationUtil = new LocationUtil(getApplicationContext());
		mLocationUtil.initLocation();
		mLocationUtil.start();
		mLocationUtil.setOnLocationListener(new OnLocationListener() {

			@Override
			public void onReceiveLocation(BDLocation location) {
				switch (location.getLocType()) {
				case BDLocation.TypeGpsLocation:
					setTopAlertText(location);
					break;
				case BDLocation.TypeNetWorkLocation:
					setTopAlertText(location);
					break;
				case BDLocation.TypeOffLineLocation:
					setTopAlertText(location);
					break;
				case BDLocation.TypeCriteriaException:
					Toast.makeText(mContext, "无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机", Toast.LENGTH_SHORT).show();
					break;
				case BDLocation.TypeNetWorkException:
					Toast.makeText(mContext, "网络不通导致定位失败，请检查网络是否通畅", Toast.LENGTH_SHORT).show();
					break;
				case BDLocation.TypeServerError:
					Toast.makeText(mContext, "服务端网络定位失败", Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
			}

		});
	}

	private void setTopAlertText(BDLocation location) {
		mTopAlert.getmTvMore().setText(location.getCity());
		mTopAlert.getmTvMore().setVisibility(View.VISIBLE);
	}

	private void initViewPager() {
		mImageResId = new int[] { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher };
		mImageViews = new ArrayList<ImageView>();
		// 初始化图片资源
		for (int i = 0; i < mImageResId.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(mImageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			mImageViews.add(imageView);
		}

		mDots = new ArrayList<View>();
		mDots.add(findViewById(R.id.v_dot0));
		mDots.add(findViewById(R.id.v_dot1));
		mDots.add(findViewById(R.id.v_dot2));

		mViewPager = (ViewPager) findViewById(R.id.vp_action);
		mViewPager.setAdapter(new ImagePagerAdapter());// 设置填充ViewPager页面的适配器
		// 设置一个监听器，当ViewPager中的页面改变时调用
		mViewPager.setOnPageChangeListener(new CustomPageChangeListener());

	}

	/**
	 * 换行切换任务
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (mViewPager) {
				System.out.println("currentItem: " + mCurrentItem);
				mCurrentItem = (mCurrentItem + 1) % mImageViews.size();
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}

	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 */
	private class CustomPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		public void onPageSelected(int position) {
			mCurrentItem = position;
			mDots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			mDots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	/**
	 * 填充ViewPager页面的适配器
	 */
	private class ImagePagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mImageResId.length;
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mImageViews.get(arg1));
			return mImageViews.get(arg1);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

		@Override
		public void finishUpdate(View arg0) {

		}
	}

	@Override
	protected void onStart() {
		mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		mScheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	protected void onStop() {
		// 当Activity不可见的时候停止切换
		mScheduledExecutorService.shutdown();
		super.onStop();
	}

	/**
	 * 判断是否有网络连接
	 * */
	public boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 打开wifi设置界面
	 * */
	private void openWifiSetting() {
		// 在Android版本10以下，调用的是：ACTION_WIRELESS_SETTINGS，版本在10以上的调用：ACTION_SETTINGS。
		// 代码如下：
		if (android.os.Build.VERSION.SDK_INT > 10) {
			// 3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
			startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
		} else {
			startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		isNetworkConnected();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
