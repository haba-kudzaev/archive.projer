/*
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.aideweb.projer.fragment;

import android.os.Bundle;

import android.support.v4.app.ListFragment;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.content.Intent;

import o1310.rx1310.app.aideweb.projer.R;
import o1310.rx1310.app.aideweb.projer.activity.ProjerActivity;

public class SimpleProjectsFragment extends ListFragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		ArrayAdapter<CharSequence> a = ArrayAdapter.createFromResource(getActivity(), R.array.projects_sample, R.layout.ui_listview_item);
		setListAdapter(a);

	}

	public void onListItemClick(ListView lv, View v, int p, long lg) {
		super.onListItemClick(lv, v, p, lg);

		switch (p) {

			case 0:
				//startActivity(new Intent(getActivity(), ProjerActivity.class));
				ProjerActivity.startWizard(getActivity(), "proj_sample_htmlCss.zip", getString(R.string.desc_project_sample_htmlCss));
				break;

			default: break;

		}

	}

}
