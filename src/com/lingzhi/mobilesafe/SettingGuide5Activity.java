package com.lingzhi.mobilesafe;

import android.os.Bundle;

public class SettingGuide5Activity extends GuideBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_guide5);
		
		setNextPage(SettingGuideFinishActivity.class);
		setPrePage(SettingGuide4Activity.class);
	}
}
