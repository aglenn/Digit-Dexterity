package com.g88.digitdexterity;

import java.util.Timer;
import java.util.TimerTask;

import com.g88.digitdexterity.R;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;

public class CountdownActivity extends Activity
{
	/** Called when the activity is first created. */
	Timer myTimer;
	ImageView imview;
	int sec;
	

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		if(MenuActivity.LockedOrientation == 1) this.setRequestedOrientation(1); // Portrait
		else this.setRequestedOrientation(0); // Landscape
		
		setContentView(R.layout.countdown);
		
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC); 
		sec = 4;
		
		imview = (ImageView) findViewById(R.id.imview);
		imview.setImageResource(R.drawable.three);
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);  

	}
	
	@Override
    protected void onPause() {
        super.onPause();
        myTimer.cancel();
    }
	
	@Override
    protected void onResume() {
        super.onResume();
        myTimer = new Timer();
        myTimer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				TimerMethod();
			}

		}, 0, 1000);
    }
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// Handle the back button
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			Intent myIntent = new Intent(getApplicationContext(), MenuActivity.class);
			startActivityForResult(myIntent, 0);
			finish();
			return true;
		}
		else
		{
			return super.onKeyDown(keyCode, event);
		}

	}
	
	protected void TimerMethod()
	{
		this.runOnUiThread(Timer_Tick);
	}

	protected Runnable Timer_Tick = new Runnable()
	{
		public void run()
		{
			sec--;
			//beep
			imview = (ImageView) findViewById(R.id.imview);
			if(sec == 3)
			{
				imview.setImageResource(R.drawable.three);
				MediaPlayer mp = MediaPlayer.create(CountdownActivity.this, R.raw.low);  
				if(Preference.getSound(getApplicationContext())) mp.start();
			}
			else if(sec == 2)
			{
				imview.setImageResource(R.drawable.two);
				MediaPlayer mp = MediaPlayer.create(CountdownActivity.this, R.raw.low);  
				if(Preference.getSound(getApplicationContext())) mp.start();
			}
			else if(sec ==1)
			{
				imview.setImageResource(R.drawable.one);
				MediaPlayer mp = MediaPlayer.create(CountdownActivity.this, R.raw.low);  
				if(Preference.getSound(getApplicationContext())) mp.start();
			}
			else
			{			
				myTimer.cancel();
				Intent myIntent = new Intent(getApplicationContext(), DigitDexterityActivity.class);
				startActivityForResult(myIntent, 0);
				MediaPlayer mp = MediaPlayer.create(CountdownActivity.this, R.raw.high);  
				if(Preference.getSound(getApplicationContext())) mp.start();
				finish();
			}
		}
	};
}
