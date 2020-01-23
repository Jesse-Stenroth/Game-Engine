package fi.jessestenroth.rectumcere.engine.basic;

/**
 * This is activity interface for collision
 * @author Jesse Stenroth
 * @version 0.1
 */
public interface CollisionActivity {
    /**
     * This is activity what happen when collision start
     * @param o1 collision object 1
     * @param o2 collision object 2
     */
    public void activity(GameObject o1, GameObject o2);
}
