
package LevelChoose;

import Model.Easy;
import Model.Hard;
import Model.Level;
import Model.Medium;


public class LevelChooser implements LevelFactory{

    private int Level;
    
     
    @Override
    public Level getLevel(int level) {
        this.Level = level;
        switch (level) {
            case 1 ->{ return new Easy();}
            case 2 ->{return new Medium();}
            case 3->{return new Hard();}
            default->{
                throw new RuntimeException("not a level Number"); 
            }
        }
    } 
}
