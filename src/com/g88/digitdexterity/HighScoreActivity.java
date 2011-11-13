package com.g88.digitdexterity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class HighScoreActivity extends Activity
{
	/** Called when the activity is first created. */
	TextView hsview;
	String first,second,third, highloc, temptext;
	SharedPreferences s;
	int j[];
	int i;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscore);
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC); 
		j = new int[5];
		j[0] = 9;
		j[1] = 16;
		j[2] = 32;
		j[3] = 64;
		j[4] = 96;
		
		
		hsview = (TextView) findViewById(R.id.hstext);
		for(i=0;i<5;i++)
		{
			temptext = (String) hsview.getText();
			first = Integer.toString(j[i]) + "_1st";
			second = Integer.toString(j[i]) + "_2nd";
			third = Integer.toString(j[i]) + "_3rd";
			highloc = Integer.toString(j[i]) + "_highs";	
		
			s = this.getSharedPreferences(highloc, MODE_WORLD_READABLE);
		
			hsview.setText(temptext + "\n\n" + Integer.toString(j[i]) + " Buttons: \n1st:    " + s.getString(first, "99:99.9") + "\n2nd:   "+ s.getString(second, "99:99.9") + "\n3rd:    "+ s.getString(third, "99:99.9"));
	
		}
		
		for(i=0;i<5;i++)
		{
			temptext = (String) hsview.getText();
			first = Integer.toString(j[i]) + "_1st";
			second = Integer.toString(j[i]) + "_2nd";
			third = Integer.toString(j[i]) + "_3rd";
			highloc = Integer.toString(j[i]) + "_highs";	
			
			first += "_r";
			second += "_r";
			third += "_r";
			highloc += "_r";
		
			s = this.getSharedPreferences(highloc, MODE_WORLD_READABLE);
		
			hsview.setText(temptext + "\n\n" + Integer.toString(j[i]) + " Buttons Reversed: \n1st:    " + s.getString(first, "99:99.9") + "\n2nd:   "+ s.getString(second, "99:99.9") + "\n3rd:    "+ s.getString(third, "99:99.9"));
	
		}
		
		//ROMAN
		for(i=0;i<5;i++)
		{
			temptext = (String) hsview.getText();
			first = Integer.toString(j[i]) + "_1st";
			second = Integer.toString(j[i]) + "_2nd";
			third = Integer.toString(j[i]) + "_3rd";
			highloc = Integer.toString(j[i]) + "_highs";	
			
			first += "_roman";
			second += "_roman";
			third += "_roman";
			highloc += "_roman";
		
			s = this.getSharedPreferences(highloc, MODE_WORLD_READABLE);
		
			hsview.setText(temptext + "\n\n" + Integer.toString(j[i]) + " Roman Buttons: \n1st:    " + s.getString(first, "99:99.9") + "\n2nd:   "+ s.getString(second, "99:99.9") + "\n3rd:    "+ s.getString(third, "99:99.9"));
	
		}
		
		for(i=0;i<5;i++)
		{
			temptext = (String) hsview.getText();
			first = Integer.toString(j[i]) + "_1st";
			second = Integer.toString(j[i]) + "_2nd";
			third = Integer.toString(j[i]) + "_3rd";
			highloc = Integer.toString(j[i]) + "_highs";	
			
			first += "_r";
			second += "_r";
			third += "_r";
			highloc += "_r";
		
			first += "_roman";
			second += "_roman";
			third += "_roman";
			highloc += "_roman";
			
			s = this.getSharedPreferences(highloc, MODE_WORLD_READABLE);
		
			hsview.setText(temptext + "\n\n" + Integer.toString(j[i]) + " Roman Buttons Reversed: \n1st:    " + s.getString(first, "99:99.9") + "\n2nd:   "+ s.getString(second, "99:99.9") + "\n3rd:    "+ s.getString(third, "99:99.9"));
	
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
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.popmenu2, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.hscores:
	    	showHighs();
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	private void showHighs()
	{
		//clear the high scores
		SharedPreferences shar;
		for(i=0;i<5;i++)
		{
			highloc = Integer.toString(j[i]) + "_highs";	
			shar = HighScoreActivity.this.getSharedPreferences(highloc, MODE_PRIVATE);
			Editor edit = shar.edit();
			edit.clear();
			edit.commit();
		}
		
		for(i=0;i<5;i++)
		{		
			highloc = Integer.toString(j[i]) + "_highs";	
			highloc += "_r";
			shar = HighScoreActivity.this.getSharedPreferences(highloc, MODE_PRIVATE);
			Editor edit = shar.edit();
			edit.clear();
			edit.commit();
		}
		
		//ROMAN
		for(i=0;i<5;i++)
		{
			highloc = Integer.toString(j[i]) + "_highs";				
			highloc += "_roman";
			shar = HighScoreActivity.this.getSharedPreferences(highloc, MODE_PRIVATE);
			Editor edit = shar.edit();
			edit.clear();
			edit.commit();		
	
		}
		
		for(i=0;i<5;i++)
		{
			highloc = Integer.toString(j[i]) + "_highs";				
			highloc += "_r";
			highloc += "_roman";
			shar = HighScoreActivity.this.getSharedPreferences(highloc, MODE_PRIVATE);
			Editor edit = shar.edit();
			edit.clear();
			edit.commit();	
		}
		
		Toast.makeText(HighScoreActivity.this, "High Scores Cleared!", Toast.LENGTH_SHORT).show();
		Intent myIntent = new Intent(getApplicationContext(), HighScoreActivity.class);
		startActivityForResult(myIntent, 0);
		finish();
		//reload view
	}	
}
