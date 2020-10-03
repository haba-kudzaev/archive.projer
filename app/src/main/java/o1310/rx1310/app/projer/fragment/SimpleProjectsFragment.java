/*
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.projer.fragment;

import android.os.Bundle;

import android.support.v4.app.ListFragment;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.content.Intent;

import o1310.rx1310.app.projer.R;
import o1310.rx1310.app.projer.activity.ProjerActivity;

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

			// htmlCss
			case 0:
				ProjerActivity.startWizard(getActivity(), "proj_sample_htmlCss.zip", getString(R.string.desc_project_sample_htmlCss));
				break;
				
			// htmlCssJs
			case 1:
				ProjerActivity.startWizard(getActivity(), "proj_sample_htmlCssJs.zip", getString(R.string.desc_project_sample_htmlCssJs));
				break;
				
			// htmlJs
			case 2:
				ProjerActivity.startWizard(getActivity(), "proj_sample_htmlJs.zip", getString(R.string.desc_project_sample_htmlJs));
				break;

			// js
			case 3:
				ProjerActivity.startWizard(getActivity(), "proj_sample_js.zip", getString(R.string.desc_project_sample_js));
				break;
				
			default: break;

		}

	}

}
