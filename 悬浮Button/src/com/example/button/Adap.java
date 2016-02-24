package com.example.button;

import java.util.List;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adap extends BaseAdapter {
	Context context;
	List<String> list;
	
	public Adap(Context context, List<String> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		String str = list.get(arg0);
		arg1 = LayoutInflater.from(context).inflate(R.layout.adap, null);
		TextView text = (TextView) arg1.findViewById(R.id.adap_text);
		text.setText(str);
		return arg1;
	}

}
