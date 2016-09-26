package com.lingzhi.mobilesafe;

import android.os.Bundle;

public class SettingGuide1Activity extends GuideBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_guide1);
		setNextPage(SettingGuide2Activity.class);
		setPrePage(null);
	}
}
