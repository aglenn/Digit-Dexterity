package com.g88.digitdexterity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class EndScreenActivity extends Activity
{
	/** Called when the activity is first created. */

	TextView FinalTime,HighScore; 
	String text;
	String first,second,third, highloc, spinnerarray[];
	
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
		
		spinnerarray = new String[4];
		spinnerarray[0] = "1x";
		spinnerarray[1] = "2x";
		spinnerarray[2] = "3x";
		spinnerarray[3] = "5x";
		
		first += "_" + spinnerarray[((int)Preference.getMultiples(getApplicationContext()).charAt(0)) - 48];
		second += "_" + spinnerarray[((int)Preference.getMultiples(getApplicationContext()).charAt(0)) - 48];
		third += "_" + spinnerarray[((int)Preference.getMultiples(getApplicationContext()).charAt(0)) - 48];
		highloc += "_" + spinnerarray[((int)Preference.getMultiples(getApplicationContext()).charAt(0)) - 48];

		FinalTime = (TextView) findViewById(R.id.ftime);
		
		HighScore = (TextView) findViewById(R.id.hscore);
		
		FinalTime.setBackgroundColor(Color.BLACK);
		FinalTime.setText("Final Time: " + DigitDexterityActivity.finaltime);
		FinalTime.setTypeface(null, Typeface.BOLD);
		
		
		SharedPreferences shar = this.getSharedPreferences(highloc, MODE_PRIVATE);
		HighScore.setBackgroundColor(Color.BLACK);
		HighScore.setText("High Scores: \n\n1st:    " + shar.getString(first, "00:00.0") + "\n2nd:   "+ shar.getString(second, "00:00.0") + "\n3rd:    "+ shar.getString(third, "00:00.0"));
		HighScore.setTypeface(null, Typeface.BOLD);
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
			MenuActivity.LockedOrientation = this.getResources().getConfiguration().orientation;
			if(Preference.getCountdown(getApplicationContext()))
			{
				Intent myIntent = new Intent(view.getContext(), CountdownActivity.class);
				startActivityForResult(myIntent, 0);
				finish();
			}
			else
			{
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
	
	@Override
    protected void onPause() {
        super.onPause();
    }
	
	@Override
    protected void onResume() {
        super.onResume();
    }
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    //Handle the back button
	    if(keyCode == KeyEvent.KEYCODE_BACK) {
	        //Ask the user if they want to quit
	        new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle("Exit Digit Dexterity?")
	        .setMessage("I get it, you've had too much fun...")
	        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

	            @Override
	            public void onClick(DialogInterface dialog, int which) {

	                //Stop the activity
	                EndScreenActivity.this.finish();    
	            }

	        })
	        .setNegativeButton("No", null)
	        .show();

	        return true;
	    }
	    else {
	        return super.onKeyDown(keyCode, event);
	    }

	}
}
