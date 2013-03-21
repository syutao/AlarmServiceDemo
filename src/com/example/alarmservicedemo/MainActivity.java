package com.example.alarmservicedemo;

import java.util.Calendar;



import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private PendingIntent pendingIntent;
	private Button buttonStart;
	private Button buttonCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		buttonStart = (Button)findViewById(R.id.startalarm);
		buttonCancel = (Button)findViewById(R.id.cancelalarm);
		
		buttonCancel.setOnClickListener(this);  
		buttonStart.setOnClickListener(this);  
		
		mContext = this;
	}
	
	
	private MyAlarmService  mMyService;
	//这里需要用到ServiceConnection在Context.bindService和context.unBindService()里用到  
	private ServiceConnection mServiceConnection = new ServiceConnection() {  
		//当我bindService时，让TextView显示MyService里getSystemTime()方法的返回值   
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			mMyService = ((MyAlarmService.MyBinder)service).getService();  
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
		}  
	};  
	
	private Context mContext; 
	@Override
	public void onClick(View v) {
		if(v == buttonStart){
			System.out.println("open");
			Intent myIntent  = new Intent();  
			myIntent.setClass(this, MyAlarmService.class); 
			
//			mContext.startService(myIntent);  
//			System.out.println("open 2");
			
			pendingIntent = PendingIntent.getService(mContext, 0, myIntent, 0);
			AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
			alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000 , 500, pendingIntent);

			System.out.println("start alarm");
			Toast.makeText(this, "Start Alarm", Toast.LENGTH_LONG).show();
		}else if (v==buttonCancel) {
			// TODO Auto-generated method stub
			AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
			alarmManager.cancel(pendingIntent);
			// Tell the user about what we did.
			Toast.makeText(this, "Cancel!", Toast.LENGTH_LONG).show();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}






