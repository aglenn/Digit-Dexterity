Digit Dexterity
Alex Glenn
6/15/2011

Digit Dexterity is a game available in the Android Market (goo.gl/l4xBh). The Changelog is below.

 ** Version 0.1 **
6/16/2011
	Title Screen works, and spinner picks the number of buttons to create when the 
next activity is launched. That activity launches correctly, and the logic for
determining the correct sequence works. There are 2 known bugs, but I believe
they are related:

1. There is a FC when the wrong button is pressed  when there are more buttons
   than can fit on a screen
   
2. The button status (whether it is enabled or not) is not maintained when the
   view is scrolled.

 ** Version 0.2 **
6/17/2011

	The two bugs have been fixed. They were related. The first happened because when
an incorrect button was pressed, each button was set to enabled, but whether it
was viewable was not considered. So when a not shown button was cast as a view
by its ID, it crashed. This was fixed by checking if the view was null when
resetting the value. The values are all stored in an array, so when they are 
re-shown, if their status changed it will be reflected. This fixed the second
bug as well.
	Randomization of the button locations is in as well. The buttons are put
in randomized locations every time that the DigitDexterityActivity is called.
	When the user finishes the game a screen appears that allows them to
retry with the same number of buttons, or pick a new number.

 ** Version 0.3 **
6/18/2011

	Timer is in and working. The final time is displayed after the game is
finished.
	The choices of possible button amounts has dropped, but each is optimized
for the screen size.
	There is also now a menu press on the main page. It allows for preferences,
a how to play section, and an about section. There are 3 known bugs.

1. The string on the final screen has extra text

2. The string on the final screen is wrong after multiple executions

3. The Timer continues to run when the application is not in view.

** Version 0.4 **
6/18/2011

	The three bugs from the last version have been fixed. Device compatibility
has increased and now supports as low as Android 1.6. 
	The battery consumption has been improved. The timer is now in hundreths
of a second instead of tenths as before
	 High scores now function for all different button numbers.
	 
** Version 0.5 **
6/21/2011

Reverse Mode has been added.
Roman Numerals have been added.
There is now a countdown after pressing go.
Battery consumption fixes
New Visuals
Option to view all high scores
Victory sound has been added. (works on media volume)
	
All new options are changeable in preferences.
