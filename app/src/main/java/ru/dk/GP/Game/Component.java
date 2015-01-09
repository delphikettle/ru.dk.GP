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
	public static float get
}
