package com.lingzhi.mobilesafe;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;
import utils.GetLocalVersion;
import utils.StreamTools;

public class SplashActivity extends Activity {

	private static final String TAG = "SplashActivity";
	private RelativeLayout rl;

	private static final int UPDATE_DIALOG = 1;

	private String download;

	private ProgressDialog pd;
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_DIALOG://��ʾ�û�����
				String description = (String) msg.obj;
				showHintDialog(description);
				break;

			default:
				break;
			}
		}

	};

	/**
	 * ������װ����
	 */
	public void install(String path) {
		Intent intent = new Intent();
		intent.setAction(android.content.Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
		getApplicationContext().startActivity(intent);
	}

	protected void showHintDialog(String description) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setCancelable(false);
		builder.setTitle("������ʾ");
		builder.setMessage(description);
		builder.setPositiveButton("��������", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				HttpUtils http = new HttpUtils();
				pd = new ProgressDialog(SplashActivity.this);
				pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				pd.show();
				File sdcard = Environment.getExternalStorageDirectory();
				File file = new File(sdcard, SystemClock.uptimeMillis() + ".apk");
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					http.download(download, file.getAbsolutePath(), new RequestCallBack<File>() {

						@Override
						public void onSuccess(ResponseInfo<File> responseInfo) {
							Toast.makeText(SplashActivity.this, "���سɹ�",	 0).show();
							pd.dismiss();
							File file = responseInfo.result;
							Toast.makeText(SplashActivity.this,file.exists() ?  "�ļ�����":"�ļ�������",	0).show();

							install(file.getAbsolutePath());
						}

						@Override
						public void onFailure(HttpException error, String msg) {
							Toast.makeText(SplashActivity.this, "����ʧ��", 0).show();
							pd.dismiss();
							//������ҳ
							gohome();
						}

						@Override
						public void onLoading(long total, long current, boolean isUploading) {
							pd.setMax((int)total);
							pd.setProgress((int)current);
							super.onLoading(total, current, isUploading);
						}
					});
				}else {
					//������ҳ
					gohome();
				}
			}
		});
		builder.setNegativeButton("�Ժ���˵", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				gohome();
			}

		});
		builder.show();
	};

	private void gohome() {
		SystemClock.sleep(2000);
		Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		rl = (RelativeLayout) findViewById(R.id.rl_splash);
		//��������
		AlphaAnimation animation = new AlphaAnimation(0f, 1.0f);
		animation.setDuration(2000);
		Log.d(TAG, "start animation!");

		//��������
		rl.startAnimation(animation);
		
		//�鿴�û��Ƿ���˼����°�ť
		SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
		boolean bl = sp.getBoolean("updata", true);
		if(bl){
			new Thread(new GetVersion()).start();
		}else {
			new Thread(){
				public void run() {
					SystemClock.sleep(2000);
					gohome();
				};
			}.start();
		}
	}

	/**
	 * �������̣߳���ȡ�������汾��
	 */

	class GetVersion implements Runnable{
		private static final String TAG = "SplashActivity";

		public void run() {
			String path = getResources().getString(R.string.url);
			try {
				URL url = new URL(path);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(2000);
				int code = connection.getResponseCode();
				if(code == 200){
					InputStream is = connection.getInputStream();
					String resoult = StreamTools.readStream(is);
					JSONObject json = new JSONObject(resoult);
					String version = json.getString("version");
					String desc = json.getString("hint");
					download = json.getString("downLoad");
					Log.i(TAG, "�������汾" + version);
					//�Ƚϱ��ذ汾�źͷ������汾��
					String localversion = GetLocalVersion.getVersion(SplashActivity.this);
					if(version.equals(localversion)){
						//�汾һ�£���������������������
						Log.i(TAG, "�Ѿ������°汾");
						gohome();
					}else{
						//�汾��һ�£���ʾ����
						Message message = Message.obtain();
						message.what = UPDATE_DIALOG;
						message.obj = desc;
						handler.sendMessage(message);
					}
				}else {
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(SplashActivity.this, "���ӷ�����ʧ��", 0).show();
						}
					});
					gohome();
				}
			} catch (Exception e) {
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(SplashActivity.this, "���ӷ�����ʧ��", 0).show();
					}
				});
				gohome();
			}
		}
	}
}
