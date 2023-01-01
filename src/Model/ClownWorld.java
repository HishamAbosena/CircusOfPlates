package Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;

public class ClownWorld implements World {

    private static final int MAX_TIME = 1 * 60 * 1000;	// 1 minute
    private final long startTime = System.currentTimeMillis();
    private int score = 0;
    private int width;
    private int height;
    private final List<GameObject> controlable = new LinkedList<>();
    private final List<GameObject> movable = new LinkedList<>();
    private final List<GameObject> constant = new LinkedList<>();
    private final List<GameObject> leftStack = new LinkedList<>();
    private final List<GameObject> rightStack = new LinkedList<>();

    private Plate lastPlate1;
    private Plate lastPlate2;
    private Plate lastPlate3;
    private Plate lastPlate4;

    public ClownWorld(int width, int height, int level) throws CloneNotSupportedException {
        this.width = width;
        this.height = height;
//        GameObject background;
//        background = new PictureObject(0, 0, ImageIO.read(getClass().getClassLoader().getResourceAsStream("handsa.png")),width,height,false);
        GameObject background = new PictureObject(0, 0, "assets/handsa.png",width,height,false);
        int lineHeight = 10;
        int lineWidth = 300;
        int x1 = 0;
        int y1 = 30;
        int x2 = 0;
        int y2 = 100;
        int x3 = 700;
        int y3 = 30;
        int x4 = 700;
        int y4 = 100;
        constant.add(background);
        constant.add(new Line(x1, y1, lineWidth, lineHeight, Color.BLACK, 1));
        constant.add(new Line(x2, y2, lineWidth, lineHeight, Color.BLACK, 2));
        constant.add(new Line(x3, y3, lineWidth, lineHeight, Color.BLACK, 3));
        constant.add(new Line(x4, y4, lineWidth, lineHeight, Color.BLACK, 4));

        x3 = width - 75;
        x4 = width - 75;
        for (int i = 0; i < 20; i++) {
            lastPlate1 = new Plate(x1, y1 - 10, "Plate" + getRandomNum(1, 3) + ".png", 75, 10, 1, getRandomNumNotZero(-1, 1), false);
            lastPlate2 = new Plate(x2, y2 - 10, "Plate" + getRandomNum(1, 3) + ".png", 75, 10, 2, getRandomNumNotZero(-1, 1), false);
            lastPlate3 = new Plate(x3, y3 - 10, "Plate" + getRandomNum(1, 3) + ".png", 75, 10, 3, getRandomNumNotZero(-1, 1), false);
            lastPlate4 = new Plate(x4, y4 - 10, "Plate" + getRandomNum(1, 3) + ".png", 75, 10, 4, getRandomNumNotZero(-1, 1), false);
            movable.add(lastPlate1);
            movable.add(lastPlate2);
            movable.add(lastPlate3);
            movable.add(lastPlate4);
            x1 -= 160;
            x2 -= 160;
            x3 += 160;
            x4 += 160;
        }
        controlable.add(new Clown(300, 400, "hisham2.png", 250, 200, true));

    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
        for (int i = 0; i < movable.size() - 1; i++) {
            Plate object = (Plate) movable.get(i);
            try {
                Plate clone = object.clone();
                Clown clown = ((Clown) (controlable.get(0)));
                updatePlates(object, object.getType());
                if (leftStack.isEmpty()) {
                    if (clown.intersectLeft(object)) {
                        addToLeftStack(object, true);
                        reBuild(clone);
                    }
                } else if (intersect(object, leftStack.get(leftStack.size() - 1))) {
                    addToLeftStack(object, false);
                    reBuild(clone);
                }
                if (rightStack.isEmpty()) {
                    if (clown.intersectRight(object)) {
                        addToRightStack(object, true);
                        reBuild(clone);
                    }
                } else if (intersect(object, rightStack.get(rightStack.size() - 1))) {
                    addToRightStack(object, false);
                    reBuild(clone);
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            updateLeft();
            updateRight();
            if (object.getY() == height) {
                reBuild(object);
            }

        }
        System.err.println(movable.size());
        return !timeout;
    }

    private boolean intersect(GameObject object1, GameObject object2) {
        return Math.abs(object1.getX() - object2.getX()) <= object1.getWidth() && Math.abs(object1.getY() - object2.getY()) <= object1.getHeight();
    }

    private void addToLeftStack(GameObject object, boolean LeftStackEmpty) {
        Plate plate = (Plate) object;
        Clown clown = (Clown) controlable.get(0);
        if (LeftStackEmpty) {
        } else {
            plate.setY(leftStack.get(leftStack.size() - 1).getY() - plate.getHeight());
        }
        plate.setStack(1);
        plate.setHorizontalOnly(true);
        leftStack.add(plate);
        movable.remove(plate);
        controlable.add(object);
        plate.setLastStack(clown);
    }

    private void addToRightStack(GameObject object, boolean RightStackEmpty) {
        Plate plate = (Plate) object;
        Clown clown = (Clown) controlable.get(0);
        if (RightStackEmpty) {
        } else {
            plate.setY(rightStack.get(rightStack.size() - 1).getY() - plate.getHeight());
        }
        plate.setStack(2);
        plate.setHorizontalOnly(true);
        rightStack.add(plate);
        movable.remove(plate);
        controlable.add(object);
        plate.setLastStack(clown);
    }

    private void reBuild(GameObject object) {
        Plate plate = (Plate) object;
        if (!movable.contains(object)) {
            movable.add(object);
        }

        int xOfLastItem, yOfLastItem;
        int plateWidth = plate.getHeight();
        switch (plate.getType()) {
            case 1: {
                xOfLastItem = lastPlate1.getX() - 160;
                yOfLastItem = constant.get(1).getY() - plateWidth;
                object.setX(xOfLastItem);
                object.setY(yOfLastItem);
                lastPlate1 = plate;
                break;
            }
            case 2: {
                xOfLastItem = lastPlate2.getX() - 160;
                yOfLastItem = constant.get(2).getY() - plateWidth;
                object.setX(xOfLastItem);
                object.setY(yOfLastItem);
                lastPlate2 = plate;
                break;
            }
            case 3: {
                xOfLastItem = lastPlate3.getX() + 160;
                yOfLastItem = constant.get(3).getY() - plateWidth;
                object.setX(xOfLastItem);
                object.setY(yOfLastItem);
                lastPlate3 = plate;
                break;
            }
            case 4: {
                xOfLastItem = lastPlate4.getX() + 160;
                yOfLastItem = constant.get(4).getY() - plateWidth;
                object.setX(xOfLastItem);
                object.setY(yOfLastItem);
                lastPlate4 = plate;
                break;
            }
            default: {

                break;
            }

        }

    }

    private void updatePlates(Plate object, int type) {
        switch (type) {
            case 1: {
                if (object.getX() + getSpeed() <= ((Line) constant.get(1)).getWidth() && object.getY() + object.getHeight() <= ((Line) constant.get(1)).getY()) {
                    object.setX(object.getX() + getSpeed());
                } else {
                    object.setX(object.getX() + object.getXChange());
                    object.setY(object.getY() + getSpeed());
                }
                break;
            }
            case 2: {
                if (object.getX() + getSpeed() <= ((Line) constant.get(2)).getWidth() && object.getY() + object.getHeight() <= ((Line) constant.get(2)).getY()) {
                    object.setX(object.getX() + getSpeed());
                } else {
                    object.setX(object.getX() + object.getXChange());
                    object.setY(object.getY() + getSpeed());
                }
                break;
            }
            case 3: {
                if (object.getX() + getSpeed() > width - ((Line) constant.get(3)).getWidth() - object.getWidth() && object.getY() + object.getHeight() <= ((Line) constant.get(3)).getY()) {
                    object.setX(object.getX() - getSpeed());
                } else {
                    object.setX(object.getX() + object.getXChange());
                    object.setY(object.getY() + getSpeed());
                }
                break;
            }
            case 4: {
                if (object.getX() + getSpeed() > width - ((Line) constant.get(4)).getWidth() - object.getWidth() && object.getY() + object.getHeight() <= ((Line) constant.get(4)).getY()) {
                    object.setX(object.getX() - getSpeed());
                } else {
                    object.setX(object.getX() + object.getXChange());
                    object.setY(object.getY() + getSpeed());
                }
                break;
            }
            default: {
                break;
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

    private void updateLeft() {
        if (leftStack.size() >= 3) {
            Plate p1 = (Plate) leftStack.get(leftStack.size() - 1);
            Plate p2 = (Plate) leftStack.get(leftStack.size() - 2);
            Plate p3 = (Plate) leftStack.get(leftStack.size() - 3);
            if (p1.getPath().equals(p2.getPath()) && p2.getPath().equals(p3.getPath())) {
                leftStack.remove(leftStack.size() - 1);
                leftStack.remove(leftStack.size() - 1);
                leftStack.remove(leftStack.size() - 1);
                controlable.remove(p1);
                controlable.remove(p2);
                controlable.remove(p3);
                score++;
            }
        }
    }

    private void updateRight() {
        if (rightStack.size() >= 3) {
            Plate p1 = (Plate) rightStack.get(rightStack.size() - 1);
            Plate p2 = (Plate) rightStack.get(rightStack.size() - 2);
            Plate p3 = (Plate) rightStack.get(rightStack.size() - 3);
            if (p1.getPath().equals(p2.getPath()) && p2.getPath().equals(p3.getPath())) {
                rightStack.remove(rightStack.size() - 1);
                rightStack.remove(rightStack.size() - 1);
                rightStack.remove(rightStack.size() - 1);
                controlable.remove(p1);
                controlable.remove(p2);
                controlable.remove(p3);
                score++;
            }
        }
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
        return 2;
    }

    @Override
    public int getControlSpeed() {
        return 20;
    }
}
