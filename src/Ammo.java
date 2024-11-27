import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing
{
	private int speed;

	public Ammo()
	{
		this(0, 0, 10, 10, 3);
	}

	public Ammo(int x, int y)
	{
		this(x, y, 10, 10, 3);
	}

	public Ammo(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h);
		speed = s;
	}

	@Override
	public void setSpeed(int s)
	{
		speed = s;
	}

	@Override
	public int getSpeed()
	{
	   return speed;
	}

	@Override
	public void draw(Graphics window)
	{
		window.setColor(Color.yellow);
		window.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void move(String direction)
	{
		switch (direction) {
			case "LEFT" -> setX(getX() - getSpeed());
			case "RIGHT" -> setX(getX() + getSpeed());
			case "UP" -> setY(getY() - getSpeed());
			case "DOWN" -> setY(getY() + getSpeed());
		}
	}

	public String toString()
	{
		return "";
	}
}
