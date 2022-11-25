import game_component.HomeGame;
import game_component.PanelGame;

//import game_component.PanelGame;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main extends JFrame implements ActionListener{
    HomeGame hg1 = new HomeGame();
    PanelGame g1 = new PanelGame();
    //PanelGame panelGame = new PanelGame();
    public Main(){
        this.setSize(960, 540);
        this.add(hg1);
        hg1.BExit1.addActionListener(this);
        hg1.BStart.addActionListener(this);
        g1.BPause.addActionListener(this);
        g1.Bresum.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hg1.BStart) {
            this.setLocationRelativeTo(null);
            this.remove(hg1);
            this.setSize(1300, 800);
            this.add(g1);
            g1.requestFocusInWindow();
            g1.timestart = false;
            g1.scor = 0;
            g1.HP = 3;
            g1.times = 100;
            g1.startball = false;
            g1.timestart = false;
        }else if (e.getSource() == hg1.BExit1) {
            System.exit(0);
        }
        this.validate();
        this.repaint();
    }
    public static void main(String[] args) {
        JFrame jf = new Main();
        jf.setSize(960, 540);
        jf.setTitle("Animation Example");
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
    }
}
