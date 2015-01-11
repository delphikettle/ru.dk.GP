package ru.dk.GP;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Андрей on 11.01.2015.
 */
public class LevelSurfaceViewer extends SurfaceView implements SurfaceHolder.Callback{
    public LevelSurfaceViewer(Context context) {
        super(context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
class SurfaceViewThread extends  Thread{

}