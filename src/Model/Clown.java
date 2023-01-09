
package Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.LinkedList;
import java.util.List;

public class Clown extends PictureObject implements ObservableStackOfPlates{
    private final List<GameObject> leftStack = new LinkedList<>();    //array list of the plates on the controlable "clown" object left hand
    private final List<GameObject> rightStack = new LinkedList<>();   //array list of the plates on the controlable "clown" object right hand
    private GameObserver observer;
    private int rightCounter = 1,leftCounter=1; //the number of plates with the same color on each clonwn's hand
    private static Clown instance;
    
    public static Clown getClownInstance(int x, int y, String path,int width,int height,boolean horizontalOnly,GameObserver observer){
        if(instance == null){
         instance = new Clown(x,y,path,width,height,horizontalOnly,observer);
        }
           
        return instance;
    }
    
    private Clown(int x, int y, String path,int width,int height,boolean horizontalOnly,GameObserver observer) {
        super(x, y, path,width,height,horizontalOnly);
        this.observer  = observer;
    }
    public boolean intersectLeft(GameObject object){
        return Math.abs(   this.getX() - object.getX()) <= 72 && object.getY()+object.getHeight() >= this.getY() && object.getY() < this.getY() ;
    }
    
    public boolean intersectRight(GameObject object){
        return Math.abs(   (this.getX() + this.getWidth() -70) - object.getX()) <= 72 && object.getY()+object.getHeight() >= this.getY() && object.getY() < this.getY() ;
    }    


    public List<GameObject> getLeftStack() {
        return leftStack;
    }

    public List<GameObject> getRightStack() {
        return rightStack;
    }
    
    public void addPlate(GameObject gameObject) {
        Plate newPlate = (Plate)gameObject;
        Plate topPlate;
        int stackSide = ((MovableGameObject)gameObject).getStack();
        
        switch (stackSide){
            case 1 ->{
                if(!leftStack.isEmpty()){                       
                    topPlate = (Plate)leftStack.get(leftStack.size()-1);
                if(topPlate.getPath().equals(newPlate.getPath()))
                    leftCounter++;
                else
                    leftCounter=1;
                }
                else
                    leftCounter = 1;
                leftStack.add(gameObject);
                if(leftCounter == 3){
                    notifyOvserver((LinkedList<GameObject>) leftStack);
                    leftCounter=1;/// 3 consecutive same color plates occured in left
                } 

                System.err.println("addddddddddddddddddddddddddddddddddddddddddddd          left   "+ leftStack.size());
            }
            
            case 2 -> {
                if(!rightStack.isEmpty()){                       
                    topPlate = (Plate)rightStack.get(rightStack.size()-1);
                if(topPlate.getPath().equals(newPlate.getPath()))
                    rightCounter++;
                else
                    rightCounter=1;
                }
                else 
                    rightCounter = 1;
                rightStack.add(gameObject);
                if(rightCounter == 3){
                    notifyOvserver((LinkedList<GameObject>) rightStack);rightCounter=1;/// 3 consecutive same color plates occured in left
                } 

                System.err.println("addddddddddddddddddddddddddddddddddddddddddddd          right   "+ rightStack.size());

   
            }
        }
        System.err.println("left counte ::::::::::::::;;" + leftCounter);
        
        
        System.err.println("right counte ::::::::::::::;;" + rightCounter);
        System.err.println(((MovableGameObject)gameObject).getPath());

    }

    
    public void removePlate(GameObject gameObject) {
        int stackSide = ((MovableGameObject)gameObject).getStack();
        switch (stackSide){
            case 1 ->{
                leftStack.remove(gameObject);
            }
            
            case 2 -> {
                rightStack.remove(gameObject);
            }
            default ->{
                
            }
        }    
    }

    @Override
    public void notifyOvserver(LinkedList<GameObject> stack) {
        observer.removeAndGain(stack);
    }

    @Override
    public void setOvserver(GameObserver observer) {
        this.observer = observer;
    }
}
