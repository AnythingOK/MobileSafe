package com.lingzhi.mobilesafe;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import utils.StartActivityUtil;

public class GuideBaseActivity extends Activity {

	public Class<?> nextPage;
	public Class<?> prePage;
	public GestureDetector mGestureDetector;
	
	public Class<?> getNextPage() {
		return nextPage;
	}

	public void setNextPage(Class<?> nextPage) {
		this.nextPage = nextPage;
	}

	public Class<?> getPrePage() {
		return prePage;
	}

	public void setPrePage(Class<?> prePage) {
		this.prePage = prePage;
	}

	public void nextPage(View v){
		if(nextPage != null){
			finish();
			overridePendingTransition(R.anim.next_enter, R.anim.next_exit);
			StartActivityUtil.open(this, nextPage);
		}
	}

	public void prePage(View v){
		if(prePage != null){
			finish();
			overridePendingTransition(R.anim.previous_enter,	R.anim.previous_exit);
			StartActivityUtil.open(this, prePage);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
			public boolean onFling(android.view.MotionEvent e1, android.view.MotionEvent e2, float velocityX, float velocityY) {
				float offX = e1.getRawX() - e2.getRawX();
				if(Math.abs(offX) > 150){
					if(offX > 0){
						nextPage(null);
						return true;
					}
					if(offX < 0){
						prePage(null);
						return true;
					}
				}
				return super.onFling(e1, e2, velocityX, velocityY);
			};
		});
	}
}
