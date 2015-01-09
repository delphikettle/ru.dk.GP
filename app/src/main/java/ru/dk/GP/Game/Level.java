package ru.dk.GP.Game;

import java.util.ArrayList;

import static ru.dk.GP.Game.Component.getDistance;

public abstract class Level extends Thread
{
	ArrayList<Component> particles;
	//borders:
	private int xMin, xMax, yMin, yMax;
	private float G=1;
	private float timeFactor=1;
	private static long currentRealTime;
	private double currentGameTime;
	private boolean isMove=false, isEnd=false;
	public Level(int w, int h) {
		particles = new ArrayList<Component>();
		xMin = yMin =0;
		xMax = w;
		yMax = h;
		currentRealTime=System.currentTimeMillis();
		currentGameTime=0;
		this.setDaemon(true);
	}

	private void Interaction(Component c1, Component c2){
		float d=getDistance(c1,c2);
		float F= (float) (c1.getF()*c2.getF()/Math.pow(d,2));
		float F1=F/c1.getM(),F2=F/c2.getM();
		c1.informXAcceleration(F1*Component.getXDiff(c1,c2)/d);
		c2.informXAcceleration(F2*Component.getXDiff(c2,c1)/d);
		c1.informXAcceleration(F1*Component.getYDiff(c1, c2)/d);
		c2.informXAcceleration(F2*Component.getYDiff(c2, c1)/d);
	}
	private void Move(float time){
		for(int i=0;i<particles.size();i++)
			if(particles.get(i) != null){
			for(int j=i+1;i<particles.size();j++)
				if(particles.get(j)!=null)
					this.Interaction(particles.get(i),particles.get(j));
			particles.get(i).nextStep(time);
		}
	}

	private float getNextStepTime(){
		return (float)((currentGameTime=(this.timeFactor*(-currentRealTime+(currentRealTime=System.currentTimeMillis())))+currentGameTime)-currentGameTime);
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
}
