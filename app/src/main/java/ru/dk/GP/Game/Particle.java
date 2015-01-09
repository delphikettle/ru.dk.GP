package ru.dk.GP.Game;

import java.util.ArrayList;

public class Particle extends Component
{
	private ArrayList<Component> components;
	Particle(float x, float y,float m){
		super(x,y,m);
		components=new ArrayList<Component>(1);

	}
}
