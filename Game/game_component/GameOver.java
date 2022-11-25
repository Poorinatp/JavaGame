package game_component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Graphics;

public class GameOver extends JPanel{
	
	private ImageIcon feildover = new ImageIcon("/game_image/gameover1.png");
	private ImageIcon exitover = new ImageIcon("/game_image/exit.png");
	private ImageIcon restart = new ImageIcon("/game_image/start.png");
	public JButton BStartover = new JButton(restart);
	public JButton BExitover  = new JButton(exitover);
	
	public GameOver(){
		this.setLayout(null);
		BExitover.setBounds(500, 650, 150,90);
		add(BExitover);
		add(BStartover);
		BStartover.setBounds(750,650,150,90);
                add(BStartover);
		
	}
	public void paintComponent(Graphics g){
		  super.paintComponent(g);
		  g.drawImage(feildover.getImage(),0,0,1000,800,this);
	  }
}
