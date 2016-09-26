package com.lingzhi.mobilesafe.adapter;

import com.lingzhi.mobilesafe.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter implements ListAdapter {

	private Context context;
	
	int[] image = new int[]{R.drawable.main_ic_1sjfd, R.drawable.main_ic_2srlj, R.drawable.main_ic_3rjgj,
			R.drawable.main_ic_4jcgl,R.drawable.main_ic_5lltj,R.drawable.main_ic_6sjsd,R.drawable.main_ic_7hcql,
			R.drawable.main_ic_8cygj};
	String[ ] items = new String[ ] {"手机防盗", "骚扰拦截", "软件管家", "进程管理", "流量统计", "手机杀毒", "缓存清理", "常用工具"};
	String[ ] desc = new String[ ]{"远程定位手机", "全面骚扰拦截", "管理您的软件", "管理您的进程",
			"流量一目了然", "病毒无处藏身", "系统快如火箭", "工具大全" };
	
	public GridAdapter(Context context) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return items.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.home_grid, null);
		}
		
		ImageView grid_item = (ImageView) convertView.findViewById(R.id.iv_grid_item);
		TextView item_title = (TextView) convertView.findViewById(R.id.tv_item_title);
		TextView item_desc = (TextView) convertView.findViewById(R.id.tv_item_desc);
		grid_item.setImageResource(image[position]);
		item_title.setText(items[position]);
		item_desc.setText(desc[position]);
		
		return convertView;
	}

}
