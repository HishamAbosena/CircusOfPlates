package Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClownWorld implements World, GameObserver {

    private static final int MAX_TIME = 1 * 60 * 1000;	// 1 minute
    private long startTime = System.currentTimeMillis();
    private int score = 0;     // count the score of the user
    private final int width;      //width of the game window
    private final int height;      //height of the game window
    private final List<GameObject> controlable = new LinkedList<>();
    private final List<GameObject> movable = new LinkedList<>();
    private final List<GameObject> constant = new LinkedList<>();
    private boolean timeOut;

    private MovableGameObject[] lastPlates = new Plate[4];
    private MovableGameObject[] lastBombs = new Bomb[4];
    private MovableGameObject[] lastMystrey = new Mysterybox[4];
    private MovableGameObject[] lestDynamit = new Dynamit[4];

    // data  that changes based on the level
    private Level level;

    public ClownWorld(int width, int height, Level level) throws CloneNotSupportedException {
        this.width = width;
        this.height = height;
        this.level = level; //setting the level of the game
        createMovableObjects(75, 10, 30, 30, 40, 40); //create all constants game objects like "plates", "bombs" etc      
        GameObject background = new PictureObject(0, 0, level.getBackgroundPath(), width, height, false);
        createConstantObjects(level.getBackgroundPath(), 300, 10);       //create all constants game objects like "backgroud", " lines"
        createControlableObjects(250, 200);
    }

    public void newGame() { //restarting the time for the newGame
        startTime = System.currentTimeMillis();
        //refresh();
    }

    private void createConstantObjects(String backgroundPath, int lineWidth, int lineHeight) { //lineDistancevertically is the distance between each line vertically
        int x1 = 0;
        int y1 = 30;
        int x2 = 0;
        int y2 = 100;
        int x3 = 700;
        int y3 = 30;
        int x4 = 700;
        int y4 = 100;
        GameObject background = new PictureObject(0, 0, backgroundPath, width, height, false);
        constant.add(background);
        constant.add(new Line(0, 30, lineWidth, lineHeight, Color.BLACK, 1));
        constant.add(new Line(0, 100, lineWidth, lineHeight, Color.BLACK, 2));
        constant.add(new Line(width - lineWidth, 30, lineWidth, lineHeight, Color.BLACK, 3));
        constant.add(new Line(width - lineWidth, 100, lineWidth, lineHeight, Color.BLACK, 4));
    }

    private void createMovableObjects(int plateWidth, int plateHeight, int bombWidth, int bombHieght, int mystryBoxWidth, int mystreyBoxHeight) {
        int x1p = 0, x1b = 0, x1m = 0, x1d = 0;
        int y1p = 30, y1b = 30, y1m = 30, y1d = 30;
        int x2p = 0, x2b = 100, x2m = 100, x2d = 100;
        int y2p = 100, y2b = 100, y2m = 100, y2d = 100;
        int x3p = width - plateWidth, x3b = width - plateWidth + 150, x3m = width - plateWidth + 150, x3d = x3m;
        int y3p = 30, y3b = 30, y3m = 30, y3d = y3m;
        int x4p = width - plateWidth, x4b = width - plateWidth + 200, x4m = width - plateWidth + 200, x4d = x4m;
        int y4p = 100, y4b = 100, y4m = 100, y4d = y4m;

        for (int i = 0; i < 20; i++) {
            System.err.println("                 " + x1p);
            lastPlates[0] = new Plate(x1p -= level.getPlateDistanse(), y1p - 10, "/plate" + getRandomNum(1, level.getNumberofPlates()) + ".png", plateWidth, plateHeight, 1, getRandomNumNotZero(-1, 1), false);
            lastPlates[1] = new Plate(x2p -= level.getPlateDistanse(), y2p - 10, "/plate" + getRandomNum(1, level.getNumberofPlates()) + ".png", plateWidth, plateHeight, 2, getRandomNumNotZero(-1, 1), false);
            lastPlates[2] = new Plate(x3p += level.getPlateDistanse(), y3p - 10, "/plate" + getRandomNum(1, level.getNumberofPlates()) + ".png", plateWidth, plateHeight, 3, getRandomNumNotZero(-1, 1), false);
            lastPlates[3] = new Plate(x4p += level.getPlateDistanse(), y4p - 10, "/plate" + getRandomNum(1, level.getNumberofPlates()) + ".png", plateWidth, plateHeight, 4, getRandomNumNotZero(-1, 1), false);
            movable.add(lastPlates[0]);
            movable.add(lastPlates[1]);
            movable.add(lastPlates[2]);
            movable.add(lastPlates[3]);
        }
        if (level.isIsBomb()) {
            for (int i = 0; i < 5; i++) {
                System.err.println("                 " + x1b);
                lastBombs[0] = new Bomb(x1b -= level.getBombDistance(), y1b - 30, "/bomb.png", bombWidth, bombHieght, 1, getRandomNumNotZero(-1, 1), false);
                lastBombs[1] = new Bomb(x2b -= level.getBombDistance(), y2b - 30, "/bomb.png", bombWidth, bombHieght, 2, getRandomNumNotZero(-1, 1), false);
                lastBombs[2] = new Bomb(x3b += level.getBombDistance(), y3b - 30, "/bomb.png", bombWidth, bombHieght, 3, getRandomNumNotZero(-1, 1), false);
                lastBombs[3] = new Bomb(x4b += level.getBombDistance(), y4b - 30, "/bomb.png", bombWidth, bombHieght, 4, getRandomNumNotZero(-1, 1), false);
                movable.add(lastBombs[0]);
                movable.add(lastBombs[1]);
                movable.add(lastBombs[2]);
                movable.add(lastBombs[3]);
            }
        }
        if (level.isIsMystryox()) {
            for (int i = 0; i < 5; i++) {
                System.err.println("                 " + x1m);

                lastMystrey[0] = new Mysterybox(x1m -= level.getMystreyBoxDistance(), y1m - 40, "/mysterybox.png", mystryBoxWidth, mystreyBoxHeight, 1, getRandomNumNotZero(-1, 1), getRandomNumNotZero(1, 3), false);
                lastMystrey[1] = new Mysterybox(x2m -= level.getMystreyBoxDistance(), y2m - 40, "/mysterybox.png", mystryBoxWidth, mystreyBoxHeight, 2, getRandomNumNotZero(-1, 1), getRandomNumNotZero(1, 3), false);
                lastMystrey[2] = new Mysterybox(x3m += level.getMystreyBoxDistance(), y3m - 40, "/mysterybox.png", mystryBoxWidth, mystreyBoxHeight, 3, getRandomNumNotZero(-1, 1), getRandomNumNotZero(1, 3), false);
                lastMystrey[3] = new Mysterybox(x4m += level.getMystreyBoxDistance(), y4m - 40, "/mysterybox.png", mystryBoxWidth, mystreyBoxHeight, 4, getRandomNumNotZero(-1, 1), getRandomNumNotZero(1, 3), false);
                movable.add(lastMystrey[0]);
                movable.add(lastMystrey[1]);
                movable.add(lastMystrey[2]);
                movable.add(lastMystrey[3]);
            }
        }
        if (level.isIsDinamet()) {
            for (int i = 0; i < 5; i++) {
                System.err.println("  " + x1d);
                lestDynamit[0] = new Dynamit(x1d -= level.getDinametDistance(), y1d - 60, "/Dynamit.png", 60, 60, 1, getRandomNumNotZero(-1, 1), false);
                lestDynamit[1] = new Dynamit(x2d -= level.getDinametDistance(), y2d - 60, "/Dynamit.png", 60, 60, 2, getRandomNumNotZero(-1, 1), false);
                lestDynamit[2] = new Dynamit(x3d += level.getDinametDistance(), y3d - 60, "/Dynamit.png", 60, 60, 3, getRandomNumNotZero(-1, 1), false);
                lestDynamit[3] = new Dynamit(x4d += level.getDinametDistance(), y4d - 60, "/Dynamit.png", 60, 60, 4, getRandomNumNotZero(-1, 1), false);
                movable.add(lestDynamit[0]);
                movable.add(lestDynamit[1]);
                movable.add(lestDynamit[2]);
                movable.add(lestDynamit[3]);
            }
        }

    }

    private void createControlableObjects(int clownWidth, int clownHeight) {
        controlable.add(Clown.getClownInstance(300, 400, level.getClownPath(), clownWidth, clownHeight, true, this));
    }

    @Override
    public boolean refresh() {
        int i = 0;
        timeOut = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
        Iterator<GameObject> it = movable.iterator();
        while (it.hasNext()) {

       // for ( i = 0; i < movable.size() - 1; i++) {
            MovableGameObject object = (MovableGameObject) movable.get(i);
            try {
                MovableGameObject clone = object.clone();
                Clown clown = ((Clown) (controlable.get(0)));
                object.moveTo(width, (Line) constant.get(object.getType()), getSpeed());
                if (clown.getLeftStack().isEmpty()) {
                    if (clown.intersectLeft(object)) {
                        if (object instanceof Plate) {
                            addToLeftStack(object, true);
                        } else if (object instanceof Mysterybox) {
                            if (((Mysterybox) object).getOperation() == 1) {
                                removeAllPoints();
                            } else if (((Mysterybox) object).getOperation() == 2) {
                                duplicateScore();
                            }
                            movable.remove(object);
                        } else if (object instanceof Dynamit) {
                            endGame();
                            movable.remove(object);
                        }
                        reBuild(clone);
                        break;
                    }
                } else if (intersect(object, clown.getLeftStack().get(clown.getLeftStack().size() - 1))) {
                    if (object instanceof Plate) {
                        addToLeftStack(object, false);
                    } else if (object instanceof Dynamit) {
                        endGame();
                        movable.remove(object);
                    } else if (object instanceof Bomb) {
                        removeAllLeft(object);
                        movable.remove(object);
                    } else if (object instanceof Mysterybox) {
                        if (((Mysterybox) object).getOperation() == 1) {
                            removeAllPoints();
                        } else if (((Mysterybox) object).getOperation() == 2) {
                            duplicateScore();
                        }
                        movable.remove(object);
                    }

                    reBuild(clone);
                    break;
                }
                if (clown.getRightStack().isEmpty()) {
                    if (clown.intersectRight(object)) {
                        if (object instanceof Plate) {
                            addToRightStack(object, true);
                            reBuild(clone);
                        }
                        break;
                    }
                } else if (intersect(object, clown.getRightStack().get(clown.getRightStack().size() - 1))) {
                    if (object instanceof Plate) {
                        addToRightStack(object, false);
                    } else if (object instanceof Bomb) {
                        removeAllRight(object);
                        movable.remove(object);
                    } else if (object instanceof Mysterybox) {
                        if (((Mysterybox) object).getOperation() == 1) {
                            removeAllPoints();
                        } else if (((Mysterybox) object).getOperation() == 2) {
                            duplicateScore();
                        }
                        movable.remove(object);
                    }
                    reBuild(clone);
                    break;
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            if (object.getY() == height) {
                reBuild(object);
            }
            
       // }
        i++;
        it.next();
        }
        return !timeOut;
    }

    private void removeAllPoints() {
        score = 0;
    }

    private void duplicateScore() {
        System.err.println("s bef" + score);
        score = score * 2;
        System.err.println("s af" + score);
    }

    private void endGame() {
        timeOut = true;
    }

    private boolean intersect(GameObject object1, GameObject object2) {
        return Math.abs(object1.getX() - object2.getX()) <= object1.getWidth() && Math.abs(object1.getY() - object2.getY()) <= object1.getHeight();
    }

    private void removeAllLeft(GameObject object) {
        Clown clown = ((Clown) controlable.get(0));
        for (GameObject plate : clown.getLeftStack()) {
            ((MovableGameObject) plate).setVisible(false);
            controlable.remove(plate);
        }
        (clown.getLeftStack()).clear();
        ((MovableGameObject) object).setVisible(false);

    }

    private void removeAllRight(GameObject object) {
        Clown clown = ((Clown) controlable.get(0));
        for (GameObject plate : clown.getRightStack()) {
            ((MovableGameObject) plate).setVisible(false);
            controlable.remove(plate);
        }
        (clown.getRightStack()).clear();
        ((MovableGameObject) object).setVisible(false);
    }

    private void addToLeftStack(GameObject object, boolean LeftStackEmpty) {
        Plate plate = (Plate) object;
        Clown clown = (Clown) controlable.get(0);
        if (LeftStackEmpty) {
        } else {
            plate.setY(clown.getLeftStack().get(clown.getLeftStack().size() - 1).getY() - plate.getHeight());
        }
        plate.setStack(1);
        plate.setHorizontalOnly(true);
        movable.remove(plate);
        controlable.add(object);
        plate.setLastStack(clown);
        clown.addPlate(object);
    }

    private void addToRightStack(GameObject object, boolean RightStackEmpty) {
        Plate plate = (Plate) object;
        Clown clown = (Clown) controlable.get(0);
        if (RightStackEmpty) {
        } else {
            plate.setY(clown.getRightStack().get(clown.getRightStack().size() - 1).getY() - plate.getHeight());
        }
        plate.setStack(2);
        plate.setHorizontalOnly(true);
        movable.remove(plate);
        controlable.add(object);
        plate.setLastStack(clown);
        clown.addPlate(object);

    }

    private void reBuild(GameObject object) {
        MovableGameObject gameObject = (MovableGameObject) object;
        if (!movable.contains(object)) {
            movable.add(object);
        }
        int xDistance = 0;
        MovableGameObject[] lastGameObject = new MovableGameObject[4];
        int xOfLastItem, yOfLastItem;
        int gameObjectHeihgt = object.getHeight();
        if (gameObject instanceof Plate) {
            xDistance = level.getPlateDistanse();
            lastGameObject = lastPlates;
        } else if (object instanceof Bomb) {
            xDistance = level.getBombDistance();
            lastGameObject = lastBombs;
        } else if (object instanceof Mysterybox) {
            xDistance = level.getMystreyBoxDistance();
            lastGameObject = lastMystrey;
        } else if (object instanceof Dynamit) {
            xDistance = level.getDinametDistance();
            lastGameObject = lestDynamit;
        }
        switch (gameObject.getType()) {
            case 1 -> {
                xOfLastItem = lastGameObject[0].getX() - xDistance;
                yOfLastItem = constant.get(1).getY() - gameObjectHeihgt;
                object.setX(xOfLastItem);
                object.setY(yOfLastItem);
                lastGameObject[0] = gameObject;
            }
            case 2 -> {
                xOfLastItem = lastGameObject[1].getX() - xDistance;
                yOfLastItem = constant.get(2).getY() - gameObjectHeihgt;
                object.setX(xOfLastItem);
                object.setY(yOfLastItem);
                lastGameObject[1] = gameObject;
            }
            case 3 -> {
                xOfLastItem = lastGameObject[2].getX() + xDistance;
                yOfLastItem = constant.get(3).getY() - gameObjectHeihgt;
                object.setX(xOfLastItem);
                object.setY(yOfLastItem);
                lastGameObject[2] = gameObject;
            }
            case 4 -> {
                xOfLastItem = lastGameObject[3].getX() + xDistance;
                yOfLastItem = constant.get(4).getY() - gameObjectHeihgt;
                object.setX(xOfLastItem);
                object.setY(yOfLastItem);
                lastGameObject[3] = gameObject;
            }
            default -> {
            }

        }

    }

    private int getRandomNum(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    private int getRandomNumNotZero(int min, int max) {
        int temp = (int) (Math.random() * 2);
        if (temp == 0) {
            return (int) (Math.random() * (-1 - min) + min);
        } else {
            return (int) (Math.random() * (max - 1) + 1);
        }
    }

    @Override
    public void removeAndGain(LinkedList<GameObject> stack) { //only called to remove 3 plates and gain a point to the player
        GameObject p1 = stack.get(stack.size() - 1);
        GameObject p2 = stack.get(stack.size() - 2);
        GameObject p3 = stack.get(stack.size() - 3);

        System.err.println("size before  ;;;;;;;;;;;;;;;;;;;;;;;;;;;" + stack.size());
        System.err.println("size before2 ;;;;;;;;;;;;;;;;;;;;;;;;;;;" + controlable.size());
        stack.remove(p1);
        stack.remove(p2);
        stack.remove(p3);
        controlable.remove(p1);
        controlable.remove(p2);
        controlable.remove(p3);
        System.err.println("size aftear ;;;;;;;;;;;;;;;;;;;;;;;;;;;" + stack.size());
        System.err.println("size After2 ;;;;;;;;;;;;;;;;;;;;;;;;;;;" + controlable.size());
        score++;

    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return movable;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return controlable;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getStatus() {
        return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000);	// update status
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public int getControlSpeed() {
        return 20;
    }

}
