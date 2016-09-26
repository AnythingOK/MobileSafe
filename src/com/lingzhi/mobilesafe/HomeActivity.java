package com.lingzhi.mobilesafe;

import com.lingzhi.mobilesafe.adapter.GridAdapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import utils.StartActivityUtil;

public class HomeActivity extends Activity {
	private ImageView main_ic;
	private GridView main_item;
	private static final String TAG = "HomeActivity";
	private SharedPreferences sp;
	private AlertDialog dialog;
	private AlertDialog enterDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		main_ic = (ImageView) findViewById(R.id.iv_main_ic);	
		main_item = (GridView) findViewById(R.id.gv_main_item);

		ObjectAnimator oa = ObjectAnimator.ofFloat(main_ic, "rotationY", 0,  90, 180, 270, 360);
		oa.setDuration(3000);
		oa.setRepeatCount(ObjectAnimator.INFINITE);
		oa.setRepeatMode(ObjectAnimator.RESTART);
		oa.start();

		main_item.setAdapter(new GridAdapter(this));
		main_item.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
				case 0:
					//判断用户是否设置过密码
					if(isSetPwd()){
						Log.i(TAG, "输入密码");
						showEnterDialog();
					}else {
						Log.i(TAG, "设置密码");
						showSetPwdDialog();
					}
					break;
				case 1:
					Log.i(TAG, "进入骚扰拦截");
					break;
				}
			}
		});
	}

	public void settingCenterActivity(View view){
		Intent intent = new Intent(this, SettingActivity.class);
		startActivity(intent);
	}

	/**
	 * 判断是否保存过密码
	 */
	public boolean isSetPwd(){
		sp = getSharedPreferences("config", MODE_PRIVATE);
		String pwd = sp.getString("key", null);
		if(TextUtils.isEmpty(pwd)){
			return false;
		}else {
			return true;
		}
	}

	/**
	 * 展示设置密码对话框
	 */
	private void showSetPwdDialog() {
		AlertDialog.Builder adb = new Builder(this);
		View view = View.inflate(this, R.layout.dialog_setpassword, null);
		final EditText et_setpwd = (EditText) view.findViewById(R.id.et_setpwd);
		final EditText et_setpwd2 = (EditText) view.findViewById(R.id.et_setpwd2);
		Button confirm = (Button) view.findViewById(R.id.btn_confirm);
		Button cancle = (Button) view.findViewById(R.id.btn_cancle);
		adb.setView(view);
		dialog = adb.show();

		cancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String password = et_setpwd.getText().toString().trim();
				String password2 = et_setpwd2.getText().toString().trim();

				if(TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)){
					Toast.makeText(HomeActivity.this, "密码不能为空", 0).show();
					return;
				}
				
				if(!(password.equals(password2))){
					Toast.makeText(HomeActivity.this, "密码输入不一致", 0).show();
					return;
				}
				
				Editor pdw = sp.edit();
				pdw.putString("key", password);
				pdw.commit();
				dialog.dismiss();//关闭对话框
				//跳转到输入密码对话框
				showEnterDialog();
			}

		});
	}

	/**
	 * 输入密码对话框
	 */
	private void showEnterDialog() {
		AlertDialog.Builder builder = new Builder(HomeActivity.this);
		View view = View.inflate(HomeActivity.this, R.layout.dialog_enterpwd, null);
		builder.setView(view);
		enterDialog = builder.show();
		
		final EditText enterpwd = (EditText) view.findViewById(R.id.et_enterpwd);
		Button enter = (Button) view.findViewById(R.id.btn_enter_confirm);
		Button cancle = (Button) view.findViewById(R.id.btn_enter_cancle);
		
		cancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				enterDialog.dismiss();
			}
		});
		
		enter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String key = sp.getString("key", null);
				String enterKey = enterpwd.getText().toString().trim();
				if(key.equals(enterKey)){
					enterDialog.dismiss();
					//进入手机防盗界面（判断是否是第一次进入，如果是就进入新手引导，不是就直接进入）
					boolean isFirst = sp.getBoolean("isFirst", true);
					if(isFirst){
						//进入设置引导
						StartActivityUtil.open(v.getContext(), SettingGuide1Activity.class);
//						Editor edit = sp.edit();
//						edit.putBoolean("isFirst", false);
//						edit.commit();
					}else{
						//进入手机防盗界面
						StartActivityUtil.open(v.getContext(), MobileGuard.class);
					}
				}else{
					Toast.makeText(getApplicationContext(), "密码错误，请重新输入", 0).show();
					return;
				}
			}
		});
	}
}
