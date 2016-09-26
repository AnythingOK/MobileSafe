package utils;

import android.content.Context;
import android.content.pm.PackageInfo;
/**
 * 获得当前软件版本号
 * @author lingzhizpl
 *
 */
public class GetLocalVersion {
	public static String getVersion(Context context){
		try {
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			String version = packageInfo.versionName;
			return version;
		} catch (Exception e) {
			return "获取版本号失败";
		}
	}
}
