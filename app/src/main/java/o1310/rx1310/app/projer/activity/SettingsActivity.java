/*
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.projer.activity;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.os.Build;

import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import android.webkit.WebSettings;
import android.webkit.WebView;

import android.widget.ListView;

import o1310.rx1310.app.projer.R;
import o1310.rx1310.app.projer.utility.AppUtils;

public class SettingsActivity extends PreferenceActivity {

	ListView mListView;
	SharedPreferences mSharedPreferences;
	
	EditTextPreference defaultDir;
	Preference appVersion, appDevInfo, aideInstalledStatus, installInA2IGA;

	String mAideWebPackageName, appVersionType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.app_settings);
		
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		if (AppUtils.getAppVersion(this, getPackageName(), false).contains("b")) {
			appVersionType = "beta";
		} else {
			appVersionType = "stable";
		}
		
		mAideWebPackageName = mSharedPreferences.getString("dbg_aideCustomPackageName", "com.aide.web");
		
		getActionBar().setDisplayHomeAsUpEnabled(true);

		mListView = findViewById(android.R.id.list);
		mListView.setDivider(null);

		defaultDir = (EditTextPreference) findPreference("defaultDir");
		defaultDir.setSummary(getString(R.string.pref_default_dir_summary));
		
		aideInstalledStatus = findPreference("aideInstalledStatus");
		aideInstalledStatus.setSummary(aideWebInstalledStatus());
		
		appVersion = findPreference("appVersion");
		appVersion.setSummary(AppUtils.getAppVersion(this, getPackageName(), false));
		
		appDevInfo = findPreference("appDevInfo");
		appDevInfo.setSummary("Package name: " + getPackageName() + "\nVersion type: " + appVersionType + "\nDevice: " + Build.MANUFACTURER + " " + Build.MODEL + " (" + Build.DEVICE + ")\nAndroid version: " + Build.VERSION.RELEASE + " (SDK " + Build.VERSION.SDK + ")\n\n" + Build.FINGERPRINT);
		
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
				
			case "appVersion":
				AppUtils.Toast(this, "👨‍💻 with ❤️ by rx1310", true, false);
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
			return "true | version: " + AppUtils.getAppVersion(this, mAideWebPackageName, true);
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
	
	// диалог со списком изменений
	/*void appChangelogDialog() {

		WebView mWebView = new android.webkit.WebView(this);
		WebSettings mWebSettings = mWebView.getSettings();

		mWebSettings.setJavaScriptEnabled(true);

		mWebView.setWebChromeClient(new android.webkit.WebChromeClient());
		mWebView.setWebViewClient(new android.webkit.WebViewClient());
		mWebView.loadUrl("file:///android_res/raw/changelog.html");

		AlertDialog.Builder mAlertBuilder = new android.support.v7.app.AlertDialog.Builder(this);
		mAlertBuilder.setView(mWebView);
		mAlertBuilder.setTitle("Changelogs");
		mAlertBuilder.show();

	}*/
	
}
