import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class CurvedSmoothLine {
	public boolean IsDrawing;
	int RunningLength = 0;
	int cframe;
	float frac;
	int anglePercent;
	int frames;
	int totalLength;
	int X1;
	int angle;
	GraphicsContext Graphic;
	int Y1;
	int X2;
	int Y2;
	int STangle;
	int r;
	int b;
	int g;
	int dir;
	double a;
	public CurvedSmoothLine(GraphicsContext Graphic , int r , int g , int b, double a , int X1 ,int X2 , int Y1 , int Y2 , int frames , int angle , int STangle,int dir) {
    	Graphic.setStroke(Color.rgb(r, g, b, a));
    	IsDrawing = false;
    	draw(Graphic,X1,X2,Y1,Y2,frames,angle,STangle,dir);
    }
	public CurvedSmoothLine(GraphicsContext Graphic,int X1 ,int Y1 , int X2 , int Y2 , int frames, int angle, int STangle,int dir) {
		r = 0;
		g = 0;
		b = 0;
		a = 1.0;
		IsDrawing = false;
    	draw(Graphic,X1,X2,Y1,Y2,frames,angle,STangle,dir);
    }
	public void draw(GraphicsContext Graphic, int X1 ,int X2 , int Y1 , int Y2 , int frames,int angle, int STangle , int dir){
		if(IsDrawing == false){
    	totalLength = (int) (Math.sqrt((X1-X2)^2 + (Y1-Y2)^2));
    	this.X1 = X1;
    	this.X2 = X2;
    	this.Y1 = Y1;
    	this.Y2 = Y2;
    	this.dir = dir;
    	this.angle = angle;
    	this.STangle = STangle;
    	cframe = 0;
    	this.frames = frames; 
    	RunningLength = 0;
    	IsDrawing = true;
    	this.Graphic = Graphic;
		}
		
	}
	public void update(){
		if(IsDrawing == true && cframe < frames ){
    		frac = (float)cframe/(float)frames;
    		if(dir == 0){
    			anglePercent = (int)(angle*frac);
    			Graphic.strokeArc(X1, Y1, X2, Y2, STangle+angle, anglePercent, ArcType.OPEN);
    		}else{
    			anglePercent = (int)(angle*frac);
    			Graphic.strokeArc(X1, Y1, X2, Y2, STangle, anglePercent, ArcType.OPEN);
    		}
    		System.out.println(cframe+"/"+frames);
    		cframe++;
    		System.out.println(IsDrawing == true && cframe < frames);
		}else{
			IsDrawing = false;
			if(dir == 0){
				Graphic.strokeArc(X1, Y1, X2, Y2, STangle+angle, angle, ArcType.OPEN);
			}else{
				anglePercent = (int)(angle*frac);
				Graphic.strokeArc(X1, Y1, X2, Y2, STangle, angle, ArcType.OPEN);
			}
		}
		
	}
}
