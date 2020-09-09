package o1310.rx1310.app.aideweb.projer.activity;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import o1310.rx1310.app.aideweb.projer.R;
import o1310.rx1310.app.aideweb.projer.activity.MainActivity;

public class MainActivity extends AppCompatActivity {

	SharedPreferences mSharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		if (Build.VERSION.SDK_INT >= 23 && (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(MainActivity.this, new String []{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

	}

}
