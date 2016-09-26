package utils;

import android.content.Context;
import android.content.pm.PackageInfo;
/**
 * ��õ�ǰ����汾��
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
			return "��ȡ�汾��ʧ��";
		}
	}
}
