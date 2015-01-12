package ru.dk.GP.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Component
{
	private volatile float vx,vy;
	private volatile float x,y;
	private float m,r,density;
	private Particle owner;
	private boolean isNeedRecount=true;
	private float lastF;
	private boolean isRedrawNeeded=true;
	private Bitmap lastBitmap;
	public Component(float x, float y,float m) throws IllegalArgumentException {
		this.x=x;
		this.y=y;
		this.vx=0;
		this.vy=0;
		if (m>0)this.m=m; else throw new IllegalArgumentException();
		this.density=1;
		this.r=(float)Math.sqrt(this.m/Math.PI/this.density);
		owner=null;
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
		Log.i("Component",""+this.getX()+" ; "+this.getY());
	}
	public float getF(){
		if(isNeedRecount)return lastF=recountF();else
			return lastF;
	}
	private float recountF(){
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
	public void setOwner(Particle owner){
		this.owner=owner;
		this.x=this.y=0;
	}
	public Bitmap getBitmap(Paint paint){
		if(isRedrawNeeded){
			lastBitmap=drawBitmap(paint);
			isRedrawNeeded=false;
		}
		return lastBitmap;
	}
	private Bitmap drawBitmap(Paint paint){
		lastBitmap=Bitmap.createBitmap((int)(2.1*r+1),(int)(2.1*r+1), Bitmap.Config.ALPHA_8);
		Canvas cnv=new Canvas();
		cnv.setBitmap(lastBitmap);
		cnv.drawCircle(cnv.getWidth()/2.0f,cnv.getHeight(),this.r,paint);
		return lastBitmap;
	}
}
