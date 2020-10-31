/*
 * @author      rx1310 <rx1310@inbox.ru>
 * @copyright   Copyright (c) o1310, 2020
 * @license     MIT License
 */

package o1310.rx1310.app.projer.utility;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import android.net.Uri;

import android.widget.Toast;

import o1310.rx1310.app.projer.R;

public class AppUtils {
	
	// Показ Toast-сообщения
	public static void Toast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	// Для отправки репортов на почту разработчика
	public static void sendReport(Context context) {

		String mailSubject = "REPORT: Projer " + getAppVersion(context, getPackageName(context));
		
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL, new String[]{"rx1310@inbox.ru"});
		i.putExtra(Intent.EXTRA_SUBJECT, mailSubject);
		i.putExtra(Intent.EXTRA_TEXT, "Что произошло: \n\n\nPackage name: " + getPackageName(context));

		try { 
			context.startActivity(Intent.createChooser(i, "Send with")); 
		} catch (ActivityNotFoundException e) {
			AppUtils.Toast(context, "App not found!");
		}

	}

	// Открытие ссылки
	public static void openURL(Context context, String link) {

		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(link));
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);

	}
	
	// Проверка установки приложения на устройстве
	public static Boolean checkAppInstall(Context context, String packageName) {

		try {

			PackageManager pm = context.getPackageManager();
			pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);

			return true;

		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}

	}

	// Получение package name приложения
	public static String getPackageName(Context context) {

		try {

			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);

			String pn = pi.packageName;
			return pn;

		} catch (Exception exc) {
			exc.printStackTrace();
			return "e: getPackageName()";
		}

	}
	
	// Получение версии приложения
	public static String getAppVersion(Context context, String packageName) {
		
		try {

			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(packageName, 0);

			String vn = pi.versionName;
			int vc = pi.versionCode;
			
			return vn + "." + vc;

		} catch (Exception exc) {
			exc.printStackTrace();
			return "e: appAppVersion()";
		}
		
	}

	// Запуск стороннего приложения
	public static void startApp(Context context, String packageName) {

		Intent i = context.getPackageManager().getLaunchIntentForPackage(packageName);

		if (i == null) {

			i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse("market://details?id=" + packageName));

		}

		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);

	}

	// Запроч на удаление приложения
	public static void uninstallApp(Context context, String packageName) {

		Intent i = new Intent(Intent.ACTION_DELETE);

		i.setData(Uri.parse("package:" + packageName));

		context.startActivity(i);

	}
	
}
