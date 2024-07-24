/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Rimóczi Loránd EOH12I
 */

public class GameEngine extends JPanel implements KeyListener, ActionListener
{
    
    //pozíció tömbök
    private int[] snakeXLength = new int[32]; //32-szer a 25
    private int[] snakeYLength = new int[32];
    
    private int[] objectX = {25,50,75,100,125,150,175,200,225,250,275,300,325,
        350,375,400,425,450,475,500,525,550,575,600,
        625,650,675,700,725,750,775,800};
    private int[] objectY = {25,50,75,100,125,150,175,200,225,250,275,300,325,
        350,375,400,425,450,475,500,525,550,575,600};
    
    //kövek pozíciói tömbök
    private ArrayList<Integer> stonesX = new ArrayList<>();
    private ArrayList<Integer> stonesY = new ArrayList<>();
    
    //irány logikai értékek
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    
    //játék megállításához logikai érték
    private boolean paused = false;
    
    //kezdő kígyó hossza
    private int lengthOfSnake = 2;
    
    //pontszám
    private int score = 0;
    
    //kezdéshez irány pozíció
    private String[] positions = {"right","left","up","down"};
    
    //random kő és gyümölcs
    private Random random = new Random();
    private int fruitX = random.nextInt(32);
    private int fruitY = random.nextInt(24);
    private int stoneX = random.nextInt(32);
    private int stoneY = random.nextInt(24);
    
    private int idX;
    private int idY;
    
    //kezdő irányhoz random érték
    private int begin = getRandomNumber(0, 3);
    
    //képforrásokhoz
    private ImageIcon fruit;
    private ImageIcon body;
    private ImageIcon tail;
    private ImageIcon headRight;
    private ImageIcon headLeft;
    private ImageIcon headUp;
    private ImageIcon headDown;
    private ImageIcon backGround;
    
    private Image stoneImage;
    
    private ArrayList<ImageIcon> stonesI = new ArrayList<>();
    
    //időméréshez
    private Timer newFrameTimer;
    private int delay = 100;
    
    private int moves = 0;
    
    //adatbázis
    private Databases data;
    
    //időmérő
    private static int time = 0;
    private Timer timer = new Timer(1000, new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {    
            time++;
        }
    });
    
    //konstruktor
    public GameEngine(Databases data)
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        newFrameTimer = new Timer(delay, this);
        newFrameTimer.start();
        timer.start();
        this.data = data;
    }
    
    //játék rajzolása
    public void paint(Graphics g)
    {
        if(moves == 0)
        {
            paused = false;
            
            fruitX = random.nextInt(32);
            fruitY = random.nextInt(24);
            
            begin = getRandomNumber(0, 3);
            
            if(positions[begin] == "right")
            {
                right = true;
                moves++;
                headRight = new ImageIcon("src/pictures/headright.png");
                headRight.paintIcon(this,g,snakeXLength[0],snakeYLength[0]);
            }
            if(positions[begin] == "left")
            {
                left = true;
                moves++;
                headLeft = new ImageIcon("src/pictures/headLeft.png");
                headLeft.paintIcon(this,g,snakeXLength[0],snakeYLength[0]);
            }
            if(positions[begin] == "up")
            {
                up = true;
                moves++;
                headUp = new ImageIcon("src/pictures/headUp.png");
                headUp.paintIcon(this,g,snakeXLength[0],snakeYLength[0]);
            }
            if(positions[begin] == "down")
            {
                down = true;
                moves++;
                headDown = new ImageIcon("src/pictures/headDown.png");
                headDown.paintIcon(this,g,snakeXLength[0],snakeYLength[0]);
            }
            
            while(stonesX.size() < 10)
            {
                 
                idX = random.nextInt(32);
                idY = random.nextInt(24);
                if(!stonesX.contains(objectX[idX]) && !stonesY.contains(objectX[idY]))
                {
                    stonesX.add(objectX[idX]);
                    stonesY.add(objectX[idY]);
                }
            }
            
            snakeXLength[1] = 375;
            snakeXLength[0] = 400;
            
            snakeYLength[1] = 300;
            snakeYLength[0] = 300;
            
            
        }
        
        //pálya és felület rajzolása
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 865, 660);
        
        g.setColor(Color.WHITE);
        g.drawRect(24, 24, 801, 601);
        
        backGround = new ImageIcon("src/pictures/background.png");
        g.drawImage(backGround.getImage(), 25, 25, this);
        
        g.setColor(Color.YELLOW);
        g.setFont(new Font("arial",Font.PLAIN, 16));
        g.drawString("Score: " + score, 50, 18);
        
        g.setColor(Color.YELLOW);
        g.setFont(new Font("arial",Font.PLAIN, 16));
        g.drawString("Length: " + lengthOfSnake, 200, 18);
        
        g.setColor(Color.YELLOW);
        g.setFont(new Font("arial",Font.PLAIN, 16));
        g.drawString("Time: " + time + " sec", 400, 18);
        
        
        
        //kígyó rajzolása, megfelől irányú fej változatok
        for(int i = 0; i < lengthOfSnake; i++)
        {
            if(i==0 && right)
            {
                headRight = new ImageIcon("src/pictures/headright.png");
                headRight.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
            }
            if(i==0 && left)
            {
                headLeft = new ImageIcon("src/pictures/headLeft.png");
                headLeft.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
            }
            if(i==0 && up)
            {
                headUp = new ImageIcon("src/pictures/headUp.png");
                headUp.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
            }
            if(i==0 && down)
            {
                headDown = new ImageIcon("src/pictures/headDown.png");
                headDown.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
            }
            
            if(i!=0 && i != lengthOfSnake - 1)
            {
                body = new ImageIcon("src/pictures/body.png");
                body.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
            }
            
            if(i == lengthOfSnake - 1)
            {
                tail = new ImageIcon("src/pictures/tail.png");
                tail.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
            }
        }
        
        //kövek rajzolása
        for(int s = 0; s < 10; s++)
        {
            
            stoneImage = new ImageIcon("src/pictures/rock.png").getImage();
            g.drawImage(stoneImage, (int)stonesX.get(s), (int)stonesY.get(s), this);
        }
        
        //étel rajzolása
        fruit = new ImageIcon("src/pictures/fruit.png");
        fruit.paintIcon(this, g, objectX[fruitX], objectY[fruitY]);
        
        //étellel való találkozás, új étel elhelyezés
        if((objectX[fruitX] == snakeXLength[0] && objectY[fruitY] == snakeYLength[0]))
        {
            score++;
            lengthOfSnake++;
            fruitX = random.nextInt(32);
            fruitY = random.nextInt(24);
        }
        
        //kő érintés
        for(int s = 0; s < 10; s++)
        {
            if((stonesX.get(s) == snakeXLength[0] && stonesY.get(s) == snakeYLength[0]))
            {
                ////pontszám kezelése miatt előbb sima pause, utána a pontszám kezelése, maj utána 0ázás
                paused = true;
                
                String input = JOptionPane.showInputDialog("Sajnos vesztettél!\n" + score + " pontot értél el.\n" + "Add meg a neved a pontszám mentéséhez!\n");
                data.storeHighScore(input, score);
                paintEnd(g);
            }
        }
        
        //önmagába harapás
        for(int b = 1; b < lengthOfSnake; b++)
        {
            if(snakeXLength[b] == snakeXLength[0] && snakeYLength[b] == snakeYLength[0])
            {
                //pontszám kezelése miatt előbb sima pause, utána a pontszám kezelése, maj utána 0ázás
                paused = true;
                
                String input = JOptionPane.showInputDialog("Sajnos vesztettél!\n" + score + " pontot értél el.\n" + "Add meg a neved a pontszám mentéséhez!\n");
                data.storeHighScore(input, score);
                paintEnd(g);
            }
        }
        
        //pálya széle
        if(snakeXLength[0] > 800 || snakeXLength[0] < 25 || 
                snakeYLength[0] > 600 || snakeYLength[0] < 25)
        {
            ////pontszám kezelése miatt előbb sima pause, utána a pontszám kezelése, maj utána 0ázás
            paused = true;
            
            String input = JOptionPane.showInputDialog("Sajnos vesztettél!\n" + score + " pontot értél el.\n" + "Add meg a neved a pontszám mentéséhez!\n");
            data.storeHighScore(input, score);
            paintEnd(g);
        }
        
        g.dispose();
    }
    
    //gépelés, hibakezelés
    @Override
    public void keyTyped(KeyEvent e) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    //gombnyomásra reagálás, WASD és nyilak esetén
    @Override
    public void keyPressed(KeyEvent e) 
    {
        //ESC billentyű szüneteltetés
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            paused = !paused;
        }
        //SPACE újraindít
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            restart();
        }
        
        //irány beállítása a WASD és nyíl billentyűkkel
        if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            moves++;
            if(!left)
            {
                right = true;
                left = false;
                up= false;
                down = false;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            moves++;
            if(!right)
            {
                right = false;
                left = true;
                up= false;
                down = false;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
        {
            moves++;
            if(!down)
            {
                right = false;
                left = false;
                up= true;
                down = false;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            moves++;
            if(!up)
            {
                right = false;
                left = false;
                up= false;
                down = true;
            }
        }
    }
    
    //kötelező absztrakt override, és hibakezelés
    @Override
    public void keyReleased(KeyEvent e) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    //kígyó mozgatása
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(!paused)
        {
            if(right)
            {
                for(int r = lengthOfSnake - 1; r >= 0; r--)
                {
                    snakeYLength[r+1] = snakeYLength[r];
                }
                for(int r = lengthOfSnake; r >= 0; r--)
                {
                    if(r==0)
                    {
                        snakeXLength[r] = snakeXLength[r] + 25;
                    }
                    else
                    {
                        snakeXLength[r] = snakeXLength[r-1];
                    }
                }
            }
            if(left)
            {
                for(int r = lengthOfSnake - 1; r >= 0; r--)
                {
                    snakeYLength[r+1] = snakeYLength[r];
                }
                for(int r = lengthOfSnake; r >= 0; r--)
                {
                    if(r==0)
                    {
                        snakeXLength[r] = snakeXLength[r] - 25;
                    }
                    else
                    {
                        snakeXLength[r] = snakeXLength[r-1];
                    }
                }
            }
            if(down)
            {
                for(int r = lengthOfSnake - 1; r >= 0; r--)
                {
                    snakeXLength[r+1] = snakeXLength[r];
                }
                for(int r = lengthOfSnake; r >= 0; r--)
                {
                    if(r==0)
                    {
                        snakeYLength[r] = snakeYLength[r] + 25;
                    }
                    else
                    {
                        snakeYLength[r] = snakeYLength[r-1];
                    }
                }
            }
            if(up)
            {
                for(int r = lengthOfSnake - 1; r >= 0; r--)
                {
                    snakeXLength[r+1] = snakeXLength[r];
                }
                for(int r = lengthOfSnake; r >= 0; r--)
                {
                    if(r==0)
                    {
                        snakeYLength[r] = snakeYLength[r] - 25;
                    }
                    else
                    {
                        snakeYLength[r] = snakeYLength[r-1];
                    }
                }
            }
            repaint(); 
        }
    }
    
    //random szám generálás
    public int getRandomNumber(int min, int max) 
    {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    //vereség esetén történő game over felirat
    public void paintEnd(Graphics g)
    {
        //biztos ami biztos
        paused = true;
        
        right = false;
        left = false;
        up = false;
        down = false;
        
        stonesX.clear();
        stonesY.clear();
                
        moves = 0;
        score = 0;
        lengthOfSnake = 2;
        time = 0;
        
        g.setColor(Color.red);
        g.setFont(new Font("arial", Font.BOLD, 50));
        g.drawString("Game Over", 300, 300);
                
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("Space az újraindításhoz", 340, 340);
        
        g.dispose();
    }
    
    //újraindítás
    public void restart()
    {
        time = 0;
        moves = 0;
        score = 0;
        lengthOfSnake = 2;
        stonesX.clear();
        stonesY.clear();
        right = false;
        left = false;
        up = false;
        down = false;
                
        repaint();
    }
}

