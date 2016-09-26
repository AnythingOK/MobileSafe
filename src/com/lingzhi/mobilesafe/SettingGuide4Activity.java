package com.lingzhi.mobilesafe;

import android.os.Bundle;

public class SettingGuide4Activity extends GuideBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_guide4);
		
		setNextPage(SettingGuide5Activity.class);
		setPrePage(SettingGuide3Activity.class);
	}
}
