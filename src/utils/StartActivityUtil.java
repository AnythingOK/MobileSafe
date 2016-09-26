package utils;

import android.content.Context;
import android.content.Intent;

public class StartActivityUtil {

	public static void open(Context context, Class<?> activity) {
		Intent intent = new Intent(context, activity);
		context.startActivity(intent);
	}
}
