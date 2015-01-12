package ru.dk.GP.Game;

import android.util.Log;

import java.util.ArrayList;

import static ru.dk.GP.Game.Component.getDistance;

public abstract class Level extends Thread implements Runnable
{
	private ArrayList<Component> particles;
	//borders:
	private int xMin, xMax, yMin, yMax;
	private float G=1;
	private float timeFactor=1;
	private static long currentRealTime;
	private float currentGameTime;
	private boolean isMove=false, isEnd=false;
	public Level(int w, int h) {
		particles = new ArrayList<Component>();
		xMin = yMin =0;
		xMax = w;
		yMax = h;
		currentRealTime=System.currentTimeMillis();
		currentGameTime=0;
		setParticles();
		this.isMove=true;
		//this.setDaemon(true);
	}
	abstract public void setParticles();
	synchronized final public ArrayList<Component> getComponents(){
		return particles;
	}

	final public void addComponent(Component component){
		particles.add(component);
	}
	synchronized private void Interaction(Component c1, Component c2){
		float d=getDistance(c1,c2);
		float F= (float) (c1.getF()*c2.getF()/Math.pow(d,2));
		float F1=F/c1.getM(),F2=F/c2.getM();
		c1.informXAcceleration(F1*Component.getXDiff(c1,c2)/d);
		c2.informXAcceleration(F2*Component.getXDiff(c2,c1)/d);
		c1.informYAcceleration(F1 * Component.getYDiff(c1, c2) / d);
		c2.informYAcceleration(F2 * Component.getYDiff(c2, c1) / d);
	}
	synchronized private void Move(float time){
		for(int i=0;i<particles.size();i++) {
			try {
				for (int j = i + 1; i < particles.size(); j++)
					this.Interaction(particles.get(i), particles.get(j));
				particles.get(i).nextStep(time);
			}catch (NullPointerException e){particles.remove(i);}
			catch (IndexOutOfBoundsException e){
				Log.e("Level.Move()",e.toString());
			}
		}
	}

	synchronized private float getNextStepTime(){
		return (float)((currentGameTime=(this.timeFactor*(-currentRealTime+(currentRealTime=System.currentTimeMillis())))+currentGameTime                              )-currentGameTime);
	}

	@Override
	public void run() {
		super.run();
		currentRealTime=System.currentTimeMillis();
		currentGameTime=0;
		while (!isEnd)
			if(isMove) {
				this.Move(this.getNextStepTime());
			}
	}
	public float getG(){
		return G;
	}
	public float setG(float newG){
		return this.G=newG;
	}
	public float getTimeFactor(){
		return this.timeFactor;
	}
	public float setTimeFactor(float newTimeFactor){
		if(newTimeFactor>0) return this.timeFactor=newTimeFactor;
			else return this.timeFactor;
	}
	public int getXMin(){
		return this.xMin;
	}
	public int getYMin(){
		return this.yMin;
	}
	public int getXMax(){
		return this.xMax;
	}
	public int getYMax(){
		return this.yMax;
	}
	public int setXMin(int newXMin){
		return this.xMin=newXMin;
	}
	public int setYMin(int newYMin){
		return this.yMin=newYMin;
	}
	public int setXMax(int newXMax){
		return this.xMax=newXMax;
	}
	public int setYMax(int newYMax){
		return this.yMax=newYMax;
	}
	public float getCurrentGameTime(){
		return this.currentGameTime;
	}
	public void Pause(){

	}
	public void Resume(){

	}

}
