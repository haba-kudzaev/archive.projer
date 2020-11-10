/*
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.projer.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import android.preference.PreferenceManager;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.text.TextUtils;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.AlertDialog;

import o1310.rx1310.app.projer.R;
import o1310.rx1310.app.projer.utility.AppUtils;

import rx1310.lib.unzipper.Unzipper;

public class ProjerActivity extends AppCompatActivity implements View.OnClickListener {

	Toolbar mToolbar;
	Button mCreateProject;
	EditText mInputProjectName;
	ImageView mCreatorStatusView;
	TextView mProjectDescView, mInfoCurrentDir, mInfoAideAutorun;
	
	String mProjectAssetFile, mProjectDesc, mDefaultDir4Projects, mAideWebPackageName;
	boolean mRunAideAfterProjectCreation, mFinishProjerActivityAfterProjectCreation;
	
	SharedPreferences mSharedPreferences;
	Intent mIntent;
	
	CreatorTask mCreatorTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_projer);

		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		mIntent = getIntent();
		
		mProjectAssetFile = mIntent.getStringExtra("PROJECT_ASSET_FILE");
		mProjectDesc = mIntent.getStringExtra("PROJECT_DESC");
		
		mDefaultDir4Projects = mSharedPreferences.getString("defaultDir", "AppProjects");
		mAideWebPackageName = mSharedPreferences.getString("dbg_aideCustomPackageName", "com.aide.web");
		
		mRunAideAfterProjectCreation = mSharedPreferences.getBoolean("runAideAfterProjectCreation", false);
		mFinishProjerActivityAfterProjectCreation = mSharedPreferences.getBoolean("finishProjerActivityAfterProjectCreation", false);
		
		mToolbar = findViewById(R.id.ui_view_toolBar);
		//mToolbar.setNavigationIcon(R.drawable.ic_close);
		
		// ? Настройка Toolbar
		setSupportActionBar(mToolbar); 
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		
		mInputProjectName = findViewById(R.id.ui_projer_view_inputProjectName);
		mInputProjectName.setHint(R.string.hint_projer_inputProjectName);
		
		mCreateProject = findViewById(R.id.ui_projer_view_createProject);
		mCreateProject.setOnClickListener(this);
		
		mCreatorStatusView = findViewById(R.id.ui_projer_view_creatorStatus);
		mCreatorStatusView.setImageResource(R.drawable.ic_wait);
		
		mProjectDescView = findViewById(R.id.ui_projer_view_textProjectDesc);
		mProjectDescView.setText(mProjectDesc);
		
		mInfoCurrentDir = findViewById(R.id.ui_projer_view_info_currentDir);
		
		if (TextUtils.isEmpty(mDefaultDir4Projects)) {
			mInfoCurrentDir.setText(R.string.msg_error_emptyDefaultDir);
		} else {
			mInfoCurrentDir.setText(String.format(getString(R.string.projer_info_currentDir), mDefaultDir4Projects));
		}
		
		mInfoAideAutorun = findViewById(R.id.ui_projer_view_info_autorunAideStatus);
		mInfoAideAutorun.setText(String.format(getString(R.string.projer_info_autorunAideStatus), autoRunAideStatus()));
		
		// ? Если поле ввода папки для проектов пустое
		if (TextUtils.isEmpty(mDefaultDir4Projects)) {
			emptyDefaultDirMessage();
			mCreateProject.setEnabled(false);
			mInputProjectName.setEnabled(false);
		}
		
	}
	
	// ! Для кнопки "Close" в Toolbar
	// ? Закрывает активити
	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return true;
	}
	
	public void onClick(View v) {

		switch(v.getId()) {
			
			case R.id.ui_projer_view_createProject:
				
				if (TextUtils.isEmpty(mInputProjectName.getText().toString())) {
					
					mInputProjectName.setError(getString(R.string.msg_error_emptyProjectName));
					
				} else {
					
					mCreatorTask = new CreatorTask();
					mCreatorTask.execute();
					
				}
				
				break;
				
			default: break;

		}

	}
	
	public static void startWizard(Context context, String assetFileName, String projectDesc) {
		
		Intent i = new Intent(context, ProjerActivity.class);
		i.putExtra("PROJECT_ASSET_FILE", assetFileName);
		i.putExtra("PROJECT_DESC", projectDesc);
		context.startActivity(i);
		
	} 
	
	String autoRunAideStatus() {
		
		if (mRunAideAfterProjectCreation) {
			return getString(R.string.projer_info_autorunAideStatus_will_be);
		} else {
			return getString(R.string.projer_info_autorunAideStatus_will_not);
		}
		
	}
	
	// ? Диалог, вызываемый при попытке создать проект в пустом каталоге
	void emptyDefaultDirMessage() {
		
		AlertDialog.Builder mAlertBuilder = new AlertDialog.Builder(ProjerActivity.this);

		mAlertBuilder.setTitle(R.string.app_name);
		mAlertBuilder.setIcon(R.drawable.ic_warning);
		mAlertBuilder.setMessage(R.string.msg_error_emptyDefaultDir);
		mAlertBuilder.setCancelable(false);
		mAlertBuilder.setPositiveButton(R.string.activity_settings, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface d, int i) {
				startActivity(new Intent(ProjerActivity.this, SettingsActivity.class));
			}
		});
		mAlertBuilder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface d, int i) {
				finish();
			}
		});

		mAlertBuilder.show();
		
	}
	
	// ? Отдельный поток для разархивирования проекта
	class CreatorTask extends AsyncTask<Void, Void, Void> {

		String projExtractPath() {
			
			// ? Если бета-версия сабжа, то проекты будут разархивированы в спец. папку 
			if (AppUtils.getAppVersion(ProjerActivity.this, getPackageName(), false).contains("b")) {
				return "/sdcard/.projer_beta/" + mDefaultDir4Projects + "/" + mInputProjectName.getText().toString();
			} else {
				return "/sdcard/" + mDefaultDir4Projects + "/" + mInputProjectName.getText().toString();
			}
			
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			mCreatorStatusView.setVisibility(View.VISIBLE);
			mCreateProject.setEnabled(false);
			
		}

		@Override
		protected Void doInBackground(Void... params) {
			
			// ? Работа библиотеки Unzipper
			Unzipper.unzipFromAssets(ProjerActivity.this, mProjectAssetFile, projExtractPath());
			
			if (mRunAideAfterProjectCreation) {
				AppUtils.startApp(ProjerActivity.this, mAideWebPackageName);
			}
			
			return null;
			
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			mCreatorStatusView.setImageResource(R.drawable.ic_done);
			
			if (mFinishProjerActivityAfterProjectCreation) {
				finish();
			} else {
				
				// ? Скрываем иконку
				Handler handler = new Handler(); 
				handler.postDelayed(new Runnable() {
					public void run() {

						mCreatorStatusView.setVisibility(View.GONE);
						mCreateProject.setEnabled(true);

					} 
				}, 1000);
				
			}
			
			AppUtils.Toast(ProjerActivity.this, getString(R.string.msg_done), true, true);
			
		}
		
	}
	
}
