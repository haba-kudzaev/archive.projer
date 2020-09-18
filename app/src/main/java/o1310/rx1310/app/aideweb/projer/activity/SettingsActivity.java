/*
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.aideweb.projer.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.ListView;

import o1310.rx1310.app.aideweb.projer.R;

public class SettingsActivity extends PreferenceActivity {

	ListView mListView;
	SharedPreferences mSharedPreferences;
	
	EditTextPreference defaultDir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.app_settings);
		
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);

		mListView = findViewById(android.R.id.list);
		mListView.setDivider(null);

		defaultDir = (EditTextPreference) findPreference("defaultDir");
		defaultDir.setSummary(getString(R.string.pref_default_dir_summary));
		
	}
	
}
