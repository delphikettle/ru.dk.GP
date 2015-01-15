package ru.dk.GP.Game.Levels;

import ru.dk.GP.Game.Component;
import ru.dk.GP.Game.Level;
import java.util.*;

/**
 * Created by Андрей on 12.01.2015.
 */
public class TestLevel extends Level {
    public TestLevel(int w, int h) {
        super(w, h);
		this.setG(0.01f);
    }

    @Override
    public void setParticles(int w, int h) {
		Random rnd=new Random();
		for(int i=0;i<100;i++)
        	this.addComponent(new Component(rnd.nextInt(w),rnd.nextInt(h),rnd.nextInt(2500)+100));
    }
}
