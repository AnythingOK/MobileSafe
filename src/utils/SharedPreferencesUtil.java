package utils;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/***

 */
public class SharedPreferencesUtil {


	private static SharedPreferences sharedPreferences;

	private static void init(Context context)
	{
		if(sharedPreferences==null)
		{
			sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);//1.�ļ���
		}

	}
	/*** ���� ����boolean����
	@param key
	@param value
	 */
	public static void putBoolean(Context context,String key,Boolean value)
	{
		init(context);
		Editor editor=sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();//��Ч
	}
	/*** ����
	@param settingActivity
	@param autoUpdate
	@return
	 */
	public static boolean getBoolean(Context context, String key) {
		init(context);
		return  sharedPreferences.getBoolean(key, false);
	}
	/*** ����
	@param context
	@param protectedPwd
	@return
	 */
	public static String getString(Context context, String key) {
		init(context);
		return sharedPreferences.getString(key, null);
	}
	/*** ����
	@param context
	@param key
	@param value
	 */
	public static void putString(Context context, String key, String value) {
		init(context);
		Editor editor=sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();//��Ч
	}
}

