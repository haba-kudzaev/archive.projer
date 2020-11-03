/*
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.projer.ui.preference;

import android.content.Context;
import android.util.AttributeSet;

import o1310.rx1310.app.projer.R;

public class DescPreference extends CategoryPreference {

	public DescPreference(Context c, AttributeSet attrs) {
		super(c, attrs);
		setSelectable(false);
		setLayoutResource(R.layout.ui_preference_desc);
	}

}
