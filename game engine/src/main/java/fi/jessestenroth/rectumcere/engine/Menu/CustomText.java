package fi.jessestenroth.rectumcere.engine.Menu;
import fi.jessestenroth.rectumcere.engine.basic.ActionWhenKey;
import fi.jessestenroth.rectumcere.engine.basic.CanDrawToCanvas;
import fi.jessestenroth.rectumcere.engine.basic.GameSystem;
import fi.jessestenroth.rectumcere.engine.input.KeyEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * use example:
 *  public void start(GameSystem gs) {
 *  System.out.println("Start");
 *  CustomText text = new CustomText("fileName.ttf","Hello",100.0);
 *  text.setPosition(10,10);
 *  gs.addObjectToDrawing(text);
 * }
 * @author Jesse Stenroth
 * @version 0.2
 */
public class CustomText implements CanDrawToCanvas, ActionWhenKey {

    private Text text;
    private KeyEvent press;
    private KeyEvent release;


    /**
     * This Constructor get ttf file's url and font size
     * @param url ttf file's url in file system
     * @param t this is text where you want use font
     * @param size font's size
     */
    public CustomText(String url, String t, double size){
        this.text = new Text();
        this.text.setText(t);
        this.text.setX(0);
        this.text.setY(0);
        this.text.setFont(Font.loadFont(url, size));
        this.text.setFill(Color.BLACK);
    }

    /**
     * This method set fill color for text.
     * NOTICE default color is black
     * @param color Color is javafx object
     */
    public void setColor(Color color){
        this.text.setFill(color);
    }

    /**
     * This method return Text object of javafx
     * @return Text object of javafx with font
     */
    public Text getText(){
        return this.text;
    }
    /**
     * This methos change text.
     * @param tt new text
     */
    public void setText(String tt){
        this.text.setText(tt);
    }

    /**
     * This method set X position of text
     * @param x X position
     */
    public void setX(int x){
        this.text.setX(x);
    }

    /**
     * This method set Y position of text
     * @param y y position
     */
    public void setY(int y){
        this.text.setY(y);
    }

    /**
     * This method set position of text
     * @param x x position
     * @param y y position
     */
    public void setPosition(int x, int y){
        this.text.setX(x);
        this.text.setY(y);
    }

    public String toString(){
        return "text: " + getText().getText();
    }

    @Override
    public void draw(GameSystem gs) {
        GraphicsContext content = gs.getGraphicsContext();
        Text out =getText();
        content.setFont(out.getFont());
        content.setFill(out.getFill());
        content.fillText(out.getText(), out.getX(), out.getY());
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
