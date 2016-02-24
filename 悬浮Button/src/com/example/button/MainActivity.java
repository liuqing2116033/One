package com.example.button;

import java.util.ArrayList;
import java.util.List;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.faizmalkani.floatingactionbutton.FloatingActionButton;

public class MainActivity extends Activity{
	FloatingActionButton but;
	ListView lv;
	List<String> list;
	Adap adap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(com.example.button.R.layout.activity_main);
		
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(com.example.button.R.id.lv);
		but = (FloatingActionButton) super.findViewById(com.example.button.R.id.but);
		but.listenTo(lv);
		but.bringToFront();
		but.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "点击了悬浮按钮", 1).show();
			}
		});
		
		data();
		adap = new Adap(MainActivity.this, list);
		lv.setAdapter(adap);
		
	}
	private void data() {
		// TODO Auto-generated method stub
		list = new ArrayList<String>();
		for(int i=0;i<20;i++){
			list.add("第"+i+"个Item");
		}
	}
	
}
