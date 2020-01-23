package fi.jessestenroth.rectumcere.engine.input;

import javafx.scene.input.KeyCode;

/**
 * This interface handle event of key event action
 * @author Jesse Stenroth
 * @version 0.1
 */
public interface KeyEvent {
    /**
     * Action of event
     * @param e Value what key have pressed NOTICE: this code is javafx KeyCode
     */
    public void event(KeyCode e);
}
