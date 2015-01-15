package ru.dk.GP;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import ru.dk.GP.Game.*;

/**
 * Created by Андрей on 11.01.2015.
 */
public class LevelSurfaceViewer extends SurfaceView implements SurfaceHolder.Callback{
    private GameViewThread drawThread;
    private Level  level;
    private int w,h;
    public LevelSurfaceViewer(Context context, Level level,int w, int h) {
        super(context);
		Log.i("Time","LevelSurfaceViewer "+
			  System.currentTimeMillis());
        getHolder().addCallback(this);
        this.w=w;
        this.h=h;
        this.level=level;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
		Log.i("Time","surfaceCreated "+
			  System.currentTimeMillis());
        drawThread= new GameViewThread(holder,level,w,h);
        drawThread.setDaemon(true);
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry=false;
            }catch (InterruptedException e){}
        }
    }
}
class SurfaceViewThread extends  Thread{
    private SurfaceHolder surfaceHolder;
    private  Level level;
    private GameViewThread gameViewThread;
    private Canvas canvas;
    private boolean isRunning=true;
    private int w,h;
    SurfaceViewThread(SurfaceHolder surfaceHolder, Level level, int w, int h){
        this.surfaceHolder=surfaceHolder;
        this.level=level;
		if(!level.isAlive())
        level.start();
        this.w=w;
        this.h=h;
        setRunning(true);
    }

    public void setRunning(boolean newFlag){
        isRunning=newFlag;
        if(isRunning){
            gameViewThread=new GameViewThread(surfaceHolder,level,w,h);
			if(!gameViewThread.isAlive())
            gameViewThread.start();
        }else{
            gameViewThread.stopThread();
            gameViewThread=null;
        }
    }
    @Override
    public void run() {
        Paint paint=new Paint();
        while(isRunning){
            canvas=null;
            try{
                canvas=surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    //Bitmap btm= BitmapFactory.decodeResource(MainActivity.thisis.getResources(),R.drawable.ic_launcher);
                    Canvas cnv=new Canvas();
                    Bitmap btm=Bitmap.createBitmap(w,h, Bitmap.Config.ALPHA_8);
                    cnv.setBitmap(btm);
                    cnv.drawColor(Color.YELLOW);
                    //Bitmap btm=gameViewThread.getBitmap();
                    //canvas.drawBitmap(btm,0,0,paint);
					canvas.drawColor(Color.CYAN);
                }
            }finally {
                if(canvas!=null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
