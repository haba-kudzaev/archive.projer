/*
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.projer.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.widget.ListView;
import o1310.rx1310.app.projer.R;
import o1310.rx1310.app.projer.utility.AppUtils;
import o1310.rx1310.app.projer.utility.PacManUtils;

public class SettingsActivity extends PreferenceActivity {

	ListView mListView;
	SharedPreferences mSharedPreferences;
	
	EditTextPreference defaultDir;
	Preference appVersion, aideInstalledStatus;

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
		appVersion.setSummary(PacManUtils.getAppVersion$name(this, getPackageName()) + "." + PacManUtils.getAppVersion$code(this, getPackageName()));
		
	}
	
	public boolean onPreferenceTreeClick(PreferenceScreen s, Preference p) {

		switch (p.getKey()) {

			case "dbg_other":
				dbgOtherPrefsDialog();
				break;
				
			case "appSourceCode":
				AppUtils.openURL(this, "https://github.com/rx1310/aideweb-projer");
				break;
				
			case "appDev":
				AppUtils.openURL(this, "https://t.me/rx1310_dev");
				break;

		}

		return super.onPreferenceTreeClick(s, p);

	}
	
	// Статус установки AIDE Web
	String aideWebInstalledStatus() {
		
		if (PacManUtils.checkAppInstall(this, mAideWebPackageName)) {
			return "true | version: " + PacManUtils.getAppVersion$name(this, mAideWebPackageName) + " (" + PacManUtils.getAppVersion$code(this, mAideWebPackageName) + ")";
		} else {
			return "false";
		}
		
	}
	
	// Диалог "Прочее" | Debug
	void dbgOtherPrefsDialog() {
		
		AlertDialog.Builder b = new AlertDialog.Builder(SettingsActivity.this);

		b.setTitle(R.string.pref_dbg_other);
		b.setItems(R.array.pref_dbg_other_options, new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface d, int itemPos) {

					if (itemPos == 0) {
						PacManUtils.uninstallApp(SettingsActivity.this, mAideWebPackageName);
					}

					if (itemPos == 1) {
						PacManUtils.uninstallApp(SettingsActivity.this, getPackageName());
					}

				}
			});
			
		b.setPositiveButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface d, int i) {
				d.dismiss();
			}
		});

		b.show();
		
	}
	
}
