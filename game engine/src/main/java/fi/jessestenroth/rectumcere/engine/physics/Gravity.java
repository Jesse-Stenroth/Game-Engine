package fi.jessestenroth.rectumcere.engine.physics;

import fi.jessestenroth.rectumcere.engine.basic.GameObject;

/**
 * This interface is for gravity physics (what must be implements in class)
 * @author Jesse Stenroth
 * @version 0.1
 */
public interface Gravity {
    /**
     * This method move object to direction where want move
     * @param object game object what want to move
     * @param direction which direction gravity is
     * @param delta delta time for moving implementions
     */
    public void move(GameObject object, Directions direction, double delta);

    /**
     * This method set how strength is gravity
     * @param st value of gravity
     */
    public void setStrength(double st);
}
