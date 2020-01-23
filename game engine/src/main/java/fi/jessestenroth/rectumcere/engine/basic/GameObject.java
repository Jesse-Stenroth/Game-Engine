package fi.jessestenroth.rectumcere.engine.basic;
import fi.jessestenroth.rectumcere.engine.input.ActionWhenObjectClicked;
import fi.jessestenroth.rectumcere.engine.input.KeyEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class is basic object for games
 * @author Jesse Stenroth
 * @version 0.2
 */
public class GameObject implements CanDrawToCanvas,ActionWhenKey{
    private double x;
    private double y;
    private double width;
    private double height;
    private Image image;
    private CollisionActivity activity;
    private KeyEvent press;
    private KeyEvent release;
    private ActionWhenObjectClicked action;
    private double weight = 1.0;
    private boolean physics = true;

    /**
     * This method set action when object has clicked
     * @param a class what implements action
     */
    public void setActionWhenClicked(ActionWhenObjectClicked a){
        this.action = a;
    }

    /**
     * This method give back class what implements interface of ActionWhenObjectClicked
     * @return action what do
     */
    public ActionWhenObjectClicked getActionWhenClicked(){
        return this.action;
    }
    /**
     * This method return activity class
     * @return class what contains function what do when collision start
     */
    public CollisionActivity getActivity() {
        return activity;
    }

    /**
     * This method set activity function to collision
     * @param activity Class implements CollisionActivity interface
     */
    public void setActivity(CollisionActivity activity) {
        this.activity = activity;
    }

    /**
     * This method set image to GameObject
     * @param src source of image support BMP,GIF,JPEG and PNG (information from javafx Image class because this class use it)
     */
    public void setImage(String src) {
        this.image = new Image(src, true);
    }

    /**
     * This Constructor set basic information for GameObject
     * @param X x position
     * @param Y y position
     * @param WIDTH width of object
     * @param HEIGHT height of object
     */
    public GameObject(double X, double Y, double WIDTH, double HEIGHT){
        setX(X);
        setY(Y);
        setWidth(WIDTH);
        setHeight(HEIGHT);
    }

    /**
     * This return y position of object
     * @return upper left y position
     */
    public double getY() {
        return y;
    }

    /**
     * set y position of obect
     * @param y position y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * This return x position of object
     * @return upper left x position
     */
    public double getX() {
        return x;
    }

    /**
     * set x position of obect
     * @param x position x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * get height of object
     * @return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * set height of object
     * @param height height information
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * get width of object
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * set width of object
     * @param width width information
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * This disable physics in this object
     */
    public void disablePhysics(){
        this.physics = false;
    }

    /**
     * This enable physics in this object
     */
    public void enablePhysics(){
        this.physics = true;
    }

    /**
     * This method tell is physics on in this object
     * @return true if physics on else it is false
     */
    public boolean getIsPhysics(){
        return this.physics;
    }

    /**
     * This set object's weight if it is more than 0
     * @param weig
     */
    public void setWeight(double weig){
        if(weig > 0.0){
            this.weight = weig;
        }
    }

    @Override
    public void draw(GameSystem gs) {
        if(this.image != null) {
            GraphicsContext content = gs.getGraphicsContext();
            content.drawImage(this.image, this.x, this.y, this.width, this.height);
        }
    }

    @Override
    public void setPressed(KeyEvent event) {
        this.press = event;
    }

    @Override
    public void setReleased(KeyEvent event) {
        this.release = event;
    }

    @Override
    public KeyEvent getPressed() {
        return this.press;
    }

    @Override
    public KeyEvent getReleased() {
        return this.release;
    }
}
