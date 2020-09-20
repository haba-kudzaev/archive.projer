/*
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.aideweb.projer.activity;

import android.Manifest;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;

import android.preference.PreferenceManager;

import android.support.design.widget.TabLayout;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

import o1310.rx1310.app.aideweb.projer.R;
import o1310.rx1310.app.aideweb.projer.activity.MainActivity;
import o1310.rx1310.app.aideweb.projer.adapter.TheFragmentPagerAdapter;
import o1310.rx1310.app.aideweb.projer.fragment.SimpleProjectsFragment;

public class MainActivity extends AppCompatActivity {

	Toolbar mToolbar;
	ViewPager mViewPager;
	TabLayout mTabLayout;
	SharedPreferences mSharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		mToolbar = findViewById(R.id.ui_view_toolBar);
		mViewPager = findViewById(R.id.ui_view_viewPager);
		mTabLayout = findViewById(R.id.ui_view_tabLayout);

		setSupportActionBar(mToolbar);
		setViewPager(mViewPager);
		
		mTabLayout.setupWithViewPager(mViewPager);
		
		// Запрос разрешений
		if (Build.VERSION.SDK_INT >= 23 && (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String []{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

	}
	
	private void setViewPager(ViewPager vp) {

		TheFragmentPagerAdapter a = new TheFragmentPagerAdapter(getSupportFragmentManager());

		a.addFragment(new SimpleProjectsFragment(), getString(R.string.tab_projects_simple));
		a.addFragment(new SimpleProjectsFragment(), getString(R.string.tab_projects_bootstrap));
		a.addFragment(new SimpleProjectsFragment(), getString(R.string.tab_projects_jquery));
		a.addFragment(new SimpleProjectsFragment(), getString(R.string.tab_projects_vuejs));
		
		vp.setAdapter(a);

	}
	
	public boolean onCreateOptionsMenu(Menu m) {
		
		getMenuInflater().inflate(R.menu.main, m);
		
		return super.onCreateOptionsMenu(m);
		
	}
    
    public boolean onOptionsItemSelected(MenuItem mi) {

        int id = mi.getItemId();

        switch(id) {

            case R.id.menu_main_settings :
				startActivity(new Intent(this, SettingsActivity.class));
				return true;
				
        }

        return super.onOptionsItemSelected(mi);
		
    }

}
