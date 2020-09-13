/*
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.aideweb.projer.activity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import o1310.rx1310.app.aideweb.projer.R;
import o1310.rx1310.util.unzipper.Unzipper;

public class ProjerActivity extends AppCompatActivity implements View.OnClickListener {

	Toolbar mToolbar;
	Button mCreateProject;
	EditText mInputProjectName;
	ImageView mCreatorStatus;
	SharedPreferences mSharedPreferences;

	CreatorTask mCreatorTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_projer);

		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		mToolbar = findViewById(R.id.ui_view_toolBar);
		
		mInputProjectName = findViewById(R.id.ui_projer_view_inputProjectName);
		
		mCreateProject = findViewById(R.id.ui_projer_view_createProject);
		mCreateProject.setOnClickListener(this);
		
		setSupportActionBar(mToolbar);
		
	}
	
	public void onClick(View v) {

		switch(v.getId()) {
			
			case R.id.ui_projer_view_createProject:
				mCreatorTask = new CreatorTask();
				mCreatorTask.execute();
				break;

			default: break;

		}

	}
	
	
	class CreatorTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
		}

		@Override
		protected Void doInBackground(Void... params) {
			//Unzipper.unzipFromAssets(this, "", "");
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			//tvInfo.setText("End");
		}
		
	}
	
}
