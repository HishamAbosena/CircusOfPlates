
package Model;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Clown extends PictureObject{
    
    public Clown(int x, int y, String path,int width,int height,boolean horizontalOnly) {
        super(x, y, path,width,height,horizontalOnly);
    }
    public boolean intersectLeft(GameObject object){
        return Math.abs(   this.getX() - object.getX()) <= 72 && object.getY()+object.getHeight() >= this.getY() && object.getY() < this.getY() ;
    }
    
    public boolean intersectRight(GameObject object){
        return Math.abs(   (this.getX() + this.getWidth() -70) - object.getX()) <= 72 && object.getY()+object.getHeight() >= this.getY() && object.getY() < this.getY() ;
    }    
}
