package ru.dk.GP.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.graphics.*;
import ru.dk.GP.*;

public class Component
{
	private float vx,vy;
	private float x,y;
	private float m,r,density;
	private Particle owner;
	private boolean isNeedRecount=true;
	private float lastF;
	private boolean isRedrawNeeded=true;
	private Bitmap lastBitmap;
	public Component(float x, float y,float m) throws IllegalArgumentException {
		this.x=x;
		this.y=y;
		this.vx=10;
		this.vy=1;
		if (m>0)this.m=m; else throw new IllegalArgumentException();
		this.density=1;
		this.r=(float)Math.sqrt(this.m/Math.PI/this.density);
		this.owner=null;
		lastBitmap=Bitmap.createBitmap((int)(2.1*r+1),(int)(2.1*r+1), Bitmap.Config.ALPHA_8);
	}
	public Component(Particle p){
		x=0;
		y=0;
		this.owner=p;
	}
	public void nextStep(float time){
		if(owner==null){
			x += vx * time;
			y += vy * time;
		}else{
			
		}
		//x += vx *0.000005f;
		//y += vy *0.000005f;
		//if(time==0f) throw new IllegalArgumentException(""+time);
		//Log.i("Component",""+this.getX()+" ; "+this.getY());
	}
	public float getF(Component cAnother){
		if(isNeedRecount)return lastF=recountF(cAnother);else
			return lastF;
	}
	private float recountF(Component cAnother){
		isNeedRecount=false;
		return m;
	}
	public static float getDistance(Component c1, Component c2) throws IllegalArgumentException {
		if(c1.owner==null&&c2.owner==null)return (float)(Math.sqrt(Math.pow(c1.x-c2.x,2)+Math.pow(c1.y-c2.y,2)));else {
			throw new IllegalArgumentException("c1 and c2 are must not have owners");
		}
	}

	public float getM() {
		return m;
	}
	public float informXAcceleration(float ax){
		return vx+=ax;
	}
	public float informYAcceleration(float ay){
		return vy+=ay;
	}
	public static float getXDiff(Component c1, Component c2) throws IllegalArgumentException{
		if(c1.owner==null&&c2.owner==null)return c2.x-c1.x; else{
			throw new IllegalArgumentException("c1 and c2 are must not have owners");
		}
	}
	public static float getYDiff(Component c1, Component c2) throws IllegalArgumentException{
		if(c1.owner==null&&c2.owner==null)return c2.y-c1.y; else{
			throw new IllegalArgumentException("c1 and c2 are must not have owners");
		}
	}
	public float getX(){
		return this.x;
	}
	public float getY(){
		return this.y;
	}
	public float getR(){
		return this.r;
	}
	public void setOwner(Particle owner){
		this.owner=owner;
		this.x=this.y=0;
	}
	public final Bitmap getBitmap(Paint paint){
		if(isRedrawNeeded){
			lastBitmap=drawBitmap(paint);
			isRedrawNeeded=false;
		}
		return lastBitmap;
	}
	private Bitmap drawBitmap(Paint paint){
		lastBitmap=Bitmap.createBitmap((int)(2.1*r+1),(int)(2.1*r+1), Bitmap.Config.ARGB_8888);
		Canvas cnv=new Canvas(lastBitmap);
		//cnv.setBitmap(lastBitmap);
		paint.setColor(Color.argb(255,255,0,0));
		//paint.setShadowLayer(this.r*1.5f,0,0,Color.argb(255,0,255,255));
		cnv.drawCircle(cnv.getWidth()/2.0f,cnv.getHeight()/2.0f,this.r,paint);
		//paint.setColor(Color.BLACK);
		//cnv.drawText(""+this.r,0,0,paint);
		//lastBitmap=BitmapFactory.decodeResource(MainActivity.thisis.getResources(),R.drawable.ic_launcher);
		return lastBitmap;
	}
}
