import java.awt.*;

public class AlienAmmo extends MovingThing {
    private int speed;
    public AlienAmmo() {
        this(0, 0, 10, 10, 3);
    }
    public AlienAmmo(int x, int y) {
        this(x, y, 10, 10, 3);
    }
    public AlienAmmo(int x, int y, int w, int h, int s) {
        super(x, y, w, h);
        speed = s;
    }

    @Override
    public void setSpeed(int s) {
        speed = s;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void draw(Graphics window) {
        window.setColor(Color.red);
        window.fillRect(getX(), getY(), getWidth(), getHeight());
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
}
