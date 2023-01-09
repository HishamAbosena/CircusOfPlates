
package Model;


public class Mysterybox extends MovableGameObject{
    private int operation;
    
    public Mysterybox(int x, int y, String path, int width, int height, int type, int xChange,int operation, boolean horizontalOnly) {
        super(x, y, path, width, height, type, xChange, horizontalOnly);
        this.operation = operation;
    } 

    public int getOperation() {
        return operation;
    }
    
}
