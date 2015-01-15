package ru.dk.GP.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.*;
import android.view.*;

/**
 * Created by Андрей on 11.01.2015.
 */
public class GameViewThread extends Thread{
    private Bitmap bitmap;
    private Bitmap lastBitmap;
    private Canvas canvas;
    private Level level;
    private ArrayList<Component> components;
    private Component component;
    private Paint paint;
    private Bitmap bitmapOfComponent;
    private float scale,xShift,yShift;
    private boolean isRunning,isEnd;
	private Timer timer;
	private SurfaceHolder surfaceHolder;
    public GameViewThread(SurfaceHolder holder,Level level,int w, int h){
		Log.i("Time","GameViewThread0 "+
			  System.currentTimeMillis());
        this.level=level;
		Log.i("Time","GameViewThread1 "+
			  System.currentTimeMillis());
        this.setDaemon(true);
		Log.i("Time","GameViewThread2 "+
			  System.currentTimeMillis());
        bitmap=Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
		Log.i("Time","GameViewThread3 "+
			  System.currentTimeMillis());
        canvas= new Canvas(bitmap);
		Log.i("Time","GameViewThread4 "+
			  System.currentTimeMillis());
        lastBitmap=bitmap.copy(Bitmap.Config.ARGB_8888,true);
		Log.i("Time","GameViewThread5 "+
			  System.currentTimeMillis());
        components=level.getComponents();
		Log.i("Time","GameViewThread6 "+
			  System.currentTimeMillis());
		this.surfaceHolder=holder;
		Log.i("Time","GameViewThread7 "+
			  System.currentTimeMillis());
        paint=new Paint();
		Log.i("Time","GameViewThread8 "+
			  System.currentTimeMillis());
        this.scale=1;
		Log.i("Time","GameViewThread9 "+
			  System.currentTimeMillis());
        this.xShift=0;
		Log.i("Time","GameViewThread10 "+
			  System.currentTimeMillis());
        this.yShift=0;
		Log.i("Time","GameViewThread11 "+
			  System.currentTimeMillis());
		this.setRunning(true);
		Log.i("Time","GameViewThread12 "+
			  System.currentTimeMillis());
		this.isEnd=false;
		Log.i("Time","GameViewThread13 "+
			  System.currentTimeMillis());
		this.setDaemon(true);
		Log.i("Time","GameViewThread14 "+
			  System.currentTimeMillis());
		//this.level.start();
        //reDraw(canvas);
		Log.i("Time","GameViewThread15 "+
			  System.currentTimeMillis());
    }

    public void drawBackground(Canvas canvas){
		paint.setShadowLayer(0,0,0,0);
        canvas.drawColor(Color.CYAN);
    }
	public void setRunning(boolean newFlag){
		this.isRunning=newFlag;
		if(isRunning){
			level.start();
			this.timer=new Timer();
			this.timer.schedule(new TimerTask(){

				Canvas canvasForSurface;
					@Override
					synchronized public void run()
					{
						// TODO: Implement this method
						if(isRunning){
							canvasForSurface=null;
							try{
								canvasForSurface=surfaceHolder.lockCanvas();
								synchronized(surfaceHolder){
									//canvasForSurface.drawBitmap(bitmap,0,0,paint);
									//canvasForSurface.drawColor(Color.BLUE);
									//components=level.getComponents();
									//canvasForSurface.drawBitmap(components.get(0).getBitmap(paint),0,0,paint);
									//paint.setColor(Color.BLUE);
									//canvasForSurface.drawCircle(components.get(0).getX(),components.get(0).getY(),components.get(0).getR(),paint);
									//canvasForSurface.drawBitmap(bitmap,0,0,paint);
									//reDraw(canvasForSurface);
									//Bitmap btm=Bitmap.createBitmap(1024,1024, Bitmap.Config.ARGB_4444);
									//Canvas cnv=new Canvas(btm);
									//cnv.drawColor(Color.GREEN);
									canvasForSurface.drawBitmap(lastBitmap,0,0,paint);
								}
							}catch(NullPointerException e){Log.e("timer.run()",e.toString());}
							finally{
								if(canvasForSurface!=null){
									surfaceHolder.unlockCanvasAndPost(canvasForSurface);
								}
							}
						}
						
					}

				
			},TimeUnit.MILLISECONDS.toMillis(34),TimeUnit.MILLISECONDS.toMillis(100));
		}else{
			
			timer.cancel();
			timer=null;
		}
	}
    @Override
    public void run() {
		
        super.run();
        while(!isEnd)
			if(isRunning){
            	reDraw(canvas);
        	}
    }
    private void reDraw(Canvas cnv){
		canvas=new Canvas(bitmap);
		paint.setShadowLayer(0,0,0,0);
        drawBackground(cnv);
        components=level.getComponents();
        for(int i=0;i<components.size();i++) {
            component=components.get(i);
            if(component==null)continue;
            bitmapOfComponent=component.getBitmap(paint);
            int size=bitmapOfComponent.getWidth();
            cnv.drawBitmap(bitmapOfComponent,component.getX()-size/2,component.getY()-size/2,paint);
			//cnv.drawColor(Color.GRAY);
        }
        //Canvas cnv=new Canvas();
        //Canvas cnv1= new Canvas(lastBitmap);
       	//cnv1.drawColor(Color.RED);
        lastBitmap=bitmap.copy(Bitmap.Config.ARGB_8888,false);
        //if (lastBitmap==null)throw new RuntimeException();
    }

    public void stopThread(){
        this.isEnd=true;
    }
    public Bitmap getBitmap(){
        return lastBitmap;
    }
	
}
