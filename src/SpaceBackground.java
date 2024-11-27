import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SpaceBackground {
    private final int FRAME_WIDTH;
    private final int FRAME_HEIGHT;
    private static final int STAR_WIDTH = 2;
    private static final int STAR_HEIGHT = 2;
    private static final int STAR_AMOUNT = 50;
    private static final int MIN_STAR_SPEED = 1;
    private static final int MAX_STAR_SPEED = 6;
    ArrayList<Star> stars;
    SpaceBackground(int frameWidth, int frameHeight) {
        FRAME_WIDTH = frameWidth;
        FRAME_HEIGHT = frameHeight;
        Random random = new Random();
        stars = new ArrayList<>();

        //create stars
        for(int i = 0; i < STAR_AMOUNT; i++) {

            int randX = random.nextInt(0, FRAME_WIDTH);
            int randSpeed = random.nextInt(MIN_STAR_SPEED, MAX_STAR_SPEED);
            stars.add(new Star(randX, 0, STAR_WIDTH, STAR_HEIGHT, randSpeed));
        }
    }
    public void draw(Graphics window) {
        for (Star star : stars) {
            star.draw(window);
        }
    }
    public void move() {
        Random random = new Random();
        for(Star star : stars) {
            star.move("DOWN");
            if(star.getY() > FRAME_HEIGHT){
                //reset star
                int randX = random.nextInt(0, FRAME_WIDTH);
                int randSpeed = random.nextInt(MIN_STAR_SPEED, MAX_STAR_SPEED);
                star.setPos(randX, 0);
                star.setSpeed(randSpeed);
            }
        }
    }
}

class Star extends MovingThing {
    private int speed;
    Star(int x, int y, int w, int h, int s) {
        super(x, y, w, h);
        speed = s;
    }

    @Override
    public void move(String direction) {
        switch (direction) {
            case "LEFT" -> setX(getX() - getSpeed());
            case "RIGHT" -> setX(getX() + getSpeed());
            case "UP" -> setY(getY() - getSpeed());
            case "DOWN" -> setY(getY() + getSpeed());
        }
    }

    @Override
    public void draw(Graphics window) {
        window.setColor(Color.white);
        window.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void setSpeed(int s) {
        speed = s;
    }

    @Override
    public int getSpeed() {
        return speed;
    }
}