package fi.jessestenroth.rectumcere.engine.physics.ownPhysicsEngine;

import fi.jessestenroth.rectumcere.engine.basic.GameObject;
import fi.jessestenroth.rectumcere.engine.physics.Directions;
import fi.jessestenroth.rectumcere.engine.physics.Gravity;

/**
 * This class is my own implemention of gravity physics
 * @author Jesse Stenroth
 * @version 0.1
 */
public class GravityPhysics implements Gravity {
    private double strength = 35.81;
    @Override
    public void move(GameObject object, Directions direction, double delta) {
        double x = object.getX();
        double y = object.getY();
        if(delta < 10.0) {
            if (direction == Directions.UP) {
                y = y - (0.5 * strength * delta);
            } else if (direction == Directions.DOWN) {
                y = y + (0.5 * strength * delta);
            } else if (direction == Directions.LEFT) {
                x = x - (0.5 * strength * delta);
            } else if (direction == Directions.RIGHT) {
                x = x + (0.5 * strength * delta);
            }
        } else{
            //if delta is too high
            if (direction == Directions.UP) {
                y = y - (0.5 * strength);
            } else if (direction == Directions.DOWN) {
                y = y + (0.5 * strength);
            } else if (direction == Directions.LEFT) {
                x = x - (0.5 * strength);
            } else if (direction == Directions.RIGHT) {
                x = x + (0.5 * strength);
            }
        }
        object.setX(x);
        object.setY(y);
    }

    @Override
    public void setStrength(double st) {
        this.strength = st;
    }
}
