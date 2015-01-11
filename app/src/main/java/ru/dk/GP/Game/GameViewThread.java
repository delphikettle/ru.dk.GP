package ru.dk.GP.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Андрей on 11.01.2015.
 */
public class GameViewThread extends Thread{
    private Bitmap bitmap,lastBitmap;
    private Canvas canvas;
    private Level level;
    private Component[] components;
    private Component component;
    private Paint paint;
    private Bitmap bitmapOfComponent;
    private float scale,xShift,yShift;
    GameViewThread(Level level,int w, int h){
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
    }

    private void drawBackground(Canvas canvas){
        canvas.drawColor(Color.WHITE);
    }
    @Override
    public void run() {
        super.run();
        while(true){
            reDraw();
        }
    }
    private void reDraw(){
        drawBackground(canvas);
        for(int i=0;i<components.length;i++) {
            component=components[i];
            if(component==null)continue;
            bitmapOfComponent=component.getBitmap(paint);
            int size=bitmapOfComponent.getWidth();
            canvas.drawBitmap(bitmapOfComponent,component.getX()-size/2,component.getY()-size/2,paint);
        }
        lastBitmap=bitmap.copy(Bitmap.Config.ALPHA_8,true);

    }

}
