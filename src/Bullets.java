import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Bullets {
	private List<Ammo> ammo;

	public Bullets()
	{
		ammo = new ArrayList<>();
	}

	public void add(Ammo al)
	{
		ammo.add(al);
	}

	public void drawEmAll(Graphics window)
	{
		for (Ammo bullet : ammo) {
			bullet.draw(window);
		}
	}

	public void moveEmAll()
	{
		for(Ammo bullet : ammo) {
			bullet.move("UP");
		}
	}

	public void cleanEmUp()
	{
		ammo.removeIf(bullet -> bullet.getY() < -bullet.getWidth());
	}

	public List<Ammo> getShots()
	{
		return ammo;
	}

	public String toString()
	{
		return "";
	}
}
