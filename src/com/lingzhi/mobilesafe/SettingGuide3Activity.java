package com.lingzhi.mobilesafe;

import android.os.Bundle;

public class SettingGuide3Activity extends GuideBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_guide3);
		
		setNextPage(SettingGuide4Activity.class);
		setPrePage(SettingGuide2Activity.class);
	}
}
