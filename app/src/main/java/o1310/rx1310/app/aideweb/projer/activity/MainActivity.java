package o1310.rx1310.app.aideweb.projer.activity;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import o1310.rx1310.app.aideweb.projer.R;
import o1310.rx1310.app.aideweb.projer.activity.MainActivity;
import o1310.rx1310.app.aideweb.projer.adapter.TheFragmentPagerAdapter;
import o1310.rx1310.app.aideweb.projer.fragment.MainFragment;

public class MainActivity extends AppCompatActivity
 {

	Toolbar mToolbar;
	ViewPager mViewPager;
	TabLayout mTabLayout;
	
	SharedPreferences mSharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		
		mToolbar = findViewById(R.id.ui_view_toolBar);
		mViewPager = findViewById(R.id.ui_view_viewPager);
		mTabLayout = findViewById(R.id.ui_view_tabLayout);

		setSupportActionBar(mToolbar);
		setViewPager(mViewPager);
		mTabLayout.setupWithViewPager(mViewPager);
		
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		if (Build.VERSION.SDK_INT >= 23 && (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String []{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

	}
	
	private void setViewPager(ViewPager vp) {

		TheFragmentPagerAdapter a = new TheFragmentPagerAdapter(getSupportFragmentManager());

		a.addFragment(new MainFragment(), getString(R.string.app_name));
		a.addFragment(new MainFragment(), getString(R.string.app_name));
		a.addFragment(new MainFragment(), getString(R.string.app_name));
		a.addFragment(new MainFragment(), getString(R.string.app_name));
		a.addFragment(new MainFragment(), getString(R.string.app_name));

		vp.setAdapter(a);

	}

}
