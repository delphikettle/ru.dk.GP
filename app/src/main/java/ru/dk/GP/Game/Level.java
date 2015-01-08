package ru.dk.GP.Game;

public abstract class Level extends Thread
{
	static public void Interaction(Component c1, Component c2){
		float F= c1.getF()*c2.getF();
	}
	
}
