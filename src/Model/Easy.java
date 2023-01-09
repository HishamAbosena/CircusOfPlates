package Model;


public class Easy implements Level{
    
    @Override
    public String getBackgroundPath() {
        return "/gamebackground.png";
    }

    @Override
    public String getClownPath() {
        return "/clown.png";
    }

    @Override
    public int getNumberofPlates() {
       return 3;
    }

    @Override
    public int getPlateDistanse() {
        return 200;
    }

    @Override
    public int getBombDistance() {
        return 200*3;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public int getMystreyBoxDistance() {
        return 200*5;
    }

    @Override
    public int getDinametDistance() {
        return 200*6;
    }

    @Override
    public boolean isIsBomb() {
        return true;
    }

    @Override
    public boolean isIsDinamet() {
        return false;
    }

    @Override
    public boolean isIsMystryox() {
        return  false;
    }

    @Override
    public boolean isWinner(int score) {
           return score>=10;
    }
}
