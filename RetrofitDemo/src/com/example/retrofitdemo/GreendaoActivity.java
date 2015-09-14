package com.example.retrofitdemo;

import java.util.List;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.retrofitdemo.DaoMaster.DevOpenHelper;
import com.example.retrofitdemo.Db_userDao.Properties;

import de.greenrobot.dao.query.QueryBuilder;

public class GreendaoActivity extends Activity {
	private SQLiteDatabase db;
	private DaoMaster daoMaster;
	private DaoSession daoSession;
	private Db_userDao noteDao;
	private TextView mTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_greendao);
		init();
	}

	private void init() {
		mTv = (TextView) findViewById(R.id.txtv_greendao);
		DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "Db_user", null);
		db = helper.getWritableDatabase();
		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
		noteDao = daoSession.getDb_userDao();
		Db_user db_user = new Db_user(null, "heo", "ouyang", "33333333333", "444444444444", "weixin", "1243435", "deviceno");
		Db_user db_user1 = new Db_user(null, "hello", "ou", "33333333333", "444444444444", "weixin", "1243435", "deviceno");
		noteDao.insert(db_user);
		noteDao.insert(db_user1);
	}

	public void showData(View view) {
		QueryBuilder<Db_user> qb = noteDao.queryBuilder();
		qb.where(Properties.Deviceno.eq("deviceno"), Properties.Wechat.eq("weixin"));
		List<Db_user> list = qb.list();
		for (Db_user data : list) {
			Log.i("bm", "username=" + data.getUser_name());
		}
	}
}
