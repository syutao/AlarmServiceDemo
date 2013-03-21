package com.example.alarmservicedemo;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyAlarmService extends Service {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		System.out.println("hello world");
		Toast.makeText(this, "MyAlarmService.onCreate()", Toast.LENGTH_LONG).show();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("hello world 2"); 
		Toast.makeText(this, "MyAlarmService.onBind()", Toast.LENGTH_LONG).show();
		return null;
	}
	
	public class MyBinder extends Binder{  
		MyAlarmService getService()  
		{  
			return MyAlarmService.this;  
		}  
	}  

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("MyAlarmService.onDestroy()");
		Toast.makeText(this, "MyAlarmService.onDestroy()", Toast.LENGTH_LONG).show();
	}

	public int onStartCommand(Intent intent, int flags, int startId){
		// TODO Auto-generated method stub
		super.onStartCommand(intent, flags, startId);
		System.out.println("MyAlarmService.onStart()");
		Toast.makeText(this, "MyAlarmService.onStart()", Toast.LENGTH_LONG).show();
		return START_STICKY;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("MyAlarmService.onUnbind()");
		Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG).show();
		return super.onUnbind(intent);
	}
}
