package o1310.rx1310.app.aideweb.projer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class TheFragmentPagerAdapter extends FragmentPagerAdapter
 {

	private final List<Fragment> mFragment = new ArrayList<>();
	private final List<String> mTitle = new ArrayList<>();

	public TheFragmentPagerAdapter(FragmentManager m) {
		super(m);
	}


	public Fragment getItem(int p) {
		return mFragment.get(p);
	}

	@Override
	public int getCount() {
		return mFragment.size();
	}

	public void addFragment(Fragment f, String t) {
		mFragment.add(f);
		mTitle.add(t);
	}
	@Override

	public CharSequence getPageTitle(int p) {
		return mTitle.get(p);
	}

}
