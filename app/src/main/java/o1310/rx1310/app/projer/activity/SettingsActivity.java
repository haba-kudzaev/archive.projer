/*
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.projer.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.text.TextUtils;
// import android.app.AlertDialog;

import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import android.widget.ListView;

import o1310.rx1310.app.projer.R;
import o1310.rx1310.app.projer.utility.AppUtils;

import android.support.v7.app.AlertDialog;

public class SettingsActivity extends PreferenceActivity {

	ListView mListView;
	SharedPreferences mSharedPreferences;
	
	EditTextPreference defaultDir;
	Preference appVersion, aideInstalledStatus, installInA2IGA;

	String mAideWebPackageName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.app_settings);
		
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		mAideWebPackageName = mSharedPreferences.getString("dbg_aideCustomPackageName", "com.aide.web");
		
		getActionBar().setDisplayHomeAsUpEnabled(true);

		mListView = findViewById(android.R.id.list);
		mListView.setDivider(null);

		defaultDir = (EditTextPreference) findPreference("defaultDir");
		defaultDir.setSummary(getString(R.string.pref_default_dir_summary));
		
		aideInstalledStatus = findPreference("aideInstalledStatus");
		aideInstalledStatus.setSummary(aideWebInstalledStatus());
		
		appVersion = findPreference("appVersion");
		appVersion.setSummary(AppUtils.getAppVersion(this, getPackageName()) + "\n" + AppUtils.getPackageName(this));
		
		installInA2IGA = findPreference("installInA2IGA");
		installInA2IGA.setSummary(a2igaInstalledStatus());
		
	}
	
	public boolean onPreferenceTreeClick(PreferenceScreen s, Preference p) {

		switch (p.getKey()) {

			case "dbg_uninstallAideWeb":
				AppUtils.uninstallApp(SettingsActivity.this, mAideWebPackageName);
				break;
				
			case "dbg_uninstallProjer":
				AppUtils.uninstallApp(SettingsActivity.this, getPackageName());
				break;
				
			case "appSourceCode":
				AppUtils.openURL(this, "https://github.com/rx1310/projer");
				break;
				
			case "appDev":
				AppUtils.openURL(this, "https://t.me/rx1310_dev");
				break;
				
			case "installInA2IGA":
				installInA2IGA();
				break;

		}

		return super.onPreferenceTreeClick(s, p);

	}
	
	// Статус установки AIDE Web
	String aideWebInstalledStatus() {
		
		if (AppUtils.checkAppInstall(this, mAideWebPackageName)) {
			return "true | version: " + AppUtils.getAppVersion(this, mAideWebPackageName);
		} else {
			return "false";
		}
		
	}
	
	// Статус установки A2IGA
	String a2igaInstalledStatus() {
		
		if (AppUtils.checkAppInstall(this, "o1310.rx1310.app.a2iga")) {
			return getString(R.string.pref_more_a2iga_summary);
		} else {
			return getString(R.string.pref_more_a2iga_summary) + "\n\n" + getString(R.string.pref_more_a2iga_not_found_summary);
		}
		
	}
	
	void installInA2IGA() {
		
		if (AppUtils.checkAppInstall(this, "o1310.rx1310.app.a2iga")) {
			
			Intent sendPackageName = new Intent();
			sendPackageName.setAction(Intent.ACTION_SEND);
			sendPackageName.putExtra(Intent.EXTRA_TEXT, getPackageName());
			sendPackageName.setType("text/plain");
			startActivity(Intent.createChooser(sendPackageName, getString(R.string.pref_more_a2iga_chooser_title)));
			
		} else {
			AppUtils.openURL(this, "https://github.com/o1310/a2iga/releases");
		}
		
	}
	
}
