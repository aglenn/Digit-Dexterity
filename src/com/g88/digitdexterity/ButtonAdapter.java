package com.g88.digitdexterity;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class ButtonAdapter extends BaseAdapter
{
	private Context mContext;
	private OnClickListener mOnButtonClick;

	public ButtonAdapter(Context c)
	{
		mContext = c;
	}

	public void setOnButtonClickListener(OnClickListener listener)
	{
		mOnButtonClick = listener;
	}

	public int getCount()
	{
		return MenuActivity.count;
		// return mThumbIds.length;
	}

	public Object getItem(int position)
	{
		return null;
	}

	public long getItemId(int position)
	{
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// ImageView imageView;
		Button tempBut;
		if (convertView == null)
		{ // if it's not recycled, initialize some attributes
			tempBut = new Button(mContext);
			tempBut.setLayoutParams(new GridView.LayoutParams(DigitDexterityActivity.bsize, DigitDexterityActivity.bsize));
			tempBut.setOnClickListener(mOnButtonClick);
			tempBut.setPadding(8, 8, 8, 8);
		}
		else
		{
			tempBut = (Button) convertView;
		}

		tempBut.setId(1000 + DigitDexterityActivity.location[position]);////
		if (DigitDexterityActivity.butenable[DigitDexterityActivity.location[position]] == 0)
		{
			tempBut.setEnabled(false);
		}
		else
		{
			tempBut.setEnabled(true);
		}
		if(Preference.getRoman(mContext))
		{
			tempBut.setText(DigitDexterityActivity.location_s[position]);
		}
		else
		{
			tempBut.setText(Integer.toString(DigitDexterityActivity.location[position] + 1));
		}	
		tempBut.setTypeface(null, Typeface.BOLD);
		tempBut.setTextSize(20);
		
		return tempBut;
	}

}