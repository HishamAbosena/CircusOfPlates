package Model;


public class Hard implements Level{

    @Override
    public String getBackgroundPath() {
        return "/handsa.png";
    }

    @Override
    public String getClownPath() {
        return "/hisham3.png";
    }

    @Override
    public int getNumberofPlates() {
       return 5;
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
        return 3;
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
        return true;
    }

    @Override
    public boolean isIsMystryox() {
        return  true;
    }

    @Override
    public boolean isWinner(int score) {
        return score>=30;
    }
}
