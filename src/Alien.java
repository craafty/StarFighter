import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import javax.imageio.ImageIO;

public class Alien extends MovingThing
{

	private int speed;
	private final int moveDist;
	private final int startX;
	//private final int startY;
	private boolean movingRight = true;
	private BufferedImage image;

	public Alien()
	{
		this(0, 0, 30, 30, 0, 0);
	}

	public Alien(int x, int y)
	{
		this(x, y, 30, 30, 0, 0);
	}

	public Alien(int x, int y, int s)
	{
		this(x, y, 30, 30, s, 0);
	}

	public Alien(int x, int y, int w, int h, int s, int m)
	{
		super(x, y, w, h);
		startX = x;
		//startY = y;
		speed = s;
		moveDist = m;
		try
		{
			URL url = getClass().getResource("/images/alien.png");
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

   public void move(String direction) {
	   if(movingRight && getX() >= startX + moveDist)
		   movingRight = false;
	   if(!movingRight && getX() <= startX - moveDist)
		   movingRight = true;

	   //final decision
	   if(movingRight) setX(getX() + getSpeed());
	   else setX(getX() - getSpeed());
	}

	public void draw(Graphics window)
	{
		window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	public String toString()
	{
		return "alien";
	}
}
