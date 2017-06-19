import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DeltaSmoothLine {
	public boolean IsDrawing;
	int RunningLength = 0;
	int cframe;
	float frac;
	int cx;
	int frames;
	int cy;
	int totalLength;
	int X1;
	GraphicsContext Graphic;
	int Y1;
	int X2;
	int Y2;
	int r;
	int b;
	int g;
	double a;
	public DeltaSmoothLine(GraphicsContext Graphic , int r , int g , int b, double a , int X1 ,int X2 , int Y1 , int Y2 , int frames) {
    	this.r = r;
    	this.g = g;
    	this.b = b;
    	this.a = a;
    	IsDrawing = false;
    	draw(Graphic,X1,X2,Y1,Y2,frames);
    }
	public DeltaSmoothLine(GraphicsContext Graphic,int X1 ,int X2 , int Y1 , int Y2 , int frames) {
		r = 0;
		g = 0;
		b = 0;
		a = 1.0;
		IsDrawing = false;
    	draw(Graphic,X1,X2,Y1,Y2,frames);
    }
	public void draw(GraphicsContext Graphic, int X1 ,int X2 , int Y1 , int Y2 , int frames){
		if(IsDrawing == false){
    	totalLength = (int) (Math.sqrt((X1-X2)^2 + (Y1-Y2)^2));
    	this.X1 = X1;
    	this.X2 = X2;
    	this.Y1 = Y1;
    	this.Y2 = Y2;
    	cframe = 0;
    	this.frames = frames; 
    	RunningLength = 0;
    	IsDrawing = true;
    	this.Graphic = Graphic;
		}
		
	}
	public void update(){
		Graphic.setStroke(Color.rgb(r, g, b, a));
		if(IsDrawing == true && cframe < frames ){
    		frac = (float)cframe/(float)frames;
    		cx = (int)((frac*X2)+(1-frac)*(X1));
    		cy = (int)((frac*Y2)+(1-frac)*(Y1));
    		System.out.println(cframe+"/"+frames);
    		RunningLength = (int) (Math.sqrt((X1-cx)^2 + (Y1-cy)^2));
    		Graphic.strokeLine(X1, Y1, cx, cy);
    		cframe++;
		}else{
			IsDrawing = false;
			Graphic.strokeLine(X1, Y1, X2, Y2);
		}
	}
}
