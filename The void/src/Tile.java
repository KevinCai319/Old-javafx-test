import java.awt.*;
import KeyHandler.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.awt.event.KeyListener;
//import javax.media.*;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Random;
import javax.swing.*;
public class Tile {
	private int x;
	private int y;
	private int ID;
	private boolean Solid;
	public Tile(int x, int y, int ID, boolean Solid){
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.Solid = Solid;
	}
	public Image renderImg(){
		File file = new File("src/"+ Integer.toString(ID) +".png");
    	String absolutePath = file.getAbsolutePath();
    	System.out.println(absolutePath);
		return new ImageIcon(absolutePath).getImage();
	}
	public Rectangle GetHitBox(){
		return new Rectangle(x,y,MainInit.TileSize,MainInit.TileSize);
	}
	public int GetID(){
		return ID;
	}
	public boolean GetSolid(){
		return Solid;
	}
}
