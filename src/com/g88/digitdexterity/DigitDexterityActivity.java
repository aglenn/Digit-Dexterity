/*
Digit Dexterity
Alex Glenn
6/15/2011
 */
package com.g88.digitdexterity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class DigitDexterityActivity extends Activity
{
	/** Called when the activity is first created. */
	ButtonAdapter buttonadapt;
	public static int butenable[];
	public static int location[];
	public static int randoml[];
	public static String romans[];
	int i, j, temp, lastclick, height, width;
	int min, sec, mil;
	long mStartTime, millis1, millis2, millis3;
	Random rand;
	public static String finaltime;
	public static TextView time;
	public static TextView highs;
	public static int bsize;
	Runnable mUpdateTimeTask;
	Timer myTimer;
	SharedPreferences s;
	String first, second, third, highloc, tempstring, spinnerarray[];

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		if(MenuActivity.LockedOrientation == 1) this.setRequestedOrientation(1); // Portrait
		else this.setRequestedOrientation(0); // Landscape
		
		setContentView(R.layout.main);

		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

		if (Preference.getReverse(getApplicationContext()))
		{
			lastclick = MenuActivity.count;
		}
		else
		{

			lastclick = -1;
		}

		min = sec = mil = 0;

		GridView gridview = (GridView) findViewById(R.id.gridview);

		buttonadapt = new ButtonAdapter(this);
		butenable = new int[MenuActivity.count];
		time = (TextView) findViewById(R.id.time);
		highs = (TextView) findViewById(R.id.highs);

		first = Integer.toString(MenuActivity.count) + "_1st";
		second = Integer.toString(MenuActivity.count) + "_2nd";
		third = Integer.toString(MenuActivity.count) + "_3rd";
		highloc = Integer.toString(MenuActivity.count) + "_highs";

		if (Preference.getReverse(getApplicationContext()))
		{
			first += "_r";
			second += "_r";
			third += "_r";
			highloc += "_r";
		}

		if (Preference.getRoman(getApplicationContext()))
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

		first += "_" + spinnerarray[((int) Preference.getMultiples(getApplicationContext()).charAt(0)) - 48];
		second += "_" + spinnerarray[((int) Preference.getMultiples(getApplicationContext()).charAt(0)) - 48];
		third += "_" + spinnerarray[((int) Preference.getMultiples(getApplicationContext()).charAt(0)) - 48];
		highloc += "_" + spinnerarray[((int) Preference.getMultiples(getApplicationContext()).charAt(0)) - 48];

		// OTHER SIZE SUPPORT

		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();

		if(MenuActivity.LockedOrientation == 1) // Portrait
		{
			gridview.setNumColumns(3);
			bsize = (int) (width / 3.7);
			gridview.setVerticalSpacing((height - (bsize * 3)) / 9);
		}
		else // Landscape
		{
			gridview.setNumColumns(4);
			bsize = (int) (height / 3.7);
			gridview.setVerticalSpacing((width - (bsize * 3)) / 9);
		}


		s = this.getSharedPreferences(highloc, MODE_WORLD_READABLE);

		millis1 = (((int) s.getString(first, "99:99.9").toCharArray()[0] - 48) * 10 * 60 * 1000);
		millis1 += (((int) s.getString(first, "99:99.9").toCharArray()[1] - 48) * 60 * 1000);
		millis1 += (((int) s.getString(first, "99:99.9").toCharArray()[3] - 48) * 10 * 1000);
		millis1 += (((int) s.getString(first, "99:99.9").toCharArray()[4] - 48) * 1000);
		millis1 += (((int) s.getString(first, "99:99.9").toCharArray()[6] - 48) * 100);

		millis2 = (((int) s.getString(second, "99:99.9").toCharArray()[0] - 48) * 10 * 60 * 1000);
		millis2 += (((int) s.getString(second, "99:99.9").toCharArray()[1] - 48) * 60 * 1000);
		millis2 += (((int) s.getString(second, "99:99.9").toCharArray()[3] - 48) * 10 * 1000);
		millis2 += (((int) s.getString(second, "99:99.9").toCharArray()[4] - 48) * 1000);
		millis2 += (((int) s.getString(second, "99:99.9").toCharArray()[6] - 48) * 100);

		millis3 = (((int) s.getString(third, "99:99.9").toCharArray()[0] - 48) * 10 * 60 * 1000);
		millis3 += (((int) s.getString(third, "99:99.9").toCharArray()[1] - 48) * 60 * 1000);
		millis3 += (((int) s.getString(third, "99:99.9").toCharArray()[3] - 48) * 10 * 1000);
		millis3 += (((int) s.getString(third, "99:99.9").toCharArray()[4] - 48) * 1000);
		millis3 += (((int) s.getString(third, "99:99.9").toCharArray()[6] - 48) * 100);

		for (i = 0; i < MenuActivity.count; i++)
		{
			butenable[i] = 1;
		}

		if (s.getString(first, "default").equals("default"))
		{
			highs.setText("\t\t\t\t\t" + "NO HIGH SCORES");
		}
		else
		{
			highs.setText("\t\t\t\t\t\t\t 1st: " + s.getString(first, "default"));
		}

		romans = getResources().getStringArray(R.array.rnumerals);
		location = new int[MenuActivity.count];
		rand = new Random();
		randoml = new int[MenuActivity.count];

		for (i = 0; i < MenuActivity.count; i++)
		{
			randoml[i] = rand.nextInt();
			location[i] = i;
		}

		for (i = 0; i < MenuActivity.count - 1; i++)
		{
			for (j = 0; j < MenuActivity.count - 1; j++)
			{
				if (randoml[j] > randoml[j + 1])
				{
					temp = randoml[j];
					randoml[j] = randoml[j + 1];
					randoml[j + 1] = temp;

					temp = location[j];
					location[j] = location[j + 1];
					location[j + 1] = temp;
				}
			}
		}

		gridview.setAdapter(buttonadapt);

		buttonadapt.setOnButtonClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Button tempbut;
				tempbut = (Button) findViewById(v.getId());
				if (Preference.getReverse(getApplicationContext()))
				{
					if (v.getId() - 999 == lastclick)
					{
						tempbut.setEnabled(false);
						butenable[v.getId() - 1000] = 0;
						lastclick--;
					}
					else
					{
						if (Preference.getXRandom(getApplicationContext()))
						{
							Intent myIntent = new Intent(v.getContext(), DigitDexterityActivity.class);
							startActivityForResult(myIntent, 0);
							finish();
						}

						lastclick = MenuActivity.count;

						for (i = 0; i < MenuActivity.count; i++)
						{
							butenable[i] = 1;
							tempbut = (Button) findViewById(i + 1000);
							if (tempbut != null)
							{
								tempbut.setEnabled(true);
							}
						}
						Toast.makeText(DigitDexterityActivity.this, "Wrong Digit! Start Over", Toast.LENGTH_SHORT)
								.show();
					}
				}
				else
				{
					if (v.getId() - 1001 == lastclick)
					{
						tempbut.setEnabled(false);
						butenable[v.getId() - 1000] = 0;
						lastclick++;
					}
					else
					{
						if (Preference.getXRandom(getApplicationContext()))
						{
							Intent myIntent = new Intent(v.getContext(), DigitDexterityActivity.class);
							startActivityForResult(myIntent, 0);
							finish();
						}

						lastclick = -1;

						for (i = 0; i < MenuActivity.count; i++)
						{
							butenable[i] = 1;
							tempbut = (Button) findViewById(i + 1000);
							if (tempbut != null)
							{
								tempbut.setEnabled(true);
							}
						}
						Toast.makeText(DigitDexterityActivity.this, "Wrong Digit! Start Over", Toast.LENGTH_SHORT)
								.show();
					}
				}

				if (Preference.getReverse(getApplicationContext()))
				{
					if (lastclick == 0)
					{
						myTimer.cancel();
						finaltime = time.getText().toString().substring(14, 21);
						DeterminePlace();
						Intent myIntent = new Intent(v.getContext(), EndScreenActivity.class);
						startActivityForResult(myIntent, 0);
						finish();
					}
				}
				else
				{
					if (lastclick == MenuActivity.count - 1)
					{
						myTimer.cancel();
						finaltime = time.getText().toString().substring(14, 21);
						DeterminePlace();
						MediaPlayer mp = MediaPlayer.create(DigitDexterityActivity.this, R.raw.tada);  
						if(Preference.getSound(getApplicationContext())) mp.start();
						Intent myIntent = new Intent(v.getContext(), EndScreenActivity.class);
						startActivityForResult(myIntent, 0);
						finish();
					}
				}

			}

			private void DeterminePlace()
			{
				long finalmillis;
				SharedPreferences shar = DigitDexterityActivity.this.getSharedPreferences(highloc, MODE_PRIVATE);
				Editor edit = shar.edit();
				edit.clear();

				finalmillis = (((int) finaltime.toCharArray()[0] - 48) * 10 * 60 * 1000);
				finalmillis += (((int) finaltime.toCharArray()[1] - 48) * 60 * 1000);
				finalmillis += (((int) finaltime.toCharArray()[3] - 48) * 10 * 1000);
				finalmillis += (((int) finaltime.toCharArray()[4] - 48) * 1000);
				finalmillis += (((int) finaltime.toCharArray()[6] - 48) * 100);

				if (finalmillis < millis1) // New first place
				{
					edit.putString(first, finaltime);
					edit.putString(second, shar.getString(first, "99:99.9"));
					edit.putString(third, shar.getString(second, "99:99.9"));
					edit.commit();
				}
				else if (finalmillis < millis2) // New second place
				{
					edit.putString(first, shar.getString(first, "99:99.9"));
					edit.putString(second, finaltime);
					edit.putString(third, shar.getString(second, "99:99.9"));
					edit.commit();
				}
				else if (finalmillis < millis3) // New third place
				{
					edit.putString(first, shar.getString(first, "99:99.9"));
					edit.putString(second, shar.getString(second, "99:99.9"));
					edit.putString(third, finaltime);
					edit.commit();
				}
				else
				{
					// Not on the high score list
				}
			}
		});

	}

	@Override
	protected void onPause()
	{
		super.onPause();
		myTimer.cancel();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		myTimer = new Timer();
		myTimer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				TimerMethod();
			}

		}, 0, 100);
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
			long tempmillis;
			time = (TextView) findViewById(R.id.time);
			highs = (TextView) findViewById(R.id.highs);
			String temp;

			s = DigitDexterityActivity.this.getSharedPreferences(highloc, MODE_WORLD_READABLE);

			// This method runs in the same thread as the UI.

			// Do something to the UI thread here
			mil++;
			if (mil == 10)
			{
				mil = 0;
				sec++;
				if (sec == 60)
				{
					sec = 0;
					min++;
				}
			}
			if (min < 10)
			{
				temp = "0" + Integer.toString(min);
			}
			else
			{
				temp = Integer.toString(min);
			}

			if (sec < 10)
			{
				temp += ":0" + Integer.toString(sec);
			}
			else
			{
				temp += ":" + Integer.toString(sec);
			}

			temp += "." + Integer.toString(mil);
			time.setText("Time Elapsed: " + temp);

			tempmillis = (((int) temp.toCharArray()[0] - 48) * 10 * 60 * 1000);
			tempmillis += (((int) temp.toCharArray()[1] - 48) * 60 * 1000);
			tempmillis += (((int) temp.toCharArray()[3] - 48) * 10 * 1000);
			tempmillis += (((int) temp.toCharArray()[4] - 48) * 1000);
			tempmillis += (((int) temp.toCharArray()[6] - 48) * 100);

			if (tempmillis > millis2)
			{
				highs.setText("\t\t\t\t\t\t\t 3rd: " + s.getString(third, "99:99.9"));
			}
			else if (tempmillis > millis1)
			{
				highs.setText("\t\t\t\t\t\t\t 2nd: " + s.getString(second, "99:99.9"));
			}
		}
	};
}
