/*
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */
 
package o1310.rx1310.app.projer.activity;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;

import android.support.v7.app.AppCompatActivity;

import o1310.rx1310.app.projer.R;

public class IntroActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {

					Intent i = new Intent(IntroActivity.this, MainActivity.class);
					IntroActivity.this.startActivity(i);
					// после того как загрузились в MainActivity нужно
					// убить IntroActivity, иначе при нажатии на кнопку Back
					// юзер снова попадет на сплеш.
					IntroActivity.this.finish();
					overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
				}

			}, 2200);

	}

}
