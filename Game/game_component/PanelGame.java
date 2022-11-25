package game_component;
import game_obj.Ball;
import game_obj.Player;
import game_obj.Shark;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;


public class PanelGame extends JPanel implements ActionListener{
   
    private ImageIcon image = new ImageIcon(this.getClass().getResource("/game_image/gameover1.png"));
    private final ImageIcon imgstate1 = new ImageIcon(this.getClass().getResource("/game_image/state1.jpg"));
    private final ImageIcon imgstate2 = new ImageIcon(this.getClass().getResource("/game_image/state2.jpg"));
    private final ImageIcon pause = new ImageIcon(this.getClass().getResource("/game_image/p.png"));
    private final ImageIcon resum = new ImageIcon(this.getClass().getResource("/game_image/play.png"));

    Player m = new Player();

    HomeGame hg = new HomeGame();
    ImageIcon feildover = new ImageIcon(this.getClass().getResource("/game_image/gameover1.png"));
    ImageIcon exitover = new ImageIcon(this.getClass().getResource("/game_image/exit.png"));
    ImageIcon restart = new ImageIcon(this.getClass().getResource("/game_image/start.png"));
    JButton BStartover = new JButton(restart);
    JButton BExitover = new JButton(exitover);

    private JLabel score = new JLabel();
    public JButton BPause = new JButton(pause);
    public JButton Bresum = new JButton(resum);

    public ArrayList<Ball> ball = new ArrayList<Ball>();
    public ArrayList<Shark> sharks = new ArrayList<Shark>();
    public int times;
    public int HP = 30;
    public int rs1 = 1;
    public int rs2 = 2;
    public int amount = 50;
    public boolean timestart = true;
    public boolean startball = false;

    private GameOver gameover = new GameOver();
    public int scor = 0;
    boolean paralyze1 = false;
    int time_paralyze = 5;

    Thread time = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }

                if (timestart == false) {
                    repaint();
                }
            }
        }
    });

    Thread actor = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                }
                repaint();
            }
        }
    });

    Thread tballs1 = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    if (startball == false) {
                        Thread.sleep((long) (Math.random() * 10000) + 2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (startball == false) {
                    sharks.add(new Shark());
                }
            }
        }
    });

    Thread t = new Thread(new Runnable() {
        public void run() {
            while (true) {
                if (timestart == false) {
                    times = (times - 1);
                    if (paralyze1) {
                        time_paralyze--;
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    public PanelGame() {
        this.setFocusable(true);
        this.setLayout(null);
        BPause.setBounds(850, 50, 40, 40);
        Bresum.setBounds(900, 50, 40, 40);
        BPause.addActionListener(this);
        Bresum.addActionListener(this);
        this.add(BPause);
        this.add(score);
        this.add(Bresum);

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int a = e.getKeyCode();
                if (!paralyze1) {
                    if (a == KeyEvent.VK_A) {
                        if(m.x>0){m.x -= 10;}
                    } else if (a == KeyEvent.VK_D) {
                        if(m.x<1100){m.x += 10;}
                    }
                    if (m.count > 3) {
                        //m.count = 0;
                    } else if (a == KeyEvent.VK_UP || a == KeyEvent.VK_SPACE) {
                        m.count = 5;
                        if(amount>0){
                            for(int i=0;i<2;i++){
                                m.count = i;
                            }
                            ball.add(new Ball(m.x+100, m.y)); 
                            amount--;// 
                        }
                    }
                }
            }
            public void keyReleased(KeyEvent e) {
                m.count = 0;
            }
        });
        m.x = 400;
        time.start();
        actor.start();
        t.start();
        tballs1.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (times <= 0 || HP <= 0) 
        {
            this.remove(BPause);
            this.remove(Bresum);
            this.setLayout(null);
            g.drawImage(feildover.getImage(), 0, 0, 1300, 800, this);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 40));
            g.drawString("SCORE   " + scor, 380, 200);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 70));
            g.drawString("GAME OVER", 290, 150);
        } 
        else if (times <= 80)
        {
            g.drawImage(imgstate2.getImage(), 0, 0, 1300, 800, this);

            if (paralyze1) {

            } else {
                g.drawImage(m.im[m.count].getImage(), m.x, 600, 137, 79, this);
            }

            if (m.x < 0) {
                m.x = this.getWidth() - 10;
            }
            if (m.x > this.getWidth()) {
                m.x = 20;
            }

            for (int i = 0; i < ball.size(); i++) {
                Ball b = ball.get(i);
                g.drawImage(b.imfire[b.count % 5].getImage(), b.x, b.y, 25, 25, null);
                b.move();
                b.count++;
                if (b.y < 0) {
                    ball.remove(i);
                }
            }
            //วาดซอมบี้
            for (int i = 0; i < sharks.size(); i++) {
                g.drawImage(sharks.get(i).getImage(), sharks.get(i).getX(), sharks.get(i).getY(), 159, 111, this);
            }
            //ทำดาเมจ zonbie
            for (int i = 0; i < ball.size(); i++) {
                for (int j = 0; j < sharks.size(); j++) {
                    if (Intersect(ball.get(i).getbound(), sharks.get(j).getbound())) { //intersect การชน

                        int HPzombie = sharks.get(j).getHP();
                        
                        if(HPzombie>1){
                            ball.remove(i);
                            sharks.get(j).setHP(HPzombie-1);
                        }else{
                            ball.remove(i);
                            sharks.remove(j);
                        }

                        scor += 10;
                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }

            
            for (int j = 0; j < sharks.size(); j++) {
                if (Intersect(sharks.get(j).getbound(),m.getbound() )) {
                    sharks.get(j).setX(sharks.get(j).getX()+50);
                    HP=HP-1;
                }
            }


            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 30));
            g.setColor(Color.WHITE);
            g.drawString("SCORE =  " + scor, 50, this.getHeight() - 10);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 50));
            g.drawString("Time " + times, this.getWidth() - 200, this.getHeight() - 50);
            g.setColor(Color.WHITE);
            g.drawString("HP  " + HP, 50, this.getHeight() - 50);

        } 
        else if (times <= 0 || HP <= 0) // ตาย และหมดเวลา
        {
            this.remove(BPause);
            this.remove(Bresum);
            this.setLayout(null);
            g.drawImage(feildover.getImage(), 0, 0, 1300, 800, this);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 40));
            g.drawString("SCORE   " + scor, 380, 200);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 70));
            g.drawString("GAME OVER", 290, 150);
        } 
        else
        {
            g.drawImage(imgstate1.getImage(), 0, 0, 1300, 800, this);
            if (paralyze1) {

            } else {
                g.drawImage(m.im[m.count].getImage(), m.x, 600, 137, 79, this);
            }

            if (m.x < 0) {
                m.x = this.getWidth() - 10;
            }
            if (m.x > this.getWidth()) {
                m.x = 20;
            }

            for (int i = 0; i < ball.size(); i++) {
                Ball ba = ball.get(i);
                g.drawImage(ba.imfire[ba.count % 5].getImage(), ba.x, ba.y, 25, 25, null);
                ba.move();
                ba.count++;
                if (ba.y < 0) {
                    ball.remove(i);
                }
            }
            //========================================ball1================= 
            for (int i = 0; i < sharks.size(); i++) {
                g.drawImage(sharks.get(i).getImage(), sharks.get(i).getX(), sharks.get(i).getY(), 159, 111, this);
            }
            //ทำดาเมจ zonbie
            for (int i = 0; i < ball.size(); i++) {
                for (int j = 0; j < sharks.size(); j++) {
                    if (Intersect(ball.get(i).getbound(), sharks.get(j).getbound())) {

                        int HPzombie = sharks.get(j).getHP();
                        
                        if(HPzombie>1){
                            ball.remove(i);
                            sharks.get(j).setHP(HPzombie-1);
                        }else{
                            ball.remove(i);
                            sharks.remove(j);
                        }

                        scor += 10;
                        g.drawString("+10", m.x + 100, 650);
                    }
                }
            }


            //ชนคน
            for (int j = 0; j < sharks.size(); j++) {
                if (Intersect(sharks.get(j).getbound(),m.getbound() )) {
                    sharks.get(j).setX(sharks.get(j).getX()+50);
                    HP=HP-1;
                }
            }


            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 30));
            g.setColor(Color.WHITE);
            g.drawString("SCORE =  " + scor, 50, this.getHeight() - 10);
            g.setFont(new Font("Hobo Std", Font.HANGING_BASELINE, 50));
            g.drawString("Time " + times, this.getWidth() - 200, this.getHeight() - 50);
            g.setColor(Color.WHITE);
            g.drawString("HP  " + HP, 50, this.getHeight() - 50);
        }
    }

    public boolean Intersect(Rectangle2D a, Rectangle2D b) {
        return (a.intersects(b));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BStartover) {
            this.setSize(100, 800);
            this.add(hg);
            this.setLocation(null);
            timestart = true;
            startball = true;
        } else if (e.getSource() == BExitover) {
            System.exit(0);
        }
    }

}
