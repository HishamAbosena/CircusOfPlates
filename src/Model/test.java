
package Model;

import eg.edu.alexu.csd.oop.game.GameEngine;
import java.util.logging.Level;
import java.util.logging.Logger;


public class test {
    public static int CLOWNWORLDWIDTH  = 1000;
    public static int CLOWNWORLDHEIGHT = 700;
   
    public static void main(String[] args) {
        try {
            GameEngine.start("Very Simple Game in 99 Line of Code", new ClownWorld(CLOWNWORLDWIDTH, CLOWNWORLDHEIGHT,1));
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
