package com.example.retrofitdemo;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AnnonationActivity extends Activity {
	private TextView mTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_annonation);
		mTv = (TextView) findViewById(R.id.tv);
		myFruit("dadadaddddddd", "bbbbb");
		// getAnnonation(AnnonationActivity.class);
	}

	private void myFruit(@FruitColor("hhhhhhhhhhhhh") String data, @Body String param) {
		try {
			Class<?> clazz = AnnonationActivity.class;
			Method dm = clazz.getDeclaredMethod("myFruit", String.class, String.class);
			Annotation[][] p = dm.getParameterAnnotations();
			for (int i = 0; i < p.length; i++) {
				for (int j = 0; j < p[i].length; j++) {
					if (p[i][j] instanceof FruitColor) {
						FruitColor f = (FruitColor) p[i][j];
						mTv.setText("name:" + dm.getName() + "data:" + f.value());
						Toast.makeText(getApplication(), data, Toast.LENGTH_SHORT).show();
					} else if (p[i][j] instanceof Body) {
						Toast.makeText(getApplication(), param, Toast.LENGTH_SHORT).show();
						mTv.setText(param);
					}
				}
			}
		} catch (NoSuchMethodException e) {

		}
	}

	@Target(ElementType.PARAMETER)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface FruitColor {
		String value() default "Hello Annonation";
	}

	@Target(ElementType.PARAMETER)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface Body {
	}

	private void getAnnonation(Class<?> clazz) {
		try {
			Method dm = clazz.getDeclaredMethod("myFruit", String.class, String.class);
			Annotation[][] p = dm.getParameterAnnotations();
			for (int i = 0; i < p.length; i++) {
				for (int j = 0; j < p[i].length; j++) {
					if (p[i][j] instanceof FruitColor) {
						FruitColor f = (FruitColor) p[i][j];
						mTv.setText("name:" + dm.getName() + "data:" + f.value());
					} else if (p[i][j] instanceof Body) {
						mTv.setText("body");
					}
				}
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
