package ru.dk.GP.Game.Levels;

import ru.dk.GP.Game.Component;
import ru.dk.GP.Game.Level;

/**
 * Created by Андрей on 12.01.2015.
 */
public class TestLevel extends Level {
    public TestLevel(int w, int h) {
        super(w, h);
    }

    @Override
    public void setParticles() {
        this.addComponent(new Component(100,100,1000));
    }
}
