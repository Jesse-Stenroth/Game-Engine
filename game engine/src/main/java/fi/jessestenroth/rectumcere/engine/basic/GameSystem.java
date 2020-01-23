package fi.jessestenroth.rectumcere.engine.basic;

import fi.jessestenroth.rectumcere.engine.input.MousePressingAction;
import fi.jessestenroth.rectumcere.engine.physics.Directions;
import fi.jessestenroth.rectumcere.engine.physics.Gravity;
import fi.jessestenroth.rectumcere.engine.physics.ownPhysicsEngine.GravityPhysics;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is whole basic game system what contains code to use javafx in easy way in game.
 * Example of usage:
 * GameSystem gg = new GameSystem();
 * help h = new help(); //this class implements Game interface
 * gg.setGame(h);
 * gg.main(args);
 * @author Jesse Stenroth
 * @version 0.3
 */
public class GameSystem extends Application {
    private String t = "RectumcereEngineGame";
    private int h = 450;
    private int w = 500;
    private static Game g;
    private GraphicsContext gc;
    private Canvas canvas;
    private ArrayList<CanDrawToCanvas> ObjectList = new ArrayList<>();
    private MousePressingAction MouseClick;
    private Gravity gravity = new GravityPhysics();
    /**
     * This is deltaTime
     * time what gone from last frame
     */
    public double deltaTime = 1.0;
    private double currentTime, lastTime;

    /**
     * This Constructor is default constructor.
     */
    public GameSystem(){
       canvas = new Canvas(this.w, this.h);
       gc = canvas.getGraphicsContext2D();
    }

    /**
     * Get GraphicsContext NOTICE THIS IS JAVAFX CLASS
     * @return GraphicsContext of game content
     */
    public GraphicsContext getGraphicsContext() {
        return gc;
    }

    /**
     * This method set class to memory
     * @param g class what implements Game interface
     */
    public void setGame(Game g) {
        this.g = g;
    }

    /**
     * This method set width of window
     * @param w width of window
     */
    public void setWidth(int w) {
        this.w = w;
    }

    /**
     * This method set title of window
     * @param t title of window
     */
    public void setTitle(String t) {
        this.t = t;
    }

    /**
     * This method set height of window
     * @param h height of window
     */
    public void setHeight(int h) {
        this.h = h;
    }

    /**
     * with this method you can put your own gravity to game
     * @param engine class what implements Gravity interface
     */
    public void setOwnPhysicsGravity(Gravity engine){
        this.gravity = engine;
    }

    /**
     * This method must be called by your main method.
     * example (help is class what implements Game interface):
     *         help h = new help();
     *         GameSystem gg = new GameSystem();
     *         gg.setGame(h);
     *         gg.main(args);
     * @param args args from main method's args
     */
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(this.t);
        stage.setResizable(false);
        canvas.setWidth(this.w);
        canvas.setHeight(this.h);
        g.start(this);
        drawObjects();
        Pane root = new Pane();
        root.getChildren().add(canvas);
        Scene s = new Scene(root);
        //key actions
        s.setOnKeyPressed(event -> {
            ObjectList.forEach(i -> {
                if(i instanceof ActionWhenKey){
                    ActionWhenKey a = (ActionWhenKey) i;
                    if(a.getPressed() != null) {
                        a.getPressed().event(event.getCode());
                    }
                }
            });
        });
        s.setOnKeyReleased(event -> {
            ObjectList.forEach(i -> {
                if(i instanceof ActionWhenKey){
                    ActionWhenKey a = (ActionWhenKey) i;
                    if(a.getReleased() != null) {
                        a.getReleased().event(event.getCode());
                    }
                }
            });
        });
        //key actions end
        //mouse actions
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getSceneX();
                double y = event.getSceneY();
                ObjectList.forEach(e -> {
                    if(e instanceof GameObject){
                        GameObject obj = (GameObject) e;
                        double helpX = obj.getX();
                        double helpY = obj.getY();
                        double wi = obj.getWidth();
                        double he = obj.getHeight();
                        if((x >= helpX && x <= (helpX+wi)) && (y >= helpY && y <= (helpY + he))){
                            obj.getActionWhenClicked().action();
                        }
                    }
                });
                if(MouseClick != null){
                    MouseClick.action(x,y);
                }
            }
        });
        //mouse actions end
        stage.setScene(s);
        //game loop
        GameSystem help = this;
        Timer randomTimer = new Timer();
        randomTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    gc.clearRect(0,0,w,h);
                    //delta
                    currentTime = (double)System.nanoTime()/(double)1000000000L;
                    deltaTime = currentTime - lastTime;
                    //delta end
                    GravityUpdate();
                    checkCollision();
                    g.update(help);
                    drawObjects();
                    //current time to last time
                    lastTime = currentTime;
                });
            }
        }, 0, 10);
        stage.show();
    }

    /**
     * This method check every game object and move them if necessary
     */
    private void GravityUpdate() {
        ObjectList.forEach(e -> {
            if(e instanceof GameObject){
                GameObject help12 = (GameObject) e;
                if(help12.getIsPhysics()){
                    gravity.move(help12, Directions.DOWN, deltaTime);
                }
            }
        });
    }

    /**
     * This method add Object to drawing list
     * @param o Object what implements CanDrawToCanvas interface
     */
    public void addObjectToDrawing(CanDrawToCanvas o){
        this.ObjectList.add(o);
    }

    /**
     * This method draw every item from ObjectList
     */
    private void drawObjects(){
        GameSystem system = this;
        ObjectList.forEach(e -> {
            e.draw(system);
        });
    }

    /**
     * This method add mouse clicked action in game
     * @param action class what implements MousePressingAction interface
     */
    public void setClickedAction(MousePressingAction action){
        this.MouseClick = action;
    }

    /**
     * This method check collisions
     */
    private void checkCollision(){
        ArrayList<GameObject> helpList = new ArrayList<>();
        ObjectList.forEach(e -> {
            if( e instanceof GameObject){
                helpList.add((GameObject) e);
            }
        });
        helpList.forEach(e -> {
            ObjectList.forEach(t -> {
                //if t is GameObject and e is not same object that t
                if(t instanceof GameObject && e != t){
                   GameObject help = (GameObject) t;
                   //if area is in other area
                   if(((help.getX() >= e.getX() && help.getX() <= (e.getX() + e.getWidth())) || ((help.getX() + help.getWidth()) >= e.getX()) && ((help.getX() + help.getWidth()) <= (e.getX() + e.getWidth()))) && (help.getY() >= e.getY() && help.getY() <= (e.getY() + e.getHeight()) || ((help.getY() + help.getHeight()) >= e.getY() && (help.getY() + help.getHeight()) <= (e.getY() + e.getHeight())))){
                       //disble physics
                       if(!e.getIsPhysics() || !help.getIsPhysics()) {
                           e.disablePhysics();
                           help.disablePhysics();
                       }
                       try {
                           e.getActivity().activity(e, help);
                       } catch (Exception r){
                           System.out.println("maybe the method is missing");
                       }
                   }
                }
            });
        });
    }
    @Override
    public void stop(){
        g.close(this);
    }
}
