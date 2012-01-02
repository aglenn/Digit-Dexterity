package com.g88.digitdexterity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Preference extends PreferenceActivity
{
	static ListPreference multiples;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);  
	}
	
	public static boolean getXRandom(Context appContext) {
		//get hard mode enable
		SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
		return app_preferences.getBoolean("xrandom", false);
	}
	
	
	public static boolean getReverse(Context appContext) {
		//get Reverse value
		SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
		return app_preferences.getBoolean("reverse", false);	
		
	}
	
	public static boolean getSound(Context appContext) {
		//get Sound value
		SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
		return app_preferences.getBoolean("sound", true);	
		
	}
	
	public static boolean getCountdown(Context appContext) {
		//get countdown value
		SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
		return app_preferences.getBoolean("countdown", false);		
	}
	
	public static String getMultiples(Context appContext) {
		//get Multiples value		
		SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
		return app_preferences.getString("multiples", "0x");
	}
	
	public static boolean getRoman(Context appContext) {
		//get Roman value
		SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
		return app_preferences.getBoolean("roman", false);		
	}
}