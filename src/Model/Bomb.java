
package Model;

public class Bomb extends MovableGameObject implements Cloneable{
    
    public Bomb(int x, int y, String path, int width, int height, int type, int xChange, boolean horizontalOnly) {
        super(x, y, path, width, height, type, xChange, horizontalOnly);
    }
    
}
