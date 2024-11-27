import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlienHorde
{
	private static final int ALIEN_AMMO_WIDTH = 10;
	private static final int ALIEN_AMMO_HEIGHT = 10;
	private static final int ALIEN_AMMO_SPEED = 2;
	private List<Alien> aliens;
	private AlienBullets shots;
	public AlienHorde(int frameHeight)
	{
		aliens = new ArrayList<>();
		shots = new AlienBullets(frameHeight);
	}
	public List<AlienAmmo> getAllShots() {
		return shots.getShots();
	}

	public void add(Alien al)
	{
		aliens.add(al);
	}

	public void drawEmAll( Graphics window )
	{
		for(Alien alien : aliens) {
			alien.draw(window);
		}
		shots.drawEmAll(window);
	}

	public void moveEmAll()
	{
		for(Alien alien : aliens) {
			alien.move("");
		}
		shots.moveEmAll();
		shots.cleanEmUp();
	}

	public void shootBullets() {
		Random random = new Random();
		for (Alien alien : aliens) {
			if(random.nextInt(2) == 0) {
				shots.add(new AlienAmmo(alien.getX() + (alien.getWidth()/2) - (ALIEN_AMMO_WIDTH/2),
						alien.getY() + alien.getHeight(), ALIEN_AMMO_WIDTH, ALIEN_AMMO_HEIGHT, ALIEN_AMMO_SPEED));
			}
		}
	}

	public void removeDeadOnes(List<Ammo> shots)
	{
		for(int i = aliens.size()-1; i >= 0; i--) {
			for(int j = shots.size()-1; j >= 0; j--) {
				Alien alien = aliens.get(i);
				Ammo ammo = shots.get(j);
				int alienX = alien.getX() + (alien.getWidth()/2);
				int alienY = alien.getY() + (alien.getHeight()/2);
				int ammoX = ammo.getX() + (ammo.getWidth()/2);
				int ammoY = ammo.getY() + (ammo.getHeight()/2);
				double distance = Math.sqrt((alienX - ammoX)*(alienX - ammoX) +
						(alienY - ammoY)*(alienY - ammoY));

				if(distance <= alien.getWidth()*(1/2.)) {
					aliens.remove(i);
					shots.remove(j);
					break;
				}
			}
		}
	}

	public String toString()
	{
		return "";
	}
}
