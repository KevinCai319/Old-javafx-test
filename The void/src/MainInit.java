//import javax.media.*;
import java.awt.*;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.ArcType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import KeyHandler.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;
import java.awt.event.*;
import java.net.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import javax.swing.*;
import KeyHandler.*;
import java.util.concurrent.TimeUnit;
public class MainInit extends Application {//The main Frame class
	/**
	 * 
	 */
	public static JFrame frame;//initiates windowwith jframe
	public static boolean start = false;//checks if player sprite loaded
	public static final int TileSize = 40;//Size of tile by px.
	public static boolean IsNewMap = true;//checks if map is loaded
	public static Graphics h;//graphics for rendering stuff in general
	private static player Player_1;//1st player obj
	public static int level = 1;//starting level is 1
	private static final long serialVersionUID = 1L;//ignore
	private static ArrayList<Tile> TileList = new ArrayList<Tile>();
	private static ArrayList<Integer> TileDataID = new ArrayList<Integer>();
	private static ArrayList<Boolean> TileDataSolid = new ArrayList<Boolean>();
	static Scene window;
	static int cutsceneFrame = 0;
	static GraphicsContext gc;
	static Group root;
	static Canvas canvas;
	static ArrayList<DeltaSmoothLine> SmoothLines = new ArrayList<DeltaSmoothLine>();
	static ArrayList<CurvedSmoothLine> LineCurve = new ArrayList<CurvedSmoothLine>();
	static boolean DidStart = false;
    public static void main(String[] args) {
    	launch(args);
    }
    static HashSet<String> currentlyActiveKeys;
    @Override
    	public void start(Stage primaryStage) throws Exception {//Main code
    		// TODO Auto-generated method stub
    		primaryStage.setTitle("The Void");
    		root = new Group();
    	    canvas = new Canvas(1200, 600);
    	    gc = canvas.getGraphicsContext2D();
    	    root.getChildren().add(canvas);
    		window = new Scene(root);
    		primaryStage.setScene(window);
    		prepareActionHandlers();
    		primaryStage.initStyle(StageStyle.DECORATED);
    		primaryStage.show();
    		center(primaryStage);
    		sound Ambient = new sound();
    		drawShapes(gc);
    		new AnimationTimer()
            {
                public void handle(long currentNanoTime)
                {
                    tickAndRender();
                }
            }.start();
    		
    		//smooth(gc,0,0,500,500,1);
    		
    		
    	}
    private static void tickAndRender()
    {
        // clear canvas
        gc.clearRect(0, 0, 1200, 600);
        if(!DidStart){
        	IntroCutscene();
        	cutsceneFrame++;
        }
    }
    public static void IntroCutscene(){
    	//System.out.println("intro");
    	if(cutsceneFrame == 0){
    		smooth(1,500,1220,500,60);
    	}
    	if(cutsceneFrame == 30){
    		smooth(100,500,100,300,60);
    	}
    	if(cutsceneFrame == 65){
    		smooth(400,300,400,500,25);
    	}
    	if(cutsceneFrame == 75){
    		smooth(100,300,400,300,30);
    	}
    	if(cutsceneFrame == 95){
    		smooth(400,498,400,400,30,255,255,255,1.0);
    	}
    	if(cutsceneFrame == 95){
    		smooth(700,500,1000,400,30,0,0,0,0.5);
    	}
    	if(cutsceneFrame == 95){
    		smooth(1000,400,1200,400,30,0,0,0,0.5);
    	}
    	if(cutsceneFrame == 100){
    		addArc(10,110,30,30,45,100,45,1);
    	}
    	for(int i = 0; i < SmoothLines.size() ; i++ ){
    		System.out.println(SmoothLines.get(i).IsDrawing);
    		SmoothLines.get(i).update();
    		
    	}
    	for(int i = 0; i < LineCurve.size() ; i++ ){
    		System.out.println(LineCurve.get(i).IsDrawing);
    		LineCurve.get(i).update();
    		
    	}
    }
    private static void prepareActionHandlers()
    {
        // use a set so duplicates are not possible
        currentlyActiveKeys = new HashSet<String>();
        window.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                currentlyActiveKeys.add(event.getCode().toString());
            }
        });
        window.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                currentlyActiveKeys.remove(event.getCode().toString());
            }
        });
    }
    public void center(Stage primaryStage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }
    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240,ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                       new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                         new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                          new double[]{210, 210, 240, 240}, 4);
    }
    public static void setColor(int r,int g,int b , double a){
    	gc.setStroke(Color.rgb(r, g, b, a));
    }
    private static void smooth(int X, int Y, int X2, int Y2, int frames , int r,int g,int b , double a){
    	DeltaSmoothLine n = new DeltaSmoothLine(gc,r,g,b,a,X,X2,Y,Y2,frames);
    	System.out.println("tesdt");
    	SmoothLines.add(n);
    }
    private static void smooth(int X, int Y, int X2, int Y2, int frames){
    	gc.setStroke(Color.BLACK);
    	DeltaSmoothLine hhjki = new DeltaSmoothLine(gc,X,X2,Y,Y2,frames);
    	SmoothLines.add(hhjki);
    }
    private static void addArc(int r , int g , int b, double a , int X1 ,int X2 , int Y1 , int Y2 , int frames , int angle , int STangle,int dir){
    	CurvedSmoothLine lol = new CurvedSmoothLine(gc, r , g , b, a , X1 ,X2 , Y1 , Y2 , frames , angle ,  STangle,dir);
    	LineCurve.add(lol);
    }
    private static void addArc(int X1 ,int Y1 , int X2 , int Y2 , int frames , int angle , int STangle,int dir){
    	CurvedSmoothLine lol = new CurvedSmoothLine(gc,X1 ,Y1 , X2 , Y2 , frames , angle ,  STangle,dir);
    	LineCurve.add(lol);
    }

}