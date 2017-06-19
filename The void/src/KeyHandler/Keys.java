package KeyHandler;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Keys extends KeyAdapter{
	public static boolean L;//static so all classes can acess this, no need for multiple KeySensors
	public static boolean R;
	public static boolean U;
	public static boolean D;
	public static boolean IsPressing;
    public void keyPressed(KeyEvent event) {
		IsPressing = true;
	    int key = event.getKeyCode();
	    switch(key) {
	    case KeyEvent.VK_LEFT :
	    	L = true;
	    	break;
	    
	    case KeyEvent.VK_RIGHT :
	    	R = true;
	    	break;
	    case KeyEvent.VK_UP :
	    	U = true;
	    	break;
	    
	    case KeyEvent.VK_DOWN :
	    	D = true;
	    	break;
	    }

    }
    public void keyReleased(KeyEvent event){
    	IsPressing = true;
	    int key = event.getKeyCode();
	    switch(key) {
	    case KeyEvent.VK_LEFT :
	    	L = false;
	    	break;
	    
	    case KeyEvent.VK_RIGHT :
	    	R = false;
	    	break;
	    case KeyEvent.VK_UP :
	    	U = false;
	    	break;
	    
	    case KeyEvent.VK_DOWN :
	    	D = false;
	    	break;
	    }
    }

}
