/*
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.aideweb.projer.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import o1310.rx1310.app.aideweb.projer.R;
import android.widget.Toast;

public class ProjerActivity extends AppCompatActivity {

	Toolbar mToolbar;
	SharedPreferences mSharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_projer);

		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		mToolbar = findViewById(R.id.ui_view_toolBar);
		
		setSupportActionBar(mToolbar);
		
		Toast.makeText(this, "", Toast.LENGTH_LONG).show();
		
	}
	
}
