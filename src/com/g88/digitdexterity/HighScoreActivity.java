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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HighScoreActivity extends Activity
{
	/** Called when the activity is first created. */
	TextView hsview, hstitle;
	String first,second,third, highloc, temptext;
	SharedPreferences s;
	int j[];
	int i;
	Spinner spinner;
	CheckBox reverse;
	CheckBox roman;
	Button submit;
	String spinnerarray[];
	ArrayAdapter<String> adapter;
	
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
		
		spinner = (Spinner) findViewById(R.id.spinner);
		reverse = (CheckBox) findViewById(R.id.reverse);
		roman = (CheckBox) findViewById(R.id.roman);
		submit = (Button) findViewById(R.id.submit);
		
		spinnerarray = new String[4];
		spinnerarray[0] = "1x";
		spinnerarray[1] = "2x";
		spinnerarray[2] = "3x";
		spinnerarray[3] = "5x";

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerarray);

		spinner.setAdapter(adapter);
		
		hsview = (TextView) findViewById(R.id.hstext);
		hstitle = (TextView) findViewById(R.id.hstitle);

		
		hstitle.setText("\nRegular " + spinnerarray[spinner.getSelectedItemPosition()]);
		hsview.setText("");
		for(i=0;i<5;i++)
		{
			temptext = hsview.getText().toString();
			first = Integer.toString(j[i]) + "_1st";
			second = Integer.toString(j[i]) + "_2nd";
			third = Integer.toString(j[i]) + "_3rd";
			highloc = Integer.toString(j[i]) + "_highs";	
		
			first += "_" + spinnerarray[spinner.getSelectedItemPosition()];
			second += "_" + spinnerarray[spinner.getSelectedItemPosition()];
			third += "_" + spinnerarray[spinner.getSelectedItemPosition()];
			highloc += "_" + spinnerarray[spinner.getSelectedItemPosition()];
			
			s = this.getSharedPreferences(highloc, MODE_WORLD_READABLE);
		
			hsview.setText(temptext + "\n" + Integer.toString(j[i]) + " Buttons: \n1st:    " + s.getString(first, "99:99.9") + "\n2nd:   "+ s.getString(second, "99:99.9") + "\n3rd:    "+ s.getString(third, "99:99.9") + "\n");
	
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
	
	public void onClick(View view)
	{
		if (view.getId() == R.id.submit)
		{
			if(reverse.isChecked() && !roman.isChecked())
	        {
	        	//REVERSED
				hstitle.setText("\nReversed " + spinnerarray[spinner.getSelectedItemPosition()]);
				hsview.setText("");
	        	for(i=0;i<5;i++)
	    		{
	        		temptext = hsview.getText().toString();
	    			first = Integer.toString(j[i]) + "_1st";
	    			second = Integer.toString(j[i]) + "_2nd";
	    			third = Integer.toString(j[i]) + "_3rd";
	    			highloc = Integer.toString(j[i]) + "_highs";	
	    			
	    			first += "_r";
	    			second += "_r";
	    			third += "_r";
	    			highloc += "_r";
	    			
	    			first += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    			second += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    			third += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    			highloc += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    		
	    			s = this.getSharedPreferences(highloc, MODE_WORLD_READABLE);
	    		
	    			hsview.setText(temptext + "\n" + Integer.toString(j[i]) + " Buttons: \n1st:    " + s.getString(first, "99:99.9") + "\n2nd:   "+ s.getString(second, "99:99.9") + "\n3rd:    "+ s.getString(third, "99:99.9") + "\n");
	    	
	    		}
	        }
	        else if(!reverse.isChecked() && roman.isChecked())
	        {
	        	//ROMAN
	        	hstitle.setText("\nRoman " + spinnerarray[spinner.getSelectedItemPosition()]);
	        	hsview.setText("");
	    		for(i=0;i<5;i++)
	    		{
	    			temptext = hsview.getText().toString();
	    			first = Integer.toString(j[i]) + "_1st";
	    			second = Integer.toString(j[i]) + "_2nd";
	    			third = Integer.toString(j[i]) + "_3rd";
	    			highloc = Integer.toString(j[i]) + "_highs";	
	    			
	    			first += "_roman";
	    			second += "_roman";
	    			third += "_roman";
	    			highloc += "_roman";
	    			
	    			first += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    			second += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    			third += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    			highloc += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    		
	    			s = this.getSharedPreferences(highloc, MODE_WORLD_READABLE);
	    		
	    			hsview.setText(temptext +  "\n" + Integer.toString(j[i]) + " Buttons: \n1st:    " + s.getString(first, "99:99.9") + "\n2nd:   "+ s.getString(second, "99:99.9") + "\n3rd:    "+ s.getString(third, "99:99.9") + "\n");
	    	
	    		}
	        }
	        else if(reverse.isChecked() && roman.isChecked())
	        {
	        	//REVERSED ROMAN
	        	hstitle.setText("\nReversed Roman " + spinnerarray[spinner.getSelectedItemPosition()]);
	        	hsview.setText("");
	        	for(i=0;i<5;i++)
	    		{
	        		temptext = hsview.getText().toString();
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
	    			
	    			first += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    			second += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    			third += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    			highloc += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    			
	    			s = this.getSharedPreferences(highloc, MODE_WORLD_READABLE);
	    		
	    			hsview.setText(temptext + "\n" + Integer.toString(j[i]) + " Buttons: \n1st:    " + s.getString(first, "99:99.9") + "\n2nd:   "+ s.getString(second, "99:99.9") + "\n3rd:    "+ s.getString(third, "99:99.9") + "\n");
	    		
	    		}
	        }
	        else
	        {
	        	//REGULAR
	        	hstitle.setText("\nRegular " + spinnerarray[spinner.getSelectedItemPosition()]);
	        	hsview.setText("");
	    		for(i=0;i<5;i++)
	    		{
	    			temptext = hsview.getText().toString();
	    			first = Integer.toString(j[i]) + "_1st";
	    			second = Integer.toString(j[i]) + "_2nd";
	    			third = Integer.toString(j[i]) + "_3rd";
	    			highloc = Integer.toString(j[i]) + "_highs";	
	    			
	    			first += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    			second += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    			third += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    			highloc += "_" + spinnerarray[spinner.getSelectedItemPosition()];
	    		
	    			s = this.getSharedPreferences(highloc, MODE_WORLD_READABLE);
	    		
	    			hsview.setText(temptext + "\n" + Integer.toString(j[i]) + " Buttons: \n1st:    " + s.getString(first, "99:99.9") + "\n2nd:   "+ s.getString(second, "99:99.9") + "\n3rd:    "+ s.getString(third, "99:99.9") + "\n");
	    	
	    		}
	        }
		}
	}

	private void showHighs()
	{
		//clear the high scores

		int k;
		SharedPreferences shar;
		for(k=0;k<4;k++)
		{
			for(i=0;i<5;i++)
			{
				highloc = Integer.toString(j[i]) + "_highs";	
				highloc += "_" + spinnerarray[k];
				shar = HighScoreActivity.this.getSharedPreferences(highloc, MODE_PRIVATE);
				Editor edit = shar.edit();
				edit.clear();
				edit.commit();
			}
		
			for(i=0;i<5;i++)
			{		
				highloc = Integer.toString(j[i]) + "_highs";	
				highloc += "_r";
				highloc += "_" + spinnerarray[k];
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
				highloc += "_" + spinnerarray[k];
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
				highloc += "_" + spinnerarray[k];
				shar = HighScoreActivity.this.getSharedPreferences(highloc, MODE_PRIVATE);
				Editor edit = shar.edit();
				edit.clear();
				edit.commit();	
			}
		}
		Toast.makeText(HighScoreActivity.this, "High Scores Cleared!", Toast.LENGTH_SHORT).show();
		Intent myIntent = new Intent(getApplicationContext(), HighScoreActivity.class);
		startActivityForResult(myIntent, 0);
		finish();
		//reload view
	}	
}
