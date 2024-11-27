import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.util.*;

public class Ship extends MovingThing
{
	private int speed;
	private BufferedImage image;

	public Ship()
	{
		this(10,10,50,50,5);
	}

	public Ship(int x, int y)
	{
	   this(x, y, 50, 50, 5);
	}

	public Ship(int x, int y, int s)
	{
	   this(x, y, 50, 50, s);
	}

	public Ship(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h);
		speed = s;
		try
		{
			URL url = getClass().getResource("/images/ship.png");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
		}
	}


	public void setSpeed(int s)
	{
	   speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}

	public int getImageWidth() {
		return image.getWidth();
	}
	public int getImageHeight() {
		return image.getHeight();
	}

	public void move(String direction)
	{
		switch (direction) {
			case "LEFT" -> setX(getX() - getSpeed());
			case "RIGHT" -> setX(getX() + getSpeed());
			case "UP" -> setY(getY() - getSpeed());
			case "DOWN" -> setY(getY() + getSpeed());
		}
	}
	public boolean isDead(List<AlienAmmo> shots) {
		for(int j = shots.size()-1; j >= 0; j--) {
			AlienAmmo alienAmmo = shots.get(j);
			int shipX = getX() + (getWidth()/2);
			int shipY = getY() + (getHeight()/2);
			int alienAmmoX = alienAmmo.getX() + (alienAmmo.getWidth()/2);
			int alienAmmoY = alienAmmo.getY() + (alienAmmo.getHeight()/2);

			double distance = Math.sqrt((shipX - alienAmmoX)*(shipX - alienAmmoX) +
					(shipY - alienAmmoY)*(shipY - alienAmmoY));

			if(distance <= getWidth()*(1/3.)) {
				return true;
			}
		}
		return false;
	}

	public void draw(Graphics window)
	{
		window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);

		//show collider
		/*
		window.setColor(Color.green);
		int shipCenterX = getX() + (getWidth()/2);
		int shipCenterY = getY() + (getHeight()/2);
		int radius = (int)(getWidth()*(1/3.));
		window.drawOval(shipCenterX - radius, shipCenterY - radius, 2 * radius, 2 * radius);
		 */
	}

	public String toString()
	{
		return super.toString() + " " + getSpeed();
	}
}
