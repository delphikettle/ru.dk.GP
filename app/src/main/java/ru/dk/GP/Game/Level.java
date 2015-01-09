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
	public Level(int w, int h) {
		particles = new ArrayList<Component>();
		xMin = yMin =0;
		xMax = w;
		yMax = h;
	}

	private void Interaction(Component c1, Component c2){
		float d=getDistance(c1,c2);
		float F= (float) (c1.getF()*c2.getF()/Math.pow(d,2));
		float F1=F/c1.getM(),F2=F/c2.getM();
	}
	private void Move(float time){
		for(int i=0;i<particles.size();i++){
			for(int j=i+1;i<particles.size();j++)
				this.Interaction(particles.get(i),particles.get(j));
			particles.get(i).nextStep();
		}
	}
}
