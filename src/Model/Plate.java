
package Model;


public class Plate extends MovableGameObject implements Cloneable{
        
    public Plate(int x,int y,String path,int width,int height,int type,int xChange,boolean horizontalOnly){
        super(x, y, path, width, height,type,xChange, horizontalOnly);
    }
}


