package ru.dk.GP;

import android.app.*;
import android.os.*;
import ru.dk.GP.Game.Levels.TestLevel;

public class MainActivity extends Activity 
{
    public static MainActivity thisis;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        thisis=this;
        setContentView( new LevelSurfaceViewer(this,new TestLevel(1024,1024),1024,1024));
    }
}
