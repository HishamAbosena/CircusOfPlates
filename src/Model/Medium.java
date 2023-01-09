package Model;



public class Medium implements Level{

    
        @Override
    public String getBackgroundPath() {
        return "/background2.png";
    }

    @Override
    public String getClownPath() {
        return "/clown.png";
    }

    @Override
    public int getNumberofPlates() {
       return 4;
    }

    @Override
    public int getPlateDistanse() {
        return 160;
    }

    @Override
    public int getBombDistance() {
        return 160*3;
    }

    @Override
    public int getSpeed() {
        return 2;
    }
        @Override
    public int getMystreyBoxDistance() {
        return 160*4;
    }

    @Override
    public int getDinametDistance() {
        return 160*5;
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
        return  true;
    }

    @Override
    public boolean isWinner(int score) {
        return score>=20;
    }
}
