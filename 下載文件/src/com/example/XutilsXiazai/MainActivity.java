package com.example.XutilsXiazai;

import java.io.File;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class MainActivity extends ActionBarActivity {
	Button but,but2;
	String url = "http://v.juhe.cn/cba/teamList.php?key=7e9d3eaffb2a523cf09b9958240e8f15";
	boolean b = true;
	HttpHandler handler;
	ProgressBar bar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		but = (Button) findViewById(R.id.but);
		but2 = (Button) findViewById(R.id.but2);
		bar = (ProgressBar) findViewById(R.id.ProgressBar);
		but.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
					Start_Download(url);
				
			}
		});
		
		but2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(b){
					b = false;
					zanting_kaishi(url);
				}else{
					//����cancel()����ֹͣ����
					handler.cancel();
				}
				
			}
		});
	}
	
	private void zanting_kaishi(String url) {
		// TODO Auto-generated method stub
		HttpUtils http = new HttpUtils();
		handler = http.download(url,
		    "sdcard/haha123123.txt",
		    true, // ���Ŀ���ļ����ڣ�����δ��ɵĲ��ּ������ء���������֧��RANGEʱ���������ء�
		    true, // ��������󷵻���Ϣ�л�ȡ���ļ�����������ɺ��Զ���������
		    new RequestCallBack<File>() {

		        @Override
		        public void onStart() {
		            Log.e("TAG", "conn...");
		        }

		        @Override
		        public void onLoading(long total, long current, boolean isUploading) {
		        	bar.setMax((int) total);
	            	bar.setMax((int) current);
		        	 Log.e("TAG", current + "/" + total);
		        }

		        @Override
		        public void onSuccess(ResponseInfo<File> responseInfo) {
		        	 Log.e("TAG", "downloaded:" + responseInfo.result.getPath());
		        }
		        
		        @Override
		        public void onFailure(HttpException error, String msg) {
		        	 Log.e("TAG", msg);
		        }
		});
	}
	
	private void Start_Download(String url) {
		// TODO Auto-generated method stub
		HttpUtils http = new HttpUtils();
		//�����ɼ����߳̽�������  
		http.configRequestThreadPoolSize(5);
        /**
         * 1.url ���ص�·��
         * 2.target �����ļ������·��
         * 3.autoResume �Ƿ�֧�ֶϵ�����
         * 4.RequestCallBack ����ص�  
         */
        http.download(url, "sdcard/haha.txt", true,new RequestCallBack<File>() {

            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                Toast.makeText(getApplicationContext(), responseInfo.result.getPath(), 1).show();
                Log.e("TAG", "����ɹ�"+responseInfo.toString());
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            	Log.e("TAG", "����ʧ��"+msg);
            }

            /**
             * ���ؽ��� pbΪ��������ʵ�ֽ��������ȼ���
             * total �ܵô�С
             * current ��ǰ�Ĵ�С
             */
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            	Log.e("TAG", "�����ļ�������M��"+total);
            	Log.e("TAG", "�����ļ��Į�ǰ�M��"+current);
            	bar.setMax((int) total);
            	bar.setProgress((int) current);   
            }
        });
	}
}
