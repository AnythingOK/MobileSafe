package com.lingzhi.mobilesafe.ui;

import com.lingzhi.mobilesafe.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SwitchView extends ImageView {

	private boolean statu = true;
	
	public boolean getStatu(){
		return statu;
	}
	
	public void setStatu(boolean statu){
		this.statu = statu;
		if(statu){
			setImageResource(R.drawable.settting_on);
		}else {
			setImageResource(R.drawable.settting_off);
		}
	}
	
	public void changeStatu(){
		setStatu(!statu);
	}
	
	public SwitchView(Context context) {
		super(context);
	}

	public SwitchView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public SwitchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

}
