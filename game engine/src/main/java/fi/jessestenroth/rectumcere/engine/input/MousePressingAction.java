package fi.jessestenroth.rectumcere.engine.input;

/**
 * This interface is action what happen when mouse clicked.
 * Only one can be in the game
 * @author Jesse Stenroth
 * @version 0.1
 */
public interface MousePressingAction {
    /**
     * function what do when mouse clicked
     * @param x mouse X-position
     * @param y mouse Y-position
     */
    public void action(double x, double y);
}
