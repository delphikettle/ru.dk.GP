package ru.dk.GP.Game;

public abstract class Component
{
	private volatile float vx,vy;
	private volatile float x,y;
	private float m;
	private Particle owner;
	private boolean isNeedRecount=true;
	private float lastF;
	public Component(float x, float y,float m){
		this.x=x;
		this.y=y;
		this.vx=0;
		this.vy=0;
		this.m=m;
		owner=null;
	}
	public Component(Particle p){
		x=0;
		y=0;
		this.owner=p;
	}
	public void nextStep(){
		if(owner==null){
			x+=vx;
			y+=vy;
		}else{
			
		}
	}
	public float getF(){
		if(isNeedRecount)return lastF=recountF();else
			return lastF;
	}
	private float recountF(){
		isNeedRecount=false;
		return m;
	}
}
