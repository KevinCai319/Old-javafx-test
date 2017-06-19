import java.awt.Rectangle;
import KeyHandler.*;
public class player {
	public int x;
	//private static Keys lol = new Keys();
	public int y;
	private int Xchange = 0;
	private int Ychange = 0;
	public boolean CanChange;
	private int lives;
	private double health;
	private double DMG;
	public int Speed;
	private double coinage;
	public player(){
		health = 100;
		lives = 5;
		CanChange = false;
		DMG = 20;
		Speed = 1;
		x = 600;
		y = 280;
		CanChange = false;
		coinage = 0;
		System.out.println("Player Created ...");
		
	}
	public void ded(){
		lives -=1;
		health = 100;
		CanChange = false;
		DMG = 20;
		Speed = 1;
	}
	public void SetMovement(Rectangle NewHitBox){
		if(CanChange){
			x = NewHitBox.x;
			y = NewHitBox.y;
			CanChange = false;
		}
	}
	public Rectangle FutureHitBox(){
		if(Keys.IsPressing == true){
			Xchange = 0;
			Ychange = 0;
			Xchange = Keys.L ? Keys.R ? 0:-1:Keys.R ? 1:0;
			Ychange = Keys.U ? Keys.D ? 0:-1:Keys.D ? 1:0;
			CanChange = true;
			return new Rectangle(x+Speed*Xchange,y+Speed*Ychange,MainInit.TileSize, MainInit.TileSize);
		}else{
			return HitBox();
		}
	}
	
	public Rectangle HitBox(){
		return new Rectangle(x, y, MainInit.TileSize, MainInit.TileSize);
	}
}
