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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import o1310.rx1310.app.aideweb.projer.R;

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
	
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.projer, menu);
		
		return super.onCreateOptionsMenu(menu);
		
	}


    public boolean onOptionsItemSelected(MenuItem mi) {

        int id = mi.getItemId();

        switch(id) {

            case R.id.menu_projer_close :
				finish();
				return true;

        }

        return super.onOptionsItemSelected(mi);
    }
	
}
