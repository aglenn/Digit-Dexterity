package com.g88.digitdexterity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MenuActivity extends Activity
{
	/** Called when the activity is first created. */

	public static int count;
	Button Go;
	Spinner spinner;
	String spinnerarray[];
	int i;
	public static int LockedOrientation;
	int j[];
	ArrayAdapter<String> adapter;
	LinearLayout LL;
	TextView numbut;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

		LL = (LinearLayout) findViewById(R.id.LL);

		Go = (Button) findViewById(R.id.go);
		spinner = (Spinner) findViewById(R.id.spinner);

		numbut = (TextView) findViewById(R.id.numbut);
		numbut.setBackgroundColor(Color.BLACK);
		numbut.setTypeface(null, Typeface.BOLD);

		j = new int[5];
		j[0] = 9;
		j[1] = 16;
		j[2] = 32;
		j[3] = 64;
		j[4] = 96;

		spinnerarray = new String[5];
		spinnerarray[0] = Integer.toString(9);
		spinnerarray[1] = Integer.toString(16);
		spinnerarray[2] = Integer.toString(32);
		spinnerarray[3] = Integer.toString(64);
		spinnerarray[4] = Integer.toString(96);

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerarray);

		spinner.setAdapter(adapter);

	}

	@Override
	protected void onPause()
	{
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// Handle the back button
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			// Ask the user if they want to quit
			new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit Digit Dexterity?")
					.setMessage("I get it, you've had too much fun...")
					.setPositiveButton("Yes", new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface dialog, int which)
						{

							// Stop the activity
							finish();
						}

					}).setNegativeButton("No", null).show();

			return true;
		}
		else
		{
			return super.onKeyDown(keyCode, event);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.popmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
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
		if (view.getId() == R.id.go)
		{
			count = j[spinner.getSelectedItemPosition()];
			LockedOrientation = this.getResources().getConfiguration().orientation;
			if (Preference.getCountdown(getApplicationContext()))
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

		else
		{
		}
	}
}
