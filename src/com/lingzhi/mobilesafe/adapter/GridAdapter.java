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
	String[ ] items = new String[ ] {"�ֻ�����", "ɧ������", "����ܼ�", "���̹���", "����ͳ��", "�ֻ�ɱ��", "��������", "���ù���"};
	String[ ] desc = new String[ ]{"Զ�̶�λ�ֻ�", "ȫ��ɧ������", "�����������", "�������Ľ���",
			"����һĿ��Ȼ", "�����޴�����", "ϵͳ������", "���ߴ�ȫ" };
	
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
