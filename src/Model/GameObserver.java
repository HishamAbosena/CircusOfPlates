package Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.LinkedList;


public interface GameObserver {
    public void removeAndGain(LinkedList<GameObject> stack);
    
}
