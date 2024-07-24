/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 *
 * @author Rimóczi Loránd EOH12I
 */

public class Frame extends JFrame
{
    private JFrame frame;
    private GameEngine gameArea;
    private Databases data = new Databases();
    
    public Frame()
    {
        frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameArea = new GameEngine(data);
        frame.getContentPane().add(gameArea);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu Game = new JMenu("Játék");
        menuBar.add(Game);
        
        JMenuItem newMenuItem = new JMenuItem("Új játék");
        Game.add(newMenuItem);
        newMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.restart();
            }
        });
        
        JMenuItem highScore = new JMenuItem("Ranglista");
        Game.add(highScore);
        highScore.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                new HighScoreW(data.getHighScores(),null);
            }
        });
        
        frame.setBounds(10, 10, 865, 710);
        frame.setBackground(Color.DARK_GRAY);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
