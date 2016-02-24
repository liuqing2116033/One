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
					//调用cancel()方法停止下载
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
		    true, // 如果目标文件存在，接着未完成的部分继续下载。服务器不支持RANGE时将从新下载。
		    true, // 如果从请求返回信息中获取到文件名，下载完成后自动重命名。
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
		//设置由几条线程进行下载  
		http.configRequestThreadPoolSize(5);
        /**
         * 1.url 下载的路径
         * 2.target 下载文件保存的路径
         * 3.autoResume 是否支持断点续传
         * 4.RequestCallBack 请求回调  
         */
        http.download(url, "sdcard/haha.txt", true,new RequestCallBack<File>() {

            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                Toast.makeText(getApplicationContext(), responseInfo.result.getPath(), 1).show();
                Log.e("TAG", "请求成功"+responseInfo.toString());
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            	Log.e("TAG", "请求失敗"+msg);
            }

            /**
             * 加载进度 pb为进度条，实现进度条进度加载
             * total 总得大小
             * current 当前的大小
             */
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            	Log.e("TAG", "下载文件的最大進度"+total);
            	Log.e("TAG", "下载文件的當前進度"+current);
            	bar.setMax((int) total);
            	bar.setProgress((int) current);   
            }
        });
	}
}
