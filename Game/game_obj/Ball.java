package game_obj;

import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Ball extends JPanel{
    public ImageIcon[] imfire = new ImageIcon[5];
    public int y;
    public int x;
    public int count=0;
    public Ball(int x,int y){
        for(int i=0;i<imfire.length;i++){
            String imageLocation = "/game_image/b"+(i+1)+".png";
            imfire[i] = new ImageIcon(this.getClass().getResource(imageLocation));
	}
            this.x=x;
            this.y=y;
    }
	
    public void move(){
	this.x+=3;
    this.y = 600 ;
    }

    
    public Rectangle2D getbound(){
    	return (new Rectangle2D.Double(x,y,15,15));
    }
}

