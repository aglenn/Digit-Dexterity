package com.g88.digitdexterity;

import java.util.Timer;
import java.util.TimerTask;

import com.g88.digitdexterity.R;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

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
		setContentView(R.layout.countdown);
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
				mp.start();
			}
			else if(sec == 2)
			{
				imview.setImageResource(R.drawable.two);
				MediaPlayer mp = MediaPlayer.create(CountdownActivity.this, R.raw.low);  
				mp.start();
			}
			else if(sec ==1)
			{
				imview.setImageResource(R.drawable.one);
				MediaPlayer mp = MediaPlayer.create(CountdownActivity.this, R.raw.low);  
				mp.start();
			}
			else
			{			
				myTimer.cancel();
				Toast.makeText(CountdownActivity.this, "It's Digit Time!", Toast.LENGTH_SHORT).show();
				Intent myIntent = new Intent(getApplicationContext(), DigitDexterityActivity.class);
				startActivityForResult(myIntent, 0);
				MediaPlayer mp = MediaPlayer.create(CountdownActivity.this, R.raw.high);  
				mp.start();
				finish();
			}
		}
	};
}
