package com.lingzhi.mobilesafe;


import com.lingzhi.mobilesafe.ui.SwitchView;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class SettingActivity extends Activity {

	private SwitchView updata;
	private SwitchView intercept;
	private SwitchView style;
	private RelativeLayout rl_updata;
	private RelativeLayout rl_intercept;
	private RelativeLayout rl_style;
	
	SharedPreferences sp ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		rl_updata = (RelativeLayout) findViewById(R.id.rl_updata);
		rl_intercept = (RelativeLayout) findViewById(R.id.rl_intercept);
		rl_style = (RelativeLayout) findViewById(R.id.rl_style);
		updata = (SwitchView) findViewById(R.id.ivs_updata);
		intercept = (SwitchView) findViewById(R.id.ivs_intercept);
		style = (SwitchView) findViewById(R.id.ivs_style);
		
		sp = getSharedPreferences("config", MODE_PRIVATE);
		updata.setStatu(sp.getBoolean("updata", true));
		rl_updata.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
			updata.changeStatu();
			Editor editor = sp.edit();
			editor.putBoolean("updata", updata.getStatu());
			editor.commit();
			}
		});
		
		rl_intercept.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				intercept.changeStatu();
			}
		});
		
		rl_style.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				style.changeStatu();
			}
		});
	}
	
}
