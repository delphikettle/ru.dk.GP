package ru.dk.GP.Game;

public abstract class Component
{
	private volatile float vx,vy;
	private volatile Float x,y;
	private float m;
	private Particle owner;
	public Component(float x, float y,float m){
		this.x=x;
		this.y=y;
		this.vx=0;
		this.vy=0;
	}
	public Component(Particle p){
		x=null;
		y=null;
		this.owner=p;
	}
	public void nextStep(){
		x+=vx;
		y+=vy;
	}
}
