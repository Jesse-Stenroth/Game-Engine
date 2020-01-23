package fi.jessestenroth.rectumcere.engine.basic;

/**
 * This interface contain basic game loops and start cause
 * @author Jesse Stenroth
 * @version 0.2
 */
public interface Game{
    /**
     * start method be called when game start
     */
    public void start(GameSystem gs);

    /**
     * update method be called when start method have called after start() and it call every time until window is closed.
     */
    public void update(GameSystem gs);
    /**
     * close method be called when game close by close button of window
     */
    public void close(GameSystem gs);
}