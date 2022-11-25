package game_obj;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
public class Player {
    public ImageIcon[] im = new ImageIcon[2];
    public int x;
    public int y=600;
    public int w=137;
    public int h=79;

    public int count = 0;

    public Player(){
        for(int i=0;i<im.length;i++){
            im[i] = new ImageIcon(this.getClass().getResource("/game_image/player"+(i+1)+".png"));
        }
    }

    void Fireball(int x,int y){
        for(int i=0;i<im.length;i++){
            String imageLocation = "/game_image/b"+(i+1)+".png";
            im[i] = new ImageIcon(this.getClass().getResource(imageLocation));
            }
            this.x=x;
            this.y=y;
    }

    public void move(){
        this.x+=1;
        this.y = 600 ;
    }

    public Rectangle2D getbound(){
        return (new Rectangle2D.Double(x,y,this.w,this.h));
    }

    public int getX(){
        return this.x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return this.y;
    }
    public int getW(){
        return this.w;
    }
    public int getH(){
        return this.h;
    }
}

