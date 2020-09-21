/*
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.aideweb.projer.utility;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

public class PacManUtils {
	
	public static Boolean checkAppInstall(Context context, String packageName) {
		
		try {
			
			PackageManager pm = context.getPackageManager();
			pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
			
			return true;
			
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
		
	}

	public static String getPackageName(Context context) {
		
		try {
			
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			
			String pn = pi.packageName;
			return pn;
			
		} catch (Exception exc) {
			exc.printStackTrace();
			return "e: appPackageName()";
		}
		
	}

	public static String getAppVersion$name(Context context, String packageName) {
		
		try {
			
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(packageName, 0);
			
			String vn = pi.versionName;
			return vn;
			
		} catch (Exception exc) {
			exc.printStackTrace();
			return "e: appVersionName()";
		}
		
	}

	public static String getAppVersion$code(Context context, String packageName) {
		
		try {
			
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(packageName, 0);
			
			int vc = pi.versionCode;
			return vc + "";
			
		} catch (Exception exc) {
			exc.printStackTrace();
			return "e: appVersionCode()";
		}
		
	}
	
	public static void startApp(Context context, String packageName) {
		
		Intent i = context.getPackageManager().getLaunchIntentForPackage(packageName);
		
		if (i == null) {
			
			i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse("market://details?id=" + packageName));
			
		}
		
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
		
	}
	
	public static void uninstallApp(Context context, String packageName) {
		
		Intent i = new Intent(Intent.ACTION_DELETE);
		
		i.setData(Uri.parse("package:" + packageName));
		
		context.startActivity(i);
		
	}

}
