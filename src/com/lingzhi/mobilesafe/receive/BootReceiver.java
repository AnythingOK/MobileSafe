package com.lingzhi.mobilesafe.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {
	private static final String TAG = "BootReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "系统加载完毕");
		Toast.makeText(context, "start finish!", Toast.LENGTH_SHORT).show();
	}
}
