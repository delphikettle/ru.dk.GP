package ru.dk.GP.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

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
    private boolean isRunning;
    public GameViewThread(Level level,int w, int h){
        this.level=level;
        this.setDaemon(true);
        canvas=new Canvas();
        bitmap=Bitmap.createBitmap(w,h, Bitmap.Config.ALPHA_8);
        canvas.setBitmap(bitmap);
        lastBitmap=bitmap.copy(Bitmap.Config.ALPHA_8,true);
        components=level.getComponents();
        paint=new Paint();
        this.scale=1;
        this.xShift=0;
        this.yShift=0;
        this.isRunning=true;
        reDraw();
    }

    public void drawBackground(Canvas canvas){
        canvas.drawColor(Color.CYAN);
    }
    @Override
    public void run() {
        super.run();
        while(isRunning){
            reDraw();
        }
    }
    private void reDraw(){
        drawBackground(canvas);
        components=level.getComponents();
        for(int i=0;i<components.size();i++) {
            component=components.get(i);
            if(component==null)continue;
            bitmapOfComponent=component.getBitmap(paint);
            int size=bitmapOfComponent.getWidth();
            canvas.drawBitmap(bitmapOfComponent,component.getX()-size/2,component.getY()-size/2,paint);

        }
        Canvas cnv=new Canvas();
        cnv.setBitmap(lastBitmap);
        cnv.drawColor(Color.RED);
        //lastBitmap=bitmap.copy(Bitmap.Config.ALPHA_8,false);
        //if (lastBitmap==null)throw new RuntimeException();
    }

    public void stopThread(){
        this.isRunning=false;
    }
    public Bitmap getBitmap(){
        return lastBitmap;
    }
}
