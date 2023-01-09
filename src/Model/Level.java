package Model;



public interface Level {
    
    public String getBackgroundPath();
    public String getClownPath();
    public int getNumberofPlates();
    public int getPlateDistanse();
    public int getBombDistance();
    public int getMystreyBoxDistance();
    public int getDinametDistance();
    public int getSpeed();
    public boolean isIsBomb() ;
    public boolean isIsDinamet() ;
    public boolean isIsMystryox() ;
    public boolean isWinner(int score);
}
