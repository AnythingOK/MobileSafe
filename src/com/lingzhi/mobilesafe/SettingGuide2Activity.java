package com.lingzhi.mobilesafe;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import utils.SharedPreferencesUtil;

public class SettingGuide2Activity extends GuideBaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_guide2);
		
		setNextPage(SettingGuide3Activity.class);
		setPrePage(SettingGuide1Activity.class);
		
		initView();
	}

	private void initView() {
		LinearLayout lock_sim = (LinearLayout) findViewById(R.id.ll_lock_sim);
		final ImageView lock = (ImageView) findViewById(R.id.iv_lock);
		
		String sim = SharedPreferencesUtil.getString(this, "SimNum");
		if(TextUtils.isEmpty(sim)){
			lock.setImageResource(R.drawable.sjfd_unlock);
		}else {
			lock.setImageResource(R.drawable.sjfd_lock);
		}
		
		lock_sim.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//获取通话相关的信息
				TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
				//获取SIM卡号
				String simNum = tm.getSimSerialNumber();
				String num = SharedPreferencesUtil.getString(v.getContext(), "SimNum");
				if(TextUtils.isEmpty(num)){
					SharedPreferencesUtil.putString(v.getContext(), "SimNum", simNum);
					lock.setImageResource(R.drawable.sjfd_lock);
				}else{
					SharedPreferencesUtil.putString(v.getContext(), "SimNum", null);
					lock.setImageResource(R.drawable.sjfd_unlock);
				}
			}
		});
	}
}
