package com.g88.digitdexterity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EndScreenActivity extends Activity
{
	/** Called when the activity is first created. */

	TextView FinalTime,HighScore; 
	String text;
	String first,second,third, highloc;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end);
		
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);  
		
		first = Integer.toString(MenuActivity.count) + "_1st";
		second = Integer.toString(MenuActivity.count) + "_2nd";
		third = Integer.toString(MenuActivity.count) + "_3rd";
		highloc = Integer.toString(MenuActivity.count) + "_highs";
		
		if(Preference.getReverse(getApplicationContext()))
		{
			first += "_r";
			second += "_r";
			third += "_r";
			highloc += "_r";
		}
		
		if(Preference.getRoman(getApplicationContext()))
		{
			first += "_roman";
			second += "_roman";
			third += "_roman";
			highloc += "_roman";
		}
		
		FinalTime = (TextView) findViewById(R.id.ftime);
		
		HighScore = (TextView) findViewById(R.id.hscore);
		
		FinalTime.setBackgroundColor(Color.BLACK);
		FinalTime.setText("Final Time: " + DigitDexterityActivity.finaltime);
		FinalTime.setTypeface(null, Typeface.BOLD);
		
		
		SharedPreferences shar = this.getSharedPreferences(highloc, MODE_PRIVATE);
		HighScore.setBackgroundColor(Color.BLACK);
		HighScore.setText("High Scores: \n\n1st:    " + shar.getString(first, "00:00.0") + "\n2nd:   "+ shar.getString(second, "00:00.0") + "\n3rd:    "+ shar.getString(third, "00:00.0"));
		HighScore.setTypeface(null, Typeface.BOLD);
		
		MediaPlayer mp = MediaPlayer.create(DigitDexterityActivity.this, R.raw.tada);  
		mp.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.popmenu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.preferences:
	    	showPreferences();
	        return true;
	    case R.id.hscores:
	    	showHighs();
	    	return true;
	    case R.id.howto:
	        showHelp();
	        return true;
	    case R.id.about:
	        showAbout();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	private void showHighs()
	{
		Intent myIntent = new Intent(getApplicationContext(), HighScoreActivity.class);
		startActivityForResult(myIntent, 0);
		
	}

	private void showAbout()
	{
		new AlertDialog.Builder(this).setTitle("About").setMessage(R.string.about).setPositiveButton("OK", null).show();  
	}
	
	private void showHelp()
	{
		if(Preference.getReverse(getApplicationContext()))
		{
			new AlertDialog.Builder(this).setTitle("How to Play:").setIcon(R.drawable.icon).setMessage(R.string.howtoplay).setPositiveButton("OK", null).show();  
		}
		else
		{
			new AlertDialog.Builder(this).setTitle("How to Play:").setIcon(R.drawable.icon).setMessage(R.string.howtoplayreverse).setPositiveButton("OK", null).show();
		}
	}
	private void showPreferences()
	{
		Intent myIntent = new Intent(getApplicationContext(), Preference.class);
		startActivityForResult(myIntent, 0);
	}
	
	public void onClick(View view)
	{
		//
		if (view.getId() == R.id.retry)
		{
			if(Preference.getCountdown(getApplicationContext()))
			{
				Intent myIntent = new Intent(view.getContext(), CountdownActivity.class);
				startActivityForResult(myIntent, 0);
				finish();
			}
			else
			{
				Toast.makeText(EndScreenActivity.this, "It's Digit Time!", Toast.LENGTH_SHORT).show();
				Intent myIntent = new Intent(getApplicationContext(), DigitDexterityActivity.class);
				startActivityForResult(myIntent, 0);
				finish();
			}
		}

		else if (view.getId() == R.id.newnumber)
		{
			Intent myIntent = new Intent(view.getContext(), MenuActivity.class);
			startActivityForResult(myIntent, 0);
			finish();
		}
	}
}