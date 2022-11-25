package game_component;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HomeGame extends JPanel{
    private ImageIcon feild = new ImageIcon(this.getClass().getResource("/game_image/home.png"));
	private ImageIcon exit = new ImageIcon(this.getClass().getResource("/game_image/exit.png"));
	private ImageIcon starts = new ImageIcon(this.getClass().getResource("/game_image/start.png"));
	public JButton BStart = new JButton(starts);
	public JButton BExit1  = new JButton(exit);
	public HomeGame(){
            setLayout(null);
            BExit1.setBounds(200, 350, 170,90);
            add(BExit1);
            add(BStart);
            BStart.setBounds(200,250,170,90);
            add(BStart);
	}
	public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(feild.getImage(),0,0,960,540,this);		
	}
}
