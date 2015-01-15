package ru.dk.GP;

import android.app.*;
import android.os.*;
import android.util.*;
import ru.dk.GP.Game.Levels.*;
import android.widget.*;

public class MainActivity extends Activity 
{
    public static MainActivity thisis;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        thisis=this;
		Log.i("Time","Main0 "+
		System.currentTimeMillis());
        setContentView( new LevelSurfaceViewer(this,new TestLevel(1024,1024),1024,1024));
		Log.i("Time","Main1 "+
			  System.currentTimeMillis());
		//throw new RuntimeException();
    }



	static public void ShowToast(String s)
	{
		Toast t1=Toast.makeText(thisis,s,Toast.LENGTH_LONG);
		t1.show();

	}

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		ShowToast("onDestroy");
		//mt.t.destroy();
		super.onDestroy();
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		ShowToast("onPause");
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		// TODO: Implement this method
		ShowToast("onResume");
		//if(thislevel!=null)
		//	thislevel.Resume();
		super.onResume();
	}

	@Override
	protected void onRestart()
	{
		// TODO: Implement this method
		ShowToast("onRestart");
		super.onRestart();
	}

	@Override
	protected void onStart()
	{
		// TODO: Implement this method
		ShowToast("onStart");
		super.onStart();
	}

	@Override
	protected void onStop()
	{
		// TODO: Implement this method
		ShowToast("onStop");
		super.onStop();
	}

	@Override
	public void onLowMemory()
	{
		// TODO: Implement this method
		ShowToast("onLowMemory");
		super.onLowMemory();
	}

	
}
