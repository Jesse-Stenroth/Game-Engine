package fi.jessestenroth.rectumcere.engine.basic;

/**
 * This interface is for objects what can draw for canvas
 * @author Jesse Stenroth
 * @version 0.1
 */
public interface CanDrawToCanvas {
    /**
     * this function call when object draw to view
     * @param gs GameSystem object
     */
    public void draw(GameSystem gs);
}
