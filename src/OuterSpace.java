import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.*;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private final int FRAME_WIDTH;
	private final int FRAME_HEIGHT;
	private final Ship ship;
	private static final int SHIP_WIDTH = 50;
	private static final int SHIP_HEIGHT = 50;
	private static final int SHIP_SPEED = 2;
    private AlienHorde horde;
	private static final int ALIEN_AMOUNT = 20;
	private static final int ALIEN_WIDTH = 40;
	private static final int ALIEN_HEIGHT = 40;
	private static final int ALIEN_SPEED = 1;
	private static final int ALIEN_MOVE_DISTANCE = 200;
	private Bullets shots;
	private static final int AMMO_WIDTH = 10;
	private static final int AMMO_HEIGHT = 10;
	private static final int AMMO_SPEED = 3;
	private boolean shootAlienBullets = false;
	Timer timer;
	private boolean[] keys;
	private BufferedImage back;
	private boolean gameOver = false;
	private final SpaceBackground spaceBackground;

	public OuterSpace(int frameWidth, int frameHeight)
	{
		FRAME_WIDTH = frameWidth;
		FRAME_HEIGHT = frameHeight;
		setBackground(Color.black);

		keys = new boolean[5];

		double shipY = ((double)FRAME_HEIGHT) * (2/3.);
		ship = new Ship(FRAME_WIDTH/2 - SHIP_WIDTH/2, (int)shipY, SHIP_WIDTH, SHIP_HEIGHT, SHIP_SPEED);

		horde = new AlienHorde(FRAME_HEIGHT);
		for (int i = 0; i < ALIEN_AMOUNT; i++) {
			//arbitrary numbers fix later
			horde.add(new Alien((i%10)*70 + 50, (i/10)*70 + 50,
					ALIEN_WIDTH, ALIEN_HEIGHT, ALIEN_SPEED, ALIEN_MOVE_DISTANCE));
		}

		shots = new Bullets();

		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				shootAlienBullets = true;
			}
		}, 1000, 1000);
		spaceBackground = new SpaceBackground(FRAME_WIDTH, FRAME_HEIGHT);

		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
	}
	public void update(Graphics window)
   {
	   paint(window);
   }

	public void paint(Graphics window)
	{
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back == null)
		   back = (BufferedImage)(createImage(getWidth(), getHeight()));

		//create a graphics reference to the background image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		//space background
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0, 0, getWidth(), getHeight());
		spaceBackground.move();
		spaceBackground.draw(graphToBack);

		//ship movement
		if(keys[0]) {ship.move("LEFT");}
		if(keys[1]) {ship.move("RIGHT");}
		if(keys[2]) {ship.move("UP");}
		if(keys[3]) {ship.move("DOWN");}

		//ship within bounds
		if(ship.getX() < 0) {
			ship.setX(0);
		}
		//more arbitrary numbers (roughly ship pixel width)
		if(ship.getX() > FRAME_WIDTH - 66) {
			ship.setX(FRAME_WIDTH - 66);
		}
		if(ship.getY() < 0) {
			ship.setY(0);
		}
		//yeah..
		if(ship.getY() > FRAME_HEIGHT - 89) {
			ship.setY(FRAME_HEIGHT - 89);
		}
		gameOver = ship.isDead(horde.getAllShots());

		//shoot ammo
		if(keys[4]) {
			//position center of ship and right above
			shots.add(new Ammo(ship.getX() + (ship.getWidth()/2) - (AMMO_WIDTH/2),
					ship.getY() + (ship.getHeight()/10), AMMO_WIDTH, AMMO_HEIGHT, AMMO_SPEED));
			keys[4] = false;
		}
		shots.moveEmAll();
		shots.cleanEmUp();

		horde.moveEmAll();

		if(shootAlienBullets) {
			horde.shootBullets();
			shootAlienBullets = false;
		}

		shots.drawEmAll(graphToBack);
		ship.draw(graphToBack);
		horde.drawEmAll(graphToBack);
		horde.removeDeadOnes(shots.getShots());

		twoDGraph.drawImage(back, null, 0, 0);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(!gameOver) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {keys[0] = true;}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {keys[1] = true;}
			if (e.getKeyCode() == KeyEvent.VK_UP) {keys[2] = true;}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {keys[3] = true;}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {keys[4] = false;}
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if(!gameOver) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {keys[0] = false;}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {keys[1] = false;}
			if (e.getKeyCode() == KeyEvent.VK_UP) {keys[2] = false;}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {keys[3] = false;}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {keys[4] = true;}
			repaint();
		}
	}

	public void keyTyped(KeyEvent e)
	{
      //no code needed here
	}

   public void run()
   {
	   try
	   {
		   while(!gameOver)
		   {
			   Thread.currentThread().sleep(5);
			   repaint();
		   }
		   timer.cancel();
	   }catch(Exception e)
	   {
		   System.out.println(e);
	   }
   }
}

