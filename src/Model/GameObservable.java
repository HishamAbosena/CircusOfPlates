
package Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.LinkedList;


interface ObservableStackOfPlates{
    public void setOvserver(GameObserver observer); //not using 'add' because we only have one observer 
   
    public void notifyOvserver(LinkedList<GameObject> stack);
}
