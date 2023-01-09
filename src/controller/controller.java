/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import LevelChoose.LevelChooser;
import Model.ClownWorld;
import eg.edu.alexu.csd.oop.game.GameEngine;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import view.MainWindow;

/**
 *
 * @author yousef abdou
 */
public class controller {
    private final int CLOWNWORLDWIDTH  = 1000;
    private final int CLOWNWORLDHEIGHT = 700;
    private int level=0;
    
     public void setGameWorld(int level) throws CloneNotSupportedException{
         this.level=level+1;
        LevelChooser levelChooser = new LevelChooser();
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem pauseMenuItem = new JMenuItem("Pause");
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
        JMenuItem nextLevelMenuItem = new JMenuItem("Next Level");
        menu.add(nextLevelMenuItem);
        menu.addSeparator();
        menu.add(pauseMenuItem);
        menu.add(resumeMenuItem);
        menuBar.add(menu);
        GameEngine.GameController gameController = GameEngine.start("Very Simple Game in 600 Line of Code", 
                new ClownWorld(CLOWNWORLDWIDTH, CLOWNWORLDHEIGHT, levelChooser.getLevel(level)),menuBar);
        
        pauseMenuItem.addActionListener((ActionEvent e) -> {
            gameController.pause();
        });
        resumeMenuItem.addActionListener((ActionEvent e) -> {
            gameController.resume();
        });
        nextLevelMenuItem.addActionListener((ActionEvent e) -> {
            try {
                gameController.changeWorld(new ClownWorld(CLOWNWORLDWIDTH, CLOWNWORLDHEIGHT,levelChooser.getLevel(this.level++) ));
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
