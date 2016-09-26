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
			sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);//1.文件名
		}

	}
	/*** 方法 保存boolean参数
	@param key
	@param value
	 */
	public static void putBoolean(Context context,String key,Boolean value)
	{
		init(context);
		Editor editor=sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();//生效
	}
	/*** 方法
	@param settingActivity
	@param autoUpdate
	@return
	 */
	public static boolean getBoolean(Context context, String key) {
		init(context);
		return  sharedPreferences.getBoolean(key, false);
	}
	/*** 方法
	@param context
	@param protectedPwd
	@return
	 */
	public static String getString(Context context, String key) {
		init(context);
		return sharedPreferences.getString(key, null);
	}
	/*** 方法
	@param context
	@param key
	@param value
	 */
	public static void putString(Context context, String key, String value) {
		init(context);
		Editor editor=sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();//生效
	}
}

