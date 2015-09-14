package com.example.retrofitdemo;

import java.io.File;

import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.deshang365.util.FileUtils;

public class RetrofitActivity extends Activity {
	private Button mBtnSend;
	String path = FileUtils.getExternalSdCardPath() + "openCity.png";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retrofit);
		mBtnSend = (Button) findViewById(R.id.btn_send);
		mBtnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				File file = new File(path);
			}
		});
	}

	public interface FileWebService {

		@Multipart
		@POST("/files")
		void upload(@Part("fileContent") TypedFile file);

	}
}
