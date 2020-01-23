package fi.jessestenroth.rectumcere.engine.basic;

import fi.jessestenroth.rectumcere.engine.input.KeyEvent;

/**
 * This interface is all Object what do something when key pressed or released
 * @author Jesse Stenroth
 * @version 0.1
 */
public interface ActionWhenKey {
    /**
     * set action when key pressed
     * @param event action what do
     */
    public void setPressed(KeyEvent event);

    /**
     * set action when key released
     * @param event action what do
     */
    public void setReleased(KeyEvent event);

    /**
     * get action
     * @return get action what must do when key pressed
     */
    public KeyEvent getPressed();

    /**
     * get action
     * @return get action what must do when key released
     */
    public KeyEvent getReleased();
}
